/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Random;

/**
 *
 * @author Elias Rodelo
 */
public class Dado {
    private int Valor;
    private int Max = 10;

    public Dado(int max) {
        this.Max = max;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public int getMax() {
        return Max;
    }

    public void setMax(int max) {
        this.Max = max;
    }
    
    public int getRandom(){
        Random ran = new Random();
        int tem = ran.nextInt(this.Max);
        return tem++;
    }
}
