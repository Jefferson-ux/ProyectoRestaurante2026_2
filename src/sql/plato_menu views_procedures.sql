/*----------------------------   MANTENIMIENTO A LA TABLA PLATO_MENU   ----------------------------*/


/* ============================================================
   1. VISTA PLATO_MENU (PK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_plato_menu AS
SELECT
    pm.id_plato_menu            AS `ID`,
    pm.nombre_plato             AS `Nombre del Plato`,
    pm.descripcion_plato        AS `Descripciones`,
    CONCAT('S/ ', FORMAT(pm.precio_plato, 2)) AS `Precio`,
    c.nombre_categoria          AS `Categoría`
FROM plato_menu pm
INNER JOIN categoria c ON pm.id_categoria = c.id_categoria
WHERE pm.estado = 1;



/* ============================================================
   2. VISTA CATEGORIA (FK)
   ============================================================ */
CREATE OR REPLACE VIEW vista_categoria AS
SELECT
    id_categoria        AS `ID`,
    nombre_categoria    AS `Nombre de Categoría`
FROM categoria
WHERE estado = 1;



/* ============================================================
   3. PROCEDURE INSERTAR/NUEVO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_plato_menu;
DELIMITER $$

CREATE PROCEDURE insertar_plato_menu(
    IN p_nombre VARCHAR(255),
    IN p_descripcion VARCHAR(500),
    IN p_precio DECIMAL(10,2),
    IN p_id_categoria INT
)
BEGIN
    DECLARE v_count INT;
	DECLARE v_msg VARCHAR(500);

    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre del plato no puede estar vacío.',
        MYSQL_ERRNO = 1045;
    END IF;

    IF p_precio < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio del plato no puede ser negativo.',
        MYSQL_ERRNO = 1046;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM categoria
    WHERE id_categoria = p_id_categoria;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('La categoría ', p_id_categoria, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1047;

    END IF;

    SELECT COUNT(*) INTO v_count
    FROM plato_menu
    WHERE UPPER(TRIM(nombre_plato)) = UPPER(TRIM(p_nombre));

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe un plato en el menú con el mismo nombre.',
        MYSQL_ERRNO = 1048;
    END IF;

    INSERT INTO plato_menu(
        nombre_plato,
        descripcion_plato,
        precio_plato,
        id_categoria,
        estado
    )
    VALUES(
        p_nombre,
        p_descripcion,
        p_precio,
        p_id_categoria,
        DEFAULT
    );
    SELECT 'Plato insertado en el menú exitosamente.' AS mensaje;

END$$

DELIMITER ;



/* ============================================================
   4. PROCEDURE BUSCAR/SEARCH
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_plato_menu;
DELIMITER $$

CREATE PROCEDURE buscar_plato_menu (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_plato_menu
    WHERE `Nombre del Plato` LIKE CONCAT('%', p_param, '%')
       OR `Precio` LIKE CONCAT('%', p_param, '%')
       OR `Categoría` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;



/* ============================================================
   5. PROCEDURE UPDATE/MODIFICAR
   ============================================================ */
DELIMITER //

CREATE PROCEDURE actualizar_plato_menu (
    IN p_id_plato_menu INT,
    IN p_nombre         VARCHAR(100),
    IN p_descripcion    VARCHAR(500),
    IN p_precio         DECIMAL(10,2),
    IN p_id_categoria   INT,
    IN p_estado         TINYINT -- Campo integrado de tu script de tablas
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre);
    DECLARE v_desc       VARCHAR(500) DEFAULT TRIM(p_descripcion);

    -- 1. Validar ID de plato y existencia
    IF p_id_plato_menu IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El ID del plato no puede ser NULL.', MYSQL_ERRNO = 20133;
    END IF;

    SELECT COUNT(*) INTO v_existencia FROM plato_menu WHERE id_plato_menu = p_id_plato_menu;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el plato con el ID proporcionado.', MYSQL_ERRNO = 20139;
    END IF;

    -- 2. Validar nombre (No vacío)
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del plato no puede estar vacío.', MYSQL_ERRNO = 20134;
    END IF;

    -- 3. Validar precio
    IF p_precio IS NULL OR p_precio <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio debe ser mayor a 0.', MYSQL_ERRNO = 20135;
    END IF;

    -- 4. Validar existencia de categoría
    SELECT COUNT(*) INTO v_existencia FROM categoria WHERE id_categoria = p_id_categoria;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La categoría no existe.', MYSQL_ERRNO = 20137;
    END IF;

    -- 5. Validar nombre duplicado (Ignorando el plato actual)
    SELECT COUNT(*) INTO v_existencia FROM plato_menu 
    WHERE UPPER(nombre_plato) = UPPER(v_nombre) AND id_plato_menu <> p_id_plato_menu;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Ya existe otro plato con ese nombre.', MYSQL_ERRNO = 20138;
    END IF;

    -- 6. Validar estado (0 o 1)
    IF p_estado IS NULL OR p_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20140;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE plato_menu
    SET nombre_plato      = v_nombre,
        descripcion_plato = v_desc,
        precio_plato      = p_precio,
        id_categoria      = p_id_categoria,
        estado            = p_estado
    WHERE id_plato_menu = p_id_plato_menu;

    -- 8. Validar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el plato.', MYSQL_ERRNO = 20141;
    END IF;

END //

DELIMITER ;
-- 9.Emeplo de uso
CALL actualizar_plato_menu(
    28,                             -- p_id_plato_menu
    'Ensalada Mixta',               -- p_nombre
    'Ensalada fresca sin limón',    -- p_descripcion
    12.99,                          -- p_precio
    2,                              -- p_id_categoria
    1                               -- p_estado (Activo)
);



/* ============================================================
   6. PROCEDURE DESACTIVAR/ACTIVAR
   ============================================================ */
DROP PROCEDURE IF EXISTS desactivar_plato_menu;
DELIMITER //
CREATE PROCEDURE Desactivar_PlatoMenu(IN p_id_plato_menu INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM plato_menu WHERE id_plato_menu = p_id_plato_menu;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El plato no existe.';
    ELSE
        UPDATE plato_menu SET estado = 0 WHERE id_plato_menu = p_id_plato_menu;
    END IF;
END //
DELIMITER ;