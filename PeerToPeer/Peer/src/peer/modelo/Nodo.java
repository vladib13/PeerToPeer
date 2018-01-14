package peer.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vladimir
 */
public class Nodo {
    private int hash_id;
    private int sucesor;
    private int predecesor;
    private List<Recurso> recursos = new ArrayList<Recurso>();
    
    public Nodo(int hash, int suc, int pre){
        hash_id = hash;
        sucesor = suc;
        predecesor = pre;
        cargarRecursos();
    }
    public Nodo(int hash){
        hash_id = hash;
    }

    public int getHash_id() {
        return hash_id;
    }

    public void setHash_id(int hash_id) {
        this.hash_id = hash_id;
    }

    public int getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(int predecesor) {
        this.predecesor = predecesor;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public int getSucesor() {
        return sucesor;
    }

    public void setSucesor(int sucesor) {
        this.sucesor = sucesor;
    }
    public void cargarRecursos(){
        BDDManager bdd = new BDDManager();
        String [] rscs = bdd.listarArchivos();
        for (int i = 0; i<rscs.length; i++){
            if(!rscs[i].isEmpty()){
            Recurso recurso = new Recurso(rscs[i].hashCode(),rscs[i],hash_id);
            recursos.add(recurso);
            }
        }
        //return recursos;
    }
}
