
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
        ConnectionDB connection = new ConnectionDB();
 
        this.conn = connection.getConnection();
       
         if (connection.getConnection() == null) {
            JOptionPane.showConfirmDialog(null, "No se puede conectar a la base de datos", "Error de conexión", 1);
        }
    }
    
    /* VIEWS --> MOSTRAR DATOS */
           public ResultSet listarProductos() throws SQLException{
        String sql = "Select * from vista_plato_menu";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarPlatoMenu(String nombre) throws SQLException{
        String sql = "{CALL buscar_plato_menu(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


       /* INSERT--> AGREGAR DATOS */
    public void insertarPlatoMenu(String nombre, String descripcion, double precio, int id_categoria) throws SQLException{
        String sql = "{CALL insertar_plato_menu(?,?,?,?)}";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, id_categoria);
            ps.execute();
            System.out.println("Plato del menú insertada con éxito");
        }
    } 


       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarPlatoMenu(int id, String nuevoNombre) throws SQLException{
        String sql = "CALL vera_ModificarFacultad(?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nuevoNombre);
            ps.executeUpdate();
            System.out.println("Facultad modificada");
        }                   

    }




       /* DESACTIVATE --> DESACTIVAR DATOS */
     public void downFacultades(int id) throws SQLException{
        String sql = "CALL vera_DesactivarFacultad(?)";/*Llamada al procedimiento*/
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Facultad desactivada");
        }   
    }
}
