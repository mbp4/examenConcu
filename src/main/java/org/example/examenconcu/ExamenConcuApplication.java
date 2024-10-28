package org.example.examenconcu;

import org.example.examenconcu.elementos.Configurador;
import org.example.examenconcu.elementos.VentanaSensores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamenConcuApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ExamenConcuApplication.class, args);
        Configurador configurador = new Configurador(100,10);
        VentanaSensores vs = new VentanaSensores(configurador);
        vs.setVisible(true);
    }

}
