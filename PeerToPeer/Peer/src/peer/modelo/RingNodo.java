/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vladimir
 */
public class RingNodo {
    protected int hash_id;
    protected String identifier;
    protected int sucesor;
    protected int predecesor;
    protected String tipoNodo;
    private int recursoSucesor;
    private List<RingNodo> recursosPreceden = new ArrayList<RingNodo>();
    
    public List<RingNodo> getRecursosPreceden() {
        return recursosPreceden;
    }

    public void setRecursosPreceden(List<RingNodo> recursosPreceden) {
        this.recursosPreceden = recursosPreceden;
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
    public int getSucesor() {
        return sucesor;
    }

    public void setSucesor(int sucesor) {
        this.sucesor = sucesor;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(String tipoNodo) {
        this.tipoNodo = tipoNodo;
    }
    public int getRecursoSucesor() {
        return recursoSucesor;
    }

    public void setRecursoSucesor(int recursoSucesor) {
        this.recursoSucesor = recursoSucesor;
    }
}
