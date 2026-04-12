/*----------------------------   MANTENIMIENTO A LA TABLA PRODUCTO   ----------------------------*/


/* ============================================================
   1. VISTA UNIDAD_MEDIDA (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_unidad_medida AS
SELECT
  nombre_unidad_medida      AS `Unidad de Medida`,
  abreviatura               AS `Abreviatura`
FROM unidad_medida;



/* ============================================================
   2. VISTA PRODUCTO (PK)
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
   3. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_producto;
DELIMITER $$

CREATE PROCEDURE insertar_producto(
    IN p_nombre VARCHAR(255),
    IN p_precio_unitario DECIMAL(10,2),
    IN p_stock_minimo INT,
    IN p_stock_actual INT,
    IN p_observacion TEXT,
    IN p_id_unidad_medida INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_estado_um TINYINT DEFAULT NULL; -- Inicializado aquí directamente
    DECLARE v_obs TEXT DEFAULT TRIM(p_observacion);

    -- 1. Validar nombre vacío
    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El nombre del producto no puede estar vacío.',
        MYSQL_ERRNO = 1029;
    END IF;

    -- 2. Validar nombre duplicado
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente
    FROM producto
    WHERE UPPER(TRIM(nombre_producto)) = UPPER(TRIM(p_nombre));

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El producto ya existe pero está INACTIVO. Debe reactivarlo.',
            MYSQL_ERRNO = 1043;
        ELSE
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un producto ACTIVO con este nombre.',
            MYSQL_ERRNO = 1034;
        END IF;
    END IF;

    -- 3. Validar precios y stock
    IF p_precio_unitario < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio debe ser >= 0.', MYSQL_ERRNO = 1030;
    END IF;

    IF p_stock_minimo < 0 OR p_stock_actual < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock no puede ser negativo.', MYSQL_ERRNO = 1031;
    END IF;

    -- 4. Validar existencia y estado de la Unidad de Medida
    SELECT estado INTO v_estado_um 
    FROM unidad_medida 
    WHERE id_unidad_medida = p_id_unidad_medida;

    IF v_estado_um IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida no existe.', MYSQL_ERRNO = 1033;
    ELSEIF v_estado_um = 0 THEN -- En MariaDB se prefiere ELSEIF todo junto o bloques claros
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida está inactiva.', MYSQL_ERRNO = 1035;
    END IF;

    -- 5. Ejecutar inserción
    INSERT INTO producto(
        nombre_producto,
        precio_producto,
        stock_minimo,
        stock_actual,
        observacion_producto,
        id_unidad_medida,
        estado 
    )
    VALUES(
        TRIM(p_nombre),
        p_precio_unitario,
        p_stock_minimo,
        p_stock_actual,
        NULLIF(v_obs, ''),
        p_id_unidad_medida,
        1
    );

    SELECT CONCAT('Producto "', TRIM(p_nombre), '" registrado correctamente.') AS mensaje;

END$$
DELIMITER ;



/* ============================================================
   4. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_producto;
DELIMITER $$

CREATE PROCEDURE buscar_producto (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_producto
    WHERE `Nombre del Producto`     LIKE CONCAT('%', p_param, '%')
       OR `Unidad de Medida`        LIKE CONCAT('%', p_param, '%')
       OR `Precio`                  LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;



/* ============================================================
   5. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DROP PROCEDURE IF EXISTS actualizar_producto;
DELIMITER //

CREATE PROCEDURE actualizar_producto (
    IN p_id_producto       INT,
    IN p_nombre            VARCHAR(100),
    IN p_precio_unitario   DECIMAL(10,2),
    IN p_stock_minimo      INT,
    IN p_stock_actual      INT,
    IN p_observacion       TEXT,
    IN p_id_unidad_medida  INT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_count INT DEFAULT 0;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_estado_um TINYINT DEFAULT NULL;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre);
    DECLARE v_obs        TEXT         DEFAULT TRIM(p_observacion);
    DECLARE v_existe_prod INT DEFAULT 0;

    -- 1. Validar ID y existencia del producto
    IF p_id_producto IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El ID del producto no puede ser NULL.', MYSQL_ERRNO = 20140;
    END IF;

    SELECT COUNT(*) INTO v_existe_prod FROM producto WHERE id_producto = p_id_producto;
    IF v_existe_prod = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el producto con el ID proporcionado.', MYSQL_ERRNO = 20141;
    END IF;

    -- 2. Validar nombre (No vacío)
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del producto no puede estar vacío.', MYSQL_ERRNO = 20142;
    END IF;

    -- 3. Validar duplicado (Nombre ya ocupado por OTRO ID)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente
    FROM producto 
    WHERE UPPER(TRIM(nombre_producto)) = UPPER(v_nombre) 
      AND id_producto <> p_id_producto;

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: El nombre ya pertenece a un producto INACTIVO. Reactívelo.', 
            MYSQL_ERRNO = 1043;
        ELSE
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Ya existe otro producto ACTIVO con ese nombre.', 
            MYSQL_ERRNO = 20143;
        END IF;
    END IF;

    -- 4. Validar precio y stocks
    IF p_precio_unitario IS NULL OR p_precio_unitario < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio no puede ser negativo.', MYSQL_ERRNO = 20144;
    END IF;

    IF p_stock_minimo < 0 OR p_stock_actual < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock no puede ser negativo.', MYSQL_ERRNO = 20145;
    END IF;

    -- 5. Lógica de inventario
    IF p_stock_minimo > p_stock_actual THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock mínimo no puede ser mayor al actual.', MYSQL_ERRNO = 20147;
    END IF;

    -- 6. Validar unidad de medida (Existencia y Estado)
    SELECT estado INTO v_estado_um FROM unidad_medida WHERE id_unidad_medida = p_id_unidad_medida;
    
    IF v_estado_um IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida no existe.', MYSQL_ERRNO = 20149;
    ELSEIF v_estado_um = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No puede asignar una unidad de medida inactiva.', MYSQL_ERRNO = 20151;
    END IF;

    -- 7. Validar longitud de observación
    IF v_obs IS NOT NULL AND CHAR_LENGTH(v_obs) > 500 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Observación demasiado larga (máx 500).', MYSQL_ERRNO = 20165;
    END IF;

    -- 8. Ejecutar actualización
    UPDATE producto
    SET nombre_producto  = v_nombre,
        precio_producto  = p_precio_unitario,
        stock_minimo     = p_stock_minimo,
        stock_actual     = p_stock_actual,
        observacion_producto = NULLIF(v_obs, ''),
        id_unidad_medida = p_id_unidad_medida
    WHERE id_producto = p_id_producto;

    SELECT CONCAT('Producto "', v_nombre, '" actualizado correctamente.') AS mensaje;

END //

DELIMITER ;



/* ============================================================
   6. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_producto;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_producto (
    IN p_id_producto INT,
    IN p_nuevo_estado TINYINT
)
BEGIN
    -- 1. Verificar que el producto exista
    IF NOT EXISTS (
        SELECT 1 FROM producto WHERE id_producto = p_id_producto
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El producto no existe.', 
        MYSQL_ERRNO = 20180;
    END IF;

    -- 2. Validar que el nuevo estado sea válido (0 o 1)
    -- Aunque la tabla tiene un CHECK, validarlo aquí permite enviar un mensaje personalizado
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 (inactivo) o 1 (activo).', 
        MYSQL_ERRNO = 20181;
    END IF;

    -- 3. Actualizar estado
    UPDATE producto
    SET estado = p_nuevo_estado
    WHERE id_producto = p_id_producto;

    SELECT CONCAT('Producto "', (SELECT nombre_producto FROM producto WHERE id_producto = p_id_producto), 
                  '" actualizado a estado: ', p_nuevo_estado) AS mensaje;
END$$
DELIMITER ;