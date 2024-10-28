package org.example.examenconcu.elementos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VentanaSensores extends JFrame{
    public JButton botonPelotas;
    private JPanel panel1;
    private JTable tablaPelotas;
    private JScrollPane scrollPanePelotas;
    private JButton botonReiniciar;
    private Configurador configurador;

    public VentanaSensores(Configurador configurador){
        this.configurador = configurador;
        this.setSize(new Dimension(3000, 2000));
        this.getContentPane().setLayout(null);
        botonPelotas = new JButton("Tirar pelotas");
        botonPelotas.setEnabled(false);
        botonPelotas.setBounds(10, 10, 300, 30);
        this.add(botonPelotas);
        botonReiniciar = new JButton("Reiniciar tabla");
        botonReiniciar.setBounds(320, 10, 300, 30);
        this.add(botonReiniciar);
        crearTablaPelotas();
    }

    private void crearTablaPelotas() {
        String[] nombresColumnas = {"Valor", "Mediciones", "Gráfica"};
        Object[][] datos = new Object[21][3];

        for (int i = 0; i < this.configurador.getNiveles()+1; i++){
            datos[i][0] = i;
            datos[i][1] = 0;
            datos[i][2] = "";
        }

        tablaPelotas = new JTable(datos, nombresColumnas);
        tablaPelotas.getColumnModel().getColumn(0).setMaxWidth(50);
        tablaPelotas.getColumnModel().getColumn(1).setMaxWidth(50);
        scrollPanePelotas = new JScrollPane();

        scrollPanePelotas.setViewportView(tablaPelotas);
        scrollPanePelotas.setBounds(10, 50, 1900, 500);
        this.add(scrollPanePelotas);

        botonPelotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empezarMedicionPelotas();
            }
        });
        botonReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarTablaPelotas();
            }
        });
    }

    private void empezarMedicionPelotas() {
        Sensor sensorT = new Sensor("Pelotas", this.configurador, this);
        ScheduledExecutorService ejecutorT = Executors.newScheduledThreadPool(8);
        ejecutorT.scheduleAtFixedRate(() -> sensorT.medir(ejecutorT), 0, 40, TimeUnit.MILLISECONDS);
    }

    private void reiniciarTablaPelotas() {
        for (int i = 0; i < this.configurador.getNiveles()+1; i++){
            tablaPelotas.setValueAt(0, i, 1);
            tablaPelotas.setValueAt("", i, 2);
        }
    }

    public void muestraPelotas(int medida) {

        if (medida >= 0 && medida < tablaPelotas.getRowCount()){
            int valorActual = (int) tablaPelotas.getValueAt(medida, 1);
            int nuevoValor = valorActual + 1;
            tablaPelotas.setValueAt(nuevoValor, medida,1);
            String nuevaGrafica = generarGrafico(nuevoValor);
            tablaPelotas.setValueAt(nuevaGrafica, medida, 2);
        }
    }

    private String generarGrafico(int nuevoValor) {
        StringBuilder grafico = new StringBuilder();

        for (int i = 0; i < nuevoValor; i++){
            grafico.append("0");
        }

        return grafico.toString();
    }
}
