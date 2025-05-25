package Citas;

import javax.swing.*;
import java.awt.*;

public class PanelCitas extends JPanel {
    public PanelCitas(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);
        setBackground(new Color(255, 240, 245));
        // Botón de retroceso al menú principal del administrador (empleados)
        JButton btnBack = new JButton("<---------------");
        btnBack.setBounds(10, 10, 100, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel titulo = new JLabel("Programación de Citas");
        titulo.setBounds(30, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);
    }
}
