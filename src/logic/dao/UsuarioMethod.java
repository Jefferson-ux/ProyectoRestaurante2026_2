package logic.dao;
import connection.ConnectionDB;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
            
    public ResultSet combobox_listarCargos() throws SQLException {
    String sql = "SELECT nombre_cargo FROM cargo WHERE estado = 1";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}
    
    
    
    public int obtenerCodigoCargo(String nombreCargo) throws SQLException {
    if (nombreCargo == null || nombreCargo.trim().isEmpty()) {
        return -1;
    }

    String sql = "SELECT id_cargo FROM cargo WHERE UPPER(nombre_cargo) = UPPER(TRIM(?))";
    
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nombreCargo.trim());
    
    ResultSet rsAux = ps.executeQuery();

    if (rsAux.next()) {
        return rsAux.getInt("id_cargo");
    } else {
        return -1;
    }
}
    
    
       /* VIEWS --> MOSTRAR DATOS */
    public ResultSet listarUsuario() throws SQLException{
        String sql = "Select * from vista_usuario";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
       
       
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarUsuario(String nombre) throws SQLException{
        String sql = "CALL buscar_usuario(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


    
       /* INSERT--> AGREGAR DATOS */
    public String registrarUsuario(String codigo, String passwordEncriptado, int id_cargo) throws SQLException {

    if (codigo == null || codigo.trim().isEmpty()) {
        return "El codigo del usuario no puede estar vacio.";
    }

    if (passwordEncriptado == null || passwordEncriptado.trim().isEmpty()) {
        return "La contraseña no puede estar vacia.";
    }

    if (id_cargo <= 0) {
        return "Debe seleccionar un cargo válido.";
    }

    String sql = "CALL insertar_usuario(?,?,?)";

    try (CallableStatement cs = conn.prepareCall(sql)) {

        cs.setString(1, codigo.trim());
        cs.setString(2, passwordEncriptado.trim());
        cs.setInt(3, id_cargo); 

        ResultSet rs = cs.executeQuery(); // ✔ SOLO UNA VEZ

        if (rs.next()) {
            return rs.getString("mensaje");
        }
    }
    
    return "Error desconocido al registrar usuario.";
}
    
    public String darBajaUsuario(int idUsuario) throws SQLException {
    if (idUsuario <= 0) {
        return "ID de usuario inválido.";
    }

    String sql = "{CALL cambiar_estado_usuario(?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idUsuario);
        
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    }

    return "Error al dar de baja al usuario.";
}
    
   public String reactivarUsuario(int idUsuario) throws SQLException {
    if (idUsuario <= 0) {
        return "ID de usuario inválido.";
    }

    String sql = "{CALL PROC_ReactivarUsuario(?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idUsuario);
        
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    }

    return "Error al reactivar el usuario.";
}     
    
    public String resetearPassword(String codigo) throws SQLException {
    if (codigo == null || codigo.trim().isEmpty()) {
        return "Debe proporcionar un código de usuario válido.";
    }

    String sql = "{CALL PROC_ResetearPassword(?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, codigo);
        
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    }

    return "Error al resetear la contraseña.";
}
    
    public boolean validarLogin(String codigo, String passwordEncriptada, String nombreCargo) throws SQLException {
    String sql = "{CALL PROC_ValidarLogin(?, ?, ?)}";
    
    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, codigo);
        cs.setString(2, passwordEncriptada);
        cs.setString(3, nombreCargo);

        try (ResultSet rs = cs.executeQuery()) {
            // Si hay al menos un resultado, login válido
            return rs.next();
        }
    }
}
    /**
 * Encripta una cadena de texto usando el algoritmo SHA-256.
 * @param password El texto plano a encriptar.
 * @return El hash en formato hexadecimal (String).
 */
    public static String encriptarSHA256(String password) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
        
    } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
        return null;
    }
}
  

}