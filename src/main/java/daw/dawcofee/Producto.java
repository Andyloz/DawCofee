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
    // Agua o leche
    private Deposito depBase;
    private double cantidadBase;
    // Polvo
    private Deposito depPolvo;
    private double cantidadPolvo;
    // Leche complementaria
    private Deposito depLeche;
    private double cantidadLeche;

    public Producto(String nombre, double precio, Deposito depBase, 
            double cantidadBase, Deposito depPolvo, double cantidadPolvo, 
            Deposito depLeche, double cantidadLeche) {
        
        this.nombre = nombre;
        this.precio = precio;
        this.depBase = depBase;
        this.cantidadBase = cantidadBase;
        this.depPolvo = depPolvo;
        this.cantidadPolvo = cantidadPolvo;
        this.depLeche = depLeche;
        this.cantidadLeche = cantidadLeche;
        this.codigo = null; // Generador
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
    
    public Deposito getDepBase() {
        return depBase;
    }

    public void setDepBase(Deposito depBase) {
        this.depBase = depBase;
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

    public void setDepPolvo(Deposito depPolvo) {
        this.depPolvo = depPolvo;
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

    public void setDepLeche(Deposito depLeche) {
        this.depLeche = depLeche;
    }

    public double getCantidadLeche() {
        return cantidadLeche;
    }

    public void setCantidadLeche(double cantidadLeche) {
        this.cantidadLeche = cantidadLeche;
    }
    
    // Método que genera automáticamente el código según las características
    // del producto.
    
    private String obtenerCodigo() {
        
        String codigo = "";
        
        switch (this.depBase.getContenido()) {
            case "agua":
                if (this.cantidadPolvo == 0) {
                    codigo += "0";
                } else {
                    codigo += "1";
                }
                break;
            case "leche":
                if (this.cantidadPolvo == 0) {
                    codigo += "0";
                } else {
                    codigo += "2";
                }
                break;
            default:
                codigo += "0";
        }
        
        switch (this.getDepPolvo().getContenido()) {
            case "café":
            case "café descafeinado":
                if (this.cantidadPolvo == 0) {
                    codigo += "0";
                } else {
                    codigo += "1";
                }
                break;
            case "cacao":
                if (this.cantidadPolvo == 0) {
                    codigo += "0";
                } else {
                    codigo += "2";
                }
                break;
            default:
                codigo += "0";
        }
        
        return null;
    }

}
