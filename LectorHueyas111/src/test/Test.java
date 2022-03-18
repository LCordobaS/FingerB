/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Empleado.EmpleadoBean;
import Empleado.EmpleadoDTO;
import Empleado.EmpleadoDao;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class Test {
    public static void main(String[] args) {
      List<EmpleadoBean> empleados;  
      EmpleadoDao dao = new EmpleadoDTO();
      
      empleados=dao.seleccionar(null);
      
      
    }
    
}
