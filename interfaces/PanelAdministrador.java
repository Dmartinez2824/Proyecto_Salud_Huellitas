package interfaces;

import javax.swing.*;

import Citas.PanelCitas;
import empleados.PanelEmpleados;
import financiacion.PanelFinanciacion;
import finanzas.PanelFinanzas;
import inventario.PanelInventario;
import sucursales.PanelSucursales;

import java.awt.*;
public class PanelAdministrador extends JPanel {
    private CardLayout cardLayout;
    private JPanel panelCentral;

    public PanelAdministrador() {
        setLayout(null);
        setBackground(new Color(60, 63, 65));

        panelCentral = new JPanel();
        panelCentral.setLocation(0, 0);
        cardLayout = new CardLayout();
        panelCentral.setLayout(cardLayout);
        panelCentral.setSize(999, 600);

        // panel base que contendrá los botones del menú administrador
        JPanel panelMenuAdministrador = new JPanel(null); 
        panelCentral.add(panelMenuAdministrador, "Administrador");
        panelMenuAdministrador.setBackground(new Color(255, 255, 255));
        
        // Añadir botones al panelMenuAdministrador
        JButton btnEmpleados = new JButton("Empleados");
        btnEmpleados.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\empleados.png"));
        btnEmpleados.setBackground(new Color(166, 135, 118));
        btnEmpleados.setBounds(600, 168, 250, 50);
        panelMenuAdministrador.add(btnEmpleados);
        
        JButton btnSucursales = new JButton("Sucursales");
        btnSucursales.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\sucursal.png"));
        btnSucursales.setBackground(new Color(166, 135, 118));
        btnSucursales.setBounds(130, 168, 250, 50);
        panelMenuAdministrador.add(btnSucursales);
        
        JButton btnInventario = new JButton("Inventarios");
        btnInventario.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\inventario.png"));
        btnInventario.setBackground(new Color(166, 135, 118));
        btnInventario.setBounds(130, 294, 250, 50);
        panelMenuAdministrador.add(btnInventario);
        
        JButton btnCitas = new JButton("Programar Citas");
        btnCitas.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\programar_Citas.png"));
        btnCitas.setBackground(new Color(166, 135, 118));
        btnCitas.setBounds(130, 396, 250, 50);
        panelMenuAdministrador.add(btnCitas);
        
        JButton btnFinanzas = new JButton("Finanzas");
        btnFinanzas.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\Financiera.png"));
        btnFinanzas.setBackground(new Color(166, 135, 118));
        btnFinanzas.setBounds(600, 396, 250, 50);
        panelMenuAdministrador.add(btnFinanzas);
        
        JButton btnFinanciacion = new JButton("Financiación");
        btnFinanciacion.setIcon(new ImageIcon("C:\\Users\\Daniel\\eclipse-workspace\\proyecto_SaludHuellitas\\src\\imagenes\\finanzas.png"));
        btnFinanciacion.setBackground(new Color(166, 135, 118));
        btnFinanciacion.setBounds(600, 294, 250, 50);
        panelMenuAdministrador.add(btnFinanciacion);
        
// panel de encabezado         
        JPanel panel_header = new JPanel();
        panel_header.setBackground(new Color(180,217,200));
        panel_header.setBounds(10, 34, 979, 80);
        panelMenuAdministrador.add(panel_header);
        panel_header.setLayout(null);
		
		JLabel lbUser = new JLabel("USER_01");
		lbUser.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 13));
		lbUser.setBounds(842, 32, 64, 17);
		panel_header.add(lbUser);
		
		JLabel lbFotoPerfil = new JLabel("");
		lbFotoPerfil.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Profile_pic_re_iwgo-removebg-preview (1).png"));
		lbFotoPerfil.setBounds(904, 5, 64, 75);
		panel_header.add(lbFotoPerfil);
		
		JLabel lbCargo = new JLabel("ADMINISTRADOR");
		lbCargo.setForeground(new Color(70, 140, 120));
		lbCargo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
		lbCargo.setBounds(10, 53, 116, 17);
		panel_header.add(lbCargo);
		
		JLabel lbTitulo = new JLabel("BIENVENDIDOS A SALUD DE HUELLITAS");
		lbTitulo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(47, 5, 882, 65);
		panel_header.add(lbTitulo);

        // Listeners que muestran cada panel correspondiente
        btnSucursales.addActionListener(e -> {
            panelCentral.add(new PanelSucursales(cardLayout, panelCentral), "sucursales");
            cardLayout.show(panelCentral, "sucursales");
        });

        btnEmpleados.addActionListener(e -> {
            panelCentral.add(new PanelEmpleados(cardLayout, panelCentral), "empleados");
            cardLayout.show(panelCentral, "empleados");
        });

        btnInventario.addActionListener(e -> {
            panelCentral.add(new PanelInventario(cardLayout, panelCentral), "inventario");
            cardLayout.show(panelCentral, "inventario");
        });

        btnCitas.addActionListener(e -> {
            panelCentral.add(new PanelCitas(cardLayout, panelCentral), "citas");
            cardLayout.show(panelCentral, "citas");
        });

        btnFinanzas.addActionListener(e -> {
            panelCentral.add(new PanelFinanzas(cardLayout, panelCentral), "finanzas");
            cardLayout.show(panelCentral, "finanzas");
        });

        btnFinanciacion.addActionListener(e -> {
            panelCentral.add(new PanelFinanciacion(cardLayout, panelCentral), "financiacion");
            cardLayout.show(panelCentral, "financiacion");
        });


        // Agregamos los demás paneles
        panelCentral.add(new PanelEmpleados(cardLayout, panelCentral), "empleados");
        panelCentral.add(new PanelSucursales(cardLayout, panelCentral), "sucursales");
        panelCentral.add(new PanelInventario(cardLayout, panelCentral), "inventario");
        panelCentral.add(new PanelCitas(cardLayout, panelCentral), "citas");
        panelCentral.add(new PanelFinanzas(cardLayout, panelCentral), "finanzas");
        panelCentral.add(new PanelFinanciacion(cardLayout, panelCentral), "financiacion");


        add(panelCentral);
    }
}
