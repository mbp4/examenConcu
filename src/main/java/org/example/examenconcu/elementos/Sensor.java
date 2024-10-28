package org.example.examenconcu.elementos;

import javax.swing.*;
import java.util.concurrent.ScheduledExecutorService;


public class Sensor{
    private int maxMediciones;
    private String tipo;
    private Gauss gauss;
    private int contador;
    private VentanaSensores vs;

    public Sensor(String tipo, int maxMediciones, VentanaSensores vs){
        this.maxMediciones = maxMediciones;
        this.tipo = tipo;
        gauss = new Gauss(0,20,7);
        contador = 0;
        this.vs = vs;
    }


    public void medir(ScheduledExecutorService ejecutor){
        int medida = gauss.gaussianRandom();
        //System.out.println("Medidicion" + (contador + 1) + "Tipo " + tipo + ": " + medida);
        vs.muestraPelotas(medida);
        contador++;
        if (contador == maxMediciones){
            ejecutor.shutdown();
        }
    }
}
