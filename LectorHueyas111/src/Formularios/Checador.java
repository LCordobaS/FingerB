/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import BD.Conexion;
import static Formularios.CapturaHuella.TEMPLATE_PROPERTY;
import clock.RelogApplet;
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
import java.applet.AudioClip;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author Isaac
 */
public final class Checador extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form CapturaDatos
     */
    String hora,minutos,segundos,ampm;
    Calendar calendario;
    Thread h1;
    public Checador() {
        
        initComponents();
        h1 = new Thread(this);
        h1.start();
        
        this.setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
public void windowClosing(java.awt.event.WindowEvent evt) {
formWindowClosing(evt);
}
public void windowOpened(java.awt.event.WindowEvent evt) {
formWindowOpened(evt);
}
});
        //CapturaHuella hueya= new CapturaHuella();
        //this.add(hueya);
        
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        relogApplet1 = new clock.RelogApplet();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lblImagenHuella = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(0, 102, 255));
        setPreferredSize(new java.awt.Dimension(1360, 763));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(31, 73, 125));
        jPanel1.setForeground(new java.awt.Color(31, 73, 125));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setName(""); // NOI18N

        lblImagenHuella.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hueya.png"))); // NOI18N
        jScrollPane4.setViewportView(lblImagenHuella);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 290, 290, 290));

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hueya.png"))); // NOI18N
        jScrollPane5.setViewportView(lblFoto);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 290, 290));

        jPanel2.setBackground(new java.awt.Color(31, 73, 125));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(31, 73, 125));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setAlignmentX(0.0F);
        jPanel3.setAlignmentY(0.0F);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHora.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("08:25:34 am");
        lblHora.setToolTipText("");
        jPanel3.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 5, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 50));

        jPanel4.setBackground(new java.awt.Color(31, 73, 125));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CLAVE");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        txtClave.setEditable(false);
        txtClave.setBackground(new java.awt.Color(31, 73, 125));
        txtClave.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPanel4.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 580, -1));

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(31, 73, 125));
        txtNombre.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 580, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 600, 210));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 620, 290));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        txtArea.setEditable(false);
        txtArea.setBackground(new java.awt.Color(31, 73, 125));
        txtArea.setColumns(20);
        txtArea.setRows(5);
        txtArea.setName("txtArea"); // NOI18N
        jScrollPane2.setViewportView(txtArea);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 1340, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -10, 1350, 770));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Checador().setVisible(true);
            }
        });
        
       
      
     
        
        
    }
    
//desde AKI
    
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
/**
*  @atributos
*  @author: Isaac
*  @access: public y private
*/
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
        try {
        identificarHuella();
        Reclutador.clear();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
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
//System.out.println("Las Caracteristicas de la Huella han sido creada");
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
          /////////modifique
//stop();
setTemplate(Reclutador.getTemplate());
         //////////Modifique
//EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla");
//btnIdentificar.setEnabled(true);
//btnGuardar.setEnabled(true);
//btnGuardar.grabFocus();
break;

case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
Reclutador.clear();
stop();
//EstadoHuellas();
setTemplate(null);
JOptionPane.showMessageDialog(Checador.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
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





//es donde hacemos la busquera de la hueya capturada contra la base de datos
public void identificarHuella() throws IOException{
     AudioClip sonido;
     int j;
     Calendar cal = Calendar.getInstance();
      Date fecha= new Date();
     String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
     Date sComida= new Date();
          sComida.setHours(11);
          sComida.setMinutes(0);
          sComida.setSeconds(0);
      Date eComida = new Date();
          eComida.setHours(14);
          eComida.setMinutes(20);
          eComida.setSeconds(0);
       Date fechaHoraCero = new Date();
       fechaHoraCero.setHours(0);
       fechaHoraCero.setMinutes(0);
       fechaHoraCero.setSeconds(0);
     //JOptionPane.showMessageDialog(null, "Bienvenido "+hora);
   // sonido= java.applet.Applet.newAudioClip(getClass().getResource(""));
    //sonido.play();
try{
//Establece los valores para la sentencia SQL
Connection c=Conexion.getConexion();

//Obtiene todas las huellas de la bd

PreparedStatement identificarStmt = c.prepareStatement("SELECT clave, apPaterno, apMaterno, nombre, hueya, foto FROM empleado");
//Obtiene todas las huellas de la bd
ResultSet rsIdentificar = identificarStmt.executeQuery();
           
//Si se encuentra el nombre en la base de datos
//byte templateBuffer[] = null;
int i=0;
while(rsIdentificar.next()){
i++;

//System.out.println("SQL:"+rsIdentificar.getString(1)+"\n");
//System.out.println("Contador:"+i+"\n");

byte templateBuffer[] = rsIdentificar.getBytes("hueya");
//Crea una nueva plantilla a partir de la guardada en la base de datos
DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
//Envia la plantilla creada al objeto contendor de Template del componente de huella digital
setTemplate(referenceTemplate);

// Compara las caracteriticas de la huella recientemente capturda con la
// alguna plantilla guardada en la base de datos que coincide con ese tipo
DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

//compara las plantilas (actual vs bd)
//Si encuentra correspondencia dibuja el mapa
//e indica el nombre de la persona que coincidió.
Image rpta=null;
if (result.isVerified()){
     //respondemos a la busqueda con un sonido
    sonido= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/acceso.wav"));
    sonido.play();
       //buscamos si existen registros de el destino planeado para este empleado
            PreparedStatement destinoStmt= c.prepareStatement("select * from destinos where claveAlumno = ? and fecha = ?");
            destinoStmt.setInt(1, rsIdentificar.getInt(1));
            destinoStmt.setDate(2, new java.sql.Date(fecha.getTime()));
            ResultSet destinoRs =destinoStmt.executeQuery();
            
               // JOptionPane.showMessageDialog(null, "encontramos registros");
                PreparedStatement buscaEntradaStmt= c.prepareStatement("select * from entradas where claveAlumno = ? and fecha = ?");
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
                                    + " fecha =? and  claveAlumno = ?");
                                             
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
                                    + " fecha =? and  claveAlumno = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 2);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();
                                     }
                                    break;
                                case 2:
                                    comidaStmt= c.prepareStatement("update entradas set horaSalida = ?, bandera =? where "
                                    + " fecha =? and  claveAlumno = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 3);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();
                                    break;
                                default:
                                   comidaStmt= c.prepareStatement("update entradas set horaSalida = ?, bandera =? where "
                                    + " fecha =? and  claveAlumno = ?");
                                             
                                     comidaStmt.setTime(1,new java.sql.Time(fecha.getTime()));
                                     comidaStmt.setInt(2, 3);        
                                     comidaStmt.setDate(3, new java.sql.Date(fecha.getTime()));
                                     comidaStmt.setInt(4, rsIdentificar.getInt(1));
                                     comidaStmt.executeUpdate();      
                                 break;   
                            }
                          
                  }else
                  {
                                  PreparedStatement salidaStmt= c.prepareStatement("insert into entradas(fecha, claveAlumno,"
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
          
    
    txtClave.setText(Integer.toString(rsIdentificar.getInt(1)));
    txtNombre.setText(rsIdentificar.getString(4).toUpperCase(Locale.FRENCH)+"  "+rsIdentificar.getString(2).toUpperCase(Locale.FRENCH)+"  "+rsIdentificar.getString(3).toUpperCase(Locale.FRENCH));
    Blob imagen = rsIdentificar.getBlob("foto");
    
 //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
 rpta= javax.imageio.ImageIO.read(imagen.getBinaryStream());
 Icon icon = new ImageIcon(rpta);
  lblFoto.setIcon(icon);
    
//JOptionPane.showMessageDialog(null, "Bienvenido "+rsIdentificar.getString("nombre"));
return;
}
}
//Si no encuentra alguna huella que coincida lo indica con un mensaje
//JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella.");
sonido= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/error.wav"));
sonido.play();
txtNombre.setText("NO EXISTE NINGUN REGISTRO QUE COINCIDA CON LA HUEYA CAPTURADA");
lblFoto.setIcon(null);
}
catch (SQLException e) {
System.out.println("Se produjo el siguiente error: "+e.getMessage());
e.printStackTrace();
}
/*finally{
con.desconectar();
}*/
}
public DPFPTemplate getTemplate() {
return template;
}

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblImagenHuella;
    private clock.RelogApplet relogApplet1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
while(ct == h1) {
calcula();
lblHora.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
try {
Thread.sleep(1000);
}catch(InterruptedException e) {}
}
    }
    

public void calcula () {
Calendar calendario = new GregorianCalendar();
Date fechaHoraActual = new Date();

calendario.setTime(fechaHoraActual);
ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
if(ampm.equals("PM")){
int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
hora = h>9?""+h:"0"+h;
}else{
hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY); }
minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
}
    
    
}
