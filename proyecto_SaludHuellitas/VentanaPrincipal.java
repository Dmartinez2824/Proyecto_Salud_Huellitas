package proyecto_SaludHuellitas;
import javax.swing.*;

import interfaces.PanelAdministrador;
import interfaces.PanelEmpleado;
import login.LoginPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public VentanaPrincipal() {
        setResizable(false);
        setTitle("Salud Huellitas - Póliza de Mascotas");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        getContentPane().setLayout(null); // Para usar posiciones absolutas

        // ░░ Barra superior personalizada
        JPanel barraSuperior = new JPanel(null); // Layout null dentro del panel
        barraSuperior.setBounds(0, 0, getWidth(), 30);
        barraSuperior.setBackground(new Color(168, 171, 171));

        JLabel cerrar = new JLabel("X");
        cerrar.setBounds(970, 5, 20, 20); // Ajusta según tamaño de ventana
        cerrar.setForeground(Color.BLACK);
        cerrar.setFont(new Font("Roboto", Font.BOLD, 25));
        cerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cerrar.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrar.setForeground(Color.BLACK);
            }
        });
        barraSuperior.add(cerrar);
        getContentPane().add(barraSuperior);
        

        // ░░ Panel contenedor para los paneles de contenido (login, admin, etc.)
        cardLayout = new CardLayout();
        panelContenedor = new JPanel();
        panelContenedor.setLayout(cardLayout);

        // Crear paneles
        LoginPanel login = new LoginPanel(this);
        PanelAdministrador adminPanel = new PanelAdministrador();
        PanelEmpleado empleadoPanel = new PanelEmpleado();

        panelContenedor.add(login, "login");
        panelContenedor.add(adminPanel, "admin");
        panelContenedor.add(empleadoPanel, "empleado");
        
        cardLayout.show(panelContenedor, "login");
        panelContenedor.setBounds(0, 30, getWidth(), getHeight() - 30);

        getContentPane().add(panelContenedor); 

        setVisible(true);
    }

    public void mostrarPanel(String nombrePanel) {
        cardLayout.show(panelContenedor, nombrePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}
