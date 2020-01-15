/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lozan
 */
public class Interfaz {
    
    static void administracion() {
        
        // Inicialización de flujo de entrada
        
        Scanner sc = new Scanner(System.in);
        
        // Entrada de usuario y contraseña
        
        System.out.println("--------------------------------");
        
        boolean hecho = false;
        
        do {

            System.out.print("Usuario: ");

            try {
                Usuario.verificarUsuario(sc.nextLine());
                hecho = true;
            } catch (InputMismatchException e) {
                System.out.println(e);
                System.out.println("");
            } catch (RuntimeException e) {
                System.out.println(e);
                System.out.println("");
            }

        } while (!hecho);
        
        do {
 
            System.out.print("Contraseña: ");
            hecho = false;

            try {
                Usuario.verificarUsuario(sc.nextLine());
            } catch (InputMismatchException e) {
                System.out.println(e);
                System.out.println("");
            } catch (RuntimeException e) {
                System.out.println(e);
                System.out.println("");
            }

        } while (!hecho);
    }
    
    static boolean introducirDinero(Cafetera cafetera, double minImporte, boolean cancelar) {
        Scanner sc = new Scanner(System.in);
        double var = 0.0;
        
        boolean cancelada = false; // Comprobador de cancelación
        do {
            System.out.println("Introduzca dinero (decimales con coma):");
            if (cancelar) {
                System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
            }
            try {
                var = sc.nextDouble();
                cafetera.getCajero().añadirSaldo(var);
                if (cancelar && var == 0) {
                    System.out.println("Dinero no introducido.");
                    cancelada = true;
                    minImporte = -1;
                } else if (cafetera.getCajero().getSaldoCliente() < minImporte) {
                    System.out.println("Introduzca al menos "+Cajero.formatearDinero(minImporte)+".");
                    sc.nextLine();
                    System.out.println("----------------------------");
                }
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Introducza una cantidad válida.");
                System.out.println("--------------------------------");
                System.out.println();
                sc.nextLine();
            }
        } while(cafetera.getCajero().getSaldoCliente() < minImporte);
        return cancelada;
    }
    
    static boolean siNo(String msg) {
        Scanner sc = new Scanner(System.in);
        String var = null;
        
        do {
            System.out.println(msg);
            var = sc.nextLine();
            
            if (!(var.equalsIgnoreCase("s") || var.equalsIgnoreCase("n"))) {
                System.out.println("Introduzca exactamente 's' (sí) o 'n' (no)");
                System.out.println();
            }
        } while (!(var.equalsIgnoreCase("s") || var.equalsIgnoreCase("n")));
        return var.equalsIgnoreCase("s");
    }
    
    public static void main(String[] args) throws Exception {
        Cafetera cafetera = new Cafetera();
        Scanner sc = new Scanner(System.in);
        int codigo = 0;
        Producto productoVenta = null;
        boolean reintentarVenta = false;
        
        while (true) {
            System.out.println("   ______      ____     __                 ");
            System.out.println("  / ____/___ _/ __/__  / /____  _________ _");
            System.out.println(" / /   / __ `/ /_/ _ \\/ __/ _ \\/ ___/ __ `/");
            System.out.println("/ /___/ /_/ / __/  __/ /_/  __/ /  / /_/ / ");
            System.out.println("\\____/\\__,_/_/  \\___/\\__/\\___/_/   \\__,_/  ");
            System.out.println("----------------------------------------");
            
            System.out.println("1. Venta de productos");
            System.out.println("2. Administración de la cafetera");
            System.out.println("----------------------------------------");
            System.out.print("Seleccione opción: ");
            int opcion = 0;
            
            // Repetir mientras no se de una opción mostrada en el inicio
            // Si la opción es 1, comenzará una venta, si es 2 irá
            // al menú de administración
            
            do {
                try {
                    opcion = sc.nextInt();
                    System.out.println("");

                    if (opcion != 1 && opcion != 2) {
                        opcion = 0;
                        System.out.print("Introduzca una opción válida: ");
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Introduzca una opción válida: ");
                }
            } while (opcion == 0);
            sc.nextLine();
            
            if (opcion == 1) {
                // Repetir mientras no se introduzca como mínimo el precio por el
                // producto más barato
                introducirDinero(cafetera, cafetera.productoMasBarato().getPrecio(), false);

                // Catálogo de productos
                System.out.println("Productos");
                System.out.println("-----------------------------");
                for (Producto producto : cafetera.getProductos()) {
                    System.out.println(producto.getCodigo()
                            + ". " + producto.getNombre()
                            + " (" + Cajero.formatearDinero(producto.getPrecio()) + ")");
                }
                System.out.println("");

                // Repetir mientras no se introduzca un código válido
                do {
                    System.out.println("Introduzca un número de código:");
                    System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
                    try {
                        codigo = sc.nextInt();
                        sc.nextLine();
                        // Seleccionamos el producto en el array
                        productoVenta = cafetera.getProductos()[codigo - 1];
                        // Venta
                        do {
                            try {
                                cafetera.venta(productoVenta);
                                reintentarVenta = false;
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                                // Si el usuario no tiene saldo suficiente, darle la 
                                // opción de introducir más dinero
                                if (e.getMessage().equals("El saldo no es suficiente.")) {
                                    System.out.println("Le faltan " + (productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente())
                                            + " € más.");
                                    if (siNo("¿Le gustaría introducir más dinero? (s/n)")) {
                                        if (!introducirDinero(cafetera, productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente(), true)) {
                                            System.out.println("Reintentando venta...");
                                            reintentarVenta = true;
                                        } else {
                                            System.out.println("Venta no realizada");
                                        }
                                    } else {
                                        System.out.println("Venta no realizada.");
                                    }
                                }
                            }
                        } while (reintentarVenta);
                    } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                        if (codigo != 0) {
                            System.out.println("Introducza un código válido!!");
                            System.out.println("-----------------------------");
                            System.out.println();
                            sc.nextLine();
                        }
                    }
                } while (codigo < 0 || codigo > cafetera.getProductos().length);
                System.out.println(productoVenta.toString());

                // Parte final del ciclo
                codigo = 0;
                System.out.println();
                System.out.println();
                System.out.println();

            } else if (opcion == 2) {
                administracion();
            }

        }
    }
}
