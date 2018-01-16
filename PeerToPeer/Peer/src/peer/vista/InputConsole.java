/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.vista;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.*;
import peer.modelo.*;

/**
 *
 * @author vladimir
 */
public class InputConsole {
    
    ConnectionManager cn;
    TransferManager tm;
    BDDManager file;
    Anillo ring = new Anillo();
    public InputConsole() throws IOException{
        tm = new TransferManager();
        file = new BDDManager();
        file.inicializarEstadisticas();
    }
    
    public void consola() {
        
		try {
		int y=0;
		while (y==0) {
			Scanner sc = new Scanner(System.in);
			final String hish=sc.nextLine();
			if(hish.contains("BUSCAR_RECURSO")){
                           ConnectionManager cm = new ConnectionManager("192.168.4.42",hish.substring(15));
                        }
                        else if(hish.equals("RECURSOS_OFRECIDOS")){
                            System.out.println();
                            for(int i =0; i < file.listarArchivos().length;i++ ){
                                System.out.println(file.listarArchivos()[i]);
                                System.out.println();
                            }
                        }
                        else if(hish.equals("NUM_DESCARGASxVIDEO")){
                            
                            List<String> statistics = file.statisticsQuery();
                            for(int i = 0; i < statistics.size(); i++){
                                System.out.println(statistics.get(i).split("/")[0]+", "+statistics.get(i).split("/")[1]+" descargas totales.");
                            }
                        }             
                        else if(hish.equals("CONECTAR AL ANILLO")){
                            ConnectionManager conn = new ConnectionManager(false,"192.168.4.42",InetAddress.getLocalHost());
                        }
                        else if(hish.equals("SALIR ANILLO")){
                            ConnectionManager conn = new ConnectionManager(true,"192.168.4.42",InetAddress.getLocalHost());
                        }
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void serverConsoleManager(Socket so){
        
        Map<String,RingNodo> nodos = new HashMap<String,RingNodo>();
        try {
                    DataOutputStream out;
                    DataInputStream in;
                    int y=0;
                    out = new DataOutputStream(so.getOutputStream());
                    in = new DataInputStream(so.getInputStream());
                    String hish=in.readUTF();
                    System.out.println(hish);
                    out.writeUTF("ROGER THAT");
                    if(hish.equals("BUSCAR_RECURSO")){
                        String song = in.readUTF();
                        System.out.println(song);
                        tm.enviar(song, so);                         
                    }
                    else if(hish.equals("CONECTAR AL ANILLO")){
                        String add = in.readUTF();
                        Nodo node = new Nodo(add);
                        nodos.put(add, node);
                        ring.ingresoAnillo(node);
                        Iterator it = nodos.keySet().iterator();
                        while(it.hasNext()){
                            ConnectionManager cm = new ConnectionManager(false,it.next().toString(),add);
                            
                        }
                        
                        
                        out.writeUTF("Conectado");
                    }
                    else if(hish.equals("SALIR ANILLO")){
                        String add = in.readUTF();
                        Nodo node = new Nodo(add);
                        nodos.put(add, node);
                        ring.ingresoAnillo(node);
                        Iterator it = nodos.keySet().iterator();
                        while(it.hasNext()){
                            ConnectionManager cm = new ConnectionManager(true,it.next().toString(),add);
                            
                        }
                        
                        
                        out.writeUTF("Conectado");
                    }
                    else if(hish.equals("NEW NODO")){
                        String newN = in.readUTF();
                        Nodo node = new Nodo(newN);
                        ring.ingresoAnillo(node);
                    }
                    else if(hish.equals("SALE NODO")){
                        String newN = in.readUTF();
                        Nodo node = new Nodo(newN);
                        ring.ingresoAnillo(node);
                    }
                }
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}
