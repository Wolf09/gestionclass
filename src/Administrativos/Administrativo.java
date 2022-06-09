/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrativos;
import Auxiliares.Control;
import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Auxiliares.MyRen;
import Auxiliares.MyRen2;
import Principal.Conexion;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien2;
import ziconos.BotAct;
import ziconos.BotConsul;
import ziconos.BotInsertar;
import ziconos.BotTodos;

/**
 *
 * @author WjhonB
 */
public class Administrativo extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private boolean band7;
    public Administrativo() {
        initComponents();
        band7=true;
        this.setTitle("PERSONAL ADMINISTRATIVO");
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setLocation(0, 0);
        this.setMinimumSize(new Dimension(ancho-20, alto-50));
       this.setMaximumSize(new Dimension(ancho-10, alto-20));
        tabInv.getTableHeader().setPreferredSize(new Dimension(20,30));
        tabInv.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabInv.setRowHeight(80);
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
        icono =new Advertencia();
        icono1=new BotConsul();
        icono2=new BotTodos();
        icono3=new BotInsertar();
        icono4=new BotAct();
        icono5=new Bien2();
        botCGA.setIcon(icono1);
        botCGN.setIcon(icono1);
        botCPG.setIcon(icono1);
        botCPG.setIcon(icono2);
        botNuevo.setIcon(icono3);
        
    }

    public void mostrardatos(){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("<html>CÓDIGO DEL<br>ADMINISTRATIVO</html>");
    modelo.addColumn("ADMINISTRATIVO");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    tabInv.setModel(modelo);
    TableColumnModel columnModel = tabInv.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(280);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(280);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(380);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(300);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    String sql="";

        sql="SELECT codadm,apellidos,nombres,celular,email FROM administrativo ORDER BY apellidos,nombres";
     
    Object []datos = new Object [5];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);      
                datos[1]=rs.getString(2)+" "+rs.getString(3);
                datos[2]=rs.getString(4);
                datos[3]=rs.getString(5);        
                modelo.addRow(datos);
            }
            tabInv.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    public void mostrardatos1(String aa1){
     MiModelo modelo = new MiModelo();
    modelo.addColumn("<html>CÓDIGO DEL<br>ADMINISTRATIVO</html>");
    modelo.addColumn("ADMINISTRATIVO");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    tabInv.setModel(modelo);
    TableColumnModel columnModel = tabInv.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(280);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(280);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(380);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(300);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    String sql="";

         sql="SELECT codadm,apellidos,nombres,celular,email FROM administrativo WHERE apellidos like '%"+aa1+"%' ORDER BY apellidos,nombres";
     
      Object []datos = new Object [5];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);      
                datos[1]=rs.getString(2)+" "+rs.getString(3);
                datos[2]=rs.getString(4);
                datos[3]=rs.getString(5);        
                modelo.addRow(datos);
            }
            tabInv.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      
      
   public void mostrardatos2(String ca){
     MiModelo modelo = new MiModelo();
    modelo.addColumn("<html>CÓDIGO DEL<br>ADMINISTRATIVO</html>");
    modelo.addColumn("ADMINISTRATIVO");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    tabInv.setModel(modelo);
    TableColumnModel columnModel = tabInv.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(280);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(280);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(380);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(300);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    String sql="";

         sql="SELECT codadm,apellidos,nombres,celular,email FROM administrativo WHERE codadm='"+ca+"' ORDER BY apellidos,nombres";
     
      Object []datos = new Object [5];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);      
                datos[1]=rs.getString(2)+" "+rs.getString(3);
                datos[2]=rs.getString(4);
                datos[3]=rs.getString(5);        
                modelo.addRow(datos);
            }
            tabInv.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
         
   
        
        
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        modificar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        botNuevo = new javax.swing.JButton();
        botCPG = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        aa = new javax.swing.JTextField();
        botCGA = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        na = new javax.swing.JTextField();
        botCGN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabInv = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        capturar.add(modificar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 123));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "ADMINISTRATIVO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        botNuevo.setBackground(new java.awt.Color(0, 0, 0));
        botNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botNuevo.setForeground(new java.awt.Color(255, 255, 255));
        botNuevo.setText("NUEVO");
        botNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNuevoActionPerformed(evt);
            }
        });

        botCPG.setBackground(new java.awt.Color(0, 0, 0));
        botCPG.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPG.setForeground(new java.awt.Color(255, 255, 255));
        botCPG.setText("TODOS");
        botCPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPGActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consultar por Apellidos del Administrativo:");

        botCGA.setBackground(new java.awt.Color(0, 0, 0));
        botCGA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCGA.setForeground(new java.awt.Color(255, 255, 255));
        botCGA.setText("Consultar");
        botCGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCGAActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Consultar por Código del Administrativo:");

        botCGN.setBackground(new java.awt.Color(0, 0, 0));
        botCGN.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCGN.setForeground(new java.awt.Color(255, 255, 255));
        botCGN.setText("Consultar");
        botCGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCGNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(na, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCGN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botCPG))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aa, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCGA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 539, Short.MAX_VALUE)
                        .addComponent(botNuevo)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(aa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCGA)
                    .addComponent(botNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(na, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCGN)
                    .addComponent(botCPG))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tabInv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabInv.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabInv);

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNuevoActionPerformed
       iAdm in=new iAdm(new java.awt.Frame(), true);
       in.setVisible(true);
        if(in.cA.getText().equals("")){
                mostrardatos();
       }else{
       mostrardatos2(in.cA.getText());
       }
    }//GEN-LAST:event_botNuevoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
       int fila= tabInv.getSelectedRow();
        String sql="";
        String aux1="";
        String aux2="";
        String aux3="";
        String aux4="";
        String aux5="";
        if(fila>=0){
            aux1=tabInv.getValueAt(fila, 0).toString();
                       sql= "select apellidos, nombres from administrativo where codadm='"+tabInv.getValueAt(fila, 0).toString()+"'";
            try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                aux2=rs.getString(1);
                aux3=rs.getString(2);
                }
            }
            catch (SQLException ex) {
            Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux4=tabInv.getValueAt(fila, 2).toString();
            aux5=tabInv.getValueAt(fila, 3).toString();
            mAdm in=new mAdm(new java.awt.Frame(), true);
            in.llenardatos(aux1,aux2,aux3,aux4,aux5);
            in.setVisible(true);
            if(in.cA.getText().equals("")){
                mostrardatos();
            }else{
            mostrardatos2(in.cA.getText());
            }
            
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_modificarActionPerformed

    /*
    private void eliminaradministrativo(){
           int fila= tabInv.getSelectedRow();
        String sep = System.getProperty("line.separator");
        if(fila>= 0){
        getToolkit().beep();
        if (JOptionPane.showConfirmDialog(this, "Se eliminara un Administrativo se eliminaran:"+sep+"Sus actividades y subactividades dependientes"+sep+"de la gestión del administrativo", "Eliminar",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            PreparedStatement pst = cn.prepareStatement ("DELETE FROM administrativo WHERE codadm='"+tabInv.getValueAt(fila, 0).toString()+"'");
            pst.executeUpdate();
            new MensajeBeep().showMessageDialog(this, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            mostrardatos2(tabInv.getValueAt(fila, 0).toString());
            } catch (Exception e) {
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta,gestion codigo Duplicados, NO SE REALIZO CAMBIOS","",JOptionPane.WARNING_MESSAGE,null);
            }
         }
        }
        else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }
*/
    
    
    private void botCPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPGActionPerformed
       mostrardatos();
       limpiar();
    }//GEN-LAST:event_botCPGActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botCGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCGAActionPerformed
        mostrardatos1(aa.getText());
        limpiar();
    }//GEN-LAST:event_botCGAActionPerformed

    private void botCGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCGNActionPerformed
        mostrardatos2(na.getText());
        limpiar();
    }//GEN-LAST:event_botCGNActionPerformed
private void limpiar(){
    aa.setText("");
    na.setText("");
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aa;
    private javax.swing.JButton botCGA;
    private javax.swing.JButton botCGN;
    private javax.swing.JButton botCPG;
    private javax.swing.JButton botNuevo;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTextField na;
    private javax.swing.JTable tabInv;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
