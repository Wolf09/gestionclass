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
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien2;
import ziconos.BotAct;
import ziconos.BotConsul;
import ziconos.BotInsertar;
import ziconos.BotTodos;
import ziconos.Buscar;

/**
 *
 * @author WjhonB
 */
public class ATarea extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private Buscar icono6;
    private static String ges5;
    private static String coddoc5;
    private static String mat5;
    private static String men5;
    private boolean band7;
    public ATarea(String ges6,String coddoc6,String mat6,String men6) {
        initComponents();
        ges5=ges6;
        coddoc5=coddoc6;
        mat5=mat6;
        men5=men6;
        band7=true;
        this.setTitle("TAREAS");
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setLocation(0, 0);
        this.setMinimumSize(new Dimension(ancho-20, alto-50));
       this.setMaximumSize(new Dimension(ancho-10, alto-20));
        tabAct.getTableHeader().setPreferredSize(new Dimension(20,30));
        tabAct.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabAct.setRowHeight(100);
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
        icono6=new Buscar();
        botAct.setIcon(icono1);
        botT.setIcon(icono2);
        btnFS.setIcon(icono1);
    }

    public void mostrardatos(String ges6,String coddoc6,String mat6,String men6){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("TAREA");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA DE<br>SUBIDA</html>");
    modelo.addColumn("OBSERVACIÓN");
    tabAct.setModel(modelo);
    TableColumnModel columnModel = tabAct.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(300);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(140);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(300);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    String sql="";

        sql="SELECT archivodoc.tareaotema,archivodoc.materia,archivodoc.mension,archivodoc.fecha,archivodoc.mensaje FROM archivodoc WHERE archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
     
    Object []datos = new Object [6];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3);
                Date fecha1= rs.getDate(4);
                Time fecha2= rs.getTime(4);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[3]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                datos[4]=rs.getString(5);
                modelo.addRow(datos);
            }
            tabAct.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
       public void mostrardatos1(String ges6,String coddoc6,String mat6,String men6,String tarea){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("TAREA");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA DE<br>SUBIDA</html>");
    modelo.addColumn("OBSERVACIÓN");
    tabAct.setModel(modelo);
    TableColumnModel columnModel = tabAct.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(300);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(140);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(300);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    String sql="";

        sql="SELECT archivodoc.tareaotema,archivodoc.materia,archivodoc.mension,archivodoc.fecha,archivodoc.mensaje FROM archivodoc WHERE archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' AND archivodoc.tareaotema like '%"+tarea+"%'ORDER BY archivodoc.fecha desc";
     
    Object []datos = new Object [6];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3);
                Date fecha1= rs.getDate(4);
                Time fecha2= rs.getTime(4);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[3]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                datos[4]=rs.getString(5);
                modelo.addRow(datos);
            }
            tabAct.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      
     public void mostrardatos2(String ges6,String coddoc6,String mat6,String men6,String fecha){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("TAREA");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA DE<br>SUBIDA</html>");
    modelo.addColumn("OBSERVACIÓN");
    tabAct.setModel(modelo);
    TableColumnModel columnModel = tabAct.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(300);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(140);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(300);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    String sql="";

          sql="SELECT archivodoc.tareaotema,archivodoc.materia,archivodoc.mension,archivodoc.fecha,archivodoc.mensaje FROM archivodoc WHERE archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' AND archivodoc.fecha>='"+fecha+"' ORDER BY archivodoc.fecha desc";
     
    Object []datos = new Object [6];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3);
                Date fecha1= rs.getDate(4);
                Time fecha2= rs.getTime(4);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[3]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                datos[4]=rs.getString(5);
                modelo.addRow(datos);
            }
            tabAct.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
         
          
    public void mostrardatos3(String tarea6,String ges6,String coddoc6,String mat6,String men6){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("TAREA");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("<html>FECHA DE<br>SUBIDA</html>");
    modelo.addColumn("OBSERVACIÓN");
    tabAct.setModel(modelo);
    TableColumnModel columnModel = tabAct.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(300);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(140);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(300);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    String sql="";

         sql="SELECT archivodoc.tareaotema,archivodoc.materia,archivodoc.mension,archivodoc.fecha,archivodoc.mensaje FROM archivodoc WHERE archivodoc.tareaotema='"+tarea6+"' AND archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
     
    Object []datos = new Object [6];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);      
                datos[2]=rs.getString(3);
                Date fecha1= rs.getDate(4);
                Time fecha2= rs.getTime(4);
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat formato2= new SimpleDateFormat("HH:mm:ss");
                datos[3]=formato.format(fecha1)+"  "+formato2.format(fecha2);
                datos[4]=rs.getString(5);
                modelo.addRow(datos);
            }
            tabAct.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        estud = new javax.swing.JMenuItem();
        infoarch = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAct = new javax.swing.JTextField();
        botAct = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fechaS = new com.toedter.calendar.JDateChooser();
        btnFS = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabAct = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        estud.setText("Estudiantes que subieron su Tarea");
        estud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudActionPerformed(evt);
            }
        });
        capturar.add(estud);

        infoarch.setText("Informacion del Archivo");
        infoarch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoarchActionPerformed(evt);
            }
        });
        capturar.add(infoarch);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 123));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "TAREAS O ACTIVIDADES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por Tarea:");

        botAct.setBackground(new java.awt.Color(0, 0, 0));
        botAct.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botAct.setForeground(new java.awt.Color(255, 255, 255));
        botAct.setText("Consultar");
        botAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botActActionPerformed(evt);
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
        jLabel2.setText("Consultar por Fecha de subida:");

        btnFS.setBackground(new java.awt.Color(0, 0, 0));
        btnFS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnFS.setForeground(new java.awt.Color(255, 255, 255));
        btnFS.setText("Consultar");
        btnFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAct, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botAct)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                        .addComponent(botT))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaS, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFS)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botAct)
                    .addComponent(botT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFS)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(fechaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabAct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabAct.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabAct);

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Atras");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE))
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

    private void botActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botActActionPerformed
        mostrardatos1(ges5,coddoc5,mat5,men5,txtAct.getText());
        limpiar();
    }//GEN-LAST:event_botActActionPerformed

    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
       mostrardatos(ges5,coddoc5,mat5,men5);
       limpiar();
    }//GEN-LAST:event_botTActionPerformed

     private void agregarArchivo(String tarea6,String direc6,String archi6,String coddoc6,String ges6,String mat6,String men6){
         Calendar calendario = GregorianCalendar.getInstance();
        Date fecha1 = calendario.getTime();
          SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sep = System.getProperty("line.separator");
        String sql="select archivodoc.direccion from archivodoc WHERE archivodoc.tareaotema='"+tarea6+"' AND archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
        String auxi1="";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
              while(rs.next())
               {
                auxi1=rs.getString(1);
               }
            if(auxi1.equals("")){
                  try {
                    PreparedStatement pst = cn.prepareStatement("UPDATE archivodoc SET direccion='"+direc6+"',archivo='"+archi6+"' WHERE tareaotema='"+tarea6+"' AND codigodoc='"+coddoc6+"' AND gestion='"+ges6+"' AND materia='"+mat6+"' AND mension='"+men6+"'");
                    pst.executeUpdate();
                    //new MensajeBeep().showMessageDialog(null, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
                } catch (Exception ex) {
                    //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Ocurrio un problema interno al subir el archivo","",JOptionPane.WARNING_MESSAGE,null);
                    //JOptionPane.showMessageDialog(this,ex.getMessage());
                    Logger.getLogger(ATarea.class.getName()).log(Level.SEVERE, null, ex);
                    band7=false;
                }
            }else{
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted YA subio un archivo para esta tarea","",JOptionPane.WARNING_MESSAGE,null);
                band7=false;
            }
        }catch(Exception ex1){
            new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Ocurrio un problema interno al subir el archivo","",JOptionPane.WARNING_MESSAGE,null);
             JOptionPane.showMessageDialog(this,ex1.getMessage());
             //Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex1);
             band7=false;
        }
      

    }
    
    
     private boolean comprobar(String tarea6,String coddoc6,String ges6,String mat6,String men6){
        String sep = System.getProperty("line.separator");
         String cap="";
        boolean band4=false;
        String sql="select archivodoc.direccion from archivodoc WHERE archivodoc.tareaotema='"+tarea6+"' AND archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
        String auxi1="";
            try {
                Statement st = cn.createStatement();
               ResultSet rs = st.executeQuery(sql);
               
               while(rs.next())
               {
                auxi1=rs.getString(1);
               }
                 
                if(auxi1.equals("")){
                    new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO subio ningun archivo para esta tarea","",JOptionPane.WARNING_MESSAGE,null);
                    band4=false;
                }else{
                    band4=true;
                }
            }catch(Exception ex){
                //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Error interno al descargar el archivo","",JOptionPane.WARNING_MESSAGE,null);
                JOptionPane.showMessageDialog(this,ex.getMessage());
                Logger.getLogger(ATarea.class.getName()).log(Level.SEVERE, null, ex);
                band4=false;
            }
            return band4;
     
     
     }
     
     
        private String pedirArc(String tarea6,String coddoc6,String ges6,String mat6,String men6){
        String sep = System.getProperty("line.separator");
         String cap="";
        String sql="SELECT archivodoc.archivo from archivodoc WHERE archivodoc.tareaotema='"+tarea6+"' AND archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              
            }catch(Exception ex){
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO subio ningun archivo para esta tarea","Advertencia",JOptionPane.WARNING_MESSAGE,null);
            
            }
            return cap;
     
     
     }
      private String pedirDir(String tarea6,String coddoc6,String ges6,String mat6,String men6){
        String sep = System.getProperty("line.separator");
         String cap="";
        String sql="SELECT archivodoc.direccion from archivodoc WHERE archivodoc.tareaotema='"+tarea6+"' AND archivodoc.codigodoc='"+coddoc6+"' AND archivodoc.gestion='"+ges6+"' AND archivodoc.materia='"+mat6+"' AND archivodoc.mension='"+men6+"' ORDER BY archivodoc.fecha desc";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
             while(rs.next())
               {
                cap=rs.getString(1);
               }
              
            }catch(Exception ex){
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta"+sep+" Usted NO subio ningun archivo para esta tarea","Advertencia",JOptionPane.WARNING_MESSAGE,null);
            
            }
            return cap;
     
     
     }
     
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        AMateria ma=new AMateria();
        ma.mostrardatos5(ges5,coddoc5,mat5,men5);
        ma.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void estudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudActionPerformed
        int fila=tabAct.getSelectedRow();
        if(fila>=0){
                AEstudianteTarea es=new AEstudianteTarea(new java.awt.Frame(), true,tabAct.getValueAt(fila, 0).toString(),coddoc5,ges5,mat5,men5);
                es.mostrardatos(tabAct.getValueAt(fila, 0).toString(),coddoc5,ges5,mat5,men5);
                es.setVisible(true);
       }else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_estudActionPerformed

    private void infoarchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoarchActionPerformed
          int fila=tabAct.getSelectedRow();
          String aux1="";
          String aux2="";
        if(fila>=0){
                InformacionArchi ar=new InformacionArchi(new java.awt.Frame(), true);
                aux1=pedirArc(tabAct.getValueAt(fila, 0).toString(),coddoc5,ges5,mat5,men5);
                aux2=pedirDir(tabAct.getValueAt(fila, 0).toString(),coddoc5,ges5,mat5,men5);  
                JOptionPane.showMessageDialog(null, "Estamos buscando su documento, esto puede llevar unos minutos","BUSCANDO..",JOptionPane.INFORMATION_MESSAGE,icono6);
                ar.mostrardatos( aux2,aux1);
                //ar.setVisible(true);
                
        }else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_infoarchActionPerformed

    private void btnFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFSActionPerformed

        Date fecha1= fechaS.getDate();
        if(fecha1 ==null){
            JOptionPane.showMessageDialog(null, "Error: fecha vacia");
        }else{
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        mostrardatos2(ges5,coddoc5,mat5,men5,formato.format(fecha1));
        
        }
        limpiar();
    }//GEN-LAST:event_btnFSActionPerformed
private void limpiar(){
    txtAct.setText("");
}
/*
public static void main(String args[]){
    String sep = System.getProperty("line.separator");
        Calendar calendario = GregorianCalendar.getInstance();
        //calendario.add(Calendar.DAY_OF_YEAR,1);
        Date fecha1 = calendario.getTime();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
        JOptionPane.showMessageDialog(null, formato.format(fecha1));
}
*/


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botAct;
    private javax.swing.JButton botT;
    private javax.swing.JButton btnFS;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JMenuItem estud;
    private com.toedter.calendar.JDateChooser fechaS;
    private javax.swing.JMenuItem infoarch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabAct;
    private javax.swing.JTextField txtAct;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
