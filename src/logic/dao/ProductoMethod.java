
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProductoMethod {
    private final Connection conn;
    
    public ProductoMethod(){
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
    
    public ResultSet combobox_ListarUnidadMedidas() throws SQLException {
        String sql = "select `Unidad de Medida` from vista_unidad_medida";
        Statement st =conn.createStatement(); // Creamos el statements
        ResultSet rs=st.executeQuery(sql); // Ejecutamos la consulta
        return rs; // Devolvemos los resultados 
    }
    
    public boolean existeProductoConNombre(String nombre, int id_producto) throws SQLException {
        String sql = "SELECT 1 FROM Producto "
            + "WHERE LOWER(nombre_producto) = LOWER(?) "
            + "AND id_producto <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre.trim());
        ps.setInt(2, id_producto); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
    }
    
    
    /* VIEWS --> MOSTRAR DATOS */
        public ResultSet listarProductos() throws SQLException{
        String sql = "Select * from vista_producto";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarProducto(String nombre) throws SQLException{
        String sql = "{CALL buscar_producto(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


       /* INSERT--> AGREGAR DATOS */
    public void insertarProducto(String nombre, double precio, int stock_minimo, int stock_actual, String obervacion,int id_unidad_medida) throws SQLException{
        if(existeProductoConNombre(nombre, 0)){
            throw  new IllegalArgumentException("Ya exsiste un producto con ese nombre.");
        }
        String sql = "{CALL insertar_producto(?,?,?,?,?,?)}";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setInt(3, stock_minimo);
            ps.setInt(4, stock_actual);
            ps.setString(5, obervacion);
            ps.setInt(6, id_unidad_medida);
            ps.execute();
            System.out.println("Producto insertado con éxito");
        }
    } 


    /* UPDATE --> ACTUALIZAR DATOS */
    public void modificarProducto(int id, String nombre, double precio, int stock_minimo, int stock_actual, String observacion, int id_unidad) throws SQLException {
        // El orden del PROCEDURE Update_Producto es: id, nombre, precio, stock_minimo, stock_actual, obs, id_unidad
        String sql = "{CALL Update_Producto(?,?,?,?,?,?,?)}";
       try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.setString(2, nombre);
            cs.setDouble(3, precio);
            cs.setInt(4, stock_minimo);
            cs.setInt(5, stock_actual);
            cs.setString(6, observacion);
            cs.setInt(7, id_unidad);
            cs.execute();
        }
    }




    //DESACTIVAR
    public void darDeBajaProducto(int codigoProducto) throws SQLException{
        String sql="CALL Desactivar_Producto(?)";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, codigoProducto); //Solo se pasa el codigo, el estado lo maneja el procedure
        cs.execute();
    }
    
    //ACtivar
    public void activarProducto(int codigoEscuela) throws SQLException{
        String sql = "CALL Activar_Producto(?)";
        CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, codigoEscuela);
        cs.execute();
    }
}
