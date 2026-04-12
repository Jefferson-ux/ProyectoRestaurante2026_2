package logic.dao;
import connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PlatoMenuMethod {
    private final Connection conn;
    public PlatoMenuMethod() {
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
           public ResultSet listarPlatoMenu() throws SQLException{
        String sql = "Select * from vista_plato_menu";/*SQL Query*/
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);  /*Ejecutamos el query y obtenemos el resultado */
    }

       /* SEARCH --> BUSCAR DATOS */
    public ResultSet buscarPlatoMenu(String nombre) throws SQLException{
        String sql = "{CALL buscar_plato_menu(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        return cs.executeQuery();
    }

       /* INSERT--> AGREGAR DATOS */
    public void insertarPlatoMenu(String nombre, String descripcion,
             double precio, int id_categoria) throws SQLException{
        if (existePlatoConNombre(nombre, 0)) {
    throw new IllegalArgumentException("Ya existe un plato con ese nombre en el menú.");
}
        String sql = "{CALL insertar_plato_menu(?,?,?,?)}";//Llamada al procedimiento
        try
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, id_categoria);
            ps.execute();
            System.out.println("Plato del menú insertada con éxito");
        }
    }

       /* UPDATE --> ACTUALIZAR DATOS */
     public void modificarPlatoMenu(int id, String nombre,String descripcion,double precio,
                int id_categoria) throws SQLException{
         if (existePlatoConNombre(nombre, id)) {
    throw new IllegalArgumentException("Ya existe un plato con ese nombre en el menú.");
}
        String sql = "{CALL actualizar_plato_menu(?,?,?,?,?)}";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setDouble(4, precio);
            ps.setInt(5, id_categoria);
            ps.executeUpdate();
            System.out.println("Plato del menú modificada");
        }

    }

       /* DESACTIVATE --> DESACTIVAR DATOS */
     public void desactivarPlatoMenu(int id) throws SQLException{
        String sql = "{CALL desactivar_plato_menu(?)}";/*Llamada al procedimiento*/
        try
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Plato quitado del menú");
        }
    }

    /* Método para verificar si un plato ya existe con un nombre específico */
     public boolean existePlatoConNombre(String nombre, int codigoPlato) throws SQLException {
    String sql = "SELECT 1 FROM Plato_Menu "
               + "WHERE LOWER(nombre_plato) = LOWER(?) "
               + "AND id_plato_menu <> ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre.trim());
                ps.setInt(2, codigoPlato);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }

         //======================================//
        // Métodos de los COMBOBOX - VIEW de FK //
       //======================================//
      //=> COMBOBOX
    public ResultSet comboListarCategorias() throws SQLException{
        String sql = "Select `Nombre de Categoría` from vista_categoria";
        Statement st = conn.createStatement(); /*Creamos la sentencia*/
        return st.executeQuery(sql);               /*Retorna el resultado*/
    }

    //=> ID_CATEGORIA
    public int comboSeleccionarID(String nombre) throws SQLException{
        String sql = "Select `ID` from vista_categoria WHERE `Nombre de Categoría` = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,nombre);
        ResultSet rsAux = ps.executeQuery();
        if (rsAux.next()){
            return rsAux.getInt("ID");
        } else {
            return -1;
        }
    }


}