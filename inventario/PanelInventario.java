package inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import conexion.ConexionBD;

public class PanelInventario extends JPanel {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public PanelInventario(CardLayout cardLayout, JPanel panelPrincipal) {
        setLayout(null);
        setBackground(new Color(255, 255, 255));

        // Botón de regreso
        JButton btnBack = new JButton("\u2190");
        btnBack.setForeground(new Color(255, 0, 0));
        btnBack.setBackground(new Color(255, 255, 255));
        btnBack.setFont(new Font("Arial", Font.BOLD, 30));
        btnBack.setBounds(10, 10, 70, 40);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> cardLayout.show(panelPrincipal, "Administrador"));
        add(btnBack);

        JLabel titulo = new JLabel("Inventario de Productos");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(100, 10, 300, 40);
        add(titulo);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Laboratorio", "Tipo", "Cantidad", "Sucursal", "Precio", "Fecha Venc.", "Estado"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBounds(20, 70, 950, 300);
        add(scrollPane);
        
        tablaProductos.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String estado = (String) value;

                if ("VENCIDO".equals(estado)) {
                    c.setBackground(new Color(255, 102, 102)); // rojo claro
                    c.setForeground(Color.BLACK);
                } else if ("PRONTO A VENCER".equals(estado)) {
                    c.setBackground(new Color(255, 255, 153)); // amarillo claro
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(204, 255, 204)); // verde claro
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });
        


        // Botón Agregar
        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(60, 390, 200, 30);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setBackground(new Color(166, 135, 118));
        btnAgregar.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            new DialogAgregarProducto(frame, this::cargarProductos).setVisible(true);
        });
        add(btnAgregar);

        // Botón Eliminar
        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setBounds(400, 390, 200, 30);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setBackground(new Color(166, 135, 118));
        btnEliminar.addActionListener(e -> eliminarProductoSeleccionado());
        add(btnEliminar);

        // Botón Editar
        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.setBounds(700, 390, 200, 30);
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.setBackground(new Color(166, 135, 118));
        btnEditar.addActionListener(e -> {
            int fila = tablaProductos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para editar");
                return;
            }

            int id = (int) modeloTabla.getValueAt(fila, 0);
            String nombre = (String) modeloTabla.getValueAt(fila, 1);
            String lab = (String) modeloTabla.getValueAt(fila, 2);
            String tipo = (String) modeloTabla.getValueAt(fila, 3);
            int cantidad = (int) modeloTabla.getValueAt(fila, 4);
            String sucursal = (String) modeloTabla.getValueAt(fila, 5);
            int precio = (int) modeloTabla.getValueAt(fila, 6);
            String fechaVenc = modeloTabla.getValueAt(fila, 7).toString();

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            new DialogEditarProducto(frame, id, nombre, lab, tipo, cantidad, sucursal, precio, fechaVenc, this::cargarProductos).setVisible(true);
        });
        add(btnEditar);

        cargarProductos();
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT * FROM inventario_productos";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                String laboratorio = rs.getString("laboratorio");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                String sucursal = rs.getString("sucursal");
                int precio = rs.getInt("precio");
                Date fechaVencimiento = rs.getDate("fecha_vencimiento");

                // Calcular estado
                LocalDate vencimiento = fechaVencimiento.toLocalDate();
                LocalDate hoy = LocalDate.now();
                String estado;
                if (vencimiento.isBefore(hoy)) {
                    estado = "VENCIDO";
                } else if (!vencimiento.isAfter(hoy.plusDays(30))) {
                    estado = "PRONTO A VENCER";
                } else {
                    estado = "VIGENTE";
                }

                Object[] fila = {
                    id, nombre, laboratorio, tipo, cantidad, sucursal, precio, fechaVencimiento.toString(), estado
                };
                modeloTabla.addRow(fila);
            }



            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + e.getMessage());
        }
    }

    private void eliminarProductoSeleccionado() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int idProducto = (int) modeloTabla.getValueAt(fila, 0);

        try {
            Connection con = ConexionBD.conectar();
            String query = "DELETE FROM inventario_productos WHERE id_producto = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();

            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
            cargarProductos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + e.getMessage());
        }
    }
} 
