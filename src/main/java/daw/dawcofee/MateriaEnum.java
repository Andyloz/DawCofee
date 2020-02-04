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
public enum MateriaEnum {

    AGUA("agua","líquido"),
    LECHE("leche","líquido"),
    CAFE("café","sólido"),
    CAFE_DESCAFEINADO("café descafeinado","sólido"),
    CACAO("cacao","sólido"),
    AZUCAR("azúcar","sólido"),
    SACARINA("sacarina","sólido");

    private final String nombre;
    private final String tipo;

    private MateriaEnum(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    protected String getNombre() {
        return nombre;
    }

    protected String getTipo() {
        return tipo;
    }

}
