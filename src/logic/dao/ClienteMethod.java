package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteMethod {
    private final Connection conn;
    
    public ClienteMethod() {
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
    
    public boolean existeClienteConDni(String dni, int id_cliente) throws SQLException {
    String sql = "SELECT 1 FROM cliente "
               + "WHERE dni_cliente = ? "
               + "AND id_cliente <> ?";
    
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, dni.trim());
    ps.setInt(2, id_cliente); // 0 si es insertar
    
    ResultSet rs = ps.executeQuery();
    return rs.next(); // true = ya existe
    }
    
    public ResultSet listarCliente() throws SQLException {
    String sql = "SELECT * FROM vista_cliente WHERE `Estado` = 1"; // solo activos
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
    }
    
    public ResultSet listarClienteInactivo() throws SQLException {
    String sql = "SELECT * FROM vista_cliente WHERE `Estado` = 0";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
    }
    
    public ResultSet buscarCliente(String parametro) throws SQLException {
    String sql = "CALL buscar_cliente(?)"; // tu procedure
    
    CallableStatement cs = conn.prepareCall(sql);
    cs.setString(1, parametro.trim()); // importante limpiar espacios
    
    return cs.executeQuery();
    }
    
    public void insertarCliente(String dni, String nombre, String apellido, 
                            String correo, String telefono, String observacion) throws SQLException {
    
    // 🔴 VALIDACIÓN PREVIA (como hiciste con proveedor)
    if (existeClienteConDni(dni, 0)) {
        throw new IllegalArgumentException("Ya existe un cliente con ese DNI.");
    }

    String sql = "CALL insertar_cliente(?,?,?,?,?,?)"; // tu procedure

    try (CallableStatement cs = conn.prepareCall(sql)) {

        cs.setString(1, dni.trim());
        cs.setString(2, nombre.trim());
        cs.setString(3, apellido.trim());
        cs.setString(4, correo != null ? correo.trim() : null);
        cs.setString(5, telefono != null ? telefono.trim() : null);
        cs.setString(6, observacion != null ? observacion.trim() : null);

        cs.execute();

        System.out.println("Cliente insertado con éxito");
    }
}
    
    public void modificarCliente(int id, String dni, String nombre, String apellido,
                             String correo, String telefono, String observacion) throws SQLException {

    String sql = "CALL actualizar_cliente(?,?,?,?,?,?,?)"; // tu procedure

    try (CallableStatement cs = conn.prepareCall(sql)) {

        cs.setInt(1, id);
        cs.setString(2, dni.trim());
        cs.setString(3, nombre.trim());
        cs.setString(4, apellido.trim());
        cs.setString(5, correo != null ? correo.trim() : null);
        cs.setString(6, telefono != null ? telefono.trim() : null);
        cs.setString(7, observacion != null ? observacion.trim() : null);

        cs.execute();

        System.out.println("Cliente modificado correctamente");
    }
}
    public void reactivarCliente(int idCliente) throws SQLException {
    if (idCliente <= 0) {
        throw new IllegalArgumentException("El ID del cliente es inválido.");
    }

    String sql = "{CALL cambiar_estado_cliente(?, ?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idCliente); // ID del cliente
        cs.setInt(2, 1);         // Estado: 1 = activo

        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            System.out.println(rs.getString("mensaje"));
        }
    }
    }
    
    public void desactivarCliente(int idCliente) throws SQLException {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("El código del cliente es inválido.");
        }
        String sql = "{CALL cambiar_estado_cliente(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, idCliente);  // Código del proveedor
        cs.setInt(2, 0);              // Estado: 0 = desactivado
        cs.execute();
    }
}
    
 
