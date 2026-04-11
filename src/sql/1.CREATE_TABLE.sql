-- Active: 1774983836645@@127.0.0.1@3306@db_restaurant
/*================================================*/
/*              TABLAS PRINCIPALES                */
/*================================================*/

DROP DATABASE IF EXISTS db_restaurant;
CREATE DATABASE db_restaurant CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE db_restaurant;


CREATE TABLE genero (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nombre_genero VARCHAR(2) NOT NULL COMMENT 'M:Masculino - F:Femenino - NB: No binario'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tipos de genero';


CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Categorias de platos';


CREATE TABLE tipo_pago (
    id_tipo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo_pago VARCHAR(100) NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Tipos de pago disponibles';


CREATE TABLE tipo_pedido (
    id_tipo_pedido INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo_pedido VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE tipo_contrato (
    id_tipo_contrato INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo_contrato VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE turno (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    nombre_turno VARCHAR(100) NOT NULL,
    horario_inicio DATETIME NOT NULL,
    horario_final DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Turnos de trabajo';


CREATE TABLE mesa (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    numero_mesa VARCHAR(10) NOT NULL,
    capacidad INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    CHECK (capacidad > 0),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Mesas del restaurante';


CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    dni_cliente CHAR(8) NOT NULL UNIQUE,
    nombre_cliente VARCHAR(100) NOT NULL,
    apellido_cliente VARCHAR(100) NOT NULL,
    correo_cliente VARCHAR(150),
    telefono_cliente VARCHAR(15),
    observacion_cliente VARCHAR(500),
    estado TINYINT DEFAULT 1 NOT NULL,
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Clientes del sistema';


CREATE TABLE cargo (
    id_cargo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cargo VARCHAR(100) NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    codigo_usuario VARCHAR(20) NOT NULL UNIQUE,
    password_usuario VARCHAR(255) NOT NULL,
    observacion_usuario VARCHAR(500),
    id_cargo INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Usuarios del sistema (usar password encriptado)';




CREATE TABLE empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    dni_empleado CHAR(8) NOT NULL UNIQUE,
    nombre_empleado VARCHAR(100) NOT NULL,
    apellido_empleado VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_registro DATE NOT NULL,
    direccion_empleado VARCHAR(150) NOT NULL,
    correo_principal VARCHAR(150) NOT NULL,
    correo_secundario VARCHAR(150),
    telefono_principal VARCHAR(15) NOT NULL,
    telefono_secundario VARCHAR(15),
    observacion_empleado VARCHAR(500),
    id_genero INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_genero) REFERENCES genero(id_genero),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE contrato (
    id_contrato INT AUTO_INCREMENT PRIMARY KEY,
    descripcion_contrato VARCHAR(200) NOT NULL,
    fecha_contrato DATE NOT NULL,
    id_turno INT NOT NULL,
    id_empleado INT NOT NULL,
    id_tipo_contrato INT NOT NULL,
    id_cargo INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_turno) REFERENCES turno(id_turno),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_contrato) REFERENCES tipo_contrato(id_tipo_contrato),
    FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE plato_menu (
    id_plato_menu INT AUTO_INCREMENT PRIMARY KEY,
    nombre_plato VARCHAR(100) NOT NULL,
    descripcion_plato VARCHAR(500),
    precio_plato DECIMAL(10,2) NOT NULL,
    id_categoria INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CHECK (precio_plato >= 0),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_pedido DATETIME NOT NULL,
    id_cliente INT NOT NULL,
    id_empleado INT NOT NULL,
    id_tipo_pedido INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_tipo_pedido) REFERENCES tipo_pedido(id_tipo_pedido),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_plato_menu INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    observacion_detalle VARCHAR(500),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_plato_menu) REFERENCES plato_menu(id_plato_menu),
    CHECK (cantidad > 0),
    CHECK (precio_unitario >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE factura (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    numero_comprobante VARCHAR(20) NOT NULL,
    fecha_pago DATE NOT NULL,
    total_factura DECIMAL(10,2) NOT NULL,
    id_pedido INT UNIQUE NOT NULL,
    id_tipo_pago INT NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_tipo_pago) REFERENCES tipo_pago(id_tipo_pago),
    CHECK (total_factura >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE reserva (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    cantidad_personas INT NOT NULL,
    observacion_reserva VARCHAR(500),
    id_cliente INT NOT NULL,
    id_mesa INT NOT NULL,
    estado TINYINT DEFAULT 1 NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_mesa) REFERENCES mesa(id_mesa),
    CHECK (fecha_fin > fecha_inicio),
    CHECK (cantidad_personas > 0),
    CHECK (estado IN (0,1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


  /*==========================================*/
 /*=============== INVENTARIO ===============*/
/*==========================================*/

CREATE TABLE unidad_medida (
    id_unidad_medida INT AUTO_INCREMENT PRIMARY KEY,
    nombre_unidad_medida VARCHAR(45) NOT NULL UNIQUE,
    abreviatura VARCHAR(10) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    precio_producto DECIMAL(10,2) NOT NULL,
    stock_minimo INT NOT NULL,
    stock_actual INT NOT NULL,
    observacion_producto TEXT,
    id_unidad_medida INT NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    FOREIGN KEY (id_unidad_medida) REFERENCES unidad_medida(id_unidad_medida),
    CHECK (estado IN (0, 1)), 
    CHECK (precio_producto >= 0),
    CHECK (stock_minimo >= 0 AND stock_actual >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    ruc CHAR(11) NOT NULL UNIQUE,
    razon_social VARCHAR(150) NOT NULL,
    telefono_proveedor VARCHAR(15) NOT NULL,
    correo_proveedor VARCHAR(150) NOT NULL,
    direccion_proveedor VARCHAR(150) NOT NULL,
    observacion_proveedor TEXT,
    estado TINYINT(1) NOT NULL DEFAULT 1,

    CHECK (estado IN (0, 1)),
    CHECK (ruc REGEXP '^[0-9]{11}$'), -- Asegura 11 dígitos numéricos
    CHECK (TRIM(razon_social) <> ''), -- Evita nombres vacíos
    CHECK (TRIM(direccion_proveedor) <> ''), -- Evita direcciones vacías
    CHECK (telefono_proveedor REGEXP '^[0-9]{7,15}$'),
    CHECK (correo_proveedor REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'),
    CHECK (CHAR_LENGTH(observacion_proveedor) <= 500) -- Limite físico en la tabla
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




CREATE TABLE proveedor_producto (
    id_proveedor INT NOT NULL,
    id_producto INT NOT NULL,
    precio_compra DECIMAL(10,2) NOT NULL,
    tiempo_entrega INT NOT NULL,
    fecha_registro DATE NOT NULL,
    estado TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_proveedor, id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CHECK (precio_compra >= 0),
    CHECK (tiempo_entrega >= 0),
    CHECK (estado IN (0, 1)) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

  /*==========================================*/
 /*================ INDICES =================*/
/*==========================================*/
CREATE INDEX idx_pedido_cliente ON pedido(id_cliente);
CREATE INDEX idx_pedido_empleado ON pedido(id_empleado);
CREATE INDEX idx_detalle_pedido ON detalle_pedido(id_pedido);
CREATE INDEX idx_reserva_cliente ON reserva(id_cliente);
CREATE INDEX idx_reserva_mesa ON reserva(id_mesa);
CREATE INDEX idx_plato_categoria ON plato_menu(id_categoria);
CREATE INDEX idx_contrato_empleado ON contrato(id_empleado);
CREATE INDEX idx_pp_producto ON proveedor_producto(id_producto);
