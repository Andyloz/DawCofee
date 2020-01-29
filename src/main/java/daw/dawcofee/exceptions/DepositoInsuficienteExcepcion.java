/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee.exceptions;

/**
 *
 * @author lozan
 */
public class DepositoInsuficienteExcepcion extends RuntimeException {
    public DepositoInsuficienteExcepcion(String contenido) {
        super("No hay suficiente "+contenido+".");
    }
}
