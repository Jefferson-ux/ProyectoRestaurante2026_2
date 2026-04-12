
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
    
    public boolean existeProveedorConNombre(String nombre, int id_proveedor) throws SQLException {
        String sql = "SELECT 1 FROM Proveedor "
            + "WHERE LOWER(ruc_proveedor) = LOWER(?) "
            + "AND id_producto <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre.trim());
        ps.setInt(2, id_proveedor); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
    }
    
    /* VIEWS --> MOSTRAR DATOS */
    public ResultSet listarProveedor() throws SQLException{
        String sql = "Select * from vista_proveedor WHERE `Estado` = 1";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
    public ResultSet listarProveedorInactivo() throws SQLException{
        String sql = "Select * from vista_proveedor WHERE `Estado` = 0";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarProovedor(String nombre) throws SQLException{
        String sql = "CALL buscar_proveedor(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       
    
    /* INSERT--> AGREGAR DATOS */
    public void insertarProveedor(String ruc, String razon_social, String telefono, String correo, String direccion, String observacion) throws SQLException{
        if(existeProveedorConNombre(razon_social, 0)){
            throw  new IllegalArgumentException("Ya exsiste un proveedor con ese nombre.");
        }
        String sql = "CALL insertar_proveedor(?,?,?,?,?,?)";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, ruc);
            ps.setString(2, razon_social);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, direccion);
            ps.setString(6, observacion);
            
            ps.execute();
            System.out.println("Proveedor insertada con éxito");
        }
    } 


       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarProveedor(int id,String ruc, String nuevaRazonSocial, String nuevoTelefono, String nuevoCorreo, String nuevaDireccion, String nuevaObservacion) throws SQLException{
        String sql = "CALL actulizar_proveedor(?,?,?,?,?,?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1, id);
            ps.setString(2, ruc);
            ps.setString(3, nuevaRazonSocial);
            ps.setString(4, nuevoTelefono);
            ps.setString(5, nuevoCorreo);
            ps.setString(6, nuevaDireccion);
            ps.setString(7, nuevaObservacion);
            ps.executeUpdate();
            System.out.println("Proveedor modificada");
        }                   

    }
     
     public void reactivarProveedor(int codigoProveedor) throws SQLException {
        if (codigoProveedor <= 0) {
            throw new IllegalArgumentException("El código del proveedor es inválido.");
        }
        String sql = "{CALL cambiar_estado_proveedor(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, codigoProveedor);  // Código del proveedor
        cs.setInt(2, 1);              // Estado: 1 = activo
        cs.execute();
    }
     
     public void desactivarProveedor(int codigoProveedor) throws SQLException {
        if (codigoProveedor <= 0) {
            throw new IllegalArgumentException("El código del proveedor es inválido.");
        }
        String sql = "{CALL cambiar_estado_proveedor(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, codigoProveedor);  // Código del proveedor
        cs.setInt(2, 0);              // Estado: 0 = desactivado
        cs.execute();
    }
}
