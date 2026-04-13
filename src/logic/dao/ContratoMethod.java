
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ContratoMethod {
    private final Connection conn;
    
    public ContratoMethod(){
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

    
    
    /******COMBOBO
     * @return 
     * @throws java.sql.SQLException************/
public ResultSet combobox_listarTurnos() throws SQLException {
    String sql = "SELECT Turno FROM vista_turno";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}
    
public int obtenerCodigoTurno(String nombre) throws SQLException {
    String sql = "SELECT ID FROM vista_turno WHERE Turno = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nombre);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getInt("ID");
    }
    return -1;
}
    
    
public ResultSet combobox_listarTipoContrato() throws SQLException {
    String sql = "SELECT `Tipo de Contrato` FROM vista_tipo_contrato";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}
    
public int obtenerCodigoTipoContrato(String nombre) throws SQLException {
    String sql = "SELECT ID FROM vista_tipo_contrato WHERE `Tipo de Contrato` = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nombre);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getInt("ID");
    }
    return -1;
}
    
    
    
public ResultSet combobox_listarCargo() throws SQLException {
    String sql = "SELECT `Nombre de Cargo` FROM vista_cargo WHERE Estado = 1";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}
    
public int obtenerCodigoCargo(String nombre) throws SQLException {
    String sql = "SELECT ID FROM vista_cargo WHERE `Nombre de Cargo` = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nombre);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getInt("ID");
    }
    return -1;
}
    
   
public ResultSet combobox_listarEmpleado() throws SQLException {
    String sql = "SELECT CONCAT(nombre_empleado,' - ',dni_empleado) AS Empleado FROM empleado WHERE estado = 1";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}


public int obtenerCodigoEmpleado(String empleado) throws SQLException {
    String sql = "SELECT id_empleado FROM empleado WHERE CONCAT(nombre_empleado,' - ',dni_empleado) = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, empleado);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getInt("id_empleado");
    }
    return -1;
}










    public ResultSet listarContrato() throws SQLException{
        String sql = "Select * from vista_contrato ";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
    
    public ResultSet listarContratoInactivo() throws SQLException{
        String sql = "Select * from vista_contrato WHERE `Estado` = 0";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }

    
    
    public ResultSet buscarContrato(String nombre) throws SQLException{
        String sql = "{CALL buscar_contrato(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery();
    }
    
    
public void insertarContrato(String descripcion, Date fecha,
        int turno, int empleado, int tipo, int cargo) throws SQLException {

    String sql = "{CALL insertar_contrato(?,?,?,?,?,?)}";
    CallableStatement cs = conn.prepareCall(sql);

    cs.setString(1, descripcion);
    cs.setDate(2, fecha);
    cs.setInt(3, turno);
    cs.setInt(4, empleado);
    cs.setInt(5, tipo);
    cs.setInt(6, cargo);

    cs.execute();
}
     
    
public void modificarContrato(int id, String descripcion, Date fecha,
        int turno, int empleado, int tipo, int cargo, int estado) throws SQLException {

    String sql = "{CALL actualizar_contrato(?,?,?,?,?,?,?,?)}";
    CallableStatement cs = conn.prepareCall(sql);

    cs.setInt(1, id);
    cs.setString(2, descripcion);
    cs.setDate(3, fecha);
    cs.setInt(4, turno);
    cs.setInt(5, empleado);
    cs.setInt(6, tipo);
    cs.setInt(7, cargo);
    cs.setInt(8, estado);

    cs.execute();
}
    
    
    
    
    public String darBajaUsuario(int idContrato) throws SQLException {
    if (idContrato <= 0) {
        return "ID de contrato inválido.";
    }

    String sql = "{CALL cambiar_estado_usuario(?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idContrato);
        
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    }

    return "Error al dar de baja al usuario.";
}
    
   public String reactivarContrato(int idContrato) throws SQLException {
    if (idContrato <= 0) {
        return "ID de contrato inválido.";
    }

    String sql = "{CALL cambiar_estado_usuario(?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idContrato);
        
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    }

    return "Error al reactivar el usuario.";
}   

   
    
 
    
   
 
}
