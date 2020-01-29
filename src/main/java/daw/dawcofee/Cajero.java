/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import java.text.DecimalFormat;

/**
 *
 * @author sander
 */

/*
 * Clase de administración del dinero de la Cafetera
 */

public class Cajero {
    private double saldoCajero; // Dinero acumulado por las ventas
    private double saldoCliente;

    public double getDinero() {
        return saldoCajero;
    }

    public void setDinero(double dinero) {
        this.saldoCajero = dinero;
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
        this.saldoCajero += venta;
    }
    
    // Método que reinicia el valor de dinero si se "extrae" de la cafetera
    
    public void vaciarCajero() {
        this.saldoCajero = 0;
    }
    
    // Formatear dinero
    
    public static String formatearDinero(double dinero) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(dinero)+" €";
    }
}
