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

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}