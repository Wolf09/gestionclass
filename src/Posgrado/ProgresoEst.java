/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import Principal.Conexion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import ziconos.Advertencia;
import ziconos.Bien;

/**
 *
 * @author WjhonB
 */
public class ProgresoEst extends javax.swing.JDialog {
    private static String tarea5;
    private static String code5;
    private static String coddoc5;
    private static String ges5;
    private static String mat5;
    private static String men5;
    private Timer timer;
    private int conta;
    private static String archivo;
    private static String origen1;
    private static String direccion;
    public ProgresoEst(java.awt.Frame parent, boolean modal,String archivo2,String origen2,String direccion2,String tarea6,String code6,String coddoc6,String ges6,String mat6,String men6) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        code5=code6;
        tarea5=tarea6;
        ges5=ges6;
        coddoc5=coddoc6;
        mat5=mat6;
        men5=men6;
        conta=0;
        archivo=archivo2;
        origen1=origen2;
        direccion=direccion2;
         timer =new Timer(0,new Progreso2());
          timer.start();
        try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/carga.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        texto.setVisible(false);
        botAc.setEnabled(false);
        
    }

    
    /*
    public void subir1() {
        
        try{
           File origen=new File(origen1+"//"+archivo);
           File destino=new File("\\\\192.168.119.143\\controldegestionclas\\posgrado\\" + direccion + "\\");
           File destino1=new File("\\\\192.168.119.143\\controldegestionclas\\posgrado\\" +direccion+ "\\"+archivo);
           if(destino1.exists()){
               new MensajeBeep().showMessageDialog(this, "El nombre del archivo ya existe!!!, cambie el nombre y vuelva a intentar", "OJO", JOptionPane.ERROR_MESSAGE, null);
               eliminar();
               texto.setText("ERROR NO SE SUBIO NINGUN DOCUMENTO");
               texto.setVisible(true);
               botAc.setEnabled(true);
               timer.stop();
           }
            
           
           
        }catch(Exception ex){
            //System.out.println("Pasa aqui!!!     2    ");
            //JOptionPane.showMessageDialog(this, ex.getMessage());
               eliminar();
               getToolkit().beep();
               texto.setText("ERROR NO SE SUBIO NINGUN DOCUMENTO");
               botAc.setEnabled(true);
               texto.setVisible(true);
               timer.stop();
        }
           
   }
    */
    
    public void eliminar(){
        String sep = System.getProperty("line.separator");
         try {
            PreparedStatement pst = cn.prepareStatement ("DELETE FROM archivoest WHERE tareaotema='"+tarea5+"' AND codigoest='"+code5+"' AND codigodoc='"+coddoc5+"' AND gestion='"+ges5+"' AND materia='"+mat5+"' AND mension='"+men5+"'");
            pst.executeUpdate();
            //new MensajeBeep().showMessageDialog(this, "Operaci??n correcta!!!","",JOptionPane.INFORMATION_MESSAGE,null);
            } catch (Exception ex) {
                new MensajeBeep().showMessageDialog(this, "Error Interno"+sep+"Al elimnar el archivo","",JOptionPane.ERROR_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
    
    }
    
    
    public class Progreso2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            File origen=new File(origen1+"//"+archivo);
            int auxi=(int) origen.length();
            progreso1.setMaximum(auxi/1024);
            progreso1.setMinimum(1);
            progreso1.setValue(1);
            conta++;
                if(conta<progreso1.getMaximum()){
                    progreso1.setValue(conta);
                }
                else{

                      try{
                       
                                File destino1=new File("\\\\167.157.10.31\\controldegestionclas\\posgrado\\" +direccion+ "\\"+archivo);
                                if(destino1.exists()){
                                    new MensajeBeep().showMessageDialog(null, "El nombre del archivo ya existe!!!, cambie el nombre y vuelva a intentar", "OJO", JOptionPane.ERROR_MESSAGE, null);
                                    eliminar();
                                    texto.setText("ERROR NO SE SUBIO NINGUN DOCUMENTO");
                                    texto.setVisible(true);
                                    botAc.setEnabled(true);
                                    timer.stop();
                                }else{

                                       try {
                                    InputStream in = new FileInputStream(origen);
                                    OutputStream out = new FileOutputStream(destino1);
                                    byte[] buf = new byte[1024];
                                    int len;
                                    while ((len = in.read(buf)) > 0) {
                                        out.write(buf, 0, len);
                                    }
                                    in.close();
                                    out.close();
                                    progreso1.setValue(progreso1.getMaximum());
                                        texto.setText("ARCHIVO SUBIDO CON EXITO!!!");
                                        botAc.setEnabled(true);
                                        timer.stop();
                                        texto.setVisible(true);
               
                                    } catch (Exception ioe) {
                                       // System.out.println("Pasa aqui!!!");
                                    eliminar();
                                    getToolkit().beep();
                                    texto.setText("ERROR DE RED !!!, NO SE SUBIO NINGUN DOCUMENTO");
                                    botAc.setEnabled(true);
                                    texto.setVisible(true);
                                    //JOptionPane.showMessageDialog(this, ioe.getMessage());
                                    timer.stop();
                                     }
                              
                              
                              
                              }



                           }catch(Exception ex){
                               //System.out.println("Pasa aqui!!!     2    ");
                               //JOptionPane.showMessageDialog(this, ex.getMessage());
                                  eliminar();
                                  getToolkit().beep();
                                  texto.setText("ERROR!!!, NO SE SUBIO NINGUN DOCUMENTO");
                                  botAc.setEnabled(true);
                                  texto.setVisible(true);
                                  timer.stop();
                           }           
                    
                    
              }
        }
  
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        texto = new javax.swing.JLabel();
        progreso1 = new javax.swing.JProgressBar();
        botAc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        texto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        texto.setForeground(new java.awt.Color(255, 255, 255));
        texto.setText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        progreso1.setForeground(new java.awt.Color(0, 102, 204));

        botAc.setText("ACEPTAR");
        botAc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botAcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(botAc)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(texto)
                .addGap(18, 18, 18)
                .addComponent(progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botAc)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void botAcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botAcActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botAcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar progreso1;
    private javax.swing.JLabel texto;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
