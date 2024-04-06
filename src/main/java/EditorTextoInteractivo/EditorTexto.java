package EditorTextoInteractivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EditorTexto extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JList<String> documentList;
    private DefaultListModel<String> listModel;
    private static final String FILE_LIST_NAME = "fileList.txt";

    public EditorTexto() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        saveButton = new JButton("Save");
        listModel = new DefaultListModel<>();
        documentList = new JList<>(listModel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(documentList), new JScrollPane(textArea));
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        add(splitPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveDocument());

        documentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadDocument(documentList.getSelectedValue());
            }
        });

        loadFileList();

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
                saveFileList();
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

    private void loadFileList() {
        File fileList = new File(FILE_LIST_NAME);
        if (fileList.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileList))) {
                String filename;
                while ((filename = reader.readLine()) != null) {
                    listModel.addElement(filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFileList() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_LIST_NAME))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.println(listModel.getElementAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}