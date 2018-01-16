package peer.modelo;

import java.util.*;
import peer.modelo.Nodo;
/**
 *
 * @author vladimir
 */
public class Anillo {
    List<RingNodo> precedidos = new ArrayList<RingNodo>();
    private List<RingNodo> nodos;
    private Map<Integer,RingNodo> mapish;
    private Map<Integer,RingNodo> mapishR;
    
    public Anillo(){
        nodos = new ArrayList<RingNodo>();
        mapish = new HashMap<Integer,RingNodo>();
        mapishR = new HashMap<Integer,RingNodo>();
    }

    public Map<Integer, RingNodo> getMapishR() {
        return mapishR;
    }

    public void setMapishR(Map<Integer, RingNodo> mapishR) {
        this.mapishR = mapishR;
    }

    
    public Map<Integer, RingNodo> getMapish() {
        return mapish;
    }

    public void setMapish(Map<Integer, RingNodo> mapish) {
        this.mapish = mapish;
    }
    
    public List<RingNodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<RingNodo> nodos) {
        this.nodos = nodos;
    }
    
    public List<RingNodo> orderRingInsert (RingNodo nuevo){
        
        if(nodos.isEmpty()){
            nodos.add(nuevo);
            if(nuevo.tipoNodo.contains("Nodo")){
                
                if(!precedidos.isEmpty()){
                    for(int i = 0; i < precedidos.size();i++){
                        nuevo.getRecursosPreceden().add(precedidos.get(i));
                    }
                }
                mapish.put(nuevo.getHash_id(), nuevo);
                precedidos.clear();
            }
            else{
                mapishR.put(nuevo.getHash_id(), nuevo);
                precedidos.add(nuevo);
            }
        }
        else{
            if ((nodos.get(nodos.size()-1).getHash_id()<nuevo.getHash_id())||(nodos.get(0).getHash_id()>nuevo.getHash_id())){
                nuevo.setSucesor(nodos.get(0).getHash_id());
                nuevo.setPredecesor(nodos.get(nodos.size()-1).getHash_id());
                nodos.get(0).setPredecesor(nuevo.getHash_id());
                nodos.get(nodos.size()-1).setSucesor(nuevo.getHash_id());
                if(nodos.get(nodos.size()-1).getHash_id()<nuevo.getHash_id()){
                    nodos.add(nuevo);
                    if(nuevo.tipoNodo.contains("Nodo")){
                        
                        if(!precedidos.isEmpty()){
                            for(int i = 0; i < precedidos.size();i++){
                                nuevo.getRecursosPreceden().add(precedidos.get(i));
                            }
                        }
                        mapish.put(nuevo.getHash_id(), nuevo);
                        precedidos.clear();
                    }
                    else{
                        mapishR.put(nuevo.getHash_id(), nuevo);
                        precedidos.add(nuevo);
                    }
                }
                else if (nodos.get(0).getHash_id()>nuevo.getHash_id()){
                    nodos.add(0,nuevo);
                    if(nuevo.tipoNodo.contains("Nodo")){
                        
                        if(!precedidos.isEmpty()){
                            for(int i = 0; i < precedidos.size();i++){
                                nuevo.getRecursosPreceden().add(precedidos.get(i));
                            }
                        }
                        mapish.put(nuevo.getHash_id(), nuevo);
                        precedidos.clear();
                    }
                    else{
                        mapishR.put(nuevo.getHash_id(), nuevo);
                        precedidos.add(nuevo);
                    }
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
                        if(nuevo.tipoNodo.contains("Nodo")){
                            
                            if(!precedidos.isEmpty()){
                            for(int j = 0; j < precedidos.size();j++){
                                nuevo.getRecursosPreceden().add(precedidos.get(j));
                            }
                        }
                        mapish.put(nuevo.getHash_id(), nuevo);
                        precedidos.clear();
                        }
                        else{
                            mapishR.put(nuevo.getHash_id(), nuevo);
                            precedidos.add(nuevo);
                        }
                        break;
                    }
                }
            }
        }
        
        return nodos;
    }
    public void actualizarSucesoresReales(){
        /*for (int i = 0; i < mapish.size(); i++){
            
        }*/
        Integer key =0;
        Iterator it = mapish.keySet().iterator();
        List<Integer> hashes = new ArrayList();
        while(it.hasNext()){
            key = Integer.parseInt(it.next().toString());
            for(int i = 0; i<mapish.get(key).getRecursosPreceden().size();i++){
                mapishR.get(mapish.get(key).getRecursosPreceden().get(i)).setRecursoSucesor(key);
            }
        }
        
    }
    public void ingresoAnillo(Nodo nuevo){
        orderRingInsert(nuevo);
        printRing();
        for (int i = 0; i < nuevo.getRecursosPropios().size();i++){
            orderRingInsert(nuevo.getRecursosPropios().get(i));
            printRing();
        }
    }
    public void nodosPrueba(){
        
        int j=0;
        for (int i = 1; i < 61; i++){
            Nodo node = new Nodo("192.168.16.".concat(""+j+"af2dfa"+j+"1k-*1"));
            node.setIdentifier(node.getIdentifier().substring(0, 11)+i);
            node.setTipoNodo("Nodo de prueba");
            String x = "Recurso de prueba";
            List <Recurso> recursosPrueba = new ArrayList<Recurso>();
            j++;
            recursosPrueba.add(new Recurso(x.concat(""+j*13+"af2dfa"+j+"1k-*1"),node.getHash_id()));
            j++;
            recursosPrueba.add(new Recurso(x.concat(""+j+"vf2kv1"+j*11+"dovn98*"),node.getHash_id()));
            node.setRecursosPropios(recursosPrueba);
            ingresoAnillo(node);
        }
    }
    
    public void printRing(){
        System.out.println("Anillo");
        System.out.println("N" + "      " +"      " + "NODO" + "      " +"      " + "ANT" + "      " +"         " + "SIG" + "      " /*+"     " + "TIPO_NODO" + "      " +" " + "NOMBRE"*/);
        System.out.println();
        for(int i = 0;i <= nodos.size()-1;i++){
            
            System.out.println(i + "      " +" " + nodos.get(i).getHash_id() + "      " +" " + nodos.get(i).getPredecesor() + "      " +" " + nodos.get(i).getSucesor() + "      " /*+" " + nodos.get(i).getTipoNodo() + "      " +" " + nodos.get(i).getIdentifier()*/);
            System.out.println();
        }
    }
}
