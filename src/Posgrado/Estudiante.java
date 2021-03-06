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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Estudiante extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private static String ges5;
    private static String coddoc5;
    private static String mat5;
    private static String mension5;
    public Estudiante(String ges6,String coddoc6,String mat6,String men6) {
        initComponents();
        ges5=ges6;
        coddoc5=coddoc6;
        mat5=mat6;
        mension5=men6;
        this.setTitle("MIS ESTUDIANTES");
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
       int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
       this.setLocation(0, 0);
        this.setMinimumSize(new Dimension(ancho-20, alto-50));
       this.setMaximumSize(new Dimension(ancho-10, alto-20));
        tabEst.getTableHeader().setPreferredSize(new Dimension(20,30));
        tabEst.getTableHeader().setFont(new Font("Lucida Grande", 1, 10));
        tabEst.setRowHeight(100);
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
        botCAD.setIcon(icono1);
        botCNG.setIcon(icono1);
        botT.setIcon(icono2);
        botNuevo.setIcon(icono3);
    }

    public void mostrardatos(String ges,String coddoc,String mat,String men){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTI??N");
    modelo.addColumn("<html>C??DIGO DE<br>ESTUDIANTE</html>");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSION");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("<html>C??DIGO DE<br>DOCENTE</html>");
    tabEst.setModel(modelo);
    TableColumnModel columnModel = tabEst.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(200);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(150);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(100);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(80);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setMaxWidth(0);
    columnModel.getColumn(8).setMinWidth(0);
    columnModel.getColumn(8).setPreferredWidth(0);
    columnModel.getColumn(8).setCellRenderer(new MyRen());  
    String sql="";

        sql="SELECT estudiante.gestion,estudiante.codigoest,estudiante.apellidos,estudiante.nombres,estudiante.materia,estudiante.mension,estudiante.celular,estudiante.email,estudiante.activo,estudiante.codigodoc FROM estudiante,docente WHERE estudiante.gestion=docente.gestion AND estudiante.codigodoc=docente.codigodoc AND estudiante.materia=docente.materia AND estudiante.mension=docente.mension AND estudiante.gestion='"+ges+"' AND estudiante.codigodoc='"+coddoc+"' AND estudiante.materia='"+mat+"' AND estudiante.mension='"+men+"' ORDER BY estudiante.apellidos, estudiante.nombres";
     
    Object []datos = new Object [11];
    
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
                datos[6]=rs.getString(8);
                if(rs.getBoolean(9)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }
                datos[8]=rs.getString(10);
                modelo.addRow(datos);
            }
            tabEst.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      public void mostrardatos1(String ges,String coddoc,String mat,String men,String apel){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTI??N");
    modelo.addColumn("<html>C??DIGO DE<br>ESTUDIANTE</html>");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSION");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("<html>C??DIGO DE<br>DOCENTE</html>");
    tabEst.setModel(modelo);
    TableColumnModel columnModel = tabEst.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(200);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(150);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(100);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(80);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setMaxWidth(0);
    columnModel.getColumn(8).setMinWidth(0);
    columnModel.getColumn(8).setPreferredWidth(0);
    columnModel.getColumn(8).setCellRenderer(new MyRen());  
    String sql="";

        sql="SELECT estudiante.gestion,estudiante.codigoest,estudiante.apellidos,estudiante.nombres,estudiante.materia,estudiante.mension,estudiante.celular,estudiante.email,estudiante.activo,estudiante.codigodoc FROM estudiante,docente WHERE estudiante.gestion=docente.gestion AND estudiante.codigodoc=docente.codigodoc AND estudiante.materia=docente.materia AND estudiante.mension=docente.mension AND estudiante.gestion='"+ges+"' AND estudiante.codigodoc='"+coddoc+"' AND estudiante.materia='"+mat+"' AND estudiante.mension='"+men+"' AND estudiante.apellidos like '%"+apel+"%'ORDER BY estudiante.apellidos, estudiante.nombres";
     
    Object []datos = new Object [11];
    
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
                datos[6]=rs.getString(8);
                if(rs.getBoolean(9)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }
                datos[8]=rs.getString(10);
                modelo.addRow(datos);
            }
            tabEst.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      
      
        public void mostrardatos2(String ges,String coddoc,String mat,String men,String codest){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTI??N");
    modelo.addColumn("<html>C??DIGO DE<br>ESTUDIANTE</html>");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSION");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("<html>C??DIGO DE<br>DOCENTE</html>");
    tabEst.setModel(modelo);
    TableColumnModel columnModel = tabEst.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(200);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(150);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(100);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(80);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setMaxWidth(0);
    columnModel.getColumn(8).setMinWidth(0);
    columnModel.getColumn(8).setPreferredWidth(0);
    columnModel.getColumn(8).setCellRenderer(new MyRen());  
    String sql="";

       sql="SELECT estudiante.gestion,estudiante.codigoest,estudiante.apellidos,estudiante.nombres,estudiante.materia,estudiante.mension,estudiante.celular,estudiante.email,estudiante.activo,estudiante.codigodoc FROM estudiante,docente WHERE estudiante.gestion=docente.gestion AND estudiante.codigodoc=docente.codigodoc AND estudiante.materia=docente.materia AND estudiante.mension=docente.mension AND estudiante.gestion='"+ges+"' AND estudiante.codigodoc='"+coddoc+"' AND estudiante.materia='"+mat+"' AND estudiante.mension='"+men+"' AND estudiante.codigoest like '%"+codest+"%'ORDER BY estudiante.apellidos, estudiante.nombres";
     
    Object []datos = new Object [11];
    
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
                datos[6]=rs.getString(8);
                if(rs.getBoolean(9)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }
                datos[8]=rs.getString(10);
                modelo.addRow(datos);
            }
            tabEst.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
         
          
     public void mostrardatos3(String ges,String codest,String mat,String men){
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTI??N");
    modelo.addColumn("<html>C??DIGO DE<br>ESTUDIANTE</html>");
    modelo.addColumn("ESTUDIANTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSION");
    modelo.addColumn("CELULAR");
    modelo.addColumn("E-MAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("<html>C??DIGO DE<br>DOCENTE</html>");
    tabEst.setModel(modelo);
    TableColumnModel columnModel = tabEst.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(80);
    columnModel.getColumn(0).setCellRenderer(new MyRen());
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(1).setCellRenderer(new MyRen());
    columnModel.getColumn(2).setPreferredWidth(200);
    columnModel.getColumn(2).setCellRenderer(new MyRen());
    columnModel.getColumn(3).setPreferredWidth(200);
    columnModel.getColumn(3).setCellRenderer(new MyRen());
    columnModel.getColumn(4).setPreferredWidth(150);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setPreferredWidth(100);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(80);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setMaxWidth(0);
    columnModel.getColumn(8).setMinWidth(0);
    columnModel.getColumn(8).setPreferredWidth(0);
    columnModel.getColumn(8).setCellRenderer(new MyRen());  
    String sql="";

        sql="SELECT estudiante.gestion,estudiante.codigoest,estudiante.apellidos,estudiante.nombres,estudiante.materia,estudiante.mension,estudiante.celular,estudiante.email,estudiante.activo,estudiante.codigodoc FROM estudiante,docente WHERE estudiante.gestion=docente.gestion AND estudiante.codigodoc=docente.codigodoc AND estudiante.materia=docente.materia AND estudiante.mension=docente.mension AND estudiante.gestion='"+ges+"' AND estudiante.codigoest='"+codest+"' AND estudiante.materia='"+mat+"' AND estudiante.mension='"+men+"'ORDER BY estudiante.apellidos, estudiante.nombres";
     
    Object []datos = new Object [11];
    
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
                datos[6]=rs.getString(8);
                if(rs.getBoolean(9)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }
                datos[8]=rs.getString(10);
                modelo.addRow(datos);
            }
            tabEst.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        Insertar = new javax.swing.JMenuItem();
        modificar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAD = new javax.swing.JTextField();
        botCAD = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        botCNG = new javax.swing.JButton();
        botNuevo = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        txtCE = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabEst = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        Insertar.setText("Registrar Estudiante");
        Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarActionPerformed(evt);
            }
        });
        capturar.add(Insertar);

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        capturar.add(modificar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 123));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3), "ESTUDIANTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por Apellidos del Estudiante:");

        botCAD.setBackground(new java.awt.Color(0, 0, 0));
        botCAD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCAD.setForeground(new java.awt.Color(255, 255, 255));
        botCAD.setText("Consultar");
        botCAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCADActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Consultar por el C??digo del estudiante:");

        botCNG.setBackground(new java.awt.Color(0, 0, 0));
        botCNG.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCNG.setForeground(new java.awt.Color(255, 255, 255));
        botCNG.setText("Consultar");
        botCNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCNGActionPerformed(evt);
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

        botT.setBackground(new java.awt.Color(0, 0, 0));
        botT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botT.setForeground(new java.awt.Color(255, 255, 255));
        botT.setText("TODOS");
        botT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAD, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botCAD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botT)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCE, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botCNG)
                    .addComponent(botNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCAD)
                    .addComponent(jLabel5)
                    .addComponent(txtCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCNG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botNuevo)
                    .addComponent(botT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabEst.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabEst.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabEst);

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
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

        jMenu2.setText("Mensaje");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Enviar Mensaje");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

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

    private void botCADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCADActionPerformed
        mostrardatos1(ges5,coddoc5,mat5,mension5,txtAD.getText());
        limpiar();
    }//GEN-LAST:event_botCADActionPerformed

    private void botCNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCNGActionPerformed
       mostrardatos2(ges5,coddoc5,mat5,mension5,txtCE.getText());
       limpiar();
    }//GEN-LAST:event_botCNGActionPerformed

    private void botNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNuevoActionPerformed
       iE in=new iE(new java.awt.Frame(), true);
       in.llenardatos(ges5,coddoc5,mat5,mension5);
       in.setVisible(true);
       if(in.ges.getText().equals("")||in.ce.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos(ges5,coddoc5,mat5,mension5);
       }else{
            mostrardatos3(in.ges.getText(),in.ce.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
        }
    }//GEN-LAST:event_botNuevoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
         int fila= tabEst.getSelectedRow();
        String sql="";
        String aux1="";
        String aux2="";
        String aux3="";
        String aux4="";
        String aux5="";
        String aux6="";
        String aux7="";
        String aux8="";
        String aux9="";
        String aux10="";
        if(fila>=0){
            aux1=tabEst.getValueAt(fila, 0).toString();
            aux2=tabEst.getValueAt(fila, 1).toString();
                       sql= "select apellidos, nombres from estudiante where codigoest='"+tabEst.getValueAt(fila, 1).toString()+"' AND gestion='"+tabEst.getValueAt(fila, 0).toString()+"' AND materia='"+tabEst.getValueAt(fila, 3).toString()+"' AND mension='"+tabEst.getValueAt(fila, 4).toString()+"'";
            try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            aux3=rs.getString(1);
            aux4=rs.getString(2);
            }
            }
            catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux5=tabEst.getValueAt(fila, 3).toString();
            aux6=tabEst.getValueAt(fila, 4).toString();
            aux7=tabEst.getValueAt(fila, 5).toString();
            aux8=tabEst.getValueAt(fila, 6).toString();
            aux9=tabEst.getValueAt(fila, 7).toString();
            aux10=tabEst.getValueAt(fila, 8).toString();
            mE in=new mE(new java.awt.Frame(), true);
            in.llenardatos(aux1,aux2,aux3,aux4,aux5,aux6,aux7,aux8,aux9,aux10);
            in.setVisible(true);
            if(in.ges.getText().equals("")||in.ce.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos(ges5,coddoc5,mat5,mension5);
            }else{
            mostrardatos3(in.ges.getText(),in.ce.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
            }
            
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_modificarActionPerformed

    /*
    private void eliminaerEst(){
           int fila= tabEst.getSelectedRow();
        String sep = System.getProperty("line.separator");
        if(fila>= 0){
        getToolkit().beep();
        if (JOptionPane.showConfirmDialog(this, "Se eliminara el Estudiante", "Eliminar",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            PreparedStatement pst = cn.prepareStatement ("DELETE FROM estudiante WHERE codigoest='"+tabEst.getValueAt(fila, 1).toString()+"' AND gestion='"+tabEst.getValueAt(fila, 0).toString()+"' AND materia='"+tabEst.getValueAt(fila, 3).toString()+"' AND mension='"+tabEst.getValueAt(fila, 4).toString()+"'");
            pst.executeUpdate();
            new MensajeBeep().showMessageDialog(this, "Operaci??n correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
             mostrardatos3(tabEst.getValueAt(fila, 0).toString(),tabEst.getValueAt(fila, 1).toString(),tabEst.getValueAt(fila, 3).toString(),tabEst.getValueAt(fila, 4).toString());
            } catch (Exception e) {
                new MensajeBeep().showMessageDialog(this, "Operaci??n Incorrecta, NO SE REALIZO CAMBIOS","",JOptionPane.WARNING_MESSAGE,null);
            }
         }
        }
        else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }
    */
    
    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
       mostrardatos(ges5,coddoc5,mat5,mension5);
       limpiar();
    }//GEN-LAST:event_botTActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Materia un=new Materia();
        un.mostrardatos5(ges5, coddoc5, mat5, mension5);
        un.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane.showMessageDialog(this, "Se enviara un mensaje a TODOS los estudiantes de ESTA materia","Mensaje",JOptionPane.INFORMATION_MESSAGE,null);
        MensajeDocente men=new MensajeDocente(new java.awt.Frame(), true,ges5,coddoc5,mat5,mension5);
        men.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
         int fila= tabEst.getSelectedRow();
        String sql="";
        String aux1="";
        String aux2="";
        String aux3="";
        String aux4="";
        String aux5="";
        if(fila>=0){
            aux1=tabEst.getValueAt(fila, 0).toString();
            aux2=tabEst.getValueAt(fila, 3).toString();
            aux3=tabEst.getValueAt(fila, 4).toString();
            aux4=tabEst.getValueAt(fila, 7).toString();
            aux5=tabEst.getValueAt(fila, 8).toString();
            iE in=new iE(new java.awt.Frame(), true);
            in.llenardatos2(aux1,aux2,aux3,aux4,aux5);
            in.setVisible(true);
            if(in.ges.getText().equals("")||in.ce.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos(ges5,coddoc5,mat5,mension5);
            }else{
            mostrardatos3(in.ges.getText(),in.ce.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
            }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_InsertarActionPerformed
private void limpiar(){
    txtAD.setText("");
    txtCE.setText("");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Insertar;
    private javax.swing.JButton botCAD;
    private javax.swing.JButton botCNG;
    private javax.swing.JButton botNuevo;
    private javax.swing.JButton botT;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTable tabEst;
    private javax.swing.JTextField txtAD;
    private javax.swing.JTextField txtCE;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
