package sucursales;

import javax.swing.*;
import java.awt.*;

public class PanelSucursales extends JPanel {
    public PanelSucursales(CardLayout cardLayout, JPanel panelCentral) {
        setLayout(null);

        JButton btnBack = new JButton("<---------------");
        btnBack.setBounds(10, 10, 100, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelCentral, "Administrador"));
        add(btnBack);

        JLabel label = new JLabel("Panel de Sucursales");
        label.setBounds(134, 60, 200, 50);
        add(label);
    }
}

