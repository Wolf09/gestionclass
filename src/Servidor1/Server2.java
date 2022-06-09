/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor1;

/**
 *
 * @author WjhonB
 */
public class Server2 {
      private MServidor servidor = null;
      public Server2(){}
    public void ejecutar(){
        if (servidor==null){
            int port = 8101;
            servidor = new MServidor(null, port);
            servidor.start();
        }
    
    
    }
}
