package EditorTextoInteractivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EditorTexto extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JList<String> documentList;
    private DefaultListModel<String> listModel;

    public EditorTexto() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        saveButton = new JButton("Save");
        listModel = new DefaultListModel<>();
        documentList = new JList<>(listModel);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
        add(new JScrollPane(documentList), BorderLayout.EAST);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDocument();
            }
        });

        documentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadDocument(documentList.getSelectedValue());
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    private void saveDocument() {
        String filename = JOptionPane.showInputDialog("Enter filename:");
        if (filename != null) {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(textArea.getText());
                listModel.addElement(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadDocument(String filename) {
        if (filename != null) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filename));
                textArea.setText(String.join("\n", lines));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}