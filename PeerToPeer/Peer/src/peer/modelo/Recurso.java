/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.modelo;

/**
 *
 * @author vladimir
 */
public class Recurso extends RingNodo{
    
    private String nombre;
    private int hashPropietario;
    public Recurso(String nombre, int hashPropietario){
        this.hash_id = hashear(nombre);
        this.hashPropietario = hashPropietario;
        this.identifier = nombre;
        tipoNodo = "Recurso";
    }
    
    

    
    public int getHashPropietario() {
        return hashPropietario;
    }

    public void setHashPropietario(int hashPropietario) {
        this.hashPropietario = hashPropietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
