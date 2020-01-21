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
    
    static boolean validar() {
        
        // Inicialización de flujo de entrada
        
        Scanner sc = new Scanner(System.in);
        
        // Entrada de usuario y contraseña
        
        System.out.println("--------------------------------");
        
        if (Usuario.getUsername() == null || 
            Usuario.getPassword() == null) {
            
            System.out.println("Cree una nueva cuenta de administrador:");
            System.out.println("El nombre de usuario de tener entre "
                     + "5 y 20 caracteres");
            System.out.println("La contraseña debe tener entre 5 y 30 "
                    + "caracteres, de los cuales \nal menos 3 deben ser "
                    + "números y al menos 1 mayúscula");
            
            String usuario;
            String contrasena;
            
            // Entrada de nuevas credenciales
            
            boolean hecho = false;

            do {

                System.out.print("Usuario: ");
                usuario = sc.nextLine();
                System.out.print("Contraseña: ");
                contrasena = sc.nextLine();
                
                try {
                    Usuario.crearUsuario(usuario, contrasena);
                    hecho = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese valores válidos");
                    System.out.println("");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println("");
                }

            } while (!hecho);
            
            return true;

        } else {
            boolean hecho = false;

            do {

                System.out.print("Usuario: ");

                if (Usuario.verificarUsuario(sc.nextLine())) {
                    hecho = true;
                }
                
            } while (!hecho);

            do {

                hecho = false;

                if (Usuario.verificarContrasena(sc.nextLine())) {
                    hecho = true;
                }

            } while (!hecho);
            
            return true;
        }
        
    }
    
    static void administracion() {
        
        // Inicialización de entrada por teclado
        
        Scanner sc = new Scanner(System.in);
        
        boolean salir;
        
        do {
        
            salir = false;
        
            // Menú de administración
            
            System.out.println("--------------------------------");
            System.out.println("1. Comprobar depósitos");
            System.out.println("2. Comprobar estado general");
            System.out.println("3. Consultar saldo de ventas realizadas");
            System.out.println("4. Rellenar depósitos");
            System.out.println("5. Salir");
            System.out.println("--------------------------------");

            // Elección de opciones
            
            int opcion;

            do {

                opcion = 0;

                try {
                    System.out.print("Ingrese una opción: ");
                    opcion = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese un valor válido");
                }

                if (opcion >= 1 || opcion <= 5) {
                    System.out.println("Ingrese una opción válida");
                }

            } while (opcion >= 1 || opcion <= 5);

            // Opciones
            
            switch (opcion) {
                case 1:
                    System.out.println("");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    salir = true;
            }

        } while (salir = false);
    }
    
    static boolean introducirDinero(Cafetera cafetera, double minImporte, boolean opCancelar) {
        
        // El método podrá ser configurado mediante el booleano opCancelar para 
        // que se pueda elegir no meter dinero. Para ello, el usuario deberá
        // introducir un 0 cuando se le pida dinero.        
        
        
        Scanner sc = new Scanner(System.in);
        double ingreso = 0.0; // Variable que almacena el dinero que metemos
        
        // Se registra al inicio del método el saldo del cliente
        double saldoInicial = cafetera.getCajero().getSaldoCliente();
        
        boolean cancelada = false; // Comprobante de cancelación
        
        do {
            System.out.println("Introduzca dinero (decimales con coma):");
            if (opCancelar) {
                System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
            }
            try {
                // Introducción por teclado del dinero
                ingreso = sc.nextDouble();
                // ...añadido al saldo actual
                cafetera.getCajero().añadirSaldo(ingreso);
                
                if (opCancelar && ingreso == 0) {
                    System.out.println("Dinero no introducido.");
                    // Este valor será devuelto como comprobante de que se ha
                    // elegido no meter dinero
                    cancelada = true;
                    // Con esta sentencia conseguimos que el saldo del cliente
                    // siempre sea mayor que el importe mínimo, por lo que 
                    // saldrá del bucle
                    minImporte = -1;
                    
                } else if (cafetera.getCajero().getSaldoCliente() < minImporte) {
                    System.out.println("Introduzca al menos "+Cajero.formatearDinero(minImporte)+".");
                    // Vuelta del saldo a su cantidad inicial
                    cafetera.getCajero().setSaldoCliente(saldoInicial);
                    sc.nextLine();
                    System.out.println("----------------------------");
                }
                System.out.println();
                
            } catch (InputMismatchException e) {
            // Si no introduce un double no saldrá del bulce
                System.out.println("Introducza una cantidad válida.");
                System.out.println("--------------------------------");
                System.out.println();
                sc.nextLine();
            }
        // Repetir mientras el saldo sea menor que el importe mínimo requerido
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
            // Reinicio de valores
            int opcion = 0; // Selección de venta/administración
            codigo = 0; // Código de producto
            
            // Espacio entre repeticiones del ciclo
            System.out.println();
            System.out.println();
            System.out.println();
            
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
            
            // Repetir mientras opcion no sea 1 o 2 (ir a venta o adminstración)
            do {
                try {
                    // Pedir opcion por teclado
                    opcion = sc.nextInt();
                    System.out.println("");

                    if (opcion != 1 && opcion != 2) {
                        // No saldrá del bulce
                        opcion = 0;
                        System.out.print("Introduzca una opción válida: ");
                    }
                } catch (InputMismatchException e) {
                // Si no introduce un int no saldrá del bucle
                    System.out.print("Introduzca una opción válida: ");
                }
            } while (opcion == 0);
            sc.nextLine();
            
            
            ///////     Entrada a venta o administración     ///////
            
            
            if (opcion == 1) {
                
                        ///////////////////////
                        // Comienzo de venta //
                        ///////////////////////
                
                // Repetir mientras no se introduzca como mínimo el precio por 
                // el producto más barato                     |
                //                         ___________________V____________________
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

                System.out.println("¡¡ Programar el devolver dinero !!");
                cafetera.getCajero().reiniciarSaldo();
                
                        /////////////////////////////////
                        // Fin de venta (vuelta atrás) //
                        /////////////////////////////////
                
            } else if (opcion == 2 && validar()) {
                administracion();
            }
        }
    }
}
