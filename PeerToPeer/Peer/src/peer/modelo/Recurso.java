/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peer.modelo;

/**
 *
 * @author vladimir
 */
public class Recurso {
    private int hash;
    private String nombre;
    private int hashPropietario;
    
    public Recurso(int hash, String nombre, int hashPropietario){
        this.hash = hash;
        this.hashPropietario = hashPropietario;
        this.nombre = nombre;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
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
    
}
