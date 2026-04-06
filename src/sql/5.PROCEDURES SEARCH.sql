USE db_restaurant;
/* ============================================================
   1. CARGO
   Valida duplicado por nombre_cargo (sin distinción de case)
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_cargo;
DELIMITER //
CREATE PROCEDURE buscar_cargo(
    IN p_param VARCHAR(85)
)
BEGIN
    SELECT *
    FROM vista_cargo
    WHERE `Nombre de Cargo` LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;




/* ============================================================
   2. CATEGORIA
   (Ya existía — se incluye aquí para tener todo unificado)
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_categoria;
DELIMITER //
CREATE PROCEDURE buscar_categoria(
    IN p_param VARCHAR(85)
)
BEGIN
    SELECT *
    FROM vista_categoria
    WHERE `Nombre de Categoría` LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;






/* ============================================================
   3. CLIENTE
   Valida duplicado por DNI (único en la tabla)
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_cliente;
DELIMITER //
CREATE PROCEDURE buscar_cliente(
    IN p_param VARCHAR(85)
)
BEGIN
    SELECT *
    FROM vista_cliente
    WHERE `Nombre de Cliente` LIKE CONCAT('%', p_param, '%')
      OR `Apellido de Cliente` LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;







/* ============================================================
   4. CONTRATO
   Valida que el empleado y el turno existan antes de insertar.
   Un empleado puede tener múltiples contratos, por eso no se
   valida duplicado de id_empleado sino solo integridad.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_contrato;
DELIMITER //
CREATE PROCEDURE buscar_contrato(
    IN p_param VARCHAR(85)
)
BEGIN
    SELECT *
    FROM vista_contrato
    WHERE `Tipo de Contrato` LIKE CONCAT('%', p_param, '%')
      OR `Empleado` LIKE CONCAT('%', p_param, '%')
      OR `DNI del Empleado` LIKE CONCAT('%', p_param, '%')
      OR `Cargo` LIKE CONCAT('%', p_param, '%')
      OR `Turno` LIKE CONCAT('%', p_param, '%');
END //
DELIMITER ;







/* ============================================================
   5. USUARIO
   Valida duplicado por codigo (único en la tabla).
   Valida que el cargo exista y esté activo antes de insertar.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_usuario;
DELIMITER $$

CREATE PROCEDURE buscar_usuario (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_usuario
    WHERE `Código de Usuario` LIKE CONCAT('%', p_param, '%')
       OR `Cargo` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;






/* ============================================================
   6. PEDIDO
   Valida que la fecha no sea NULL ni futura.
   Valida existencia de cliente, empleado y tipo de pedido.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_pedido;
DELIMITER $$

CREATE PROCEDURE buscar_pedido (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_pedido
    WHERE `Cliente` LIKE CONCAT('%', p_param, '%')
       OR `DNI del Cliente` LIKE CONCAT('%', p_param, '%')
       OR `Fecha del Pedido` LIKE CONCAT('%', p_param, '%')
       OR `Empleado` LIKE CONCAT('%', p_param, '%')
       OR `Tipo de Pedido` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;




/* ============================================================
   7. MESA
   Valida que el número de mesa sea único y tenga formato M-X.
   Valida que la capacidad sea mayor a 0.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_mesa;
DELIMITER $$

CREATE PROCEDURE buscar_mesa (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_mesa
    WHERE `Número de Mesa` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;







/* ============================================================
   8. EMPLEADO
   Valida duplicado por DNI.
   Valida formato de correos y teléfonos.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_pedido;
DELIMITER $$

CREATE PROCEDURE buscar_pedido (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_pedido
    WHERE `Cliente` LIKE CONCAT('%', p_param, '%')
       OR `Empleado` LIKE CONCAT('%', p_param, '%')
       OR `Tipo de Pedido` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;







/* ============================================================
   9. DETALLE PEDIDO
   Valida que la cantidad sea mayor a 0.
   Valida que el precio unitario sea válido.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_detalle_pedido;
DELIMITER $$

CREATE PROCEDURE buscar_detalle_pedido (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_detalle_pedido
    WHERE `Nombre de Platillo` LIKE CONCAT('%', p_param, '%')
       OR `Fecha del pedido` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;








/* ============================================================
   10. FACTURA
   Valida que el total sea mayor a 0.
   Valida duplicado y formato del número de comprobante.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_factura;
DELIMITER $$

CREATE PROCEDURE buscar_factura (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_factura
    WHERE `Número de Comprobante` LIKE CONCAT('%', p_param, '%')
       OR `Fecha de Pago` LIKE CONCAT('%', p_param, '%')
       OR `Fecha del Pedido` LIKE CONCAT('%', p_param, '%')
       OR `Cliente` LIKE CONCAT('%', p_param, '%')
       OR `Plato` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;







/* ============================================================
   11. PRODUCTO
   Valida nombre único, campos no vacíos y valores numéricos válidos.
   Valida existencia de unidad de medida.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_producto;
DELIMITER $$

CREATE PROCEDURE buscar_producto (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_producto
    WHERE `Nombre del Producto` LIKE CONCAT('%', p_param, '%')
       OR `Unidad de Medida` LIKE CONCAT('%', p_param, '%')
       OR `Precio` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;





/* ============================================================
   12. PROVEEDOR
   Valida duplicado por RUC y correo.
   Valida formato y campos obligatorios.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_proveedor;
DELIMITER $$

CREATE PROCEDURE buscar_proveedor (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_proveedor
    WHERE `RUC` LIKE CONCAT('%', p_param, '%')
       OR `Razón Social (Nombre del Proveedor)` LIKE CONCAT('%', p_param, '%')
       OR `Teléfono de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Correo de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Dirección` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;





/* ============================================================
   13. PROVEEDOR_PRODUCTO
   Valida precio, tiempo de entrega y existencia de proveedor y producto.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_proveedor;
DELIMITER $$

CREATE PROCEDURE buscar_proveedor (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_proveedor
    WHERE `RUC` LIKE CONCAT('%', p_param, '%')
       OR `Razón Social (Nombre del Proveedor)` LIKE CONCAT('%', p_param, '%')
       OR `Teléfono de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Correo de contacto` LIKE CONCAT('%', p_param, '%')
       OR `Dirección` LIKE CONCAT('%', p_param, '%');
END$$
DELIMITER ;






/* ============================================================
   14. PLATO_MENU
   Valida nombre, precio y existencia de categoría.
   Valida duplicado por nombre.
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
   15. RESERVA
   Valida fechas, cantidad de personas, existencia de cliente y mesa.
   Valida conflicto de horario en la misma mesa.
   ============================================================ */
DROP PROCEDURE IF EXISTS buscar_reserva;
DELIMITER $$

CREATE PROCEDURE buscar_reserva (
    IN p_param VARCHAR(100)
)
BEGIN
    SELECT *
    FROM vista_reserva
    WHERE `Horario de Inicio` LIKE CONCAT('%', p_param, '%')
       OR `Horario de Fin` LIKE CONCAT('%', p_param, '%')
       OR `Cliente` LIKE CONCAT('%', p_param, '%')
       OR `Número de Mesa` LIKE CONCAT('%', p_param, '%');

END$$
DELIMITER ;
