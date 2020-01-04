/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

/**
 *
 * @author sander
 */

/*
 * Clase de administración del dinero de la Cafetera
 */

public class Cajero {
    private double dinero;
    private double saldoCliente;

    public Cajero() {
        this.dinero = 0;
        this.saldoCliente = 0;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }
    
}
