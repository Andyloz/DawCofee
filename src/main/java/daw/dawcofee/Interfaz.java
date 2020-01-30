/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.DepositoInsuficienteExcepcion;
import daw.dawcofee.exceptions.SaldoInsuficienteExcepcion;
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
    
    // Método que contiene los procesos y salidas de la opción de relleno de
    // Depósitos en el menú de administración
    
    static void menuRelleno(Cafetera cafetera) {
        
        // Inicialización de entrada por teclado
        
        Scanner sc = new Scanner(System.in);

        // Elección del depósito a rellenar
        
        System.out.println("");
        System.out.println("--------------------------------");
        for (int i = 0; i < cafetera.getDepositos().length; i++) {
            System.out.println("Depósito " + (i + 1) + ": "
                    + "Depósito de "
                    + cafetera.getDepositos()[i].getContenido());
        }
        System.out.println("--------------------------------");

        boolean salir = false;
        int numDep = 0;

        System.out.println("");
        System.out.print("Ingrese el número de depósito a rellenar:");

        do {
            try {
                numDep = sc.nextInt();

                if (numDep >= 1 && numDep <= cafetera.getDepositos().length) {
                    salir = true;
                } else {
                    System.out.println("El depósito no existe");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese un valor válido");
                sc.nextLine();
            }
        } while (!salir);
        sc.nextLine();

        // Menú de opciones
        
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("1. Rellenar completo");
        System.out.println("2. Rellenar con cantidad fija");
        System.out.println("--------------------------------");

        // Elección de opciones
        
        int opcion;

        do {

            opcion = 0;

            try {
                System.out.print("Ingrese una opción: ");
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un valor válido");
                sc.nextLine();
            }

            if (opcion < 1 || opcion > 2) {
                System.out.println("Ingrese una opción válida");
            }

        } while (opcion < 1 || opcion > 2);
        sc.nextLine();

        // Opciones
        
        switch (opcion) {
            case 1:
                cafetera.getDepositos()[numDep].rellenar();
            case 2:
                
                // Ingreso de la cantidad a ingresar
                
                System.out.print("Ingrese una cantidad: ");
                
                salir = false;
                double cantidad = 0;
                do {
                    try {
                        cantidad = sc.nextDouble();

                        if (cantidad >= 0) {
                            salir = true;
                        } else {
                            System.out.println("La cantidad no puede "
                                    + "ser negativa");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Ingrese un valor válido");
                        sc.nextLine();
                    }
                } while (!salir);
                sc.nextLine();
                
                // Si la suma supera el máximo, el depósito se queda en el
                // máximo

                try {
                    cafetera.getDepositos()[numDep].rellenar(cantidad);
                } catch (RuntimeException e) {
                    System.out.println("EL depósito ha llegado a su "
                            + "máxima capacidad");
                    cafetera.getDepositos()[numDep].rellenar();
                }
        }
    }
    
    // Método que contiene las acciones y salidas del menú de administración
    
    static void administracion(Cafetera cafetera) {
        
        // Inicialización de entrada por teclado
        
        Scanner sc = new Scanner(System.in);
        
        boolean salir;
        
        do {
        
            salir = false;
        
            // Menú de administración
            
            System.out.println("");
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
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese un valor válido");
                    sc.nextLine();
                }

                if (opcion < 1 || opcion > 5) {
                    System.out.println("Ingrese una opción válida");
                }

            } while (opcion < 1 || opcion > 5);
            sc.nextLine();
            
            // Opciones
            
            switch (opcion) {
                case 1:
                    
                    // Bucle que imprimirá el estado de cada depósito
                    
                    System.out.println("");
                    
                    for (Deposito deposito : cafetera.getDepositos()) {
                        System.out.println("Depósito de " 
                                + deposito.getContenido()
                                + ": " + deposito.isIndicadorUmbrF());
                    }
                    
                    System.out.println("");
                    
                    break;
                case 2:
                    
                    // Sentencia que informará del estado general del sistema de
                    // depósitos y la cuenta de administrador
                    
                    System.out.println("");
                    
                    // Bucle para informar de todos los depósitos del array
                    
                    for (Deposito deposito : cafetera.getDepositos()) {
                        System.out.println(deposito.toString());
                        System.out.println("");
                    }
                    
                    System.out.println("El usuario de administrador es :"
                        + Usuario.getUsername());
                    System.out.println("La contraseña de administrador es: " 
                            + Usuario.getPassword());
                    
                    System.out.println("");
                    
                    break;
                case 3:
                    
                    System.out.println("Saldo de ventas realizadas: " 
                            + Cajero.formatearDinero(cafetera.getCajero().getDinero()));
                    
                    break;
                case 4:
                    menuRelleno(cafetera);
                    break;
                case 5:
                    salir = true;
            }

        } while (!salir);
    }
    
    static void introducirDinero(Cafetera cafetera, double minImporte, boolean opCancelar) throws SaldoInsuficienteExcepcion {
        
        // El método podrá ser configurado mediante el booleano opCancelar para 
        // que se pueda elegir no meter dinero. Para ello, el usuario deberá
        // introducir un 0 cuando se le pida dinero.        
        
        
        Scanner sc = new Scanner(System.in);
        double ingreso = 0.0; // Variable que almacena el dinero que metemos
        
        do {
            System.out.println("Introduzca dinero (decimales con coma):");
            if (opCancelar) {
                System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
            }
            try {
                // Introducción por teclado del dinero
                ingreso = sc.nextDouble();
                
                if (opCancelar && ingreso == 0) {
                    System.out.println("Dinero no introducido.");
                    // Se terminará la ejecución del método si introduce 0
                    throw new SaldoInsuficienteExcepcion();
                    
                } else if (ingreso + cafetera.getCajero().getSaldoCliente() < minImporte) {
                    System.out.println("Introduzca al menos "+Cajero.formatearDinero(minImporte)+".");
                    System.out.println("----------------------------");
                }
                System.out.println();
                
            } catch (InputMismatchException e) {
            // Si no introduce un double no saldrá del bulce
                System.out.println("Introducza una cantidad válida.");
                System.out.println("--------------------------------");
                System.out.println();
            } finally {
                sc.nextLine();
            }
        // Repetir mientras el saldo sea menor que el importe mínimo requerido
        } while(ingreso + cafetera.getCajero().getSaldoCliente() < minImporte);
        
        // Finalmente, añadir el importe al saldo si supera el importe mínimo
        if (ingreso >= minImporte) {
            cafetera.getCajero().añadirSaldo(ingreso);
        }
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
                } finally {
                    sc.nextLine();
                }
            } while (opcion == 0);
            
            
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
                
                
                // Pedir código
                do {
                    System.out.println("Introduzca un número de código:");
                    System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
                    try {
                        codigo = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Introducza un código válido!!");
                        System.out.println("-----------------------------");
                        System.out.println();
                    } finally {
                        sc.nextLine();
                    }
                } while (codigo < 0 || codigo > cafetera.getProductos().length);
                
                
                if (codigo != 0) {
                    // Seleccionamos el producto
                    productoVenta = cafetera.getProductos()[codigo - 1];
                    // Y procedemos a la venta
                    do {
                        reintentarVenta = false;
                        try {
                            cafetera.venta(productoVenta);
                        } catch (SaldoInsuficienteExcepcion e) {
                            System.out.println("El saldo no es suficiente \n" +
                                    "Le falta " +
                                    Cajero.formatearDinero(productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente()) + " € más. \n");
                            if (siNo("¿Le gustaría introducir más dinero? s/n")) {
                                try {
                                    introducirDinero(cafetera, productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente(), true);
                                } catch (SaldoInsuficienteExcepcion f) {
                                    reintentarVenta = false;
                                }
                            } else {
                                System.out.println("Venta no realizada.");
                            }
                        } catch (DepositoInsuficienteExcepcion e) {
                            System.out.println("DEBUG excepcion e");
                        }
                    } while (reintentarVenta);
                    
                // Si de código se ha introducido 0, terminar la venta
                } else {
                    System.out.println("Venta no realizada");
                    cafetera.getCajero().reiniciarSaldo();
                }
                
                

//                // Repetir mientras no se introduzca un código válido
//                do {
//                    System.out.println("Introduzca un número de código:");
//                    System.out.println("(Si quiere su dinero de vuelta, introduzca 0)");
//                    try {
//                        codigo = sc.nextInt();
//                        sc.nextLine();
//                        // Seleccionamos el producto en el array
//                        productoVenta = cafetera.getProductos()[codigo - 1];
//                        // Venta
//                        do {
//                            try {
//                                cafetera.venta(productoVenta);
//                                reintentarVenta = false;
//                            } catch (RuntimeException e) {
//                                System.out.println(e.getMessage());
//                                // Si el usuario no tiene saldo suficiente, darle la 
//                                // opción de introducir más dinero
//                                if (e.getMessage().equals("El saldo no es suficiente.")) {
//                                    System.out.println("Le faltan " + (productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente())
//                                            + " € más.");
//                                    if (siNo("¿Le gustaría introducir más dinero? (s/n)")) {
//                                        if (!introducirDinero(cafetera, productoVenta.getPrecio() - cafetera.getCajero().getSaldoCliente(), true)) {
//                                            System.out.println("Reintentando venta...");
//                                            reintentarVenta = true;
//                                        } else {
//                                            System.out.println("Venta no realizada");
//                                        }
//                                    } else {
//                                        System.out.println("Venta no realizada.");
//                                    }
//                                }
//                            }
//                        } while (reintentarVenta);
//                    } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
//                        if (codigo != 0) {
//                            System.out.println("Introducza un código válido!!");
//                            System.out.println("-----------------------------");
//                            System.out.println();
//                            sc.nextLine();
//                        }
//                    }
//                } while (codigo < 0 || codigo > cafetera.getProductos().length);
                System.out.println(productoVenta.toString());

                System.out.println("¡¡ Programar el devolver dinero !!");
                cafetera.getCajero().reiniciarSaldo();
                
                        /////////////////////////////////
                        // Fin de venta (vuelta atrás) //
                        /////////////////////////////////
                
            } else if (opcion == 2 && validar()) {
                administracion(cafetera);
            }
        }
    }
}
