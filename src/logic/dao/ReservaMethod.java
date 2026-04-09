package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ReservaMethod {
    private final Connection conn;
    public ReservaMethod() {
         //===========================================//
        //Crear la conexión al iniciar el formulario //
       //===========================================//
           ConnectionDB connection = new ConnectionDB();

         //=====================================//
        //Verificar que la conexion sea exitosa//
       //=====================================//
            this.conn = connection.getConnection();

            if (connection.getConnection() == null) {
            JOptionPane.showConfirmDialog(null, "No se puede conectar a la base de datos",
             "Error de conexión", 1);
        }
    }

          //======================================//
        // Métodos del Formulario de Mesa - CRUD//
       //======================================//

       /* VIEWS --> MOSTRAR DATOS */
           public ResultSet listarReserva() throws SQLException{
        String sql = "Select * from vista_reserva";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }

       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarReserva(String nombre) throws SQLException{
        String sql = "{CALL buscar_reserva(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery();
    }

public void insertarReserva(String fechaRegStr, String fechaInicioStr, String fechaFinStr, 
                            String cantPersonas, String observacion, int idCliente, int idMesa) 
                            throws Exception {
    if (mesaOcupada(idMesa, fechaInicioStr, fechaFinStr, 0)) {
        throw new IllegalArgumentException("La mesa ya está reservada en ese horario.");
    }
    
    
    String sql = "{CALL insertar_reserva(?,?,?,?,?,?,?)}";
    
    // Formato estándar que MySQL entiende (yyyy-MM-dd HH:mm:ss)
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatoDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    try (PreparedStatement ps = conn.prepareCall(sql)) {
        
        // 1. Conversión de Strings a tipos SQL
        java.util.Date parsedReg = formatoFecha.parse(fechaRegStr);
        java.sql.Date sqlReg = new java.sql.Date(parsedReg.getTime());

        java.util.Date parsedInicio = formatoDateTime.parse(fechaInicioStr);
        java.sql.Timestamp sqlInicio = new java.sql.Timestamp(parsedInicio.getTime());

        java.util.Date parsedFin = formatoDateTime.parse(fechaFinStr);
        java.sql.Timestamp sqlFin = new java.sql.Timestamp(parsedFin.getTime());

        // 2. Mapeo al PreparedStatement
        ps.setDate(1, sqlReg);
        ps.setTimestamp(2, sqlInicio);
        ps.setTimestamp(3, sqlFin);
        ps.setInt(4, Integer.parseInt(cantPersonas));
        ps.setString(5, observacion);
        ps.setInt(6, idCliente);
        ps.setInt(7, idMesa);
        
        ps.execute();
        System.out.println("Reserva insertada con éxito");
        
    } catch (Exception e) {
        System.err.println("Error de formato o SQL: " + e.getMessage());
        throw e;
    }
}








       /* UPDATE --> ACTUALIZAR DATOS */
public void modificarReserva(int idReserva, String fReg, String fIni, String fFin, 
                             String cant, String obs, String idCliente, String idMesa) throws SQLException {
    if (mesaOcupada(Integer.parseInt(idMesa), fIni, fFin, idReserva)) {
        throw new IllegalArgumentException("La mesa ya está reservada en ese horario.");
    }
    
    // 8 parámetros: el ID de la reserva + los 7 campos de datos
    String sql = "{CALL Update_Reserva(?,?,?,?,?,?,?,?)}";

    try (PreparedStatement ps = conn.prepareCall(sql)) {
        // 1. El ID de la reserva (normalmente es el primer parámetro en un UPDATE)
        ps.setInt(1, idReserva);

        // 2. Conversión de fechas usando valueOf (requiere formato yyyy-MM-dd)
        ps.setDate(2, java.sql.Date.valueOf(fReg));
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(fIni));
        ps.setTimestamp(4, java.sql.Timestamp.valueOf(fFin));

        // 3. Los demás datos
        ps.setInt(5, Integer.parseInt(cant));
        ps.setString(6, obs);
        ps.setInt(7, Integer.parseInt(idCliente));
        ps.setInt(8, Integer.parseInt(idMesa));

        ps.executeUpdate();
        System.out.println("Reserva ID " + idReserva + " modificada con éxito");
        
    } catch (IllegalArgumentException e) {
        // Por si los números no son válidos
        throw new SQLException("Error en formato de números: " + e.getMessage());
    }
}







       /* DESACTIVATE --> DESACTIVAR DATOS */
     public void desactivarReserva(int id) throws SQLException{
        String sql = "{CALL Desactivar_Reserva(?)}";/*Llamada al procedimiento*/
        try
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Reserva desactivada del menú");
        }
    }

     
     
     
     
     
     
     
     
    /* Método para verificar si una mesa ya está ocupada*/
public boolean mesaOcupada(int idMesa, String fechaInicio, String fechaFin, int idReservaActual) throws SQLException {
    // Buscamos si existe OTRA reserva para la misma mesa donde los horarios se traslapen
    String sql = "SELECT 1 FROM reserva "
               + "WHERE id_mesa = ? "
               + "AND id_reserva <> ? " // Excluimos la reserva actual si estamos editando
               + "AND estado = 1 "      // Solo contamos reservas activas
               + "AND (? < fecha_fin AND ? > fecha_inicio)"; 

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idMesa);
        ps.setInt(2, idReservaActual); // Pasar 0 si es una reserva nueva
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(fechaInicio));
        ps.setTimestamp(4, java.sql.Timestamp.valueOf(fechaFin));

        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // Retorna true si hay conflicto (mesa ocupada)
        }
    }
}

// A veces querrás evitar que un mismo cliente reserve dos mesas distintas para la misma hora.
public boolean clienteConCruce(int idCliente, String fechaInicio, int idReservaActual) throws SQLException {
    String sql = "SELECT 1 FROM reserva "
               + "WHERE id_cliente = ? "
               + "AND id_reserva <> ? "
               + "AND estado = 1 "
               + "AND ? BETWEEN fecha_inicio AND fecha_fin";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idCliente);
        ps.setInt(2, idReservaActual);
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(fechaInicio));

        try (ResultSet rs = ps.executeQuery()) {
            return rs.next();
        }
    }
}



















         //======================================//
        // Métodos de los COMBOBOX - VIEW de FK //
       //======================================//
// MESA
// Listar Números de Mesa para el ComboBox
public ResultSet comboListarMesas() throws SQLException {
    String sql = "SELECT `Número de Mesa` FROM vista_mesa";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}

// Obtener el ID real (PK) a partir del Número de Mesa
public int obtenerIdMesaPorNumero(String numeroMesa) throws SQLException {
    String sql = "SELECT id_mesa FROM mesa WHERE numero_mesa = ? AND estado = 1";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, numeroMesa);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_mesa");
            }
            return -1;
        }
    }
}











// CLIENTE
// 1. Listar el concatenado para el ComboBox
public ResultSet comboListarClientes() throws SQLException {
    String sql = "SELECT `Info_Cliente` FROM vista_cliente";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}

// 2. Obtener el ID real (PK) a partir del texto seleccionado en el Combo
public int obtenerIdClientePorConcatenado(String infoCliente) throws SQLException {
    // Buscamos en la vista comparando el campo concatenado
    String sql = "SELECT id_cliente FROM vista_cliente WHERE `Info_Cliente` = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, infoCliente);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_cliente");
            }
            return -1; // No encontrado
        }
    }
}

















}