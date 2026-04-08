USE db_restaurant;
/* ====================================================
                 EMPLEADO
===================================================== */
DROP PROCEDURE IF EXISTS cambiar_estado_empleado;
DELIMITER //

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
END//
DELIMITER ;

-- Ejemplo:

-- Para DAR DE BAJA (Inactivo) al empleado con ID 1
CALL cambiar_estado_empleado(1, 0);

-- Para REACTIVAR (Activo) al empleado con ID 1
CALL cambiar_estado_empleado(1, 1);









/* ===================================================
                PRODUCTO
===================================================== */
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
            WHERE id_producto = p_id_producto;
        END IF;
    END IF;
END //
DELIMITER ;

/* ===================================================
                PROVEEDOR
===================================================== */
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
            WHERE id_producto = p_id_producto;
        END IF;
    END IF;
END //
DELIMITER ;


/* ===================================================
                CATEGORIA
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Categoria;
DELIMITER //
CREATE PROCEDURE Desactivar_Categoria(IN p_id_categoria INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM Categoria WHERE id_categoria = p_id_categoria;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La categoría no existe.';
    ELSE
        UPDATE Categoria SET estado = 0 WHERE id_categoria = p_id_categoria;
    END IF;
END //
DELIMITER ;





/* ===================================================
                TIPO_PEDIDO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_TipoPedido;
DELIMITER //
CREATE PROCEDURE Desactivar_TipoPedido(IN p_id_tipo_pedido INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM tipo_pedido WHERE id_tipo_pedido = p_id_tipo_pedido;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de pedido no existe.';
    ELSE
        UPDATE tipo_pedido SET estado = 0 WHERE id_tipo_pedido = p_id_tipo_pedido;
    END IF;
END //
DELIMITER ;




/* ===================================================
                TIPO_CONTRATO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_TipoContrato;
DELIMITER //
CREATE PROCEDURE Desactivar_TipoContrato(IN p_id_tipo_contrato INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM tipo_contrato WHERE id_tipo_contrato = p_id_tipo_contrato;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tipo de contrato no existe.';
    ELSE
        UPDATE tipo_contrato SET estado = 0 WHERE id_tipo_contrato = p_id_tipo_contrato;
    END IF;
END //
DELIMITER ;






/* ===================================================
                MESA
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Mesa;
DELIMITER //
CREATE PROCEDURE Desactivar_Mesa(IN p_id_mesa INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM mesa WHERE id_mesa = p_id_mesa;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La mesa no existe.';
    ELSE
        UPDATE mesa SET estado = 0 WHERE id_mesa = p_id_mesa;
    END IF;
END //
DELIMITER ;






/* ===================================================
                CLIENTE
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Cliente;
DELIMITER //
CREATE PROCEDURE Desactivar_Cliente(IN p_id_cliente INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM cliente WHERE id_cliente = p_id_cliente;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cliente no existe.';
    ELSE
        UPDATE cliente SET estado = 0 WHERE id_cliente = p_id_cliente;
    END IF;
END //
DELIMITER ;





/* ===================================================
                CARGO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Cargo;
DELIMITER //
CREATE PROCEDURE Desactivar_Cargo(IN p_id_cargo INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM cargo WHERE id_cargo = p_id_cargo;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El cargo no existe.';
    ELSE
        UPDATE cargo SET estado = 0 WHERE id_cargo = p_id_cargo;
    END IF;
END //
DELIMITER ;






/* ===================================================
                USUARIO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Usuario;
DELIMITER //
CREATE PROCEDURE Desactivar_Usuario(IN p_id_usuario INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM usuario WHERE id_usuario = p_id_usuario;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario no existe.';
    ELSE
        UPDATE usuario SET estado = 0 WHERE id_usuario = p_id_usuario;
    END IF;
END //
DELIMITER ;







/* ===================================================
                CONTRATO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Contrato;
DELIMITER //
CREATE PROCEDURE Desactivar_Contrato(IN p_id_contrato INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM contrato WHERE id_contrato = p_id_contrato;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El contrato no existe.';
    ELSE
        UPDATE contrato SET estado = 0 WHERE id_contrato = p_id_contrato;
    END IF;
END //
DELIMITER ;





/* ===================================================
                PLATO_MENU
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_PlatoMenu;
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





/* ===================================================
                PEDIDO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Pedido;
DELIMITER //
CREATE PROCEDURE Desactivar_Pedido(IN p_id_pedido INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM pedido WHERE id_pedido = p_id_pedido;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El pedido no existe.';
    ELSE
        UPDATE pedido SET estado = 0 WHERE id_pedido = p_id_pedido;
    END IF;
END //
DELIMITER ;









/* ===================================================
                DETALLE_PEDIDO
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_DetallePedido;
DELIMITER //
CREATE PROCEDURE Desactivar_DetallePedido(IN p_id_detalle INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM detalle_pedido WHERE id_detalle = p_id_detalle;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El detalle no existe.';
    ELSE
        UPDATE detalle_pedido SET estado = 0 WHERE id_detalle = p_id_detalle;
    END IF;
END //
DELIMITER ;











/* ===================================================
                FACTURA
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Factura;
DELIMITER //
CREATE PROCEDURE Desactivar_Factura(IN p_id_factura INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM factura WHERE id_factura = p_id_factura;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La factura no existe.';
    ELSE
        UPDATE factura SET estado = 0 WHERE id_factura = p_id_factura;
    END IF;
END //
DELIMITER ;








/* ===================================================
                RESERVA
===================================================== */
DROP PROCEDURE IF EXISTS Desactivar_Reserva;
DELIMITER //
CREATE PROCEDURE Desactivar_Reserva(IN p_id_reserva INT)
BEGIN
    DECLARE existe INT DEFAULT 0;

    SELECT COUNT(*) INTO existe FROM reserva WHERE id_reserva = p_id_reserva;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La reserva no existe.';
    ELSE
        UPDATE reserva SET estado = 0 WHERE id_reserva = p_id_reserva;
    END IF;
END //
DELIMITER ;



