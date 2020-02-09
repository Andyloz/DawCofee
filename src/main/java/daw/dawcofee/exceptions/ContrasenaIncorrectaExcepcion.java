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
public class ContrasenaIncorrectaExcepcion extends Exception {

    public ContrasenaIncorrectaExcepcion(String contrasena) {
        super("'" + contrasena + "'" + " no es una contraseña correcta. La contraseña debe tener entre 5 y 30\n"
                    + "caracteres, de los cuales al menos 3 deben ser "
                    + "números y al\n menos 1 mayúscula");
    }
}
