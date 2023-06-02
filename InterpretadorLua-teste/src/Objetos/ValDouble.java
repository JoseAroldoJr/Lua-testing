/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;

public class ValDouble extends Variavel implements Serializable {

    private Double Valor;

    public ValDouble(Double Valor, String Tipo, String Nome) {
        super(Tipo, Nome);
        this.Valor = Valor;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    @Override
    public String toString() {
        return Valor+"";
    }

    @Override
    public int compareTo(Variavel o) {
        return super.getNome().compareTo(o.getNome());
    }

}
