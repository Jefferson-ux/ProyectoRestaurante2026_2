/*----------------------------   MANTENIMIENTO A LA TABLA EMPLEADO   ----------------------------*/


/* ============================================================
   1. VISTA GENERO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_genero AS
SELECT
    id_genero       AS `ID`,
    nombre_genero   AS `Genero`
FROM genero;



/* ============================================================
   2. VISTA EMPLEADO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_empleado AS
SELECT
    e.id_empleado               AS `ID`,
    e.dni_empleado              AS `DNI`,
    e.nombre_empleado           AS `Nombre de Empleado`,
    e.apellido_empleado         AS `Apellido de Empleado`,
    e.fecha_nacimiento          AS `Fecha de Nacimiento`,
    e.fecha_registro            AS `Fecha de Registro`,
    e.direccion_empleado        AS `Lugar de Residencia`,
    e.correo_principal          AS `Correo Principal`,
    e.correo_secundario         AS `Correo Secundario`,
    e.telefono_principal        AS `Telefono Principal`,
    e.telefono_secundario       AS `Telefono Secundario`,
    g.nombre_genero             AS `Genero`,
    e.observacion_empleado      AS `Observaciones`,
    e.estado                    AS `Estado`
FROM empleado e
INNER JOIN genero g ON e.id_genero = g.id_genero;



/* ============================================================
   3. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_empleado;
DELIMITER $$

CREATE PROCEDURE insertar_empleado(
    IN p_dni                CHAR(8),
    IN p_nombres            VARCHAR(255),
    IN p_apellidos          VARCHAR(255),
    IN p_fecha_nacimiento   DATE,
    IN p_fecha_registro     DATETIME, 
    IN p_lugar_residencia   VARCHAR(255),
    IN p_correo1            VARCHAR(255),
    IN p_correo2            VARCHAR(255), 
    IN p_telefono1          VARCHAR(20),
    IN p_telefono2          VARCHAR(20), 
    IN p_observacion        TEXT,
    IN p_id_genero          INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_estado_g TINYINT DEFAULT NULL;
    DECLARE v_obs TEXT DEFAULT TRIM(p_observacion);

    -- 1. Validar DNI (Vacío, Formato y Duplicado)
    IF p_dni IS NULL OR TRIM(p_dni) NOT REGEXP '^[0-9]{8}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: DNI inválido (debe tener 8 dígitos).', MYSQL_ERRNO = 4001;
    END IF;

    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente
    FROM empleado WHERE dni_empleado = TRIM(p_dni);

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DNI existe pero el empleado está INACTIVO.', MYSQL_ERRNO = 4020;
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El DNI ya está registrado.', MYSQL_ERRNO = 4002;
        END IF;
    END IF;

    -- 2. Validar Nombres, Apellidos y Dirección
    IF TRIM(p_nombres) = '' OR TRIM(p_apellidos) = '' OR TRIM(p_lugar_residencia) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Nombres, apellidos y dirección obligatorios.', MYSQL_ERRNO = 4003;
    END IF;

    -- 3. Validar Edad (Mayoría de edad)
    IF p_fecha_nacimiento IS NULL OR 
       TIMESTAMPDIFF(YEAR, p_fecha_nacimiento, IFNULL(p_fecha_registro, CURRENT_TIMESTAMP)) < 18 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El empleado debe ser mayor de edad.', MYSQL_ERRNO = 5006;
    END IF;

    -- 4. Validar Correos (Formato y que no sean iguales)
    IF p_correo1 IS NULL OR p_correo1 = '' OR 
       p_correo1 NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Correo principal inválido.', MYSQL_ERRNO = 4007;
    END IF;

    IF p_correo2 IS NOT NULL AND TRIM(p_correo2) <> '' THEN
        IF p_correo2 NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Correo secundario inválido.', MYSQL_ERRNO = 4008;
        END IF;
        IF LOWER(TRIM(p_correo1)) = LOWER(TRIM(p_correo2)) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Los correos no pueden ser iguales.', MYSQL_ERRNO = 4009;
        END IF;
    END IF;

    -- 5. Validar Teléfonos (Formato y que no sean iguales)
    IF p_telefono1 IS NULL OR p_telefono1 = '' OR p_telefono1 NOT REGEXP '^[0-9]{7,15}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Teléfono principal inválido (7-15 dígitos).', MYSQL_ERRNO = 4010;
    END IF;

    IF p_telefono2 IS NOT NULL AND TRIM(p_telefono2) <> '' THEN
        IF p_telefono2 NOT REGEXP '^[0-9]{7,15}$' THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Teléfono secundario inválido.', MYSQL_ERRNO = 4011;
        END IF;
        IF TRIM(p_telefono1) = TRIM(p_telefono2) THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Los teléfonos no pueden ser iguales.', MYSQL_ERRNO = 4012;
        END IF;
    END IF;

    -- 6. Validar existencia y estado del Genero
    IF NOT EXISTS (
    SELECT 1 FROM genero WHERE id_genero = p_id_genero
) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Error: Género no existe.', MYSQL_ERRNO = 4013;
END IF;

    -- 7. Ejecutar inserción
    INSERT INTO empleado (
        dni_empleado, nombre_empleado, apellido_empleado, fecha_nacimiento, 
        fecha_registro, direccion_empleado, correo_principal, correo_secundario, 
        telefono_principal, telefono_secundario, observacion_empleado, id_genero, estado
    )
    VALUES (
        TRIM(p_dni), UPPER(TRIM(p_nombres)), UPPER(TRIM(p_apellidos)), p_fecha_nacimiento,
        IFNULL(p_fecha_registro, CURRENT_TIMESTAMP), TRIM(p_lugar_residencia), 
        LOWER(TRIM(p_correo1)), NULLIF(LOWER(TRIM(p_correo2)), ''),
        TRIM(p_telefono1), NULLIF(TRIM(p_telefono2), ''),
        NULLIF(v_obs, ''), p_id_genero, 1
    );

    SELECT CONCAT('Empleado ', UPPER(TRIM(p_nombres)), ' registrado correctamente.') AS mensaje;

END$$
DELIMITER ;

-- Llamar al procedimiento y enviar registro de prueba.
CALL insertar_empleado(
    '71234567', 'Juan Alberto', 'Pérez García', '1990-05-15',NOW(), 'Av. Las Flores 123',  
    'juan.perez@gmail.com', 'juan_work@outlook.com', '987654321', '014455667',
    'Empleado con experiencia en ventas.', 1
);



/* ============================================================
   4. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_empleado;
DELIMITER $$

CREATE PROCEDURE buscar_empleado (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_empleado
    WHERE `DNI`                     LIKE CONCAT('%', p_param, '%')
        OR `Nombre de Empleado`     LIKE CONCAT('%', p_param, '%')
        OR `Apellido de Empleado`   LIKE CONCAT('%', p_param, '%')
        OR `Fecha de Nacimiento`    LIKE CONCAT('%', p_param, '%')
        OR `Fecha de Registro`      LIKE CONCAT('%', p_param, '%')
        OR `Lugar de Residencia`    LIKE CONCAT('%', p_param, '%')
        OR `Correo Principal`       LIKE CONCAT('%', p_param, '%')
        OR `Correo Secundario`      LIKE CONCAT('%', p_param, '%')
        OR `Telefono Principal`     LIKE CONCAT('%', p_param, '%')
        OR `Telefono Secundario`    LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;



/* ============================================================
   5. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DROP PROCEDURE IF EXISTS actualizar_empleado;
DELIMITER $$

CREATE PROCEDURE actualizar_empleado (
    IN p_id_empleado        INT,
    IN p_dni                CHAR(8),
    IN p_nombres            VARCHAR(255),
    IN p_apellidos          VARCHAR(255),
    IN p_fecha_nacimiento   DATE,
    IN p_fecha_registro     DATETIME,
    IN p_lugar_residencia   VARCHAR(255),
    IN p_correo1            VARCHAR(255),
    IN p_correo2            VARCHAR(255),
    IN p_telefono1          VARCHAR(20),
    IN p_telefono2          VARCHAR(20),
    IN p_observacion        TEXT,
    IN p_id_genero          INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_estado_g TINYINT DEFAULT NULL;
    DECLARE v_estado_emp TINYINT DEFAULT NULL;
    DECLARE v_nombres   VARCHAR(255) DEFAULT UPPER(TRIM(IFNULL(p_nombres,'')));
    DECLARE v_apellidos VARCHAR(255) DEFAULT UPPER(TRIM(IFNULL(p_apellidos,'')));
    DECLARE v_obs       TEXT DEFAULT TRIM(p_observacion);
    DECLARE v_existe_emp INT DEFAULT 0;

    -- 1. Validar ID
    IF p_id_empleado IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El ID del empleado no puede ser NULL.', MYSQL_ERRNO = 20200;
    END IF;

    SELECT COUNT(*), MAX(estado) INTO v_existe_emp, v_estado_emp
    FROM empleado WHERE id_empleado = p_id_empleado;

    IF v_existe_emp = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No existe el empleado con el ID proporcionado.', MYSQL_ERRNO = 20201;
    END IF;

    IF v_estado_emp = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se puede actualizar un empleado INACTIVO.', MYSQL_ERRNO = 20215;
    END IF;

    -- 2. Validar DNI
    IF p_dni IS NULL OR TRIM(p_dni) NOT REGEXP '^[0-9]{8}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: DNI inválido (8 dígitos).', MYSQL_ERRNO = 20202;
    END IF;

    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente
    FROM empleado 
    WHERE dni_empleado = TRIM(p_dni) AND id_empleado <> p_id_empleado;

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: DNI pertenece a empleado INACTIVO.', MYSQL_ERRNO = 1043;
        ELSE
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: DNI ya registrado en otro empleado.', MYSQL_ERRNO = 20203;
        END IF;
    END IF;

    -- 3. Validar datos básicos
    IF v_nombres = '' OR v_apellidos = '' OR p_lugar_residencia IS NULL OR TRIM(p_lugar_residencia) = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Nombres, apellidos y dirección obligatorios.', MYSQL_ERRNO = 20204;
    END IF;

    -- 4. Validar edad
    IF p_fecha_nacimiento IS NULL OR 
       TIMESTAMPDIFF(YEAR, p_fecha_nacimiento, IFNULL(p_fecha_registro, CURRENT_TIMESTAMP)) < 18 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Debe ser mayor de edad.', MYSQL_ERRNO = 5006;
    END IF;

    -- 5. Correos
    IF p_correo1 IS NULL OR p_correo1 = '' OR 
       p_correo1 NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Correo principal inválido.', MYSQL_ERRNO = 20207;
    END IF;

    IF p_correo2 IS NOT NULL AND TRIM(p_correo2) <> '' THEN
        IF p_correo2 NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Correo secundario inválido.', MYSQL_ERRNO = 20208;
        END IF;

        IF LOWER(TRIM(p_correo1)) = LOWER(TRIM(p_correo2)) THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Los correos no pueden ser iguales.', MYSQL_ERRNO = 20209;
        END IF;
    END IF;

    -- 6. Teléfonos
    IF p_telefono1 IS NULL OR p_telefono1 = '' OR p_telefono1 NOT REGEXP '^[0-9]{7,15}$' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Teléfono principal inválido.', MYSQL_ERRNO = 20210;
    END IF;

    IF p_telefono2 IS NOT NULL AND TRIM(p_telefono2) <> '' THEN
        IF p_telefono2 NOT REGEXP '^[0-9]{7,15}$' THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Teléfono secundario inválido.', MYSQL_ERRNO = 20211;
        END IF;

        IF TRIM(p_telefono1) = TRIM(p_telefono2) THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Error: Los teléfonos no pueden ser iguales.', MYSQL_ERRNO = 20212;
        END IF;
    END IF;

    -- 7. Género
    IF NOT EXISTS (
    SELECT 1 FROM genero WHERE id_genero = p_id_genero
) THEN
    SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Error: Género no existe.', MYSQL_ERRNO = 20213;
END IF;

    -- 8. Observación
    IF v_obs IS NOT NULL AND CHAR_LENGTH(v_obs) > 500 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Observación demasiado larga.', MYSQL_ERRNO = 20165;
    END IF;

    -- 9. UPDATE (🔥 sin tocar fecha_registro)
    UPDATE empleado
    SET dni_empleado         = TRIM(p_dni),
        nombre_empleado      = v_nombres,
        apellido_empleado    = v_apellidos,
        fecha_nacimiento     = p_fecha_nacimiento,
        direccion_empleado   = TRIM(p_lugar_residencia),
        correo_principal     = LOWER(TRIM(p_correo1)),
        correo_secundario    = NULLIF(LOWER(TRIM(p_correo2)), ''),
        telefono_principal   = TRIM(p_telefono1),
        telefono_secundario  = NULLIF(TRIM(p_telefono2), ''),
        observacion_empleado = NULLIF(v_obs, ''),
        id_genero            = p_id_genero
    WHERE id_empleado = p_id_empleado;

    SELECT CONCAT('Empleado "', v_nombres, ' ', v_apellidos, '" actualizado correctamente.') AS mensaje;

END $$
DELIMITER ;

-- Llamar al procedimiento y enviar registro de prueba.
CALL actualizar_empleado(
    11, 'David', 'Gonzales Update', '2000-01-01', NOW(), 
    'Nueva Direccion 123', 'david.gonzales@ejemplo.com', NULL, 
    '945612378', NULL, 'Se actualizó este empleado', 1, 1            
);



/* ============================================================
   6. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_empleado;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_empleado (
    IN p_id_empleado INT,
    IN p_nuevo_estado TINYINT
)
BEGIN
    -- 1. Verificar que el empleado exista
    IF NOT EXISTS (
        SELECT 1 FROM empleado WHERE id_empleado = p_id_empleado
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El empleado no existe.', 
        MYSQL_ERRNO = 20220;
    END IF;

    -- 2. Validar estado (0 o 1)
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 (inactivo) o 1 (activo).', 
        MYSQL_ERRNO = 20221;
    END IF;
SHOW PROCEDURE STATUS WHERE Name = 'insertar_empleado';
    -- 3. Actualizar estado
    UPDATE empleado
    SET estado = p_nuevo_estado
    WHERE id_empleado = p_id_empleado;

    -- 4. Mensaje
    SELECT CONCAT(
        'Empleado "', 
        (SELECT CONCAT(nombre_empleado, ' ', apellido_empleado) 
         FROM empleado 
         WHERE id_empleado = p_id_empleado), 
        '" actualizado a estado: ', p_nuevo_estado
    ) AS mensaje;

END$$
DELIMITER ;