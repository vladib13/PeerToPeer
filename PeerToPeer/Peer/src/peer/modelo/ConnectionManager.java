package peer.modelo;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vladimir
 */
public class ConnectionManager {
    
    public static final int SERVER = 5000;
    private ServerSocket servidor;
    private Socket socketServidor;
    private Socket cliente;
    private MessageManager mensajero;
    public ConnectionManager() throws IOException{
        servidor = new ServerSocket(SERVER);
        socketServidor = new Socket();
        mensajero = new MessageManager();
    }
    public ConnectionManager(InetAddress serverAddress) throws IOException{
        cliente = new Socket(serverAddress,SERVER);
        mensajero = new MessageManager();
        connectClient(serverAddress);
    }
    public void serve(){
        try { 
            while(true){
                socketServidor = servidor.accept();
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Primera conexion puerto 5000");
                int newPort = aleatorio(59001,65000);
                currentlyServe(newPort);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    
    public void connectClient(InetAddress serverAddress){
        Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Primera conexion puerto 5000");
        int puerto = mensajero.handshake(cliente);
        try {
            cliente = new Socket(serverAddress,puerto);
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Segunda conexion puerto ".concat(""+puerto));
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void currentlyServe(int port){
        try {
            final ServerSocket sc = new ServerSocket(port);
            Runnable newConnection = new Runnable() {
                public void run() { 	
                        try {
                            Socket so = sc.accept();
                        } catch (Exception e) {
                                // TODO Auto-generated catch block
                                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, e);
                        }

                }
            };
            Thread connection = new Thread(newConnection);
            connection.start();
            if (mensajero.comunicarPuerto(socketServidor, port) == 1){
                socketServidor.close();
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Segunda conexion puerto ".concat(""+port));
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
    public static int aleatorio (int min,int max){
        String resultado;
        int r;
        resultado =""+Math.floor(Math.random()*(max-min+1)+min);
        r=Integer.parseInt(resultado.substring(0,5));
        return r;
    }
    
}
