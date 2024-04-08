package EditorTextoInteractivo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditorTexto extends JPanel {
    private JTextArea textArea;
    private JButton saveButton;
    private JButton openButton;
    private List<String> savedFiles;
    private static final String SAVED_FILES_NAME = "savedFiles.txt";

    public EditorTexto() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save File");
        openButton = new JButton("Open File");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(openButton);
        add(buttonPanel, BorderLayout.SOUTH);

        savedFiles = new ArrayList<>();
        loadSavedFiles();

        saveButton.addActionListener(e -> saveFile());
        openButton.addActionListener(e -> openFile());
    }

    private void loadSavedFiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVED_FILES_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                savedFiles.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        String newFileName = JOptionPane.showInputDialog("Enter the name of the new file:");
        if (newFileName != null && !newFileName.isBlank()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
                writer.write(textArea.getText());
                savedFiles.add(newFileName);
                updateSavedFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateSavedFiles() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVED_FILES_NAME))) {
            for (String fileName : savedFiles) {
                writer.write(fileName);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFile() {
        Object[] options = savedFiles.toArray();
        int selection = JOptionPane.showOptionDialog(null, "Select a file:", "File Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (selection != JOptionPane.CLOSED_OPTION) {
            String filePath = (String) options[selection];
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                textArea.read(reader, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}