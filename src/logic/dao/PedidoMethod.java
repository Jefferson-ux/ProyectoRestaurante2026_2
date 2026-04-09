package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

public class PedidoMethod {
private Connection conn;

    // Constructor vacío por si necesitas usarlo de forma aislada
    public PedidoMethod() {
        ConnectionDB db = new ConnectionDB();
        this.conn = db.getConnection();
        
        // Verificamos una sola vez
        if (this.conn == null) {
            JOptionPane.showMessageDialog(null, 
                "Error crítico: No se pudo establecer conexión con el servidor.", 
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Constructor para inyectar la conexión (INDISPENSABLE PARA TRANSACCIONES)
    public PedidoMethod(Connection existingConn) {
        this.conn = existingConn;
    }

          //======================================//
        // Métodos del Formulario de Pedido - CRUD//
       //======================================//

       /* VIEWS --> MOSTRAR DATOS */
           public ResultSet listarPedido() throws SQLException{
        String sql = "Select * from vista_pedido";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
           public ResultSet listar() throws SQLException{
        String sql = "Select * from vista_pedido";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
           
           

       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarPedido(String nombre) throws SQLException{
        String sql = "{CALL buscar_pedido(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery();
    }
    
    
    
    /*IN p_fecha TIMESTAMP,
    IN p_id_cliente INT,
    IN p_id_empleado INT,
    IN p_id_tipo_pedido INT */
public int insertarPedido(String fecha,int id_cliente, int id_empleado, int id_tipo_pedido)throws Exception {
    
    String sql = "{CALL insertar_pedido(?,?,?,?)}";
    String sqlInsert = "INSERT INTO pedido (fecha_pedido, id_cliente, id_empleado, id_tipo_pedido, estado) VALUES (?, ?, ?, ?, 1)";
    int idGenerado = -1;
    
    // Formato estándar que MySQL entiende (yyyy-MM-dd HH:mm:ss)
    SimpleDateFormat formatoDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    try (PreparedStatement ps = conn.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS)) {
        

        java.util.Date parsedFecha = formatoDateTime.parse(fecha);
        java.sql.Timestamp sqlFecha = new java.sql.Timestamp(parsedFecha.getTime());

        // 2. Mapeo al PreparedStatement
        ps.setTimestamp(1, sqlFecha );
        ps.setInt(2, id_cliente);
        ps.setInt(3, id_empleado);
        ps.setInt(4, id_tipo_pedido);
        
        ps.execute();
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        }
        

        
    } catch (Exception e) {
        System.err.println("Error de formato o SQL: " + e.getMessage());
        throw e;
    }         
    return idGenerado;
   
    
}








       /* UPDATE --> ACTUALIZAR DATOS */
public void modificarPedido(int id_pedido,String fecha,int id_cliente, int id_empleado, int id_tipo_pedido) throws SQLException {
    
    // 8 parámetros: el ID de la pedido + los 7 campos de datos
    String sql = "{CALL Update_Pedido(?,?,?,?,?)}";

    try (PreparedStatement ps = conn.prepareCall(sql)) {
        
        
        ps.setInt(1, id_pedido);
        ps.setTimestamp(2, java.sql.Timestamp.valueOf(fecha));
        ps.setInt(3,id_cliente);
        ps.setInt(4, id_empleado);
        ps.setInt(5, id_tipo_pedido);

        ps.executeUpdate();
        System.out.println("Pedido modificado con éxito");
        
    } catch (IllegalArgumentException e) {
        // Por si los números no son válidos
        throw new SQLException("Error en formato de números: " + e.getMessage());
    }
}







       /* DESACTIVATE --> DESACTIVAR DATOS */
     public void desactivarPedido(int id) throws SQLException{
        String sql = "{CALL Desactivar_Pedido(?)}";/*Llamada al procedimiento*/
        try
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Pedido quitado exitosamente");
        }
    }




         //======================================//
        // Métodos de los COMBOBOX - VIEW de FK //
       //======================================//
// 
// Listar Números de Pedido para el ComboBox
public ResultSet comboListarTipoPedido() throws SQLException {
    String sql = "SELECT `Tipo de Pedido` FROM vista_tipo_pedido";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}

// Obtener el ID real (PK) a partir del Número de Pedido
public int obtenerIdTipoPedidoPorNumero(String nombre) throws SQLException {
    String sql = "SELECT id_tipo_pedido FROM tipo_pedido WHERE nombre_tipo_pedido = ? AND estado = 1";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_tipo_pedido");
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








public boolean registrarVentaCompleta(String fecha, int idCliente, int idEmpleado, int idTipo, List<DetallePedidoMethod.DetallePedido> listaDetalles) {
    try {
        // 1. Iniciamos la transacción: "O se guarda todo, o no se guarda nada"
        conn.setAutoCommit(false);

        // 2. Insertamos la cabecera y recuperamos el ID que acabas de programar
        int idGenerado = this.insertarPedido(fecha, idCliente, idEmpleado, idTipo);

        if (idGenerado != -1) {
            // 3. Le pasamos el ID y la lista de platos al DAO de detalles
            // Importante: Le pasamos la MISMA conexión (this.conn) para que sea la misma transacción
            DetallePedidoMethod detalleDAO = new DetallePedidoMethod(this.conn);
            boolean detallesOK = detalleDAO.registrarDetalles(idGenerado, listaDetalles);

            if (detallesOK) {
                conn.commit(); // ¡ÉXITO! Se guarda el pedido y sus platos
                System.out.println("Venta completa registrada con éxito.");
                return true;
            }
        }

        // Si llegó aquí es porque algo falló (id -1 o error en detalles)
        conn.rollback();
        return false;

    } catch (Exception e) {
        try { if(conn != null) conn.rollback(); } catch (SQLException ex) {}
        System.err.println("Error crítico en la venta: " + e.getMessage());
        return false;
    } finally {
        try { if(conn != null) conn.setAutoCommit(true); } catch (SQLException e) {}
    }
}




}