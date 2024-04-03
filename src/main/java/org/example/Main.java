package org.example;

import EditorTextoInteractivo.EditorTexto;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EditorTexto::new);
    }
}