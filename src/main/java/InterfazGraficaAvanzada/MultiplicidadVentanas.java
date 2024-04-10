package InterfazGraficaAvanzada;

import javax.swing.*;
import java.awt.*;

public class MultiplicidadVentanas extends JFrame {
    private JDesktopPane desktopPane;

    public MultiplicidadVentanas() {
        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        for (int i = 0; i < 3; i++) {
            JInternalFrame internalFrame = new JInternalFrame("Documento " + (i + 1), true, true, true, true);
            internalFrame.setBounds(i * 50, i * 50, 200, 200);
            desktopPane.add(internalFrame);
            internalFrame.setVisible(true);
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MultiplicidadVentanas();
    }
}