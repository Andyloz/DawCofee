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
    
    // Método que suma al saldo del cliente una cantidad posterior
    
    public void añadirSaldo(double saldo) {
        this.saldoCliente += saldo;
    }
    
    // Método que reinicia el saldo una vez se ha realizado la compra
    
    public void reiniciarSaldo() {
        this.saldoCliente = 0;
    }
    
    // Método que suma al dinero total el dinero de la última venta
    
    public void añadirDinero(double venta) {
        this.dinero += venta;
    }
    
    // Método que reinicia el valor de dinero si se "extrae" de la cafetera
    
    public void vaciarCajero() {
        this.dinero = 0;
    }
}
