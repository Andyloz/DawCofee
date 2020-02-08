/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.dawcofee;

import daw.dawcofee.exceptions.ContrasenaIncorrectaExcepcion;
import daw.dawcofee.exceptions.UsuarioIncorrectoExcepcion;

/**
 *
 * @author sander
 */
public class Usuario {
    private String username;
    private String password;
    
    // Método que crea el usuario y contraseña por primera vez

    public Usuario(String username, String password) throws UsuarioIncorrectoExcepcion, 
            ContrasenaIncorrectaExcepcion {
        
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
            throw new UsuarioIncorrectoExcepcion(username);
        }
        
        if (((password.length() <= 4 || password.length() >= 31) 
                || cantnumeros < 3) || cantMayus < 1) {
            
            throw new ContrasenaIncorrectaExcepcion(password);
        }
        
        if ((username.length() > 4 && username.length() < 21) && 
            (password.length() > 4 && password.length() < 31 
                && cantnumeros >= 3 && cantMayus >= 1)) {
            
            this.username = username;
            this.password = password;
        }
    }
    
    // Método que verifica las credenciales
    
    public boolean verificar(String usuario, String password){
        return this.username.equals(usuario) &&
               this.password.equals(password);   
    }
    
    // Getters y Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ContrasenaIncorrectaExcepcion {
        
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
            this.password = password;
        } else {
            throw new ContrasenaIncorrectaExcepcion(password);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws UsuarioIncorrectoExcepcion {
        
        if (username.length() > 4 && username.length() < 21) {
            this.username = username;
        } else {
            throw new UsuarioIncorrectoExcepcion(username);
        }
    }
    
}
