/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author vladimir
 */
public class FingerTable {
    
    private List<Nodo> nodos;
    private Nodo sucesor;
    private Nodo node;
    private Anillo ring;
    
    public FingerTable(Nodo node, Anillo ring){
        nodos = new ArrayList<Nodo>();
        this.ring = ring;
        this.node = node;
        this.sucesor = new Nodo("192.168.16.9");//realSucesor(node);
        
    }
    public Integer busquedaRecurso(Integer resource_hash){
        Integer hash = 0;
        for(int i = 0; i<nodos.size();i++){
            for(int j = 0; j<nodos.size();j++){
                if (nodos.get(i).getRecursosPreceden().get(j).getHash_id() == resource_hash){
                    hash = ((Recurso)nodos.get(i).getRecursosPreceden()).getHashPropietario();
                    break;
                }
                else if (nodos.get(i).getRecursosPropios().get(j).getHash_id() == resource_hash){
                    hash = nodos.get(i).hash_id;
                    break;
                }
                else{
                    FingerTable tabla = new FingerTable(nodos.get(i),ring);
                    hash = tabla.busquedaRecurso(resource_hash);
                }
            }
        }
        return hash;
        
    }
    public RingNodo realSucesor(RingNodo node){
        RingNodo rn = null;
        if (ring.getMapish().containsKey(node.getSucesor())){
            rn = ring.getMapish().get(node.getSucesor());
        }
        else{
            int suce = node.getSucesor();
            int id_suc = ring.getMapishR().get(suce).getRecursoSucesor();
            rn = ring.getMapish().get(id_suc);
        }
        return rn;
    }
    public void armarFingerTable(Anillo a){
        int iteracion =0;
        nodos.add(sucesor);
        for (int i = 0; i < 30; i++){
            iteracion = exponente(i) + node.getHash_id();
            if((exponente(31)-sucesor.getHash_id())>iteracion){
                if (a.getMapish().containsKey(iteracion)){
                    nodos.add((Nodo)a.getMapish().get(iteracion));
                }
                else if (iteracion<sucesor.getHash_id()){
                    nodos.add(sucesor);
                }
                else{
                    Iterator it = a.getMapish().keySet().iterator();
                    while(it.hasNext()){
                        Integer hash = Integer.parseInt(it.next().toString());
                        if((!a.getMapish().get(hash).equals(sucesor)) && (a.getMapish().get(hash).getHash_id() > iteracion)){
                            nodos.add((Nodo)a.getMapish().get(hash));
                            break;
                        }
                    }
                } 
            }
            else{
                iteracion = iteracion - exponente(31);
                if (a.getMapish().containsKey(iteracion)){
                    nodos.add((Nodo)a.getMapish().get(iteracion));
                }
                else{
                    Iterator it = a.getMapish().keySet().iterator();
                    while(it.hasNext()){
                        Integer hash = Integer.parseInt(it.next().toString());
                        if((!a.getMapish().get(hash).equals(sucesor)) && (a.getMapish().get(hash).getHash_id() > iteracion)){
                            nodos.add((Nodo)a.getMapish().get(hash));
                            break;
                        }
                    }
                }
            }
            
        }
    }
    public int exponente(int iter){
        int result = 2;
        int i = 0;
        while (i <iter){
            result = result*2;
            i++;
        }
        return result;
    }
    public void printFingerTable(){
        System.out.println("Tabla Finger");
        System.out.println("N" + "      " +"      " + "NODO" + "      " +"      " + "ANT" + "      " +"         " + "SIG" + "      " +"     " + "TIPO_NODO" + "      " +" " + "NOMBRE");
        System.out.println();
        for(int i = 0;i <= nodos.size()-1;i++){
            
            System.out.println(i + "      " +" " + nodos.get(i).getHash_id() + "      " +" " + nodos.get(i).getPredecesor() + "      " +" " + nodos.get(i).getSucesor() + "      " +" " + nodos.get(i).getTipoNodo() + "      " +" " + nodos.get(i).getIdentifier());
            System.out.println();
        }
    }
}

