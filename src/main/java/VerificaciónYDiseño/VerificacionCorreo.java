package VerificaciónYDiseño;

import javax.swing.*;
import java.awt.*;

public class VerificacionCorreo extends JFrame {
    private JTextField emailField;
    private JLabel validationLabel;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel instructionLabel;

    public VerificacionCorreo() {
        setLayout(new FlowLayout());

        instructionLabel = new JLabel("Porfavor, introduzca su correo electrónico:");
        emailField = new JTextField(25);
        validationLabel = new JLabel();

        okButton = new JButton("OK");
        okButton.addActionListener(e -> validateEmail());

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> System.exit(0));

        add(instructionLabel);
        add(emailField);
        add(validationLabel);
        add(okButton);
        add(cancelButton);

        setSize(300, 125);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void validateEmail() {
        String email = emailField.getText();
        if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            SwingUtilities.invokeLater(() -> dispose());
            new org.example.VentanaPrincipal();

        } else {
            validationLabel.setText("Error en el correo");
            validationLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        new VerificacionCorreo();
    }
}