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
public class Deposito {
    double capMaxima;
    double cantidad;
    double capUmbral;
    String tipo;
    boolean indicadorUmbr;

    // Vamos a distinguir entre depósito de líquido y sólido
    // Unidad líquidos: litros
    // Unidad sólidos: kilos
    
    public Deposito(String tipo) {
            this.tipo = tipo;
            capMaxima = 3.0;
            cantidad = 3.0;
            capUmbral = 0.33;
    }
    
    public Deposito(double capMaxima, double cantidad, double capUmbral, String tipo) {
        // Comprobaciones de valores pasados por parámetros
        if (capMaxima <= 0) {
            throw new RuntimeException("La capacidad máxima debe ser mayor que 0.");
        } else if (cantidad > capMaxima || cantidad < 0) {
            throw new RuntimeException("La cantidad debe estar entre la capacidad máxima y 0 (incluidos).");
        } else if (capUmbral < 0 || capUmbral > 1) {
            throw new RuntimeException("El umbral debe estar entre 1.00 y 0.00 (incluidos).");
        } else if (tipo.equals("líquido") || tipo.equals("sólido")) {
            // Asinación de valores a atributos del objeto
            this.capMaxima = capMaxima;
            this.cantidad = cantidad;
            this.capUmbral = capUmbral;
            this.tipo = tipo;
        } else {
            throw new RuntimeException("El tipo debe ser 'líquido' o 'sólido'.");
        }
    }
    
    
}
