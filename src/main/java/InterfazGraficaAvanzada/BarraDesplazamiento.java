package InterfazGraficaAvanzada;

import javax.swing.*;

public class BarraDesplazamiento extends JFrame {
    private JTextArea textArea;

    public BarraDesplazamiento() {
        textArea = new JTextArea(50, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BarraDesplazamiento();
    }
}