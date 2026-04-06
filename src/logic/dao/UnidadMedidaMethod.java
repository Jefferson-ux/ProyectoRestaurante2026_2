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


public class UnidadMedidaMethod {
    private final Connection conn;
    
    public UnidadMedidaMethod(){
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
    public int obtenerCodigoUnidad(String nombre) throws SQLException {
        String sql = "SELECT `ID` FROM vista_unidad_medida WHERE `Unidad de Medida` = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        
        if (rsAux.next()){
            return rsAux.getInt("ID");
        }else{
            return -1; //No se encontro
        }
    }
    
    /* VIEWS --> MOSTRAR DATOS */
           public ResultSet listarUnidadMedida() throws SQLException{
        String sql = "Select * from vista_unidad_medida";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
}
