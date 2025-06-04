package Citas;

import javax.swing.*;
import java.awt.*;

public class PanelCitas extends JPanel {
    public PanelCitas(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);
        setBackground(new Color(255, 240, 245));
        // Botón de retroceso al menú principal del administrador (empleados)
        JButton btnBack = new JButton("\u2190");
        btnBack.setForeground(new Color(255, 0, 0));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBack.setFont(new Font("Arial", Font.BOLD, 30));
        btnBack.setBounds(10, 10, 70, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel titulo = new JLabel("Programación de Citas");
        titulo.setBounds(30, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);
    }
}
