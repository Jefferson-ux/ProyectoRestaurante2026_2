-- VIEW
USE db_restaurant;
/*================================================*/
/*===================   VISTAS  ==================*/
/*================================================*/

/* vista_cargo */
USE db_restaurant;

/* vista_cargo */
CREATE OR REPLACE VIEW vista_cargo AS
SELECT
    id_cargo AS `ID`,
    nombre_cargo AS `Nombre de Cargo`
FROM cargo
WHERE estado = 1;



/* vista_categoria */
CREATE OR REPLACE VIEW vista_categoria AS
SELECT
    id_categoria AS `ID`,
    nombre_categoria AS `Nombre de Categoría`
FROM categoria
WHERE estado = 1;



/* vista_cliente */
CREATE OR REPLACE VIEW vista_cliente AS
SELECT
    id_cliente, -- Lo añadimos para facilitar la búsqueda
    dni_cliente AS `DNI`,
    -- Creamos el campo para el ComboBox
    CONCAT(dni_cliente, ' - ', nombre_cliente, ' ', apellido_cliente) AS `Info_Cliente`,
    nombre_cliente AS `Nombre de Cliente`,
    apellido_cliente AS `Apellido de Cliente`,
    correo_cliente AS `Correo`,
    telefono_cliente AS `Teléfono Personal`,
    observacion_cliente AS `Observaciones`
FROM cliente
WHERE estado = 1;




/* ============================================================
   4. CONTRATO
   ============================================================ */
CREATE OR REPLACE VIEW vista_contrato AS
SELECT
    c.id_contrato   AS `ID`,
    tc.nombre_tipo_contrato      AS `Tipo de Contrato`,
    e.nombre_empleado            AS `Empleado`,
    e.dni_empleado               AS `DNI del Empleado`,
    ca.nombre_cargo              AS `Cargo`,
    c.descripcion_contrato 		 AS `Descripciones`,
    c.fecha_contrato             AS `Fecha de Contrato`,
    t.nombre_turno               AS `Turno`,
    -- Cambiamos TO_CHAR por TIME_FORMAT
    TIME_FORMAT(t.horario_inicio, '%H:%i') AS `Horario Inicio`,
    TIME_FORMAT(t.horario_final, '%H:%i')  AS `Horario Final`
FROM contrato c
INNER JOIN turno t          ON c.id_turno = t.id_turno
INNER JOIN empleado e       ON c.id_empleado = e.id_empleado
INNER JOIN tipo_contrato tc ON c.id_tipo_contrato = tc.id_tipo_contrato
-- Se une con contrato, ya no con empleado 
INNER JOIN cargo ca         ON c.id_cargo = ca.id_cargo
WHERE
    c.estado = 1;



/* vista_detalle_pedido */
CREATE OR REPLACE VIEW vista_detalle_pedido AS
SELECT
    d.id_detalle        AS `ID detalle`, 
    p.id_pedido         AS `ID pedido`,
    pm.id_plato_menu    AS `ID plato`,
    pm.nombre_plato     AS `Nombre de Platillo`,
    d.cantidad          AS `Cantidad Pedida`,
    DATE_FORMAT(p.fecha_pedido, '%d/%m/%Y') AS `Fecha del pedido`,
    d.precio_unitario   AS `Precio Unitario`,
    CONCAT('S/ ', FORMAT(d.precio_unitario, 2)) AS `Precio Formateado`,
    (d.precio_unitario * d.cantidad) AS `Subtotal`,
    CONCAT('S/ ', FORMAT(d.precio_unitario * d.cantidad, 2)) AS `Subtotal Formateado`,
    -- Datos del Cliente para tus txtFields
    c.dni_cliente       AS `DNI Cliente`,
    CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS `Nombre Cliente`,
    -- Datos del Empleado para tus txtFields
    e.dni_empleado      AS `DNI Empleado`,
    CONCAT(e.nombre_empleado, ' ', e.apellido_empleado) AS `Nombre Empleado`,
    IFNULL(d.observacion_detalle, 'Sin Observaciones')  AS `Observaciones`
FROM detalle_pedido d
INNER JOIN pedido p      ON d.id_pedido = p.id_pedido
INNER JOIN plato_menu pm ON d.id_plato_menu = pm.id_plato_menu
INNER JOIN cliente c     ON p.id_cliente = c.id_cliente
INNER JOIN empleado e    ON p.id_empleado = e.id_empleado;





/* vista_empleado */
CREATE OR REPLACE VIEW vista_empleado AS
SELECT
    e.dni_empleado              AS `DNI`,
    e.nombre_empleado           AS `Nombre de Empleado`,
    e.apellido_empleado         AS `Apellido de Empleado`,
    e.fecha_nacimiento AS `Fecha de Nacimiento`,
    e.fecha_registro   AS `Fecha de Registro`,
    e.direccion_empleado AS `Lugar de Residencia`,
    e.correo_principal          AS `Correo Principal`,
    e.correo_secundario          AS `Correo Secundario`,
    e.telefono_principal AS `Teléfono Principal`,
    e.telefono_secundario AS `Teléfono Secundario`,
    g.nombre_genero             AS `Género`,
    e.observacion_empleado 		 AS `Observaciones`
FROM empleado e
INNER JOIN genero g ON e.id_genero = g.id_genero
WHERE  e.estado = 1;





/* vista_factura */
CREATE OR REPLACE VIEW vista_factura AS
SELECT
    f.numero_comprobante AS `Número de Comprobante`,
    DATE_FORMAT(f.fecha_pago, '%d/%m/%Y') AS `Fecha de Pago`,
    DATE_FORMAT(p.fecha_pedido, '%d/%m/%Y') AS `Fecha del Pedido`,
    CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS `Cliente`,
    e.nombre_empleado AS `Empleado`,
    pm.nombre_plato AS `Plato`,
    d.cantidad AS `Cantidad`,
    d.precio_unitario AS `Precio Unitario`,
    (d.cantidad * d.precio_unitario) AS `Subtotal`,
    f.total_factura AS `Total Factura`,
    tp.nombre_tipo_pago AS `Tipo de Pago`
FROM factura f
INNER JOIN pedido p ON f.id_pedido = p.id_pedido
INNER JOIN cliente c ON p.id_cliente = c.id_cliente
INNER JOIN empleado e ON p.id_empleado = e.id_empleado
INNER JOIN detalle_pedido d ON p.id_pedido = d.id_pedido
INNER JOIN plato_menu pm ON d.id_plato_menu = pm.id_plato_menu
INNER JOIN tipo_pago tp ON f.id_tipo_pago = tp.id_tipo_pago;



/* vista_genero */
CREATE OR REPLACE VIEW vista_genero AS
SELECT
    nombre_genero  AS `Genero`
FROM genero;



/* vista_mesa */
CREATE OR REPLACE VIEW vista_mesa AS
SELECT
    id_mesa AS `ID`,
  numero_mesa   AS `Número de Mesa`,
  capacidad     AS `Capacidad`
FROM mesa
WHERE estado = 1;



/* vista_pedido */
CREATE OR REPLACE VIEW vista_pedido AS
SELECT
    p.id_pedido AS `ID`,
    DATE_FORMAT(p.fecha_pedido, '%d/%m/%Y') AS `Fecha del Pedido`,
    c.dni_cliente AS `DNI del Cliente`,
    c.nombre_cliente AS `Nombre del Cliente`,
    c.apellido_cliente AS `Apellidos del Cliente`,
    e.dni_empleado AS `DNI del Empleado`,
    e.nombre_empleado AS `Nombre del Empleado`,
    e.apellido_empleado AS `Apellidos del Empleado`,
    CONCAT(c.dni_cliente,' - ',c.nombre_cliente, ' ', c.apellido_cliente) AS `Cliente`,
    CONCAT(e.dni_empleado,' - ',e.nombre_empleado, ' ', e.apellido_empleado) AS `Empleado`,
    tp.nombre_tipo_pedido AS `Tipo de Pedido`,
    IFNULL(totales.cantidad_total,0) AS `Cantidad de Platos`
FROM pedido p
INNER JOIN cliente c ON p.id_cliente = c.id_cliente
INNER JOIN empleado e ON p.id_empleado = e.id_empleado
INNER JOIN tipo_pedido tp ON p.id_tipo_pedido = tp.id_tipo_pedido
LEFT JOIN (
    SELECT id_pedido, SUM(cantidad) AS cantidad_total
    FROM detalle_pedido
    GROUP BY id_pedido
) totales ON p.id_pedido = totales.id_pedido
WHERE p.estado = 1;





/* vista_plato_menu */
CREATE OR REPLACE VIEW vista_plato_menu AS
SELECT
	pm.id_plato_menu AS `ID`,
    pm.nombre_plato AS `Nombre del Plato`,
    pm.descripcion_plato 		 AS `Descripciones`,
    CONCAT('S/ ', FORMAT(pm.precio_plato, 2)) AS `Precio`,
    c.nombre_categoria AS `Categoría`
FROM plato_menu pm
INNER JOIN categoria c ON pm.id_categoria = c.id_categoria
WHERE pm.estado = 1;






/* vista_producto */
CREATE OR REPLACE VIEW vista_producto AS
SELECT
    p.id_producto	AS `ID`,
    p.nombre_producto AS `Nombre del Producto`,
    um.nombre_unidad_medida AS `Unidad de Medida`,
    CONCAT('S/ ', FORMAT(p.precio_producto, 2)) AS `Precio`,
    p.stock_minimo AS `Stock Mínimo`,
    p.stock_actual AS `Stock Actual`,
    p.observacion_producto AS `Observaciones`,
    p.estado AS `Estado`
FROM producto p
INNER JOIN unidad_medida um
    ON p.id_unidad_medida = um.id_unidad_medida;



/* vista_proveedor */
CREATE OR REPLACE VIEW vista_proveedor AS
SELECT
  id_proveedor AS `ID`,
  ruc AS `RUC`,
  razon_social AS `Razón Social (Nombre del Proveedor)`,
  telefono_proveedor AS `Teléfono de contacto`,
  correo_proveedor AS `Correo de contacto`,
  direccion_proveedor AS `Dirección`,
  observacion_proveedor AS `Observaciones`,
  estado AS  `Estado`
FROM proveedor;



CREATE OR REPLACE VIEW vista_proveedor_producto AS
SELECT
    pp.id_proveedor AS `ID Proveedor`, -- IMPORTANTE PARA JAVA
    pp.id_producto AS `ID Producto`,   -- IMPORTANTE PARA JAVA
    pr.razon_social AS `Proveedor`,
    p.nombre_producto AS `Producto`,
    pp.precio_compra AS `Precio de Compra`,
    pp.tiempo_entrega AS `Tiempo de Entrega (días)`,
    pp.fecha_registro AS `Fecha de Registro`,
    pp.estado AS `Estado`
FROM proveedor_producto pp
INNER JOIN producto p ON pp.id_producto = p.id_producto
INNER JOIN proveedor pr ON pp.id_proveedor = pr.id_proveedor;



CREATE OR REPLACE VIEW vista_reserva AS
SELECT
    r.id_reserva     AS `ID`,
    -- El día que irán a comer (extraído de fecha_inicio)
    DATE_FORMAT(r.fecha_inicio, '%d/%m/%Y') AS `Día de Reserva`,
    -- Las horas (como ya las tenías)
    TIME_FORMAT(r.fecha_inicio, '%H:%i') AS `Entrada`,
    TIME_FORMAT(r.fecha_fin, '%H:%i')    AS `Salida`,
    r.cantidad_personas AS `Pax`,
    m.numero_mesa       AS `Mesa`,
    c.dni_cliente AS `DNI`,
    c.nombre_cliente AS `Nombre`,
    c.apellido_cliente AS `Apellido`,
    CONCAT(c.dni_cliente,' - ',c.nombre_cliente, ' ', c.apellido_cliente) AS `Cliente Formateado`,
    -- Dejamos el registro al final como dato informativo
    DATE_FORMAT(r.fecha_registro, '%d/%m/%Y') AS `Fecha de Registro`,
    CASE    
        WHEN r.observacion_reserva IS NULL OR TRIM(r.observacion_reserva) = '' 
        THEN 'Sin Observaciones' 
        ELSE r.observacion_reserva 
    END AS `Observaciones`
FROM reserva r
INNER JOIN cliente c ON r.id_cliente = c.id_cliente
INNER JOIN mesa m ON r.id_mesa = m.id_mesa
WHERE r.estado = 1;
SELECT * FROM vista_reserva;


/* vista_tipo_contrato */
CREATE OR REPLACE VIEW vista_tipo_contrato AS
SELECT
  nombre_tipo_contrato  AS `Tipo de Contrato`
FROM tipo_contrato;



/* vista_tipo_pago */
CREATE OR REPLACE VIEW vista_tipo_pago AS
SELECT
  nombre_tipo_pago      AS `Tipo de Pago`
FROM tipo_pago;
-- !TODO Observar el estado --> Verificar en el procedure que el tipo_pago sea estado 1



/* vista_tipo_pedido */
CREATE OR REPLACE VIEW vista_tipo_pedido AS
SELECT
  nombre_tipo_pedido     AS `Tipo de Pedido`
FROM tipo_pedido;


/* vista_turno */
CREATE OR REPLACE VIEW vista_turno AS
SELECT
    id_turno AS `ID`,
    nombre_turno AS `Turno`,
    TIME_FORMAT(horario_inicio, '%H:%i') AS `Horario de Inicio`,
    TIME_FORMAT(horario_final, '%H:%i') AS `Horario de Fin`
FROM turno;



/* vista_usuario */
CREATE OR REPLACE VIEW vista_usuario AS
SELECT
    u.id_usuario          AS `ID`,
    u.codigo_usuario      AS `Código de Usuario`,
    u.password_usuario    AS `Contraseña`,
    u.estado              AS `Estado`,
    c.nombre_cargo        AS `Cargo`
FROM usuario u
INNER JOIN cargo c ON u.id_cargo = c.id_cargo
	WHERE u.estado = 1;;



/* vista_unidad_medida */
CREATE OR REPLACE VIEW vista_unidad_medida AS
SELECT
  nombre_unidad_medida AS `Unidad de Medida`,
  abreviatura AS `Abreviatura`
FROM unidad_medida;


SELECT * FROM vista_cliente;
