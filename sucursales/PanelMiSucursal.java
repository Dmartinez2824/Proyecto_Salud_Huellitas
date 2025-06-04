package sucursales;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.ConexionBD;
import modelo.UsuarioActivo;

public class PanelMiSucursal extends JPanel {

    private JPanel infoPanel;

    public PanelMiSucursal() {
    	setBackground(new Color(255, 255, 255));
        setLayout(null);

        JLabel label = new JLabel("Mi Sucursal");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setBounds(180, 10, 300, 40);
        add(label);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Información de la Sucursal"));

        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBounds(50, 60, 550, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        mostrarInformacionSucursalDesdeBD(UsuarioActivo.idSucursal);
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
