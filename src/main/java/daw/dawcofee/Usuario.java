/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

/**
 *
 * @author sander
 */
public class Usuario {
    private static String username;
    private static String password;
    
    // Método que crea el usuario y contraseña por primera vez

    public static void crearUsuario(String username, String password) {
        
        // Bucle for en el que se comprobará que la contraseña cumple con los
        // requisitos definidos revisando cada char que compone el String
        
        int cantnumeros = 0;
        int cantMayus = 0;
        String clave;
        
        for (int i = 0; i < password.length(); i++) {
            
            // Paso de char a String por medio del método de clase valueOf
            
            clave = String.valueOf(password.charAt(i));
            
            if (clave.matches("[0-9]")) {
                cantnumeros++;
            } else if (clave.matches("[A-Z]")) {
                cantMayus++;
            }
        }
        
        if (username.length() <= 4 || username.length() >= 21) {
            throw new RuntimeException("El nombre de usuario de tener entre "
                    + "5 y 20 caracteres");
        }
        
        if (((password.length() <= 4 || password.length() >= 31) 
                || cantnumeros < 3) || cantMayus < 1) {
            
            throw new RuntimeException("La contraseña debe tener entre 5 y 30 "
                    + "caracteres, de los cuales \nal menos 3 deben ser "
                    + "números y al menos 1 mayúscula");
        }
        
        if ((username.length() > 4 && username.length() < 21) && 
            (password.length() > 4 && password.length() < 31 
                && cantnumeros >= 3 && cantMayus >= 1)) {
            
            Usuario.username = username;
            Usuario.password = password;
        }
    }
    
    // Método que verifica las credenciales
    
    public static boolean verificar(String usuario, String password){
        return Usuario.username.equals(usuario) &&
               Usuario.password.equals(password);   
    }
    
    // Getters y Setters

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        
        // Bucle for en el que se comprobará que la contraseña cumple con los
        // requisitos definidos revisando cada char que compone el String
        
        int cantnumeros = 0;
        int cantMayus = 0;
        String clave;
        
        for (int i = 0; i < password.length(); i++) {
            
            // Paso de char a String por medio del método de clase valueOf
            
            clave = String.valueOf(password.charAt(i));
            
            if (clave.matches("[0-9]")) {
                cantnumeros++;
            } else if (clave.matches("[A-Z]")) {
                cantMayus++;
            }
        }
        
        if (password.length() > 4 && password.length() < 31 
            && cantnumeros >= 3 && cantMayus >= 1) {
            Usuario.password = password;
        } else {
            throw new RuntimeException("La contraseña debe tener entre 5 y 30 "
                    + "caracteres, de los cuales \nal menos 3 deben ser "
                    + "números y al menos 1 mayúscula");
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        
        if (username.length() > 4 && username.length() < 21) {
            Usuario.username = username;
        } else {
            throw new RuntimeException("El nombre de usuario de tener entre "
                    + "5 y 20 caracteres");
        }
    }
    
}
