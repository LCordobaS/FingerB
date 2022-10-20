/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

/**
 *
 * @author Isaac
 */
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.swing.JOptionPane;


public class ConexionBD {
    public DataSource dataSource;
    
/**
*    @atributos
*    @author: Napster2011
*  @access: public
*/
    /*
    public  String puerto="3306";
    public  String nomservidor="blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com";
    public  String bd="blslrlwy0ckh6h5o0hl9";
    static String url = "mysql://usnetzlulepfmhm0:dnHZhUzZNmz7zphiQtPL@blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com:3306/blslrlwy0ckh6h5o0hl9";
    static String user = "usnetzlulepfmhm0";
    static String pass = "dnHZhUzZNmz7zphiQtPL";*/
    
    
    public String MYSQL_ADDON_HOST="blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com";
    public String MYSQL_ADDON_DB="blslrlwy0ckh6h5o0hl9";
    static String MYSQL_ADDON_USER="usnetzlulepfmhm0";
    public  String MYSQL_ADDON_PORT="3306";
    static String MYSQL_ADDON_PASSWORD="dnHZhUzZNmz7zphiQtPL";
    static String MYSQL_ADDON_URI="mysql://usnetzlulepfmhm0:dnHZhUzZNmz7zphiQtPL@blslrlwy0ckh6h5o0hl9-mysql.services.clever-cloud.com:3306/blslrlwy0ckh6h5o0hl9";
    
    


Connection conn=null;
static public Connection cnx = null;
    
    //Para hacer las consultas
    static public Statement st = null;
    //Para recibir las consultas
    static public ResultSet rs = null;

    //Para realizar consultas preparadas
    static public PreparedStatement pst = null;
/**
*    @function: conectar
*    @author: Napster2011
*  @description: esta funcion se encarga de conectar la base de datos con el servidor
*  @access: public
*  @return
*/
public Connection conectar(){
try{
String ruta="jdbc:mysql://";
String servidor=MYSQL_ADDON_HOST+":"+MYSQL_ADDON_PORT+"/";
Class.forName("com.mysql.jdbc.Driver");
conn = DriverManager.getConnection(ruta+servidor+MYSQL_ADDON_DB,MYSQL_ADDON_USER,MYSQL_ADDON_PASSWORD);
if (conn!=null){
    
System.out.println("Conección a base de datos listo…");
}
else if (conn==null){
throw new SQLException();
}
}
catch(SQLException e){
JOptionPane.showMessageDialog(null, e.getMessage());
}
catch (ClassNotFoundException e) {
JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
}
catch (NullPointerException e){
JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
}
finally{
return conn;
}
}


/**
*    @function: desconectar
*    @author: Napster2011
*  @description: esta funcion se encarga de realizar la desconexion de la base de datos con el servidor
*  @access: public
*  @return
*/
public void desconectar(){
conn = null;
System.out.println("Desconexion a base de datos listo…");
}
public ConexionBD()
    {
        try 
        {
            //En JDBC4 la siguiente línea no es necesaria
            //No es necesaria, se pone por compatibilidad
            Class.forName("com.mysql.jdbc.Driver");
            
            cnx = DriverManager.getConnection(MYSQL_ADDON_HOST,MYSQL_ADDON_USER,MYSQL_ADDON_PASSWORD);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        /*finally
        {
            if(cnx != null)
            {
                try {
                    cnx.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }*/
    }   
}
