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
            prod10, prod11;
    
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
        this.prod1 = new Producto("café solo", 0.80, this.dep1, 32, this.dep3, 8, this.dep2, 0);
        this.prod2 = new Producto("café solo descafeinado", 0.80, this.dep1, 32, this.dep4, 8, this.dep2, 0);
        this.prod3 = new Producto("café solo largo", 0.90, this.dep1, 35, this.dep3, 5, this.dep2, 0);
        this.prod4 = new Producto("café solo largo descafeinado", 0.90, this.dep1, 35, this.dep4, 5, this.dep2, 0);
        this.prod5 = new Producto("café con leche", 1.10, this.dep1, 18, this.dep3, 4, this.dep2, 18);
        this.prod6 = new Producto("café con leche descafeinado", 1.10, this.dep1, 18, this.dep4, 4, this.dep2, 18);
        this.prod7 = new Producto("café cortado", 1.00, this.dep1, 30, this.dep3, 8, this.dep2, 2);
        this.prod8 = new Producto("café cortado descafeinado", 1.00, this.dep1, 30, this.dep4, 8, this.dep2, 2);
        this.prod9 = new Producto("chocolate", 0.90, this.dep2, 30, this.dep5, 10, this.dep2, 0);
        this.prod10 = new Producto("leche fría", 0.90, this.dep8, 40, this.dep3, 0, this.dep2, 0);
        this.prod11 = new Producto("leche caliente", 0.90, this.dep2, 40, this.dep3, 0, this.dep2, 0);
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
    

}
