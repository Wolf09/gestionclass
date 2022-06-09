/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import Principal.Conexion;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import ziconos.Advertencia;
import ziconos.Bien;

/**
 *
 * @author WjhonB
 */
public class MensajeDocente extends javax.swing.JDialog {

    private Bien icono2;
    private Advertencia icono;
    private static String ges5;
    private static String coddoc5;
    private static String mat5;
    private static String men5;
    public MensajeDocente(java.awt.Frame parent, boolean modal,String ges6,String coddoc6,String mat6,String men6) {
        super(parent, modal);
        initComponents();
        ges5=ges6;
        coddoc5=coddoc6;
        mat5=mat6;
        men5=men6;
        this.setTitle("MENSAJE");
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

     public String obtenerCorreos(){
        ArrayList<String> corr= new ArrayList<String>();
        String correoTotal="";
    String sql="";

        sql="SELECT estudiante.email FROM estudiante,docente WHERE estudiante.gestion=docente.gestion AND estudiante.codigodoc=docente.codigodoc AND estudiante.materia=docente.materia AND estudiante.mension=docente.mension AND estudiante.gestion='"+ges5+"' AND estudiante.codigodoc='"+coddoc5+"' AND estudiante.materia='"+mat5+"' AND estudiante.mension='"+men5+"'";
     
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                corr.add(rs.getString(1));
            }
             if(corr.size()==0){
                new MensajeBeep().showMessageDialog(this, "NO existen docentes con esos datos, NO se envio ningun mensaje","",JOptionPane.WARNING_MESSAGE,null);
            }else{
            for(int x=0;x<corr.size();x++) {
                     correoTotal=correoTotal+corr.get(x)+",";
              }
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
        //System.out.println(correoTotal);
        corr.clear();
        return correoTotal;
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        corr = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        contra = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        asu = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mens = new javax.swing.JTextArea();
        botSend = new javax.swing.JButton();
        botExit = new javax.swing.JButton();
        botP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione su Provedor de Servicio:");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "gmail", "hotmail", "outlook", "yahoo" }));
        combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo:");

        corr.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña:");

        contra.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Asunto o Título:");

        asu.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mensaje:");

        mens.setColumns(20);
        mens.setLineWrap(true);
        mens.setRows(5);
        mens.setWrapStyleWord(true);
        mens.setEnabled(false);
        mens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                mensKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(mens);

        botSend.setBackground(new java.awt.Color(0, 0, 0));
        botSend.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botSend.setForeground(new java.awt.Color(255, 255, 255));
        botSend.setText("ENVIAR");
        botSend.setEnabled(false);
        botSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSendActionPerformed(evt);
            }
        });

        botExit.setBackground(new java.awt.Color(0, 0, 0));
        botExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botExit.setForeground(new java.awt.Color(255, 255, 255));
        botExit.setText("SALIR");
        botExit.setEnabled(false);
        botExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botExitActionPerformed(evt);
            }
        });

        botP.setBackground(new java.awt.Color(0, 0, 0));
        botP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botP.setForeground(new java.awt.Color(255, 255, 255));
        botP.setText("PROVEDOR");
        botP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(corr)
                                    .addComponent(contra)
                                    .addComponent(asu)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(botSend)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botExit)
                                .addGap(63, 63, 63))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botP)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(corr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(contra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(asu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botSend)
                    .addComponent(botExit))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSendActionPerformed
        String pas=new String(contra.getPassword());
        String aux="";
        String aux2="clas ";
        Correo cor=new Correo();
        if(corr.getText().equals("")||pas.equals("")||asu.getText().equals("")||mens.getText().equals("")){
            
             new MensajeBeep().showMessageDialog(this, "Los campos No pueden ser vacios","",JOptionPane.WARNING_MESSAGE,null);
        }else{
            aux=obtenerCorreos();
            botSend.setEnabled(false);
            if((combo.getSelectedItem().toString()).equals("gmail")){
                cor.mensaje(corr.getText(), pas, mens.getText(), aux, aux2+asu.getText());
            }else if((combo.getSelectedItem().toString()).equals("hotmail")){
                cor.mensajeHotmail(corr.getText(), pas, mens.getText(), aux, aux2+asu.getText());
            
            }else if((combo.getSelectedItem().toString()).equals("yahoo")){
                cor.mensajeYahoo(corr.getText(), pas, mens.getText(), aux, aux2+asu.getText());
            }else{
                cor.mensajeOutlook(corr.getText(), pas, mens.getText(), aux, aux2+asu.getText());
            
            }
            this.setVisible(false);
            
            
        }
    }//GEN-LAST:event_botSendActionPerformed

    private void comboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboKeyPressed

        
    }//GEN-LAST:event_comboKeyPressed

    private void botPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botPActionPerformed
       if((combo.getSelectedItem().toString()).equals("gmail")){
            corr.setText("@gmail.com");
        }else if((combo.getSelectedItem().toString()).equals("hotmail")){
            corr.setText("@hotmail.com");
        }else if((combo.getSelectedItem().toString()).equals("yahoo")){
            corr.setText("@yahoo.com");
        }else{
            corr.setText("@outlook.com");
        }
        corr.setEnabled(true);
        contra.setEnabled(true);
        mens.setEnabled(true);
        asu.setEnabled(true);
        botSend.setEnabled(true);
        botExit.setEnabled(true);
    }//GEN-LAST:event_botPActionPerformed

    private void botExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botExitActionPerformed

    private void mensKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla== KeyEvent.VK_ENTER){
            botSend.doClick();
        }
    }//GEN-LAST:event_mensKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asu;
    private javax.swing.JButton botExit;
    private javax.swing.JButton botP;
    private javax.swing.JButton botSend;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JPasswordField contra;
    private javax.swing.JTextField corr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mens;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();

}
