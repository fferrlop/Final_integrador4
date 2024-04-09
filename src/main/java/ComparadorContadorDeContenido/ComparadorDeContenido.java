package ComparadorContadorDeContenido;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ComparadorDeContenido extends JPanel {

    private JTextArea textArea;
    private JButton compareButton;
    private JButton selectFile1Button;
    private JButton selectFile2Button;
    private JButton countWordsButton; // New button
    private String filename1;
    private String filename2;
    private List<String> savedFiles;
    private static final String SAVED_FILES_NAME = "savedFiles.txt";

    public ComparadorDeContenido() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        compareButton = new JButton("Compare Files");
        selectFile1Button = new JButton("Select File 1");
        selectFile2Button = new JButton("Select File 2");
        countWordsButton = new JButton("Count Words"); // Initialize the new button

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectFile1Button);
        buttonPanel.add(selectFile2Button);
        buttonPanel.add(compareButton);
        buttonPanel.add(countWordsButton); // Add the new button to the button panel
        add(buttonPanel, BorderLayout.SOUTH);

        savedFiles = new ArrayList<>();
        loadSavedFiles();

        selectFile1Button.addActionListener(e -> filename1 = selectFile());
        selectFile2Button.addActionListener(e -> filename2 = selectFile());
        compareButton.addActionListener(e -> {
            if (filename1 != null && filename2 != null) {
                String comparisonResult = compareFiles(filename1, filename2);
                textArea.setText(comparisonResult);
            }
        });
        countWordsButton.addActionListener(e -> {
            if (filename1 != null) {
                int wordCount = countWordsInFile(filename1);
                textArea.setText("Word count: " + wordCount);
            }
        });
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

    public void updateSavedFiles() {
        savedFiles.clear();
        loadSavedFiles();
    }

    private String selectFile() {
        Object[] options = savedFiles.toArray();
        int selection = JOptionPane.showOptionDialog(null, "Select a file:", "File Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (selection != JOptionPane.CLOSED_OPTION) {
            return (String) options[selection];
        }
        return null;
    }

    public String compareFiles(String filename1, String filename2) {
        StringBuilder result = new StringBuilder();
        try {
            List<String> lines1 = Files.readAllLines(Paths.get(filename1));
            List<String> lines2 = Files.readAllLines(Paths.get(filename2));

            for (int i = 0; i < Math.min(lines1.size(), lines2.size()); i++) {
                if (!lines1.get(i).equals(lines2.get(i))) {
                    result.append("Difference at line ").append(i + 1).append("\n");
                    result.append("File 1: ").append(lines1.get(i)).append("\n");
                    result.append("File 2: ").append(lines2.get(i)).append("\n");
                }
            }

            if (result.length() == 0) {
                result.append("The files are identical.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private int countWordsInFile(String filename) {
        int wordCount = 0;
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
}