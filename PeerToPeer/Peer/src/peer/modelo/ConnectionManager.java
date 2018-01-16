package peer.modelo;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peer.vista.InputConsole;
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
    InputConsole input;
    Socket so;
    TransferManager tm;
    
    public ConnectionManager() throws IOException{
        servidor = new ServerSocket(SERVER);
        socketServidor = new Socket();
        mensajero = new MessageManager();
        input = new InputConsole();
        so = new Socket();
    }
   /* public ConnectionManager(String serverAddress) throws IOException{
        cliente = new Socket(serverAddress,SERVER);
        mensajero = new MessageManager();
        input = new InputConsole();
        connectClient(serverAddress);
        
    }*/
    public ConnectionManager(boolean salir,String serverAddress,InetAddress ownAddress) throws IOException{
        cliente = new Socket(serverAddress,SERVER);
        mensajero = new MessageManager();
        input = new InputConsole();
        connectClient(salir,serverAddress,ownAddress.getHostAddress());
        
    }
    public ConnectionManager(final boolean salir,String destino,final String ip) throws IOException{
        cliente = new Socket(destino,SERVER);
        mensajero = new MessageManager();
        input = new InputConsole();
        Runnable newConnection = new Runnable() {
                public void run() { 	
                    mensajero.informarNodos(cliente,ip,salir);
                }
            };
            
            Thread connection = new Thread(newConnection);
            connection.start();
        
    
        
    }
    public ConnectionManager(final String serverAddress,final String recurso) throws IOException{
        cliente = new Socket(serverAddress,SERVER);
        mensajero = new MessageManager();
        input = new InputConsole();
        tm = new TransferManager();
        
        Runnable newConnection = new Runnable() {
                public void run() { 	
                    tm.descargarArchivo(connectClientDL(serverAddress), recurso);
                }
            };
            
            Thread connection = new Thread(newConnection);
            connection.start();
        
    }
    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public MessageManager getMensajero() {
        return mensajero;
    }

    public void setMensajero(MessageManager mensajero) {
        this.mensajero = mensajero;
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public Socket getSocketServidor() {
        return socketServidor;
    }

    public void setSocketServidor(Socket socketServidor) {
        this.socketServidor = socketServidor;
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
    
    public final Socket connectClientDL(String serverAddress){
        Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Primera conexion puerto 5000");
        int puerto = mensajero.handshake(cliente);
        try {
            cliente = new Socket(serverAddress,puerto);
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Segunda conexion puerto ".concat(""+puerto));
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    public void connectClient(final boolean bool, String serverAddress, final String own){
        Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Primera conexion puerto 5000");
        int puerto = mensajero.handshake(cliente);
        try {
            cliente = new Socket(serverAddress,puerto);
            Runnable newConnection = new Runnable() {
                public void run() { 	
                    mensajero.ingresarAnillo(own, cliente,bool);
                    
                }
            };
            
            Thread connection = new Thread(newConnection);
            connection.start();
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Conectando al anillo");
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
                            so = sc.accept();
                            Runnable newConnection2 = new Runnable() {
                            public void run() { 	
                                input.serverConsoleManager(so);

                            }
                        };
                        Thread connection2 = new Thread(newConnection2);
                        connection2.start();
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
