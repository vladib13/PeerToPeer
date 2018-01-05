package peer.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import peer.modelo.Nodo;
/**
 *
 * @author vladimir
 */
public class DHT {
    private List<Nodo> nodos = DHTPrueba();
    
    public List<Nodo> orderTable (Nodo nuevo){
        if ((nodos.get(nodos.size()-1).getHash_id()<nuevo.getHash_id())||(nodos.get(0).getHash_id()>nuevo.getHash_id())){
                nuevo.setSucesor(nodos.get(0).getHash_id());
                nuevo.setPredecesor(nodos.get(nodos.size()-1).getHash_id());
                nodos.get(0).setPredecesor(nuevo.getHash_id());
                nodos.get(nodos.size()-1).setSucesor(nuevo.getHash_id());
                if(nodos.get(nodos.size()-1).getHash_id()<nuevo.getHash_id()){
                    nodos.add(nuevo);
                }
                else if (nodos.get(0).getHash_id()>nuevo.getHash_id()){
                    nodos.add(0,nuevo);
                }
        }
        else{
            for(int i =0;i<=nodos.size()-1;i++){
                if (nodos.get(i).getHash_id()>nuevo.getHash_id()){
                    nuevo.setSucesor(nodos.get(i).getHash_id());
                    nuevo.setPredecesor(nodos.get(i-1).getHash_id());
                    nodos.get(i).setPredecesor(nuevo.getHash_id());
                    nodos.get(i-1).setSucesor(nuevo.getHash_id());
                    nodos.add(i,nuevo);
                    break;
                }
            }
        }
        return nodos;
    }
    public List<Nodo> DHTPrueba(){
        List<Nodo> lista = new ArrayList<Nodo>();
        lista.add(new Nodo(1111,2222,4444));
        lista.add(new Nodo(2222,3333,1111));
        lista.add(new Nodo(3333,4444,2222));
        lista.add(new Nodo(4444,1111,3333));
        return lista;
    }
    public void imprimirTabla(List<Nodo> lista){
        for(int i = 0;i <= lista.size()-1;i++){
            System.out.println(i + " " + lista.get(i).getHash_id() + " " + lista.get(i).getPredecesor() + " " + lista.get(i).getSucesor());
            System.out.println();
        }
    }
    
}
