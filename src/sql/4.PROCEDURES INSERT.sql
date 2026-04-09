USE db_restaurant;
/* ============================================================
   1. CARGO
   Valida duplicado por nombre_cargo (sin distinción de case)
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_cargo;
DELIMITER $$
CREATE PROCEDURE insertar_cargo (
    IN p_nombre_cargo VARCHAR(255)
)
BEGIN
    DECLARE v_existencia INT;
    DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_existencia
    FROM cargo
    WHERE UPPER(TRIM(nombre_cargo)) = UPPER(TRIM(p_nombre_cargo));

    IF v_existencia > 0 THEN
    	SET v_msg = CONCAT('El cargo "', p_nombre_cargo, '" ya existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1001;

    ELSE
        INSERT INTO cargo (nombre_cargo, estado)
        VALUES (p_nombre_cargo, DEFAULT);

        SELECT CONCAT('Cargo "', p_nombre_cargo, '" registrado correctamente.') AS mensaje;
    END IF;
END$$

DELIMITER ;



/* ============================================================
   2. CATEGORIA
   (Ya existía — se incluye aquí para tener todo unificado)
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_categoria;
DELIMITER $$
CREATE PROCEDURE insertar_categoria (
    IN p_nombre_categoria VARCHAR(255)
)
BEGIN
    DECLARE v_existencia INT;
	DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_existencia
    FROM categoria
    WHERE UPPER(TRIM(nombre_categoria)) = UPPER(TRIM(p_nombre_categoria));

    IF v_existencia > 0 THEN
    	SET v_msg = CONCAT('La categoría "', p_nombre_categoria, '" ya existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1002;

    ELSE
        INSERT INTO categoria (nombre_categoria)
        VALUES (p_nombre_categoria);
        SELECT CONCAT('Categoría "', p_nombre_categoria, '" registrada correctamente.') AS mensaje;
    END IF;
END$$

DELIMITER ;


/* ============================================================
   3. CLIENTE
   Valida duplicado por DNI (único en la tabla)
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_cliente;
DELIMITER $$

CREATE PROCEDURE insertar_cliente (
    IN p_dni CHAR(8),
    IN p_nombre VARCHAR(255),
    IN p_apellido VARCHAR(255),
    IN p_correo VARCHAR(255),
    IN p_telefono VARCHAR(50),
    IN p_observacion VARCHAR(500)
)
BEGIN
    DECLARE v_existencia INT;
	DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_existencia
    FROM cliente
    WHERE dni_cliente = p_dni;

    IF v_existencia > 0 THEN
    	SET v_msg = CONCAT('Ya existe un cliente con el DNI "', p_dni, '".');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1003;

    ELSE
        INSERT INTO cliente (
            dni_cliente,nombre_cliente,apellido_cliente,correo_cliente,telefono_cliente,
            observacion_cliente,estado
        )
        VALUES (
            p_dni,p_nombre,p_apellido,p_correo,p_telefono,p_observacion,DEFAULT
        );
        SELECT CONCAT('Cliente "', p_nombre, ' ', p_apellido, '" registrado correctamente.') AS mensaje;
    END IF;
END$$

DELIMITER ;



/* ============================================================---------------------------------------------------------------------------------------
   4. CONTRATO
   Valida que el empleado y el turno existan antes de insertar.
   Un empleado puede tener múltiples contratos, por eso no se
   valida duplicado de id_empleado sino solo integridad.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_contrato;
DELIMITER $$
CREATE PROCEDURE insertar_contrato (
    IN p_descripcion VARCHAR(255),
    IN p_fecha DATE,
    IN p_id_turno INT,
    IN p_id_empleado INT,
    IN p_id_tipo_contrato INT,
    IN p_id_cargo_empleado INT
)
BEGIN
    DECLARE v_emp_existe INT;
    DECLARE v_turno_existe INT;
    DECLARE v_tipo_existe INT;
    DECLARE v_cargo_existe INT;
	DECLARE v_msg VARCHAR(500);

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
    WHERE id_cargo = p_id_cargo_empleado;

    IF v_emp_existe = 0 THEN
    	SET v_msg = CONCAT('Error: el empleado con ID ', p_id_empleado, ' no existe o está inactivo.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1004;

    ELSEIF v_turno_existe = 0 THEN
    	SET v_msg = CONCAT('Error: el turno con ID ', p_id_turno, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1005;

    ELSEIF v_tipo_existe = 0 THEN
    	SET v_msg = CONCAT('Error: el tipo de contrato con ID ', p_id_tipo_contrato, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1006;

    ELSEIF v_cargo_existe = 0 THEN
    	SET v_msg = CONCAT('Error: el cargo con ID ', p_id_cargo_empleado, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
    	MYSQL_ERRNO = 1007;

    ELSE
        INSERT INTO contrato (
            descripcion_contrato,fecha_contrato,id_turno,id_empleado,id_tipo_contrato
        )
        VALUES (
            p_descripcion,IFNULL(p_fecha, NOW()),p_id_turno,p_id_empleado,p_id_tipo_contrato
        );

        SELECT CONCAT('Contrato registrado correctamente para el empleado ID ', p_id_empleado) AS mensaje;
    END IF;
END$$

DELIMITER ;


/* ============================================================
   5. USUARIO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_usuario;
DELIMITER $$

CREATE PROCEDURE insertar_usuario (
    IN p_codigo VARCHAR(100),
    IN p_password VARCHAR(255),
    IN p_observacion VARCHAR(500),
    IN p_id_cargo INT
)
BEGIN
    DECLARE v_codigo_existe INT;
    DECLARE v_cargo_existe INT;
	DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_codigo_existe
    FROM usuario
    WHERE UPPER(codigo_usuario) = UPPER(p_codigo);

    SELECT COUNT(*) INTO v_cargo_existe
    FROM cargo
    WHERE id_cargo = p_id_cargo AND estado = 1;

    IF v_codigo_existe > 0 THEN
    	SET v_msg = CONCAT('Ya existe un usuario con el código "', p_codigo, '".');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1008;

    ELSEIF v_cargo_existe = 0 THEN
    	SET v_msg = CONCAT('Error: el cargo con ID ', p_id_cargo, ' no existe o está inactivo.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1009;

    ELSE
        INSERT INTO usuario (
            codigo_usuario,
            password_usuario,
            observacion_usuario,
            id_cargo,
            estado
        )
        VALUES (
            p_codigo,
            p_password,
            p_observacion,
            p_id_cargo,
            DEFAULT
        );

        SELECT CONCAT('Usuario "', p_codigo, '" registrado correctamente.') AS mensaje;
    END IF;
END$$

DELIMITER ;


/* ============================================================
   6. PEDIDO
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_pedido;
DELIMITER $$

CREATE PROCEDURE insertar_pedido (
    IN p_fecha TIMESTAMP,
    IN p_id_cliente INT,
    IN p_id_empleado INT,
    IN p_id_tipo_pedido INT
)
BEGIN
    DECLARE v_count INT;
	DECLARE v_msg VARCHAR(500);

    IF p_fecha IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha del pedido no puede ser NULL.',
	    MYSQL_ERRNO = 1010;
    END IF;

    IF p_fecha > NOW() THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha del pedido no puede ser futura.',
    	MYSQL_ERRNO = 1011;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM cliente
    WHERE id_cliente = p_id_cliente;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('El cliente ', p_id_cliente, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1012;

    END IF;

    SELECT COUNT(*) INTO v_count
    FROM empleado
    WHERE id_empleado = p_id_empleado;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('El empleado ', p_id_empleado, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1013;

    END IF;

    SELECT COUNT(*) INTO v_count
    FROM tipo_pedido
    WHERE id_tipo_pedido = p_id_tipo_pedido;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('El tipo de pedido ', p_id_tipo_pedido, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1014;

    END IF;

    INSERT INTO pedido (
        fecha_pedido,
        id_cliente,
        id_empleado,
        id_tipo_pedido
    )
    VALUES (
        p_fecha,
        p_id_cliente,
        p_id_empleado,
        p_id_tipo_pedido
    );
    SELECT 'Pedido insertado correctamente.' AS mensaje;

END$$

DELIMITER ;




/* ============================================================
   7. MESA
   Valida que el número de mesa sea único y tenga formato M-X.
   Valida que la capacidad sea mayor a 0.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_mesa;
DELIMITER $$

CREATE PROCEDURE insertar_mesa(
    IN p_numero_mesa VARCHAR(50),
    IN p_capacidad INT
)
BEGIN
    DECLARE v_existe INT;
	DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_existe
    FROM mesa
    WHERE numero_mesa = p_numero_mesa;

    IF v_existe > 0 THEN
    	SET v_msg = CONCAT('La mesa ', p_numero_mesa, ' ya existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1015;

    END IF;

    IF p_capacidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La capacidad debe ser mayor a 0.',
        MYSQL_ERRNO = 1016;
    END IF;

    IF p_numero_mesa NOT REGEXP '^M-[0-9]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El número de mesa debe tener el formato M-X, donde X es un número.',
        MYSQL_ERRNO = 1017;
    END IF;

    INSERT INTO mesa(
        numero_mesa,
        capacidad,
        estado
    )
    VALUES(
        p_numero_mesa,
        p_capacidad,
        DEFAULT
    );

    SELECT 'Mesa registrada correctamente.' AS mensaje;

END$$

DELIMITER ;






/* ============================================================
   8. EMPLEADO
   Valida duplicado por DNI.
   Valida formato de correos y teléfonos.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_empleado;
DELIMITER $$

CREATE PROCEDURE insertar_empleado(
    IN p_dni CHAR(8),
    IN p_nombres VARCHAR(255),
    IN p_apellidos VARCHAR(255),
    IN p_fecha_nacimiento DATE,
    IN p_fecha_registro DATE,
    IN p_lugar_residencia VARCHAR(255),
    IN p_correo1 VARCHAR(255),
    IN p_correo2 VARCHAR(255),
    IN p_telefono1 VARCHAR(20),
    IN p_telefono2 VARCHAR(20),
    IN p_observacion VARCHAR(500),
    IN p_id_genero INT
)
BEGIN
    DECLARE v_existe INT;
	DECLARE v_msg VARCHAR(500);

    SELECT COUNT(*) INTO v_existe
    FROM empleado
    WHERE dni_empleado = p_dni;

    IF v_existe > 0 THEN
    	SET v_msg = CONCAT('El empleado con DNI ', p_dni, ' ya existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1018;

    END IF;

    IF p_correo1 NOT REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El correo electrónico 1 no tiene un formato válido.',
        MYSQL_ERRNO = 1019;
    END IF;

    IF p_correo2 IS NOT NULL AND p_correo2 NOT REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El correo electrónico 2 no tiene un formato válido.',
        MYSQL_ERRNO = 1020;
    END IF;

    IF p_telefono1 NOT REGEXP '^[0-9]{9}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El teléfono 1 no tiene un formato válido.',
        MYSQL_ERRNO = 1021;
    END IF;

    IF p_telefono2 IS NOT NULL AND p_telefono2 NOT REGEXP '^[0-9]{9}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El teléfono 2 no tiene un formato válido.',
        MYSQL_ERRNO = 1022;
    END IF;

    INSERT INTO empleado(
        dni_empleado,
        nombre_empleado,
        apellido_empleado,
        fecha_nacimiento,
        fecha_registro,
        direccion_empleado,
        correo_principal,
        correo_secundario,
        telefono_principal,
        telefono_secundario,
        observacion_empleado,
        id_genero,
        estado
    )
    VALUES(
        p_dni,
        p_nombres,
        p_apellidos,
        p_fecha_nacimiento,
        p_fecha_registro,
        p_lugar_residencia,
        p_correo1,
        p_correo2,
        p_telefono1,
        p_telefono2,
        p_observacion,
        p_id_genero,
        DEFAULT
    );

    SELECT 'Empleado registrado correctamente.' AS mensaje;

END$$

DELIMITER ;






/* ============================================================
   9. DETALLE PEDIDO
   Valida que la cantidad sea mayor a 0.
   Valida que el precio unitario sea válido.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_detalle_pedido;
DELIMITER $$

CREATE PROCEDURE insertar_detalle_pedido(
    IN p_id_pedido INT,
    IN p_id_plato_menu INT,
    IN p_cantidad INT,
    IN p_precio_unitario DECIMAL(10,2),
    IN p_observacion VARCHAR(500)
)
BEGIN
    -- 1. Validaciones de lógica simple
    IF p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La cantidad debe ser mayor a cero.', MYSQL_ERRNO = 1023;
    ELSEIF p_precio_unitario < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio no puede ser negativo.', MYSQL_ERRNO = 1024;
    
    -- 2. Validación de existencia y estado (La desratización técnica)
    ELSEIF (SELECT COUNT(*) FROM plato_menu WHERE id_plato_menu = p_id_plato_menu AND estado = 1) = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El plato no existe o no está disponible.', MYSQL_ERRNO = 1025;
    
    ELSE
        -- 3. Inserción limpia
        INSERT INTO detalle_pedido(
            id_pedido,
            id_plato_menu,
            cantidad,
            precio_unitario,
            observacion_detalle
        )
        VALUES(
            p_id_pedido,
            p_id_plato_menu,
            p_cantidad,
            p_precio_unitario,
            IFNULL(p_observacion, 'Sin observaciones')
        );

        SELECT 'Detalle agregado con éxito.' AS mensaje;
    END IF;

END$$

DELIMITER ;







/* ============================================================
   10. FACTURA
   Valida que el total sea mayor a 0.
   Valida duplicado y formato del número de comprobante.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_factura;
DELIMITER $$

CREATE PROCEDURE insertar_factura(
    IN p_numero_comprobante VARCHAR(50),
    IN p_fecha DATE,
    IN p_total DECIMAL(10,2),
    IN p_id_pedido INT,
    IN p_id_tipo_pago INT
)
BEGIN
    DECLARE v_count INT;
	DECLARE v_msg VARCHAR(500);

    IF p_total <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Total inválido',
        MYSQL_ERRNO = 1025;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM factura
    WHERE UPPER(TRIM(numero_comprobante)) = UPPER(TRIM(p_numero_comprobante));

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe una factura con el mismo número de comprobante.',
        MYSQL_ERRNO = 1026;
    END IF;

    IF p_numero_comprobante NOT REGEXP '^F[0-9]{3}-[0-9]{5}$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El número de comprobante debe tener el formato FXXX-XXXXX.',
        MYSQL_ERRNO = 1027;
    END IF;

    INSERT INTO factura(
        numero_comprobante,
        fecha_pago,
        total_factura,
        id_pedido,
        id_tipo_pago
    )
    VALUES(
        p_numero_comprobante,
        IFNULL(p_fecha, NOW()),
        p_total,
        p_id_pedido,
        p_id_tipo_pago
    );

    SELECT 'Factura registrada correctamente.' AS mensaje;

END$$

DELIMITER ;




/* ============================================================
    11. PRODUCTO 
============================================================ */
DROP PROCEDURE IF EXISTS insertar_producto;
DELIMITER $$

CREATE PROCEDURE insertar_producto(
    IN p_nombre VARCHAR(255),
    IN p_precio_unitario DECIMAL(10,2),
    IN p_stock_minimo INT,
    IN p_stock_actual INT,
    IN p_observacion TEXT,
    IN p_id_unidad_medida INT
)
BEGIN
    DECLARE v_count INT;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_estado_um TINYINT;
    DECLARE v_obs TEXT DEFAULT TRIM(p_observacion);

    -- 1. Validar que el nombre no esté vacío PRIMERO
    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El nombre del producto no puede estar vacío.',
        MYSQL_ERRNO = 1029;
    END IF;

    -- 2. Validar nombre duplicado y su estado (Lógica solicitada)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente
    FROM producto
    WHERE UPPER(TRIM(nombre_producto)) = UPPER(TRIM(p_nombre))
    GROUP BY nombre_producto;

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            -- El producto existe pero está desactivado
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El producto ya existe pero está INACTIVO. Debe reactivarlo en el panel de gestión.',
            MYSQL_ERRNO = 1043;
        ELSE
            -- El producto ya está activo
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un producto ACTIVO con este nombre.',
            MYSQL_ERRNO = 1034;
        END IF;
    END IF;

    -- 3. Validar precios y stock
    IF p_precio_unitario < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El precio debe ser >= 0.', MYSQL_ERRNO = 1030;
    END IF;

    IF p_stock_minimo < 0 OR p_stock_actual < 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: El stock no puede ser negativo.', MYSQL_ERRNO = 1031;
    END IF;

    -- 4. Validar existencia y estado de la Unidad de Medida
    SELECT estado INTO v_estado_um 
    FROM unidad_medida 
    WHERE id_unidad_medida = p_id_unidad_medida;

    IF v_estado_um IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida no existe.', MYSQL_ERRNO = 1033;
    ELSIF v_estado_um = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: La unidad de medida seleccionada está inactiva.', MYSQL_ERRNO = 1034;
    END IF;

    -- 5. Ejecutar inserción
    INSERT INTO producto(
        nombre_producto,
        precio_producto,
        stock_minimo,
        stock_actual,
        observacion_producto,
        id_unidad_medida,
        estado 
    )
    VALUES(
        TRIM(p_nombre),
        p_precio_unitario,
        p_stock_minimo,
        p_stock_actual,
        IF(v_obs = '', NULL, v_obs),
        p_id_unidad_medida,
        1
    );

    SELECT CONCAT('Producto "', TRIM(p_nombre), '" registrado correctamente.') AS mensaje;

END$$

DELIMITER ;






/* ============================================================
    12. PROVEEDOR
    Valida duplicado por RUC y correo.
    Valida formato y campos obligatorios.
    Asigna estado 1 por defecto e incluye observación.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_proveedor;
DELIMITER $$

CREATE PROCEDURE insertar_proveedor(
    IN p_ruc CHAR(11),
    IN p_razon_social VARCHAR(255),
    IN p_telefono VARCHAR(50),
    IN p_correo VARCHAR(255),
    IN p_direccion VARCHAR(255),
    IN p_observacion TEXT
)
BEGIN
    -- Declaración de variables para control
    DECLARE v_count INT;
    DECLARE v_estado_existente TINYINT;
    DECLARE v_msg VARCHAR(255);

    -- 1. MANEJO DE ERRORES Y TRANSACCIONES
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL; -- Re-lanza el error para que Java lo capture
    END;

    START TRANSACTION;

    -- 2. VALIDACIÓN DE FORMATO (Longitud y que sea solo números)
    IF LENGTH(TRIM(p_ruc)) <> 11 OR TRIM(p_ruc) NOT REGEXP '^[0-9]+$' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El RUC debe tener exactamente 11 dígitos numéricos.',
        MYSQL_ERRNO = 1036;
    END IF;

    -- 3. VALIDACIÓN DE CAMPOS OBLIGATORIOS
    IF TRIM(p_razon_social) = '' OR p_razon_social IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La razón social es obligatoria.', MYSQL_ERRNO = 1037;
    END IF;

    -- 4. VALIDACIÓN DE FORMATO DE CORREO (Regex profesional)
    IF TRIM(p_correo) NOT REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El formato del correo es inválido.', MYSQL_ERRNO = 1042;
    END IF;

    -- 5. VALIDACIÓN DE DUPLICADOS (Detecta si está activo o inactivo)
    SELECT COUNT(*), MAX(estado) INTO v_count, v_estado_existente 
    FROM proveedor 
    WHERE ruc = TRIM(p_ruc);

    IF v_count > 0 THEN
        IF v_estado_existente = 0 THEN
            -- Caso especial: El RUC existe pero fue borrado/desactivado antes
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RUC ya existe pero está INACTIVO. Debe reactivarlo.',
            MYSQL_ERRNO = 1043;
        ELSE
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un proveedor activo con este RUC.',
            MYSQL_ERRNO = 1034;
        END IF;
    END IF;

    -- 6. VALIDACIÓN DE CORREO DUPLICADO
    IF EXISTS(SELECT 1 FROM proveedor WHERE correo_proveedor = TRIM(p_correo)) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El correo electrónico ya está registrado por otro proveedor.',
        MYSQL_ERRNO = 1035;
    END IF;
    
    -- 7. Validar longitud de la observación
    IF p_observacion IS NOT NULL AND LENGTH(TRIM(p_observacion)) > 500 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La observación es demasiado larga (máximo 500 caracteres).',
        MYSQL_ERRNO = 1044; -- Código correlativo al anterior
    END IF;
    
    -- 8. INSERCIÓN CON NORMALIZACIÓN
    INSERT INTO proveedor(
        ruc, 
        razon_social, 
        telefono_proveedor, 
        correo_proveedor, 
        direccion_proveedor, 
        observacion_proveedor, 
        estado
    )
    VALUES(
        TRIM(p_ruc),
        UPPER(TRIM(p_razon_social)), -- Mayúsculas para uniformidad en reportes
        TRIM(p_telefono),
        LOWER(TRIM(p_correo)),        -- Minúsculas para correos
        TRIM(p_direccion),
        TRIM(p_observacion),
        1                             -- Estado activo por defecto
    );

    COMMIT;
    
    SELECT 'Proveedor registrado exitosamente.' AS mensaje;

END$$

DELIMITER ;





/* ============================================================
   13. PROVEEDOR_PRODUCTO
   Valida precio, tiempo de entrega y existencia de proveedor y producto.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_proveedor_producto;
DELIMITER $$

CREATE PROCEDURE insertar_proveedor_producto(
    IN p_id_proveedor INT,
    IN p_id_producto INT,
    IN p_precio_compra DECIMAL(10,2),
    IN p_tiempo_entrega_dias INT
)
BEGIN
    DECLARE v_count INT;
	DECLARE v_msg VARCHAR(500);

    IF p_precio_compra < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de compra debe ser mayor o igual a 0.',
        MYSQL_ERRNO = 1041;
    END IF;

    IF p_tiempo_entrega_dias < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El tiempo de entrega debe ser mayor o igual a 0.',
        MYSQL_ERRNO = 1042;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM proveedor
    WHERE id_proveedor = p_id_proveedor;

    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El proveedor no existe.',
        MYSQL_ERRNO = 1043;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM producto
    WHERE id_producto = p_id_producto;

    IF v_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El producto no existe.',
        MYSQL_ERRNO = 1044;
    END IF;

    INSERT INTO proveedor_producto(
        id_proveedor,
        id_producto,
        precio_compra,
        tiempo_entrega,
        fecha_registro_pp
    )
    VALUES(
        p_id_proveedor,
        p_id_producto,
        p_precio_compra,
        p_tiempo_entrega_dias,
        NOW()
    );

    SELECT 'Proveedor-Producto insertado exitosamente.' AS mensaje;

END$$

DELIMITER ;







/* ============================================================
   14. PLATO_MENU
   Valida nombre, precio y existencia de categoría.
   Valida duplicado por nombre.
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
   15. RESERVA
   Valida fechas, cantidad de personas, existencia de cliente y mesa.
   Valida conflicto de horario en la misma mesa.
   ============================================================ */
DROP PROCEDURE IF EXISTS insertar_reserva;
DELIMITER $$

CREATE PROCEDURE insertar_reserva(
    IN p_fecha_registro DATE,
    IN p_fecha_inicio DATETIME,
    IN p_fecha_fin DATETIME,
    IN p_cantidad_personas INT,
    IN p_observacion VARCHAR(500),
    IN p_id_cliente INT,
    IN p_id_mesa INT
)
BEGIN
    DECLARE v_count INT;
	DECLARE v_msg VARCHAR(500);

    IF p_fecha_inicio >= p_fecha_fin THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de inicio debe ser anterior a la fecha de fin.',
        MYSQL_ERRNO = 1049;
    END IF;

    IF p_cantidad_personas <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La cantidad de personas debe ser mayor a 0.',
        MYSQL_ERRNO = 1050;
    END IF;

    SELECT COUNT(*) INTO v_count
    FROM cliente
    WHERE id_cliente = p_id_cliente;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('El cliente con ID ', p_id_cliente, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1051;

    END IF;

    SELECT COUNT(*) INTO v_count
    FROM mesa
    WHERE id_mesa = p_id_mesa;

    IF v_count = 0 THEN
    	SET v_msg = CONCAT('La mesa con ID ', p_id_mesa, ' no existe.');
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =  v_msg,
        MYSQL_ERRNO = 1052;

    END IF;

    SELECT COUNT(*) INTO v_count
    FROM reserva
    WHERE id_mesa = p_id_mesa
    AND p_fecha_inicio < fecha_fin
    AND p_fecha_fin > fecha_inicio;

    IF v_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe una reserva para la misma mesa en ese horario.',
        MYSQL_ERRNO = 1053;
    END IF;

    INSERT INTO reserva(
        fecha_registro,
        fecha_inicio,
        fecha_fin,
        cantidad_personas,
        observacion_reserva,
        id_cliente,
        id_mesa,
        estado
    )
    VALUES(
        IFNULL(p_fecha_registro, NOW()),
        p_fecha_inicio,
        p_fecha_fin,
        p_cantidad_personas,
        p_observacion,
        p_id_cliente,
        p_id_mesa,
        DEFAULT
    );

    SELECT 'Reserva insertada exitosamente.' AS mensaje;

END$$

DELIMITER ;

