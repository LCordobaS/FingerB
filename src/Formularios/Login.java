/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import AppPackage.AnimationClass;
import BD.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author Isaac
 */
public final class Login extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        cambioIdioma("Espanol");
    }

    public void cambioIdioma(String nombreIdioma) {

        idioma = new Idioma(nombreIdioma);

        LblSelec.setText(idioma.getProperty("idiomas"));
        LblBusqueda.setText(idioma.getProperty("busqueda"));
        LblRegistro.setText(idioma.getProperty("registro"));

        cbIdiomas.removeAllItems();

        String idiomas[] = {
            idioma.getProperty("espanol"),
            idioma.getProperty("ingles"),};

        for (int i = 0; i < idiomas.length; i++) {
            cbIdiomas.addItem(idiomas[i]);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LblSelec = new javax.swing.JLabel();
        JlIdioma = new javax.swing.JLabel();
        LblRegistro = new javax.swing.JLabel();
        LblBusqueda = new javax.swing.JLabel();
        JlRegistro = new javax.swing.JLabel();
        JBusqueda = new javax.swing.JLabel();
        cbIdiomas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalPolicyProvider(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 10, 10, 5, new java.awt.Color(0, 153, 51)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblSelec.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblSelec.setForeground(new java.awt.Color(255, 91, 71));
        LblSelec.setText("Seleccion de Idioma");
        jPanel2.add(LblSelec, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

        JlIdioma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Maintenance_96px.png"))); // NOI18N
        JlIdioma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JlIdioma.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JlIdiomaMouseMoved(evt);
            }
        });
        JlIdioma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JlIdiomaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JlIdiomaMouseExited(evt);
            }
        });
        jPanel2.add(JlIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 160, 150));

        LblRegistro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblRegistro.setForeground(new java.awt.Color(255, 91, 71));
        LblRegistro.setText("Registro Alumno");
        jPanel2.add(LblRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 100, 30));

        LblBusqueda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LblBusqueda.setForeground(new java.awt.Color(255, 91, 71));
        LblBusqueda.setText("Busqueda");
        jPanel2.add(LblBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, -1, 20));

        JlRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Today_96px.png"))); // NOI18N
        JlRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JlRegistro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JlRegistroMouseMoved(evt);
            }
        });
        JlRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JlRegistroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JlRegistroMouseExited(evt);
            }
        });
        jPanel2.add(JlRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 160, 140));

        JBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Google_Drive_96px.png"))); // NOI18N
        JBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JBusqueda.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JBusquedaMouseMoved(evt);
            }
        });
        JBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBusquedaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JBusquedaMouseExited(evt);
            }
        });
        jPanel2.add(JBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 160, 140));

        cbIdiomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdiomasActionPerformed(evt);
            }
        });
        jPanel2.add(cbIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 150, -1));

        jLabel6.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel6.setText("Panel Alumnos");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, 40));

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel1.setText("Idioma");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1050, 540));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 153, 51)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Multiply_32px.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, -1, -1));

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 34)); // NOI18N
        jLabel8.setText("Universidad Tecnologica del Sureste de Veracruz");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setState(Login.ICONIFIED);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        int result = JOptionPane.showConfirmDialog(null, "Desea salir de la captura de datos", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void cbIdiomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdiomasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIdiomasActionPerformed

    private void JBusquedaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBusquedaMouseExited
        JBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_JBusquedaMouseExited

    private void JBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBusquedaMouseClicked
        AltaDestinos Ventana = new AltaDestinos();
        Ventana.setVisible(true);
        dispose();
    }//GEN-LAST:event_JBusquedaMouseClicked

    private void JBusquedaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBusquedaMouseMoved
        JBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_JBusquedaMouseMoved

    private void JlRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlRegistroMouseExited
        JlRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_JlRegistroMouseExited

    private void JlRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlRegistroMouseClicked
        AltaDeEmpleados Ventana = new AltaDeEmpleados();
        Ventana.setVisible(true);
        dispose();
    }//GEN-LAST:event_JlRegistroMouseClicked

    private void JlRegistroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlRegistroMouseMoved
        JlRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_JlRegistroMouseMoved

    private void JlIdiomaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlIdiomaMouseExited
        JlIdioma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_JlIdiomaMouseExited

    private void JlIdiomaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlIdiomaMouseClicked
        switch (cbIdiomas.getSelectedIndex()) {

            case 0:
                cambioIdioma("Espanol");
                break;
            case 1:
                cambioIdioma("Ingles");
                break;

        }
    }//GEN-LAST:event_JlIdiomaMouseClicked

    private void JlIdiomaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JlIdiomaMouseMoved
        JlIdioma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_JlIdiomaMouseMoved

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JBusqueda;
    private javax.swing.JLabel JlIdioma;
    private javax.swing.JLabel JlRegistro;
    private javax.swing.JLabel LblBusqueda;
    private javax.swing.JLabel LblRegistro;
    private javax.swing.JLabel LblSelec;
    private javax.swing.JComboBox<String> cbIdiomas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
private Idioma idioma;
}
