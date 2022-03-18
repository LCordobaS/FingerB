/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;


import BD.Conexion; 
import BD.DatosDB;
import BD.PersonaD;
import Empleado.EmpleadoBean;
import Empleado.EmpleadoDTO;
import Empleado.EmpleadoDao;
import static Formularios.CapturaDatos.TEMPLATE_PROPERTY;
import Metodos.Cargar_Combobox;
import api.Api;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_READY;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.xml.sax.Attributes;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Isaac
 */
public class AltaDeEmpleados extends javax.swing.JFrame {
    
    Cargar_Combobox metodos_cargarcombobox = new Cargar_Combobox();
    PersonaD personaD = new PersonaD();
    DatosDB datoDB = new DatosDB();
    
    public void LimpiarCajas(){
      txtClave.setText(null);
      txtAPaterno.setText(null);
      txtAMaterno.setText(null);
      txtNombre.setText(null);
      cbMunicipio.addItem(null);
      cbEstado.addItem(null);
      txtTelefono.setText(null);
      txtCell.setText(null);
      txtEMail.setText(null);
      lblImagenHuella.setIcon(null);
      lblImagenHuella.setIcon(null);
      jLabel1.setIcon(null);
      cbCarrera.addItem(null);
      cbCuatrimestre.addItem(null);
      cbGrupo.addItem(null);
      
  } 
    
    File file, copia;
    
    
    
    public AltaDeEmpleados() {
        
        initComponents();
        metodos_cargarcombobox.consultar_Cuatrimestre(cbCuatrimestre);
        metodos_cargarcombobox.consultar_Grupo(cbGrupo);
        metodos_cargarcombobox.consultar_Carrera(cbCarrera);
        metodos_cargarcombobox.consultar_Estado(cbEstado);
        metodos_cargarcombobox.consultar_Municipios(cbMunicipio);
        
      cambiarIdioma("Espanol");
        
        setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        
        
                addWindowListener(new java.awt.event.WindowAdapter() {
public void windowClosing(java.awt.event.WindowEvent evt) {
formWindowClosing(evt);
}
public void windowOpened(java.awt.event.WindowEvent evt) {
formWindowOpened(evt);
}
});
    }
    public void cambiarIdioma(String nombreIdioma){
        
        idioma=new Idioma(nombreIdioma);
        
        Lblregistro.setText(idioma.getProperty("registro"));
        btnCambio.setText(idioma.getProperty("cambio"));
        LblMatricula.setText(idioma.getProperty("matricula"));
        LblCarrera.setText(idioma.getProperty("carrera"));
        LblCuatri.setText(idioma.getProperty("cuatrimestre"));
        LblGrupo.setText(idioma.getProperty("grupo"));
        LblNombre.setText(idioma.getProperty("nombre"));
        LblApp.setText(idioma.getProperty("apellidoP"));
        LblApm.setText(idioma.getProperty("apellidoM"));
        LblEstado.setText(idioma.getProperty("estado"));
        LblMunicipio.setText(idioma.getProperty("municipio"));
        LblTelefono.setText(idioma.getProperty("telefono"));
        LblCell.setText(idioma.getProperty("cel"));
        LblEMail.setText(idioma.getProperty("Email"));
        btnExaminar.setText(idioma.getProperty("examinar"));
        btnGuardar.setText(idioma.getProperty("guardar"));
        LblMatricula.setText(idioma.getProperty("matricula"));

        
        cbIdiomas.removeAllItems();
        
        String idiomas[]={
                          idioma.getProperty("espanol"),
                          idioma.getProperty("ingles"),
                          };
        
        for(int i=0;i<idiomas.length;i++){
            cbIdiomas.addItem(idiomas[i]);
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LblEMail = new javax.swing.JLabel();
        LblCell = new javax.swing.JLabel();
        LblTelefono = new javax.swing.JLabel();
        LblEstado = new javax.swing.JLabel();
        LblMunicipio = new javax.swing.JLabel();
        LblNombre = new javax.swing.JLabel();
        LblApm = new javax.swing.JLabel();
        LblMatricula = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        txtAMaterno = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCell = new javax.swing.JTextField();
        txtEMail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        btnExaminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        LblApp = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        LblCarrera = new javax.swing.JLabel();
        LblCuatri = new javax.swing.JLabel();
        LblGrupo = new javax.swing.JLabel();
        cbCarrera = new javax.swing.JComboBox();
        cbCuatrimestre = new javax.swing.JComboBox<>();
        cbGrupo = new javax.swing.JComboBox<>();
        cbMunicipio = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        btnCambio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblImagenHuella = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Lblregistro = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbIdiomas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));
        setFocusTraversalPolicyProvider(true);
        setForeground(new java.awt.Color(0, 0, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEMail.setText("E-Mail");
        jPanel2.add(LblEMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        LblCell.setText("Cell");
        jPanel2.add(LblCell, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        LblTelefono.setText("Telefono");
        jPanel2.add(LblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        LblEstado.setText("Estado");
        jPanel2.add(LblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));

        LblMunicipio.setText("Municipio");
        jPanel2.add(LblMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        LblNombre.setText("Nombre");
        jPanel2.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        LblApm.setText("Apellido Materno");
        LblApm.setAlignmentY(1.0F);
        jPanel2.add(LblApm, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        LblMatricula.setText("Matricula");
        jPanel2.add(LblMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel2.add(txtAPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 290, -1));
        jPanel2.add(txtAMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 280, -1));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 570, -1));

        txtTelefono.setToolTipText("");
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 280, -1));

        txtCell.setToolTipText("");
        jPanel2.add(txtCell, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, -1));

        txtEMail.setToolTipText("");
        jPanel2.add(txtEMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 280, -1));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin_person_user_man_2839.png"))); // NOI18N
        jScrollPane2.setViewportView(jLabel1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 280, 240));

        btnExaminar.setBackground(new java.awt.Color(153, 204, 255));
        btnExaminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        btnExaminar.setText("Examinar");
        btnExaminar.setBorder(null);
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnExaminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, 90, -1));

        btnGuardar.setBackground(new java.awt.Color(153, 204, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_user.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 90, -1));

        LblApp.setText("Apellido Paterno");
        LblApp.setAlignmentY(1.0F);
        jPanel2.add(LblApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });
        jPanel2.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, -1));

        LblCarrera.setText("Carrera");
        jPanel2.add(LblCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        LblCuatri.setText("Cuatrimestre");
        jPanel2.add(LblCuatri, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        LblGrupo.setText("Grupo");
        jPanel2.add(LblGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        cbCarrera.setBackground(new java.awt.Color(153, 204, 255));
        cbCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCarreraActionPerformed(evt);
            }
        });
        jPanel2.add(cbCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 140, -1));

        cbCuatrimestre.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.add(cbCuatrimestre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 90, -1));

        cbGrupo.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.add(cbGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 90, -1));

        cbMunicipio.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.add(cbMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 240, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Next_page_L64px.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 464, 60, 70));

        cbEstado.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 160, -1));

        btnCambio.setBackground(new java.awt.Color(153, 204, 255));
        btnCambio.setText("Cambiar");
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });
        jPanel2.add(btnCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 560));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblImagenHuella.setBackground(new java.awt.Color(255, 255, 255));
        lblImagenHuella.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/139759.png"))); // NOI18N
        jPanel3.add(lblImagenHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 260, 250));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, -40, 330, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 410, 330));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtArea.setEditable(false);
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, 390, 170));
        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, -1));

        Lblregistro.setBackground(new java.awt.Color(255, 153, 0));
        Lblregistro.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        Lblregistro.setText("REGISTRO DE ALUMNOS");
        jPanel1.add(Lblregistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Expand_Arrow_32px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Multiply_32px.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, -1, -1));

        cbIdiomas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdiomasActionPerformed(evt);
            }
        });
        jPanel1.add(cbIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1145, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExaminarActionPerformed
      
       Api x= new Api(); 
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Archivo JPG(.jpg)", "jpg");
        JFileChooser selector=new JFileChooser();
        selector.setFileFilter(filtro);
        selector.setDialogTitle("Abrir archivo...");
        File ruta = new File("tarea4");
        selector.setCurrentDirectory(ruta);
        int resultado=selector.showOpenDialog(null);
        if(resultado==JFileChooser.APPROVE_OPTION){
          file=selector.getSelectedFile();
           copia=new File(file.getAbsolutePath());
           
            try {
                x.obtenerImagen(copia);
                this.jLabel1.setIcon(new ImageIcon(x.imagen));  
                
                //m.leerMatriz(copia);
            } catch (IOException ex) {
                
            }
        }
    }//GEN-LAST:event_btnExaminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarHuella();
Reclutador.clear();
LimpiarCajas();
lblImagenHuella.setIcon(null);
start();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void cbCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCarreraActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
      int result = JOptionPane.showConfirmDialog(null, "Desea salir de la captura de datos","Exit",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if(result == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(Login.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
       Login Ventana=new Login();
        Ventana.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
       switch(cbIdiomas.getSelectedIndex()){
            
           
           
            case 0:
                cambiarIdioma("Espanol");
                break;
            case 1:
                cambiarIdioma("Ingles");
                break;
            

        }
    }//GEN-LAST:event_btnCambioActionPerformed

    private void cbIdiomasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdiomasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIdiomasActionPerformed

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
            java.util.logging.Logger.getLogger(AltaDeEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltaDeEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltaDeEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltaDeEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AltaDeEmpleados().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblApm;
    private javax.swing.JLabel LblApp;
    private javax.swing.JLabel LblCarrera;
    private javax.swing.JLabel LblCell;
    private javax.swing.JLabel LblCuatri;
    private javax.swing.JLabel LblEMail;
    private javax.swing.JLabel LblEstado;
    private javax.swing.JLabel LblGrupo;
    private javax.swing.JLabel LblMatricula;
    private javax.swing.JLabel LblMunicipio;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblTelefono;
    private javax.swing.JLabel Lblregistro;
    private javax.swing.JButton btnCambio;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbCarrera;
    private javax.swing.JComboBox<String> cbCuatrimestre;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbGrupo;
    private javax.swing.JComboBox<String> cbIdiomas;
    private javax.swing.JComboBox<String> cbMunicipio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagenHuella;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtCell;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtEMail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
private void formWindowClosing(java.awt.event.WindowEvent evt) {
stop();
} 

private void formWindowOpened(java.awt.event.WindowEvent evt) {
Iniciar();
start();
//EstadoHuellas();
//btnGuardar.setEnabled(true);
//btnIdentificar.setEnabled(true);
//btnSalir.grabFocus();
}
public  void EstadoHuellas(){
EnviarTexto("Muestra de Huellas Necesarias para Guardar Template "+ Reclutador.getFeaturesNeeded());
}    


//Varible que permite iniciar el dispositivo de lector de huella conectado
// con sus distintos metodos.
private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

//Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
// y poder estimar la creacion de un template de la huella para luego poder guardarla
private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

//Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
// o verificarla con alguna guardada en la BD
private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
// necesarias de la huella si no ha ocurrido ningun problema
private DPFPTemplate template;
public static String TEMPLATE_PROPERTY = "template";    
    
public void EnviarTexto(String string) {
txtArea.append(string + "\n");
} 
    
protected void Iniciar(){
Lector.addDataListener(new DPFPDataAdapter() {
@Override public void dataAcquired(final DPFPDataEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("La Huella Digital ha sido Capturada");
ProcesarCaptura(e.getSample());
}
});
}
});

Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
@Override public void readerConnected(final DPFPReaderStatusEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
}
});
}

@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
}
});
}
});

Lector.addSensorListener(new DPFPSensorAdapter() {
@Override public void fingerTouched(final DPFPSensorEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
}
});
}

@Override public void fingerGone(final DPFPSensorEvent e) {
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("El dedo ha sido quitado del Lector de Huella");
}
});
}
});

Lector.addErrorListener(new DPFPErrorAdapter(){
public void errorReader(final DPFPErrorEvent e){
SwingUtilities.invokeLater(new Runnable() {
public void run() {
EnviarTexto("Error: "+e.getError());
}
});
}
});
} 
    
public DPFPFeatureSet featuresinscripcion;
public DPFPFeatureSet featuresverificacion;

public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
try {
return extractor.createFeatureSet(sample, purpose);
}
catch (DPFPImageQualityException e) {
return null;
}
}

/**
*  @function: ProcesarCaptura
*  @author: Isaac
*  @description:
*  @access: public
*  @return
*/
public void ProcesarCaptura(DPFPSample sample){
// Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

// Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

// Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
if (featuresinscripcion != null){
try{
System.out.println("Las Caracteristicas de la Huella han sido creada");
Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

// Dibuja la huella dactilar capturada.
Image image=CrearImagenHuella(sample);
DibujarHuella(image);

//btnIdentificar.setEnabled(true);
}
catch (DPFPImageQualityException ex) {
System.err.println("Error: "+ex.getMessage());
}

finally {
//EstadoHuellas();

// Comprueba si la plantilla se ha creado.
switch(Reclutador.getTemplateStatus()){
case TEMPLATE_STATUS_READY: // informe de éxito y detiene  la captura de huellas
//EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla");    
stop();
setTemplate(Reclutador.getTemplate());
EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla");
//btnIdentificar.setEnabled(true);
//btnGuardar.setEnabled(true);
//btnGuardar.grabFocus();
break;

case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
Reclutador.clear();
stop();
//EstadoHuellas();
setTemplate(null);
JOptionPane.showMessageDialog(AltaDeEmpleados.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
start();
break;
}
}
}
}

public void DibujarHuella(Image image) {
lblImagenHuella.setIcon(new ImageIcon(
image.getScaledInstance(lblImagenHuella.getWidth(), lblImagenHuella.getHeight(), Image.SCALE_DEFAULT)));
repaint();
}

public void start(){
Lector.startCapture();
EnviarTexto("Utilizando el Lector de Huella Dactilar ");
}

public void setTemplate(DPFPTemplate template) {
DPFPTemplate old = this.template;
this.template = template;
firePropertyChange(TEMPLATE_PROPERTY, old, template);
}

public Image CrearImagenHuella(DPFPSample sample) {
return DPFPGlobal.getSampleConversionFactory().createImage(sample);
}

public void stop(){
Lector.stopCapture();
EnviarTexto("No se está usando el Lector de Huella Dactilar ");
}

public DPFPTemplate getTemplate() {
return template;
}

    public void guardarHuella() {
        int resultado;
       ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
       Integer tamañoHuella=template.serialize().length;
       Connection con= null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EmpleadoBean empleado= null;
        try{
            con= Conexion.getConexion();
            stmt= con.prepareStatement("select clave, hueya, hueya1 from empleado where clave= ?");
            stmt.setInt(1, Integer.parseInt(txtClave.getText()));
            rs = stmt.executeQuery();
            if(rs.next()){
               //Lee la plantilla de la base de datos
                byte templateBuffer[] = rs.getBytes(2);
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);
                  //Compara las caracteriticas de la huella recientemente capturda con la
                // plantilla guardada al usuario especifico en la base de datos
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                
                //compara las plantilas (actual vs bd)
if (result.isVerified()){
JOptionPane.showMessageDialog(null, "La huella ya existe, coloque un dedo diferente");
}
else{
PreparedStatement guardarStmt2 = con.prepareStatement("update empleado set hueya1=? where (clave=?)");
guardarStmt2.setBinaryStream(1, datosHuella,tamañoHuella);
guardarStmt2.setInt(2,Integer.parseInt(txtClave.getText()));

//Ejecuta la sentencia
guardarStmt2.execute();
guardarStmt2.close();
JOptionPane.showMessageDialog(null,"Huella Guardada Correctament");
}
}
else if(!rs.next()){
//String nombre = JOptionPane.showInputDialog("Nombre y Apellidos:");
 try{   
PreparedStatement guardarStmt = con.prepareStatement("INSERT INTO empleado(clave, NombreCarrera, NombreCuatrimestre, NombreGrupo, apPaterno, apMaterno"
        + ", nombre, NombreM,NombreE , "
        + " telefono, cel, eMail, hueya, hueya1, foto) "
        + "values(?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
guardarStmt.setInt(1,Integer.parseInt(txtClave.getText()));
guardarStmt.setString(2,cbCarrera.getSelectedItem().toString());
guardarStmt.setString(3,cbCuatrimestre.getSelectedItem().toString());
guardarStmt.setString(4,cbGrupo.getSelectedItem().toString());
guardarStmt.setString(5,txtAPaterno.getText());
guardarStmt.setString(6,txtAMaterno.getText());
guardarStmt.setString(7,txtNombre.getText());
guardarStmt.setString(8,cbMunicipio.getSelectedItem().toString());
guardarStmt.setString(9,cbEstado.getSelectedItem().toString());
guardarStmt.setString(10,txtTelefono.getText());
guardarStmt.setString(11,txtCell.getText());
guardarStmt.setString(12,txtEMail.getText());
guardarStmt.setBinaryStream(13, datosHuella,tamañoHuella);
guardarStmt.setBinaryStream(14, datosHuella,tamañoHuella);
FileInputStream fis = new FileInputStream(file);
guardarStmt.setBinaryStream(15, fis,(int)file.length());



//Ejecuta la sentencia
guardarStmt.executeUpdate();
guardarStmt.close();
 }catch(Exception ex){
  JOptionPane.showMessageDialog(null,ex.getMessage());   
 }
JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");

                
             
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
       
      
       
       
       
       
       
    }
    private Idioma idioma;
}

