/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleado;

import java.util.List;

/**
 *
 * @author Isaac
 */
public interface EmpleadoDao {
    public void insertar(EmpleadoBean empleado);
    public void actualizar(EmpleadoBean empleado);
    public void eliminar(EmpleadoBean empleado);
    public List<EmpleadoBean> seleccionar(EmpleadoBean empleado);
    public EmpleadoBean seleccionarById(int id);
      
}
