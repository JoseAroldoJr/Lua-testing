package Objetos;

import java.io.Serializable;

public abstract class Variavel implements Serializable, Comparable<Variavel>{

    private String Nome;
    private String Tipo;

    public Variavel(String Tipo, String Nome) {
        this.Nome = Nome;
        this.Tipo = Tipo;
    }

    public String getNome() {
        return Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return "Variavel{" + "Nome=" + Nome + ", Tipo=" + Tipo + '}';
    }

}
