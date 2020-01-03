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
 * Clase de modelo para los objetos Producto que ofrece la Cafetera
 */

public class Producto {
    private String nombre;
    private double precio;
    private String codigo;
    
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = null; //Inserte método generador
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
//    // Método que genera automáticamente el código según las características
//    // del producto.
//    
//    private String obtenerCodigo() {
//        return "null";
//    }
    
}
