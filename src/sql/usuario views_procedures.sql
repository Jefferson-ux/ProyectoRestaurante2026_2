/*----------------------------   MANTENIMIENTO A LA TABLA USUARIO   ----------------------------*/

USE db_restaurant;
/* ============================================================
   1. VISTA USUARIO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_usuario AS
SELECT
    u.id_usuario          AS `ID`,
    u.codigo_usuario      AS `Código de Usuario`,
    u.password_usuario    AS `Contraseña`,
    c.nombre_cargo        AS `Nombre de Cargo`,
    u.estado              AS `Estado`
FROM usuario u
INNER JOIN cargo c ON u.id_cargo = c.id_cargo;



/* ============================================================
   2. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_usuario;
DELIMITER $$

CREATE PROCEDURE insertar_usuario (
    IN p_codigo         VARCHAR(20),
    IN p_password       VARCHAR(255),
    IN p_id_cargo       INT
)
BEGIN
    DECLARE v_codigo_existe INT;
    DECLARE v_cargo_existe INT;
    DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_codigo_existe
    FROM usuario
    WHERE LOWER(TRIM(codigo_usuario)) = LOWER(TRIM(p_codigo));

    SELECT COUNT(*) INTO v_cargo_existe
    FROM cargo
    WHERE id_cargo = p_id_cargo AND estado = 1;

    IF v_codigo_existe > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El nombre de usuario ya está en uso.',
        MYSQL_ERRNO = 1008;

    ELSEIF v_cargo_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El cargo seleccionado no existe o está inactivo.',
        MYSQL_ERRNO = 1009;

    ELSE
        INSERT INTO usuario (
            codigo_usuario,
            password_usuario,
            id_cargo,
            estado
        )
        VALUES (
            TRIM(p_codigo),
            p_password, 
            p_id_cargo,
            1 
        );

        COMMIT;
        SELECT CONCAT('Usuario "', p_codigo, '" registrado correctamente.') AS mensaje;
    END IF;
END$$

DELIMITER ;



/* ============================================================
   3. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_usuario;
DELIMITER //

CREATE PROCEDURE buscar_usuario (
    IN p_filtro VARCHAR(100)
)
BEGIN
    SET p_filtro = TRIM(p_filtro);

    SELECT 
        `ID`, 
        `Código de Usuario`, 
        `Contraseña`, 
        `Nombre de Cargo`, 
        `Estado`
    FROM vista_usuario
    WHERE `Código de Usuario` LIKE CONCAT('%', p_filtro, '%')
       OR `Nombre de Cargo` LIKE CONCAT('%', p_filtro, '%');

END //

DELIMITER ;



/* ============================================================
   4. PROCEDURE RESETEAR
   ============================================================ */
DROP PROCEDURE IF EXISTS PROC_ResetearPassword;
DELIMITER $$

CREATE PROCEDURE PROC_ResetearPassword (
    IN p_codigo VARCHAR(15)
)
BEGIN
    DECLARE v_existe INT DEFAULT 0;
    DECLARE v_password_defecto VARCHAR(255);

    -- Hash SHA-256 de "123456"
    SET v_password_defecto = '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92';

    -- Verificar si existe el usuario (con TRIM y UPPER para evitar errores)
    SELECT COUNT(*) INTO v_existe
    FROM usuario
    WHERE UPPER(codigo_usuario) = UPPER(TRIM(p_codigo));

    IF v_existe = 1 THEN
        UPDATE usuario
        SET password_usuario = v_password_defecto
        WHERE UPPER(codigo_usuario) = UPPER(TRIM(p_codigo));    
        SELECT 'Contraseña reseteada correctamente.' AS mensaje;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se encontró un usuario con ese código.';
    END IF;

END $$

DELIMITER ;



/* ============================================================
   5. PROCEDURE CAMBIAR ESTADO
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_usuario;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_usuario (
    IN p_id_usuario INT
)
BEGIN
    DECLARE v_existe INT DEFAULT 0;

    -- Validar si el usuario existe y está activo
    SELECT COUNT(*) INTO v_existe
    FROM usuario
    WHERE id_usuario = p_id_usuario AND estado = 1;

    IF v_existe = 1 THEN
        -- Actualizar estado a inactivo (0)
        UPDATE usuario
        SET estado = 0
        WHERE id_usuario = p_id_usuario;

        SELECT 'Usuario dado de baja correctamente.' AS mensaje;
    ELSE
        SELECT 'No se encontró un usuario activo con ese ID.' AS mensaje;
    END IF;

END $$

DELIMITER ;