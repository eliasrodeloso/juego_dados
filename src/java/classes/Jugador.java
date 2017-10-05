/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Elias Rodelo
 */
public class Jugador {
    private String Nombre;
    private String Apellido;
    private int Apuesta;

    public Jugador(String Nombre, String Apellido, int Apuesta) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        if(Apuesta <= 10000) {
            this.Apuesta = Apuesta;
        } else {
            this.Apuesta = 10000;
        }
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getApuesta() {
        return Apuesta;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setApuesta(int Apuesta) {
        this.Apuesta = Apuesta;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "" + this.Nombre + " " + this.Apellido + " apuesta " + this.Apuesta;
    }
    
    
    
}
