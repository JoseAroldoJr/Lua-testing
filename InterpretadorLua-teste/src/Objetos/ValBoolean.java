/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;

public class ValBoolean extends Variavel implements Serializable {

    private boolean Valor;

    public ValBoolean(boolean Valor, String Tipo, String Nome) {
        super(Tipo, Nome);
        this.Valor = Valor;
    }

    public boolean isValor() {
        return Valor;
    }

    public void setValor(boolean Valor) {
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
