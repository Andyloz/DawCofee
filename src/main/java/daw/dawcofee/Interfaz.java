/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.ContrasenaIncorrectaExcepcion;
import daw.dawcofee.exceptions.DepositoInsuficienteExcepcion;
import daw.dawcofee.exceptions.SaldoInsuficienteExcepcion;
import daw.dawcofee.exceptions.UsuarioIncorrectoExcepcion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lozan
 */
public class Interfaz {
    
    static boolean validar(Cafetera cafetera) {
        
        // Inicialización de flujo de entrada
        
        Scanner sc = new Scanner(System.in);
        
        // Entrada de usuario y contraseña
        
        System.out.println("--------------------------------");
        
        if (cafetera.getUsuario() == null) {
            
            System.out.println("Cree una nueva cuenta de administrador:");
            System.out.println("El nombre de usuario de tener entre "
                     + "5 y 20 caracteres");
            System.out.println("La contraseña debe tener entre 5 y 30 "
                    + "caracteres, de los cuales \nal menos 3 deben ser "
                    + "números y al menos 1 mayúscula");
            System.out.println("");
            
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
                    cafetera.setUsuario(new Usuario(usuario,contrasena));
                    hecho = true;
                } catch (InputMismatchException e) {
                    System.out.println("Ingrese valores válidos");
                    System.out.println("");
                } catch (ContrasenaIncorrectaExcepcion | UsuarioIncorrectoExcepcion e) {
                    System.out.println(e.getMessage());
                    System.out.println("");
                }

            } while (!hecho);
            
            return true;

        } else {
            boolean hecho = false;
            
            String usuario;
            String contrasena;

            do {

                System.out.print("Usuario: ");
                usuario = sc.nextLine();
                    
                System.out.print("Contraseña: ");
                contrasena = sc.nextLine();

                if (cafetera.getUsuario().verificar(usuario, contrasena)) {
                    hecho = true;
                } else {
                    System.out.println("El Usuario o la contraseña "
                            + "no son correctos");
                    System.out.print("¿Intentar de nuevo? (s/n)");
                    
                    String salir;
                    boolean respuesta = false;
                    
                    do {
                    
                        salir = sc.nextLine();

                        if (salir.equalsIgnoreCase("s")) {
                            respuesta = true;
                        } else if (salir.equalsIgnoreCase("n")) {
                            return false;
                        } else {
                            System.out.print("Introduzca exactamente 's' (sí) "
                                    + "o 'n' (no): ");
                        }

                    } while (!respuesta);    
                    System.out.println("");
                    
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
        for (int i = 0; i < cafetera.getDepositos().size(); i++) {
            System.out.println("Depósito " + (i + 1) + ": "
                    + "Depósito de "
                    + cafetera.getDepositos().get(i).getContenido());
        }
        System.out.println("--------------------------------");

        boolean salir = false;
        int numDep = 0;

        System.out.println("");
        System.out.print("Ingrese el número de depósito a rellenar:");

        do {
            try {
                numDep = sc.nextInt();

                if (numDep >= 1 && numDep <= cafetera.getDepositos().size()) {
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
                cafetera.getDepositos().get(numDep).rellenar();
                break;
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
                    cafetera.getDepositos().get(numDep).rellenar(cantidad);
                } catch (RuntimeException e) {
                    System.out.println("EL depósito ha llegado a su "
                            + "máxima capacidad");
                    cafetera.getDepositos().get(numDep).rellenar();
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
                    
                    System.out.println("El usuario de administrador es: "
                        + cafetera.getUsuario().getUsername());
                    System.out.println("La contraseña de administrador es: " 
                        + cafetera.getUsuario().getPassword());
                    
                    System.out.println("");
                    
                    break;
                case 3:
                    
                    System.out.println("Saldo de ventas realizadas: " 
                            + Cajero.formatearDinero(cafetera.getCajero().getSaldoCajero().doubleValue()));
                    
                    break;
                case 4:
                    menuRelleno(cafetera);
                    break;
                case 5:
                    salir = true;
            }

        } while (!salir);
    }
    
    static void introducirDinero(Cafetera cafetera, BigDecimal minImporte, boolean opCancelar) throws SaldoInsuficienteExcepcion {
        
        // El método podrá ser configurado mediante el booleano opCancelar para 
        // que se pueda elegir no meter dinero. Para ello, el usuario deberá
        // introducir un 0 cuando se le pida dinero.        
        
        
        Scanner sc = new Scanner(System.in);
        BigDecimal ingreso = BigDecimal.valueOf(0.00); // Variable que almacena el dinero que metemos
        
        // Variable que guarda cada suma al ingreso
        BigDecimal buffer = BigDecimal.valueOf(0.00);
        
        if (opCancelar) {
            System.out.print("Introduzca dinero (decimales con coma).\n"
                             + "(Si quiere su dinero de vuelta, introduzca 0): ");
        } else {
            System.out.print("Introduzca dinero (decimales con coma): ");
        }
        
        do {  
            try {
                // Reinicio del buffer
                buffer = BigDecimal.valueOf(0.00);
                
                // Introducción por teclado del dinero
                buffer = BigDecimal.valueOf(sc.nextDouble());
                
                // Suma al ingreso del valor del buffer
                // Comprueba que el valor que se introduzca sea mayor que 0
                if (buffer.compareTo(BigDecimal.ZERO) > 0) {
                    ingreso = ingreso.add(buffer);
                } else {
                    throw new InputMismatchException();
                }
                
                if (opCancelar && ingreso.compareTo(BigDecimal.valueOf(0.00)) == 0) {
                    System.out.println("Dinero no introducido.");
                    // Se terminará la ejecución del método si introduce 0
                    throw new SaldoInsuficienteExcepcion();
                    
                } else if (ingreso.compareTo(minImporte) < 0) {
                    System.out.print("Introduzca al menos "
                            + Cajero.formatearDinero(minImporte.subtract(ingreso).doubleValue())
                            + " más: ");
                }
            } catch (InputMismatchException e) {
            // Si no introduce un double no saldrá del bulce
                System.out.print("Introducza una cantidad válida: ");
            } finally {
                sc.nextLine();
            }
        // Repetir mientras el saldo sea menor que el importe mínimo requerido
        } while(ingreso.compareTo(minImporte) < 0);
        System.out.println();
        System.out.println();
        
        // Añadir el importe al saldo si supera el importe mínimo
        cafetera.getCajero().sumarSaldo(ingreso);
    }
    
    static boolean siNo(String msg) {
        Scanner sc = new Scanner(System.in);
        String var = null;
        
        System.out.print(msg);
        do {
            var = sc.nextLine();
            
            if (!(var.equalsIgnoreCase("s") || var.equalsIgnoreCase("n"))) {
                System.out.print("Introduzca exactamente 's' (sí) o 'n' (no): ");
            }
        } while (!(var.equalsIgnoreCase("s") || var.equalsIgnoreCase("n")));
        return var.equalsIgnoreCase("s");
    }
    
    public static void main(String[] args) throws Exception {
        
                    ////////////////////
                    // Inicialización //
                    ////////////////////

        // Depósitos
        ArrayList<Deposito> depositos = null;
        depositos.add(new Deposito(new Materia(MateriaEnum.AGUA), 10000, 10000, 0.33));
        depositos.add(new Deposito(new Materia(MateriaEnum.CAFE), 10000, 10000, 0.33));
        depositos.add(new Deposito(new Materia(MateriaEnum.CAFE_DESCAFEINADO), 10000, 10000, 0.33));
        depositos.add(new Deposito(new Materia(MateriaEnum.CACAO), 10000, 10000, 0.33));
        depositos.add(new Deposito(new Materia(MateriaEnum.AZUCAR), 10000, 10000, 0.33));
        depositos.add(new Deposito(new Materia(MateriaEnum.SACARINA), 10000, 10000, 0.33));
        
        
        
        ///////////////
        // Productos //
        ///////////////
        
        ArrayList<Producto> productos = new ArrayList<Producto>();
        
        ////// Café //////
        
        ArrayList<Materia> materiasCafe = new ArrayList<Materia>();
        materiasCafe.add(new Materia(MateriaEnum.AGUA));
        materiasCafe.add(new Materia(MateriaEnum.CAFE));
        
        ArrayList<Double> cantidadesCafe = new ArrayList<Double>();
        cantidadesCafe.add(Double.valueOf(50));
        cantidadesCafe.add(Double.valueOf(6));
        
        productos.add(new Producto("Café", BigDecimal.valueOf(0.80), "1", materiasCafe, cantidadesCafe));
        
        ////// Café descafeinado //////
        
        ArrayList<Materia> materiasCafeDescafeinado = new ArrayList<Materia>();
        materiasCafeDescafeinado.add(new Materia(MateriaEnum.AGUA));
        materiasCafeDescafeinado.add(new Materia(MateriaEnum.CAFE_DESCAFEINADO));
        
        ArrayList<Double> cantidadesCafeDescafeinado = new ArrayList<Double>();
        cantidadesCafeDescafeinado.add(Double.valueOf(50));
        cantidadesCafeDescafeinado.add(Double.valueOf(6));
        
        productos.add(new Producto("Café descafeinado", BigDecimal.valueOf(0.80), "2", materiasCafeDescafeinado, cantidadesCafeDescafeinado));
        
        ////// Café largo //////
        
        ArrayList<Materia> materiasCafeLargo = new ArrayList<Materia>();
        materiasCafeLargo.add(new Materia(MateriaEnum.AGUA));
        materiasCafeLargo.add(new Materia(MateriaEnum.CAFE));
        
        ArrayList<Double> cantidadesCafeLargo = new ArrayList<Double>();
        cantidadesCafeLargo.add(Double.valueOf(100));
        cantidadesCafeLargo.add(Double.valueOf(12));
        
        productos.add(new Producto("Café largo", BigDecimal.valueOf(0.90), "3", materiasCafeLargo, cantidadesCafeLargo));
        
        ////// Café largo descafeinado //////
        
        ArrayList<Materia> materiasCafeLargoDescafeinado = new ArrayList<Materia>();
        materiasCafeLargoDescafeinado.add(new Materia(MateriaEnum.AGUA));
        materiasCafeLargoDescafeinado.add(new Materia(MateriaEnum.CAFE_DESCAFEINADO));
        
        ArrayList<Double> cantidadesCafeLargoDescafeinado = new ArrayList<Double>();
        cantidadesCafeLargoDescafeinado.add(Double.valueOf(100));
        cantidadesCafeLargoDescafeinado.add(Double.valueOf(12));
        
        productos.add(new Producto("Café largo descafeinado", BigDecimal.valueOf(0.90), "4", materiasCafeLargoDescafeinado, cantidadesCafeLargoDescafeinado));
        
        ////// Café con leche //////
        
        ArrayList<Materia> materiasCafeLeche = new ArrayList<Materia>();
        materiasCafeLeche.add(new Materia(MateriaEnum.AGUA));
        materiasCafeLeche.add(new Materia(MateriaEnum.CAFE));
        materiasCafeLeche.add(new Materia(MateriaEnum.LECHE));
        
        
        ArrayList<Double> cantidadesCafeLeche = new ArrayList<Double>();
        cantidadesCafeLeche.add(Double.valueOf(50));
        cantidadesCafeLeche.add(Double.valueOf(6));
        cantidadesCafeLeche.add(Double.valueOf(50));
        
        productos.add(new Producto("Café con leche", BigDecimal.valueOf(1.10), "5", materiasCafeLeche, cantidadesCafeLeche));
        
        ////// Café descafeinado con leche //////
        
        ArrayList<Materia> materiasCafeLecheDescafeinado = new ArrayList<Materia>();
        materiasCafeLecheDescafeinado.add(new Materia(MateriaEnum.AGUA));
        materiasCafeLecheDescafeinado.add(new Materia(MateriaEnum.CAFE_DESCAFEINADO));
        materiasCafeLecheDescafeinado.add(new Materia(MateriaEnum.LECHE));
        
        ArrayList<Double> cantidadesCafeLecheDescafeinado = new ArrayList<Double>();
        cantidadesCafeLecheDescafeinado.add(Double.valueOf(50));
        cantidadesCafeLecheDescafeinado.add(Double.valueOf(6));
        cantidadesCafeLecheDescafeinado.add(Double.valueOf(50));
        
        productos.add(new Producto("Café descafeinado con leche", BigDecimal.valueOf(1.10), "6", materiasCafeLecheDescafeinado, cantidadesCafeLecheDescafeinado));
        
        ////// Café cortado //////
        
        ArrayList<Materia> materiasCafeCortado = new ArrayList<Materia>();
        materiasCafeCortado.add(new Materia(MateriaEnum.AGUA));
        materiasCafeCortado.add(new Materia(MateriaEnum.CAFE));
        materiasCafeCortado.add(new Materia(MateriaEnum.LECHE));
        
        ArrayList<Double> cantidadesCafeCortado = new ArrayList<Double>();
        cantidadesCafeCortado.add(Double.valueOf(50));
        cantidadesCafeCortado.add(Double.valueOf(6));
        cantidadesCafeCortado.add(Double.valueOf(25));
        
        productos.add(new Producto("Café cortado", BigDecimal.valueOf(1.00), "7", materiasCafeCortado, cantidadesCafeCortado));
        
        ////// Café cortado descafeinado //////
        
        ArrayList<Materia> materiasCafeCortadoDescafeinado = new ArrayList<Materia>();
        materiasCafeCortadoDescafeinado.add(new Materia(MateriaEnum.AGUA));
        materiasCafeCortadoDescafeinado.add(new Materia(MateriaEnum.CAFE_DESCAFEINADO));
        materiasCafeCortadoDescafeinado.add(new Materia(MateriaEnum.LECHE));
        
        ArrayList<Double> cantidadesCafeCortadoDescafeinado = new ArrayList<Double>();
        cantidadesCafeCortadoDescafeinado.add(Double.valueOf(50));
        cantidadesCafeCortadoDescafeinado.add(Double.valueOf(6));
        cantidadesCafeCortadoDescafeinado.add(Double.valueOf(25));
        
        productos.add(new Producto("Café cortado descafeinado", BigDecimal.valueOf(1.00), "8", materiasCafeCortadoDescafeinado, cantidadesCafeCortadoDescafeinado));
        
        
        
        
        
        
        
        
        Cafetera cafetera = new Cafetera(depositos, productos, cajero);
        Scanner sc = new Scanner(System.in);
        int codigo = -1;
        Producto productoVenta = null;
        boolean reintentarVenta = false;
        
        while (true) {
            // Reinicio de valores
            int opcion = 0; // Selección de venta/administración
            codigo = -1; // Código de producto
            
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
                            + " (" + Cajero.formatearDinero(producto.getPrecio().doubleValue()) + ")");
                }
                System.out.println("");
                
                
                // Pedir código
                System.out.println("Introduzca un número de código.");
                System.out.print("(Si quiere su dinero de vuelta, introduzca 0): ");
                do {
                    try {
                        codigo = sc.nextInt();
                        
                        if (codigo < 0 || codigo > cafetera.getProductos().size()) {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Introducza un código válido: ");
                    } finally {
                        sc.nextLine();
                    }
                } while (codigo < 0 || codigo > cafetera.getProductos().size());
                System.out.println("-----------------------------");
                System.out.println("");
                
                if (codigo != 0) {
                    // Seleccionamos el producto
                    productoVenta = cafetera.getProductos().get(codigo - 1);
                    // Y procedemos a la venta
                    do {
                        reintentarVenta = false;
                        try {
                            cafetera.venta(productoVenta);
                            
                            System.out.println(productoVenta.toString());
                        } catch (SaldoInsuficienteExcepcion e) {
                            System.out.println("El saldo no es suficiente \n" +
                                    "Le falta " +
                                    Cajero.formatearDinero(productoVenta.getPrecio().subtract(cafetera.getCajero()
                                            .getSaldoCliente()).doubleValue()) + " más.");
                            if (siNo("¿Le gustaría introducir más dinero? s/n: ")) {
                                try {
                                    introducirDinero(cafetera, productoVenta.getPrecio()
                                            .subtract(cafetera.getCajero().getSaldoCliente()), true);
                                    
                                    cafetera.venta(productoVenta);
                                    System.out.println(productoVenta.toString());
                                } catch (SaldoInsuficienteExcepcion f) {
                                    reintentarVenta = false;
                                }
                            } else {
                                System.out.println();
                                System.out.println("Venta no realizada.");
                            }
                        } catch (DepositoInsuficienteExcepcion e) {
                            System.out.println(e.getMessage());
                        }
                    } while (reintentarVenta);
                    
                // Si de código se ha introducido 0, terminar la venta
                } else {
                    System.out.println();
                    System.out.println("Venta no realizada");
                }

                System.out.println("Cambio: "+Cajero.formatearDinero(cafetera.getCajero().getSaldoCliente().doubleValue()));
                cafetera.getCajero().reiniciarSaldo();
                
                        /////////////////////////////////
                        // Fin de venta (vuelta atrás) //
                        /////////////////////////////////
                
            } else if (opcion == 2 && validar(cafetera)) {
                administracion(cafetera);
            }
        }
    }
}
