package inventario;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import conexion.ConexionBD;
import modelo.UsuarioActivo;

public class PanelInventarioEmpleado extends JPanel {

    private JTable tablaProducto;
    private DefaultTableModel modeloTabla;

    public PanelInventarioEmpleado() {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Inventario de mi Sucursal");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(20, 20, 400, 30);
        add(titulo);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Laboratorio", "Tipo", "Cantidad", "Sucursal", "Precio", "F. Vencimiento"}, 0);
        tablaProducto = new JTable(modeloTabla);
        tablaProducto.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaProducto.setRowHeight(25);

        // Renderizador para color de filas según vencimiento
        tablaProducto.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String fechaVenc = (String) table.getValueAt(row, 7);
                try {
                    java.time.LocalDate venc = java.time.LocalDate.parse(fechaVenc);
                    java.time.LocalDate hoy = java.time.LocalDate.now();
                    if (venc.isBefore(hoy)) {
                        c.setBackground(new Color(255, 102, 102)); // Rojo vencido
                    } else if (venc.isBefore(hoy.plusMonths(1))) {
                        c.setBackground(new Color(255, 204, 102)); // Amarillo próximo a vencer
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaProducto);
        scrollPane.setBounds(20, 70, 900, 330);
        add(scrollPane);

        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.setBounds(20, 420, 160, 30);
        btnEditar.setBackground(new Color(166, 135, 118));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEditar.addActionListener(e -> editarProducto());
        add(btnEditar);

        cargarProductosSucursal();
    }

    private void cargarProductosSucursal() {
        modeloTabla.setRowCount(0);
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT * FROM inventario_productos WHERE sucursal=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, UsuarioActivo.sucursal);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getString("laboratorio"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getString("sucursal"),
                        rs.getInt("precio"),
                        rs.getString("fecha_vencimiento")
                });
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + e.getMessage());
        }
    }

    private void editarProducto() {
        int fila = tablaProducto.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        String nombre = (String) modeloTabla.getValueAt(fila, 1);
        String lab = (String) modeloTabla.getValueAt(fila, 2);
        String tipo = (String) modeloTabla.getValueAt(fila, 3);
        int cantidad = (int) modeloTabla.getValueAt(fila, 4);
        String sucursal = (String) modeloTabla.getValueAt(fila, 5);
        int precio = (int) modeloTabla.getValueAt(fila, 6);
        String venc = (String) modeloTabla.getValueAt(fila, 7);

        DialogEditarProducto dialog = new DialogEditarProducto((JFrame) SwingUtilities.getWindowAncestor(this),
                id, nombre, lab, tipo, cantidad, sucursal, precio, venc, this::cargarProductosSucursal);
        dialog.setVisible(true);
    }
}