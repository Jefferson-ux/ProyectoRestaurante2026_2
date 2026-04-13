/*----------------------------   MANTENIMIENTO A LA TABLA CONTRATO   ----------------------------*/


/* ============================================================
   1. VISTA TURNO (PK)
   ============================================================ */
    CREATE OR REPLACE VIEW vista_turno AS
SELECT
    id_turno AS `ID`,
    nombre_turno AS `Turno`,
    TIME_FORMAT(horario_inicio, '%H:%i') AS `Horario de Inicio`,
    TIME_FORMAT(horario_final, '%H:%i') AS `Horario de Fin`
FROM turno;



/* ============================================================
   1. VISTA TIPO_CONTRATO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_tipo_contrato AS
SELECT
    id_tipo_contrato    AS `ID`,
  nombre_tipo_contrato  AS `Tipo de Contrato`
FROM tipo_contrato;



/* ============================================================
   1. VISTA CARGO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_cargo AS
SELECT
    id_cargo AS `ID`,
    nombre_cargo AS `Nombre de Cargo`,
    estado      AS `Estado`
FROM cargo;



/* ============================================================
   1. VISTA CONTRATO (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_contrato AS
SELECT
    c.id_contrato               AS `ID`,
    tc.nombre_tipo_contrato     AS `Tipo de Contrato`,
    CONCAT(e.nombre_empleado, ' - ', e.dni_empleado) AS `Empleado`,
    ca.nombre_cargo             AS `Cargo`,
    c.descripcion_contrato      AS `Descripción`,
    DATE_FORMAT(c.fecha_contrato, '%d/%m/%Y %H:%i')  AS `Fecha de Contrato`,
    t.nombre_turno              AS `Turno`,
    TIME_FORMAT(t.horario_inicio, '%H:%i')           AS `Horario Inicio`,
    TIME_FORMAT(t.horario_final, '%H:%i')            AS `Horario Final`,
    c.estado                    AS `Estado`
FROM contrato c
INNER JOIN turno t              ON c.id_turno = t.id_turno
INNER JOIN empleado e           ON c.id_empleado = e.id_empleado
INNER JOIN tipo_contrato tc     ON c.id_tipo_contrato = tc.id_tipo_contrato
INNER JOIN cargo ca             ON c.id_cargo = ca.id_cargo;



/* ============================================================
   2. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_contrato;
DELIMITER //
CREATE PROCEDURE buscar_contrato(
    IN p_param VARCHAR(85)
)
BEGIN
    SELECT *
    FROM vista_contrato
    WHERE `Tipo de Contrato`    LIKE CONCAT('%', p_param, '%')
      OR `Empleado`             LIKE CONCAT('%', p_param, '%')
      OR `DNI del Empleado`     LIKE CONCAT('%', p_param, '%')
      OR `Cargo`                LIKE CONCAT('%', p_param, '%')
      OR `Turno`                LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;



/* ============================================================
   3. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_contrato;
DELIMITER $$

CREATE PROCEDURE insertar_contrato (
    IN p_descripcion VARCHAR(255),
    IN p_fecha DATETIME,
    IN p_id_turno INT,
    IN p_id_empleado INT,
    IN p_id_tipo_contrato INT,
    IN p_id_cargo INT
)
BEGIN
    DECLARE v_emp_existe INT;
    DECLARE v_turno_existe INT;
    DECLARE v_tipo_existe INT;
    DECLARE v_cargo_existe INT;
    DECLARE v_msg VARCHAR(500);

    -- Validar descripción
    IF p_descripcion IS NULL OR TRIM(p_descripcion) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: la descripción no puede estar vacía.',
        MYSQL_ERRNO = 1001;
    END IF;

    -- Validaciones
    SELECT COUNT(*) INTO v_emp_existe
    FROM empleado
    WHERE id_empleado = p_id_empleado AND estado = 1;

    SELECT COUNT(*) INTO v_turno_existe
    FROM turno
    WHERE id_turno = p_id_turno;

    SELECT COUNT(*) INTO v_tipo_existe
    FROM tipo_contrato
    WHERE id_tipo_contrato = p_id_tipo_contrato;

    SELECT COUNT(*) INTO v_cargo_existe
    FROM cargo
    WHERE id_cargo = p_id_cargo;

    -- Errores
    IF v_emp_existe = 0 THEN
        SET v_msg = CONCAT('Error: el empleado con ID ', p_id_empleado, ' no existe o está inactivo.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_msg,
        MYSQL_ERRNO = 1004;

    ELSEIF v_turno_existe = 0 THEN
        SET v_msg = CONCAT('Error: el turno con ID ', p_id_turno, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_msg,
        MYSQL_ERRNO = 1005;

    ELSEIF v_tipo_existe = 0 THEN
        SET v_msg = CONCAT('Error: el tipo de contrato con ID ', p_id_tipo_contrato, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_msg,
        MYSQL_ERRNO = 1006;

    ELSEIF v_cargo_existe = 0 THEN
        SET v_msg = CONCAT('Error: el cargo con ID ', p_id_cargo, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = v_msg,
        MYSQL_ERRNO = 1007;

    ELSE
        INSERT INTO contrato (
            descripcion_contrato,
            fecha_contrato,
            id_turno,
            id_empleado,
            id_tipo_contrato,
            id_cargo
        )
        VALUES (
            TRIM(p_descripcion),
            IFNULL(p_fecha, NOW()),
            p_id_turno,
            p_id_empleado,
            p_id_tipo_contrato,
            p_id_cargo
        );

        SELECT 'Contrato registrado correctamente' AS mensaje;
    END IF;

END$$
DELIMITER ;



/* ============================================================
   4. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DELIMITER //

CREATE PROCEDURE actualizar_contrato (
    IN p_id_contrato      INT,
    IN p_descripcion      VARCHAR(200),
    IN p_fecha            DATE,
    IN p_id_turno         INT,
    IN p_id_empleado      INT,
    IN p_id_tipo_contrato INT,
    IN p_id_cargo         INT,
    IN p_estado           TINYINT
)
BEGIN
    DECLARE v_existe_id INT;
    DECLARE v_existe_cargo INT;

    -- Validar existencia del contrato
    SELECT COUNT(*) INTO v_existe_id
    FROM contrato
    WHERE id_contrato = p_id_contrato;

    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20086, 
            MESSAGE_TEXT = 'Error: El contrato no existe.';
    END IF;

    -- Validar descripción
    IF p_descripcion IS NULL OR TRIM(p_descripcion) = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20087, 
            MESSAGE_TEXT = 'Error: La descripción no puede estar vacía.';
    END IF;

    -- Validar fecha
    IF p_fecha IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20088, 
            MESSAGE_TEXT = 'Error: La fecha no puede ser NULL.';
    END IF;

    IF p_fecha > CURRENT_DATE THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20089, 
            MESSAGE_TEXT = 'Error: La fecha no puede ser futura.';
    END IF;

    -- Validar turno
    SELECT COUNT(*) INTO v_existe_id
    FROM turno
    WHERE id_turno = p_id_turno;

    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20090, 
            MESSAGE_TEXT = 'Error: El turno no existe.';
    END IF;

    -- Validar empleado
    SELECT COUNT(*) INTO v_existe_id
    FROM empleado
    WHERE id_empleado = p_id_empleado;

    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20091, 
            MESSAGE_TEXT = 'Error: El empleado no existe.';
    END IF;

    -- Validar tipo contrato
    SELECT COUNT(*) INTO v_existe_id
    FROM tipo_contrato
    WHERE id_tipo_contrato = p_id_tipo_contrato;

    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20092, 
            MESSAGE_TEXT = 'Error: El tipo de contrato no existe.';
    END IF;

    -- Validar cargo
    SELECT COUNT(*) INTO v_existe_cargo
    FROM cargo
    WHERE id_cargo = p_id_cargo;

    IF v_existe_cargo = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20107, 
            MESSAGE_TEXT = 'Error: Cargo no existe.';
    END IF;

    -- Validar estado
    IF p_estado IS NULL OR p_estado NOT IN (0,1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20093, 
            MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.';
    END IF;

    -- UPDATE
    UPDATE contrato
    SET descripcion_contrato = TRIM(p_descripcion),
        fecha_contrato       = p_fecha,
        id_turno             = p_id_turno,
        id_empleado          = p_id_empleado,
        id_tipo_contrato     = p_id_tipo_contrato,
        id_cargo             = p_id_cargo,
        estado               = p_estado
    WHERE id_contrato = p_id_contrato;

    -- Validar afectación de filas
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MYSQL_ERRNO = 20094, 
            MESSAGE_TEXT = 'Error: No se pudo actualizar el contrato.';
    END IF;

END //

DELIMITER ;



/* ============================================================
   5. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS cambiar_estado_contrato;
DELIMITER //

CREATE PROCEDURE cambiar_estado_contrato(IN p_id_contrato INT)
BEGIN
    DECLARE v_existe INT DEFAULT 0;
    DECLARE v_estado INT;

    -- Verificar existencia y obtener estado
    SELECT COUNT(*), MAX(estado)
    INTO v_existe, v_estado
    FROM contrato
    WHERE id_contrato = p_id_contrato;

    -- No existe
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El contrato no existe.',
        MYSQL_ERRNO = 30010;

    -- Ya está desactivado
    ELSEIF v_estado = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El contrato ya está desactivado.',
        MYSQL_ERRNO = 30011;

    -- Desactivar
    ELSE
        UPDATE contrato 
        SET estado = 0 
        WHERE id_contrato = p_id_contrato;

        SELECT 'Contrato desactivado correctamente.' AS mensaje;
    END IF;

END //

DELIMITER ;