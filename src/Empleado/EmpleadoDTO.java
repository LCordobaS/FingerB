/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import BD.Conexion;

/**
 *
 * @author Isaac
 */
public class EmpleadoDTO implements EmpleadoDao{
    private static final String SQL_INSERT="";
    private static final String SQL_DELETE="";
    private static final String SQL_SELECT="";
    private static final String SQL_UPDATE="";
    private static final String SQL_SELECTBYID="select clave, hueya, hueya1 from empleado where clave= ?";

    @Override
    public void insertar(EmpleadoBean empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(EmpleadoBean empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(EmpleadoBean empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpleadoBean> seleccionar(EmpleadoBean empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoBean seleccionarById(int id) {
        Connection con= null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EmpleadoBean empleado= null;
        try{
            con= Conexion.getConexion();
            stmt= con.prepareStatement(SQL_SELECTBYID);
            stmt.setInt(3, id);
            rs = stmt.executeQuery();
            if(rs.next()){
               empleado.setClave(rs.getInt(1));
              // empleado.setHueya("hueya");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        return empleado;
    }
    
    
}
