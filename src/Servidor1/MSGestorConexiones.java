/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor1;

import java.util.ArrayList;

/**
 *
 * @author WjhonB
 */
public class MSGestorConexiones {
     private static MSGestorConexiones singleton=new MSGestorConexiones();
    private ArrayList<MSConexion> conexiones = new ArrayList<MSConexion>();
    
    
    public  static MSGestorConexiones getInstance(){
        return singleton;
    }
    
     public void enviarTrama(int nCodigo, String sTrama){
        for (MSConexion ms:conexiones){
            ms.enviarTrama(nCodigo, sTrama);
        }
    }
    
    public void conectaNuevo(MSConexion nuevo){
        for (MSConexion ms:conexiones){
            nuevo.enviarTrama(1, ms.getNick());
        }
        conexiones.add(nuevo);
    }
    
    public void desconecta(MSConexion eliminar){
        int nPos=-1;
        for (int n=0;n<conexiones.size();n++){
            if (conexiones.get(n)==eliminar){
                nPos=n;
            }
        }
        if (nPos!=-1){
            for (int n=0;n<conexiones.size();n++){
                if (n!=nPos){
                    conexiones.get(n).enviarTrama(3, ""+nPos);
                }
            }
            conexiones.remove(nPos);
        }
    }    
}
