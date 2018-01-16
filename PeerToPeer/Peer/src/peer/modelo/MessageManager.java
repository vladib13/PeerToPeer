package peer.modelo;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vladimir
 */
public class MessageManager {
    private DataInputStream input;
    private DataOutputStream output;
    private Integer status;
    
    public int comunicarPuerto(Socket so, int puerto){
        try {
            input = new DataInputStream(so.getInputStream());
            output = new DataOutputStream(so.getOutputStream());
            output.writeInt(puerto);
            status = input.readInt();
            
        } catch (IOException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return status;
        }
    }
    public int informarNodos(Socket so, String hash, Boolean salir){
        try {
            input = new DataInputStream(so.getInputStream());
            output = new DataOutputStream(so.getOutputStream());
            if(salir==false){
                output.writeUTF("NEW NODO");
            }
            else if(salir==true){
                output.writeUTF("SALE NODO");
            }
            input.readUTF();
            output.writeUTF(hash);
            
        } catch (IOException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return status;
        }
    }
    public void ingresarAnillo(String ip, Socket so, Boolean salir){
        try {
            input = new DataInputStream(so.getInputStream());
            output = new DataOutputStream(so.getOutputStream());
            if(salir==false){
            output.writeUTF("CONECTAR AL ANILLO");
            }
            else if (salir==true){
                output.writeUTF("SALIR ANILLO");
            }
            input.readUTF();
            output.writeUTF(ip);
            System.out.println(input.readUTF());
            
        } catch (IOException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int handshake(Socket so){
        int port = 0;
        try {
            input = new DataInputStream(so.getInputStream());
            output = new DataOutputStream(so.getOutputStream());
            port = input.readInt();
            output.writeInt(1);
            
        } catch (IOException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return port;
        }
    }
}
