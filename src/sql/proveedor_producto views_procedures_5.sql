/*----------------------------   MANTENIMIENTO A LA TABLA PROVEEDOR_PRODUCTO   ----------------------------*/


/* ============================================================
   1. VISTA PROVEEDOR (PK/FK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_proveedor AS
SELECT
  id_proveedor              AS `ID`,
  ruc                       AS `RUC`,
  razon_social              AS `Razón Social (Nombre del Proveedor)`,
  telefono_proveedor        AS `Teléfono de contacto`,
  correo_proveedor          AS `Correo de contacto`,
  direccion_proveedor       AS `Dirección`,
  observacion_proveedor     AS `Observaciones`,
  estado                    AS  `Estado`
FROM proveedor;



/* ============================================================
   2. VISTA PRODUCTO (PK/FK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_producto AS
SELECT
    p.id_producto               AS `ID`,
    p.nombre_producto           AS `Nombre del Producto`,
    um.nombre_unidad_medida     AS `Unidad de Medida`,
    CONCAT('S/ ', FORMAT(p.precio_producto, 2)) AS `Precio`,
    p.stock_minimo              AS `Stock Mínimo`,
    p.stock_actual              AS `Stock Actual`,
    p.observacion_producto      AS `Observaciones`,
    p.estado                    AS `Estado`
FROM producto p
INNER JOIN unidad_medida um ON p.id_unidad_medida = um.id_unidad_medida;



/* ============================================================
   3. VISTA PROVEEDOR_PRODUCTO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_proveedor_producto AS
SELECT
    pp.id_proveedor         AS `ID Proveedor`,
    pp.id_producto          AS `ID Producto`,
    pr.razon_social         AS `Proveedor`,
    p.nombre_producto       AS `Producto`,
    pp.precio_compra        AS `Precio de Compra`,
    pp.tiempo_entrega       AS `Tiempo de Entrega (días)`,
    pp.fecha_registro       AS `Fecha de Registro`,
    pp.estado               AS `Estado`
FROM proveedor_producto pp
INNER JOIN producto p ON pp.id_producto = p.id_producto
INNER JOIN proveedor pr ON pp.id_proveedor = pr.id_proveedor;



/* ============================================================
   4. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DELIMITER $$
DROP PROCEDURE IF EXISTS insertar_proveedor_producto$$
CREATE PROCEDURE insertar_proveedor_producto(
    IN p_id_proveedor INT,
    IN p_id_producto INT,
    IN p_precio_compra DECIMAL(10,2),
    IN p_tiempo_entrega_dias INT
)
BEGIN
    DECLARE v_count INT;
    DECLARE v_estado_existente TINYINT;

    -- 1. Validaciones de valores (CHECKs lógicos)
    IF p_precio_compra < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El precio no puede ser negativo.', MYSQL_ERRNO = 1041;
    END IF;

    -- 2. VALIDACIÓN DE DUPLICADOS (Crucial por tu Primary Key)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente 
    FROM proveedor_producto 
    WHERE id_proveedor = p_id_proveedor AND id_producto = p_id_producto;

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Esta relación ya existe pero está INACTIVA. Debe reactivarla en lugar de insertar.',
            MYSQL_ERRNO = 1046;
        ELSE
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'El proveedor ya tiene asignado este producto actualmente.',
            MYSQL_ERRNO = 1047;
        END IF;
    END IF;

    -- 3. Inserción (Si pasó las validaciones)
    INSERT INTO proveedor_producto (
        id_proveedor, 
        id_producto, 
        precio_compra, 
        tiempo_entrega, 
        fecha_registro, 
        estado
    ) VALUES (
        p_id_proveedor, 
        p_id_producto, 
        p_precio_compra, 
        p_tiempo_entrega_dias, 
        CURDATE(), 
        1
    );

    SELECT 'Relación registrada con éxito.' AS mensaje;

END$$
DELIMITER ;



/* ============================================================
   4. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_proveedor_producto;
DELIMITER $$

CREATE PROCEDURE buscar_proveedor_producto (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_proveedor_producto
    WHERE `Proveedor`       LIKE CONCAT('%', p_param, '%')
       OR `Producto`        LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;



/* ============================================================
   5. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DROP PROCEDURE IF EXISTS actualizar_proveedorproducto;
DELIMITER //

CREATE PROCEDURE actualizar_proveedorproducto (
    IN p_id_proveedor  INT,
    IN p_id_producto   INT,
    IN p_precio_compra DECIMAL(10,2),
    IN p_tiempo        INT,
    IN p_fecha         DATE
)
BEGIN
    -- Declaración de variables para validaciones
    DECLARE v_estado_prov TINYINT DEFAULT NULL;
    DECLARE v_estado_prod TINYINT DEFAULT NULL;
    DECLARE v_estado_rel  TINYINT DEFAULT NULL;

    -- 1. Validar existencia y estado del PROVEEDOR
    SELECT estado INTO v_estado_prov FROM proveedor WHERE id_proveedor = p_id_proveedor;
    
    IF v_estado_prov IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El proveedor no existe.', MYSQL_ERRNO = 20168;
    ELSEIF v_estado_prov = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El proveedor está inactivo.', MYSQL_ERRNO = 20175;
    END IF;

    -- 2. Validar existencia y estado del PRODUCTO
    SELECT estado INTO v_estado_prod FROM producto WHERE id_producto = p_id_producto;
    
    IF v_estado_prod IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El producto no existe.', MYSQL_ERRNO = 20169;
    ELSEIF v_estado_prod = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El producto está inactivo.', MYSQL_ERRNO = 20176;
    END IF;

    -- 3. Validar existencia de la RELACIÓN y su estado actual
    SELECT estado INTO v_estado_rel 
    FROM proveedor_producto 
    WHERE id_proveedor = p_id_proveedor AND id_producto = p_id_producto;
    
    IF v_estado_rel IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe la relación proveedor-producto.', MYSQL_ERRNO = 20173;
    ELSEIF v_estado_rel = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La relación existe pero está INACTIVA. Debe reactivarla.', MYSQL_ERRNO = 1046;
    END IF;

    -- 4. Validaciones de valores (CHECKs lógicos)
    IF p_precio_compra IS NULL OR p_precio_compra < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio de compra no puede ser negativo.', MYSQL_ERRNO = 20170;
    END IF;

    IF p_tiempo IS NULL OR p_tiempo < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El tiempo de entrega no puede ser negativo.', MYSQL_ERRNO = 20171;
    END IF;

    -- 5. Validar fecha (No nula ni futura)
    IF p_fecha IS NULL OR p_fecha > CURDATE() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Fecha de registro inválida o futura.', MYSQL_ERRNO = 20172;
    END IF;

    -- 6. Ejecutar actualización
    UPDATE proveedor_producto
    SET precio_compra  = p_precio_compra,
        tiempo_entrega = p_tiempo,
        fecha_registro = p_fecha
    WHERE id_proveedor = p_id_proveedor  
      AND id_producto  = p_id_producto;

    -- 7. Verificar si se realizó algún cambio
    IF ROW_COUNT() = 0 THEN
        SELECT 'No se realizaron cambios (los datos ingresados son idénticos a los actuales).' AS mensaje;
    ELSE
        SELECT CONCAT('Relación Proveedor-Producto actualizada correctamente.') AS mensaje;
    END IF;

END //

DELIMITER ;



/* ============================================================
   6. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_proveedorproducto;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_proveedorproducto (
    IN p_id_proveedor INT,
    IN p_id_producto INT,
    IN p_nuevo_estado TINYINT
)
BEGIN
    -- 1. Verificar que la relación exista
    IF NOT EXISTS (
        SELECT 1 FROM proveedor_producto 
        WHERE id_proveedor = p_id_proveedor AND id_producto = p_id_producto
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No existe una relación registrada entre este proveedor y este producto.', 
        MYSQL_ERRNO = 3010; -- Código personalizado
    END IF;

    -- 2. Validar que el nuevo estado sea válido (0 o 1)
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 (inactivo) o 1 (activo).', 
        MYSQL_ERRNO = 3011;
    END IF;

    -- 3. Actualizar estado de la relación
    UPDATE proveedor_producto
    SET estado = p_nuevo_estado
    WHERE id_proveedor = p_id_proveedor AND id_producto = p_id_producto;

    -- 4. Mensaje informativo de éxito
    SELECT CONCAT('Relación Proveedor(ID:', p_id_proveedor, ') - Producto(ID:', p_id_producto, 
                  ') actualizada a estado: ', IF(p_nuevo_estado = 1, 'ACTIVO', 'INACTIVO')) AS mensaje;
END$$

DELIMITER ;