package interfaces;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelEmpleado extends JPanel {
    private JPanel panelCentral;
    private CardLayout cardLayout;
    private static final long serialVersionUID = 1L;

    public PanelEmpleado() {
        setLayout(null);
        setBackground(new Color(60, 63, 65));

        // Panel con CardLayout para cambiar contenido
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        panelCentral.setBounds(260, 10, 720, 560); // Panel de contenido
        panelCentral.setBackground(Color.WHITE);

        // Cargar subpaneles (puedes reemplazar estos con tus paneles reales)
        panelCentral.add(new JPanel(), "sucursal");       // aquí va PanelMiSucursal
        panelCentral.add(new JPanel(), "inventario");     // PanelInventarioEmpleado
        panelCentral.add(new JPanel(), "citas");          // PanelCitasEmpleado
        panelCentral.add(new JPanel(), "consulta");       // PanelConsultaMedicaEmpleado
        panelCentral.add(new JPanel(), "poliza");         // PanelPolizaEmpleado

        // === MENÚ LATERAL ===
        JButton btnMiSucursal = new JButton("Mi Sucursal");
        btnMiSucursal.setBounds(40, 50, 180, 40);
        btnMiSucursal.setFont(new Font("Arial", Font.BOLD, 14));
        btnMiSucursal.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "sucursal"));
        add(btnMiSucursal);

        JButton btnInventario = new JButton("Inventario");
        btnInventario.setBounds(40, 110, 180, 40);
        btnInventario.setFont(new Font("Arial", Font.BOLD, 14));
        btnInventario.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "inventario"));
        add(btnInventario);

        JButton btnAgendar = new JButton("Programar Citas");
        btnAgendar.setBounds(40, 170, 180, 40);
        btnAgendar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgendar.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "citas"));
        add(btnAgendar);

        JButton btnConsulta = new JButton("Consulta Médica");
        btnConsulta.setBounds(40, 230, 180, 40);
        btnConsulta.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsulta.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "consulta"));
        add(btnConsulta);

        JButton btnPoliza = new JButton("Suscripción Póliza");
        btnPoliza.setBounds(40, 290, 180, 40);
        btnPoliza.setFont(new Font("Arial", Font.BOLD, 14));
        btnPoliza.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "poliza"));
        add(btnPoliza);

        // Agregar el panel central al panel principal
        add(panelCentral);
    }
}
