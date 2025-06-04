package interfaces;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import inventario.PanelInventario;
import inventario.PanelInventarioEmpleado;
import sucursales.PanelMiSucursal;


public class PanelEmpleado extends JPanel {
    private JPanel panelCentral;
    private CardLayout cardLayout;
    private static final long serialVersionUID = 1L;

    public PanelEmpleado() {
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        // Panel con CardLayout para cambiar contenido
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        panelCentral.setBounds(260, 100, 720, 450); // Panel de contenido
        panelCentral.setBackground(new Color(255, 255, 255));

        

        // Cargar subpaneles (puedes reemplazar estos con tus paneles reales)
        panelCentral.add(new PanelMiSucursal(), "sucursal");   
        panelCentral.add(new PanelInventario(cardLayout, panelCentral), "inventario");     // PanelInventarioEmpleado
        panelCentral.add(new JPanel(), "citas");          // PanelCitasEmpleado	
        panelCentral.add(new JPanel(), "consulta");       // PanelConsultaMedicaEmpleado
        panelCentral.add(new JPanel(), "poliza");         // PanelPolizaEmpleado
        
     // Panel encabezado
        JPanel panel_header = new JPanel();
        panel_header.setBackground(new Color(180, 217, 200));
        panel_header.setBounds(10, 10, 970, 80);
        panel_header.setLayout(null);
        add(panel_header);

        JLabel lbUser = new JLabel("USER_01");
        lbUser.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 13));
        lbUser.setBounds(832, 32, 64, 17);
        panel_header.add(lbUser);

        JLabel lbFotoPerfil = new JLabel("");
        lbFotoPerfil.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Profile_pic_re_iwgo-removebg-preview (1).png"));
        lbFotoPerfil.setBounds(894, 5, 64, 75);
        panel_header.add(lbFotoPerfil);

        JLabel lbCargo = new JLabel("EMPLEADO");
        lbCargo.setForeground(new Color(70, 140, 120));
        lbCargo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
        lbCargo.setBounds(10, 53, 116, 17);
        panel_header.add(lbCargo);

        JLabel lbTitulo = new JLabel("BIENVENIDO A SALUD DE HUELLITAS");
        lbTitulo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 20));
        lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitulo.setBounds(47, 5, 882, 65);
        panel_header.add(lbTitulo);


        // === MENÚ LATERAL ===
        JButton btnMiSucursal = new JButton("Mi Sucursal");
        btnMiSucursal.setBackground(new Color(166, 135, 118));
        btnMiSucursal.setBounds(40, 100, 180, 40);
        btnMiSucursal.setFont(new Font("Arial", Font.BOLD, 14));
        btnMiSucursal.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "sucursal"));
        add(btnMiSucursal);

        JButton btnInventario = new JButton("Inventario");
        btnInventario.setBackground(new Color(166, 135, 118));
        btnInventario.setBounds(40, 191, 180, 40);
        btnInventario.setFont(new Font("Arial", Font.BOLD, 14));
        btnInventario.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "inventario"));
        add(btnInventario);

        JButton btnAgendar = new JButton("Programar Citas");
        btnAgendar.setBackground(new Color(166, 135, 118));
        btnAgendar.setBounds(40, 287, 180, 40);
        btnAgendar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgendar.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "citas"));
        add(btnAgendar);

        JButton btnConsulta = new JButton("Consulta Médica");
        btnConsulta.setBackground(new Color(166, 135, 118));
        btnConsulta.setBounds(40, 388, 180, 40);
        btnConsulta.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsulta.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "consulta"));
        add(btnConsulta);

        JButton btnPoliza = new JButton("Suscripción Póliza");
        btnPoliza.setBackground(new Color(166, 135, 118));
        btnPoliza.setBounds(40, 491, 180, 40);
        btnPoliza.setFont(new Font("Arial", Font.BOLD, 14));
        btnPoliza.addActionListener((ActionEvent e) -> cardLayout.show(panelCentral, "poliza"));
        add(btnPoliza);

        // Agregar el panel central al panel principal
        add(panelCentral);
        
        
    }
}
