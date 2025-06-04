package empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.ConexionBD;
import modelo.UsuarioActivo;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PanelEmpleados extends JPanel {

    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JButton btnAgregar, btnEliminar, btnEditar;

    public PanelEmpleados(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        JButton btnBack = new JButton("\u2190");
        btnBack.setForeground(new Color(255, 0, 0));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
        btnBack.setFont(new Font("Arial", Font.BOLD, 30));
        btnBack.setBounds(10, 10, 70, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel titulo = new JLabel("Gestión de Empleados");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(130, 10, 300, 40);
        add(titulo);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido", "Correo", "Teléfono", "Ocupación", "Horario", "Sucursal"}, 0);
        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
        tablaEmpleados.setBackground(new Color(99, 200, 183));
        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setBounds(20, 70, 700, 300);
        add(scrollPane);

        btnAgregar = new JButton("Agregar Empleado");
        btnAgregar.setBackground(new Color(255, 255, 255));
        btnAgregar.setBounds(20, 390, 200, 30);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(e -> mostrarFormularioAgregar());
        add(btnAgregar);

        btnEliminar = new JButton("Eliminar Empleado");
        btnEliminar.setBackground(new Color(255, 255, 255));
        btnEliminar.setBounds(240, 390, 200, 30);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(e -> eliminarEmpleadoSeleccionado());
        add(btnEliminar);

        btnEditar = new JButton("Editar Empleado");
        btnEditar.setBackground(new Color(255, 255, 255));
        btnEditar.setBounds(430, 390, 200, 30);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.addActionListener(e -> {
            int filaSeleccionada = tablaEmpleados.getSelectedRow();
            if (filaSeleccionada != -1) {
                int idEmpleado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                new PanelEditarEmpleado((JFrame) SwingUtilities.getWindowAncestor(this), idEmpleado).setVisible(true);
                cargarEmpleados();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un empleado para editar.");
            }
        });
        add(btnEditar);

        cargarEmpleados();
    }

    private void cargarEmpleados() {
        modeloTabla.setRowCount(0);
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT e.id_usuario, e.nombre, e.apellido, e.correo, e.telefono, e.ocupacion, e.horario, s.nombre AS sucursal " +
                           "FROM usuarios_huellitas e " +
                           "LEFT JOIN sucursales s ON e.id_sucursal = s.id_sucursal " +
                           "WHERE e.rol = 'Empleado'";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("ocupacion"),
                    rs.getString("horario"),
                    rs.getString("sucursal")
                };
                modeloTabla.addRow(fila);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + e.getMessage());
        }
    }

    private void mostrarFormularioAgregar() {
        PanelFormularioEmpleado formulario = new PanelFormularioEmpleado((JFrame) SwingUtilities.getWindowAncestor(this));
        formulario.setVisible(true);
        cargarEmpleados();
    }

    private void eliminarEmpleadoSeleccionado() {
        int filaSeleccionada = tablaEmpleados.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un empleado a eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este empleado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion != JOptionPane.YES_OPTION) return;

        int idEmpleado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        try {
            Connection con = ConexionBD.conectar();
            String query = "DELETE FROM usuarios_huellitas WHERE id_usuario = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idEmpleado);
            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Empleado eliminado con éxito.");
            cargarEmpleados();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar empleado: " + e.getMessage());
        }
    }
} 
