/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Auxiliares.MyRen;
import Principal.Conexion;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien;


public class AEstudianteTarea extends javax.swing.JDialog {

    private Bien icono2;
    private Advertencia icono;
    private static String tarea5;
    private static String coddoc5;
    private static String ges5;
    private static String mat5;
    private static String men5;
    public AEstudianteTarea(java.awt.Frame parent, boolean modal,String tarea6,String coddoc6,String ges6,String mat6,String men6) {
        super(parent, modal);
        initComponents();
        tarea5=tarea6;
        coddoc5=coddoc6;
        ges5=ges6;
        mat5=mat6;
        men5=men6;
        this.setTitle("ESTUDIANTES QUE SUBIERON SU TAREA");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        icono =new Advertencia();
        icono2=new Bien();
        tabE.getTableHeader().setPreferredSize(new Dimension(20,30));
        tabE.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabE.setRowHeight(70);
        try{
       this.setIconImage(new ImageIcon(getClass().getResource("/zimagenes/carga.jpg")).getImage());
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
    }

    public void mostrardatos(String tarea,String cd,String ges,String mat,String men){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTION");
    modelo.addColumn("CODIGO");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("TAREA");
    modelo.addColumn("MATERIA");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA Y HORA<br>DE SUBIDA</html>");
    tabE.setModel(modelo);
    TableColumnModel columnModel = tabE.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(80);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(180);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(180);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(180);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(80);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(140);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    String sql="";

        sql="SELECT es.gestion,es.codigoest,est.apellidos,est.nombres,es.tareaotema,es.materia,es.mension,es.fecha FROM archivoest as es,archivodoc as doc,estudiante as est where es.tareaotema=doc.tareaotema AND es.codigodoc=doc.codigodoc AND es.gestion=doc.gestion AND es.materia=doc.materia AND es.mension=doc.mension AND es.codigoest=est.codigoest AND es.gestion=est.gestion AND es.materia=est.materia AND es.mension=est.mension AND es.tareaotema='"+tarea+"' AND es.codigodoc='"+cd+"' AND es.gestion='"+ges+"' AND es.materia='"+mat+"' AND es.mension='"+men+"' order by es.gestion desc,est.apellidos asc,est.nombres asc";
    
 
    Object []datos = new String [8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3)+" "+rs.getString(4);
                datos[3]=rs.getString(5);
                datos[4]=rs.getString(6);
                datos[5]=rs.getString(7);
                Date fecha1= rs.getDate(8);
                Time fecha2= rs.getTime(8);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[6]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                modelo.addRow(datos);
            }
            tabE.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AEstudianteTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
    public void mostrardatos2(String tarea,String cd,String ges,String mat,String men,String apel){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTION");
    modelo.addColumn("CODIGO");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("TAREA");
    modelo.addColumn("MATERIA");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA Y HORA<br>DE SUBIDA</html>");
    tabE.setModel(modelo);
    TableColumnModel columnModel = tabE.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(80);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(180);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(180);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(180);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(80);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(140);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    String sql="";

        sql="SELECT es.gestion,es.codigoest,est.apellidos,est.nombres,es.tareaotema,es.materia,es.mension,es.fecha FROM archivoest as es,archivodoc as doc,estudiante as est where es.tareaotema=doc.tareaotema AND es.codigodoc=doc.codigodoc AND es.gestion=doc.gestion AND es.materia=doc.materia AND es.mension=doc.mension AND es.codigoest=est.codigoest AND es.gestion=est.gestion AND es.materia=est.materia AND es.mension=est.mension AND es.tareaotema='"+tarea+"' AND es.codigodoc='"+cd+"' AND es.gestion='"+ges+"' AND es.materia='"+mat+"' AND es.mension='"+men+"' AND est.apellidos like '%"+apel+"%' order by es.gestion desc,est.apellidos asc,est.nombres asc";
    
 
    Object []datos = new String [8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3)+" "+rs.getString(4);
                datos[3]=rs.getString(5);
                datos[4]=rs.getString(6);
                datos[5]=rs.getString(7);
                Date fecha1= rs.getDate(8);
                Time fecha2= rs.getTime(8);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[6]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                modelo.addRow(datos);
            }
            tabE.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AEstudianteTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public void mostrardatos3(String tarea,String cd,String ges,String mat,String men,String codest){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTION");
    modelo.addColumn("CODIGO");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("TAREA");
    modelo.addColumn("MATERIA");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA Y HORA<br>DE SUBIDA</html>");
    tabE.setModel(modelo);
    TableColumnModel columnModel = tabE.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(80);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(180);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(180);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(180);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(80);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(140);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    String sql="";

         sql="SELECT es.gestion,es.codigoest,est.apellidos,est.nombres,es.tareaotema,es.materia,es.mension,es.fecha FROM archivoest as es,archivodoc as doc,estudiante as est where es.tareaotema=doc.tareaotema AND es.codigodoc=doc.codigodoc AND es.gestion=doc.gestion AND es.materia=doc.materia AND es.mension=doc.mension AND es.codigoest=est.codigoest AND es.gestion=est.gestion AND es.materia=est.materia AND es.mension=est.mension AND es.tareaotema='"+tarea+"' AND es.codigodoc='"+cd+"' AND es.gestion='"+ges+"' AND es.materia='"+mat+"' AND es.mension='"+men+"' AND es.codigoest like '%"+codest+"%' order by es.gestion desc,est.apellidos asc,est.nombres asc";
    
 
    Object []datos = new String [8];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3)+" "+rs.getString(4);
                datos[3]=rs.getString(5);
                datos[4]=rs.getString(6);
                datos[5]=rs.getString(7);
                Date fecha1= rs.getDate(8);
                Time fecha2= rs.getTime(8);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[6]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                modelo.addRow(datos);
            }
            tabE.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AEstudianteTarea.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
      private boolean comprobar(String tarea7,String codest7,String coddoc7,String ges7,String mat7,String men7){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT direccion FROM archivoest WHERE tareaotema='"+tarea7+"' AND codigoest='"+codest7+"' AND codigodoc='"+coddoc7+"' AND gestion='"+ges7+"' AND materia='"+mat7+"' AND mension='"+men7+"'";
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
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" NO se subio ningun archivo para esta tarea","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
                band4=false;
            }
            return band4;
     
     
     }
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cPA = new javax.swing.JTextField();
        botcPA = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cPC = new javax.swing.JTextField();
        btncPC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabE = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "ESTUDIANTES QUE SUBIERON SU TAREA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por Apellidos:");

        botcPA.setBackground(new java.awt.Color(0, 0, 0));
        botcPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botcPA.setForeground(new java.awt.Color(255, 255, 255));
        botcPA.setText("Consultar");
        botcPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botcPAActionPerformed(evt);
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consultar por Código del Estudiante:");

        btncPC.setBackground(new java.awt.Color(0, 0, 0));
        btncPC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncPC.setForeground(new java.awt.Color(255, 255, 255));
        btncPC.setText("Consultar");
        btncPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncPCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPC))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPA, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botcPA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncPC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addComponent(botT)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botcPA)
                    .addComponent(botT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncPC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botcPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botcPAActionPerformed
        mostrardatos2(tarea5,coddoc5,ges5,mat5,men5,cPA.getText());
        cPA.setText("");
        cPC.setText("");
    }//GEN-LAST:event_botcPAActionPerformed

    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
        mostrardatos(tarea5,coddoc5,ges5,mat5,men5);
        cPA.setText("");
        cPC.setText("");
    }//GEN-LAST:event_botTActionPerformed

    private void btncPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncPCActionPerformed
        mostrardatos3(tarea5,coddoc5,ges5,mat5,men5,cPC.getText());
        cPA.setText("");
        cPC.setText("");
    }//GEN-LAST:event_btncPCActionPerformed

    
     private String pedirArc(String tarea7,String codest7,String coddoc7,String ges7,String mat7,String men7){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="SELECT archivo FROM archivoest WHERE tareaotema='"+tarea7+"' AND codigoest='"+codest7+"' AND codigodoc='"+coddoc7+"' AND gestion='"+ges7+"' AND materia='"+mat7+"' AND mension='"+men7+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              
            }catch(Exception ex){
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+"NO se subio ningun archivo para esta tarea","Advertencia",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            return cap;
     
     
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botT;
    private javax.swing.JButton botcPA;
    private javax.swing.JButton btncPC;
    private javax.swing.JTextField cPA;
    private javax.swing.JTextField cPC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabE;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();

}
