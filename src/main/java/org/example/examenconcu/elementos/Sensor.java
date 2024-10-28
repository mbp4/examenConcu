package org.example.examenconcu.elementos;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;


public class Sensor{
    private boolean[] pelotas;
    private int maxMediciones;
    private String tipo;
    private Gauss gauss;
    private int contador;
    private VentanaSensores vs;
    private Configurador configurador;

    public Sensor(String tipo, Configurador configurador, VentanaSensores vs){
        this.configurador = configurador;
        this.maxMediciones = configurador.getPelotas();
        this.pelotas = new boolean[maxMediciones];
        for (int i = 0; i < maxMediciones; i++){
            pelotas[i] = false;
        }
        this.tipo = tipo;
        gauss = new Gauss(0,configurador.getNiveles(),7);
        contador = 0;
        this.vs = vs;
    }


    public void medir(ScheduledExecutorService ejecutor){
        int medida = gauss.gaussianRandom();
        vs.muestraPelotas(medida);
        Random random = new Random();
        int pelota = random.nextInt(this.maxMediciones);
        while (pelotas[pelota] == true){
            pelota = random.nextInt(this.maxMediciones);
        }
        contador= pelota;
        pelotas[pelota] = true;
        System.out.println("La pelota número: " + contador + " ha caído en el deposito del tablero número: " + medida);

        Boolean terminado = true;

        for (int i = 0; i<pelotas.length; i++){
            if (pelotas[i] == false){
                terminado = false;
            }

        }

        if (terminado){
            ejecutor.shutdown();
            System.out.println("Fin del experimento");
        }
    }
}
