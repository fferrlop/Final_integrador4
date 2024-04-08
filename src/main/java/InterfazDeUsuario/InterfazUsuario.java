package InterfazDeUsuario;

import ComparadorContadorDeContenido.ComparadorDeContenido;
import EditorTextoInteractivo.EditorTexto;

import javax.swing.*;
import java.awt.*;

public class InterfazUsuario extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton backButton;
    private JButton openEditorButton;
    private JButton openComparatorButton;
    private EditorTexto editorTexto;
    private ComparadorDeContenido comparadorDeContenido;

    public InterfazUsuario() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        backButton = new JButton("Back");
        openEditorButton = new JButton("Open Editor");
        openComparatorButton = new JButton("Open Comparator");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(openEditorButton);
        buttonPanel.add(openComparatorButton);

        editorTexto = new EditorTexto();
        comparadorDeContenido = new ComparadorDeContenido();

        cardPanel.add(new JPanel(), "EMPTY");
        cardPanel.add(editorTexto, "EDITOR");
        cardPanel.add(comparadorDeContenido, "COMPARATOR");

        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "EMPTY"));
        openEditorButton.addActionListener(e -> cardLayout.show(cardPanel, "EDITOR"));
        openComparatorButton.addActionListener(e -> cardLayout.show(cardPanel, "COMPARATOR"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
}