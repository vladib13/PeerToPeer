package peer.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
     final static String rootDirectory = "peerFiles/";
     final static String resourcesDirectory = "peerFiles/resources/";
     List<String> statistics = new ArrayList<String>();
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
    public List<String> statisticsQuery (){

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            archivo = new File (rootDirectory+"statistics.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            String linea ="";
            int i = 0;
            while(true){
                linea=br.readLine();
                if((!linea.equals(null))){
                    statistics.add(linea);
                    i++;
                }
                else{
                    break;
                }
            }
            fr.close();    
        }
        catch(Exception e){
            
        }
        return statistics;
    }
    
    public void statisticsUpdate (String recurso){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Integer line = 0;
        
        try {
            archivo = new File (rootDirectory+"statistics.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            String linea ="";
            int i = 0;
            while((linea=br.readLine())!= null){
                
                
                    if(linea.contains(recurso)){
                        line = i;
                        System.out.println(line);
                    }
                    statistics.add(linea);
                    i++;
            }
                    fr.close();
                    String modificar = statistics.get(line);
                    System.out.println("a modificar "+modificar);
                    int num = Integer.parseInt(modificar.split("/")[1]);
                    System.out.println("Ahora tiene " + num);
                    num++;
                    modificar = modificar.split("/")[0].concat("/"+num);
                    System.out.println("Nueva linea "+modificar);
                    statistics.set(line, modificar);
                    statisticsInsert (statistics);
                   
            
            
            
        }
        catch(Exception e){
            
        }
    }
    public void delete(){

    }
    public void inicializarEstadisticas(){
        
        String [] rscs = listarArchivos();
        List<String> data = new ArrayList<String>();
        for (int i = 0; i<rscs.length; i++){
            if(!rscs[i].isEmpty()){
                data.add(rscs[i]+"/"+0);
            }
        }
        statisticsInsert (data);
    }
    public void statisticsInsert (List<String> data){

        FileWriter fichero = null;
        PrintWriter pw = null;
        File f = new File(rootDirectory+"statistics.txt");
        try
        {
            if(f.exists()){
                f.delete();
            }
            fichero = new FileWriter(f);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < data.size(); i++)
                pw.println(data.get(i));

            fichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
