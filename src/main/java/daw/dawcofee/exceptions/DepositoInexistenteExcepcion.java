/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee.exceptions;

import daw.dawcofee.Materia;

/**
 *
 * @author sander
 */
public class DepositoInexistenteExcepcion extends Exception {
    
    public DepositoInexistenteExcepcion() {
        super("No se ha encontrado depósito.");
    }

    public DepositoInexistenteExcepcion(Materia materia) {
        super("No existe ningún depósito para " + materia.getNombre() + ".");
    }

    public DepositoInexistenteExcepcion(String msg) {
        super(msg);
    }
}
