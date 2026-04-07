
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EmpleadoMethod {
    private final Connection conn;
    private Statement st;
    private ResultSet rs;

    public EmpleadoMethod() {
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
    
    
    /** Lista los nombres de todas los generos activos (Estado = 1
     * @return 
     * @throws java.sql.SQLException */
    public ResultSet listarGeneros() throws SQLException {
        String sql = "SELECT nombre_genero FROM genero";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }
    
    /** Busca el ID del género basado en su nombre
     * @param nombre
     * @return 
     * @throws java.sql.SQLException */
    public int obtenerIdGenero(String nombre) throws SQLException {
        String sql = "SELECT id_genero FROM genero WHERE nombre_genero = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        if (rsAux.next()){
            return rsAux.getInt("id_genero");
        } else {
            return -1;
        }
    }
    
    /** Lista todos los empleados activos desde la vista [vista_empleado]
     * @return 
     * @throws java.sql.SQLException */
    public ResultSet listarEmpleados() throws SQLException {
        String sql = "SELECT * FROM vista_empleado WHERE estado = 1";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }
    
    /** Busca empleados activos por nombres o apellidos usando el procedimient
     * @param texto
     * @return 
     * @throws java.sql.SQLException*/
    public ResultSet buscarEmpleados(String texto) throws SQLException {
        String sql = "{CALL buscar_empleado(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, texto);
            rs = cs.executeQuery();
            return rs;
    }
    
    /** Insertar un nuevo empleado usando el procedimiento almacenado
     * @param dni
     * @param nombres
     * @param apellidos
     * @param fechaNacimiento
     * @param direccion
     * @param telefono1
     * @param telefono2
     * @param correo
     * @param idGenero
     * @throws java.sql.SQLException */
    public void insertarEmpleado(String dni, String nombres, String apellidos, 
                             String fechaNacimiento, String direccion, 
                             String telefono1, String telefono2, 
                             String correo, int idGenero) throws SQLException {

        // Validación de formato de correo
        if (correo == null || !correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("El formato del correo es inválido.");
        }

        // Validar duplicado de DNI o Correo usando la vista
        String sqlCheck = "SELECT dni_empleado, correo_principal FROM V_Empleado_Genero WHERE dni_empleado = ? OR correo_principal = ?";
        PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
        psCheck.setString(1, dni);
        psCheck.setString(2, correo);
        ResultSet rs = psCheck.executeQuery();

        while (rs.next()) {
            if (dni.equals(rs.getString("dni_empleado"))) {
                throw new IllegalArgumentException("El DNI ya está registrado.");
            }
            if (correo.equalsIgnoreCase(rs.getString("correo_principal"))) {
                throw new IllegalArgumentException("El correo ya está registrado.");
            }
        }

        // Llamada al procedimiento almacenado de la base de datos
        String sqlCall = "{CALL insertar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sqlCall);
    
        cs.setString(1, dni);
        cs.setString(2, nombres);
        cs.setString(3, apellidos);
        cs.setString(4, fechaNacimiento);
        cs.setString(5, "2026-04-06"); // Fecha de registro (puedes usar la actual)
        cs.setString(6, direccion);
        cs.setString(7, correo);
        cs.setNull(8, java.sql.Types.VARCHAR); // Correo secundario opcional
        cs.setString(9, telefono1);
        cs.setString(10, telefono2); // Puede ser null si no hay segundo teléfono
        cs.setInt(11, idGenero);
    
        cs.executeUpdate();
        JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
    }
    
    /** Actualiza los datos editables de un empleado usando el procedimiento 
     * @param idEmpleado Actualiza los datos editables de un empleado usando el procedimiento
     * @param nombres
     * @param correo
     * @param apellidos
     * @param direccion
     * @param fechaNacimiento
     * @param telefono1
     * @param telefono2
     * @param idGenero
     * @throws java.sql.SQLException */
    public void actualizarEmpleado(int idEmpleado, String nombres, String apellidos, 
                               String fechaNacimiento, String direccion, 
                               String correo, String telefono1, String telefono2, 
                               int idGenero) throws SQLException {

        // Validación de formato de correo
        if (correo == null || !correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("El formato del correo es inválido. "
                    + "Debe contener '@' y un dominio válido.");
        }

        // Validar que el nuevo correo no esté ya registrado por otro empleado
        String sqlValidacion = "SELECT id_empleado FROM V_Empleado_Genero WHERE correo_principal = ?";
        PreparedStatement ps = conn.prepareStatement(sqlValidacion);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idExistente = rs.getInt("id_empleado");
            if (idExistente != idEmpleado) {
                throw new IllegalArgumentException("El correo ya está registrado para otro empleado.");
            }
        }

        // Llamada al procedimiento almacenado
        String sqlCall = "{CALL Update_Empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sqlCall);

        cs.setInt(1, idEmpleado);            // p_id_empleado
        cs.setString(2, nombres);            // p_nombre_empleado
        cs.setString(3, apellidos);          // p_apellido_empleado
        cs.setString(4, fechaNacimiento);    // p_fecha_nacimiento
        cs.setString(5, "2026-04-06");       // p_fecha_registro (puedes usar la fecha actual)
        cs.setString(6, direccion);          // p_direccion_empleado
        cs.setString(7, correo);             // p_correo_principal
        cs.setNull(8, java.sql.Types.VARCHAR); // p_correo_secundario (opcional)
        cs.setString(9, telefono1);          // p_telefono_principal
        cs.setString(10, telefono2);         // p_telefono_secundario (opcional)
        cs.setInt(11, idGenero);             // p_id_genero

        cs.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos del empleado actualizados correctamente.");
    }   
    
    /** Da de baja a un empleado (cambia su estado a 0) usando el procedimiento
     * @param idEmpleado
     * @throws java.sql.SQLException */
    public void darDeBajaEmpleado(int idEmpleado) throws SQLException {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("El código del empleado es inválido.");
        }

        String sql = "{CALL cambiar_estado_empleado(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, idEmpleado); // ID del empleado
        cs.setInt(2, 0);          // Estado: 0 = inactivo (baja)
        cs.execute();
        JOptionPane.showMessageDialog(null, "Empleado dado de baja correctamente.");
    }

    /** Reactiva a un emplead
     * @param idEmpleado
     * @throws java.sql.SQLException */
    public void reactivarEmpleado(int idEmpleado) throws SQLException {
        // 1. Validar que el ID sea un número positivo
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("El código del empleado es inválido.");
        }
        String sql = "{CALL cambiar_estado_empleado(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, idEmpleado); // ID del empleado
        cs.setInt(2, 1);          // Estado: 1 = activo (reactivar)
        cs.execute();
        JOptionPane.showMessageDialog(null, "Empleado reactivado correctamente.");
    }
}