package peer;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import peer.modelo.*;

/**
 *
 * @author vladimir
 */
public class ClientePrueba {

   
    public static void main(String[] args) {
        try {
            ConnectionManager con = new ConnectionManager(InetAddress.getLocalHost());
            /*Nodo node = new Nodo(4444,5555,3333);
            for (int i = 0; i<node.getRecursos().size();i++){
                System.out.println(node.getRecursos().get(i).getNombre());
            } 
            /*BDDManager bdd = new BDDManager();
            String [] x = bdd.listarArchivos();
            for (int i = 0; i<x.length;i++){
                System.out.println(x[i]);
            }*/
                
            /*DHT tabla = new DHT();
            tabla.setNodos(tabla.DHTPrueba());
            tabla.imprimirTabla(tabla.getNodos());
            List<Nodo> lista = tabla.orderTableDelete(tabla.getNodos().get(1));
            tabla.imprimirTabla(lista);*/
        } catch (IOException ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
