
/***************************************
1. EMPLEADO
*****************************************/
DROP PROCEDURE IF EXISTS cambiar_estado_empleado;
DELIMITER $$

CREATE PROCEDURE cambiar_estado_empleado (
    IN p_id_empleado    INT,
    IN p_nuevo_estado   TINYINT
)
BEGIN
    -- Verificar que el empleado exista
    IF NOT EXISTS (
        SELECT 1 FROM empleado WHERE id_empleado = p_id_empleado
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El empleado no existe.';
    END IF;

    -- Validar que el nuevo estado sea 0 o 1
    IF p_nuevo_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El estado debe ser 0 o 1.';
    END IF;

    -- Actualizar estado del empleado
    UPDATE empleado
    SET estado = p_nuevo_estado
    WHERE id_empleado = p_id_empleado;
END$$

DELIMITER ;

-- Ejemplo:

-- Para DAR DE BAJA (Inactivo) al empleado con ID 1
CALL cambiar_estado_empleado(1, 0);

-- Para REACTIVAR (Activo) al empleado con ID 1
CALL cambiar_estado_empleado(1, 1);



























/***************************************
2. PRODUCTO
*****************************************/
DROP PROCEDURE IF EXISTS Desactivar_Producto;
DELIMITER //
CREATE PROCEDURE Desactivar_Producto(IN p_id_producto INT)
BEGIN
    DECLARE existe INT DEFAULT 0;
    DECLARE yaDesactivado INT DEFAULT 0;
    /* Verificar si el Empleado existe */
    SELECT COUNT(*) INTO existe
    FROM Producto WHERE id_producto = p_id_producto;
    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El código del producto no existe. ';
    ELSE
        /* Verificar si ya está desactivada */
        SELECT COUNT(*) INTO yaDesactivado
        FROM Producto
        WHERE id_producto = p_id_producto AND estado = 0;
        IF yaDesactivado > 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El Producto ya está desactivado.';
        ELSE
            /* Proceder con la desactivación */
            UPDATE Producto
            SET estado = 0
            WHERE id_producto = P_id_producto;
        END IF;
    END IF;
END //

/***************************************
3. PROVEEDOR
*****************************************/
DROP PROCEDURE IF EXISTS Desactivar_Proveedor;
DELIMITER //
CREATE PROCEDURE Desactivar_Proveedor(IN p_id_proveedor INT)
BEGIN
    DECLARE existe INT DEFAULT 0;
    DECLARE yaDesactivado INT DEFAULT 0;
    /* Verificar si el Empleado existe */
    SELECT COUNT(*) INTO existe
    FROM Proveedor WHERE id_proveedor = p_id_proveedor;
    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El código del proveedor no existe. ';
    ELSE
        /* Verificar si ya está desactivada */
        SELECT COUNT(*) INTO yaDesactivado
        FROM Proveedor
        WHERE id_proveedor = p_id_proveedor AND estado = 0;
        IF yaDesactivado > 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El Proveedor ya está desactivado.';
        ELSE
            /* Proceder con la desactivación */
            UPDATE Proveedor
            SET estado = 0
            WHERE id_producto = P_id_producto;
        END IF;
    END IF;
END //

