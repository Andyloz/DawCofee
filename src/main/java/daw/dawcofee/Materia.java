/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

/**
 *
 * @author lozan
 */
public class Materia {
    private final MateriaEnum contenido;

    public Materia(MateriaEnum contenido) {
        this.contenido = contenido;
    }
    
    public String getNombre() {
        return contenido.getNombre();
    }
    
    public String getTipo() {
        return contenido.getTipo();
    }
}
