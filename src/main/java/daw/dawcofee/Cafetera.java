/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

/**
 *
 * @author lozan
 */
public class Cafetera {
    
    // Componentes Depósito
    private Deposito dep1;
    private Deposito dep2;
    private Deposito dep3;
    private Deposito dep4;
    private Deposito dep5;
    private Deposito dep6;
    private Deposito dep7;
    private Deposito dep8;
    
    // Componentes Producto
    private Producto prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, 
            prod10, prod11, prod12, prod13, prod14, prod15, prod16, prod17, 
            prod18, prod19, prod20;
    
    // Contador de ventas
    private int ventas;
    
    
    public Cafetera() throws Exception {
        this.ventas = 0;
        
        // Inicialización de depósitos
        this.dep1 = new Deposito("agua", 10, 10, 0.33, "líquido");
        this.dep2 = new Deposito("leche", 10, 10, 0.33, "líquido");
        this.dep3 = new Deposito("café", 10, 10, 0.33, "sólido");
        this.dep4 = new Deposito("café descafeinado", 10, 10, 0.33, "sólido");
        this.dep5 = new Deposito("cacao", 10, 10, 0.33, "sólido");
        this.dep6 = new Deposito("azúcar", 10, 10, 0.33, "sólido");
        this.dep7 = new Deposito("sacarina", 10, 10, 10, "sólido");
        this.dep8 = new Deposito("leche fría", 10, 10, 0.33, "líquido");
        
        // Inicialización de productos
        
    }
    
    
    // Getters y setters
    
    public double getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

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

    public Deposito getDep8() {
        return dep8;
    }

    public void setDep8(Deposito dep8) {
        this.dep8 = dep8;
    }
    
    public void añadirVenta() {
        this.ventas++;
    }

}
