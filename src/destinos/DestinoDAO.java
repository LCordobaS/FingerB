/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package destinos;

import java.util.List;

/**
 *
 * @author Isaac
 */
public interface DestinoDAO {
    public int insertaDestino(DestinosBean destino);
    public int actualizaDestino(DestinosBean destino);
    public int eliminaDestino(DestinosBean destino);
    public List<DestinosBean> seleccionaDestinos();
    public List<DestinosBean> seleccionaDestinoByEmpleado(int claveEmpleado);
    
}
