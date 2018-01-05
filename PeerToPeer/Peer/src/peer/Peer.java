package peer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import peer.modelo.*;

/**
 *
 * @author vladimir
 */
public class Peer {

   
    public static void main(String[] args) {
        
        DHT tabla = new DHT();
        List<Nodo> lista = tabla.orderTable(new Nodo(5555,0,0));
        tabla.imprimirTabla(lista);
    }
}
