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
    private Deposito depAgua;
    private Deposito depLeche;
    private Deposito depCafe;
    private Deposito depCafeDesc;
    private Deposito depCacao;
    // Componentes Producto
    private Producto prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, 
            prod10, prod11, prod12, prod13, prod14, prod15, prod16, prod17, 
            prod18, prod19, prod20;
    
    // Contador de ventas
    private double ventas;
    
    
    
    public Cafetera() throws Exception {
        // Inicialización de depósitos
        depAgua = new Deposito("agua", 10, 10, 0.33, "líquido");
        depLeche = new Deposito("leche", 10, 10, 0.33, "líquido");
        depCafe = new Deposito("café", 10, 10, 0.33, "sólido");
        depCafeDesc = new Deposito("café descafeinado", 10, 10, 0.33, "sólido");
        depCacao = new Deposito("cacao", 10, 10, 0.33, "sólido");
        
        // Inicialización de productos
        
    }
    
    
    
    // Getters y setters
    
    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public Deposito getDepAgua() {
        return depAgua;
    }

    public void setDepAgua(Deposito depAgua) {
        this.depAgua = depAgua;
    }

    public Deposito getDepLeche() {
        return depLeche;
    }

    public void setDepLeche(Deposito depLeche) {
        this.depLeche = depLeche;
    }

    public Deposito getDepCafe() {
        return depCafe;
    }

    public void setDepCafe(Deposito depCafe) {
        this.depCafe = depCafe;
    }

    public Deposito getDepCafeDesc() {
        return depCafeDesc;
    }

    public void setDepCafeDesc(Deposito depCafeDesc) {
        this.depCafeDesc = depCafeDesc;
    }

    public Deposito getDepCacao() {
        return depCacao;
    }

    public void setDepCacao(Deposito depCacao) {
        this.depCacao = depCacao;
    }
    
    
    
    
}
