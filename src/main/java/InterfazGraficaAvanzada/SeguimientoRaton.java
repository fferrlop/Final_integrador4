package InterfazGraficaAvanzada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeguimientoRaton extends JFrame {
    private JLabel label;

    public SeguimientoRaton() {
        label = new JLabel();
        add(label, BorderLayout.SOUTH);

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                label.setText("Mouse Position: " + e.getX() + ", " + e.getY());
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SeguimientoRaton();
    }
}