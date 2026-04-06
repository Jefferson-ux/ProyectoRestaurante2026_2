package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CategoriaMethod {

    private final Connection conn;
    
    public CategoriaMethod() {
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
           public ResultSet listarCategoria() throws SQLException{
        String sql = "Select * from vista_categoria";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarCategoria(String nombre) throws SQLException{
        String sql = "CALL buscar_categoria(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       



       /* INSERT--> AGREGAR DATOS */
    public void insertarCategoria(String nombre) throws SQLException{
        if (existeCategoriaConNombre(nombre, 0)) {
    throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
}
        String sql = "{CALL insertar_categoria(?)}";//Llamada al procedimiento
        try
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.execute();
            System.out.println("Categoría añadida con éxito");
        }
    } 


       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarCategoria(int id, String nuevoNombre) throws SQLException{
         if (existeCategoriaConNombre(nuevoNombre, id)) {
    throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
}
        String sql = "{CALL Update_Categoria(?,?)}";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nuevoNombre);
            ps.executeUpdate();
            System.out.println("Categoría modificada");
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
  
    
public boolean existeCategoriaConNombre(String nombre, int codigoEscuela) throws SQLException {
    // Usamos LOWER para que no importe si escriben en mayúsculas o minúsculas
    String sql = "SELECT 1 FROM categoria "
               + "WHERE LOWER(nombre_categoria) = LOWER(?) "
               + "AND id_categoria <> ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre.trim());
        ps.setInt(2, codigoEscuela); // Pasamos 0 si es un INSERT nuevo
        
        try (ResultSet rs = ps.executeQuery()) {
            // Si rs.next() es true, significa que encontró una fila con ese nombre
            return rs.next(); 
        }
    } catch (SQLException e) {
        System.err.println("Error al validar nombre de la categoría: " + e.getMessage());
        throw e;
    }
}
       
     
     
     
         //======================================//  
        // Métodos de los COMBOBOX - VIEW de FK //
       //======================================//            
      


}