/***************************************
                 CATEGORIA
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Categoria;
DELIMITER //
CREATE PROCEDURE Activar_Categoria(IN p_id_categoria INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM categoria WHERE id_categoria = p_id_categoria;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La categoría no existe.';
    ELSE
        UPDATE categoria SET estado = 1 WHERE id_categoria = p_id_categoria;
    END IF;
END //
DELIMITER ;




/***************************************
                 TIPO_PEDIDO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_TipoPedido;
DELIMITER //
CREATE PROCEDURE Activar_TipoPedido(IN p_id_tipo_pedido INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM tipo_pedido WHERE id_tipo_pedido = p_id_tipo_pedido;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El tipo de pedido no existe.';
    ELSE
        UPDATE tipo_pedido SET estado = 1 WHERE id_tipo_pedido = p_id_tipo_pedido;
    END IF;
END //
DELIMITER ;




/***************************************
                 TIPO_CONTRATO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_TipoContrato;
DELIMITER //
CREATE PROCEDURE Activar_TipoContrato(IN p_id_tipo_contrato INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM tipo_contrato WHERE id_tipo_contrato = p_id_tipo_contrato;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El tipo de contrato no existe.';
    ELSE
        UPDATE tipo_contrato SET estado = 1 WHERE id_tipo_contrato = p_id_tipo_contrato;
    END IF;
END //
DELIMITER ;



/***************************************
                 MESA
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Mesa;
DELIMITER //
CREATE PROCEDURE Activar_Mesa(IN p_id_mesa INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM mesa WHERE id_mesa = p_id_mesa;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La mesa no existe.';
    ELSE
        UPDATE mesa SET estado = 1 WHERE id_mesa = p_id_mesa;
    END IF;
END //
DELIMITER ;



/***************************************
                 CLIENTE
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Cliente;
DELIMITER //
CREATE PROCEDURE Activar_Cliente(IN p_id_cliente INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM cliente WHERE id_cliente = p_id_cliente;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El cliente no existe.';
    ELSE
        UPDATE cliente SET estado = 1 WHERE id_cliente = p_id_cliente;
    END IF;
END //
DELIMITER ;



/***************************************
                 CARGO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Cargo;
DELIMITER //
CREATE PROCEDURE Activar_Cargo(IN p_id_cargo INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM cargo WHERE id_cargo = p_id_cargo;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El cargo no existe.';
    ELSE
        UPDATE cargo SET estado = 1 WHERE id_cargo = p_id_cargo;
    END IF;
END //
DELIMITER ;



/***************************************
                 USUARIO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Usuario;
DELIMITER //
CREATE PROCEDURE Activar_Usuario(IN p_id_usuario INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM usuario WHERE id_usuario = p_id_usuario;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El usuario no existe.';
    ELSE
        UPDATE usuario SET estado = 1 WHERE id_usuario = p_id_usuario;
    END IF;
END //
DELIMITER ;



/***************************************
                 EMPLEADO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Empleado;
DELIMITER //
CREATE PROCEDURE Activar_Empleado(IN p_id_empleado INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM empleado WHERE id_empleado = p_id_empleado;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El empleado no existe.';
    ELSE
        UPDATE empleado SET estado = 1 WHERE id_empleado = p_id_empleado;
    END IF;
END //
DELIMITER ;



/***************************************
                 CONTRATO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Contrato;
DELIMITER //
CREATE PROCEDURE Activar_Contrato(IN p_id_contrato INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM contrato WHERE id_contrato = p_id_contrato;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El contrato no existe.';
    ELSE
        UPDATE contrato SET estado = 1 WHERE id_contrato = p_id_contrato;
    END IF;
END //
DELIMITER ;



/***************************************
                 PLATO_MENU
*****************************************/
DROP PROCEDURE IF EXISTS Activar_PlatoMenu;
DELIMITER //
CREATE PROCEDURE Activar_PlatoMenu(IN p_id_plato_menu INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM plato_menu WHERE id_plato_menu = p_id_plato_menu;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El plato no existe.';
    ELSE
        UPDATE plato_menu SET estado = 1 WHERE id_plato_menu = p_id_plato_menu;
    END IF;
END //
DELIMITER ;




/***************************************
                 PEDIDO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Pedido;
DELIMITER //
CREATE PROCEDURE Activar_Pedido(IN p_id_pedido INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM pedido WHERE id_pedido = p_id_pedido;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El pedido no existe.';
    ELSE
        UPDATE pedido SET estado = 1 WHERE id_pedido = p_id_pedido;
    END IF;
END //
DELIMITER ;



/***************************************
                 DETALLE_PEDIDO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_DetallePedido;
DELIMITER //
CREATE PROCEDURE Activar_DetallePedido(IN p_id_detalle INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM detalle_pedido WHERE id_detalle = p_id_detalle;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El detalle no existe.';
    ELSE
        UPDATE detalle_pedido SET estado = 1 WHERE id_detalle = p_id_detalle;
    END IF;
END //
DELIMITER ;



/***************************************
                 FACTURA
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Factura;
DELIMITER //
CREATE PROCEDURE Activar_Factura(IN p_id_factura INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM factura WHERE id_factura = p_id_factura;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La factura no existe.';
    ELSE
        UPDATE factura SET estado = 1 WHERE id_factura = p_id_factura;
    END IF;
END //
DELIMITER ;



/***************************************
                 RESERVA
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Reserva;
DELIMITER //
CREATE PROCEDURE Activar_Reserva(IN p_id_reserva INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM reserva WHERE id_reserva = p_id_reserva;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La reserva no existe.';
    ELSE
        UPDATE reserva SET estado = 1 WHERE id_reserva = p_id_reserva;
    END IF;
END //
DELIMITER ;



/***************************************
                 PRODUCTO
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Producto;
DELIMITER //
CREATE PROCEDURE Activar_Producto(IN p_id_producto INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM producto WHERE id_producto = p_id_producto;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El producto no existe.';
    ELSE
        UPDATE producto SET estado = 1 WHERE id_producto = p_id_producto;
    END IF;
END //
DELIMITER ;



/***************************************
                 PROVEEDOR
*****************************************/
DROP PROCEDURE IF EXISTS Activar_Proveedor;
DELIMITER //
CREATE PROCEDURE Activar_Proveedor(IN p_id_proveedor INT)
BEGIN
    DECLARE existe INT;

    SELECT COUNT(*) INTO existe FROM proveedor WHERE id_proveedor = p_id_proveedor;

    IF existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El proveedor no existe.';
    ELSE
        UPDATE proveedor SET estado = 1 WHERE id_proveedor = p_id_proveedor;
    END IF;
END //
DELIMITER ;


