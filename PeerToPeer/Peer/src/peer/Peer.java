package peer;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author vladimir
 */
public class Peer {

   
    public static void main(String[] args) {
        RequestBody formBody = new FormBody.Builder().add("username","El administrador de redes de esos que llaman el maula, esos que les preguntas la configuracion basica de una red y te dicen CISCO").build();
        Request r = new Request.Builder().url("http:/localhost:8888/viucab/Notificaciones/notificacionMail").post(formBody).build();
        /*try{
        ServerSocket sc = new ServerSocket(5000); 
        int s = new String("192.168.1.100").hashCode();
        
        System.out.println(String.valueOf(s));
        
        }
        catch(ConnectException e){
            
        }
        catch(IOException e){
            
        }*/
        
    }
}
