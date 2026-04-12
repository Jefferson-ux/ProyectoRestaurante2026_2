
package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class FacturaMethod {
    private final Connection conn;
    
    public FacturaMethod(){
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
    
    //Cliente
    public ResultSet combobox_listarCliente() throws SQLException{
        String sql = "Select * from vista_cliente";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    public int obtenerCodigoUnidad(String nombre) throws SQLException {
        String sql = "SELECT `ID` FROM vista_cliente WHERE `Nombre del Cliente` = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        
        if (rsAux.next()){
            return rsAux.getInt("ID");
        }else{
            return -1; //No se encontro
        }
    }
   
    //Empleado
    public ResultSet combobox_listarEmpleado() throws SQLException{
        String sql = "Select * from vista_empleado";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    public int obtenerCodigoEmpleado(String nombre) throws SQLException {
        String sql = "SELECT `ID` FROM vista_empleado WHERE `Nombre de Empleado` = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        
        if (rsAux.next()){
            return rsAux.getInt("ID");
        }else{
            return -1; //No se encontro
        }
    }
    
    //Metodo de pago
    public ResultSet combobox_listartipoPago() throws SQLException{
        String sql = "Select * from tipo_pago";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    } 
    
    public int obtenerCodigoTipoPago(String nombre) throws SQLException {
        String sql = "SELECT  FROM tipo_pago WHERE nombre_tipo_pago = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rsAux = ps.executeQuery();
        
        if (rsAux.next()){
            return rsAux.getInt("tipo_pago");
        }else{
            return -1; //No se encontro
        }
    }
    
    public boolean existeFactura(String numeroComprobante, int id_factura) throws SQLException {
        String sql = "SELECT 1 FROM Factura "
            + "WHERE LOWER(numero_comprobante) = LOWER(?) "
            + "AND id_factura <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, numeroComprobante.trim());
        ps.setInt(2, id_factura); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
    }
    
    
    /* VIEWS --> MOSTRAR DATOS */
        public ResultSet listarFacturas() throws SQLException{
        String sql = "Select * from vista_factura";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
        
    /* VIEWS --> MOSTRAR DATOS */
        public ResultSet listarPedidos() throws SQLException{
        String sql = "Select * from vista_pedido";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }
          
    
       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarFactura(String nombre) throws SQLException{
        String sql = "{CALL buscar_factura(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery(); 
    }       


       /* INSERT--> AGREGAR DATOS */
    public void insertarFactura(String numComprobante, java.util.Date fecha, double total, int id_pedido, int tipo_pago) throws SQLException {
        // 1. Ya no necesitas validar 'existeFactura' aquí, el SP lo hace con SIGNAL

        String sql = "{CALL insertar_factura(?,?,?,?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) { // Usamos CallableStatement
            cs.setString(1, numComprobante);

            // 2. Conversión segura de fecha
            if (fecha != null) {
                cs.setDate(2, new java.sql.Date(fecha.getTime()));
            } else {
                cs.setDate(2, null); // El SP usará CURDATE() según tu lógica
            }

            cs.setDouble(3, total);
            cs.setInt(4, id_pedido);
            cs.setInt(5, tipo_pago);

            // 3. Ejecución
            cs.execute();
            System.out.println("Factura insertada con éxito");
        }
        // Las excepciones SIGNAL del SP caen directamente al catch que llame a este método
    }


    /* UPDATE --> ACTUALIZAR DATOS */
    public void modificarFactura(int id, String numComprobante, java.sql.Date fecha, double total, int id_pedido, int id_tipo_pago) throws SQLException {
        String sql = "{CALL Update_Factura(?,?,?,?,?,?)}";

        try (CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.setString(2, numComprobante);
            cs.setDate(3, fecha);
            cs.setDouble(4, total);
            cs.setInt(5, id_pedido);
            cs.setInt(6, id_tipo_pago);

            cs.execute();
            // Si llega aquí, es que pasó todas las validaciones del SP
        }
    }
    
    public static class ComboItem {
        private int id;
        private String display;

        public ComboItem(int id, String display) {
            this.id = id;
            this.display = display;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return display;
        }
    }
    
}
