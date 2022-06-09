/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author WjhonB
 */
public class MCliente extends Thread{
    private int port;
    private String url;
    private Socket s;
    private boolean bConectado;
    ChatDoc1 ventana;
    private String nick;
     public MCliente(int port, String url, String nick, ChatDoc1 ventana) {
        this.port=port;
        this.url=url;
        this.ventana=ventana;
        this.nick=nick;
    }
     
     public void run(){
        try{
            s=new Socket(url, port);
            DataInputStream dis=new DataInputStream(s.getInputStream());
            enviarTrama(1, nick);
            bConectado=true;
            while(bConectado){
                int nCodigo =dis.readInt();
                String sTrama=dis.readUTF();
                switch(nCodigo){
                    case 1:
                        ventana.nuevaPersona(sTrama);
                        JOptionPane.showMessageDialog(ventana, "Un Nuevo Usuario Conectado, Verifique su Lista de Contactos Online");
                        break;
                    case 2:
                        ventana.mensajeRecibido(sTrama);
                        
                        break;
                    case 3:
                        try{
                            int nPos = Integer.parseInt(sTrama);
                            ventana.borrarPersona(nPos);
                        }catch(Exception e2){
                        }
                        break;
                }
            }
            //JOptionPane.showMessageDialog(ventana, "Se ha podido conectar");
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana, "No se pudo establecer la conexión, Habilite el Puerto para Admitir la Conexión");
        }
    }
     
     
     
      public void enviarMensaje(String sMensaje){
        enviarTrama(2, sMensaje);
    }
    
    public void enviarTrama(int nCodigo, String sTrama){
        try{
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeInt(nCodigo);
            dos.writeUTF(sTrama);
        }catch(Exception e){
            JOptionPane.showMessageDialog(ventana, "No se pudo enviar el mensaje");
        }
        
    }
     
     
    
}
