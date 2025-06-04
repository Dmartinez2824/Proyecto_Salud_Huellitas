package inventario;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DialogAgregarProducto extends JDialog {

    private JTextField txtNombre, txtLaboratorio, txtTipo, txtCantidad, txtPrecio;
    private JComboBox<String> comboSucursal;
    private JDateChooser dateChooser;

    public DialogAgregarProducto(JFrame parent, Runnable onSuccess) {
        super(parent, "Agregar Producto", true);
        setLayout(null);
        setSize(400, 480);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 120, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(150, 30, 200, 25);
        add(txtNombre);

        JLabel lblLab = new JLabel("Laboratorio:");
        lblLab.setBounds(30, 70, 120, 25);
        add(lblLab);
        txtLaboratorio = new JTextField();
        txtLaboratorio.setBounds(150, 70, 200, 25);
        add(txtLaboratorio);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 110, 120, 25);
        add(lblTipo);
        txtTipo = new JTextField();
        txtTipo.setBounds(150, 110, 200, 25);
        add(txtTipo);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(30, 150, 120, 25);
        add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 150, 200, 25);
        add(txtCantidad);

        JLabel lblSucursal = new JLabel("Sucursal:");
        lblSucursal.setBounds(30, 190, 120, 25);
        add(lblSucursal);
        comboSucursal = new JComboBox<>(new String[]{"Sucursal del Norte", "Sucursal del Sur", "Sucursal Central"});
        comboSucursal.setBounds(150, 190, 200, 25);
        add(comboSucursal);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 230, 120, 25);
        add(lblPrecio);
        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 230, 200, 25);
        add(txtPrecio);

        JLabel lblVenc = new JLabel("Fecha Venc:");
        lblVenc.setBounds(30, 270, 120, 25);
        add(lblVenc);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(150, 270, 200, 25);
        add(dateChooser);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(140, 330, 100, 30);
        btnGuardar.setBackground(new Color(166, 135, 118));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarProducto(onSuccess));
        add(btnGuardar);
    }

    private void guardarProducto(Runnable onSuccess) {
        String nombre = txtNombre.getText().trim();
        String lab = txtLaboratorio.getText().trim();
        String tipo = txtTipo.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();
        String sucursal = (String) comboSucursal.getSelectedItem();
        String precioTexto = txtPrecio.getText().trim();
        java.util.Date utilDate = dateChooser.getDate();

        if (nombre.isEmpty() || lab.isEmpty() || tipo.isEmpty() || cantidadTexto.isEmpty() || precioTexto.isEmpty() || utilDate == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
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
            Connection con = conexion.ConexionBD.conectar();
            
            String sqlCheck = "SELECT COUNT(*) FROM inventario_productos WHERE nombre_producto=? AND laboratorio=? AND tipo=? AND cantidad=? AND sucursal=? AND precio=? AND fecha_vencimiento=?";
            PreparedStatement checkStmt = con.prepareStatement(sqlCheck);
            checkStmt.setString(1, nombre);
            checkStmt.setString(2, lab);
            checkStmt.setString(3, tipo);
            checkStmt.setInt(4, cantidad);
            checkStmt.setString(5, sucursal);
            checkStmt.setInt(6, precio);
            checkStmt.setString(7, fechaVenc);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "El producto ya existe en el inventario.");
                rs.close();
                checkStmt.close();
                con.close();
                return;
            }
            rs.close();
            checkStmt.close();

            // Insertar nuevo producto
            String sql = "INSERT INTO inventario_productos (nombre_producto, laboratorio, tipo, cantidad, sucursal, precio, fecha_vencimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, lab);
            stmt.setString(3, tipo);
            stmt.setInt(4, cantidad);
            stmt.setString(5, sucursal);
            stmt.setInt(6, precio);
            stmt.setString(7, fechaVenc);

            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
            onSuccess.run();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage());
        }
    }
}
