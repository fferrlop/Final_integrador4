package InterfazDeUsuario;

import BusquedaPalabrasYContactos.BuscadorPalabras;
import ComparadorContadorDeContenido.ComparadorDeContenido;
import EditorTextoInteractivo.EditorTexto;
import BusquedaPalabrasYContactos.BuscadorPalabras;
import BusquedaPalabrasYContactos.Contactos;
import BusquedaPalabrasYContactos.DatosContactos;
import VerificaciónYDiseño.DiseñoGrafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazUsuario extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton backButton;
    private JButton openEditorButton;
    private JButton openComparatorButton;
    private JButton verContactosButton;
    private JButton buscarPalabrasButton;
    private EditorTexto editorTexto;
    private ComparadorDeContenido comparadorDeContenido;
    private BuscadorPalabras busquedaPalabras;
    private Contactos contactos;
    private JLabel mousePositionLabel;
    private JButton drawButton;

    public InterfazUsuario() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        backButton = new JButton("Volver");
        openEditorButton = new JButton("Editor archivos");
        openComparatorButton = new JButton("Comparador");
        verContactosButton = new JButton("Gestión Contactos");
        buscarPalabrasButton = new JButton("Buscar Palabras");
        drawButton = new JButton("Dibujar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(openEditorButton);
        buttonPanel.add(openComparatorButton);
        buttonPanel.add(verContactosButton);
        buttonPanel.add(buscarPalabrasButton);
        buttonPanel.add(drawButton);

        comparadorDeContenido = new ComparadorDeContenido();
        editorTexto = new EditorTexto(comparadorDeContenido);
        busquedaPalabras = new BuscadorPalabras();
        contactos = new Contactos();

        cardPanel.add(new JPanel(), "EMPTY");
        cardPanel.add(editorTexto, "EDITOR");
        cardPanel.add(comparadorDeContenido, "COMPARATOR");


        JPanel largePanel = new JPanel();
        largePanel.setPreferredSize(new Dimension(1000, 2000));
        cardPanel.add(largePanel, "LARGE");

        JScrollPane scrollPane = new JScrollPane(cardPanel);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        backButton.addActionListener(e -> cardLayout.show(cardPanel, "EMPTY"));
        openEditorButton.addActionListener(e -> cardLayout.show(cardPanel, "EDITOR"));
        openComparatorButton.addActionListener(e -> cardLayout.show(cardPanel, "COMPARATOR"));
        verContactosButton.addActionListener(e -> mostrarDialogoContactos());


        buscarPalabrasButton.addActionListener(e -> {
            String palabra = JOptionPane.showInputDialog("Introduce la palabra a buscar:");
            String archivo = (String) JOptionPane.showInputDialog(null, "Selecciona un archivo:", "Archivo", JOptionPane.QUESTION_MESSAGE, null, editorTexto.getSavedFiles(), editorTexto.getSavedFiles()[0]);
            int count = busquedaPalabras.buscarPalabraEnArchivo(palabra, archivo);
            JOptionPane.showMessageDialog(null, "La palabra '" + palabra + "' aparece " + count + " veces en el archivo '" + archivo + "'.");
        });

        // Seguimiento del Ratón
        mousePositionLabel = new JLabel();
        add(mousePositionLabel, BorderLayout.SOUTH);
        drawButton.addActionListener(e -> new DiseñoGrafico());
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mousePositionLabel.setText("Mouse Position: " + e.getX() + ", " + e.getY());
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600 );
        setVisible(true);
    }

    private void mostrarDialogoContactos() {
        JDialog dialogoContactos = new JDialog(this, "Contactos", true);
        dialogoContactos.setLayout(new FlowLayout());

        JButton agregarContactoButton = new JButton("Añadir contacto");
        agregarContactoButton.addActionListener(e -> agregarContacto());
        dialogoContactos.add(agregarContactoButton);

        JButton mostrarContactosButton = new JButton("Contactos");
        mostrarContactosButton.addActionListener(e -> mostrarContactos());
        dialogoContactos.add(mostrarContactosButton);

        dialogoContactos.pack();
        dialogoContactos.setLocationRelativeTo(this); // Asegura que el diálogo se abre en el centro de la ventana principal
        dialogoContactos.setVisible(true);
    }

    private void agregarContacto() {
        String nombre = JOptionPane.showInputDialog("Introduce el nombre del contacto:");
        String email = JOptionPane.showInputDialog("Introduce el email del contacto:");
        String numeroTelefono = JOptionPane.showInputDialog("Introduce el número de teléfono del contacto:");
        DatosContactos nuevoContacto = new DatosContactos(nombre, email, numeroTelefono);
        contactos.agregarContacto(nuevoContacto);
    }

    private void mostrarContactos() {
        String contactosStr = "";
        for (DatosContactos contacto : contactos.getListaContactos()) {
            contactosStr += "Nombre: " + contacto.getNombre() + ", Email: " + contacto.getEmail() + ", Teléfono: " + contacto.getNumeroTelefono() + "\n";
        }
        JOptionPane.showMessageDialog(null, contactosStr);
    }
}