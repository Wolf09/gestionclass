/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.Control;
import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Auxiliares.MyRen;
import Auxiliares.MyRen2;
import Auxiliares.Report2;
import Principal.Conexion;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
public class EMateria extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private static String code5;
    public EMateria(String code6) {
        initComponents();
        code5=code6;
        this.setTitle("ESTUDIANTES");
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setLocation(0, 0);
        this.setMinimumSize(new Dimension(ancho-20, alto-50));
       this.setMaximumSize(new Dimension(ancho-10, alto-20));
        tabMat.getTableHeader().setPreferredSize(new Dimension(20,30));
        tabMat.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabMat.setRowHeight(100);
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
        //botCPG.setIcon(icono1);
        botCD.setIcon(icono1);
        botCM.setIcon(icono1);
        botT.setIcon(icono2);
    }

    public void mostrardatos(String ce){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("EMAIL");
    tabMat.setModel(modelo);
    TableColumnModel columnModel = tabMat.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(100);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setMaxWidth(0);
    columnModel.getColumn(1).setMinWidth(0);
    columnModel.getColumn(1).setPreferredWidth(0);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(390);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(350);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(200);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(200);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.email FROM docente,estudiante WHERE docente.codigodoc=estudiante.codigodoc AND docente.gestion=estudiante.gestion AND docente.materia=estudiante.materia AND docente.mension=estudiante.mension AND estudiante.codigoest='"+ce+"' ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
    Object []datos = new Object [10];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                datos[3]=rs.getString(6); 
                datos[4]=rs.getString(7);
                datos[5]=rs.getString(8);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    public void mostrardatos1(String ce,String apel){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("EMAIL");
    tabMat.setModel(modelo);
    TableColumnModel columnModel = tabMat.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(100);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setMaxWidth(0);
    columnModel.getColumn(1).setMinWidth(0);
    columnModel.getColumn(1).setPreferredWidth(0);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(390);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(350);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(200);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(200);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.email FROM docente,estudiante WHERE docente.codigodoc=estudiante.codigodoc AND docente.gestion=estudiante.gestion AND docente.materia=estudiante.materia AND docente.mension=estudiante.mension AND estudiante.codigoest='"+ce+"' AND docente.apellidos like '%"+apel+"%' ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
    Object []datos = new Object [10];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                datos[3]=rs.getString(6); 
                datos[4]=rs.getString(7);
                datos[5]=rs.getString(8);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      
      
      
    public void mostrardatos2(String ce,String mat){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("EMAIL");
    tabMat.setModel(modelo);
    TableColumnModel columnModel = tabMat.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(100);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setMaxWidth(0);
    columnModel.getColumn(1).setMinWidth(0);
    columnModel.getColumn(1).setPreferredWidth(0);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(390);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(350);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(200);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(200);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.email FROM docente,estudiante WHERE docente.codigodoc=estudiante.codigodoc AND docente.gestion=estudiante.gestion AND docente.materia=estudiante.materia AND docente.mension=estudiante.mension AND estudiante.codigoest='"+ce+"' AND docente.materia like '%"+mat+"%' ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
    Object []datos = new Object [10];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                datos[3]=rs.getString(6); 
                datos[4]=rs.getString(7);
                datos[5]=rs.getString(8);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
       
      
      
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        tareas = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botCM = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        cPM = new javax.swing.JTextField();
        cAD = new javax.swing.JTextField();
        botCD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabMat = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        tareas.setText("Tareas o Actividades");
        tareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tareasActionPerformed(evt);
            }
        });
        capturar.add(tareas);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 123));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "MATERIAS O MODULOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por Apellidos del Docente:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Consultar por Materia o Modulo:");

        botCM.setBackground(new java.awt.Color(0, 0, 0));
        botCM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCM.setForeground(new java.awt.Color(255, 255, 255));
        botCM.setText("Consultar");
        botCM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCMActionPerformed(evt);
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

        botCD.setBackground(new java.awt.Color(0, 0, 0));
        botCD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCD.setForeground(new java.awt.Color(255, 255, 255));
        botCD.setText("Consultar");
        botCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPM, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cAD, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botCM)
                .addGap(233, 233, 233)
                .addComponent(botT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCD)
                    .addComponent(botT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(botCM)
                    .addComponent(cPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabMat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabMat.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabMat);

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

        jMenu2.setText("Chat");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Chat con Docentes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botCMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCMActionPerformed
       mostrardatos2(code5,cPM.getText());
        limpiar();
    }//GEN-LAST:event_botCMActionPerformed

    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
       mostrardatos(code5);
    }//GEN-LAST:event_botTActionPerformed

    private void tareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tareasActionPerformed
        int fila= tabMat.getSelectedRow();
        //String sep = System.getProperty("line.separator");
        if(fila>= 0){
                ETarea ta=new ETarea(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString(),code5);
                ta.mostrardatos(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
                ta.setVisible(true);
                this.setVisible(false);
        }
        else{
               new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_tareasActionPerformed

    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCDActionPerformed
        mostrardatos1(code5,cAD.getText());
        limpiar();        
    }//GEN-LAST:event_botCDActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       ChatDocEst estd=new ChatDocEst(new java.awt.Frame(), true);
        estd.mostrardatos(code5);
        estd.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
       private String buscarIPDOC(String coddoc){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT ip FROM chatdoc WHERE cod='"+coddoc+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
            
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO esta Registrado y/o habilitado","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return cap;
    
    }
    
    
    
    private void limpiar(){
    cAD.setText("");
    cPM.setText("");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCD;
    private javax.swing.JButton botCM;
    private javax.swing.JButton botT;
    private javax.swing.JTextField cAD;
    private javax.swing.JTextField cPM;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabMat;
    private javax.swing.JMenuItem tareas;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
