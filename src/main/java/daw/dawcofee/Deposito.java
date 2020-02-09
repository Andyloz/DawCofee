/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.DepositoInsuficienteExcepcion;
import java.text.DecimalFormat;

/**
 *
 * @author lozan
 */
public class Deposito {
    private Materia contenido;
    private double capMaxima;
    private double cantidad;
    private double capUmbral;
    private boolean indicadorUmbr;

    // Vamos a distinguir entre depósito de líquido y sólido
    // Unidad líquidos: mililitros
    // Unidad sólidos: gramos
    
    
    public Deposito(Materia contenido, double capMaxima, double cantidad, double capUmbral) throws Exception {
        // Comprobaciones de valores pasados por parámetros
        if (capMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad máxima debe ser mayor que 0.");
        } else if (cantidad > capMaxima || cantidad < 0) {
            throw new IllegalArgumentException("La cantidad debe estar entre la capacidad máxima y 0 (incluidos).");
        } else if (capUmbral < 0 || capUmbral > 1) {
            throw new IllegalArgumentException("El umbral debe estar entre 1.00 y 0.00 (incluidos).");
        } else {
            this.contenido = contenido;
            this.capMaxima = capMaxima;
            this.cantidad = cantidad;
            this.capUmbral = capUmbral;
            this.indicadorUmbr = (capMaxima * capUmbral) >= cantidad;
        }
    }
    
    
    // Métodos modificadores de la cantidad
    public void actualizarUmbral() {
        indicadorUmbr = (capMaxima * capUmbral) >= cantidad;
    }
    
    public void rellenar() { // Al máximo
        cantidad = capMaxima;
        this.actualizarUmbral();
    }
    
    public void rellenar(double cantidad) {
        if (this.cantidad + cantidad > capMaxima) {
            throw new RuntimeException("La cantidad añadida no puede superar la capacidad máxima ("+this.getCapMaximaF()+").");
        } else {
            this.cantidad += cantidad;
            this.actualizarUmbral();
        }
    }
    
    public void vaciar() { // Al máximo
        cantidad = 0;
        this.actualizarUmbral();
    }
    
    public void vaciar(double cantidad) {
        if (this.cantidad - cantidad < 0) {
            throw new DepositoInsuficienteExcepcion(this.contenido.toString());
        } else {
            this.cantidad -= cantidad;
            this.actualizarUmbral();
        }
    }
    
    
    
    // Get formateado
    public String getCapMaximaF() {
        if (contenido.getTipo().equals("líquido")) {
            return capMaxima+" ml";
        } else {
            return capMaxima+" gr";
        }
    }
    
    public String getCantidadF() {
        if (contenido.getTipo().equals("líquido")) {
            return cantidad+" ml";
        } else {
            return cantidad+" gr";
        }
    }
    
    public String getCapUmbralF() {
        DecimalFormat df = new DecimalFormat("#%");
        return df.format(capUmbral);
    }
    
    public String isIndicadorUmbrF() {
        String var = indicadorUmbr
                ? "El depósito está en umbral"
                : "El depósito está por encima del umbral";
        return var;
    }
    
    
    
                //////////////////////
                // Getter y Setters //
                //////////////////////
    
    
    public Materia getContenido() {
        return contenido;
    }

    public void setContenido(Materia contenido) {
        this.contenido = contenido;
    }

    public double getCapMaxima() {
        return capMaxima;
    }

    public void setCapMaxima(double capMaxima) {
        this.capMaxima = capMaxima;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCapUmbral() {
        return capUmbral;
    }

    public void setCapUmbral(double capUmbral) {
        this.capUmbral = capUmbral;
    }

    public boolean isIndicadorUmbr() {
        return indicadorUmbr;
    }

    public void setIndicadorUmbr(boolean indicadorUmbr) {
        this.indicadorUmbr = indicadorUmbr;
    }
    
    
    
    // Método toString
    
    // Ejemplo de salida:
    // Depósito de agua
    // Capacidad (máxima): 500 de 10000 ml
    // El depósito está por debajo del umbral (33%)
    @Override
    public String toString() {
        return "Depósito de " + contenido.getNombre() + "\n" +
               "Capacidad (máxima): " + getCantidad() + " de " + getCapMaximaF() + "\n" +
               isIndicadorUmbrF() + " (" + getCapUmbralF() + ")";
    }  
}
