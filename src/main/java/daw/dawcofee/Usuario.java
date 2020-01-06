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
    private String username;
    private String password;
    
    // Constructor con control de requisitos

    public Usuario(String username, String password) {
        
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
        
        if (username.length() > 4 && username.length() < 21) {
            this.username = username;
        } else {
            throw new RuntimeException("El nombre de usuario de tener entre "
                    + "5 y 20 caracteres");
        }
        
        if (password.length() > 4 && password.length() < 31 
            && cantnumeros >= 3 && cantMayus >= 1) {
            this.password = password;
        } else {
            throw new RuntimeException("La contraseña debe tener entre 5 y 30 "
                    + "caracteres, de los cuales \nal menos 3 deben ser "
                    + "números y al menos 1 mayúscula");
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
