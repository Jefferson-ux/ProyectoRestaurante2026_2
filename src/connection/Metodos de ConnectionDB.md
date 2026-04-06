# MÉTODOS CONNECTIO_DB

## DESCRIPCIÓN

Este documento toma nota de los métodos que se obtienen de ConnectionDB.  
Además se explicará su funcionamiento y dónde usarlo.

## MÉTODOS

-   **public ConnectionDB() {}** -> Método constructor. Este no se usa fuera, sirve para ejecutar el código dentro de este apenas se instancie el objeto ConnectionDB. En este caso, inicializa la conexión apenas se instancie la clase: `ConnectionDB connecction = new Connection()`
-   **public public void closeConnection(){}** --> Cerrar la conexión. Esto libera recursos innecesarios en el momento. Mejora el rendimiento, asi que es muy recomendable su uso. Usado casi obligatoriamente en estos casos:
    -   **Después de una consulta (Query):** Si solo vas a leer un nombre de la base de datos, abres, lees y cierras inmediatamente.
    -   **Al cerrar la aplicación:** Para asegurar que no queden procesos "zombis" en el servidor.
    -   **En el bloque finally:** Es la práctica recomendada para asegurar que se cierre incluso si ocurre un error (Exception).
-   **public Connection getConnection(){}:** Usar la conexión en otros archivos




src/
├── config/                   # 1. LA LLAVE: Conexión a la DB
│   └── Conexion.java         # Solo abrir/cerrar conexión
│
├── model.beans/              # 2. LOS MOLDES: Clases espejo de tus 15 tablas
│   ├── Usuario.java          # Variables: id, nombre, pin (Getters/Setters)
│   ├── Producto.java         
│   └── Venta.java            
│
├── model.dao/                # 3. EL MOTOR: Aquí escribes todo el SQL
│   ├── UsuarioDAO.java       # Métodos: validarPin(), registrar(), listar()
│   ├── ProductoDAO.java      
│   └── VentaDAO.java         
│
├── controller/               # 4. EL PUENTE: Une la vista con los datos
│   ├── LoginController.java  # Lógica del PIN y acceso al menú
│   └── AlmacenController.java # Lógica de cálculos o validaciones CRUD
│
└── view/                     # 5. LA CARA: Todo lo hecho con Matisse
    ├── FrmLogin.java         # Ventana de PIN
    ├── FrmMenu.java          # Menú principal
    └── FrmMantenimiento.java # Ventanas CRUD para las tablas






