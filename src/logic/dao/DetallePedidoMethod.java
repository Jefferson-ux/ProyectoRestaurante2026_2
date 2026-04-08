package logic.dao;

import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
}