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
public class DMateria extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    private static String coddoc5;
    public DMateria(String coddoc6) {
        initComponents();
        coddoc5=coddoc6;
        this.setTitle("DOCENTES");
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
            modificarIPDOC(false);
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
        botCPG.setIcon(icono1);
        botCU.setIcon(icono1);
        botCM.setIcon(icono1);
        botT.setIcon(icono2);
    }

    public void mostrardatos(String cd){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("CELULAR");
    modelo.addColumn("EMAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("TIPO");
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
    columnModel.getColumn(4).setPreferredWidth(100);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setMaxWidth(0);
    columnModel.getColumn(5).setMinWidth(0);
    columnModel.getColumn(5).setPreferredWidth(0);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(100);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setPreferredWidth(100);
    columnModel.getColumn(8).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.codigodoc='"+cd+"' ORDER BY docente.gestion desc,docente.materia asc";
     
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
                datos[6]=rs.getString(9);
                if(rs.getBoolean(10)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }   
                datos[8]=rs.getString(11);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
       public void mostrardatos1(String cd,String ges){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("CELULAR");
    modelo.addColumn("EMAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("TIPO");
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
    columnModel.getColumn(4).setPreferredWidth(100);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setMaxWidth(0);
    columnModel.getColumn(5).setMinWidth(0);
    columnModel.getColumn(5).setPreferredWidth(0);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(100);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setPreferredWidth(100);
    columnModel.getColumn(8).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.codigodoc='"+cd+"' AND docente.gestion like '%"+ges+"%' ORDER BY docente.gestion desc,docente.materia asc";
     
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
                datos[6]=rs.getString(9);
                if(rs.getBoolean(10)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }   
                datos[8]=rs.getString(11);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
      
      
      
   public void mostrardatos2(String cd,String ges,String men){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("CELULAR");
    modelo.addColumn("EMAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("TIPO");
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
    columnModel.getColumn(4).setPreferredWidth(100);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setMaxWidth(0);
    columnModel.getColumn(5).setMinWidth(0);
    columnModel.getColumn(5).setPreferredWidth(0);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(100);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setPreferredWidth(100);
    columnModel.getColumn(8).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.codigodoc='"+cd+"' AND docente.gestion like '%"+ges+"%' AND docente.mension like '%"+men+"%' ORDER BY docente.gestion desc,docente.materia asc";
     
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
                datos[6]=rs.getString(9);
                if(rs.getBoolean(10)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }   
                datos[8]=rs.getString(11);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
       
    
    public void mostrardatos3(String cd,String mat){
    boolean hab=false;
    MiModelo modelo = new MiModelo();
    modelo.addColumn("GESTIÓN");
    modelo.addColumn("<html>CÓDIGO DE<br>DOCENTE</html>");
    modelo.addColumn("DOCENTE");
    modelo.addColumn("<html>MATERIA<br>O MODULO</html>");
    modelo.addColumn("MENSIÓN");
    modelo.addColumn("CELULAR");
    modelo.addColumn("EMAIL");
    modelo.addColumn("HABILITADO");
    modelo.addColumn("TIPO");
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
    columnModel.getColumn(4).setPreferredWidth(100);
    columnModel.getColumn(4).setCellRenderer(new MyRen());
    columnModel.getColumn(5).setMaxWidth(0);
    columnModel.getColumn(5).setMinWidth(0);
    columnModel.getColumn(5).setPreferredWidth(0);
    columnModel.getColumn(5).setCellRenderer(new MyRen());
    columnModel.getColumn(6).setPreferredWidth(100);
    columnModel.getColumn(6).setCellRenderer(new MyRen());
    columnModel.getColumn(7).setPreferredWidth(100);
    columnModel.getColumn(7).setCellRenderer(new MyRen());
    columnModel.getColumn(8).setPreferredWidth(100);
    columnModel.getColumn(8).setCellRenderer(new MyRen());
    String sql="";

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.codigodoc='"+cd+"' AND docente.materia like '%"+mat+"%' ORDER BY docente.gestion desc,docente.materia asc";
     
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
                datos[6]=rs.getString(9);
                if(rs.getBoolean(10)==true){
                    datos[7]="SI";
                }else{
                    datos[7]="NO";
                }   
                datos[8]=rs.getString(11);
                modelo.addRow(datos);
            }
            tabMat.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
         
         
      
      private Date pedirfecha3(String coddoc,String ges,String nom,String cur,String mat){
        
        String sql="SELECT fechaf FROM docente WHERE coddoc='"+coddoc+"' AND gestion='"+ges+"' AND nominacion='"+nom+"' AND curso='"+cur+"' AND materia='"+mat+"' ORDER BY docente.apellidos, docente.nombres";
     Date fecha2=null;
     SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
    String aux="";
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                Date fecha1= rs.getDate(1);
                Calendar calendario = GregorianCalendar.getInstance();
                calendario.setTime(fecha1);
                calendario.add(Calendar.DAY_OF_YEAR,10);
                aux=formato.format(calendario.getTime());
            }
           
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
         try {

            fecha2 = formato.parse(aux);

        } catch (ParseException e) {
            e.printStackTrace();
        }
            return fecha2;


}
      
      
      
      
        private void eliminarEv(String ges,String coddoc,String nom,String cur,String mat){

        String sep = System.getProperty("line.separator");
        String aux="";
                Calendar calendario = GregorianCalendar.getInstance();
                Date fecha1 = calendario.getTime();
                SimpleDateFormat formato= new SimpleDateFormat("dd-MM-yyyy");
                aux=formato.format(fecha1);
                try {

                    fecha1 = formato.parse(aux);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date fecha2=pedirfecha3(coddoc,ges,nom,cur,mat);

                          if(fecha1.compareTo(fecha2)>=0){
                                try {
                                    PreparedStatement pst = cn.prepareStatement ("DELETE FROM evaluardoc WHERE gestion='"+ges+"' AND coddoc='"+coddoc+"' AND nominacion='"+nom+"' AND curso='"+cur+"' AND materia='"+mat+"'");
                                    pst.executeUpdate();
                                    System.out.println("Nota de los estudiantes a docentes eliminado");
                                    
                                    } catch (Exception e) {
                                        System.out.println("Error al eliminar Nota de los estudiantes a docentes");
                                    }
                            }else{
                                System.out.println("Fecha fuera de tiempo al eliminar Nota de los estudiantes a docentes");

                            }      

     
     }
      
      
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        estudiante = new javax.swing.JMenuItem();
        tareas = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botCM = new javax.swing.JButton();
        botT = new javax.swing.JButton();
        cPMA = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cPGM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cPM = new javax.swing.JTextField();
        botCU = new javax.swing.JButton();
        cPG = new javax.swing.JTextField();
        botCPG = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabMat = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        estudiante.setText("Estudiantes");
        estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudianteActionPerformed(evt);
            }
        });
        capturar.add(estudiante);

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
        jLabel1.setText("Consultar por Gestión:");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consultar por Gestión");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("y Mensión:");

        botCU.setBackground(new java.awt.Color(0, 0, 0));
        botCU.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCU.setForeground(new java.awt.Color(255, 255, 255));
        botCU.setText("Consultar");
        botCU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCUActionPerformed(evt);
            }
        });

        botCPG.setBackground(new java.awt.Color(0, 0, 0));
        botCPG.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botCPG.setForeground(new java.awt.Color(255, 255, 255));
        botCPG.setText("Consultar");
        botCPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botCPGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPMA))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPG, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botCPG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPGM, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPM, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botCM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botCU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cPGM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCU)
                    .addComponent(cPG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botCPG)
                    .addComponent(botT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(botCM)
                    .addComponent(cPMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jMenuItem1.setText("Chat con mis Estudiantes");
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
       mostrardatos3(coddoc5,cPMA.getText());
        limpiar();
    }//GEN-LAST:event_botCMActionPerformed

    private void botTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTActionPerformed
       mostrardatos(coddoc5);
    }//GEN-LAST:event_botTActionPerformed

    private void estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudianteActionPerformed
        int fila= tabMat.getSelectedRow();
        //String sep = System.getProperty("line.separator");
        if(fila>= 0){
            DEstudiante es=new DEstudiante(tabMat.getValueAt(fila, 0).toString(),coddoc5,tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
            es.mostrardatos(tabMat.getValueAt(fila, 0).toString(),coddoc5,tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
            es.setVisible(true);
            this.setVisible(false);
        } else{
               new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_estudianteActionPerformed

    private void tareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tareasActionPerformed
        int fila= tabMat.getSelectedRow();
        //String sep = System.getProperty("line.separator");
        if(fila>= 0){
                 DTarea ta=new DTarea(tabMat.getValueAt(fila, 0).toString(),coddoc5,tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
                ta.mostrardatos(tabMat.getValueAt(fila, 0).toString(),coddoc5,tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
                ta.setVisible(true);
                this.setVisible(false);
        }
        else{
               new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_tareasActionPerformed

    private void modificarIPDOC(boolean auxi){
             try {
            PreparedStatement pst = cn.prepareStatement ("UPDATE chatdoc SET chat="+auxi+" WHERE cod='"+coddoc5+"'");
            pst.executeUpdate();
            //new MensajeBeep().showMessageDialog(this, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            //this.setVisible(false);
            } catch (Exception ex) {
            //new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, Gestión,curso,nominación Duplicados"+sep+"O la cantidad de letras sobrepasa lo permitido para un campo NO SE REALIZO CAMBIOS!!!","",JOptionPane.WARNING_MESSAGE,null);
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error al modificar chat docente",1);
            }
    
    
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        modificarIPDOC(false);
        new Control().cerrarApp();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void botCUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCUActionPerformed
        mostrardatos2(coddoc5,cPGM.getText(),cPM.getText());
        limpiar();        
    }//GEN-LAST:event_botCUActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String aux=buscarIPDOC(coddoc5);
        ChatDoc1 ch=new ChatDoc1(new java.awt.Frame(), true,aux);
        ch.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void botCPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botCPGActionPerformed
        mostrardatos1(coddoc5,cPG.getText());
        limpiar();
    }//GEN-LAST:event_botCPGActionPerformed
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
    cPGM.setText("");
    cPMA.setText("");
    cPG.setText("");
    cPM.setText("");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botCM;
    private javax.swing.JButton botCPG;
    private javax.swing.JButton botCU;
    private javax.swing.JButton botT;
    private javax.swing.JTextField cPG;
    private javax.swing.JTextField cPGM;
    private javax.swing.JTextField cPM;
    private javax.swing.JTextField cPMA;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JMenuItem estudiante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
