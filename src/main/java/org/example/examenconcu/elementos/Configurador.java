package org.example.examenconcu.elementos;

public class Configurador {
    private int pelotas;
    private int niveles;

    public Configurador(int pelotas, int niveles){
        this.pelotas = pelotas;
        this.niveles = niveles;
    }

    public Configurador(){
        this.pelotas = 100;
        this.niveles = 10;
    }

    public int getPelotas() {
        return pelotas;
    }

    public void setPelotas(int pelotas) {
        this.pelotas = pelotas;
    }

    public int getNiveles() {
        return niveles;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }
}
