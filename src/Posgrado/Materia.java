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
public class Materia extends javax.swing.JFrame {

    private Advertencia icono;
    private BotConsul icono1;
    private BotTodos icono2;
    private BotInsertar icono3;
    private BotAct icono4;
    private Bien2 icono5;
    public Materia() {
        initComponents();
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
        btnCPG.setIcon(icono1);
        btnCPM.setIcon(icono1);
        btnCPA.setIcon(icono1);
        btnHABIL.setIcon(icono1);
        //btnT.setIcon(icono2);
        botNuevo.setIcon(icono3);
    }

    public void mostrardatos(){
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

       sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
 
    public void mostrardatos2(String ges){
    MiModelo modelo = new MiModelo();
    boolean auxi=true;
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

       sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.gestion like '%"+ges+"%' AND docente.activo="+auxi+" ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
       
    public void mostrardatos3(String ges,String mat){
    MiModelo modelo = new MiModelo();
    boolean auxi=true;
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

       sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.gestion like '%"+ges+"%' AND docente.materia like '%"+mat+"%' AND docente.activo="+auxi+" ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
    
    public void mostrardatos4(String ges,String apel){
    MiModelo modelo = new MiModelo();
    boolean auxi=true;
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

      sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.gestion like '%"+ges+"%' AND docente.apellidos like '%"+apel+"%' AND docente.activo="+auxi+" ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
    
    public void mostrardatos6(String ges,boolean habil){
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

       sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.gestion like '%"+ges+"%' AND docente.activo="+habil+" ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
          
    public void mostrardatos5(String ges,String coddoc,String mat,String mension){
    MiModelo modelo = new MiModelo();
    boolean auxi=true;
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

       sql="SELECT docente.gestion,docente.codigodoc,docente.grado,docente.apellidos,docente.nombres,docente.materia,docente.mension,docente.celular,docente.email,docente.activo,docente.tipo FROM docente WHERE docente.gestion='"+ges+"' AND docente.codigodoc='"+coddoc+"' AND docente.materia='"+mat+"' AND docente.mension='"+mension+"'ORDER BY docente.gestion desc,docente.apellidos asc,docente.nombres asc";
     
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
        Insertar = new javax.swing.JMenuItem();
        modificar = new javax.swing.JMenuItem();
        estudiante = new javax.swing.JMenuItem();
        tareas = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        botNuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cPG = new javax.swing.JTextField();
        btnCPG = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cPGM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cPM = new javax.swing.JTextField();
        btnCPM = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cPGA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cPA = new javax.swing.JTextField();
        btnCPA = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cPGH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        habil = new javax.swing.JComboBox<>();
        btnHABIL = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabMat = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        Insertar.setText("Registrar Docente");
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

        botNuevo.setBackground(new java.awt.Color(0, 0, 0));
        botNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botNuevo.setForeground(new java.awt.Color(255, 255, 255));
        botNuevo.setText("NUEVO");
        botNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botNuevoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Consultar por Gestion:");

        btnCPG.setBackground(new java.awt.Color(0, 0, 0));
        btnCPG.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCPG.setForeground(new java.awt.Color(255, 255, 255));
        btnCPG.setText("Consultar");
        btnCPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPGActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Consultar por Gestion");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("y Materia:");

        btnCPM.setBackground(new java.awt.Color(0, 0, 0));
        btnCPM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCPM.setForeground(new java.awt.Color(255, 255, 255));
        btnCPM.setText("Consultar");
        btnCPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPMActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Consultar por Gestion");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("y Apellidos:");

        btnCPA.setBackground(new java.awt.Color(0, 0, 0));
        btnCPA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCPA.setForeground(new java.awt.Color(255, 255, 255));
        btnCPA.setText("Consultar");
        btnCPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPAActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Consultar por Gestion");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("y Habilitado:");

        habil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        btnHABIL.setBackground(new java.awt.Color(0, 0, 0));
        btnHABIL.setForeground(new java.awt.Color(255, 255, 255));
        btnHABIL.setText("Consultar");
        btnHABIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHABILActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPGH))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPGA))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cPG, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCPG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cPGM, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cPA)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cPM))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCPA)
                                .addGap(0, 275, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(habil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHABIL)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCPM)
                    .addComponent(botNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cPG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCPG)
                    .addComponent(jLabel2)
                    .addComponent(cPGM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCPM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cPGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCPA))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(botNuevo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cPGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(habil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHABIL))))
                .addContainerGap(19, Short.MAX_VALUE))
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

        jMenu4.setText("Mensaje");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Enviar Mensaje");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botNuevoActionPerformed
       iM in=new iM(new java.awt.Frame(), true);
       in.setVisible(true);
      if(in.ges.getText().equals("")||in.cd.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos();
       }else{
            mostrardatos5(in.ges.getText(),in.cd.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
       }
    }//GEN-LAST:event_botNuevoActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
         int fila= tabMat.getSelectedRow();
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
        String aux11="";
        if(fila>=0){
            aux1=tabMat.getValueAt(fila, 0).toString();
            aux2=tabMat.getValueAt(fila, 1).toString();
                       sql= "select grado, apellidos, nombres from docente where gestion='"+tabMat.getValueAt(fila, 0).toString()+"' AND codigodoc='"+tabMat.getValueAt(fila, 1).toString()+"' AND materia='"+tabMat.getValueAt(fila, 3).toString()+"' AND mension='"+tabMat.getValueAt(fila, 4).toString()+"'";
            try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            aux3=rs.getString(1);
            aux4=rs.getString(2);
            aux5=rs.getString(3);
            }
            }
            catch (SQLException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            }
            aux6=tabMat.getValueAt(fila, 3).toString();
            aux7=tabMat.getValueAt(fila, 4).toString();
            aux8=tabMat.getValueAt(fila, 5).toString();
            aux9=tabMat.getValueAt(fila, 6).toString();
            aux10=tabMat.getValueAt(fila, 7).toString();
            aux11=tabMat.getValueAt(fila, 8).toString();
            mM in=new mM(new java.awt.Frame(), true);
            in.llenardatos(aux1,aux2,aux3,aux4,aux5,aux6,aux7,aux8,aux9,aux10,aux11);
            in.setVisible(true);
            if(in.ges.getText().equals("")||in.cd.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos();
            }else{
            mostrardatos5(in.ges.getText(),in.cd.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
            }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_modificarActionPerformed

    /*
    private void eliminarbutton(){
    
             int fila= tabMat.getSelectedRow();
        String sep = System.getProperty("line.separator");
        if(fila>= 0){
        getToolkit().beep();
        if (JOptionPane.showConfirmDialog(this, "Al Eliminar una Materia se eliminaran:"+sep+"Los Estudiantes dependientes de cada docente"+sep+"Las unidades Tematicas y Tareas o Actividades", "Eliminar",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
            PreparedStatement pst = cn.prepareStatement ("DELETE FROM docente WHERE gestion='"+tabMat.getValueAt(fila, 0).toString()+"' AND codigodoc='"+tabMat.getValueAt(fila, 1).toString()+"' AND materia='"+tabMat.getValueAt(fila, 3).toString()+"' AND mension='"+tabMat.getValueAt(fila, 4).toString()+"'");
            pst.executeUpdate();
            new MensajeBeep().showMessageDialog(this, "Operación correcta!!!","",JOptionPane.WARNING_MESSAGE,icono2);
            //mostrardatos5(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
            mostrardatos();
            } catch (Exception e) {
                new MensajeBeep().showMessageDialog(this, "Operación Incorrecta, NO SE REALIZO CAMBIOS","",JOptionPane.WARNING_MESSAGE,null);
            }
         }
        }
        else{
             new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }
*/
    
    private void estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudianteActionPerformed
        int fila= tabMat.getSelectedRow();
        //String sep = System.getProperty("line.separator");
        if(fila>= 0){
            Estudiante es=new Estudiante(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
            es.mostrardatos(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
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
                Tarea ta=new Tarea(tabMat.getValueAt(fila, 0).toString(),tabMat.getValueAt(fila, 1).toString(),tabMat.getValueAt(fila, 3).toString(),tabMat.getValueAt(fila, 4).toString());
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

    private void btnCPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPGActionPerformed
        mostrardatos2(cPG.getText());
        limpiar();
    }//GEN-LAST:event_btnCPGActionPerformed

    private void btnCPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPMActionPerformed
       mostrardatos3(cPGM.getText(),cPM.getText());
       limpiar();
    }//GEN-LAST:event_btnCPMActionPerformed

    private void btnCPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPAActionPerformed
        mostrardatos4(cPGA.getText(),cPA.getText());
        limpiar();
    }//GEN-LAST:event_btnCPAActionPerformed

    private void btnHABILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHABILActionPerformed
       if(habil.getSelectedItem().toString().equals("SI")){
        mostrardatos6(cPGH.getText(),true);
       limpiar();
       }else{
        mostrardatos6(cPGH.getText(),false);
       limpiar();
       }
    }//GEN-LAST:event_btnHABILActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        MensajeAdm1 men=new MensajeAdm1(new java.awt.Frame(), true);
        men.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        int fila= tabMat.getSelectedRow();
        String sql="";
        String aux1="";
        String aux2="";
        String aux3="";
        String aux4="";
        String aux5="";
        String aux6="";
        if(fila>=0){
            aux1=tabMat.getValueAt(fila, 0).toString();
            aux2=tabMat.getValueAt(fila, 4).toString();
            aux3=tabMat.getValueAt(fila, 5).toString();
            aux4=tabMat.getValueAt(fila, 6).toString();
            aux5=tabMat.getValueAt(fila, 7).toString();
            aux6=tabMat.getValueAt(fila, 8).toString();
            iM in=new iM(new java.awt.Frame(), true);
            in.llenardatos(aux1,aux2,aux3,aux4,aux5,aux6);
            in.setVisible(true);
            if(in.ges.getText().equals("")||in.cd.getText().equals("")||in.mat.getText().equals("")||(in.comboM.getSelectedItem().toString()).equals("")){
                mostrardatos();
            }else{
            mostrardatos5(in.ges.getText(),in.cd.getText(),in.mat.getText(),in.comboM.getSelectedItem().toString());
            }
        }
        else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    }//GEN-LAST:event_InsertarActionPerformed
private void limpiar(){
    cPG.setText("");
    cPM.setText("");
    cPGM.setText("");
    cPA.setText("");
    cPGA.setText("");
    cPGH.setText("");
    habil.setSelectedItem("SI");
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Insertar;
    private javax.swing.JButton botNuevo;
    private javax.swing.JButton btnCPA;
    private javax.swing.JButton btnCPG;
    private javax.swing.JButton btnCPM;
    private javax.swing.JButton btnHABIL;
    private javax.swing.JTextField cPA;
    private javax.swing.JTextField cPG;
    private javax.swing.JTextField cPGA;
    private javax.swing.JTextField cPGH;
    private javax.swing.JTextField cPGM;
    private javax.swing.JTextField cPM;
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JMenuItem estudiante;
    private javax.swing.JComboBox<String> habil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTable tabMat;
    private javax.swing.JMenuItem tareas;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
