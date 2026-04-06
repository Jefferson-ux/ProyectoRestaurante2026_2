USE db_restaurant;
-- ================================================
-- SCRIPT DE INSERCIÓN DE DATOS - MySQL
-- Adaptado desde Oracle (secuencias eliminadas,
-- fechas convertidas, estructura ajustada al DDL)
-- ================================================

USE db_restaurant;

/* ================================================ */
/*  GENERO (3)                                      */
/* ================================================ */
INSERT INTO genero (nombre_genero) VALUES
    ('M'),
    ('F'),
    ('NE');




/* ================================================ */
/*  CATEGORIA (10)                                  */
/* ================================================ */
INSERT INTO categoria (nombre_categoria, estado) VALUES
    ('Platos Marinos',           1),
    ('Comida Criolla',           1),
    ('Parrillas',                1),
    ('Especialidades de la Casa',1),
    ('Entradas',                 1),
    ('Menu del Dia',             1),
    ('Sopas y Caldos',           1),
    ('Postres',                  1),
    ('Bebidas',                  1),
    ('Jugos Naturales',          1);

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
    ('Turno Manana',      '2025-01-01 06:00:00', '2025-01-01 14:00:00'),
    ('Turno Tarde-Noche', '2025-01-01 14:00:00', '2025-01-01 22:00:00');

/* ================================================ */
/*  MESA (20)                                       */
/* ================================================ */
INSERT INTO mesa (numero_mesa, capacidad, estado) VALUES
    ('M-01',  2, 1),
    ('M-02',  2, 1),
    ('M-03',  4, 1),
    ('M-04',  4, 1),
    ('M-05',  4, 1),
    ('M-06',  4, 1),
    ('M-07',  6, 1),
    ('M-08',  6, 1),
    ('M-09',  6, 1),
    ('M-10',  6, 1),
    ('M-11',  8, 1),
    ('M-12',  8, 1),
    ('M-13',  8, 1),
    ('M-14',  4, 1),
    ('M-15',  4, 1),
    ('M-16',  2, 1),
    ('M-17', 10, 1),
    ('M-18', 10, 1),
    ('M-19',  6, 1),
    ('M-20',  4, 1);

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
/*  id_cargo referencia la tabla cargo              */
/* ================================================ */
INSERT INTO usuario (codigo_usuario, password_usuario, observacion_usuario, id_cargo, estado) VALUES
    ('USR001', 'e3b0c44298fc1c149afb4c8996fb924',  'Administrador del sistema',        1, 1),
    ('USR002', 'a665a45920422f9d417e4867efdc4fb8', 'Usuario temporal para supervisión', 1, 1),
    ('USR006', '6b86b273ff34fce19d6b804eff5a3f57', 'Cuenta desactivada temporalmente',  1, 0);

/* ================================================ */
/*  EMPLEADO (10)                                   */
/*  MySQL; el cargo se registra en contrato         */
/* ================================================ */
INSERT INTO empleado (dni_empleado, nombre_empleado, apellido_empleado, fecha_nacimiento, fecha_registro, direccion_empleado, correo_principal, correo_secundario, telefono_principal, telefono_secundario, observacion_empleado, id_genero, estado) VALUES
    ('80100001', 'Jorge Luis',     'Mamani Quispe',  '1985-03-12', '2022-01-15', 'Av. Sol 123, Cusco',          'j.mamani@restaurante.pe',    NULL,                            '966200001', NULL,        NULL,                                               1, 1),
    ('80100002', 'Maria Elena',    'Condori Huanca', '1990-07-25', '2021-06-01', 'Jr. Lima 456, Arequipa',      'm.condori@restaurante.pe',   'm.condori.alt@gmail.com',       '966200002', '014512233', 'Bilingue espanol/ingles',                          2, 1),
    ('80100003', 'Carlos Andres',  'Paredes Soto',   '1988-11-08', '2023-03-10', 'Calle Loreto 789, Lima',      'c.paredes@restaurante.pe',   NULL,                            '966200003', '013301122', NULL,                                               1, 1),
    ('80100004', 'Lucia Fernanda', 'Ramos Vega',     '1995-05-17', '2023-05-20', 'Av. Tupac Amaru 321, Puno',   'l.ramos@restaurante.pe',     'lucia.ramos.personal@gmail.com','966200004', NULL,        'Especialista en pasteleria y reposteria',          2, 1),
    ('80100005', 'Pedro Alonso',   'Chavez Torres',  '1982-09-30', '2020-09-01', 'Jr. Ancash 654, Lima',        'p.chavez@restaurante.pe',    NULL,                            '966200005', '012234455', NULL,                                               1, 1),
    ('80100006', 'Ana Belen',      'Flores Cruz',    '1993-01-22', '2024-01-08', 'Av. Grau 987, Trujillo',      'a.flores@restaurante.pe',    'ana.flores.b@outlook.com',      '966200006', '044112233', 'Certificada en Buenas Practicas de Manufactura',   2, 1),
    ('80100007', 'Ricardo Paul',   'Salas Mora',     '1987-06-14', '2019-11-12', 'Calle Union 147, Ica',        'r.salas@restaurante.pe',     NULL,                            '966200007', NULL,        'Experiencia en cocina internacional',               1, 1),
    ('80100008', 'Elena Rosa',     'Gutierrez Paz',  '1991-12-03', '2023-07-01', 'Av. Ejercito 258, Lima',      'e.gutierrez@restaurante.pe', 'elena.gutierrez@gmail.com',     '966200008', '012998877', NULL,                                               2, 1),
    ('80100009', 'Fernando Jose',  'Alvarado Rios',  '1980-04-19', '2022-08-15', 'Jr. Piura 369, Lima',         'f.alvarado@restaurante.pe',  NULL,                            '966200009', '013456677', 'Responsable de turno noche y supervision de caja', 1, 1),
    ('80100010', 'Diana Cecilia',  'Benites Poma',   '1997-08-07', '2024-02-01', 'Av. Colonial 741, Lima',      'd.benites@restaurante.pe',   'd.benites.alt@gmail.com',       '966200010', NULL,        NULL,                                               2, 1);



/* ================================================ */
/*  CONTRATO (10)                                   */
/*  id_cargo tomado del Oracle original por empleado*/
/*  emp1=Jefe Cocina(2), emp2=Gerente(1),           */
/*  emp3=Mesero(3),      emp4=Ayud.Cocina(5),       */
/*  emp5=Jefe Cocina(2), emp6=Ayud.Cocina(5),       */
/*  emp7=Jefe Cocina(2), emp8=Mesero(3),            */
/*  emp9=Cajero(4),      emp10=Ayud.Cocina(5)       */
/* ================================================ */
INSERT INTO contrato (descripcion_contrato, fecha_contrato, id_turno, id_empleado, id_tipo_contrato, id_cargo, estado) VALUES
    ('Contrato inicial chef principal',          '2022-01-15', 1,  1, 2, 2, 1),
    ('Contrato administracion y gestion',        '2021-06-01', 2,  2, 2, 1, 1),
    ('Contrato mesero turno manana',             '2023-03-10', 1,  3, 1, 3, 1),
    ('Contrato pastelera y repostera',           '2023-05-20', 2,  4, 1, 5, 1),
    ('Contrato cocinero senior indefinido',      '2020-09-01', 1,  5, 2, 2, 1),
    ('Contrato practicas pre-profesionales BPM', '2024-01-08', 2,  6, 4, 5, 1),
    ('Contrato jefe cocina experiencia intl.',   '2019-11-12', 1,  7, 2, 2, 1),
    ('Contrato mesera turno tarde reemplazo',    '2023-07-01', 2,  8, 3, 3, 1),
    ('Contrato cajero supervisor de turno',      '2022-08-15', 1,  9, 3, 4, 1),
    ('Contrato ayudante cocina nuevo ingreso',   '2024-02-01', 2, 10, 1, 5, 1);



/* ================================================ */
/*  PLATO_MENU (27)                                 */
/*  Categorías: 5=Entradas, 2=Comida Criolla,       */
/*  8=Postres, 9=Bebidas, 7=Sopas y Caldos,         */
/*  6=Menu del Dia (ensaladas)                      */
/* ================================================ */
INSERT INTO plato_menu (nombre_plato, descripcion_plato, precio_plato, id_categoria, estado) VALUES
    -- Entradas (id_categoria=5)
    ('Causa limeña de pollo',      'Papa amarilla molida con pollo, mayonesa y aji amarillo',            18.00, 5, 1),
    ('Papa a la huancaina',        'Papa sancochada bañada en cremosa salsa huancaina con queso',        16.00, 5, 1),
    ('Anticuchos de corazon',      'Brochetas de corazon marinadas al aji panca servidas a la brasa',    22.00, 5, 1),
    ('Tequeños de queso fresco',   'Tequeños crocantes rellenos de queso serrano con aji de mariscos',   14.00, 5, 1),
    ('Ceviche mixto premium',      'Corvina y mariscos frescos en leche de tigre con aji limo',          35.00, 5, 1),
    -- Platos Principales (id_categoria=2)
    ('Lomo saltado clasico',       'Lomo fino saltado con tomate, cebolla, papas fritas y sillao',       42.00, 2, 1),
    ('Aji de gallina tradicional', 'Pollo deshilachado en salsa de aji amarillo con nueces y aceitunas', 38.00, 2, 1),
    ('Pollo al horno con romero',  'Pierna de pollo al horno con papas doradas y ensalada fresca',       36.00, 2, 1),
    ('Pescado a lo macho',         'Corvina frita con salsa mariscos picante, arroz y tostones',         48.00, 2, 1),
    ('Seco de res norteño',        'Guiso de res con culantro, frijoles canario y arroz blanco',         40.00, 2, 1),
    ('Chicharron de cerdo criollo','Cerdo frito crocante acompanado de camote, mote y sarza criolla',    44.00, 2, 1),
    ('Arroz con mariscos del dia', 'Arroz meloso con camaron, calamar y conchas segun disponibilidad',   52.00, 2, 1),
    ('Quinua saltada con verduras','Quinua blanca saltada con brocoli, pimiento, champiñones y soya',    30.00, 2, 1),
    -- Postres (id_categoria=8)
    ('Picarones con miel de higos','Picarones esponjosos de camote y zapallo con miel de higos y canela',14.00, 8, 1),
    ('Suspiro limeño clasico',     'Manjar blanco con merengue italiano al oporto y canela molida',      16.00, 8, 1),
    ('Cheesecake de maracuya',     'Tarta de queso horneada con coulis de maracuya y frutos rojos',      18.00, 8, 1),
    ('Helado artesanal 3 sabores', 'Tres bolas de helado artesanal: lucuma, chirimoya y maracuya',       12.00, 8, 1),
    -- Bebidas (id_categoria=9)
    ('Chicha morada artesanal',    'Chicha de maiz morado con membrillo, piña y canela en jarra',        10.00, 9, 1),
    ('Pisco sour clasico',         'Coctel de pisco quebranta, limon sutil, jarabe y clara de huevo',    22.00, 9, 1),
    ('Limonada frozen menta',      'Limonada batida con hielo, hoja de menta y jarabe de azucar',        12.00, 9, 1),
    ('Agua mineral 625ml',         'Botella de agua mineral sin gas 625 ml marca San Luis',               5.00, 9, 1),
    ('Jugo de naranja natural',    'Vaso grande de jugo de naranja recien exprimido sin azucar',         10.00, 9, 1),
    -- Sopas y Caldos (id_categoria=7)
    ('Sopa criolla limeña',        'Sopa con fideo cabello, carne molida, leche evaporada y aji panca',  24.00, 7, 1),
    ('Crema de espinaca organica', 'Crema suave de espinaca fresca con crutones al ajo y crema UHT',     20.00, 7, 1),
    ('Chupe de camaron arequipeño','Chupe cremoso de camaron con papa, leche, huevo pochado y rocoto',   45.00, 7, 1),
    -- Ensaladas / Menu del Dia (id_categoria=6)
    ('Ensalada fresca del huerto', 'Lechuga hidroponica, tomate cherry, cebolla morada y vinagreta',     18.00, 6, 1),
    ('Ensalada tibia de quinua',   'Quinua caliente con pepino, tomate, palta, almendras y limon',       22.00, 6, 1);



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
    ( 1,  6, 2, 42.00, 'Sin sal'),
    ( 1, 18, 2, 10.00, NULL),
    ( 2,  7, 1, 38.00, 'Extra picante'),
    ( 2, 19, 1, 22.00, NULL),
    ( 3,  5, 2, 35.00, NULL),
    ( 3, 22, 2, 10.00, 'Servir caliente'),
    ( 4,  9, 1, 48.00, NULL),
    ( 4,  1, 2, 18.00, 'Cortar en cubos pequeños'),
    ( 5, 10, 1, 40.00, NULL),
    ( 5, 18, 2, 10.00, NULL),
    ( 6, 11, 1, 44.00, 'Sin gluten'),
    ( 6, 20, 1, 12.00, NULL),
    ( 7,  6, 1, 42.00, NULL),
    ( 7,  2, 1, 16.00, 'Extra limón'),
    ( 8, 12, 2, 52.00, NULL),
    ( 8, 21, 1, 10.00, 'Sin azúcar'),
    ( 9, 13, 1, 30.00, NULL),
    ( 9, 23, 1, 24.00, 'Servir frío'),
    (10,  7, 2, 38.00, NULL),
    (10, 14, 1, 14.00, 'Sin cebolla'),
    (11,  5, 1, 35.00, NULL),
    (11, 19, 2, 22.00, 'Extra picante'),
    (12,  8, 1, 36.00, NULL),
    (12, 24, 1, 20.00, 'Servir caliente'),
    (13,  3, 2, 22.00, NULL),
    (13, 25, 1, 45.00, 'Cortar en cubos pequeños'),
    (14,  6, 1, 42.00, NULL),
    (14, 15, 2, 16.00, 'Sin sal'),
    (15, 10, 1, 40.00, NULL),
    (15, 26, 1, 22.00, 'Extra picante'),
    (16, 12, 2, 52.00, NULL),
    (16, 21, 2, 10.00, 'Sin azúcar'),
    (17,  4, 1, 18.00, NULL),
    (17, 27, 1, 22.00, 'Servir frío'),
    (18,  9, 1, 48.00, NULL),
    (18, 18, 2, 10.00, 'Extra limón'),
    (19,  7, 1, 38.00, NULL),
    (19, 16, 1, 16.00, 'Sin cebolla'),
    (20, 11, 1, 44.00, NULL),
    (20, 19, 2, 22.00, 'Extra picante'),
    (21,  6, 2, 42.00, NULL),
    (21, 22, 1, 10.00, 'Servir caliente'),
    (22, 13, 1, 30.00, NULL),
    (22, 17, 1, 10.00, 'Sin sal'),
    (23,  5, 1, 35.00, NULL),
    (23, 14, 2, 14.00, 'Extra picante'),
    (24,  8, 1, 36.00, NULL),
    (24, 20, 1, 12.00, 'Sin gluten'),
    (25,  3, 1, 22.00, NULL),
    (25, 23, 1, 24.00, 'Servir frío'),
    (26,  7, 1, 38.00, NULL),
    (26, 21, 1, 10.00, 'Extra limón'),
    (27, 12, 1, 52.00, NULL),
    (27, 26, 1, 22.00, 'Sin azúcar'),
    (28, 13, 2, 30.00, NULL),
    (28, 18, 2, 10.00, 'Servir caliente'),
    (29,  6, 1, 42.00, NULL),
    (29, 24, 1, 20.00, 'Sin cebolla'),
    (30,  9, 1, 48.00, NULL),
    (30, 22, 2, 10.00, 'Extra picante');



/* ================================================ */
/*  FACTURA (30)                                    */
/* ================================================ */
INSERT INTO factura (numero_comprobante, fecha_pago, total_factura, id_pedido, id_tipo_pago) VALUES
    ('F001-00001', '2025-05-01', 104.00,  1, 1),
    ('F001-00002', '2025-05-02',  60.00,  2, 2),
    ('F001-00003', '2025-05-03',  90.00,  3, 1),
    ('F001-00004', '2025-05-04',  84.00,  4, 3),
    ('F001-00005', '2025-05-05',  60.00,  5, 1),
    ('F001-00006', '2025-05-06',  56.00,  6, 2),
    ('F001-00007', '2025-05-07',  58.00,  7, 1),
    ('F001-00008', '2025-05-08', 114.00,  8, 2),
    ('F001-00009', '2025-05-09',  54.00,  9, 1),
    ('F001-00010', '2025-05-10',  90.00, 10, 3),
    ('F001-00011', '2025-05-11',  79.00, 11, 1),
    ('F001-00012', '2025-05-12',  76.00, 12, 2),
    ('F001-00013', '2025-05-13',  89.00, 13, 1),
    ('F001-00014', '2025-05-14',  74.00, 14, 1),
    ('F001-00015', '2025-05-15',  62.00, 15, 3),
    ('F001-00016', '2025-05-16', 124.00, 16, 2),
    ('F001-00017', '2025-05-17',  40.00, 17, 1),
    ('F001-00018', '2025-05-18',  68.00, 18, 2),
    ('F001-00019', '2025-05-19',  54.00, 19, 1),
    ('F001-00020', '2025-05-20',  88.00, 20, 1),
    ('F001-00021', '2025-05-21',  94.00, 21, 3),
    ('F001-00022', '2025-05-22',  40.00, 22, 2),
    ('F001-00023', '2025-05-23',  63.00, 23, 1),
    ('F001-00024', '2025-05-24',  48.00, 24, 2),
    ('F001-00025', '2025-05-25',  46.00, 25, 1),
    ('F001-00026', '2025-05-26',  48.00, 26, 3),
    ('F001-00027', '2025-05-27',  74.00, 27, 1),
    ('F001-00028', '2025-05-28',  80.00, 28, 2),
    ('F001-00029', '2025-05-29',  62.00, 29, 1),
    ('F001-00030', '2025-05-30',  68.00, 30, 2);

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
/*             INVENTARIO                         */
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
/*  PROVEEDOR (7)                                   */
/* ================================================ */
INSERT INTO proveedor ( ruc, razon_social , telefono_proveedor, correo_proveedor, direccion_proveedor) VALUES
    ('20100254785','Distribuidora El Campo SAC',  '123456789',  'Imprent@gmail.com',         'Av. Los Libertadores 234, Lima'),
    ('20304512896','Insumos del Norte EIRL',      '1093647837', 'GranjaUltralinda@gmail.com','Jr. Huallaga 456, Trujillo'),
    ('20451236987','Agropecuaria Los Andes SA',   '123857622',  'ArtesinaFAC@hotmail.com',   'Carretera Central Km 45, Huancayo'),
    ('20512478963','Frigorifico Central SAC',     '388756617',  'Forzaproducts@gmail.com',   'Calle Real 789, Huancayo'),
    ('20623541897','Bebidas y Mas EIRL',          '56646789',   'EmpresaSAC@gmail.com',      'Av. Arequipa 1023, Lima'),
    ('20714523698','Lacteos del Sur SAC',         '904998384',  'Lacteon@gmail.com',         'Av. Ejercito 550, Arequipa'),
    ('20836547120','Importaciones Gourmet SAC',   '17762563',   'Tecnoly2002@gmail.com',     'Jr. Union 512, Lima');

/* ================================================ */
/*  PRODUCTO (40)                                   */
/*  id_unidad_medida: kg=1,lt=2,und=3,              */
/*                   bot=4,cja=5,gr=6               */
/* ================================================ */
INSERT INTO producto (nombre_producto, precio_producto, stock_minimo, stock_actual, id_unidad_medida) VALUES
    ('Arroz blanco extra',          2.50,  10,  45, 1),
    ('Aceite vegetal girasol',      5.80,   5,  18, 2),
    ('Azucar rubia',                2.20,   8,  30, 1),
    ('Harina de trigo preparada',   2.80,  10,  40, 1),
    ('Sal de mesa yodada',          0.90,   3,  15, 1),
    ('Pollo entero fresco',         8.50,   5,  25, 1),
    ('Carne de res lomo fino',     18.00,   5,  20, 1),
    ('Filete de pescado corvina',  15.00,   3,  12, 1),
    ('Camarones frescos medianos', 35.00,   2,   8, 1),
    ('Carne de cerdo pierna',      14.00,   4,  16, 1),
    ('Leche entera fresca',         4.20,   6,  24, 2),
    ('Mantequilla sin sal',        18.50,   2,   6, 1),
    ('Queso fresco serrano',       22.00,   2,   8, 1),
    ('Huevos de gallina',           0.45,  30, 120, 3),
    ('Crema de leche UHT',         10.00,   3,  10, 2),
    ('Tomate italiano',             3.50,   5,  22, 1),
    ('Cebolla roja peruana',        2.00,   5,  18, 1),
    ('Papa amarilla huayro',        2.80,  10,  50, 1),
    ('Aji amarillo fresco',         6.00,   2,   9, 1),
    ('Ajo pelado importado',        8.00,   2,   7, 1),
    ('Pimiento rojo morrón',        5.00,   2,  10, 1),
    ('Zanahoria baby',              1.80,   5,  20, 1),
    ('Brocoli fresco',              4.50,   3,  12, 1),
    ('Espinaca organica',           3.20,   2,   8, 1),
    ('Limon sutil peruano',         3.80,   3,  14, 1),
    ('Maiz morado seco',            4.00,   2,   9, 1),
    ('Quinua blanca organica',      9.50,   3,  15, 1),
    ('Lentejas importadas',         4.80,   3,  12, 1),
    ('Frijoles canario',            5.50,   3,  11, 1),
    ('Champiñones frescos',        12.00,   2,   7, 1),
    ('Agua mineral sin gas',        1.50,  24,  96, 4),
    ('Gaseosa cola 1.5L',           4.50,  12,  48, 4),
    ('Cerveza nacional lata',       4.00,  12,  60, 3),
    ('Vino tinto reserva',         35.00,   3,  12, 4),
    ('Jugo naranja concentrado',    6.00,   4,  16, 2),
    ('Cafe molido especial',       28.00,   1,   4, 1),
    ('Te verde en bolsas',          8.50,   2,   8, 5),
    ('Canela en rama entera',      15.00,   1,   2, 6),
    ('Chuño blanco selecto',        4.20,   2,   9, 1),
    ('Rocoto rojo fresco',          7.00,   1,   5, 1);

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