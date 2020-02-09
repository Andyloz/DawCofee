 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.DepositoInexistenteExcepcion;
import daw.dawcofee.exceptions.DepositoInsuficienteExcepcion;
import daw.dawcofee.exceptions.SaldoInsuficienteExcepcion;
import java.util.ArrayList;

/**
 *
 * @author lozan
 */
public class Cafetera {
    
    private ArrayList<Deposito> depositos;
    private ArrayList<Producto> productos;
    private Cajero cajero;
    private Usuario usuario;
    
    // Método constructor

    public Cafetera(ArrayList<Deposito> depositos, ArrayList<Producto> productos, Cajero cajero) {
        
        this.depositos = depositos;
        this.productos = productos;
        this.cajero = cajero;
        this.usuario = null;
    }
    
//    // Método constructor
//    public Cafetera() throws Exception {
//        // Inicialización del cajero
//        this.cajero = new Cajero();
//        
//        // Inicialización de depósitos
//        
//        
//        // Inicialización de productos
//        this.prod1 = new Producto("Café", 0.80, "1",
//                this.dep1, 50, 
//                this.dep3, 6, 
//                this.dep2, 0);
//        this.prod2 = new Producto("Café descafeinado", 0.80, "2", 
//                this.dep1, 50, 
//                this.dep4, 6, 
//                this.dep2, 0);
//        this.prod3 = new Producto("Café largo", 0.90, "3", 
//                this.dep1, 100, 
//                this.dep3, 12, 
//                this.dep2, 0);
//        this.prod4 = new Producto("Café largo descafeinado", 0.90, "4", 
//                this.dep1, 100, 
//                this.dep4, 12, 
//                this.dep2, 0);
//        this.prod5 = new Producto("Café con leche", 1.10, "5", 
//                this.dep1, 50, 
//                this.dep3, 6, 
//                this.dep2, 50);
//        this.prod6 = new Producto("Café descafeinado con leche", 1.10, "6", 
//                this.dep1, 50, 
//                this.dep4, 6, 
//                this.dep2, 50);
//        this.prod7 = new Producto("Café cortado", 1.00, "7", 
//                this.dep1, 50, 
//                this.dep3, 6, 
//                this.dep2, 25);
//        this.prod8 = new Producto("Café cortado descafeinado", 1.00, "8", 
//                this.dep1, 50, 
//                this.dep4, 6, 
//                this.dep2, 25);
//        this.prod9 = new Producto("Chocolate", 0.90, "9", 
//                this.dep2, 100, 
//                this.dep5, 12, 
//                null, 0);
//        this.prod10 = new Producto("Leche fría", 0.90, "10", 
//                this.dep2, 100, 
//                null, 0, 
//                null, 0);
//        this.prod11 = new Producto("Leche caliente", 0.90, "11", 
//                this.dep2, 100, 
//                null, 0, 
//                null, 0);
//        
//        // Array de productos
//        productos = new Producto[]{prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11};
//        
//        // Array de depósitos
//        depositos = new Deposito[]{dep1, dep2, dep3, dep4, dep5, dep6, dep7};
//    }
    
    
    
    public void venta(Producto producto) throws SaldoInsuficienteExcepcion, DepositoInexistenteExcepcion {
        
        if (cajero.getSaldoCliente().compareTo(producto.getPrecio()) < 0) {
            throw new SaldoInsuficienteExcepcion();
        } else {
            // Almacenar la cantidad de cada depósito antes de vaciarlos.
            // Nos servirá para poder dejarlos como estaban si no hay suficiente
            // producto de alguno
            
            ArrayList<Double> cantidadesReinicio = new ArrayList<>();
            ArrayList<Deposito> listaDepositos = new ArrayList<>();
            
            boolean guardado;
            
            // Por cada materia del producto, el sistema revisará los depósitos de
            // la cafetera. Al depósito que contenga la misma materia que necesita el
            // producto se guardará el valor de su cantidad en la lista anterior por si
            // es necesario restaurarlo, y se saldrá del bucle.
            
            for (int i = 0; i < producto.getMaterias().size(); i++) {
                
                guardado = false;
                
                for (Deposito deposito : this.depositos) {
                    if (deposito.getContenido().getContenido()
                            .equals(producto.getMaterias().get(i).getContenido())) {
                        
                        cantidadesReinicio.add(deposito.getCantidad());
                        listaDepositos.add(deposito);
                        guardado = true;
                        break;
                    }
                }
                
                if (!guardado) {
                    
                    // Si no hubiera un depósito con la materia
                    // deseada, se lanzará una excepción.
            
                    throw new DepositoInexistenteExcepcion(producto.getMaterias().get(i));
                }
            }
            
            /////   Vaciado de depósitos   /////
            // Si alguno se vacía satisfactoriamente, pero el siguiente lanza una
            // excepción, se dejarán todos los depósitos como estaban
            try {
                for (int i = 0; i < listaDepositos.size(); i++) {
                    listaDepositos.get(i).vaciar(producto.getCantidades().get(i));
                }
                
                cajero.añadirDinero(producto.getPrecio());
                cajero.restarSaldo(producto.getPrecio());
            } catch (DepositoInsuficienteExcepcion e) {
                
                // Por cada depósito previo se le aplica el valor guardado previamente
                
                for (int i = 0; i < listaDepositos.size(); i++) {
                    listaDepositos.get(i).setCantidad(cantidadesReinicio.get(i));
                }
            }
        }
    }
    
    
    
    public Producto productoMasBarato() {
        Producto buffer1 = null;
        Producto buffer2;
        for (int i = 0; i < productos.size() - 1; i++) {
            if ( i == 0) {
                buffer1 = productos.get(i);
            } else {
                buffer2 = productos.get(i);
                
                buffer1 = buffer1.getPrecio().compareTo(buffer2.getPrecio()) < 0
                        ? buffer1
                        : buffer2;
            }
        }
        return buffer1;
    }
    
    
    // Getters y setters

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public ArrayList<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(ArrayList<Deposito> depositos) {
        this.depositos = depositos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
