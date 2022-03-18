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
    public  String puerto="3306";
    public  String nomservidor="localhost";
    public  String bd="huella";
    static String url = "jdbc:mysql://localhost/huella";
    static String user = "root";
    static String pass = "Luis1234";


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
String servidor=nomservidor+":"+puerto+"/";
Class.forName("com.mysql.jdbc.Driver");
conn = DriverManager.getConnection(ruta+servidor+bd,user,pass);
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
            
            cnx = DriverManager.getConnection(url,user,pass);
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
