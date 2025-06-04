package inventario;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import conexion.ConexionBD;
import java.sql.Date;
import java.time.LocalDate;
import java.text.SimpleDateFormat;

public class DialogEditarProducto extends JDialog {

    private JTextField txtNombre, txtLaboratorio, txtTipo, txtCantidad, txtPrecio;
    private JComboBox<String> comboSucursal;
    private JDateChooser dateChooser;
    private int idProducto;

    public DialogEditarProducto(JFrame parent, int idProducto, String nombre, String laboratorio, String tipo, int cantidad, String sucursal, int precio, String fechaVencimiento, Runnable onSuccess) {
        super(parent, "Editar Producto", true);
        setLayout(null);
        setSize(400, 480);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);

        this.idProducto = idProducto;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 120, 25);
        add(lblNombre);
        txtNombre = new JTextField(nombre);
        txtNombre.setBounds(150, 30, 200, 25);
        add(txtNombre);

        JLabel lblLab = new JLabel("Laboratorio:");
        lblLab.setBounds(30, 70, 120, 25);
        add(lblLab);
        txtLaboratorio = new JTextField(laboratorio);
        txtLaboratorio.setBounds(150, 70, 200, 25);
        add(txtLaboratorio);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 110, 120, 25);
        add(lblTipo);
        txtTipo = new JTextField(tipo);
        txtTipo.setBounds(150, 110, 200, 25);
        add(txtTipo);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30, 150, 120, 25);
        add(lblCantidad);
        txtCantidad = new JTextField(String.valueOf(cantidad));
        txtCantidad.setBounds(150, 150, 200, 25);
        add(txtCantidad);

        JLabel lblSucursal = new JLabel("Sucursal:");
        lblSucursal.setBounds(30, 190, 120, 25);
        add(lblSucursal);
        comboSucursal = new JComboBox<>(new String[]{"Sucursal del Norte", "Sucursal del Sur", "Sucursal Central"});
        comboSucursal.setSelectedItem(sucursal);
        comboSucursal.setBounds(150, 190, 200, 25);
        add(comboSucursal);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 230, 120, 25);
        add(lblPrecio);
        txtPrecio = new JTextField(String.valueOf(precio));
        txtPrecio.setBounds(150, 230, 200, 25);
        add(txtPrecio);

        JLabel lblVenc = new JLabel("Fecha Venc:");
        lblVenc.setBounds(30, 270, 120, 25);
        add(lblVenc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(java.sql.Date.valueOf(fechaVencimiento));
        dateChooser.setBounds(150, 270, 200, 25);
        add(dateChooser);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.setBounds(140, 330, 100, 30);
        btnGuardar.setBackground(new Color(166, 135, 118));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> actualizarProducto(onSuccess));
        add(btnGuardar);
    }

    private void actualizarProducto(Runnable onSuccess) {
        String nombre = txtNombre.getText().trim();
        String lab = txtLaboratorio.getText().trim();
        String tipo = txtTipo.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
        String sucursal = (String) comboSucursal.getSelectedItem();
        String precioTexto = txtPrecio.getText().trim();
        java.util.Date utilDate = dateChooser.getDate();

        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una fecha de vencimiento válida.");
            return;
        }

        String fechaVenc = new SimpleDateFormat("yyyy-MM-dd").format(utilDate);
        try {
            LocalDate venc = LocalDate.parse(fechaVenc);
            if (venc.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(this, "La fecha de vencimiento debe ser futura o igual a hoy.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa YYYY-MM-DD.");
            return;
        }

        int cantidad, precio;
        try {
            cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(this, "La cantidad no puede ser negativa.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido.");
            return;
        }

        try {
            precio = Integer.parseInt(precioTexto);
            if (precio < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido.");
            return;
        }

        try {
            Connection con = ConexionBD.conectar();
            String sql = "UPDATE inventario_productos SET nombre_producto=?, laboratorio=?, tipo=?, cantidad=?, sucursal=?, precio=?, fecha_vencimiento=? WHERE id_producto=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, lab);
            stmt.setString(3, tipo);
            stmt.setInt(4, cantidad);
            stmt.setString(5, sucursal);
            stmt.setInt(6, precio);
            stmt.setString(7, fechaVenc);
            stmt.setInt(8, idProducto);

            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente");
            onSuccess.run();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar producto: " + e.getMessage());
        }
    }
}
