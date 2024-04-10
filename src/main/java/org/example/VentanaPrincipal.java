package org.example;

import InterfazDeUsuario.InterfazUsuario;
import InterfazGraficaAvanzada.*;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setLayout(new BorderLayout());


        InterfazUsuario interfazUsuario = new InterfazUsuario();
        JScrollPane scrollPane = new JScrollPane(interfazUsuario);
        add(scrollPane, BorderLayout.CENTER);

        add(new MultiplicidadVentanas(), BorderLayout.NORTH);
        add(new SeguimientoRaton(), BorderLayout.SOUTH);
        add(new BarraDesplazamiento(), BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}