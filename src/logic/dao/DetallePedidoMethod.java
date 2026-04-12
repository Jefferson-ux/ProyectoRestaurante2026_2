package logic.dao;

import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DetallePedidoMethod {
private Connection conn;

    // Constructor vacío por si necesitas usarlo de forma aislada
    public DetallePedidoMethod() {
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
    public DetallePedidoMethod(Connection existingConn) {
        this.conn = existingConn;
    }
    
    
    
    public ResultSet listarDetalle() throws SQLException{
        String sql = "Select * from vista_detalle_pedido";/*SQL Query*/
        
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
    

    
    
    // ============================================================
    // 1. MÉTODO INSERT (CREATE)
    // ============================================================
    public boolean registrarDetalles (int idPedido, List<DetallePedido> lista) {
        String sql = "{call insertar_detalle_pedido(?, ?, ?, ?, ?)}";
        try (CallableStatement cs = conn.prepareCall(sql)) {
            for (DetallePedido det : lista) {
                cs.setInt(1, idPedido);
                cs.setInt(2, det.idPlatoMenu); // Acceso directo por ser clase interna
                cs.setInt(3, det.cantidad);
                cs.setDouble(4, det.precioUnitario);
                cs.setString(5, det.observacion);
                cs.addBatch();
            }
            cs.executeBatch();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar detalles: " + e.getMessage());
            return false;
        }
    }

    
    
    
    // ============================================================
    // 2. MÉTODO READ (Usando tu Vista Fina)
    // ============================================================
    public List<DetallePedido> listarDetalles(int idPedido) {
        List<DetallePedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM vista_detalle_pedido WHERE `ID pedido` = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetallePedido d = new DetallePedido();
                d.idDetalle = rs.getInt("ID detalle");
                d.idPlatoMenu = rs.getInt("ID plato");
                d.nombrePlato = rs.getString("Nombre de Platillo");
                d.cantidad = rs.getInt("Cantidad Pedida");
                d.precioUnitario = rs.getDouble("Precio Unitario");
                d.subtotal = rs.getDouble("Subtotal");
                d.observacion = rs.getString("Observaciones");
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer vista: " + e.getMessage());
        }
        return lista;
    }

    
    
    // ============================================================
// 3. MÉTODO DELETE (Para quitar un plato específico)
// ============================================================
public boolean eliminarDetalle(int idDetalle) {
    String sql = "DELETE FROM detalle_pedido WHERE id_detalle = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idDetalle);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al eliminar pericote: " + e.getMessage());
        return false;
    }
}

// ============================================================
// 4. MÉTODO UPDATE (Para cambiar cantidad u observación)
// ============================================================
public boolean actualizarDetalle(int idDetalle, int nuevaCantidad, String nuevaObs) {
    String sql = "UPDATE detalle_pedido SET cantidad = ?, observacion_detalle = ? WHERE id_detalle = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, nuevaCantidad);
        ps.setString(2, nuevaObs);
        ps.setInt(3, idDetalle);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar el detalle: " + e.getMessage());
        return false;
    }
}
    
    
    


// PLATILLO
// 1. Listar el nombre concatenado (ej: "Lomo Saltado - S/ 25.00") para el ComboBox
public ResultSet comboListarPlatillos() throws SQLException {
    // Usamos el nombre de la columna que tengas en tu vista_plato_menu
    // Asumiendo que se llama 'Nombre de Platillo' o similar según tu SQL anterior
    String sql = "SELECT `Nombre del Plato` FROM vista_plato_menu";
    Statement st = conn.createStatement();
    return st.executeQuery(sql);
}

// 2. Obtener el ID real (PK) a partir del texto seleccionado en el Combo
public int obtenerIdPlatilloPorNombre(String nombrePlatillo) throws SQLException {
    // Buscamos el ID original comparando con el alias de la vista
    String sql = "SELECT `ID` FROM vista_plato_menu WHERE `Nombre del Plato` = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, nombrePlatillo);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("ID");
            }
            return -1; // No encontrado
        }
    }
}






    
    
    // ============================================================
    //  (Inner Class / Interfaz de datos)
    // ============================================================
    public static class DetallePedido {
        public int idDetalle;
        public int idPlatoMenu;
        public String nombrePlato;   // De la vista
        public int cantidad;
        public double precioUnitario;
        public double subtotal;      // De la vista
        public String observacion;

        // Constructor vacío para el READ
        public DetallePedido() {}

        // Constructor rápido para el INSERT (cuando agregas al carrito)
        public DetallePedido(int idPlato, int cant, double precio, String obs) {
            this.idPlatoMenu = idPlato;
            this.cantidad = cant;
            this.precioUnitario = precio;
            this.observacion = obs;
        }
    }
    
    public double obtenerPrecioPorPlatoMenu(String plato) throws SQLException {
    // Buscamos en la vista comparando el campo concatenado
    String sql = "SELECT precio_plato FROM plato_menu WHERE nombre_plato = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, plato);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("precio_plato");
            }
            return 0; // No encontrado
        }
    }
}
    
    
    
           public ResultSet listarPedido(int id) throws SQLException{
        String sql = "Select * from vista_pedido where `ID`=?";/*SQL Query*/
        
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                ;
            }
            
        }
    }
        
        
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
    
    
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           

    public ResultSet listarEmpleados() throws SQLException {
        String sql = "SELECT * FROM vista_empleado";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
    
    public ResultSet buscarEmpleados(String texto) throws SQLException {
        String sql = "{CALL buscar_empleado(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, texto);
            ResultSet rs = cs.executeQuery();
            return rs;
    }
    
    public ResultSet listarCliente() throws SQLException {
        String sql = "SELECT * FROM vista_cliente";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
    
    public ResultSet buscarCliente(String texto) throws SQLException {
        String sql = "{CALL buscar_cliente(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, texto);
            ResultSet rs = cs.executeQuery();
            return rs;
    }    
           
           
           
           
           
           
}