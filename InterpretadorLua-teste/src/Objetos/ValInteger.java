/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;

public class ValInteger extends Variavel implements Serializable {

    private int Valor;

    public ValInteger(int Valor, String Tipo, String Nome) {
        super(Tipo, Nome);
        this.Valor = Valor;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    @Override
    public String toString() {
        return  Valor + "";
    }

    @Override
    public int compareTo(Variavel o) {
        return super.getNome().compareTo(o.getNome());
    }

}
