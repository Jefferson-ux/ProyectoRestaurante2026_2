
/***************************************
1. EMPLEADO
*****************************************/
DROP PROCEDURE IF EXISTS Desactivar_Empleado;
DELIMITER //
CREATE PROCEDURE Desactivar_Empleado(IN dni_empleado INT)
BEGIN
    DECLARE existe INT DEFAULT 0;
    DECLARE yaDesactivado INT DEFAULT 0;
    /* Verificar si el Empleado existe */
    SELECT COUNT(*) INTO existe
    FROM Empleado WHERE dni_empleado = dni_empleado;
    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El código de Empleado no existe. ';
    ELSE
        /* Verificar si ya está desactivada */
        SELECT COUNT(*) INTO yaDesactivado
        FROM Empleado
        WHERE dni_empleado = dni_empleado AND estado = 0;
        IF yaDesactivado > 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El Empleado ya está desactivado.';
        ELSE
            /* Proceder con la desactivación */
            UPDATE Empleado
            SET estado = 0
            WHERE dni_empleado = dni_empleado;
        END IF;
    END IF;
END //

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

