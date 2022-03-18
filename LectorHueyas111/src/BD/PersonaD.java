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
public class PersonaD {
    private int IdPersona;
    private String Matricula,ApPaterno,ApMaterno,Nombre,calle,noExterior,IdMunicipio,
            IdEstado,cp,telefono,cel,eMail,IdCarrera,IdCuatrimestre,IdGrupo;

    public PersonaD(){
        
    }
    
    public PersonaD(int IdPersona, String Matricula, 
            String ApPaterno, String ApMaterno, String Nombre, 
            String calle, String noExterior, String IdMunicipio, 
            String IdEstado, String cp, String telefono, String cel, 
            String eMail, String IdCarrera, String IdCuatrimestre, String IdGrupo) {
        this.IdPersona = IdPersona;
        this.Matricula = Matricula;
        this.ApPaterno = ApPaterno;
        this.ApMaterno = ApMaterno;
        this.Nombre = Nombre;
        this.calle = calle;
        this.noExterior = noExterior;
        this.IdMunicipio = IdMunicipio;
        this.IdEstado = IdEstado;
        this.cp = cp;
        this.telefono = telefono;
        this.cel = cel;
        this.eMail = eMail;  
        this.IdCarrera = IdCarrera;
        this.IdCuatrimestre = IdCuatrimestre;
        this.IdGrupo = IdGrupo;
    }
    
    
}
