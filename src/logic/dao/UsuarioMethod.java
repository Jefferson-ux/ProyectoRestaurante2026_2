package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;





public class UsuarioMethod {

    private final Connection conn;
    
    public UsuarioMethod() {
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
    
    
    
    
         //======================================//  
        // Métodos del Formulario de Mesa - CRUD//
       //======================================//
            
       /* VIEWS --> MOSTRAR DATOS */
           public ResultSet listarUsuarios() throws SQLException{
        String sql = "Select * from vista_usuario";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarUsuarios(String nombre) throws SQLException{
        String sql = "CALL buscar_usuario(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


    
       /* INSERT--> AGREGAR DATOS */
    public void insertarUsuarios(String codigo, String password, String observacion,String id_cargo) throws SQLException{
        String sql = "CALL insertar_usuario(?,?,?,?)";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, codigo);
            ps.setString(2, password);
            ps.setString(3, observacion);
            ps.setString(4, id_cargo);
            ps.execute();
            System.out.println("Mesa insertada con éxito");
        }
    } 


       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarUsuarios(int id, String nuevoNombre) throws SQLException{
        String sql = "CALL vera_ModificarFacultad(?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nuevoNombre);
            ps.executeUpdate();
            System.out.println("Facultad modificada");
        }                   

    }




       /* DESACTIVATE --> DESACTIVAR DATOS */
     public void deleteUsuarios(int id) throws SQLException{
        String sql = "CALL vera_DesactivarFacultad(?)";/*Llamada al procedimiento*/
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Facultad desactivada");
        }   
    }                     
  
       
       
         //======================================//  
        // Métodos de los COMBOBOX - VIEW de FK //
       //======================================//            
      


}