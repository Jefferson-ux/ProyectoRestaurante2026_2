USE db_restaurant;


/* ================================================ */
/*  GENERO (3)                                      */
/* ================================================ */
INSERT INTO genero (nombre_genero) VALUES
    ('M'),
    ('F'),
    ('NB');



/* ================================================ */
/*  CATEGORIA (10)                                  */
/* ================================================ */
INSERT INTO categoria (nombre_categoria, estado) VALUES
    ('Platos Marinos',              1),
    ('Comida Criolla',              1),
    ('Parrillas',                   1),
    ('Especialidades de la Casa',   1),
    ('Entradas',                    1),
    ('Menu del Dia',                1),
    ('Sopas y Caldos',              1),
    ('Postres',                     1),
    ('Bebidas',                     1),
    ('JugSOBREos Naturales',             1);



/* ================================================ */
/*  TIPO_PAGO (3)                                   */
/* ================================================ */
INSERT INTO tipo_pago (nombre_tipo_pago, estado) VALUES
    ('Efectivo',                  1),
    ('Tarjeta de credito/debito', 1),
    ('Transferencia bancaria',    1);



/* ================================================ */
/*  TIPO_PEDIDO (3)                                 */
/* ================================================ */
INSERT INTO tipo_pedido (nombre_tipo_pedido) VALUES
    ('Consumo en local'),
    ('Para llevar'),
    ('Delivery');



/* ================================================ */
/*  TIPO_CONTRATO (4)                               */
/* ================================================ */
INSERT INTO tipo_contrato (nombre_tipo_contrato) VALUES
    ('Contrato a Plazo Fijo'),
    ('Contrato Indefinido'),
    ('Contrato por Obra'),
    ('Contrato de Practicas');



/* ================================================ */
/*  TURNO (2)                                       */
/* ================================================ */
INSERT INTO turno (nombre_turno, horario_inicio, horario_final) VALUES
    ('Turno Manana',      '08:00:00', '16:00:00'),
    ('Turno Tarde-Noche', '16:00:00', '23:59:00');



/* ================================================ */
/*  MESA (20)                                       */
/* ================================================ */
INSERT INTO mesa (numero_mesa, capacidad, estado) VALUES
    ('M-01', 2, 1),
    ('M-02', 2, 1),
    ('M-03', 2, 1),
    ('M-04', 2, 1),
    ('M-05', 2, 1),
    ('M-06', 2, 1),
    ('M-07', 4, 1),
    ('M-08', 4, 1),
    ('M-09', 4, 1),
    ('M-10', 4, 1),
    ('M-11', 4, 1),
    ('M-12', 4, 1),
    ('M-13', 6, 1),
    ('M-14', 6, 1),
    ('M-15', 6, 1),
    ('M-16', 6, 1),
    ('M-17', 8, 1),
    ('M-18', 8, 1),
    ('M-19', 10, 1),
    ('M-20', 10, 1);



/* ================================================ */
/*  CLIENTE (30)                                    */
/* ================================================ */
INSERT INTO cliente (dni_cliente, nombre_cliente, apellido_cliente, correo_cliente, telefono_cliente, observacion_cliente, estado) VALUES
    ('71230001', 'Carlos',    'Ramirez Soto',    'carlos.ramirez@gmail.com',  '987600001', 'Cliente frecuente',                        1),
    ('71230002', 'Maria',     'Lopez Diaz',      'maria.lopez@outlook.com',   '987600002', 'Prefiere mesa cerca a la ventana',         1),
    ('71230003', 'Jose',      'Gutierrez Paz',   NULL,                        '987600003', NULL,                                       1),
    ('71230004', 'Ana',       'Torres Vega',     'ana.torres@gmail.com',      NULL,        NULL,                                       1),
    ('71230005', 'Luis',      'Flores Rios',     'luis.flores@gmail.com',     '987600005', NULL,                                       1),
    ('71230006', 'Rosa',      'Vargas Mena',     NULL,                        '987600006', NULL,                                       1),
    ('71230007', 'Pedro',     'Mendoza Cruz',    'pedro.mendoza@yahoo.com',   '987600007', 'Solicita siempre factura',                 1),
    ('71230008', 'Elena',     'Castillo Mora',   'elena.castillo@gmail.com',  NULL,        'Reserva para reuniones familiares',        1),
    ('71230009', 'Miguel',    'Quispe Huanca',   'miguel.quispe@gmail.com',   '987600009', NULL,                                       1),
    ('71230010', 'Sofia',     'Paredes Luna',    NULL,                        '987600010', 'Cliente muy puntual',                      1),
    ('71230011', 'Fernando',  'Salas Pinto',     'fernando.salas@gmail.com',  '987600011', 'Prefiere pagar con tarjeta',               1),
    ('71230012', 'Lucia',     'Ramos Tapia',     'lucia.ramos@hotmail.com',   '987600012', NULL,                                       1),
    ('71230013', 'Ricardo',   'Cruz Benites',    'ricardo.cruz@gmail.com',    NULL,        NULL,                                       0),
    ('71230014', 'Patricia',  'Morales Cano',    NULL,                        '987600014', 'Visita el restaurante los fines de semana',1),
    ('71230015', 'Juan',      'Huanca Ccopa',    'juan.huanca@gmail.com',     '987600015', NULL,                                       1),
    ('71230016', 'Carla',     'Mamani Ticona',   'carla.mamani@gmail.com',    '987600016', 'Cliente recomendado por otro cliente',     1),
    ('71230017', 'Victor',    'Condori Ayna',    NULL,                        '987600017', 'Prefiere atención rápida',                 1),
    ('71230018', 'Diana',     'Chavez Quispe',   'diana.chavez@gmail.com',    '987600018', NULL,                                       1),
    ('71230019', 'Andres',    'Pinto Salazar',   'andres.pinto@outlook.com',  '987600019', 'Suele pedir para llevar',                  1),
    ('71230020', 'Claudia',   'Vega Oliva',      NULL,                        '987600020', 'Le gusta sentarse en la terraza',          1),
    ('71230021', 'Hector',    'Alvarado Neira',  'hector.alvarado@gmail.com', '987600021', NULL,                                       1),
    ('71230022', 'Margarita', 'Soto Espinoza',   'margarita.soto@gmail.com',  NULL,        NULL,                                       1),
    ('71230023', 'Oscar',     'Delgado Fuentes', 'oscar.delgado@gmail.com',   '987600023', NULL,                                       1),
    ('71230024', 'Irma',      'Villanueva Ore',  NULL,                        '987600024', NULL,                                       0),
    ('71230025', 'Raul',      'Espinoza Meza',   'raul.espinoza@hotmail.com', '987600025', 'Le gusta probar platos nuevos',            1),
    ('71230026', 'Sandra',    'Benites Vargas',  'sandra.benites@gmail.com',  '987600026', 'Cliente habitual del almuerzo',            1),
    ('71230027', 'Roberto',   'Oliva Reyes',     NULL,                        '987600027', NULL,                                       1),
    ('71230028', 'Natalia',   'Tapia Flores',    'natalia.tapia@gmail.com',   NULL,        'Suele venir con grupo grande',             1),
    ('71230029', 'Alberto',   'Cano Perez',      'alberto.cano@gmail.com',    '987600029', 'Prefiere bebidas sin alcohol',             1),
    ('71230030', 'Veronica',  'Meza Cordova',    'veronica.meza@gmail.com',   '987600030', NULL,                                       1);



/* ================================================ */
/*  CARGO (5)                                       */
/* ================================================ */
INSERT INTO cargo (nombre_cargo, estado) VALUES
    ('Gerente General',    1),
    ('Jefe de Cocina',     1),
    ('Mesero',             1),
    ('Cajero',             1),
    ('Ayudante de Cocina', 1);



/* ================================================ */
/*  USUARIO (3)                                     */
/* ================================================ */
INSERT INTO usuario (codigo_usuario, password_usuario, id_cargo, estado) VALUES
    ('USR001', 'e3b0c44298fc1c149afb4c8996fb924', 1, 1),
    ('USR002', 'a665a45920422f9d417e4867efdc4fb8', 1, 1),
    ('USR003', '6b86b273ff34fce19d6b804eff5a3f57', 1, 0);



/* ================================================ */
/*  EMPLEADO (10)                                   */
/* ================================================ */
INSERT INTO empleado (dni_empleado, nombre_empleado, apellido_empleado, fecha_nacimiento, fecha_registro, direccion_empleado, correo_principal, correo_secundario, telefono_principal, telefono_secundario, observacion_empleado, id_genero, estado) VALUES
    ('80100001', 'Jorge Luis',     'Mamani Quispe',  '1985-03-12', '2022-01-15', 'Av. Sol 123, Cusco',          'j.mamani@restaurante.pe',    NULL,                            '966200001', NULL,        NULL,                                               1, 1),
    ('80100002', 'Maria Elena',    'Condori Huanca', '1990-07-25', DEFAULT,      'Jr. Lima 456, Arequipa',      'm.condori@restaurante.pe',   'm.condori.alt@gmail.com',       '966200002', '014512233', 'Bilingue espanol/ingles',                          2, 1),
    ('80100003', 'Carlos Andres',  'Paredes Soto',   '1988-11-08', '2023-03-10', 'Calle Loreto 789, Lima',      'c.paredes@restaurante.pe',   NULL,                            '966200003', '013301122', NULL,                                               1, 1),
    ('80100004', 'Lucia Fernanda', 'Ramos Vega',     '1995-05-17', DEFAULT,      'Av. Tupac Amaru 321, Puno',   'l.ramos@restaurante.pe',     'lucia.ramos.personal@gmail.com','966200004', NULL,        'Especialista en pasteleria y reposteria',          2, 1),
    ('80100005', 'Pedro Alonso',   'Chavez Torres',  '1982-09-30', '2020-09-01', 'Jr. Ancash 654, Lima',        'p.chavez@restaurante.pe',    NULL,                            '966200005', '012234455', NULL,                                               1, 1),
    ('80100006', 'Ana Belen',      'Flores Cruz',    '1993-01-22', '2024-01-08', 'Av. Grau 987, Trujillo',      'a.flores@restaurante.pe',    'ana.flores.b@outlook.com',      '966200006', '044112233', 'Certificada en Buenas Practicas de Manufactura',   2, 1),
    ('80100007', 'Ricardo Paul',   'Salas Mora',     '1987-06-14', '2019-11-12', 'Calle Union 147, Ica',        'r.salas@restaurante.pe',     NULL,                            '966200007', NULL,        'Experiencia en cocina internacional',              1, 1),
    ('80100008', 'Elena Rosa',     'Gutierrez Paz',  '1991-12-03', '2023-07-01', 'Av. Ejercito 258, Lima',      'e.gutierrez@restaurante.pe', 'elena.gutierrez@gmail.com',     '966200008', '012998877', NULL,                                               2, 1),
    ('80100009', 'Fernando Jose',  'Alvarado Rios',  '1980-04-19', DEFAULT,      'Jr. Piura 369, Lima',         'f.alvarado@restaurante.pe',  NULL,                            '966200009', '013456677', 'Responsable de turno noche y supervision de caja', 1, 1),
    ('80100010', 'Diana Cecilia',  'Benites Poma',   '1997-08-07', '2024-02-01', 'Av. Colonial 741, Lima',      'd.benites@restaurante.pe',   'd.benites.alt@gmail.com',       '966200010', NULL,        NULL,                                               2, 1);



/* ================================================ */
/*  CONTRATO (10)                                   */
/* ================================================ */
INSERT INTO contrato (descripcion_contrato, fecha_contrato, id_turno, id_empleado, id_tipo_contrato, id_cargo, estado) VALUES
    ('Contrato inicial chef principal',          '2022-01-15', 1,  1, 2, 2, 1),
    ('Contrato administracion y gestion',             DEFAULT, 2,  2, 2, 1, 1),
    ('Contrato mesero turno manana',             '2023-03-10', 1,  3, 1, 3, 1),
    ('Contrato pastelera y repostera',           '2023-05-20', 2,  4, 1, 5, 1),
    ('Contrato cocinero senior indefinido',           DEFAULT, 1,  5, 2, 2, 1),
    ('Contrato practicas pre-profesionales BPM', '2024-01-08', 2,  6, 4, 5, 1),
    ('Contrato jefe cocina experiencia intl.',   '2019-11-12', 1,  7, 2, 2, 1),
    ('Contrato mesera turno tarde reemplazo',    '2023-07-01', 2,  8, 3, 3, 1),
    ('Contrato cajero supervisor de turno',      '2022-08-15', 1,  9, 3, 4, 1),
    ('Contrato ayudante cocina nuevo ingreso',   '2024-02-01', 2, 10, 1, 5, 1);



/* ================================================ */
/*  PLATO_MENU (27)                                 */
/* ================================================ */
INSERT INTO plato_menu (nombre_plato, descripcion_plato, precio_plato, id_categoria, estado) VALUES
    -- Platos Marinos (id_categoria = 1)
    ('Ceviche Clasico',             'Pescado fresco con limon y aji',                   28.00,  1, 1),
    ('Arroz con Mariscos',          'Arroz sazonado con mariscos mixtos',               32.00,  1, 1),
    -- Comida Criolla (id_categoria = 2)
    ('Lomo Saltado',                'Carne salteada con papas fritas',                  25.00,  2,1),
    ('Aji de Gallina',              'Crema de gallina con arroz',                       22.00,  2,1),
    -- Parrillas (id_categoria = 3)
    ('Parrilla Mixta',              'Carne, pollo y chorizo a la parrilla',             40.00,  3,1),
    ('Bistec a lo Pobre',           'Carne con huevo, arroz y papas',                   30.00,  3,1),
    ('Picanha al Quebracho',        'Corte de res a la parrilla con sal de maras',      55.00,  3,1),
    -- Especialidades de la Casa (id_categoria = 4)
    ('Pollo a la Brasa',            'Pollo con papas y ensalada de casa',               35.00,  4,1),
    ('Chaufa Especial',             'Arroz chaufa con carnes mixtas',                   27.00,  4,1),
    ('Tacu Tacu con Lomo Saltado',  'Mantecoso de arroz y frejol con lomo fino al wok', 45.00,  2,1),
    -- Entradas (id_categoria = 5)
    ('Tequeños',                    'Palitos fritos rellenos de queso',                 12.00,  5,1),
    ('Causa Limeña',                'Papa rellena con pollo',                           15.00,  5,1),
    ('Papa a la Huancaina',         'Papa con salsa cremosa',                           14.00,  5,1),
    -- Menu del Dia (id_categoria = 6)
    ('Seco de Pollo con Frejoles',  'Pollo guisado acompañado con frejoles y arroz',    18.00,  6,1),
    ('Tallarines Verdes con Bistec','Pasta al pesto con carne a la plancha',            20.00,  6,1),
    ('Arroz con Pollo',             'Arroz verde con pollo y ensalada',                 17.00,  6,1),
    -- Sopas y Caldos (id_categoria = 7)
    ('Caldo de Gallina',            'Sopa caliente con gallina, huevo y fideos',        14.00,  7,1),
    ('Caldo de Mote',               'Caldo tradicional con carne y mote',               13.00,  7,1),
    ('Caldo de Res',                'Sopa nutritiva con carne de res y verduras',       15.00,  7,1),
    -- Postres (id_categoria = 8)
    ('Arroz con Leche',             'Postre tradicional',                               7.00,   8,1),
    ('Torta de Chocolate',          'Postre dulce casero',                              10.00,  8,1),
    -- Bebidas (id_categoria = 9)
    ('Chicha Morada',               'Bebida tradicional de maiz morado',                6.00,   9,1),
    ('Maracuya Helada',             'Bebida refrescante de maracuya',                   6.50,   9,1),
    ('Suspiro a la Limena',         'Dulce tradicional con merengue al oporto',         15.00,  5,1),
    -- Jugos Naturales (id_categoria = 10)
    ('Jugo de Naranja',             'Natural sin azucar',                               6.00,   10,1),
    ('Jugo de Papaya',              'Refrescante y natural',                            7.00,   10,1),
    ('Limonada Frozen',             'Zumo de limon granizado con abundante hielo',      10.00,  6,1);



/* ================================================ */
/*  PEDIDO (30)                                     */
/* ================================================ */
INSERT INTO pedido (fecha_pedido, id_cliente, id_empleado, id_tipo_pedido, estado) VALUES
    ('2025-05-01 12:30:00',  1,  3, 1, 1),
    ('2025-05-02 13:00:00',  2,  3, 1, 1),
    ('2025-05-03 19:15:00',  3,  5, 2, 1),
    ('2025-05-04 20:00:00',  4,  8, 1, 1),
    ('2025-05-05 12:45:00',  5,  3, 3, 1),
    ('2025-05-06 13:30:00',  6,  5, 1, 1),
    ('2025-05-07 19:00:00',  7,  8, 2, 1),
    ('2025-05-08 20:30:00',  8,  3, 1, 1),
    ('2025-05-09 12:00:00',  9,  5, 3, 1),
    ('2025-05-10 13:15:00', 10,  8, 1, 1),
    ('2025-05-11 19:30:00', 11,  3, 1, 1),
    ('2025-05-12 20:00:00', 12,  5, 2, 1),
    ('2025-05-13 12:30:00', 13,  8, 1, 1),
    ('2025-05-14 13:00:00', 14,  3, 3, 1),
    ('2025-05-15 19:45:00', 15,  5, 1, 1),
    ('2025-05-16 20:15:00', 16,  8, 1, 1),
    ('2025-05-17 12:00:00', 17,  3, 2, 1),
    ('2025-05-18 13:30:00', 18,  5, 1, 1),
    ('2025-05-19 19:00:00', 19,  8, 3, 1),
    ('2025-05-20 20:00:00', 20,  3, 1, 1),
    ('2025-05-21 12:30:00', 21,  5, 1, 1),
    ('2025-05-22 13:00:00', 22,  8, 2, 1),
    ('2025-05-23 19:15:00', 23,  3, 1, 1),
    ('2025-05-24 20:30:00', 24,  5, 3, 1),
    ('2025-05-25 12:00:00', 25,  8, 1, 1),
    ('2025-05-26 13:45:00', 26,  3, 1, 1),
    ('2025-05-27 19:00:00', 27,  5, 2, 1),
    ('2025-05-28 20:00:00', 28,  8, 1, 1),
    ('2025-05-29 12:30:00', 29,  3, 3, 1),
    ('2025-05-30 13:00:00', 30,  5, 1, 1);



/* ================================================ */
/*  DETALLE_PEDIDO (60)                             */
/* ================================================ */
INSERT INTO detalle_pedido (id_pedido, id_plato_menu, cantidad, precio_unitario, observacion_detalle) VALUES
    (1, 1, 2, 28.00, 'Sin sal'),
    (1, 22, 2, 6.00, NULL),
    (2, 3, 1, 25.00, 'Extra picante'),
    (2, 23, 1, 6.50, NULL),
    (3, 5, 2, 40.00, NULL),
    (3, 24, 2, 15.00, 'Servir caliente'),
    (4, 8, 1, 35.00, NULL),
    (4, 2, 2, 32.00, 'Cortar en cubos'),
    (5, 6, 1, 30.00, NULL),
    (5, 21, 2, 10.00, NULL),
    (6, 7, 1, 55.00, 'Bien cocido'),
    (6, 22, 1, 6.00, NULL),
    (7, 4, 1, 22.00, NULL),
    (7, 1, 1, 28.00, 'Extra limón'),
    (8, 9, 2, 27.00, NULL),
    (8, 23, 1, 6.50, 'Sin azúcar'),
    (9, 10, 1, 45.00, NULL),
    (9, 25, 1, 6.00, 'Servir frío'),
    (10, 3, 2, 25.00, NULL),
    (10, 12, 1, 15.00, 'Sin cebolla'),
    (11, 5, 1, 40.00, NULL),
    (11, 20, 2, 7.00, 'Extra dulce'),
    (12, 8, 1, 35.00, NULL),
    (12, 24, 1, 15.00, 'Servir frío'),
    (13, 2, 2, 32.00, NULL),
    (13, 7, 1, 55.00, 'Término medio'),
    (14, 1, 1, 28.00, NULL),
    (14, 13, 2, 14.00, 'Sin sal'),
    (15, 6, 1, 30.00, NULL),
    (15, 26, 1, 7.00, 'Natural'),
    (16, 9, 2, 27.00, NULL),
    (16, 22, 2, 6.00, 'Sin azúcar'),
    (17, 4, 1, 22.00, NULL),
    (17, 27, 1, 10.00, 'Bien helado'),
    (18, 8, 1, 35.00, NULL),
    (18, 21, 2, 10.00, 'Extra dulce'),
    (19, 3, 1, 25.00, NULL),
    (19, 14, 1, 18.00, 'Servir caliente'),
    (20, 7, 1, 55.00, NULL),
    (20, 23, 2, 6.50, 'Sin azúcar'),
    (21, 1, 2, 28.00, NULL),
    (21, 25, 1, 6.00, 'Natural'),
    (22, 10, 1, 45.00, NULL),
    (22, 17, 1, 14.00, 'Bien caliente'),
    (23, 5, 1, 40.00, NULL),
    (23, 12, 2, 15.00, 'Extra picante'),
    (24, 9, 1, 27.00, NULL),
    (24, 22, 1, 6.00, 'Sin azúcar'),
    (25, 3, 1, 25.00, NULL),
    (25, 23, 1, 6.50, 'Servir frío'),
    (26, 8, 1, 35.00, NULL),
    (26, 21, 1, 10.00, 'Extra dulce'),
    (27, 6, 1, 30.00, NULL),
    (27, 26, 1, 7.00, 'Natural'),
    (28, 2, 2, 32.00, NULL),
    (28, 24, 2, 15.00, 'Servir frío'),
    (29, 1, 1, 28.00, NULL),
    (29, 13, 1, 14.00, 'Sin sal'),
    (30, 7, 1, 55.00, NULL),
    (30, 22, 2, 6.00, 'Sin azúcar');



/* ================================================ */
/*  FACTURA (30)                                    */
/* ================================================ */
INSERT INTO factura (numero_comprobante, fecha_pago, total_factura, id_pedido, id_tipo_pago, fecha_registro) VALUES
    ('F001-00001', '2025-05-01', 104.00,  1, 1, '2025-05-01 08:30:00'),
    ('F001-00002', '2025-05-02',  60.00,  2, 2, '2025-05-02 09:15:00'),
    ('F001-00003', '2025-05-03',  90.00,  3, 1, '2025-05-03 10:00:00'),
    ('F001-00004', '2025-05-04',  84.00,  4, 3, '2025-05-04 11:45:00'),
    ('F001-00005', '2025-05-05',  60.00,  5, 1, '2025-05-05 14:20:00'),
    ('F001-00006', '2025-05-06',  56.00,  6, 2, '2025-05-06 16:10:00'),
    ('F001-00007', '2025-05-07',  58.00,  7, 1, '2025-05-07 08:05:00'),
    ('F001-00008', '2025-05-08', 114.00,  8, 2, '2025-05-08 12:30:00'),
    ('F001-00009', '2025-05-09',  54.00,  9, 1, '2025-05-09 17:40:00'),
    ('F001-00010', '2025-05-10',  90.00, 10, 3, '2025-05-10 09:55:00'),
    ('F001-00011', '2025-05-11',  79.00, 11, 1, '2025-05-11 11:20:00'),
    ('F001-00012', '2025-05-12',  76.00, 12, 2, '2025-05-12 15:00:00'),
    ('F001-00013', '2025-05-13',  89.00, 13, 1, '2025-05-13 10:10:00'),
    ('F001-00014', '2025-05-14',  74.00, 14, 1, '2025-05-14 13:45:00'),
    ('F001-00015', '2025-05-15',  62.00, 15, 3, '2025-05-15 16:30:00'),
    ('F001-00016', '2025-05-16', 124.00, 16, 2, '2025-05-16 09:00:00'),
    ('F001-00017', '2025-05-17',  40.00, 17, 1, '2025-05-17 11:15:00'),
    ('F001-00018', '2025-05-18',  68.00, 18, 2, '2025-05-18 14:40:00'),
    ('F001-00019', '2025-05-19',  54.00, 19, 1, '2025-05-19 12:20:00'),
    ('F001-00020', '2025-05-20',  88.00, 20, 1, '2025-05-20 10:05:00'),
    ('F001-00021', '2025-05-21',  94.00, 21, 3, '2025-05-21 15:50:00'),
    ('F001-00022', '2025-05-22',  40.00, 22, 2, '2025-05-22 08:30:00'),
    ('F001-00023', '2025-05-23',  63.00, 23, 1, '2025-05-23 17:10:00'),
    ('F001-00024', '2025-05-24',  48.00, 24, 2, '2025-05-24 11:00:00'),
    ('F001-00025', '2025-05-25',  46.00, 25, 1, '2025-05-25 13:25:00'),
    ('F001-00026', '2025-05-26',  48.00, 26, 3, '2025-05-26 14:50:00'),
    ('F001-00027', '2025-05-27',  74.00, 27, 1, '2025-05-27 10:40:00'),
    ('F001-00028', '2025-05-28',  80.00, 28, 2, '2025-05-28 16:15:00'),
    ('F001-00029', '2025-05-29',  62.00, 29, 1, '2025-05-29 09:30:00'),
    ('F001-00030', '2025-05-30',  68.00, 30, 2, '2025-05-30 15:20:00');



/* ================================================ */
/*  RESERVA (10)                                    */
/* ================================================ */
INSERT INTO reserva (fecha_registro, fecha_inicio, fecha_fin, cantidad_personas, observacion_reserva, id_cliente, id_mesa, estado) VALUES
    ('2025-06-10 00:00:00', '2025-06-10 12:30:00', '2025-06-10 14:30:00', 4, 'Cliente canceló 2 veces la última reserva',  1,  3, 1),
    ('2025-06-11 00:00:00', '2025-06-13 18:00:00', '2025-06-13 20:00:00', 2, NULL,                                          2,  1, 1),
    ('2025-06-12 00:00:00', '2025-06-14 09:15:00', '2025-06-14 11:15:00', 6, NULL,                                          5,  7, 1),
    ('2025-06-13 00:00:00', '2025-06-17 20:00:00', '2025-06-17 22:00:00', 8, 'Cliente canceló 1 vez',                      10, 11, 0),
    ('2025-06-14 00:00:00', '2025-06-18 13:45:00', '2025-06-18 15:45:00', 4, NULL,                                         15,  4, 1),
    ('2025-06-15 00:00:00', '2025-06-19 10:30:00', '2025-06-19 12:30:00', 6, NULL,                                         20,  9, 1),
    ('2025-06-16 00:00:00', '2025-06-20 14:20:00', '2025-06-20 16:20:00', 2, 'Cliente habitual, muy puntual',               8,  2, 1),
    ('2025-06-17 00:00:00', '2025-06-23 11:45:00', '2025-06-23 13:45:00', 4, NULL,                                         25,  5, 0),
    ('2025-06-18 00:00:00', '2025-06-24 16:30:00', '2025-06-24 18:30:00', 3, 'Suele pedir mesa cerca de la barra',         12, 14, 1),
    ('2025-06-19 00:00:00', '2025-06-27 19:15:00', '2025-06-27 21:15:00', 5, 'Canceló la reserva anterior sin avisar',     18, 19, 1);


/*================================================*/
/*             INVENTARIO SEPARADO                */
/*================================================*/


/* ================================================ */
/*  UNIDAD_MEDIDA (6)                               */
/* ================================================ */
INSERT INTO unidad_medida (nombre_unidad_medida, abreviatura) VALUES
    ('Kilogramo', 'kg'),
    ('Litro',     'lt'),
    ('Unidad',    'und'),
    ('Botella',   'bot'),
    ('Caja',      'cja'),
    ('Gramo',     'gr');



/* ================================================ */
/* PRODUCTO (40)                                    */
/* ================================================ */
INSERT INTO producto (nombre_producto, precio_producto, stock_minimo, stock_actual, observacion_producto, id_unidad_medida, estado) VALUES
    ('Arroz blanco extra',          2.50,  10,  45, 'Grano largo seleccionado, marca Costeño', 1, 1),
    ('Aceite vegetal girasol',      5.80,   5,  18, 'Botella de 1 litro premium', 2, 1),
    ('Azucar rubia',                2.20,   8,  30, NULL, 1, 1),
    ('Harina de trigo preparada',   2.80,  10,  40, 'Contiene polvo de hornear', 1, 1),
    ('Sal de mesa yodada',          0.90,   3,  15, NULL, 1, 1),
    ('Pollo entero fresco',         8.50,   5,  25, 'Peso promedio 2.2kg por unidad', 1, 1),
    ('Carne de res lomo fino',     18.00,   5,  20, 'Corte tipo exportación', 1, 1),
    ('Filete de pescado corvina',  15.00,   3,  12, 'Mantener a -18 grados centígrados', 1, 1),
    ('Camarones frescos medianos', 35.00,   2,   8, 'Producto de temporada', 1, 1),
    ('Carne de cerdo pierna',      14.00,   4,  16, NULL, 1, 1),
    ('Leche entera fresca',         4.20,   6,  24, 'Envase Tetra Pak larga vida', 2, 1),
    ('Mantequilla sin sal',        18.50,   2,   6, 'Marca Gloria, presentación 200g', 1, 1),
    ('Queso fresco serrano',       22.00,   2,   8, 'Producido en Cajamarca', 1, 1),
    ('Huevos de gallina',           0.45,  30, 120, 'Huevos rosados tamaño grande', 3, 1),
    ('Crema de leche UHT',         10.00,   3,  10, 'Contenido graso 35%', 2, 1),
    ('Tomate italiano',             3.50,   5,  22, 'Maduración media para ensalada', 1, 1),
    ('Cebolla roja peruana',        2.00,   5,  18, NULL, 1, 1),
    ('Papa amarilla huayro',        2.80,  10,  50, 'Ideal para puré o causa', 1, 1),
    ('Aji amarillo fresco',         6.00,   2,   9, NULL, 1, 1),
    ('Ajo pelado importado',        8.00,   2,   7, 'Presentación en bolsa al vacío', 1, 1),
    ('Pimiento rojo morrón',        5.00,   2,  10, NULL, 1, 1),
    ('Zanahoria baby',              1.80,   5,  20, 'Bolsas de 500 gramos', 1, 1),
    ('Brocoli fresco',              4.50,   3,  12, 'Lavar antes de consumir', 1, 1),
    ('Espinaca organica',           3.20,   2,   8, 'Sin pesticidas químicos', 1, 1),
    ('Limon sutil peruano',         3.80,   3,  14, 'Cosecha del norte del país', 1, 1),
    ('Maiz morado seco',            4.00,   2,   9, 'Variedad Canteño', 1, 1),
    ('Quinua blanca organica',      9.50,   3,  15, 'Grano lavado y perlado', 1, 1),
    ('Lentejas importadas',         4.80,   3,  12, 'Origen canadiense', 1, 1),
    ('Frijoles canario',            5.50,   3,  11, NULL, 1, 1),
    ('Champiñones frescos',        12.00,   2,   7, 'Mantener refrigerado', 1, 1),
    ('Agua mineral sin gas',        1.50,  24,  96, 'Caja de 24 botellas de 500ml', 4, 1),
    ('Gaseosa cola 1.5L',           4.50,  12,  48, 'Envase descartable', 4, 1),
    ('Cerveza nacional lata',       4.00,  12,  60, 'Pack de 6 unidades', 3, 0),
    ('Vino tinto reserva',         35.00,   3,  12, 'Cosecha 2021 Valle de Ica', 4, 1),
    ('Jugo naranja concentrado',    6.00,   4,  16, 'Sin azúcar añadida', 2, 1),
    ('Cafe molido especial',       28.00,   1,   4, 'Tueste medio artesanal', 1, 1),
    ('Te verde en bolsas',          8.50,   2,   8, 'Caja de 25 sobres', 5, 1),
    ('Canela en rama entera',      15.00,   1,   2, 'Importada de Sri Lanka', 6, 1),
    ('Chuño blanco selecto',        4.20,   2,   9, 'Procesado en altura', 1, 0),
    ('Rocoto rojo fresco',          7.00,   1,   5, 'Sabor muy picante', 1, 1);



/* ================================================ */
/* PROVEEDOR (7)                                    */
/* ================================================ */
INSERT INTO proveedor (ruc, razon_social, telefono_proveedor, correo_proveedor, direccion_proveedor, observacion_proveedor, estado) VALUES
    ('20100254785', 'Distribuidora El Campo SAC', '123456789', 'Imprent@gmail.com', 'Av. Los Libertadores 234, Lima', 'Proveedor principal de granos y harinas', 1),
    ('20304512896', 'Insumos del Norte EIRL', '1093647837', 'GranjaUltralinda@gmail.com', 'Jr. Huallaga 456, Trujillo', 'Entregas solo los días martes y jueves', 1),
    ('20451236987', 'Agropecuaria Los Andes SA', '123857622', 'ArtesinaFAC@hotmail.com', 'Carretera Central Km 45, Huancayo', NULL, 1),
    ('20512478963', 'Frigorifico Central SAC', '388756617', 'Forzaproducts@gmail.com', 'Calle Real 789, Huancayo', 'Especialista en cortes de res y cerdo', 1),
    ('20623541897', 'Bebidas y Mas EIRL', '56646789', 'EmpresaSAC@gmail.com', 'Av. Arequipa 1023, Lima', 'Contacto directo con preventa: Anexo 102', 1),
    ('20714523698', 'Lacteos del Sur SAC', '904998384', 'Lacteon@gmail.com', 'Av. Ejercito 550, Arequipa', 'Crédito a 30 días previa orden de compra', 0),
    ('20836547120', 'Importaciones Gourmet SAC', '17762563', 'Tecnoly2002@gmail.com', 'Jr. Union 512, Lima', 'Productos importados de Europa y Asia', 1);



/* ================================================ */
/*  PROVEEDOR_PRODUCTO (20)                         */
/* ================================================ */
INSERT INTO proveedor_producto (id_proveedor, id_producto, precio_compra, tiempo_entrega, fecha_registro) VALUES
    (1,  1,  2.10, 2, '2024-01-10'),
    (1,  3,  1.90, 2, '2024-01-10'),
    (1,  4,  2.50, 3, '2024-01-10'),
    (2, 16,  3.00, 2, '2024-02-05'),
    (2, 17,  1.70, 2, '2024-02-05'),
    (2, 18,  2.40, 2, '2024-02-05'),
    (3,  6,  7.80, 3, '2024-03-01'),
    (3,  7, 16.50, 3, '2024-03-01'),
    (3, 10, 12.00, 3, '2024-03-01'),
    (4,  8, 13.00, 2, '2024-03-15'),
    (4,  9, 32.00, 2, '2024-03-15'),
    (5, 31,  1.20, 1, '2024-04-01'),
    (5, 32,  4.00, 1, '2024-04-01'),
    (5, 33,  3.50, 1, '2024-04-01'),
    (6, 11,  3.80, 2, '2024-04-10'),
    (6, 12, 16.00, 2, '2024-04-10'),
    (6, 13, 19.00, 2, '2024-04-10'),
    (7, 27,  8.50, 4, '2024-05-01'),
    (7, 39,  3.80, 4, '2024-05-01'),
    (7, 40,  6.00, 3, '2024-05-01');