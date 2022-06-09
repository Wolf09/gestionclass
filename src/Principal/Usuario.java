/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Auxiliares.Control;
import Auxiliares.MensajeBeep;
import Interfaz.Administrador;
import Interfaz.ControlaHora;
import Posgrado.AMateria;
import Posgrado.DMateria;
import Posgrado.EMateria;
import Servidor1.Server2;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import ziconos.Error1;

/**
 *
 * @author WjhonB
 */
public class Usuario extends javax.swing.JFrame {

   private Error1 icono;
   private static String user="";
    public Usuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        icono = new Error1();
        this.addWindowListener ( new WindowAdapter() { 
        public void windowClosing(WindowEvent e) { 
            new Control().cerrarApp();
        } 
        }); 
           try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/carga.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
           this.getContentPane().setBackground(new Color(0,183,255));
        //this.setAlwaysOnTop(true);
        this.setVisible(true);
        
    }
    
    public Usuario(String u){}

    public String getUser(){
    return user;
    }

     public void acceder(String usuario, String pass)
    {
        String cap="";
        String cap1="";
        String cap2="";
       String sql="SELECT usuario,contrasena,tipo FROM usuarios WHERE usuario='"+usuario+"' AND contrasena='"+pass+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString("usuario");
                cap1 =rs.getString("contrasena");
                cap2 =rs.getString("tipo");
               }
            rs.beforeFirst();                   
             if(rs.next()){
               
                if(cap2.equals("ADMINISTRADOR")){
                    user=usuario;
                    this.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Usted esta Ingresando al programa, un momento porfavor....","",JOptionPane.INFORMATION_MESSAGE,icono); 
                    Administrador a=new Administrador();
                    
                }
                 if(cap2.equals("DOCENTE")){
                     user=usuario;
                     String sep = System.getProperty("line.separator");
                     String aux1="";
                     if(buscarDoc(cap,true)==true){
                         aux1=obtenerIp1();
                         if(buscarIPDOC(cap)==true){
                             modificarIPDOC(cap,aux1,"SI");
                         }else{
                             agregarIPDOC(cap,aux1,"SI");
                         }
                         Server2 serv=new Server2();
                         serv.ejecutar();
                         this.setVisible(false);
                          JOptionPane.showMessageDialog(this, "Usted esta Ingresando al programa, un momento porfavor....","",JOptionPane.INFORMATION_MESSAGE,icono);
                         DMateria doc=new DMateria(cap);
                         doc.mostrardatos(cap);
                         doc.setVisible(true);
                     }else{
                         new MensajeBeep().showMessageDialog(null, "Operación Incorrecta Usted no esta Registrado"+sep+"O no esta como Docente Activo, El Programa se Cerrara...","",JOptionPane.ERROR_MESSAGE,null);
                         new Control().cerrarApp();
                     }
                    
                    
                }
                  if(cap2.equals("ESTUDIANTE")){
                     user=usuario;
                     String sep = System.getProperty("line.separator");
                     if(buscarEst(cap,true)==true){   
                         this.setVisible(false);
                          JOptionPane.showMessageDialog(this, "Usted esta Ingresando al programa, un momento porfavor....","",JOptionPane.INFORMATION_MESSAGE,icono);
                         EMateria doc=new EMateria(cap);
                         doc.mostrardatos(cap);
                         doc.setVisible(true);
                     }else{
                         new MensajeBeep().showMessageDialog(null, "Operación Incorrecta Usted no esta Registrado"+sep+"O no esta como Estudiante Activo, El Programa se Cerrara...","",JOptionPane.ERROR_MESSAGE,null);
                         new Control().cerrarApp();
                     }
                    
                    
                }
                  
                   if(cap2.equals("ADMINISTRATIVO")){
                    String sep = System.getProperty("line.separator");
                    if(buscarAdministrativo(cap)==true){
                         this.setVisible(false);
                          JOptionPane.showMessageDialog(this, "Usted esta Ingresando al programa, un momento porfavor....","",JOptionPane.INFORMATION_MESSAGE,icono);
                         AMateria doc=new AMateria();
                         doc.mostrardatos();
                         doc.setVisible(true);
                    }
                    else{
                         new MensajeBeep().showMessageDialog(null, "Operación Incorrecta Usted no esta Registrado"+sep+"Como Admnistrativo","",JOptionPane.ERROR_MESSAGE,null);
                         new Control().cerrarApp();
                     }
                }

            }
             else{
                           new MensajeBeep().showMessageDialog(this, "Nombre de Usuario O Contraseña Incorrectos","",JOptionPane.WARNING_MESSAGE,icono);  
             }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"",JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
     
     
     private String obtenerIp1() {
         String aux1="";
             try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            //System.out.println("------------------");
            while (interfaces.hasMoreElements()) {
                NetworkInterface interfaz = interfaces.nextElement();
                // No necesitamos el Loopback
                if (interfaz.isLoopback()) {
                    continue;
                }
                //System.out.println("Interfaz: " + interfaz.getDisplayName());
                Enumeration<InetAddress> direccion = interfaz.getInetAddresses();
                while (direccion.hasMoreElements()) {
                    InetAddress ip = direccion.nextElement();
                    // Solo IPv4
                    if (ip instanceof Inet6Address) {
                        continue;
                    }
                   //System.out.println(ip.getHostAddress());
                   
                 if(!interfaz.getDisplayName().contains("Virtual")){
                     //System.out.println(ip.getHostAddress());
                     aux1=ip.getHostAddress();
                 }
                
                }
               //System.out.println("------------------");
            }
        } catch(SocketException e) {
            System.out.println(e);
        }
             return aux1;
     }  
     
     
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsu = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        panelUsu2 = new Auxiliares.PanelUsu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(410, 312));

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 16)); // NOI18N
        jLabel1.setText("Bienvenido al Programa CargaDescarga");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel2.setText("A continuación introdusca su nombre de Usuario");

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel3.setText("Y Contraseña");

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel4.setText("Usuario:");

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel5.setText("Contraseña:");

        txtUsu.setNextFocusableComponent(txtContra);
        txtUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuKeyTyped(evt);
            }
        });

        txtContra.setNextFocusableComponent(btnIngresar);
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraKeyTyped(evt);
            }
        });

        btnIngresar.setText("INGRESAR");
        btnIngresar.setNextFocusableComponent(btnSalir);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        btnIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnIngresarKeyTyped(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.setNextFocusableComponent(txtUsu);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        panelUsu2.setOpaque(false);

        javax.swing.GroupLayout panelUsu2Layout = new javax.swing.GroupLayout(panelUsu2);
        panelUsu2.setLayout(panelUsu2Layout);
        panelUsu2Layout.setHorizontalGroup(
            panelUsu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );
        panelUsu2Layout.setVerticalGroup(
            panelUsu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel4))
                            .addComponent(jLabel5))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel3))
                            .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelUsu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel4)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(39, 39, 39)
                                .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelUsu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIngresar)
                    .addComponent(btnSalir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
            String usu=txtUsu.getText();
            String pas=new String(txtContra.getPassword());
            if(usu.equals("")&&pas.equals("")){
                new MensajeBeep().showMessageDialog(this, "No Ingreso Datos, ingrese Por favor","Datos Nulos",JOptionPane.WARNING_MESSAGE,icono);
            }
            else{
                acceder(usu, pas);
            }
    }//GEN-LAST:event_btnIngresarActionPerformed

    
    private boolean buscarDoc(String coddoc,boolean habil){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT codigodoc FROM docente WHERE codigodoc='"+coddoc+"' AND activo="+habil+"";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              rs.beforeFirst();                   
              if(rs.next()){
                  band4=true;
              }else{
                  band4=false;
              }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
                //JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return band4;
    
    }
    
   
    private boolean buscarIPDOC(String coddoc){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT cod FROM chatdoc WHERE cod='"+coddoc+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              rs.beforeFirst();                   
              if(rs.next()){
                  band4=true;
              }else{
                  band4=false;
              }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return band4;
    
    }
    
    private void agregarIPDOC(String cod,String ip,String chat){
        
         try {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO chatdoc(cod,ip,chat) VALUES (?,?,?)");
                pst.setString(1, cod);
                pst.setString(2, ip);
                if(chat.equals("SI")){
                pst.setBoolean(3, true);
                }else{
                pst.setBoolean(3, false);
                }
                pst.executeUpdate();
                //new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            } catch (Exception ex) {
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Gestión, nominación,curso DUPLICADOS"+sep+"O la cantidad de letras sobrepasa lo permitido para un campo NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this,ex.getMessage(),"agre1",1);
            }
    
    
    }
    
    
    private void modificarIPDOC(String coddoc,String ip,String chat){
        boolean auxili=false;
        if(chat.equals("SI")){
                     auxili=true;
        }     
        try {
            PreparedStatement pst = cn.prepareStatement ("UPDATE chatdoc SET ip='"+ip+"',chat="+auxili+" WHERE cod='"+coddoc+"'");
            pst.executeUpdate();
            //new MensajeBeep().showMessageDialog(this, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            //this.setVisible(false);
            } catch (Exception ex) {
            //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Gestión,curso,nominación Duplicados"+sep+"O la cantidad de letras sobrepasa lo permitido para un campo NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
            //JOptionPane.showMessageDialog(this,ex.getMessage(),"mod1",1);
            }
    
    
    }
    
    private boolean buscarEst(String code,boolean habil){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT codigoest FROM estudiante WHERE codigoest='"+code+"' AND activo="+habil+"";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              rs.beforeFirst();                   
              if(rs.next()){
                  band4=true;
              }else{
                  band4=false;
              }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
            
            }
            return band4;
    
    }
    
   
    
          
          
     private boolean buscarAdministrativo(String cod){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT codadm FROM administrativo WHERE codadm='"+cod+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              rs.beforeFirst();                   
              if(rs.next()){
                  band4=true;
              }else{
                  band4=false;
              }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
                //JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return band4;
    
    }
          
          
    
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       new Control().cerrarApp();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyTyped

    }//GEN-LAST:event_btnIngresarKeyTyped

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed

    }//GEN-LAST:event_btnIngresarKeyPressed

    private void txtUsuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuKeyTyped
         char tecla= evt.getKeyChar();
        if(tecla== KeyEvent.VK_ENTER){
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txtUsuKeyTyped

    private void txtContraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyTyped
         char tecla= evt.getKeyChar();
        if(tecla== KeyEvent.VK_ENTER){
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txtContraKeyTyped

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private Auxiliares.PanelUsu panelUsu2;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtUsu;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
