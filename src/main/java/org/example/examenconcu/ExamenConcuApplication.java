package org.example.examenconcu;

import org.example.examenconcu.elementos.Configurador;
import org.example.examenconcu.elementos.Maquina;
import org.example.examenconcu.elementos.Sensor;
import org.example.examenconcu.elementos.VentanaSensores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ExamenConcuApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ExamenConcuApplication.class, args);

        Configurador configurador = new Configurador(100,10);
        VentanaSensores vs = new VentanaSensores(configurador);

        vs.setVisible(true);

        Maquina maquina = new Maquina(configurador, vs);
        ScheduledExecutorService ejecutorT = Executors.newScheduledThreadPool(8);
        ejecutorT.scheduleAtFixedRate(() -> maquina.construir(ejecutorT), 0, 40, TimeUnit.MILLISECONDS);

    }

}
