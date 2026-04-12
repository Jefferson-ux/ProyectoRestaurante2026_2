/*----------------------------   MANTENIMIENTO A LA TABLA PROVEEDOR   ----------------------------*/

USE db_restaurant;
/* ============================================================
   1. VISTA PROVEEDOR (PK)
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
   2. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_proveedor;
DELIMITER $$

CREATE PROCEDURE insertar_proveedor(
    IN p_ruc CHAR(11),
    IN p_razon_social VARCHAR(255),
    IN p_telefono VARCHAR(50),
    IN p_correo VARCHAR(255),
    IN p_direccion VARCHAR(255),
    IN p_observacion TEXT
)
BEGIN
    -- Declaración de variables para control
    DECLARE v_count INT;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_msg VARCHAR(255);

    -- 1. MANEJO DE ERRORES Y TRANSACCIONES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL; -- Re-lanza el error para que Java lo capture
    END;

    START TRANSACTION;

    -- 2. VALIDACIÓN DE FORMATO (Longitud y que sea solo números)
    IF LENGTH(TRIM(p_ruc)) <> 11 OR TRIM(p_ruc) NOT REGEXP '^[0-9]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El RUC debe tener exactamente 11 dígitos numéricos.',
        MYSQL_ERRNO = 1036;
    END IF;

    -- 3. VALIDACIÓN DE CAMPOS OBLIGATORIOS
    IF TRIM(p_razon_social) = '' OR p_razon_social IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La razón social es obligatoria.', MYSQL_ERRNO = 1037;
    END IF;

    -- 4. VALIDACIÓN DE FORMATO DE CORREO (Regex profesional)
    IF TRIM(p_correo) NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El formato del correo es inválido.', MYSQL_ERRNO = 1042;
    END IF;

    -- 5. VALIDACIÓN DE DUPLICADOS (Detecta si está activo o inactivo)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente 
    FROM proveedor 
    WHERE ruc = TRIM(p_ruc);

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            -- Caso especial: El RUC existe pero fue borrado/desactivado antes
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RUC ya existe pero está INACTIVO. Debe reactivarlo.',
            MYSQL_ERRNO = 1043;
        ELSE
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un proveedor activo con este RUC.',
            MYSQL_ERRNO = 1034;
        END IF;
    END IF;

    -- 6. VALIDACIÓN DE CORREO DUPLICADO
    IF EXISTS(SELECT 1 FROM proveedor WHERE correo_proveedor = TRIM(p_correo)) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El correo electrónico ya está registrado por otro proveedor.',
        MYSQL_ERRNO = 1035;
    END IF;
    
    -- 7. Validar longitud de la observación
    IF p_observacion IS NOT NULL AND LENGTH(TRIM(p_observacion)) > 500 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La observación es demasiado larga (máximo 500 caracteres).',
        MYSQL_ERRNO = 1044; -- Código correlativo al anterior
    END IF;
    
    -- 8. INSERCIÓN CON NORMALIZACIÓN
    INSERT INTO proveedor(
        ruc, 
        razon_social, 
        telefono_proveedor, 
        correo_proveedor, 
        direccion_proveedor, 
        observacion_proveedor, 
        estado
    )
    VALUES(
        TRIM(p_ruc),
        UPPER(TRIM(p_razon_social)),
        TRIM(p_telefono),
        LOWER(TRIM(p_correo)),     
        TRIM(p_direccion),
        TRIM(p_observacion),
        1                           
    );

    COMMIT;
    
    SELECT 'Proveedor registrado exitosamente.' AS mensaje;

END$$
DELIMITER ;



/* ============================================================
   3. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_proveedor;
DELIMITER $$

CREATE PROCEDURE buscar_proveedor (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_proveedor
    WHERE `RUC` LIKE CONCAT('%', p_param, '%')
       OR `Razón Social (Nombre del Proveedor)` LIKE CONCAT('%', p_param, '%')
       OR `Teléfono de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Correo de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Dirección` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;



/* ============================================================
   4. PROCEDURE ACTUALIZAR/MODIFICAR
   ============================================================ */
DELIMITER //

DROP PROCEDURE IF EXISTS actualizar_proveedor //

CREATE PROCEDURE actualizar_proveedor (
    IN p_id_proveedor   INT,
    IN p_ruc            CHAR(11),
    IN p_razon_social   VARCHAR(150),
    IN p_telefono       VARCHAR(15),
    IN p_correo         VARCHAR(150),
    IN p_direccion      VARCHAR(150),
    IN p_observacion    TEXT
)
BEGIN
    -- Declaración de variables para control
    DECLARE v_id_existe INT;
    DECLARE v_ruc_duplicado INT;

    -- 1. MANEJO DE ERRORES Y TRANSACCIONES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    -- 2. VALIDAR EXISTENCIA DEL ID
    SELECT COUNT(*) INTO v_id_existe FROM proveedor WHERE id_proveedor = p_id_proveedor;
    
    IF v_id_existe = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El proveedor con el ID proporcionado no existe.', 
        MYSQL_ERRNO = 20161;
    END IF;

    -- 3. VALIDAR FORMATO DE RUC (Solo números y 11 dígitos)
    IF TRIM(p_ruc) NOT REGEXP '^[0-9]{11}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El RUC debe contener exactamente 11 dígitos numéricos.', 
        MYSQL_ERRNO = 20168;
    END IF;

    -- 4. VALIDAR RUC DUPLICADO (Que no lo tenga OTRO proveedor)
    SELECT COUNT(*) INTO v_ruc_duplicado FROM proveedor 
    WHERE ruc = TRIM(p_ruc) AND id_proveedor <> p_id_proveedor;

    IF v_ruc_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El RUC ingresado ya pertenece a otro proveedor registrado.', 
        MYSQL_ERRNO = 20169;
    END IF;

    -- 5. VALIDAR RAZÓN SOCIAL
    IF p_razon_social IS NULL OR TRIM(p_razon_social) = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: La razón social es un campo obligatorio.', 
        MYSQL_ERRNO = 20162;
    END IF;

    -- 6. VALIDAR CORREO (Regex profesional y duplicados)
    IF LOWER(TRIM(p_correo)) NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El formato del correo electrónico es inválido.', 
        MYSQL_ERRNO = 20164;
    END IF;

    -- 7. VALIDAR LONGITUD DE OBSERVACIÓN
    IF p_observacion IS NOT NULL AND CHAR_LENGTH(TRIM(p_observacion)) > 500 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: La observación no puede exceder los 500 caracteres.', 
        MYSQL_ERRNO = 20165;
    END IF;

    -- 8. EJECUTAR ACTUALIZACIÓN CON NORMALIZACIÓN
    UPDATE proveedor
    SET ruc                 = TRIM(p_ruc),
        razon_social        = UPPER(TRIM(p_razon_social)), -- Mantener consistencia con INSERT
        telefono_proveedor  = TRIM(p_telefono),
        correo_proveedor    = LOWER(TRIM(p_correo)),    -- Mantener consistencia con INSERT
        direccion_proveedor = TRIM(p_direccion),
        observacion_proveedor = TRIM(p_observacion)
    WHERE id_proveedor = p_id_proveedor;

    COMMIT;

END //
DELIMITER ;



/* ============================================================
   5. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_proveedor;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_proveedor (
    IN p_id_proveedor INT,
    IN p_nuevo_estado TINYINT
)
BEGIN
    -- 1. Verificar que el proveedor exista
    IF NOT EXISTS (
        SELECT 1 FROM proveedor WHERE id_proveedor = p_id_proveedor
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El proveedor no existe.', 
        MYSQL_ERRNO = 20170;
    END IF;

    -- 2. Validar que el nuevo estado sea válido (0 o 1)
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', 
        MYSQL_ERRNO = 20171;
    END IF;

    -- 3. Actualizar estado (Sin importar si tiene productos vinculados)
    UPDATE proveedor
    SET estado = p_nuevo_estado
    WHERE id_proveedor = p_id_proveedor;

    SELECT CONCAT('Proveedor actualizado a estado: ', p_nuevo_estado) AS mensaje;
END$$

DELIMITER ;