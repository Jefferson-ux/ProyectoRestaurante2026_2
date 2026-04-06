
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
    public ResultSet combobox_ListarGeneros() throws SQLException {
        String sql = "SELECT `Genero` FROM vista_genero";
        Statement st =conn.createStatement(); // Creamos el statements
        ResultSet rs=st.executeQuery(sql); // Ejecutamos la consulta
        return rs; // Devolvemos los resultados 
    }

    public boolean existeEmpleadoGenero (String nombre, int id_empleado) throws SQLException {
        String sql = "SELECT 1 FROM Empleado "
            + "WHERE LOWER(nombre_empleado) = LOWER(?) "
            + "AND id_empleado <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre.trim());
        ps.setInt(2, id_empleado); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
        
    }
    /* VIEWS --> MOSTRAR DATOS */
        public ResultSet listarEmpleados() throws SQLException {
        String sql = "Select * from vista_empleado";/*SQL Query*/
        Statement st = conn.createStatement();
        return st.executeQuery(sql);
    }

       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarEmpleados(String nombre) throws SQLException {
        String sql = "{CALL buscar_empleado(?)}";
        CallableStatement cs = conn.prepareCall(sql);
        cs.setString(1, nombre);
        return cs.executeQuery();
    }

    
       /* INSERT--> AGREGAR DATOS */
    public void insertarEmpleado(String dni, String nombres, String apellidos, String fecha_nacimiento, String fecha_registro, String lugar_residencia, String correo, String telefono, int id_genero) throws SQLException{
        if (existeEmpleadoGenero(nombres, 0)){
            throw new IllegalArgumentException("El nombre del empleado ya esta registrado.");
        }
        String sql = "{CALL insertar_producto(?,?,?,?,?,?,?,?,?)}";//Llamada al procedimiento
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setString(1, dni);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setString(4, fecha_nacimiento);
            ps.setString(5, fecha_registro);
            ps.setString(6, lugar_residencia);
            ps.setString(7, correo);
            ps.setString(8, telefono);
            ps.setInt(9, id_genero);
            ps.execute();
            System.out.println("Empleado insertado");
        }                   
    }    


       /* UPDATE --> ACTUALIZAR DATOS */
    public void modificarEmpleado(int id, String newNombre, String newApellido, String newFechanac, String newFechareg, String newDireccion, String newcorreo1, String newcorreo2, String newtelefono1, String newtelefono2, String newObservacion, int genero, int estado) throws SQLException{
        if (existeEmpleadoGenero(newNombre, id)){
            throw new IllegalArgumentException("El nombre del empleado ya esta registrado.");
        }
        String sql = "{CALL Update_Empleado(?,?,?,?,?,?,?,?,?,?,?,?,?)}";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, newNombre);
            ps.setString(3, newApellido);
            ps.setString(4, newFechanac);
            ps.setString(5, newFechareg);
            ps.setString(6, newFechareg);
            ps.setString(7, newDireccion);
            ps.setString(8, newcorreo1);
            ps.setString(9, newcorreo2);
            ps.setString(10, newtelefono1);
            ps.setString(11, newtelefono2);
            ps.setString(11, newObservacion);
            ps.setInt(11, genero);
            ps.setInt(12, estado);
            ps.executeUpdate();
            System.out.println("Empleado modificado");
        }                   
    }    

    /** * Baja lógica por DNI */
    public void darDeBajaEmpleado(String dni_empleado) throws SQLException {
        String sql = "{CALL Desactivar_Empleado(?)}";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni_empleado);
            pstmt.executeUpdate();
        }
    }
    
}