package Objetos;

import java.io.Serializable;

public class ValString extends Variavel implements Serializable {

    private String Valor;

    public ValString(String Valor, String Tipo, String Nome) {
        super(Tipo, Nome);
        this.Valor = Valor;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    @Override
    public String toString() {
        return(Valor.contains("\'")?Valor.replaceAll("\'",""):Valor.replaceAll("\"",""));
    }

    @Override
    public int compareTo(Variavel o) {
        return super.getNome().compareTo(o.getNome());
    }

}
