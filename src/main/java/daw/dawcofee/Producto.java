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
 * Clase de modelo para los objetos Producto que ofrece la Cafetera
 */

public class Producto {
    private final String nombre;
    private double precio;
    private final String codigo;
    // Agua o leche
    private final Deposito depBase;
    private double cantidadBase;
    // Polvo
    private final Deposito depPolvo;
    private double cantidadPolvo;
    // Leche complementaria
    private final Deposito depLeche;
    private double cantidadLeche;

    
    
    // Método constructor
    public Producto(String nombre, double precio, String codigo, 
            Deposito depBase, double cantidadBase, Deposito depPolvo,
            double cantidadPolvo, Deposito depLeche, double cantidadLeche) {
        
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.depBase = depBase;
        this.cantidadBase = cantidadBase;
        this.depPolvo = depPolvo;
        this.cantidadPolvo = cantidadPolvo;
        this.depLeche = depLeche;
        this.cantidadLeche = cantidadLeche;
    }
    
    
    
    
    // Getters y setters
    
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public Deposito getDepBase() {
        return depBase;
    }

    public double getCantidadBase() {
        return cantidadBase;
    }

    public void setCantidadBase(double cantidadBase) {
        this.cantidadBase = cantidadBase;
    }

    public Deposito getDepPolvo() {
        return depPolvo;
    }

    public double getCantidadPolvo() {
        return cantidadPolvo;
    }

    public void setCantidadPolvo(double cantidadPolvo) {
        this.cantidadPolvo = cantidadPolvo;
    }

    public Deposito getDepLeche() {
        return depLeche;
    }

    public double getCantidadLeche() {
        return cantidadLeche;
    }

    public void setCantidadLeche(double cantidadLeche) {
        this.cantidadLeche = cantidadLeche;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", codigo=" + codigo + ", depBase=" + depBase + ", cantidadBase=" + cantidadBase + ", depPolvo=" + depPolvo + ", cantidadPolvo=" + cantidadPolvo + ", depLeche=" + depLeche + ", cantidadLeche=" + cantidadLeche + '}';
    }

    
}
