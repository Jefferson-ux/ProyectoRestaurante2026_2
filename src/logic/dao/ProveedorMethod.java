
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProveedorMethod {
    private final Connection conn;
    
    public ProveedorMethod(){
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
    
    /* VIEWS --> MOSTRAR DATOS */
    public ResultSet listarProveedor() throws SQLException{
        String sql = "Select * from vista_Proveedor";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarProovedor(String nombre) throws SQLException{
        String sql = "CALL buscar_Proveedor(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       
    
    /* INSERT--> AGREGAR DATOS */
    public void insertarProveedor(String ruc, String razon_social, String telefono, String correo, String direccion) throws SQLException{
        String sql = "CALL insertar_proveedor(?,?,?,?,?)";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, ruc);
            ps.setString(2, razon_social);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, direccion);
            
            ps.execute();
            System.out.println("Proveedor insertada con éxito");
        }
    } 


       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarProveedor(String ruc, String nuevaRazonSocial, String nuevoTelefono, String nuevoCorreo, String nuevaDireccion) throws SQLException{
        String sql = "CALL Update_Proveedor(?,?,?,?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setString(1, ruc);
            ps.setString(2, nuevaRazonSocial);
            ps.setString(3, nuevoTelefono);
            ps.setString(4, nuevoCorreo);
            ps.setString(5, nuevaDireccion);
            ps.executeUpdate();
            System.out.println("Proveedor modificada");
        }                   

    }
}
