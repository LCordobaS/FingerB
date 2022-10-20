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
    /*
    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://usnetzlulepfmhm0:dnHZhUzZNmz7zphiQtPL@blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com:3306/blslrlwy0ckh6h5o0hl9";
    private static final String USER="usnetzlulepfmhm0";
    private static final String PASS="dnHZhUzZNmz7zphiQtPL";
    private static Driver driver =null;
    Connection conn=null;*/
    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";

    private static final String MYSQL_ADDON_USER="usnetzlulepfmhm0";
    
    private static final String MYSQL_ADDON_PASSWORD="dnHZhUzZNmz7zphiQtPL";
    private static final String MYSQL_ADDON_URI="jdbc:mysql://blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com:3306/blslrlwy0ckh6h5o0hl9";
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
    
    return DriverManager.getConnection(MYSQL_ADDON_URI, MYSQL_ADDON_USER, MYSQL_ADDON_PASSWORD);
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


