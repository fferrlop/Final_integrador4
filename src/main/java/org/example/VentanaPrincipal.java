package org.example;

import InterfazDeUsuario.InterfazUsuario;
import InterfazGraficaAvanzada.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setLayout(new BorderLayout());

        // Crear una instancia de cada clase y añadirla a la ventana principal
        add(new InterfazUsuario(), BorderLayout.CENTER);
        add(new MultiplicidadVentanas(), BorderLayout.NORTH);
        add(new SeguimientoRaton(), BorderLayout.SOUTH);
        add(new BarraDesplazamiento(), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}