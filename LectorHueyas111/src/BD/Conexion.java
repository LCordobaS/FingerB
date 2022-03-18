/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;



/**
 *
 * @author Isaac
 */
public class Conexion {
    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/huella?useUnicode=true&characterEncoding=utf-8";
    private static final String USER="root";
    private static final String PASS="Luis1234";
    private static Driver driver =null;
    Connection conn=null;
    
    public static synchronized Connection getConexion() throws SQLException{
    try{
        Class driverClass = Class.forName(DRIVER_CLASS);
        driver = (Driver)driverClass.newInstance();
        DriverManager.registerDriver(driver);    
    }catch(Exception ex){
        ex.printStackTrace();
    } 
    
    return DriverManager.getConnection(URL, USER, PASS);
}
    
      

   
 public static void close(Connection con){
    try{
        if(con!=null)con.close();
    }catch(SQLException ex){
        ex.printStackTrace();
    }
    
}       
  
public static void close(ResultSet rs){
    try{
        if(rs!=null) rs.close();
    }catch(SQLException ex){
        ex.printStackTrace();
    }
    
}

public static void close(PreparedStatement stmt){
    try{
        if(stmt!=null) stmt.close();
    }catch(SQLException ex){
     ex.printStackTrace();
    }
    
}

    Conexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet consulta(String sql){
        ResultSet res = null;
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            res = pstm.executeQuery();
        }catch (SQLException e){
            System.err.println("Error de consulta: " + e.getMessage());
        }
        return res;
    }
    
    public DefaultComboBoxModel Obt_Grupo(){
        DefaultComboBoxModel ListaGrupo = new DefaultComboBoxModel();
        ListaGrupo.addElement("Seleccione una clave");  
        ResultSet res = this.consulta("select * from grupo order by numeroGrupo");
        try{
            while(res.next()){
                ListaGrupo.addElement(res.getString("NumeroGrupo"));
            }
            res.close();
        }catch(SQLException ex){
            System.err.print(ex.getMessage());
            
            
        }
        return ListaGrupo;
    }

}


