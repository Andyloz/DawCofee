/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.DepositoInsuficienteExcepcion;
import daw.dawcofee.exceptions.SaldoInsuficienteExcepcion;

/**
 *
 * @author lozan
 */
public class Cafetera {

    // Componentes Depósito
    private Deposito dep1, dep2, dep3, dep4, dep5, dep6, dep7;
    
    // Componentes Producto
    private Producto prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, 
            prod10, prod11;
    
    // Array de productos
    //Producto[] productos = {prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11}; 
    private Producto productos[];
    
    // Array de depósitos
    // Deposito[] depositos = {dep1, dep2, dep3, dep4, dep5, dep6, dep7};
    private Deposito depositos[];
    
    // Cajero
    private Cajero cajero = new Cajero();
    
    // Método constructor
    public Cafetera() throws Exception {
        // Inicialización del cajero
        this.cajero = new Cajero();

        // Inicialización de depósitos
        this.dep1 = new Deposito("agua", 10000, 10000, 0.33, "líquido");
        this.dep2 = new Deposito("leche", 10000, 10000, 0.33, "líquido");
        this.dep3 = new Deposito("café", 10000, 10000, 0.33, "sólido");
        this.dep4 = new Deposito("café descafeinado", 10000, 10000, 0.33, "sólido");
        this.dep5 = new Deposito("cacao", 10000, 10000, 0.33, "sólido");
        this.dep6 = new Deposito("azúcar", 10000, 10000, 0.33, "sólido");
        this.dep7 = new Deposito("sacarina", 10000, 10000, 0.33, "sólido");
        
        // Inicialización de productos
        this.prod1 = new Producto("Café", 0.80, "1",
                this.dep1, 50, 
                this.dep3, 6, 
                this.dep2, 0);
        this.prod2 = new Producto("Café descafeinado", 0.80, "2", 
                this.dep1, 50, 
                this.dep4, 6, 
                this.dep2, 0);
        this.prod3 = new Producto("Café largo", 0.90, "3", 
                this.dep1, 100, 
                this.dep3, 12, 
                this.dep2, 0);
        this.prod4 = new Producto("Café largo descafeinado", 0.90, "4", 
                this.dep1, 100, 
                this.dep4, 12, 
                this.dep2, 0);
        this.prod5 = new Producto("Café con leche", 1.10, "5", 
                this.dep1, 50, 
                this.dep3, 6, 
                this.dep2, 50);
        this.prod6 = new Producto("Café descafeinado con leche", 1.10, "6", 
                this.dep1, 50, 
                this.dep4, 6, 
                this.dep2, 50);
        this.prod7 = new Producto("Café cortado", 1.00, "7", 
                this.dep1, 50, 
                this.dep3, 6, 
                this.dep2, 25);
        this.prod8 = new Producto("Café cortado descafeinado", 1.00, "8", 
                this.dep1, 50, 
                this.dep4, 6, 
                this.dep2, 25);
        this.prod9 = new Producto("Chocolate", 0.90, "9", 
                this.dep2, 100, 
                this.dep5, 12, 
                null, 0);
        this.prod10 = new Producto("Leche fría", 0.90, "10", 
                this.dep2, 100, 
                null, 0, 
                null, 0);
        this.prod11 = new Producto("Leche caliente", 0.90, "11", 
                this.dep2, 100, 
                null, 0, 
                null, 0);
        
        // Array de productos
        productos = new Producto[]{prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11};
        
        // Array de depósitos
        depositos = new Deposito[]{dep1, dep2, dep3, dep4, dep5, dep6, dep7};
    }
    
    
    
    public void venta(Producto producto) throws SaldoInsuficienteExcepcion {
        
        if (cajero.getSaldoCliente().compareTo(producto.getPrecio()) < 0) {
            throw new SaldoInsuficienteExcepcion();
        } else {
            // Almacenar la cantidad de cada depósito antes de vaciarlos.
            // Nos servirá para poder dejarlos como estaban si no hay suficiente
            // producto de alguno
            double cantidadBase = producto.getCantidadBase();
            double cantidadPolvo = producto.getCantidadPolvo();
            double cantidadLeche = producto.getCantidadLeche();
            
            /////   Vaciado de depósitos   /////
            // Si alguno se vacía satisfactoriamente, pero el siguiente lanza una
            // excepción, se dejarán todos los depósitos como estaban
            try {
                producto.getDepBase().vaciar(producto.getCantidadBase());
                producto.getDepPolvo().vaciar(producto.getCantidadPolvo());
                producto.getDepLeche().vaciar(producto.getCantidadLeche());
                
                cajero.añadirDinero(producto.getPrecio());
                cajero.restarSaldo(producto.getPrecio());
            } catch (DepositoInsuficienteExcepcion e) {
                producto.getDepBase().setCantidad(cantidadBase);
                producto.getDepPolvo().setCantidad(cantidadPolvo);
                producto.getDepLeche().setCantidad(cantidadLeche);
            }
        }
    }
    
    
    
    public Producto productoMasBarato() {
        Producto buffer1 = null;
        Producto buffer2 = null;
        for (int i = 0; i < productos.length- 1; i++) {
            if ( i == 0) {
                buffer1 = productos[i];
            } else {
                buffer2 = productos[i];
                
                buffer1 = buffer1.getPrecio().compareTo(buffer2.getPrecio()) < 0
                        ? buffer1
                        : buffer2;
            }
        }
        return buffer1;
    }
    
    
    // Getters y setters
    

    public Deposito getDep1() {
        return dep1;
    }

    public void setDep1(Deposito dep1) {
        this.dep1 = dep1;
    }

    public Deposito getDep2() {
        return dep2;
    }

    public void setDep2(Deposito dep2) {
        this.dep2 = dep2;
    }

    public Deposito getDep3() {
        return dep3;
    }

    public void setDep3(Deposito dep3) {
        this.dep3 = dep3;
    }

    public Deposito getDep4() {
        return dep4;
    }

    public void setDep4(Deposito dep4) {
        this.dep4 = dep4;
    }

    public Deposito getDep5() {
        return dep5;
    }

    public void setDep5(Deposito dep5) {
        this.dep5 = dep5;
    }

    public Deposito getDep6() {
        return dep6;
    }

    public void setDep6(Deposito dep6) {
        this.dep6 = dep6;
    }

    public Deposito getDep7() {
        return dep7;
    }

    public void setDep7(Deposito dep7) {
        this.dep7 = dep7;
    }

    public Producto getProd1() {
        return prod1;
    }

    public void setProd1(Producto prod1) {
        this.prod1 = prod1;
    }

    public Producto getProd2() {
        return prod2;
    }

    public void setProd2(Producto prod2) {
        this.prod2 = prod2;
    }

    public Producto getProd3() {
        return prod3;
    }

    public void setProd3(Producto prod3) {
        this.prod3 = prod3;
    }

    public Producto getProd4() {
        return prod4;
    }

    public void setProd4(Producto prod4) {
        this.prod4 = prod4;
    }

    public Producto getProd5() {
        return prod5;
    }

    public void setProd5(Producto prod5) {
        this.prod5 = prod5;
    }

    public Producto getProd6() {
        return prod6;
    }

    public void setProd6(Producto prod6) {
        this.prod6 = prod6;
    }

    public Producto getProd7() {
        return prod7;
    }

    public void setProd7(Producto prod7) {
        this.prod7 = prod7;
    }

    public Producto getProd8() {
        return prod8;
    }

    public void setProd8(Producto prod8) {
        this.prod8 = prod8;
    }

    public Producto getProd9() {
        return prod9;
    }

    public void setProd9(Producto prod9) {
        this.prod9 = prod9;
    }

    public Producto getProd10() {
        return prod10;
    }

    public void setProd10(Producto prod10) {
        this.prod10 = prod10;
    }

    public Producto getProd11() {
        return prod11;
    }

    public void setProd11(Producto prod11) {
        this.prod11 = prod11;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public Deposito[] getDepositos() {
        return depositos;
    }
}
