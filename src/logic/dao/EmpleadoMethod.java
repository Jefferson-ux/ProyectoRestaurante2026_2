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
    public java.util.List<String> listarGeneros() throws SQLException {
    java.util.List<String> lista = new java.util.ArrayList<>();
    String sql = "SELECT nombre_genero FROM genero"; 
    
    // El try-with-resources cierra automáticamente el Statement y ResultSet
    try (Statement stLocal = conn.createStatement(); 
         ResultSet rsLocal = stLocal.executeQuery(sql)) {
        
        while (rsLocal.next()) {
            lista.add(rsLocal.getString("nombre_genero"));
        }
    }
    return lista;
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
        String sql = "SELECT * FROM vista_empleado";
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
     * @param correo1
     * @param correo2
     * @param observacion
     * @param idGenero
     * @throws java.sql.SQLException */
    public void insertarEmpleado(String dni, String nombres, String apellidos, 
                             String fechaNacimiento, String direccion, 
                             String telefono1, String telefono2, 
                             String correo1, String correo2, int idGenero, String observacion) throws SQLException {

        // Validación de formato de correo
        if (correo1 == null || !correo1.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("El formato del correo es inválido.");
        }

        // Validar duplicado de DNI o Correo usando la vista
        String sqlCheck = "SELECT dni_empleado, correo_principal FROM empleado WHERE dni_empleado = ? OR correo_principal = ?";
        try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
            psCheck.setString(1, dni);
            psCheck.setString(2, correo1);
            try (ResultSet rs = psCheck.executeQuery()) {
                while (rs.next()) {
                    if (dni.equals(rs.getString("dni_empleado"))) {
                        throw new IllegalArgumentException("El DNI ya está registrado.");
                    }
                    if (correo1.equalsIgnoreCase(rs.getString("correo_principal"))) {
                        throw new IllegalArgumentException("El correo ya está registrado.");
                    }
                }
            }
        }

        // Llamada al procedimiento almacenado de la base de datos
        String sqlCall = "{CALL insertar_empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cs = conn.prepareCall(sqlCall);
    
        cs.setString(1, dni);
        cs.setString(2, nombres);
        cs.setString(3, apellidos);
        cs.setString(4, fechaNacimiento);
        cs.setString(5, direccion);
        cs.setString(6, correo1);
        if (correo2 == null || correo2.trim().isEmpty()) {
            cs.setNull(7, java.sql.Types.VARCHAR);
        } else {
            cs.setString(7, correo2);
        }
        cs.setString(8, telefono1);
        if (telefono2 == null || telefono2.trim().isEmpty()) {
            cs.setNull(9, java.sql.Types.VARCHAR);
        } else {
            cs.setString(9, telefono2);
        }
        cs.setInt(10, idGenero);
        cs.setString(11, observacion);

        cs.executeUpdate();
        JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
    }
    
    /** Actualiza los datos editables de un empleado usando el procedimiento
     * @param dni
     * @param nombres
     * @param apellidos
     * @param direccion
     * @param fechaNacimiento
     * @param telefono1
     * @param correo1
     * @param correo2
     * @param telefono2
     * @param idGenero
     * @param observacion
     * @throws java.sql.SQLException */
    public void actualizarEmpleado(String dni, String nombres, String apellidos, 
                               String fechaNacimiento, String direccion, 
                               String telefono1, String telefono2, 
                               String correo1, String correo2, 
                               int idGenero, String observacion) throws SQLException {

    // 1. Validación de formato de correo
    if (correo1 == null || !correo1.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
        throw new IllegalArgumentException("El formato del correo principal es inválido.");
    }

    // 2. CORRECCIÓN: Validar contra la tabla 'empleado' directamente
    String sqlValidacion = "SELECT dni_empleado FROM empleado WHERE correo_principal = ?";
    try (PreparedStatement ps = conn.prepareStatement(sqlValidacion)) {
        ps.setString(1, correo1);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String dniExistente = rs.getString("dni_empleado");
                // Si el correo es de OTRO empleado (DNI distinto), lanzamos error
                if (!dniExistente.equals(dni)) {
                    throw new IllegalArgumentException("El correo ya está registrado para otro empleado.");
                }
            }
        }
    }

    // 3. CORRECCIÓN: Llamada al procedimiento (11 parámetros sincronizados con tu DB)
    String sqlCall = "{CALL Update_Empleado(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    try (CallableStatement cs = conn.prepareCall(sqlCall)) {

        cs.setString(1, dni);               // p_dni_empleado
        cs.setString(2, nombres);           // p_nombre_empleado
        cs.setString(3, apellidos);         // p_apellido_empleado
        cs.setString(4, fechaNacimiento);   // p_fecha_nacimiento
        cs.setString(5, direccion);         // p_direccion_empleado (Índice correcto)
        cs.setString(6, correo1);           // p_correo_principal
        
        // Manejo de Correo Secundario
        if (correo2 == null || correo2.trim().isEmpty()) {
            cs.setNull(7, java.sql.Types.VARCHAR);
        } else {
            cs.setString(7, correo2);
        }
        
        cs.setString(8, telefono1);         // p_telefono_principal
        
        // Manejo de Teléfono Secundario
        if (telefono2 == null || telefono2.trim().isEmpty()) {
            cs.setNull(9, java.sql.Types.VARCHAR);
        } else {
            cs.setString(9, telefono2);
        }
        
        cs.setInt(10, idGenero);            // p_id_genero
        cs.setString(11, observacion);      // p_observacion_empleado (Índice 11)

        cs.executeUpdate();
        // El JOptionPane es mejor dejarlo en el Frm_Empleado para no mezclar capas
    }
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

    /** Reactiva a un empleado 
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