
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
DROP PROCEDURE IF EXISTS ACtivar_Producto;
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
            SET estado = 1
            WHERE id_producto = P_id_producto;
        END IF;
    END IF;
END //

/************************************************
6. DAR DE BAJA Y ACTIVAR
************************************************/
--7. Procedure para dar de baja cuando el estado sea 0 
--Tambien lo usaremos para reactivar y sea 1
DROP PROCEDURE IF EXISTS CambiarEstadoProveedor;
DELIMITER $$

CREATE PROCEDURE CambiarEstadoProveedor (
  IN p_CodigoProveedor INT,
  IN p_NuevoEstado TINYINT
)
BEGIN
  -- Verificar que el proveedor exista
  IF NOT EXISTS (
    SELECT 1 FROM Proveedor WHERE id_proveedor = p_CodigoProveedor
  ) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El proveedor no existe.';
  END IF;

  -- Validar que el nuevo estado sea 0 o 1
  IF p_NuevoEstado NOT IN (0, 1) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El estado debe ser 0 o 1.';
  END IF;

  -- Actualizar estado
  UPDATE proveedor
  SET estado = p_NuevoEstado
  WHERE id_proveedor = p_CodigoProveedor;
END$$

DELIMITER ;

