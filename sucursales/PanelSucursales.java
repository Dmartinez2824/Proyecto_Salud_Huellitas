package sucursales;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.ConexionBD;
import modelo.UsuarioActivo;

public class PanelSucursales extends JPanel {

    private JPanel infoPanel;
    private JComboBox<String> comboSucursales;
    private JButton btnBuscar;

    public PanelSucursales(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);

        JButton btnBack = new JButton("\u2190");
        btnBack.setForeground(new Color(255, 0, 0));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBack.setFont(new Font("Arial", Font.BOLD, 30));
        btnBack.setBounds(10, 10, 70, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel label = new JLabel("Panel de Sucursales");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(180, 10, 300, 40);
        add(label);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información de la Sucursal"));

        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBounds(50, 100, 500, 240);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        if (UsuarioActivo.rol.equals("Administrador")) {
            comboSucursales = new JComboBox<>();
            cargarSucursalesEnCombo();
            comboSucursales.setBounds(50, 60, 300, 25);
            add(comboSucursales);

            btnBuscar = new JButton("Buscar Sucursal");
            btnBuscar.setBounds(370, 60, 150, 25);
            btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnBuscar.addActionListener(e -> {
                int id = getIdSucursalSeleccionada();
                mostrarInformacionSucursalDesdeBD(id);
            });
            add(btnBuscar);
        } else if (UsuarioActivo.rol.equals("Empleado")) {
            mostrarInformacionSucursalDesdeBD(UsuarioActivo.idSucursal);
            JScrollPane scrollPane1 = new JScrollPane(infoPanel);
            scrollPane1.setBounds(50, 60, 550, 300);
            scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            add(scrollPane1);
        }
    }

    private void cargarSucursalesEnCombo() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT id_sucursal, nombre FROM sucursales";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_sucursal");
                String nombre = rs.getString("nombre");
                comboSucursales.addItem(id + " - " + nombre);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sucursales: " + e.getMessage());
        }
    }

    private int getIdSucursalSeleccionada() {
        String seleccion = (String) comboSucursales.getSelectedItem();
        return Integer.parseInt(seleccion.split(" - ")[0].trim());
    }

    private void mostrarInformacionSucursalDesdeBD(int idSucursal) {
        infoPanel.removeAll();

        String nombre = "", direccion = "", telefono = "", encargado = "";

        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT s.nombre, s.direccion, s.telefono, CONCAT(u.nombre, ' ', u.apellido) AS encargado " +
                           "FROM sucursales s " +
                           "LEFT JOIN usuarios_huellitas u ON s.encargado_id = u.id_usuario " +
                           "WHERE s.id_sucursal = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idSucursal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombre");
                direccion = rs.getString("direccion");
                telefono = rs.getString("telefono");
                encargado = rs.getString("encargado");
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Error al obtener la sucursal: " + e.getMessage());
            infoPanel.add(errorLabel);
            infoPanel.revalidate();
            infoPanel.repaint();
            return;
        }

        infoPanel.add(new JLabel("ID: " + idSucursal));
        infoPanel.add(new JLabel("Nombre: " + nombre));
        infoPanel.add(new JLabel("Dirección: " + direccion));
        infoPanel.add(new JLabel("Teléfono: " + telefono));
        infoPanel.add(new JLabel("Encargado: " + encargado));

        infoPanel.revalidate();
        infoPanel.repaint();
    }
} 
