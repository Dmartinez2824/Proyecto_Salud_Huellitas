package finanzas;

import javax.swing.*;
import java.awt.*;

public class PanelFinanzas extends JPanel {
    public PanelFinanzas(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);
        setBackground(new Color(245, 245, 255));
        // Botón de retroceso al menú principal del administrador (empleados)
        JButton btnBack = new JButton("<---------------");
        btnBack.setBounds(10, 10, 100, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel titulo = new JLabel("Gestión de Finanzas");
        titulo.setBounds(30, 20, 300, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo);
    }
}
