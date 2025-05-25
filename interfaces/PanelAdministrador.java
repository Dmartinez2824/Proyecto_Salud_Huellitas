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
        cardLayout = new CardLayout();
        panelCentral.setLayout(cardLayout);
        panelCentral.setSize(1000, 600);

        // Creamos un panel base que contendrá los botones del menú administrador
        JPanel panelMenuAdministrador = new JPanel(null); // Absolute layout
        panelCentral.add(panelMenuAdministrador, "Administrador");
        panelMenuAdministrador.setBackground(new Color(60, 63, 65));
        
        // Añadir botones al panelMenuAdministrador
        JButton btnEmpleados = new JButton("Empleados");
        btnEmpleados.setBounds(396, 168, 160, 40);
        panelMenuAdministrador.add(btnEmpleados);
        
        JButton btnSucursales = new JButton("Sucursales");
        btnSucursales.setBounds(44, 168, 160, 40);
        panelMenuAdministrador.add(btnSucursales);
        
        JButton btnInventario = new JButton("Inventarios");
        btnInventario.setBounds(44, 294, 160, 40);
        panelMenuAdministrador.add(btnInventario);
        
        JButton btnCitas = new JButton("Programar Citas");
        btnCitas.setBounds(44, 406, 160, 40);
        panelMenuAdministrador.add(btnCitas);
        
        JButton btnFinanzas = new JButton("Finanzas");
        btnFinanzas.setBounds(396, 406, 160, 40);
        panelMenuAdministrador.add(btnFinanzas);
        
        JButton btnFinanciacion = new JButton("Financiación");
        btnFinanciacion.setBounds(396, 294, 160, 40);
        panelMenuAdministrador.add(btnFinanciacion);

        // Listeners que muestran cada panel correspondiente
        btnEmpleados.addActionListener(e -> cardLayout.show(panelCentral, "empleados"));
        btnSucursales.addActionListener(e -> cardLayout.show(panelCentral, "sucursales"));
        btnInventario.addActionListener(e -> cardLayout.show(panelCentral, "inventario"));
        btnCitas.addActionListener(e -> cardLayout.show(panelCentral, "citas"));
        btnFinanzas.addActionListener(e -> cardLayout.show(panelCentral, "finanzas"));
        btnFinanciacion.addActionListener(e -> cardLayout.show(panelCentral, "financiacion"));

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
