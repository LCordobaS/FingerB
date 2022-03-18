/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

/**
 *
 * @author Luis Alfredo Cordoba
 */
public class DatosDB {
    private int IdCarrera;
    private String NombreCarrera;
    

    /*datos producto*/
    private int IdCuatrimestre;
    private String NumeroCuatrimestre;
    
    
    public DatosDB(){
        
        /*inializamos las variables*/
        this.IdCarrera=0;
        this.NombreCarrera="";
        
        
        this.IdCuatrimestre=0;
        this.NumeroCuatrimestre="";
        
        
    }

    public int getIdCarrera() {
        return IdCarrera;
    }

    public void setIdCarrera(int IdCarrera) {
        this.IdCarrera = IdCarrera;
    }

    public String getNombreCarrera() {
        return NombreCarrera;
    }

    public void setNombreCarrera(String NombreCarrera) {
        this.NombreCarrera = NombreCarrera;
    }

    public int getIdCuatrimestre() {
        return IdCuatrimestre;
    }

    public void setIdCuatrimestre(int IdCuatrimestre) {
        this.IdCuatrimestre = IdCuatrimestre;
    }

    public String getNumeroCuatrimestre() {
        return NumeroCuatrimestre;
    }

    public void setNumeroCuatrimestre(String NumeroCuatrimestre) {
        this.NumeroCuatrimestre = NumeroCuatrimestre;
    }
    
    
}


