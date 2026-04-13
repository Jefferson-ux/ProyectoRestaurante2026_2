
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProveedorProductoMethod {
    private final Connection conn;
    
    public ProveedorProductoMethod(){
        //===========================================//   
        //Crear la conexión al iniciar el formulario //
       //===========================================//   
           ConnectionDB connection = new ConnectionDB();
           
           
         //=====================================//  
        //Verificar que la conexion sea exitosa//
       //=====================================//
            this.conn = connection.getConnection();
       
            if (connection.getConnection() == null) {
            JOptionPane.showConfirmDialog(null, "No se puede conectar a la base de datos", "Error de conexión", 1);
        }
    }
    
    // Método en el DAO para cargar los ComboBoxes de forma genérica
    public void llenarCombo(JComboBox<ComboItem> cb, ResultSet rs, String colID, String colNombre) throws SQLException {
        cb.removeAllItems();
        while (rs.next()) {
            cb.addItem(new ComboItem(
                    rs.getInt(colID),
                    rs.getString(colNombre)
            ));
        }
    }
    
    //Metodos para obtener y rellenar el jcombobox de proveedor
    public ResultSet jcombobox_listarProveedores() throws SQLException{
        String sql = "Select * from vista_proveedor";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    
    //Metodos para obtener y rellenar el jcombobox de producto
    public ResultSet jcombobox_listarProductos() throws SQLException{
        String sql = "Select * from vista_producto";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    //Metodo para evitar duplicados
    public boolean existeRelacion(int id_proveedor, int id_producto) throws SQLException {
        // Aquí buscamos la combinación exacta de ambos IDs
        String sql = "SELECT 1 FROM proveedor_producto "
                + "WHERE id_proveedor = ? AND id_producto = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_proveedor);
            ps.setInt(2, id_producto);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true si la relación ya existe en la tabla
            }
        }
    }
    
    
    /* VIEWS --> MOSTRAR DATOS */
        public ResultSet listarProveedoresYProductos() throws SQLException{
        String sql = "Select * from vista_proveedor_producto WHERE `Estado` = 1";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
        
    /* VIEWS --> MOSTRAR DATOS DESACTIVADOS */
        public ResultSet listarProveedoresYProductosDesactivados() throws SQLException{
        String sql = "Select * from vista_proveedor_producto WHERE `Estado` = 0";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarProveedorYProducto(String nombre) throws SQLException{
        String sql = "{CALL buscar_proveedor_producto(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


    /* INSERTAR RELACIÓN PROVEEDOR - PRODUCTO */
    public void insertarProveedorProducto(int idProveedor, int idProducto, double precio, int tiempo) throws SQLException {

        // 1. Validación de duplicados usando tu método
        if (existeRelacion(idProveedor, idProducto)) {
            // Lanzamos la excepción para que la interfaz (el JFrame) la capture y muestre el mensaje
            throw new IllegalArgumentException("Ya existe una relación para este proveedor y producto.");
        }

        // 2. Llamada al procedimiento almacenado
        String sql = "{CALL insertar_proveedor_producto(?, ?, ?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setInt(2, idProducto);
            cs.setDouble(3, precio);
            cs.setInt(4, tiempo);

            cs.execute();
            System.out.println("Relación vinculada correctamente.");
        }
    }


    /* UPDATE --> ACTUALIZAR RELACIÓN PROVEEDOR-PRODUCTO */
    public void modificarProveedorProducto(int idProveedor, int idProducto, double precioCompra, int tiempoEntrega, java.sql.Date fecha) throws SQLException {

        // 1. Llamada al procedimiento Update_ProveedorProducto
        // El orden es: id_prov, id_prod, precio, tiempo, fecha
        String sql = "{CALL actualizar_proveedorproducto(?,?,?,?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setInt(2, idProducto);
            cs.setDouble(3, precioCompra);
            cs.setInt(4, tiempoEntrega);
            cs.setDate(5, fecha);

            cs.execute();
            System.out.println("Relación Proveedor-Producto modificada con éxito");
        }
    }


    /* DESACTIVAR RELACIÓN (BAJA LÓGICA) */
    public void desactivarProveedorProducto(int idProveedor, int idProducto) throws SQLException {
        if (idProveedor <= 0 || idProducto <= 0) {
            throw new IllegalArgumentException("Los códigos de proveedor y producto deben ser válidos.");
        }

        // Llamada al procedimiento CambiarEstadoProveedorProducto(id_prov, id_prod, estado)
        String sql = "{CALL cambiar_estado_proveedorproducto(?, ?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setInt(2, idProducto);
            cs.setInt(3, 0); // Estado: 0 = inactivo
            cs.execute();
            System.out.println("Relación desactivada con éxito.");
        }
    }

    /* ACTIVAR/REACTIVAR RELACIÓN */
    public void reactivarProveedorProducto(int idProveedor, int idProducto) throws SQLException {
        if (idProveedor <= 0 || idProducto <= 0) {
            throw new IllegalArgumentException("Los códigos de proveedor y producto deben ser válidos.");
        }

        String sql = "{CALL cambiar_estado_proveedorproducto(?, ?, ?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, idProveedor);
            cs.setInt(2, idProducto);
            cs.setInt(3, 1); // Estado: 1 = activo
            cs.execute();
            System.out.println("Relación reactivada con éxito.");
        }
    }
    
    public static class ComboItem {

        private int id;
        private String nombre;

        public ComboItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " - " + nombre; // Esto es lo que verá el usuario
        }
    }
}
