package peer.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vladimir
 */
public class Nodo extends RingNodo{
    
    private List<Recurso> recursosPropios = new ArrayList<Recurso>();
    
    
    public Nodo(String ip, int suc, int pre){
        hash_id = hashear(ip);
        identifier = ip;
        sucesor = suc;
        predecesor = pre;
        tipoNodo = "Nodo Real";
        cargarRecursos();
    }
    public Nodo(String ip){
        hash_id = hashear(ip);
        identifier = ip;
        sucesor = 0;
        predecesor = 0;
        tipoNodo = "Nodo Real";
        cargarRecursos();
    }

    

    public List<Recurso> getRecursosPropios() {
        return recursosPropios;
    }

    public void setRecursosPropios(List<Recurso> recursosPropios) {
        this.recursosPropios = recursosPropios;
    }
    public int hashear(String h){
        int hash =0;
        if (String.valueOf(h.hashCode()).contains("-")){
            hash = Integer.parseInt(String.valueOf(h.hashCode()).substring(1));
        }
        else{
            hash = h.hashCode();
        }
        return hash;
    }

    public void cargarRecursos(){
        BDDManager bdd = new BDDManager();
        String [] rscs = bdd.listarArchivos();
        for (int i = 0; i<rscs.length; i++){
            if(!rscs[i].isEmpty()){
            Recurso recurso = new Recurso(rscs[i],hash_id);
            recursosPropios.add(recurso);
            }
        }
        //return recursos;
    }
}
