/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package destinos;

import java.util.Date;

/**
 *
 * @author Isaac
 */
public class DestinosBean {
    private Date fecha;
    private int claveEmpleado;
    private String descripcion;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(int claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
       
}
