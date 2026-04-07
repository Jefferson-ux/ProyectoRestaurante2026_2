/**************************************
1.- CARGO
**************************************/

-- 1. Primero definimos el delimitador
DELIMITER //

DROP PROCEDURE IF EXISTS Update_Cargo //

-- 2. Creamos el procedimiento
CREATE PROCEDURE Update_Cargo (
    IN p_id_cargo      INT,
    IN p_nombre_cargo  VARCHAR(255),
    IN p_estado        INT
)
BEGIN
    DECLARE v_existencia INT;
    DECLARE v_id_existe  INT;

    -- Validar que el ID exista
    SELECT COUNT(*) INTO v_id_existe FROM cargo WHERE id_cargo = p_id_cargo;

    IF v_id_existe = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El ID de cargo no existe.', 
            MYSQL_ERRNO = 20071;
    END IF;

    -- Validar nombre no nulo o vacío
    IF p_nombre_cargo IS NULL OR TRIM(p_nombre_cargo) = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El nombre del cargo es obligatorio.', 
            MYSQL_ERRNO = 20072;
    END IF;

    -- Validar estado
    IF p_estado IS NULL OR p_estado NOT IN (0,1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', 
            MYSQL_ERRNO = 20073;
    END IF;

    -- Validar duplicados
    SELECT COUNT(*) INTO v_existencia FROM cargo 
    WHERE UPPER(nombre_cargo) = UPPER(TRIM(p_nombre_cargo)) 
      AND id_cargo <> p_id_cargo;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Ya existe un cargo con ese nombre.', 
            MYSQL_ERRNO = 20074;
    END IF;

    -- Ejecutar el UPDATE
    UPDATE cargo
    SET nombre_cargo = TRIM(p_nombre_cargo),
        estado       = p_estado
    WHERE id_cargo = p_id_cargo;

END // 

-- 3. Volvemos al delimitador estándar
DELIMITER ;

--4. Ejemplo de uso
CALL Update_Cargo(5,"Ayudante de Cocina II",1);

/**************************************
2.- CATEGORIA 
**************************************/

DELIMITER //

DROP PROCEDURE IF EXISTS Update_Categoria //

CREATE PROCEDURE Update_Categoria (
    IN p_id_categoria     INT,
    IN p_nombre_categoria VARCHAR(255),
    IN p_estado            INT
)
BEGIN
    DECLARE v_existencia INT;
    DECLARE v_id_existe  INT;

    -- 1. Validar que el ID exista
    SELECT COUNT(*) INTO v_id_existe FROM categoria WHERE id_categoria = p_id_categoria;

    IF v_id_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: No se encontró la categoría con ese ID.',
            MYSQL_ERRNO = 20077;
    END IF;

    -- 2. Validar nombre no nulo o vacío
    IF p_nombre_categoria IS NULL OR TRIM(p_nombre_categoria) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El nombre de la categoría no puede estar vacío.',
            MYSQL_ERRNO = 20075;
    END IF;

    -- 3. Validar estado (debe ser 0 o 1)
    IF p_estado IS NULL OR p_estado NOT IN (0,1) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.',
            MYSQL_ERRNO = 20078; 
    END IF;

    -- 4. Verificar duplicados (mismo nombre en otro ID)
    SELECT COUNT(*) INTO v_existencia 
    FROM categoria 
    WHERE UPPER(TRIM(nombre_categoria)) = UPPER(TRIM(p_nombre_categoria)) 
      AND id_categoria <> p_id_categoria;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Ya existe una categoría con ese nombre.',
            MYSQL_ERRNO = 20076;
    END IF;

    -- 5. Ejecutar el UPDATE
    UPDATE categoria
    SET nombre_categoria = TRIM(p_nombre_categoria),
        estado           = p_estado
    WHERE id_categoria = p_id_categoria;

END //

DELIMITER ;

CALL Update_Categoria(10,"Jugos Naturales",1);

/**************************************
3.- CLIENTE
**************************************/
DELIMITER //

DROP PROCEDURE IF EXISTS Update_Cliente //

CREATE PROCEDURE Update_Cliente (
    IN p_id_cliente      INT,
    IN p_dni             VARCHAR(20),
    IN p_nombre          VARCHAR(100),
    IN p_apellido        VARCHAR(100),
    IN p_correo          VARCHAR(100),
    IN p_telefono        VARCHAR(20),
    IN p_observacion     VARCHAR(200),
    IN p_estado          INT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_dni_duplicado INT;
    DECLARE v_id_existe      INT;
    
    -- Variables para limpieza de datos
    DECLARE v_dni         VARCHAR(20)  DEFAULT TRIM(p_dni);
    DECLARE v_correo      VARCHAR(100) DEFAULT LOWER(TRIM(p_correo));
    DECLARE v_nombre      VARCHAR(100) DEFAULT TRIM(p_nombre);
    DECLARE v_apellido    VARCHAR(100) DEFAULT TRIM(p_apellido);
    DECLARE v_telefono    VARCHAR(20)  DEFAULT TRIM(p_telefono);
    DECLARE v_observacion VARCHAR(200) DEFAULT TRIM(p_observacion);

    -- Manejo de excepciones para ROLLBACK
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL; -- Re-lanza el error para que Java lo capture
    END;

    START TRANSACTION;

    -- 1. Validar que el ID exista
    SELECT COUNT(*) INTO v_id_existe FROM cliente WHERE id_cliente = p_id_cliente;
    IF v_id_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el cliente con ese ID.', MYSQL_ERRNO = 20085;
    END IF;

    -- 2. Validar DNI (8 dígitos)
    -- En MySQL se usa REGEXP
    IF v_dni IS NULL OR NOT v_dni REGEXP '^[0-9]{8}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El DNI debe tener exactamente 8 dígitos.', MYSQL_ERRNO = 20078;
    END IF;

    -- 3. Validar correo
    IF v_correo IS NULL OR NOT v_correo REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El correo electrónico es inválido.', MYSQL_ERRNO = 20079;
    END IF;

    -- 4. Validar nombre y apellido
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre no puede estar vacío.', MYSQL_ERRNO = 20080;
    END IF;

    IF v_apellido IS NULL OR v_apellido = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El apellido no puede estar vacío.', MYSQL_ERRNO = 20081;
    END IF;

    -- 5. Validar teléfono (9 dígitos, empieza con 9)
    IF v_telefono IS NULL OR NOT v_telefono REGEXP '^9[0-9]{8}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El teléfono debe tener 9 dígitos y empezar con 9.', MYSQL_ERRNO = 20082;
    END IF;

    -- 6. Validar estado
    IF p_estado IS NULL OR p_estado NOT IN (0,1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20083;
    END IF;

    -- 7. Validar DNI duplicado
    SELECT COUNT(*) INTO v_dni_duplicado FROM cliente 
    WHERE dni_cliente = v_dni AND id_cliente <> p_id_cliente;

    IF v_dni_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Ya existe otro cliente con ese DNI.', MYSQL_ERRNO = 20084;
    END IF;

    -- 8. UPDATE
    UPDATE cliente
    SET dni_cliente      = v_dni,
        nombre_cliente    = v_nombre,
        apellido_cliente  = v_apellido,
        correo_cliente    = v_correo,
        telefono_cliente  = v_telefono,
        observacion_cliente = v_observacion,
        estado            = p_estado
    WHERE id_cliente = p_id_cliente;

    COMMIT;

END //

DELIMITER ;
--9. Ejemplo de uso
CALL Update_Cliente(
    28, 
    '32955556', 
    'David', 
    'Gonzales Aguilar', 
    'davidaguilar@gmail.com', 
    '987654321', 
    'Cliente muy frecuente', 
    1
);

/**************************************
4.- CONTRATO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Contrato (
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

--8. Ejemplo de uso
CALL Update_Contrato(
    11,                      -- p_id_contrato
    'Contrato actualizado',  -- p_descripcion
    '2024-07-01',            -- p_fecha (En MySQL se pasa como String YYYY-MM-DD)
    2,                       -- p_id_turno
    5,                       -- p_id_empleado
    1,                       -- p_id_tipo_contrato
    1                        -- p_estado
);

/**************************************
5.- DETALLE PEDIDO
**************************************/

DELIMITER //

DROP PROCEDURE IF EXISTS Update_DetallePedido //

CREATE PROCEDURE Update_DetallePedido (
    IN p_id_detalle   INT,
    IN p_cantidad     DECIMAL(10,2), -- Usamos decimal por si hay cantidades no enteras
    IN p_precio       DECIMAL(10,2),
    IN p_observacion  VARCHAR(200)
)
BEGIN
    DECLARE v_existe INT;
    -- Equivalente a NVL: Si el TRIM resulta vacío o NULL, ponemos 'SIN OBSERVACIONES'
    DECLARE v_obs_limpia VARCHAR(200);

    -- Manejo de errores
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    -- Lógica de limpieza para la observación
    SET v_obs_limpia = TRIM(p_observacion);
    IF v_obs_limpia IS NULL OR v_obs_limpia = '' THEN
        SET v_obs_limpia = 'SIN OBSERVACIONES';
    END IF;

    START TRANSACTION;

    -- 1. Validar existencia del detalle
    SELECT COUNT(*) INTO v_existe FROM detalle_pedido WHERE id_detalle = p_id_detalle;
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El detalle de pedido no existe.', MYSQL_ERRNO = 20095;
    END IF;

    -- 2. Validar cantidad
    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La cantidad debe ser mayor a 0.', MYSQL_ERRNO = 20096;
    END IF;

    -- 3. Validar precio
    IF p_precio IS NULL OR p_precio <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio debe ser mayor a 0.', MYSQL_ERRNO = 20097;
    END IF;

    -- 4. Validar que el pedido asociado exista (Integridad)
    SELECT COUNT(*) INTO v_existe 
    FROM pedido p
    INNER JOIN detalle_pedido d ON p.id_pedido = d.id_pedido
    WHERE d.id_detalle = p_id_detalle;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El pedido asociado no existe.', MYSQL_ERRNO = 20098;
    END IF;

    -- 5. UPDATE
    UPDATE detalle_pedido
    SET cantidad_detalle        = p_cantidad,
        precio_unitario_detalle = p_precio,
        observacion_detalle     = v_obs_limpia
    WHERE id_detalle = p_id_detalle;

    COMMIT;

END //

DELIMITER ;
--6.Ejemplo de uso
CALL Update_DetallePedido(
    61,                     -- p_id_detalle
    2,                      -- p_cantidad
    25.00,                  -- p_precio
    'Sin cebolla ni ajo'    -- p_observacion
);


/* ============================================================
   6. EMPLEADO
   ============================================================ */
DROP PROCEDURE IF EXISTS Update_Empleado;
DELIMITER $$

CREATE PROCEDURE Update_Empleado (
    IN p_id_empleado            INT,
    IN p_nombre_empleado        VARCHAR(100),
    IN p_apellido_empleado      VARCHAR(100),
    IN p_fecha_nacimiento       DATE,
    IN p_fecha_registro         DATE,
    IN p_direccion_empleado     VARCHAR(150),
    IN p_correo_principal       VARCHAR(150),
    IN p_correo_secundario      VARCHAR(150),
    IN p_telefono_principal     VARCHAR(15),
    IN p_telefono_secundario    VARCHAR(15),
    IN p_id_genero              INT
)
BEGIN

    -- Validar que el empleado exista
    IF NOT EXISTS (SELECT 1 FROM empleado WHERE id_empleado = p_id_empleado) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El empleado no existe.';
    END IF;

    -- Validar que el nuevo correo principal no esté duplicado en otro empleado
    IF EXISTS (
        SELECT 1 FROM empleado 
        WHERE correo_principal = p_correo_principal AND id_empleado <> p_id_empleado
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Este correo principal ya está registrado para otro empleado.';
    END IF;

    -- Validar que el nuevo correo secundario no esté duplicado en otro empleado
    IF p_correo_secundario IS NOT NULL AND EXISTS (
        SELECT 1 FROM empleado 
        WHERE correo_secundario = p_correo_secundario AND id_empleado <> p_id_empleado
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El correo secundario ya está registrado para otro empleado.';
    END IF;

    -- Validar formato básico del correo principal (debe contener @ y un punto)
    IF p_correo_principal NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Formato de correo principal no válido.';
    END IF;

    -- Validar formato del correo secundario solo si se ha ingresado uno
    IF p_correo_secundario IS NOT NULL AND p_correo_secundario NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Formato de correo secundario no válido.';
    END IF;

    -- Validar que Teléfonos no sean iguales
    IF p_telefono_secundario IS NOT NULL AND p_telefono_principal = p_telefono_secundario THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Telefono Principal y Telefono Secundario no pueden ser iguales.';
    END IF;

    -- Actualización de los datos del empleado
    UPDATE empleado
    SET
        nombre_empleado = p_nombre_empleado,
        apellido_empleado = p_apellido_empleado,
        fecha_nacimiento = p_fecha_nacimiento,
        fecha_registro = p_fecha_registro,    
        direccion_empleado = p_direccion_empleado,
        telefono_principal = p_telefono_principal,
        telefono_secundario = p_telefono_secundario,
        correo_principal = p_correo_principal,
        correo_secundario = p_correo_secundario, 
        id_genero = p_id_genero
    WHERE id_empleado = p_id_empleado;

END$$

DELIMITER ;

CALL Update_Empleado(
    2,                          
    'Luis Alberto',           
    'Mendoza Ríos',             
    '1988-10-25',              
    '2026-04-06',               
    'Calle Las Magnolias 456', 
    'luis.mendoza@empresa.com', 
    NULL,                     
    '987654321',              
    '912345678',              
    1                        
);


/**************************************
7.- FACTURA
Tiene cambios en los campos, tiene mas con respecto a los de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Factura (
    IN p_id_factura        INT,
    IN p_num_comprobante   VARCHAR(20),
    IN p_fecha_pago        DATE,
    IN p_total             DECIMAL(10,2),
    IN p_id_pedido         INT,
    IN p_id_tipo_pago      INT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existe_factura INT;
    DECLARE v_existe_pedido  INT;
    DECLARE v_existe_pago    INT;

    -- 1. Validar existencia de la factura
    SELECT COUNT(*) INTO v_existe_factura FROM factura WHERE id_factura = p_id_factura;
    IF v_existe_factura = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La factura no existe.', MYSQL_ERRNO = 20110;
    END IF;

    -- 2. Validar número de comprobante
    IF p_num_comprobante IS NULL OR TRIM(p_num_comprobante) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El número de comprobante es obligatorio.', MYSQL_ERRNO = 20113;
    END IF;

    -- 3. Validar fecha de pago (No futura)
    IF p_fecha_pago IS NULL OR p_fecha_pago > CURDATE() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Fecha de pago inválida o futura.', MYSQL_ERRNO = 20114;
    END IF;

    -- 4. Validar total
    IF p_total IS NULL OR p_total <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El total debe ser mayor a 0.', MYSQL_ERRNO = 20111;
    END IF;

    -- 5. Validar existencia del pedido
    SELECT COUNT(*) INTO v_existe_pedido FROM pedido WHERE id_pedido = p_id_pedido;
    IF v_existe_pedido = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El pedido no existe.', MYSQL_ERRNO = 20115;
    END IF;

    -- 6. Validar existencia del tipo de pago
    SELECT COUNT(*) INTO v_existe_pago FROM tipo_pago WHERE id_tipo_pago = p_id_tipo_pago;
    IF v_existe_pago = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El tipo de pago no existe.', MYSQL_ERRNO = 20116;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE factura
    SET numero_comprobante = TRIM(p_num_comprobante),
        fecha_pago         = p_fecha_pago,
        total_factura      = p_total,
        id_pedido          = p_id_pedido,
        id_tipo_pago       = p_id_tipo_pago
    WHERE id_factura = p_id_factura;

    -- 8. Validar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en la factura.', MYSQL_ERRNO = 20112;
    END IF;

END //

DELIMITER ;

--8. Emplo de uso
CALL Update_Factura(
    1,              -- id_factura
    'F001-00045',   -- numero_comprobante
    CURDATE(),      -- fecha_pago
    185.20,         -- total_factura
    10,             -- id_pedido
    2               -- id_tipo_pago (Ej: Tarjeta)
);

/**************************************
8.- GENERO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Genero (
    IN p_id_genero     INT,
    IN p_nombre_genero VARCHAR(2)
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existe_id  INT;
    DECLARE v_existe_nom INT;
    DECLARE v_nombre      VARCHAR(2) DEFAULT UPPER(TRIM(p_nombre_genero));

    -- 1. Validar existencia del género por ID
    SELECT COUNT(*) INTO v_existe_id FROM genero WHERE id_genero = p_id_genero;
    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El género no existe.', MYSQL_ERRNO = 20113;
    END IF;

    -- 2. Validar nombre (No vacío y longitud)
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del género no puede estar vacío.', MYSQL_ERRNO = 20114;
    END IF;

    -- 3. Validar duplicado (Que otro ID no tenga ya ese nombre)
    SELECT COUNT(*) INTO v_existe_nom FROM genero 
    WHERE nombre_genero = v_nombre AND id_genero <> p_id_genero;
    
    IF v_existe_nom > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre de género ya está registrado.', MYSQL_ERRNO = 20115;
    END IF;

    -- 4. Ejecutar actualización
    UPDATE genero
    SET nombre_genero = v_nombre
    WHERE id_genero = p_id_genero;

    -- 5. Validar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se pudo actualizar el género.', MYSQL_ERRNO = 20116;
    END IF;

END //

DELIMITER ;
-- Ejemplo para actualizar a 'NB' (No Binario)
CALL Update_Genero(3, 'NB');

/**************************************
9.- MESA
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Mesa (
    IN p_id_mesa       INT,
    IN p_numero_mesa   VARCHAR(10),
    IN p_capacidad     INT,
    IN p_estado        TINYINT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existe_id  INT;
    DECLARE v_existe_num INT;
    DECLARE v_numero     VARCHAR(10) DEFAULT UPPER(TRIM(p_numero_mesa));

    -- 1. Validar existencia de la mesa por ID
    SELECT COUNT(*) INTO v_existe_id FROM mesa WHERE id_mesa = p_id_mesa;
    IF v_existe_id = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No existe la mesa con el ID proporcionado.', MYSQL_ERRNO = 20121;
    END IF;

    -- 2. Validar número de mesa (No vacío)
    IF v_numero IS NULL OR v_numero = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El número de mesa no puede estar vacío.', MYSQL_ERRNO = 20117;
    END IF;

    -- 3. Validar duplicado (Que otra mesa no tenga el mismo número)
    SELECT COUNT(*) INTO v_existe_num FROM mesa 
    WHERE numero_mesa = v_numero AND id_mesa <> p_id_mesa;
    
    IF v_existe_num > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Ya existe una mesa con ese número.', MYSQL_ERRNO = 20118;
    END IF;

    -- 4. Validar capacidad
    IF p_capacidad IS NULL OR p_capacidad <= 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: La capacidad debe ser mayor a 0.', MYSQL_ERRNO = 20119;
    END IF;

    -- 5. Validar estado
    IF p_estado IS NULL OR p_estado NOT IN (0,1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20120;
    END IF;

    -- 6. Ejecutar actualización
    UPDATE mesa
    SET numero_mesa = v_numero,
        capacidad   = p_capacidad,
        estado      = p_estado
    WHERE id_mesa = p_id_mesa;

    -- 7. Validar si hubo cambios efectivos
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se realizaron cambios (los datos ya eran idénticos).', MYSQL_ERRNO = 20122;
    END IF;

END //

DELIMITER ;
--8. EJEMPLO DE USO
CALL Update_Mesa(
    21,         -- p_id_mesa
    'M-05',     -- p_numero_mesa
    4,          -- p_capacidad
    1           -- p_estado (Activo)
);

/**************************************
10- PEDIDO
Mas campos a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Pedido (
    IN p_id_pedido       INT,
    IN p_fecha           DATETIME,
    IN p_id_cliente      INT,
    IN p_id_empleado     INT,
    IN p_id_tipo_pedido  INT,
    IN p_estado          TINYINT -- Campo integrado según tu tabla
)
BEGIN
    -- Declaración de variable para validaciones
    DECLARE v_existencia INT;

    -- 1. Validar ID pedido y existencia
    SELECT COUNT(*) INTO v_existencia FROM pedido WHERE id_pedido = p_id_pedido;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El pedido no existe.', MYSQL_ERRNO = 20123;
    END IF;

    -- 2. Validar fecha (No NULL y no futura)
    IF p_fecha IS NULL OR p_fecha > NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Fecha de pedido inválida o futura.', MYSQL_ERRNO = 20124;
    END IF;

    -- 3. Validar cliente
    SELECT COUNT(*) INTO v_existencia FROM cliente WHERE id_cliente = p_id_cliente;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El cliente no existe.', MYSQL_ERRNO = 20127;
    END IF;

    -- 4. Validar empleado
    SELECT COUNT(*) INTO v_existencia FROM empleado WHERE id_empleado = p_id_empleado;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El empleado no existe.', MYSQL_ERRNO = 20129;
    END IF;

    -- 5. Validar tipo de pedido
    SELECT COUNT(*) INTO v_existencia FROM tipo_pedido WHERE id_tipo_pedido = p_id_tipo_pedido;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El tipo de pedido no existe.', MYSQL_ERRNO = 20131;
    END IF;

    -- 6. Validar estado (0: Anulado/Inactivo, 1: Activo/Pendiente)
    IF p_estado IS NULL OR p_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20133;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE pedido
    SET fecha_pedido   = p_fecha,
        id_cliente     = p_id_cliente,
        id_empleado    = p_id_empleado,
        id_tipo_pedido = p_id_tipo_pedido,
        estado         = p_estado
    WHERE id_pedido = p_id_pedido;

    -- 8. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el pedido.', MYSQL_ERRNO = 20132;
    END IF;

END //

DELIMITER ;
-- 9.Ejemplo de uso
CALL Update_Pedido(
    31,          -- id_pedido
    NOW(),       -- fecha_pedido
    2,           -- id_cliente
    1,           -- id_empleado
    1,           -- id_tipo_pedido
    1            -- estado (Activo)
);

/**************************************
11- PLATO_MENU
Cambio de nombres a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Plato_Menu (
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
CALL Update_Plato_Menu(
    28,                             -- p_id_plato_menu
    'Ensalada Mixta',               -- p_nombre
    'Ensalada fresca sin limón',    -- p_descripcion
    12.99,                          -- p_precio
    2,                              -- p_id_categoria
    1                               -- p_estado (Activo)
);

/**************************************
12- PRODUCTO
Cambio de nombres a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Producto (
    IN p_id_producto       INT,
    IN p_nombre            VARCHAR(100),
    IN p_precio_unitario   DECIMAL(10,2),
    IN p_stock_minimo      INT,
    IN p_stock_actual      INT,
    IN p_id_unidad_medida  INT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre);

    -- 1. Validar ID y existencia del producto
    IF p_id_producto IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El ID del producto no puede ser NULL.', MYSQL_ERRNO = 20140;
    END IF;

    SELECT COUNT(*) INTO v_existencia FROM producto WHERE id_producto = p_id_producto;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el producto con el ID proporcionado.', MYSQL_ERRNO = 20141;
    END IF;

    -- 2. Validar nombre (No vacío)
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del producto no puede estar vacío.', MYSQL_ERRNO = 20142;
    END IF;

    -- 3. Validar duplicado (Nombre ya ocupado por otro ID)
    SELECT COUNT(*) INTO v_existencia FROM producto 
    WHERE UPPER(nombre_producto) = UPPER(v_nombre) AND id_producto <> p_id_producto;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Ya existe otro producto con ese nombre.', MYSQL_ERRNO = 20143;
    END IF;

    -- 4. Validar precio (No negativo)
    IF p_precio_unitario IS NULL OR p_precio_unitario < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio no puede ser negativo.', MYSQL_ERRNO = 20144;
    END IF;

    -- 5. Validar stock mínimo y actual
    IF p_stock_minimo IS NULL OR p_stock_minimo < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock mínimo no puede ser negativo.', MYSQL_ERRNO = 20145;
    END IF;

    IF p_stock_actual IS NULL OR p_stock_actual < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock actual no puede ser negativo.', MYSQL_ERRNO = 20146;
    END IF;

    -- 6. Validar relación de stock (Lógica de inventario)
    IF p_stock_minimo > p_stock_actual THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock mínimo no puede ser mayor al stock actual.', MYSQL_ERRNO = 20147;
    END IF;

    -- 7. Validar unidad de medida
    SELECT COUNT(*) INTO v_existencia FROM unidad_medida WHERE id_unidad_medida = p_id_id_unidad_medida;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida no existe.', MYSQL_ERRNO = 20149;
    END IF;

    -- 8. Ejecutar actualización
    UPDATE producto
    SET nombre_producto  = v_nombre,
        precio_producto  = p_precio_unitario,
        stock_minimo     = p_stock_minimo,
        stock_actual     = p_stock_actual,
        id_unidad_medida = p_id_unidad_medida
    WHERE id_producto = p_id_producto;

    -- 9. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el producto.', MYSQL_ERRNO = 20150;
    END IF;

END //

DELIMITER ;


/**************************************
13- PROVEEDOR
Mas campos  a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Proveedor (
    IN p_id_proveedor  INT,
    IN p_ruc           CHAR(11),
    IN p_razon_social  VARCHAR(150),
    IN p_telefono      VARCHAR(15),
    IN p_correo        VARCHAR(150),
    IN p_direccion     VARCHAR(150)
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_ruc        CHAR(11)     DEFAULT TRIM(p_ruc);
    DECLARE v_razon      VARCHAR(150) DEFAULT TRIM(p_razon_social);
    DECLARE v_correo     VARCHAR(150) DEFAULT LOWER(TRIM(p_correo));

    -- 1. Validar existencia del proveedor
    SELECT COUNT(*) INTO v_existencia FROM proveedor WHERE id_proveedor = p_id_proveedor;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El proveedor no existe.', MYSQL_ERRNO = 20161;
    END IF;

    -- 2. Validar RUC (11 dígitos y no nulo)
    IF v_ruc IS NULL OR v_ruc NOT REGEXP '^[0-9]{11}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El RUC debe tener 11 dígitos numéricos.', MYSQL_ERRNO = 20168;
    END IF;

    -- 3. Validar RUC duplicado
    SELECT COUNT(*) INTO v_existencia FROM proveedor 
    WHERE ruc = v_ruc AND id_proveedor <> p_id_proveedor;
    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El RUC ya pertenece a otro proveedor.', MYSQL_ERRNO = 20169;
    END IF;

    -- 4. Validar Razón Social
    IF v_razon IS NULL OR v_razon = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La razón social es obligatoria.', MYSQL_ERRNO = 20162;
    END IF;

    -- 5. Validar Teléfono (Mínimo 7, máximo 15 según tu CHECK)
    IF p_telefono IS NULL OR p_telefono NOT REGEXP '^[0-9]{7,15}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Teléfono inválido (7 a 15 dígitos).', MYSQL_ERRNO = 20163;
    END IF;

    -- 6. Validar Correo
    IF v_correo NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Formato de correo inválido.', MYSQL_ERRNO = 20164;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE proveedor
    SET ruc                 = v_ruc,
        razon_social        = v_razon,
        telefono_proveedor  = TRIM(p_telefono),
        correo_proveedor    = v_correo,
        direccion_proveedor = TRIM(p_direccion)
    WHERE id_proveedor = p_id_proveedor;

    -- 8. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el proveedor.', MYSQL_ERRNO = 20167;
    END IF;

END //

DELIMITER ;




/**************************************
14- PROVEEDOR_PRODUCTO
Cambio de nombres a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_ProveedorProducto (
    IN p_id_proveedor  INT,
    IN p_id_producto   INT,
    IN p_precio_compra DECIMAL(10,2),
    IN p_tiempo        INT,
    IN p_fecha         DATE
)
BEGIN
    -- Declaración de variable para validaciones
    DECLARE v_existencia INT;

    -- 1. Validar existencia del proveedor
    SELECT COUNT(*) INTO v_existencia FROM proveedor WHERE id_proveedor = p_id_proveedor;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El proveedor no existe.', MYSQL_ERRNO = 20168;
    END IF;

    -- 2. Validar existencia del producto
    SELECT COUNT(*) INTO v_existencia FROM producto WHERE id_producto = p_id_producto;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El producto no existe.', MYSQL_ERRNO = 20169;
    END IF;

    -- 3. Validar existencia de la relación (Llave primaria compuesta)
    SELECT COUNT(*) INTO v_existencia FROM proveedor_producto 
    WHERE id_proveedor = p_id_proveedor AND id_producto = p_id_producto;
    
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe la relación proveedor-producto.', MYSQL_ERRNO = 20173;
    END IF;

    -- 4. Validar precio de compra (Según CHECK >= 0)
    IF p_precio_compra IS NULL OR p_precio_compra < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio de compra no puede ser negativo.', MYSQL_ERRNO = 20170;
    END IF;

    -- 5. Validar tiempo de entrega (Según CHECK >= 0)
    IF p_tiempo IS NULL OR p_tiempo < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El tiempo de entrega no puede ser negativo.', MYSQL_ERRNO = 20171;
    END IF;

    -- 6. Validar fecha (No nula ni futura)
    IF p_fecha IS NULL OR p_fecha > CURDATE() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Fecha de registro inválida o futura.', MYSQL_ERRNO = 20172;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE proveedor_producto
    SET precio_compra  = p_precio_compra,
        tiempo_entrega = p_tiempo,
        fecha_registro = p_fecha
    WHERE id_proveedor = p_id_proveedor 
      AND id_producto  = p_id_producto;

    -- 8. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el registro.', MYSQL_ERRNO = 20174;
    END IF;

END //

DELIMITER ;




/**************************************
15- RESERVA
Cambio de nombres a comparación de Oracle
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Reserva (
    IN p_id_reserva        INT,
    IN p_fecha_registro    DATETIME,
    IN p_fecha_inicio      DATETIME,
    IN p_fecha_fin         DATETIME,
    IN p_cantidad_personas INT,
    IN p_observacion       VARCHAR(500),
    IN p_id_cliente        INT,
    IN p_id_mesa           INT,
    IN p_estado            TINYINT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existe    INT;
    DECLARE v_capacidad INT;
    DECLARE v_ocupada   INT;

    -- 1. Validar existencia de la reserva
    SELECT COUNT(*) INTO v_existe FROM reserva WHERE id_reserva = p_id_reserva;
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe la reserva.', MYSQL_ERRNO = 20182;
    END IF;

    -- 2. Validar estado (0 o 1)
    IF p_estado IS NULL OR p_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20174;
    END IF;

    -- 3. Validar cantidad de personas
    IF p_cantidad_personas IS NULL OR p_cantidad_personas <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La cantidad de personas debe ser mayor a 0.', MYSQL_ERRNO = 20175;
    END IF;

    -- 4. Validar fecha de registro (No futura)
    IF p_fecha_registro > NOW() THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La fecha de registro no puede ser futura.', MYSQL_ERRNO = 20176;
    END IF;

    -- 5. Validar coherencia de fechas (Inicio < Fin)
    IF p_fecha_inicio >= p_fecha_fin THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La fecha de inicio debe ser anterior a la de fin.', MYSQL_ERRNO = 20177;
    END IF;

    -- 6. Validar existencia de cliente
    SELECT COUNT(*) INTO v_existe FROM cliente WHERE id_cliente = p_id_cliente;
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El cliente no existe.', MYSQL_ERRNO = 20178;
    END IF;

    -- 7. Validar existencia de mesa y su capacidad
    SELECT COUNT(*), MAX(capacidad) INTO v_existe, v_capacidad 
    FROM mesa WHERE id_mesa = p_id_mesa;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La mesa no existe.', MYSQL_ERRNO = 20179;
    END IF;

    IF p_cantidad_personas > v_capacidad THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La cantidad excede la capacidad de la mesa.', MYSQL_ERRNO = 20180;
    END IF;

    -- 8. Validar cruce de horarios (Solo si la reserva se intenta activar/mantener activa)
    IF p_estado = 1 THEN
        SELECT COUNT(*) INTO v_ocupada
        FROM reserva
        WHERE id_mesa = p_id_mesa
          AND p_fecha_inicio < fecha_fin
          AND p_fecha_fin > fecha_inicio
          AND id_reserva <> p_id_reserva
          AND estado = 1;

        IF v_ocupada > 0 THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La mesa ya está ocupada en ese horario.', MYSQL_ERRNO = 20181;
        END IF;
    END IF;

    -- 9. Ejecutar actualización
    UPDATE reserva
    SET fecha_registro      = p_fecha_registro,
        fecha_inicio        = p_fecha_inicio,
        fecha_fin           = p_fecha_fin,
        cantidad_personas   = p_cantidad_personas,
        observacion_reserva = IFNULL(TRIM(p_observacion), 'SIN OBSERVACIÓN'),
        id_cliente          = p_id_cliente,
        id_mesa             = p_id_mesa,
        estado              = p_estado
    WHERE id_reserva = p_id_reserva;

    -- 10. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en la reserva.', MYSQL_ERRNO = 20183;
    END IF;

END //

DELIMITER ;





/**************************************
16- TIPO_CONTRATO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_TipoContrato (
    IN p_id_tipo_contrato      INT,
    IN p_nombre_tipo_contrato  VARCHAR(100)
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre_tipo_contrato);

    -- 1. Validar que el nombre no sea nulo o vacío
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del tipo de contrato no puede estar vacío.', MYSQL_ERRNO = 20183;
    END IF;

    -- 2. Validar que el ID exista en la tabla
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_contrato 
    WHERE id_tipo_contrato = p_id_tipo_contrato;

    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el tipo de contrato con el ID proporcionado.', MYSQL_ERRNO = 20184;
    END IF;

    -- 3. Validar duplicado (que otro ID no tenga el mismo nombre)
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_contrato 
    WHERE UPPER(nombre_tipo_contrato) = UPPER(v_nombre) 
      AND id_tipo_contrato <> p_id_tipo_contrato;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del tipo de contrato ya existe.', MYSQL_ERRNO = 20185;
    END IF;

    -- 4. Ejecutar actualización
    UPDATE tipo_contrato
    SET nombre_tipo_contrato = v_nombre
    WHERE id_tipo_contrato = p_id_tipo_contrato;

    -- 5. Verificar si hubo cambios efectivos
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios (los datos ya eran idénticos).', MYSQL_ERRNO = 20186;
    END IF;

END //

DELIMITER ;





/**************************************
17- TIPO_PAGO
CAMPOS ADICIONALES A COMPARACION DE ORACLE
**************************************/
DELIMITER //

CREATE PROCEDURE Update_TipoPago (
    IN p_id_tipo_pago      INT,
    IN p_nombre_tipo_pago  VARCHAR(100),
    IN p_estado            TINYINT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre_tipo_pago);

    -- 1. Validar que el nombre no sea nulo o vacío
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del tipo de pago no puede estar vacío.', MYSQL_ERRNO = 20186;
    END IF;

    -- 2. Validar que el ID exista
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_pago 
    WHERE id_tipo_pago = p_id_tipo_pago;

    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No existe el tipo de pago con el ID proporcionado.', MYSQL_ERRNO = 20187;
    END IF;

    -- 3. Validar duplicado (mismo nombre en otro ID)
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_pago 
    WHERE UPPER(nombre_tipo_pago) = UPPER(v_nombre) 
      AND id_tipo_pago <> p_id_tipo_pago;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre del tipo de pago ya existe.', MYSQL_ERRNO = 20188;
    END IF;

    -- 4. Validar estado (0 o 1)
    IF p_estado IS NULL OR p_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20189;
    END IF;

    -- 5. Ejecutar actualización
    UPDATE tipo_pago
    SET nombre_tipo_pago = v_nombre,
        estado           = p_estado
    WHERE id_tipo_pago = p_id_tipo_pago;

    -- 6. Verificar si hubo cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No se realizaron cambios en el registro.', MYSQL_ERRNO = 20190;
    END IF;

END //

DELIMITER ;





/**************************************
18- TIPO_PEDIDO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_TipoPedido (
    IN p_id_tipo_pedido      INT,
    IN p_nombre_tipo_pedido  VARCHAR(100)
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre_tipo_pedido);

    -- 1. Validar que el nombre no sea nulo o vacío
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El nombre del tipo de pedido no puede estar vacío.', MYSQL_ERRNO = 20189;
    END IF;

    -- 2. Validar existencia del ID antes de actualizar
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_pedido 
    WHERE id_tipo_pedido = p_id_tipo_pedido;

    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se encontró el tipo de pedido con el ID proporcionado.', MYSQL_ERRNO = 20191;
    END IF;

    -- 3. Validar duplicado (que otro registro no tenga el mismo nombre)
    SELECT COUNT(*) INTO v_existencia 
    FROM tipo_pedido 
    WHERE UPPER(nombre_tipo_pedido) = UPPER(v_nombre) 
      AND id_tipo_pedido <> p_id_tipo_pedido;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El nombre del tipo de pedido ya existe.', MYSQL_ERRNO = 20190;
    END IF;

    -- 4. Ejecutar actualización
    UPDATE tipo_pedido
    SET nombre_tipo_pedido = v_nombre
    WHERE id_tipo_pedido = p_id_tipo_pedido;

    -- 5. Verificar si hubo cambios efectivos
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se realizaron cambios (los datos ya eran idénticos).', MYSQL_ERRNO = 20192;
    END IF;

END //

DELIMITER ;




/**************************************
19. TURNO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Turno (
    IN p_id_turno       INT,
    IN p_nombre_turno   VARCHAR(100),
    IN p_horario_inicio TIME,
    IN p_horario_final  TIME
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre_turno);

    -- 1. Validar existencia del ID antes de cualquier operación
    SELECT COUNT(*) INTO v_existencia FROM turno WHERE id_turno = p_id_turno;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se encontró el turno con el ID proporcionado.', MYSQL_ERRNO = 20196;
    END IF;

    -- 2. Validar nombre vacío
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El nombre del turno no puede estar vacío.', MYSQL_ERRNO = 20192;
    END IF;

    -- 3. Validar duplicado (mismo nombre en otro ID)
    SELECT COUNT(*) INTO v_existencia 
    FROM turno 
    WHERE UPPER(nombre_turno) = UPPER(v_nombre) 
      AND id_turno <> p_id_turno;

    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El nombre del turno ya existe.', MYSQL_ERRNO = 20193;
    END IF;

    -- 4. Validar horas no nulas
    IF p_horario_inicio IS NULL OR p_horario_final IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: Los horarios no pueden ser NULL.', MYSQL_ERRNO = 20194;
    END IF;

    -- 5. Validar rango de horario (Inicio debe ser menor que Fin)
    IF p_horario_final <= p_horario_inicio THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El horario final debe ser mayor que el horario de inicio.', MYSQL_ERRNO = 20195;
    END IF;

    -- 6. Ejecutar actualización
    UPDATE turno
    SET nombre_turno   = v_nombre,
        horario_inicio = p_horario_inicio,
        horario_final  = p_horario_final
    WHERE id_turno = p_id_turno;

    -- 7. Verificar si hubo cambios efectivos
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se realizaron cambios (los datos ya eran idénticos).', MYSQL_ERRNO = 20197;
    END IF;

END //

DELIMITER ;




/* ============================================================
   20. UNIDAD_MEDIDA
   ============================================================ */
DELIMITER //

CREATE PROCEDURE Update_UnidadMedida (
    IN p_id_unidad_medida INT,
    IN p_nombre            VARCHAR(100),
    IN p_abreviatura       VARCHAR(20)
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existencia INT;
    DECLARE v_nombre     VARCHAR(100) DEFAULT TRIM(p_nombre);
    DECLARE v_abreviatura VARCHAR(20)  DEFAULT LOWER(TRIM(p_abreviatura));

    -- 1. Validar campos obligatorios
    IF v_nombre IS NULL OR v_nombre = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Nombre vacío.', MYSQL_ERRNO = 20197;
    END IF;

    IF v_abreviatura IS NULL OR v_abreviatura = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Abreviatura vacía.', MYSQL_ERRNO = 20198;
    END IF;

    -- 2. Validar existencia del ID
    SELECT COUNT(*) INTO v_existencia FROM unidad_medida WHERE id_unidad_medida = p_id_unidad_medida;
    IF v_existencia = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: ID de unidad no existe.', MYSQL_ERRNO = 20201;
    END IF;

    -- 3. Validar duplicados (Nombre y Abreviatura)
    SELECT COUNT(*) INTO v_existencia FROM unidad_medida 
    WHERE UPPER(nombre_unidad_medida) = UPPER(v_nombre) AND id_unidad_medida <> p_id_unidad_medida;
    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El nombre de unidad ya existe.', MYSQL_ERRNO = 20199;
    END IF;

    SELECT COUNT(*) INTO v_existencia FROM unidad_medida 
    WHERE UPPER(abreviatura) = UPPER(v_abreviatura) AND id_unidad_medida <> p_id_unidad_medida;
    IF v_existencia > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La abreviatura ya existe.', MYSQL_ERRNO = 20200;
    END IF;

    -- 4. UPDATE
    UPDATE unidad_medida
    SET nombre_unidad_medida = v_nombre,
        abreviatura          = v_abreviatura
    WHERE id_unidad_medida = p_id_unidad_medida;

    -- 5. Verificar cambios
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: No hubo cambios.', MYSQL_ERRNO = 20202;
    END IF;

END //

DELIMITER ;




/**************************************
21. USUARIO
**************************************/
DELIMITER //

CREATE PROCEDURE Update_Usuario (
    IN p_id_usuario   INT,
    IN p_codigo       VARCHAR(50),
    IN p_password     VARCHAR(200),
    IN p_observacion  VARCHAR(200),
    IN p_id_cargo     INT,
    IN p_estado       TINYINT
)
BEGIN
    -- Declaración de variables locales
    DECLARE v_existe      INT;
    DECLARE v_codigo      VARCHAR(50)  DEFAULT UPPER(TRIM(p_codigo));
    DECLARE v_password    VARCHAR(200) DEFAULT TRIM(p_password);
    DECLARE v_observacion VARCHAR(200) DEFAULT IFNULL(TRIM(p_observacion), 'SIN OBSERVACIÓN');

    -- 1. Validar existencia del usuario antes de proceder
    SELECT COUNT(*) INTO v_existe FROM usuario WHERE id_usuario = p_id_usuario;
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No existe el usuario con el ID proporcionado.', MYSQL_ERRNO = 20207;
    END IF;

    -- 2. Validar código de usuario (No nulo o vacío)
    IF v_codigo IS NULL OR v_codigo = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El código de usuario no puede estar vacío.', MYSQL_ERRNO = 20202;
    END IF;

    -- 3. Validar contraseña
    IF v_password IS NULL OR v_password = '' THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: La contraseña no puede estar vacía.', MYSQL_ERRNO = 20203;
    END IF;

    -- 4. Validar estado (0 o 1)
    IF p_estado IS NULL OR p_estado NOT IN (0, 1) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El estado debe ser 0 o 1.', MYSQL_ERRNO = 20204;
    END IF;

    -- 5. Validar existencia del cargo (FK)
    SELECT COUNT(*) INTO v_existe FROM cargo WHERE id_cargo = p_id_cargo;
    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El cargo proporcionado no existe.', MYSQL_ERRNO = 20205;
    END IF;

    -- 6. Validar código duplicado (que no lo use otro usuario)
    SELECT COUNT(*) INTO v_existe FROM usuario 
    WHERE UPPER(codigo_usuario) = v_codigo AND id_usuario <> p_id_usuario;

    IF v_existe > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: El código de usuario ya está en uso por otra cuenta.', MYSQL_ERRNO = 20206;
    END IF;

    -- 7. Ejecutar actualización
    UPDATE usuario
    SET codigo_usuario      = v_codigo,
        password_usuario    = v_password,
        observacion_usuario = v_observacion,
        id_cargo            = p_id_cargo,
        estado              = p_estado
    WHERE id_usuario = p_id_usuario;

    -- 8. Verificar si hubo cambios efectivos
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Error: No se realizaron cambios (los datos ya eran idénticos).', MYSQL_ERRNO = 20208;
    END IF;

END //

DELIMITER ;
