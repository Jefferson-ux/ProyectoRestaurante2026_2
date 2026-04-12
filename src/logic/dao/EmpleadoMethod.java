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

    public EmpleadoMethod() {
        //======================================================//
        //      Crear la conexión al iniciar el formulario      //
        //======================================================//
            ConnectionDB connection = new ConnectionDB();


        //======================================================//
        //        Verificar que la conexion sea exitosa         //
        //======================================================//
            this.conn = connection.getConnection();
       
            if (connection.getConnection() == null) {
            JOptionPane.showConfirmDialog(null, "No se puede conectar a la base de datos", "Error de conexión", 1);
        }
    }
    
    public ResultSet combobox_listarGenero() throws SQLException{
        String sql = "Select * from vista_genero";   /*SQL Query*/
        Statement st = conn.createStatement();              /*Creamos la sentencia*/
        return st.executeQuery(sql);                        /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    
    public int obtenerGenero(String nombre) throws SQLException {
        String sql = "SELECT `ID` FROM vista_genero WHERE `Genero` = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        
        if (rsAux.next()){
            return rsAux.getInt("ID");
        }else{
            return -1; //No se encontro
        }
    }    

    public boolean existeEmpleadoConNombre(String nombre, int id_empleado) throws SQLException {
        String sql = "SELECT 1 FROM empleado "
            + "WHERE LOWER(nombre_empleado) = LOWER(?) "
            + "AND dni <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre.trim());
        ps.setInt(2, id_empleado); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
    }
    
   
    
    /** Lista todos los empleados activos desde la vista [vista_empleado]
     * @return 
     * @throws java.sql.SQLException */
        public ResultSet listarEmpleados() throws SQLException{
        String sql = "Select * from vista_empleado WHERE `Estado` = 1";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
        
    /** Busca empleados activos por nombres o apellidos usando el procedimient
     * @param texto
     * @return 
     * @throws java.sql.SQLException*/
        public ResultSet listarEmpleadosDesactivados() throws SQLException{
        String sql = "Select * from vista_empleado WHERE `Estado` = 0";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
    public ResultSet buscarEmpleado(String nombre) throws SQLException{
        String sql = "{CALL buscar_empleado(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    } 

        
    /** Insertar un nuevo empleado usando el procedimiento almacenado
     * @param dni
     * @param nombres
     * @param apellidos
     * @param fechaNacimiento
     * @param fechaRegistro
     * @param residencia
     * @param telefono1
     * @param telefono2
     * @param correo1
     * @param correo2
     * @param observacion
     * @param idGenero
     * @throws java.sql.SQLException */
    public void insertarEmpleado(
        String dni, 
        String nombres, 
        String apellidos, 
        java.sql.Date fechaNacimiento, 
        java.sql.Timestamp fechaRegistro, 
        String residencia, 
        String correo1, 
        String correo2, 
        String telefono1, 
        String telefono2, 
        String observacion, 
        int idGenero
        ) throws SQLException {
    
    // 1. Definimos la llamada al procedimiento (12 parámetros)
    String sql = "{CALL insertar_empleado(?,?,?,?,?,?,?,?,?,?,?,?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        
        // 2. Seteamos los valores
        cs.setString(1, dni);
        cs.setString(2, nombres);
        cs.setString(3, apellidos);
        cs.setDate(4, fechaNacimiento);
        
        // Si fechaRegistro es null, el procedimiento usará CURRENT_TIMESTAMP automáticamente
        cs.setTimestamp(5, fechaRegistro); 
        
        cs.setString(6, residencia);
        cs.setString(7, correo1);
        cs.setString(8, correo2);
        cs.setString(9, telefono1);
        cs.setString(10, telefono2);
        cs.setString(11, observacion);
        cs.setInt(12, idGenero);

        // 3. Ejecutamos
        cs.execute();
        
        // 4. Opcional: Obtener el mensaje de éxito del SELECT del procedimiento
        try (ResultSet rs = cs.getResultSet()) {
            if (rs != null && rs.next()) {
                System.out.println(rs.getString("mensaje"));
            }
        }

    } catch (SQLException e) {
        // Aquí capturamos los SIGNAL SQLSTATE que configuramos en MySQL
        System.err.println("Error al insertar empleado: " + e.getMessage());
        throw e; // Re-lanzamos para que la UI pueda mostrar el error al usuario
    }
}
    
    
    
    
    
    /** Actualiza los datos editables de un empleado usando el procedimiento
     * @param idEmpleado
     * @param dni
     * @param nombres
     * @param apellidos
     * @param direccion
     * @param fechaNacimiento
     * @param fechaRegistro
     * @param telefono1
     * @param residencia
     * @param correo1
     * @param correo2
     * @param telefono2
     * @param idGenero
     * @param observacion
     * @throws java.sql.SQLException */
    public void modificarEmpleado(
    int idEmpleado,
    String dni,
    String nombres,
    String apellidos,
    java.sql.Date fechaNacimiento,
    java.sql.Timestamp fechaRegistro, // Se envía para el cálculo de edad, aunque el UPDATE no la toque
    String residencia,
    String correo1,
    String correo2,
    String telefono1,
    String telefono2,
    String observacion,
    int idGenero
) throws SQLException {

    // El orden del PROCEDURE actualizar_empleado es: 
    // 1.id, 2.dni, 3.nombres, 4.apellidos, 5.f_nac, 6.f_reg, 7.residencia, 
    // 8.c1, 9.c2, 10.t1, 11.t2, 12.obs, 13.id_genero
    String sql = "{CALL actualizar_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    try (CallableStatement cs = conn.prepareCall(sql)) {
        cs.setInt(1, idEmpleado);
        cs.setString(2, dni);
        cs.setString(3, nombres);
        cs.setString(4, apellidos);
        cs.setDate(5, fechaNacimiento);
        
        // Se pasa para la validación de mayoría de edad dentro del procedimiento
        cs.setTimestamp(6, fechaRegistro); 
        
        cs.setString(7, residencia);
        cs.setString(8, correo1);
        cs.setString(9, correo2);
        cs.setString(10, telefono1);
        cs.setString(11, telefono2);
        cs.setString(12, observacion);
        cs.setInt(13, idGenero);

        cs.execute();
        
        // Opcional: Imprimir mensaje de confirmación de la base de datos
        try (ResultSet rs = cs.getResultSet()) {
            if (rs != null && rs.next()) {
                System.out.println(rs.getString("mensaje"));
            }
        }
    } catch (SQLException e) {
        // Captura los SIGNAL SQLSTATE (como el de empleado inactivo o DNI duplicado)
        System.err.println("Error al modificar empleado: " + e.getMessage());
        throw e;
    }
}
    
    
    
    /** Da de baja a un empleado (cambia su estado a 0) usando el procedimiento
     * @param idEmpleado
     * @throws java.sql.SQLException */
    public void reactivarEmpleado(int idEmpleado) throws SQLException {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("El ID del empleado es inválido.");
        }
  
        String sql = "{CALL cambiar_estado_empleado(?, ?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, idEmpleado);  // Código del proveedor
        cs.setInt(2, 1);              // Estado: 1 = activo
        cs.execute();
    }

    public void desactivarEmpleado(int idEmpleado) throws SQLException {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("El ID del empleado es inválido.");
        }
        String sql = "{CALL cambiar_estado_empleado(?, ?)}";        
        CallableStatement cs = conn.prepareCall(sql);
        cs.setInt(1, idEmpleado);  // Código del producto
        cs.setInt(2, 0);              // Estado: 0 = desactivado
        cs.execute();
}
}