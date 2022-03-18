/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package destinos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import BD.Conexion;
import java.util.Date;

/**
 *
 * @author Isaac
 */
public class DestionoDTO implements DestinoDAO{
     private static final String SQL_INSERTA="insert into destinos(fecha, claveEmpleado, descripcion) values(?,?,?)";
     private static final String SQL_ACTUALIZA="update destinos set fecha=?, claveEmpleado=?, descripcion=? where clave=?";
     private static final String SQL_ELIMINA="delete from destinos where clave=?";
     private static final String SQL_SELALL="select * from destinos";
     private static final String SQL_SELBYEMPLEADO="select * from destinos where claveEmpleado=?";
     

    @Override
    public int insertaDestino(DestinosBean destino) {
           int result=0;
           Date fecha= new Date();
                 fecha=destino.getFecha();
           Connection con = null;
           PreparedStatement stmt = null;
           try{
               con = Conexion.getConexion();
               stmt = con.prepareStatement(SQL_INSERTA);
               int index=1;
               stmt.setDate(index++,new java.sql.Date(fecha.getTime()));
               stmt.setInt(index++,destino.getClaveEmpleado());
               stmt.setString(index, destino.getDescripcion());
               result= stmt.executeUpdate();
               
           }catch(Exception ex){
               ex.printStackTrace();
           }finally{
               Conexion.close(stmt);
               Conexion.close(con);
           }
           
      
      return result;     
    }

    @Override
    public int actualizaDestino(DestinosBean destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminaDestino(DestinosBean destino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DestinosBean> seleccionaDestinos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DestinosBean> seleccionaDestinoByEmpleado(int claveEmpleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
