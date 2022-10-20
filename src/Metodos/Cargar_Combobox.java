/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/**
 *
 * @author EDBAST
 */
public class Cargar_Combobox {
    
Pool metodospool = new Pool();


public void consultar_Cuatrimestre(JComboBox cbCuatrimestre){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT * FROM cuatrimestre ORDER BY IdCuatrimestre ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbCuatrimestre.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbCuatrimestre.addItem(result.getString("NumeroCuatrimestre"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }

}
    

}
public void consultar_Grupo(JComboBox cbGrupo){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT * FROM grupo ORDER BY IdGrupo ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbGrupo.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbGrupo.addItem(result.getString("NumeroGrupo"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }

}
    

}
public void consultar_Carrera(JComboBox cbCarrera){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT * FROM carrera ORDER BY IdCarrera ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbCarrera.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbCarrera.addItem(result.getString("NombreCarrera"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }

}
    

}
public void consultar_Estado(JComboBox cbEstado){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT NombreE FROM estados ORDER BY NombreE ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbEstado.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbEstado.addItem(result.getString("NombreE"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }

}
    

}
public void consultar_Municipios(JComboBox cbMunicipios){

//Creamos objeto tipo Connection    
java.sql.Connection conectar = null;    
PreparedStatement pst = null;
ResultSet result = null;

//Creamos la Consulta SQL
String SSQL = "SELECT NombreM FROM municipios ORDER BY IdMunicipio ASC";

//Establecemos bloque try-catch-finally
try {
       
   //Establecemos conexión con la BD 
   conectar = metodospool.dataSource.getConnection();  
   //Preparamos la consulta SQL
   pst = conectar.prepareStatement(SSQL);
   //Ejecutamos la consulta
   result = pst.executeQuery();
   
   //LLenamos nuestro ComboBox
   cbMunicipios.addItem("Seleccione una opción");
   
   while(result.next()){
   
       cbMunicipios.addItem(result.getString("NombreM"));
   
   }
   
    
} catch (SQLException e) {

    JOptionPane.showMessageDialog(null, e);
    
}finally{

    if(conectar!=null){
        
        try {
        
            conectar.close();
            result.close();
            
            conectar=null;
            result=null;
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);
        
        }
    
    }

}
    

}
    
    
}
