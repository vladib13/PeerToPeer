package peer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author vladimir
 */
public class Peer {

   
    public static void main(String[] args) {
        try{
        ServerSocket sc = new ServerSocket(5000); 
        int s = new String("192.168.1.100").hashCode();
        
        System.out.println(String.valueOf(s));
        
        }
        catch(ConnectException e){
            
        }
        catch(IOException e){
            
        }
    }
}
