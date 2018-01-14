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
