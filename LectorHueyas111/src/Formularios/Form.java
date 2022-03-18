/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.Conexion;
import clock.RelogApplet;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Isaac
 */
public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Form
     */
    public Form() {
        initComponents();
        this.setLocationRelativeTo(null);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txtClave = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));
        getContentPane().add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 80, -1));

        jLabel1.setText("Clave");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 80, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 80, -1));
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 290, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 180, -1));
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 180, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      Calendar cal = Calendar.getInstance();
      Date fechaHoraCero = new Date();
      Date auxFecha= new Date();
      Date sComida= new Date();
          sComida.setHours(11);
          sComida.setMinutes(0);
          sComida.setSeconds(0);
          Date eComida = new Date();
          eComida.setHours(14);
          eComida.setMinutes(20);
          eComida.setSeconds(0);
          Date salida= new Date();
          salida.setHours(4);
          salida.setMinutes(0);
          salida.setSeconds(0);
      int horaCero=fechaHoraCero.getHours();
      fechaHoraCero.setHours(0);
      fechaHoraCero.setMinutes(0);
      fechaHoraCero.setSeconds(0);
      Date fecha= new Date();
           
      String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
        try {
             Connection c=Conexion.getConexion();
             PreparedStatement identificarStmt = c.prepareStatement("SELECT clave, apPaterno, apMaterno, nombre, hueya, foto FROM empleado"
                     + " where  clave = ? ");
             identificarStmt.setInt(1, Integer.parseInt(txtClave.getText()));
             ResultSet rsIdentificar = identificarStmt.executeQuery();
             
             if(rsIdentificar.next()){
                 
                PreparedStatement destinoStmt= c.prepareStatement("select * from destinos where claveEmpleado = ? and fecha = ?");
                destinoStmt.setInt(1, rsIdentificar.getInt(1));
                destinoStmt.setDate(2, new java.sql.Date(fecha.getTime()));
                ResultSet destinoRs =destinoStmt.executeQuery();
                
                   // JOptionPane.showMessageDialog(null, "encontramos registros");
                    PreparedStatement buscaEntradaStmt= c.prepareStatement("select * from entradas where claveEmpleado = ? and fecha = ?");
                    buscaEntradaStmt.setInt(1, rsIdentificar.getInt(1));
                    buscaEntradaStmt.setDate(2,new java.sql.Date(fecha.getTime()));
                    ResultSet buscaEntradaRs= buscaEntradaStmt.executeQuery();
                
                      if(buscaEntradaRs.next()){
                          PreparedStatement comidaStmt= null;
                         
                            switch(buscaEntradaRs.getInt("bandera")){
                                case 0:
                                     
                                      
                                    if(fecha.getTime()>=sComida.getTime())
                                    {
                                    comidaStmt= c.prepareStatement("update entradas set horaSComida = ?, bandera =? where "
                                    + " fecha =? and  claveEmpleado = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 1);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();
                                    }
                                    break;
                                case 1:
                                    
                                     if(fecha.getTime()>=eComida.getTime()){
                                    comidaStmt= c.prepareStatement("update entradas set horaEComida = ?, bandera =? where "
                                    + " fecha =? and  claveEmpleado = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 2);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();
                                     }
                                    break;
                                case 2:
                                    comidaStmt= c.prepareStatement("update entradas set horaSalida = ?, bandera =? where "
                                    + " fecha =? and  claveEmpleado = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 3);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();
                                    break;
                                default:
                                   comidaStmt= c.prepareStatement("update entradas set horaSalida = ?, bandera =? where "
                                    + " fecha =? and  claveEmpleado = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 3);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();      
                                 break;   
                            }
                                    
                              
                      }else
                      {
                                     PreparedStatement salidaStmt= c.prepareStatement("insert into entradas(fecha, claveEmpleado,"
                                             + "horaEntrada, entrada, destino, horaSComida, horaEComida, horaSalida, bandera) values(?,?,?,?,?,?,?,?,?)");
                                     salidaStmt.setDate(1,new java.sql.Date(fecha.getTime()));
                                     salidaStmt.setInt(2,rsIdentificar.getInt(1) );
                                     salidaStmt.setTime(3, new java.sql.Time(fecha.getTime()));
                                     salidaStmt.setString(4, hora);
                                     if(destinoRs.next()){
                                     salidaStmt.setString(5, destinoRs.getString("descripcion"));
                                     }else{
                                     salidaStmt.setString(5, "DESTINO LOCAL");    
                                     }
                                     salidaStmt.setTime(6,new java.sql.Time(fechaHoraCero.getTime()));
                                     salidaStmt.setTime(7,new java.sql.Time(fechaHoraCero.getTime()));
                                     salidaStmt.setTime(8,new java.sql.Time(fechaHoraCero.getTime()));
                                     salidaStmt.setInt(9, 0);
                                     salidaStmt.executeUpdate();
                      }
             JOptionPane.showMessageDialog(this, "Acceso correcto...");
                      
             }else{
                 
               JOptionPane.showMessageDialog(this, "Empleado no registrado...");
             }          
                      
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      Calendar cal = Calendar.getInstance();
      Date fechaHoraCero = new Date();
      Date resta= new Date();
      int horaCero=fechaHoraCero.getHours();
      fechaHoraCero.setHours(0);
      fechaHoraCero.setMinutes(0);
      fechaHoraCero.setSeconds(0);
      Date fecha= new Date();
      String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
      String hora1=fechaHoraCero.getHours()+":"+fechaHoraCero.getMinutes()+":"+fechaHoraCero.getSeconds();
      jTextField1.setText(hora);
      jTextField2.setText(hora1);
      
      Date operacion=new Date();
           //operacion= fecha+fechaHoraCero;
         if(fecha.getTime()>fechaHoraCero.getTime()){
             jTextField3.setText(hora+ "es mayor que "+hora1); 
         }
         else{
          jTextField3.setText(hora1+ "es mayor que "+hora);   
         }
         resta.setTime(fecha.getTime()-fechaHoraCero.getTime());
        jTextField4.setText(resta.getHours()+":"+resta.getMinutes()+":"+resta.getSeconds()); 
         resta.setTime(fecha.getTime()+fechaHoraCero.getTime());
        jTextField5.setText(resta.getHours()+":"+resta.getMinutes()+":"+resta.getSeconds());
      
      
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form().setVisible(true);
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField txtClave;
    // End of variables declaration//GEN-END:variables
}
