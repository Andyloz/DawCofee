/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author sander
 */

/*
 * Clase de modelo para los objetos Producto que ofrece la Cafetera
 */

public class Producto {
    private final String nombre;
    private BigDecimal precio;
    private String codigo;
    ArrayList<Materia> materias;
    ArrayList<Double> cantidades;

    
    
    public Producto(String nombre, BigDecimal precio, String codigo, ArrayList<Materia> materias, ArrayList<Double> cantidades) {
        if (precio.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio no puede ser menor que 0");
        }
        
        for (Double cantidad : cantidades) {
            if (cantidad <= 0) {
                throw new IllegalArgumentException("Las cantidades no pueden ser menor o igual que 0");
            }
        }
        
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.materias = materias;
        this.cantidades = cantidades;
    }
    
    

    // Getters y setters
    
    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }
    
    @Override
    public String toString() {
        return "Cafe listo: "+nombre+".";
    }
}
