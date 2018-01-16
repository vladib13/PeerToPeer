/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.controlador;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import peer.modelo.*;
import peer.vista.InputConsole;

/**
 *
 * @author vladimir
 */
public class ControladorPeer {
    
            ConnectionManager conn;
            InputConsole input;
            
            public ControladorPeer(){
                try {
                    input = new InputConsole();
                    Runnable newConnection3 = new Runnable() {
                        public void run() { 	
                                try {
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Presione 1 para nodo fantasma, cualquier otra cosa para nodo normal");
                                    int fantasma = sc.nextInt();
                                    if(fantasma != 1){
                                        input.consola();
                                    }
                                    
                                } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, e);
                                }

                        }
                    };
                    
                    Thread connection3 = new Thread(newConnection3);
                    connection3.start();
                    conn = new ConnectionManager();
                    conn.serve();
            
                } catch (IOException ex) {
                    Logger.getLogger(ControladorPeer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
}
