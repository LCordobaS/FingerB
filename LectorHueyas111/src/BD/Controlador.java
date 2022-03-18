/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import javax.swing.JComboBox;

/**
 *
 * @author Luis Alfredo Cordoba
 */
public class Controlador {
    public ConexionBD bd = new ConexionBD();
    
    public void llenarCombo(JComboBox cbo, String consulta, int columna)
    {
        try 
        {
            //Siempre que queremos llenar algo tenemos que limpiarlo
            cbo.removeAllItems();
            
            //Crea un objeto de tipo Statement para enviar consultas a la BD
            //el cual devuelve un Statement
            ConexionBD.st = ConexionBD.cnx.createStatement();
            //Ejecutamos el SQL
            //el cual devuelve un ResultSet (objeto en forma de tabla)
            ConexionBD.rs = ConexionBD.st.executeQuery(consulta);
            
            //Recorremos el ResultSet, nos devuelve verdadero cuando tiene un registro
            while(ConexionBD.rs.next())
            {
                //Al método getString le pasamos como argumento el nombre de la columna
                //o número de la columna de la tabla que queremos que nos devuelva.
                cbo.addItem(ConexionBD.rs.getString(columna));
            }
            
            //Para que no se seleccione ninguno en el combobox
            cbo.setSelectedIndex(-1);
            
            //Limpiamos la memoria
            ConexionBD.rs.close();
            ConexionBD.st.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
