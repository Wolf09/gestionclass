/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import Auxiliares.MiModelo;
import Principal.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import ziconos.Advertencia;
import ziconos.Bien;

/**
 *
 * @author WjhonB
 */
public class ChatDocEst extends javax.swing.JDialog {

    private Bien icono2;
    private Advertencia icono;
    public ChatDocEst(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
          this.setTitle("LISTA DE CHAT DE DOCENTES ACTIVOS");
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

    
    
     public void mostrardatos(String code){
         MiModelo modelo = new MiModelo();
         modelo.addColumn("ip");
        modelo.addColumn("DOCENTE");
        modelo.addColumn("ACTIVO");
        tabCh.setModel(modelo);
        TableColumnModel columnModel = tabCh.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(100);
        String sql="";

        sql="SELECT chatdoc.ip,docente.grado,docente.apellidos,docente.nombres,chatdoc.chat FROM docente,estudiante,chatdoc WHERE docente.gestion=estudiante.gestion AND docente.codigodoc=estudiante.codigodoc AND docente.materia=estudiante.materia AND docente.mension=estudiante.mension AND docente.codigodoc=chatdoc.cod AND estudiante.codigoest='"+code+"' ORDER BY docente.apellidos, docente.nombres";
     
        String []datos = new String [5];
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        
            while(rs.next()){
                datos[0]=rs.getString(1);      
                datos[1]=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
                datos[2]=rs.getString(5);
                modelo.addRow(datos);
            }
            tabCh.setModel(modelo);
        } catch (SQLException ex) {
            //Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
             new MensajeBeep().showMessageDialog(this, ex.getMessage(),"Fila",JOptionPane.WARNING_MESSAGE,null);
        }
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capturar = new javax.swing.JPopupMenu();
        unir = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCh = new javax.swing.JTable();

        unir.setText("UNIRSE");
        unir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirActionPerformed(evt);
            }
        });
        capturar.add(unir);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabCh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabCh.setComponentPopupMenu(capturar);
        jScrollPane1.setViewportView(tabCh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirActionPerformed
        int fila= tabCh.getSelectedRow();
        if(fila>= 0){
            String aux=tabCh.getValueAt(fila, 2).toString();
            if(aux.equals("NO")){
                new MensajeBeep().showMessageDialog(this, "El Docente No esta ACTIVO en este momento,intente mas Tarde","Activo",JOptionPane.WARNING_MESSAGE,null);
            }else{
             this.setVisible(false);
            ChatEst1 est=new ChatEst1(new java.awt.Frame(), true,tabCh.getValueAt(fila, 0).toString());
            est.setVisible(true);
            
            }
        }else{
        new MensajeBeep().showMessageDialog(this, "No selecciono una fila","Fila",JOptionPane.WARNING_MESSAGE,null);
        }
        
    }//GEN-LAST:event_unirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu capturar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabCh;
    private javax.swing.JMenuItem unir;
    // End of variables declaration//GEN-END:variables
Conexion cc = new Conexion();
Connection cn = cc.conexion();
}
