/*----------------------------   MANTENIMIENTO A LA TABLA CLIENTE   ----------------------------*/

USE db_restaurant;
/* ============================================================
   1. VISTA CLIENTE (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_cliente AS
SELECT
    id_cliente              AS `ID`,
    dni_cliente             AS `DNI`,
    nombre_cliente          AS `Nombre de Cliente`,
    apellido_cliente        AS `Apellido de Cliente`,
    correo_cliente          AS `Correo Personal`,
    telefono_cliente        AS `Telefono Personal`,
    observacion_cliente     AS `Observaciones`,
    estado                  AS  `Estado`
FROM cliente;



/* ============================================================
   2. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_cliente;
DELIMITER $$

CREATE PROCEDURE insertar_cliente (
    IN p_dni         CHAR(8),
    IN p_nombre      VARCHAR(255),
    IN p_apellido    VARCHAR(255),
    IN p_correo      VARCHAR(255),
    IN p_telefono    VARCHAR(50),
    IN p_observacion VARCHAR(500)
)
BEGIN
    -- Declaración de variables para control
    DECLARE v_count INT;
    DECLARE v_estado_existente TINYINT;

    -- 1. MANEJO DE ERRORES Y TRANSACCIONES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    -- 2. VALIDACIÓN DE DNI (Exactamente 8 números)
    IF LENGTH(TRIM(p_dni)) <> 8 OR TRIM(p_dni) NOT REGEXP '^[0-9]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El DNI debe tener exactamente 8 dígitos numéricos.',
        MYSQL_ERRNO = 1001;
    END IF;

    -- 3. VALIDACIÓN DE CAMPOS OBLIGATORIOS
    IF TRIM(p_nombre) = '' OR TRIM(p_apellido) = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'El nombre y apellido son obligatorios.', 
        MYSQL_ERRNO = 1002;
    END IF;

    -- 4. VALIDACIÓN DE FORMATO DE CORREO
    IF p_correo IS NOT NULL AND TRIM(p_correo) <> '' THEN
        IF TRIM(p_correo) NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'El formato del correo es inválido.', 
            MYSQL_ERRNO = 1004;
        END IF;
    END IF;

    -- 5. VALIDACIÓN DE DUPLICADOS (DNI y Estado)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente 
    FROM cliente 
    WHERE dni_cliente = TRIM(p_dni);

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            -- Caso: El cliente existe pero está inactivo
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El DNI ya existe pero el cliente está INACTIVO. Debe reactivarlo.',
            MYSQL_ERRNO = 4020; -- Usando el código que ya manejas en Java para inactivos
        ELSE
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un cliente activo con este DNI.',
            MYSQL_ERRNO = 1003;
        END IF;
    END IF;

    -- 6. INSERCIÓN CON NORMALIZACIÓN
    -- Usamos UPPER para nombres/apellidos y LOWER para el correo
    INSERT INTO cliente (
        dni_cliente,
        nombre_cliente,
        apellido_cliente,
        correo_cliente,
        telefono_cliente,
        observacion_cliente,
        estado
    )
    VALUES (
        TRIM(p_dni),
        UPPER(TRIM(p_nombre)),
        UPPER(TRIM(p_apellido)),
        LOWER(TRIM(p_correo)),
        TRIM(p_telefono),
        TRIM(p_observacion),
        1
    );

    COMMIT;
    
    SELECT CONCAT('Cliente ', UPPER(TRIM(p_nombre)), ' registrado correctamente.') AS mensaje;

END$$
DELIMITER ;



/* ============================================================
   3. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_cliente;
DELIMITER //

CREATE PROCEDURE buscar_cliente(
    IN p_param VARCHAR(85)
)
BEGIN
    SET p_param = TRIM(p_param);

    SELECT *
    FROM vista_cliente
    WHERE `DNI`                     LIKE CONCAT('%', p_param, '%')
       OR `Nombre de Cliente`       LIKE CONCAT('%', p_param, '%')
       OR `Apellido de Cliente`     LIKE CONCAT('%', p_param, '%')
       OR `Correo Personal`         LIKE CONCAT('%', p_param, '%')
       OR `Telefono Personal`       LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;



/* ============================================================
   4. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DELIMITER //

DROP PROCEDURE IF EXISTS actualizar_cliente //

CREATE PROCEDURE actualizar_cliente (
    IN p_id_cliente      INT,
    IN p_dni             CHAR(8),
    IN p_nombre          VARCHAR(255),
    IN p_apellido        VARCHAR(255),
    IN p_correo          VARCHAR(255),
    IN p_telefono        VARCHAR(50),
    IN p_observacion     VARCHAR(500)
)
BEGIN
    -- Declaración de variables para control
    DECLARE v_id_existe      INT;
    DECLARE v_dni_duplicado  INT;

    -- 1. MANEJO DE ERRORES Y TRANSACCIONES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL; -- Re-lanza el error para que Java lo capture
    END;

    START TRANSACTION;

    -- 2. VALIDAR EXISTENCIA DEL ID
    SELECT COUNT(*) INTO v_id_existe FROM cliente WHERE id_cliente = p_id_cliente;
    
    IF v_id_existe = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El cliente con el ID proporcionado no existe.', 
        MYSQL_ERRNO = 20085;
    END IF;

    -- 3. VALIDAR FORMATO DE DNI (Exactamente 8 números)
    IF TRIM(p_dni) NOT REGEXP '^[0-9]{8}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El DNI debe contener exactamente 8 dígitos numéricos.', 
        MYSQL_ERRNO = 20078;
    END IF;

    -- 4. VALIDAR DNI DUPLICADO (Que no lo tenga OTRO cliente)
    SELECT COUNT(*) INTO v_dni_duplicado FROM cliente 
    WHERE dni_cliente = TRIM(p_dni) AND id_cliente <> p_id_cliente;

    IF v_dni_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El DNI ingresado ya pertenece a otro cliente registrado.', 
        MYSQL_ERRNO = 20084;
    END IF;

    -- 5. VALIDAR NOMBRE Y APELLIDO (No vacíos)
    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre es un campo obligatorio.', MYSQL_ERRNO = 20080;
    END IF;

    IF p_apellido IS NULL OR TRIM(p_apellido) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El apellido es un campo obligatorio.', MYSQL_ERRNO = 20081;
    END IF;

    -- 6. VALIDAR CORREO (Regex profesional)
    IF p_correo IS NOT NULL AND TRIM(p_correo) <> '' THEN
        IF LOWER(TRIM(p_correo)) NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: El formato del correo electrónico es inválido.', 
            MYSQL_ERRNO = 20079;
        END IF;
    END IF;

    -- 7. VALIDAR TELÉFONO (Ejemplo: 9 dígitos)
    IF TRIM(p_telefono) NOT REGEXP '^[0-9]{7,15}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El teléfono debe tener entre 7 y 15 dígitos numéricos.', 
        MYSQL_ERRNO = 20082;
    END IF;

    -- 9. EJECUTAR ACTUALIZACIÓN CON NORMALIZACIÓN
    UPDATE cliente
    SET dni_cliente         = TRIM(p_dni),
        nombre_cliente      = UPPER(TRIM(p_nombre)),
        apellido_cliente    = UPPER(TRIM(p_apellido)),
        correo_cliente      = LOWER(TRIM(p_correo)),
        telefono_cliente    = TRIM(p_telefono),
        observacion_cliente = TRIM(p_observacion)
    WHERE id_cliente = p_id_cliente;

    COMMIT;
    -- Mensaje de éxito para el ResultSet de Java
    SELECT 'Cliente actualizado correctamente.' AS mensaje;

END //

DELIMITER ;



/* ============================================================
   5. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_cliente;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_cliente (
    IN p_id_cliente INT,
    IN p_nuevo_estado TINYINT
)
BEGIN
    -- 1. Verificar que el cliente exista
    IF NOT EXISTS (
        SELECT 1 FROM cliente WHERE id_cliente = p_id_cliente
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El cliente no existe.', 
        MYSQL_ERRNO = 20090; -- Código de error correlativo a tus validaciones de cliente
    END IF;

    -- 2. Validar que el nuevo estado sea válido (0 = Inactivo, 1 = Activo)
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', 
        MYSQL_ERRNO = 20091;
    END IF;

    -- 3. Actualizar estado
    UPDATE cliente
    SET estado = p_nuevo_estado
    WHERE id_cliente = p_id_cliente;

    -- 4. Confirmación para el ResultSet de Java
    SELECT CONCAT('Cliente actualizado a estado: ', IF(p_nuevo_estado = 1, 'Activo', 'Inactivo')) AS mensaje;
END$$
DELIMITER ;