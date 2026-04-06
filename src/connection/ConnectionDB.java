
package connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
        
     //====================================//
    /* --> Variables de configuración <-- */
   //====================================//
    private Connection conn;//1.--> Conexión a MySQL
    private Statement st;  //2.--> Ejecución del SQL
    private ResultSet rs; //3.--> Resultados del sql
    
    
     //=====================================//
    /* --> Config |> Datos de conexión <-- */
   //=====================================//
    
    private static final String URL = "jdbc:mysql://localhost:3306/db_restaurant";
            //=> jdbc (protocolo JDBC) localhost (mi PC) 3306 (port) universidad_db (database)
    private static final String USER = "root";  
            //=> USUARIO 
    private static final String PASSWORD = "";
            //=> PASSWORD 
    
    
     //================================================//
    /* --> Contrsuctor que inicializa la conexión <-- */
   //================================================//
    public ConnectionDB() {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa con la BD 'universidad'.");
        ////////////////////////////////////////////////////////////////////////
            
        } catch (ClassNotFoundException e) { //=> Por si no existe el Driver
            System.err.println("Error, no se encontró el driver JBDC");
        } catch (SQLException e) { //Por si falla la conexión
            System.err.println("Error al conectar con la base de datos: "+e.getMessage());
        }
    }
    
   
      
     //==============================================================//
    /* --> Métodos para obtener los valores de la base de datos <-- */
   //==============================================================//    

    //===> ESCUELAS PROFESIONALES
    public ResultSet listEscuelasProfesionales() throws SQLException{
        String sql = "SELECT * FROM view_escuelasprofesionales";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    public ResultSet listDocentes() throws SQLException{
        String sql = "SELECT * FROM view_docente";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    public ResultSet listUsuarios() throws SQLException{
        String sql = "SELECT * FROM view_usuario";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }
    public ResultSet listCursos () throws SQLException{
        String sql = "SELECT * FROM view_curso";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }    
    public ResultSet listPlanEstudios () throws SQLException{
        String sql = "SELECT * FROM view_planEstudios";
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }    


    
    //===> MANTENIMIENTO TABLA FACULTAD
        //=> LISTAR
    public ResultSet listFacultades() throws SQLException{
        String sql = "Select * from v_mostrarfacultades";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    //=> BUSCAR
    public ResultSet buscarFacultades(String nombre) throws SQLException{
        String sql = "CALL vera_BuscarFacultades(?)";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        rs = cs.executeQuery();  /*Ejecutamos y obtenemos los resultados*/
        return rs;                 
    }
    //=> INSERTAR
    public void insertFacultades(String nombre) throws SQLException{
        String sql = "CALL vera_InsertarFacultad(?)";//Llamada al procedimiento
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.execute();
            System.out.println("Facultad insertada");
        }
    }     
        
     //=> MODIFICAR
     public void modifFacultades(int id, String nuevoNombre) throws SQLException{
        String sql = "CALL vera_ModificarFacultad(?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nuevoNombre);
            ps.executeUpdate();
            System.out.println("Facultad modificada");
        }                   

    }
     
     //=> DELETE
     public void downFacultades(int id) throws SQLException{
        String sql = "CALL vera_DesactivarFacultad(?)";/*Llamada al procedimiento*/
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Facultad desactivada");
        }                   

    }
    
     
     
     

     
     
     
     
    //===> MANTENIMIENTO TABLA PROFESION
        //=> LISTAR
    public ResultSet listProfesion() throws SQLException{
        String sql = "Select * from v_mostrarprofesion";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    //=> BUSCAR
    public ResultSet buscarProfesion(String nombre) throws SQLException{
        String sql = "{CALL vera_BuscarProfesiones(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, nombre);    /*Asignamos parámetros*/
        rs = cs.executeQuery();  /*Ejecutamos y obtenemos los resultados*/
        return rs;                 
    }
    //=> INSERTAR
    public void insertProfesion(String nombre) throws SQLException{
        String sql = "CALL vera_InsertarProfesion(?)";//Llamada al procedimiento
        try 
            (PreparedStatement ps = conn.prepareCall(sql)){
            ps.setString(1, nombre);
            ps.execute();
            System.out.println("Profesion insertada");
        }
    }     
        
     //=> MODIFICAR
     public void modifProfesion(int id, String nuevoNombre) throws SQLException{
        String sql = "CALL vera_ModificarProfesion(?,?)";/*Llamada al procedimiento*/
        try (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.setString(2, nuevoNombre);
            ps.executeUpdate();
            System.out.println("Profesion modificada");
        }                   

    }
     
     //=> DELETE
     public void downProfesion(int id) throws SQLException{
        String sql = "CALL vera_DesactivarProfesion(?)";/*Llamada al procedimiento*/
        try 
            (PreparedStatement ps =conn.prepareCall(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Profesion desactivada");
        }                   

    }  
     
     
     
     
     
     
     
     
    
     
     
     
     
     //===> MANTENIMIENTO TABLA ESCUELAPROFESIONAL
      //=> COMBOBOX
    public ResultSet listFacultadesCombo() throws SQLException{
        String sql = "Select NombreFacultad from v_mostrarfacultades";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    //=> IDFACULTAD
    public int getFacultadIdCombo(String nombre) throws SQLException{
        String sql = "Select CodigoFacultad from v_mostrarfacultades WHERE NombreFacultad = ?";/*SQL Query*/
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,nombre);
        ResultSet rsAux = ps.executeQuery();
        if (rsAux.next()){
            return rsAux.getInt("CodigoFacultad");
        } else {
            return -1;
        }        
    } 

    
     
        //=> LISTAR
    public ResultSet verEscuelasProfesionales() throws SQLException{
        String sql = "Select * from view_EscuelaProfesional";/*SQL Query*/
        st = conn.createStatement(); /*Creamos la sentencia*/
        rs = st.executeQuery(sql);  /*Ejecutamos el query */
        return rs;                 /*Retorna el resultado*/
    }
    //=> BUSCAR
    public ResultSet buscarEscuelaProfesional(String param) throws SQLException{
        String sql = "{CALL vera_BuscarEscuelaProfesional(?)}";/*Llamada al procedimiento*/
        CallableStatement cs = conn.prepareCall(sql);/*Usamos CallableStatement*/
        cs.setString(1, param);    /*Asignamos parámetros*/
        rs = cs.executeQuery();  /*Ejecutamos y obtenemos los resultados*/
        return rs;                 
    }
    
    public boolean existeEscuelaConNombre(String nombre, int codigoEscuela) throws SQLException {
        String sql = "SELECT 1 FROM EscuelaProfesional "
            + "WHERE LOWER(NombreEscuela) = LOWER(?) "
            + "AND CodigoEscuela <> ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre.trim());
        ps.setInt(2, codigoEscuela); // 0 si es insertar
        ResultSet rs = ps.executeQuery();
        return rs.next(); // si devuelve algo, ya existe
    }
    
    //=> INSERTAR
    public void insertEscuelaProfesional(String nombreEscuela,int codigoFacultad) throws SQLException{
        if(existeEscuelaConNombre(nombreEscuela, 0)) {
            throw new IllegalArgumentException("Ya existe una escuela con ese nombre.");
        }
        
        String sql = "CALL vera_InsertarProfesion(?)";//Llamada al procedimiento
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombreEscuela.trim());
        ps.setInt(2, codigoFacultad);
        ps.execute();
        System.out.println("Escuela insertada");
    }   
    
        
/** Modifica los datos de una escuela profesional si no hay duplicación de nombre */
public void modificarEscuelaProfesional(int codigoEscuela, String nuevoNombre, 
        int nuevoCodigoFacultad) throws SQLException {
    // Validar si ya existe otra escuela con ese mismo nombre
    if (existeEscuelaConNombre(nuevoNombre, codigoEscuela)) {
        throw new IllegalArgumentException("El nombre de la escuela ya está registrado en otra escuela.");
    }
    
    String sql = "{CALL vera_ModificarEscuelaProfesional(?, ?, ?)}";
    CallableStatement cs = conn.prepareCall(sql);
    cs.setInt(1, codigoEscuela);
    cs.setString(2, nuevoNombre.trim());
    cs.setInt(3, nuevoCodigoFacultad);
    
    cs.execute();
}

/** Da de baja una escuela profesional (estado = 0) */
public void darDeBajaEscuela(int codigoEscuela) throws SQLException {
    String sql = "{CALL Vera_DarDeBajaEscuelaProfesional(?)}";
    CallableStatement cs = conn.prepareCall(sql);
    cs.setInt(1, codigoEscuela); // Solo se pasa el código, el estado lo maneja el procedure
    cs.execute();
}

/** Activa una escuela profesional (estado = 1) */
public void activarEscuela(int codigoEscuela) throws SQLException {
    String sql = "{CALL Vera_ActivarEscuelaProfesional(?)}";
    CallableStatement cs = conn.prepareCall(sql);
    cs.setInt(1, codigoEscuela); // Solo se pasa el código, el estado lo maneja el procedure
    cs.execute();
}
    
    
    

     //==============================================//
    /* --> Método de cierre de la base de datos <-- */
   //==============================================//
    public void closeConnection(){
        try{
            
            if (rs!=null) rs.close();     // Cerrar resultados
            if (st!=null) st.close();     // Cerrar sentencia 
            if (conn!=null) conn.close(); // Cerrar conexión
            System.out.println("Conexión cerrada correctamente!");
        } catch (SQLException e){
            System.err.println("Error al cerrar la conexión: "+e.getMessage());
        }
        
        
        
        
    }
     //================================================================//
    /* --> Método getConexión |> Usar la Conexión en otras clases <-- */
   //================================================================//
    public Connection getConnection(){
            return conn;
        }
}
