/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author sander
 */

/*
 * Clase de administración del dinero de la Cafetera
 */

public class Cajero {
    private BigDecimal saldoCajero; // Dinero acumulado por las ventas
    private BigDecimal saldoCliente;

    public Cajero() {
        saldoCajero = BigDecimal.valueOf(0.00);
        saldoCliente = BigDecimal.valueOf(0.00);
    }
    
    public BigDecimal getSaldoCajero() {
        return saldoCajero;
    }

    public void setSaldoCajero(BigDecimal saldoCajero) {
        this.saldoCajero = saldoCajero;
    }

    public BigDecimal getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(BigDecimal saldoCliente) {
        this.saldoCliente = saldoCliente;
    }
    
    // Método que suma al saldo del cliente una cantidad posterior
    
    public void añadirSaldo(BigDecimal saldo) {
        this.saldoCliente = saldoCliente.add(saldo);
    }
    
    // Método que reinicia el saldo una vez se ha realizado la compra
    
    public void reiniciarSaldo() {
        this.saldoCliente = BigDecimal.valueOf(0.00);
    }
    
    // Método que suma al dinero total el dinero de la última venta
    
    public void añadirDinero(BigDecimal venta) {
        this.saldoCajero.add(venta);
    }
    
    // Método que reinicia el valor de dinero si se "extrae" de la cafetera
    
    public void vaciarCajero() {
        this.saldoCajero = BigDecimal.valueOf(0.00);
    }
    
    // Formatear dinero
    
    public static String formatearDinero(double dinero) {
        DecimalFormat df = new DecimalFormat("0.00' '¤");
        return df.format(dinero);
    }
}
