package peer.modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import peer.Peer;

/**
 *
 * @author vladimir
 */
/*import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;*/

public class BDDManager {

     private File file;
     private PrintWriter writer;
     final static String resourcesDirectory = "peerFiles/resources/";
        /*try {
            writer = new PrintWriter(file, "UTF-8");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    public BDDManager(){
        
    }
    public String[] listarArchivos(){
        String[] files = null;
        file = new File(resourcesDirectory);
        files = file.list();
        return files;
    }
    public String query (){

        String consulta ="";

        return consulta;
    }
    public void update (){
    }
    public void delete(){

    }
    public void insert (){

    }
}
