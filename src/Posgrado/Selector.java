/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import Auxiliares.Redondear;
import Principal.Conexion;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ziconos.Advertencia;
import ziconos.Bien;

/**
 *
 * @author WjhonB
 */
public class Selector extends javax.swing.JDialog {

    private Bien icono2;
    private Advertencia icono;
    private static String act5;
    private static String unitem5;
    private static String ges5;
    private static String coddoc5;
    private static String nom5;
    private static String cur5;
    private static String mat5;
    private boolean band;
    public Selector(java.awt.Frame parent, boolean modal,String act6,String unitem6,String ges6,String coddoc6,String nom6,String cur6,String mat6) {
        super(parent, modal);
        initComponents();
        act5=act6;
        unitem5=unitem6;
        ges5=ges6;
        coddoc5=coddoc6;
        nom5=nom6;
        cur5=cur6;
        mat5=mat6;
        band=true;
        this.setTitle("SELECCIONE EL ARCHIVO");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        icono =new Advertencia();
        icono2=new Bien();
        try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/carga.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selector1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        selector1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selector1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selector1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selector1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selector1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selector1ActionPerformed
      
        int seleccion = selector1.showOpenDialog(null);//
       //int seleccion2=selector1.
       if (seleccion == JFileChooser.APPROVE_OPTION)
        {
             File fichero = selector1.getSelectedFile();
             try{
          
           String direc ="\\\\192.168.119.143\\controldegestionclas\\posgrado\\" + coddoc5 + "\\";
           File directorio =new File(direc);
            if (!directorio.exists()) {
                        directorio.mkdir();
            } 
             }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            agregar(act5,unitem5,ges5,coddoc5,nom5,cur5,mat5,fichero.getName());
            if(band==true){
             this.setVisible(false);
  //AQUI PAUSE          //Progreso pro=new Progreso(new java.awt.Frame(), true,fichero.getName(), fichero.getParent(), coddoc5,act5,unitem5,ges5,coddoc5,nom5,cur5,mat5);
          //  pro.setVisible(true);
            
            
            }
            
        }
        
    }//GEN-LAST:event_selector1ActionPerformed


    private void agregar(String act7,String unitem7,String ges7,String coddoc7,String nom7,String cur7,String mat7,String archivo1){
         Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
          SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sep = System.getProperty("line.separator");
        try {
                    PreparedStatement pst = cn.prepareStatement("INSERT INTO archidoc(direccion,archivo,fecha,act,unitem,gestion,coddoc,nominacion,curso,materia) VALUES (?,?,?,?,?,?,?,?,?,?)");
                    pst.setString(1, coddoc7);
                    pst.setString(2, archivo1);
                    pst.setString(3, formato.format(fecha1));
                    pst.setString(4, act7);
                    pst.setString(5, unitem7);
                    pst.setString(6, ges7);
                    pst.setString(7, coddoc7);
                    pst.setString(8, nom7);
                    pst.setString(9, cur7);
                    pst.setString(10, mat7);
                    pst.executeUpdate();
                    //new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                } catch (Exception ex) {
                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted ya subio u archivo para esta tarea","",JOptionPane.WARNING_MESSAGE,null);
                    band=false;
                    this.setVisible(false);
                }

    }
   
/*
    public static void main(String args[]){
        Selector sec=new Selector(new java.awt.Frame(), true,"","","","","","","");
        sec.setVisible(true);
    }
 */

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser selector1;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();

}
