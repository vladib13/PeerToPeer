package peer;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import peer.controlador.ControladorPeer;
import peer.modelo.*;
import peer.vista.InputConsole;

/**
 *
 * @author vladimir
 */
public class ClientePrueba {

   
    public static void main(String[] args) {
        try {
            /*Nodo node = new Nodo(InetAddress.getLocalHost().getHostAddress());
            Anillo ring = new Anillo ();
            ring.nodosPrueba();
            ring.ingresoAnillo(node);
            
            FingerTable ft = new FingerTable(node,ring);
            ft.armarFingerTable(ring);
            ft.printFingerTable();*/
            //ControladorPeer cont = new ControladorPeer();
            InputConsole input = new InputConsole();
            input.consola();
            
        } catch (Exception ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
