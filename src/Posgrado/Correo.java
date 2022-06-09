/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Posgrado;

import Auxiliares.MensajeBeep;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import ziconos.Mensaje;

/**
 *
 * @author WjhonB
 */
public class Correo {
    private Mensaje icono1;
    public Correo(){
         icono1=new Mensaje();
    }
    public void mensaje(String correo1,String passw,String msn,String para,String asunto){
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo1, passw);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo1));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setText(msn);
            JOptionPane.showMessageDialog(null, "Denos un momento estamos enviando su mensaje","",JOptionPane.INFORMATION_MESSAGE,icono1);
            Transport.send(message);
            new MensajeBeep().showMessageDialog(null, "Su mensaje ha sido enviado","",JOptionPane.INFORMATION_MESSAGE,null);
            //System.out.println("Su mensaje ha sido enviado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion o Interno al enviar el mensaje, Error Revise su correo y contraseña, Le recomendamos usar: gmail o outlook si el problema persiste");
        }
    }
    
    
    
    public void mensajeHotmail(String correo1,String passw,String msn,String para,String asunto){
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.live.com");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo1, passw);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo1));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setText(msn);
            JOptionPane.showMessageDialog(null, "Denos un momento estamos enviando su mensaje","",JOptionPane.INFORMATION_MESSAGE,icono1);
            Transport.send(message);
            new MensajeBeep().showMessageDialog(null, "Su mensaje ha sido enviado","",JOptionPane.INFORMATION_MESSAGE,null);
            //System.out.println("Su mensaje ha sido enviado");
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion o Interno al enviar el mensaje, Error Revise su correo y contraseña, Le recomendamos usar: gmail o outlook si el problema persiste");
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void mensajeYahoo(String correo1,String passw,String msn,String para,String asunto){
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.mail.yahoo.com");
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo1, passw);
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correo1));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(para));
                    message.setSubject(asunto);
                    message.setText(msn);
                    JOptionPane.showMessageDialog(null, "Denos un momento estamos enviando su mensaje","",JOptionPane.INFORMATION_MESSAGE,icono1);
                    Transport.send(message);
                    new MensajeBeep().showMessageDialog(null, "Su mensaje ha sido enviado","",JOptionPane.INFORMATION_MESSAGE,null);
                    //System.out.println("Su mensaje ha sido enviado");
                }  catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error de Conexion o Interno al enviar el mensaje, Error Revise su correo y contraseña, Le recomendamos usar: gmail o outlook si el problema persiste");
                }
        
    }
    
    
    public void mensajeOutlook(String correo1,String passw,String msn,String para,String asunto){
    Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.live.com");
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(correo1, passw);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo1));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setText(msn);
            JOptionPane.showMessageDialog(null, "Denos un momento estamos enviando su mensaje","",JOptionPane.INFORMATION_MESSAGE,icono1);
            Transport.send(message);
            new MensajeBeep().showMessageDialog(null, "Su mensaje ha sido enviado","",JOptionPane.INFORMATION_MESSAGE,null);
            //System.out.println("Su mensaje ha sido enviado");
        }  catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error de Conexion o Interno al enviar el mensaje, Error Revise su correo y contraseña, Le recomendamos usar: gmail o outlook si el problema persiste");
        }
    }
    
    
    
}
