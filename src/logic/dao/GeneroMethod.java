/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.dao;
import connection.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class GeneroMethod {
    private final Connection conn;
    
    public GeneroMethod(){
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
           public ResultSet listarGenero() throws SQLException{
        String sql = "Select * from vista_genero";   /*SQL Query*/
        Statement st = conn.createStatement();              /*Creamos la sentencia*/
        return st.executeQuery(sql);                        /*Ejecutamos el query y obtenemos el resultado */
    }
}
