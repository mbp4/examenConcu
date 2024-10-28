package org.example.examenconcu.elementos;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class Maquina {
    private Gauss gauss;
    private int contador;
    private VentanaSensores vs;
    private Configurador configurador;
    private int contPelotas = 0;
    private int contNiveles = 0;

    public Maquina(Configurador configurador, VentanaSensores vs){
        this.configurador = configurador;
        gauss = new Gauss(0,configurador.getNiveles(),7);
        this.vs = vs;

    }

    public void construir(ScheduledExecutorService ejecutor){
        Random random = new Random();
        if (contNiveles < configurador.getNiveles()) {
            try {
                Thread.sleep(random.nextInt(1500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Construyendo nivel numero: " + contNiveles);
            contNiveles++;
            if (contNiveles == configurador.getNiveles()) {
                System.out.println("Se termino la construccion de niveles, empieza la construccion de pelotas");
            }
        }else {
            try {
                Thread.sleep(random.nextInt(800));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Constuyendo la pelota numero: " + contPelotas);
            contPelotas++;
            if (contPelotas == configurador.getPelotas()){
                System.out.println("Finalizo la construccion del tablero, ya puede hacer funcionar el tablero");
                vs.botonPelotas.setEnabled(true);
                ejecutor.shutdown();
            }
        }
    }
}
