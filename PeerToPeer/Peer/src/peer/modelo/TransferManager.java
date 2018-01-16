/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.modelo;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author vladimir
 */
public class TransferManager {
    
    BDDManager bd;
    public TransferManager(){
        bd = new BDDManager();
    }
    public void enviar(String song, Socket soNew) {
		try {
                    bd.statisticsUpdate(song);
                    DataOutputStream outN = new DataOutputStream(soNew.getOutputStream());
                    DataInputStream inN = new DataInputStream(soNew.getInputStream());
				int pe;
				long peso;
				File video = new File("peerFiles/resources/"+song.toString());
				peso=video.length();
				outN.writeUTF(peso+"");
				FileInputStream fis = new FileInputStream(video);
				inN=new DataInputStream(fis);
				byte [] carga = new byte [512];
				while ((pe=inN.read(carga))!= -1) {
					outN.write(carga, 0, pe);
					
				}
                                fis.close();
				outN.close();
				inN.close();
                                
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
    public void descargarArchivo(Socket server,String nombreCancion) {
		try {
                    DataOutputStream o = new DataOutputStream(server.getOutputStream());
                    DataInputStream i = new DataInputStream(server.getInputStream());
                        o.writeUTF("BUSCAR_RECURSO");
                        System.out.println(i.readUTF());
                        o.writeUTF(nombreCancion);
			int pesox;
			int peso,pe;
			peso=Integer.parseInt(i.readUTF());
			pesox=peso;
			File video;
			video = new File("videosDesc/"+nombreCancion);
			FileOutputStream fos = new FileOutputStream(video);
			byte [] carga = new byte [512];
			DataOutputStream out = new DataOutputStream(fos);
			while ((pe=i.read(carga))!= -1) {
				out.write(carga, 0, pe);
				
			}
			out.close();
                        fos.close();
			i.close();
			o.close();
			
			
		}
		catch(Exception e) {
			//System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
}
