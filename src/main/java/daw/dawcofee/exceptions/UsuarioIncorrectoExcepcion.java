/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee.exceptions;

/**
 *
 * @author sander
 */
public class UsuarioIncorrectoExcepcion extends Exception {

    public UsuarioIncorrectoExcepcion(String usuario) {
        super(usuario + "no es un usuario correcto. El nombre de usuario de tener entre\n"
                    + "5 y 20 caracteres");
    }

}
