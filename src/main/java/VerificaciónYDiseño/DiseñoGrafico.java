package VerificaciónYDiseño;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DiseñoGrafico extends JFrame {
    private JPanel drawingPanel;
    private Point pointStart = null;
    private Point pointEnd = null;
    private BufferedImage image;
    private Graphics2D graphics2D;
    private JButton clearButton;

    public DiseñoGrafico() {
        drawingPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pointStart = e.getPoint();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                graphics2D.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                pointStart = pointEnd;
                drawingPanel.repaint();
            }
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            drawingPanel.repaint();
        });

        add(drawingPanel, BorderLayout.CENTER);
        add(clearButton, BorderLayout.SOUTH);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        graphics2D = image.createGraphics();
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
    }
}