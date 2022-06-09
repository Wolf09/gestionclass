/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Auxiliares.MyRen;
import Principal.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien;
import ziconos.Bien2;
import ziconos.BotAct;
import ziconos.BotConsul;
import ziconos.BotEli;
import ziconos.BotInsertar;
import ziconos.BotMod;
import ziconos.BotTodos;

/**
 *
 * @author WjhonB
 */
public class Usuarios extends javax.swing.JDialog {

private Advertencia icono;
private Bien icono2;
private BotConsul icono7;
private BotTodos icono3;
private BotInsertar icono4;
private BotAct icono5;
private Bien2 icono6;
private BotMod icono8;
private BotEli icono9;
    public Usuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("USUARIOS");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cpt.addItem("DOCENTE");
        cpt.addItem("ESTUDIANTE");
        cpt.addItem("ADMINISTRATIVO");
        cpt.addItem("ADMINISTRADOR");
        cpt.addItem("CONTROL HORA");
        icono =new Advertencia();
        icono2 =new Bien();
        icono7=new BotConsul();
        icono3=new BotTodos();
        icono4=new BotInsertar();
        icono5=new BotAct();
        icono6=new Bien2(); 
        icono8=new BotMod();
        icono9=new BotEli();
        botCPA.setIcon(icono7);
        botCPT.setIcon(icono7);
        botNuevo.setIcon(icono4);
        botT.setIcon(icono3);
        tabU.setRowHeight(90);
        try{
        this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/carga.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }

    public void mostrardatos(){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("USUARIO");
    modelo.addColumn("CONTRASEÑA");
    modelo.addColumn("TIPO");
    tabU.setModel(modelo);
    TableColumnModel columnModel = tabU.getColumnModel();
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setCellRenderer(new MyRen());

    String sql="";

        sql="SELECT * FROM usuarios order by usuario desc,tipo desc";
    
 
    String []datos = new String [4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            tabU.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
      public void mostrardatos1(String user){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("USUARIO");
    modelo.addColumn("CONTRASEÑA");
    modelo.addColumn("TIPO");
    tabU.setModel(modelo);
    TableColumnModel columnModel = tabU.getColumnModel();
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setCellRenderer(new MyRen());

    String sql="";

        sql="SELECT * FROM usuarios where usuario='"+user+"'order by usuario desc,tipo desc";
    
 
    String []datos = new String [4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            tabU.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
        public void mostrardatos2(String tipo){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("USUARIO");
    modelo.addColumn("CONTRASEÑA");
    modelo.addColumn("TIPO");
    tabU.setModel(modelo);
    TableColumnModel columnModel = tabU.getColumnModel();
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setCellRenderer(new MyRen());

    String sql="";

        sql="SELECT * FROM usuarios where tipo='"+tipo+"'order by usuario desc,tipo desc";
    
 
    String []datos = new String [4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                modelo.addRow(datos);
            }
            tabU.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
      
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        captura = new javax.swing.JPopupMenu();
        modificar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cpc = new javax.swing.JTextField();
        botCPA = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        botNuevo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cpt = new javax.swing.JComboBox<>();
        botCPT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabU = new javax.swing.JTable();

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        captura.add(modificar);

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        captura.add(eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por el codigo del usuario:");

        botCPA.setBackground(new java.awt.Color(0, 0, 0));
        botCPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPA.setForeground(new java.awt.Color(255, 255, 255));
        botCPA.setText("Consultar");
        botCPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPAActionPerformed(evt);
            }
        });

        botT.setBackground(new java.awt.Color(0, 0, 0));
        botT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botT.setForeground(new java.awt.Color(255, 255, 255));
        botT.setText("TODOS");
        botT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botTActionPerformed(evt);
            }
        });

        botNuevo.setBackground(new java.awt.Color(0, 0, 0));
        botNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botNuevo.setForeground(new java.awt.Color(255, 255, 255));
        botNuevo.setText("NUEVO");
        botNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNuevoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Consultar por Tipo:");

        cpt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        botCPT.setBackground(new java.awt.Color(0, 0, 0));
        botCPT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPT.setForeground(new java.awt.Color(255, 255, 255));
        botCPT.setText("Consultar");
        botCPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCPA)
                        .addGap(281, 281, 281)
                        .addComponent(botNuevo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cpt, 0, 212, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCPT)
                        .addGap(372, 372, 372)
                        .addComponent(botT)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cpc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCPA)
                    .addComponent(botNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cpt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCPT)
                    .addComponent(botT))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        tabU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabU.setComponentPopupMenu(captura);
        jScrollPane1.setViewportView(tabU);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botCPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPAActionPerformed
        mostrardatos1(cpc.getText());
        limpiar();
    }//GEN-LAST:event_botCPAActionPerformed

    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
        mostrardatos();
        limpiar();
    }//GEN-LAST:event_botTActionPerformed

    private void botNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNuevoActionPerformed
        iU in=new iU(new java.awt.Frame(), true);
        in.setVisible(true);
        mostrardatos1(in.usu.getText());
    }//GEN-LAST:event_botNuevoActionPerformed

    private void botCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPTActionPerformed
        if((cpt.getSelectedItem()).toString().equals("")){
          new MensajeBeep().showMessageDialog(this, "No selecciono un elemento de la Lista","Fallo",JOptionPane.WARNING_MESSAGE,null);    
        }else{
        mostrardatos2((cpt.getSelectedItem()).toString());
        limpiar();
        }
    }//GEN-LAST:event_botCPTActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
       int fila= tabU.getSelectedRow();
        String aux1="";
        String aux2="";
        String aux3="";
        if(fila>= 0){
            aux1=tabU.getValueAt(fila, 0).toString();
            aux2=tabU.getValueAt(fila, 1).toString();
            aux3=tabU.getValueAt(fila, 2).toString();
            mU mo=new mU(new java.awt.Frame(), true);
            mo.llenardatos(aux1, aux2,aux3);
            mo.setVisible(true);
            mostrardatos1(mo.usu.getText());
        }else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_modificarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int fila= tabU.getSelectedRow();
        String sep = System.getProperty("line.separator");
        if(fila>= 0){
        getToolkit().beep();
        if (JOptionPane.showConfirmDialog(this, "Se Eliminara el usuario?", "Eliminar",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            PreparedStatement pst = cn.prepareStatement ("DELETE FROM usuarios WHERE usuario='"+tabU.getValueAt(fila, 0).toString()+"'");
            pst.executeUpdate();
            new MensajeBeep().showMessageDialog(this, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            mostrardatos1(tabU.getValueAt(fila, 0).toString());
            } catch (Exception e) {
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta codigo de usuario duplicado, NO SE REALIZO CAMBIOS","",JOptionPane.WARNING_MESSAGE,null);
            }
         }
        }
        else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void limpiar(){
    cpc.setText("");
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCPA;
    private javax.swing.JButton botCPT;
    private javax.swing.JButton botNuevo;
    private javax.swing.JButton botT;
    private javax.swing.JPopupMenu captura;
    private javax.swing.JTextField cpc;
    private javax.swing.JComboBox<String> cpt;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTable tabU;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
