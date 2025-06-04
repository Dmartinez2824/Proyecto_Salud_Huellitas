package empleados;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import conexion.ConexionBD;

public class PanelFormularioEmpleado extends JDialog {

    private JTextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtOcupacion, txtHorario;
    private JPasswordField txtContrasena;
    private JComboBox<String> comboRol, comboSucursal;

    public PanelFormularioEmpleado(JFrame parent) {
        super(parent, "Agregar Empleado", true);
        setLayout(null);
        setSize(400, 500);
        setLocationRelativeTo(parent);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 30, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 30, 200, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 70, 100, 25);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(140, 70, 200, 25);
        add(txtApellido);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 110, 100, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 110, 200, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(30, 150, 100, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(140, 150, 200, 25);
        add(txtContrasena);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 190, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 190, 200, 25);
        add(txtTelefono);

        JLabel lblOcupacion = new JLabel("Ocupación:");
        lblOcupacion.setBounds(30, 230, 100, 25);
        add(lblOcupacion);

        txtOcupacion = new JTextField();
        txtOcupacion.setBounds(140, 230, 200, 25);
        add(txtOcupacion);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(30, 270, 100, 25);
        add(lblRol);

        comboRol = new JComboBox<>(new String[]{"Empleado"});
        comboRol.setBounds(140, 270, 200, 25);
        add(comboRol);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setBounds(30, 310, 100, 25);
        add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(140, 310, 200, 25);
        add(txtHorario);

        JLabel lblSucursal = new JLabel("Sucursal:");
        lblSucursal.setBounds(30, 350, 100, 25);
        add(lblSucursal);

        comboSucursal = new JComboBox<>();
        comboSucursal.setBounds(140, 350, 200, 25);
        add(comboSucursal);
        cargarSucursales();

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(140, 400, 100, 30);
        btnGuardar.addActionListener(e -> guardarEmpleado());
        add(btnGuardar);
    }

    private void cargarSucursales() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT id_sucursal, nombre FROM sucursales";
            PreparedStatement stmt = con.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comboSucursal.addItem(rs.getInt("id_sucursal") + " - " + rs.getString("nombre"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sucursales: " + e.getMessage());
        }
    }

    private void guardarEmpleado() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "INSERT INTO usuarios_huellitas (nombre, apellido, correo, contraseña, telefono, ocupacion, rol, horario, id_sucursal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtApellido.getText());
            stmt.setString(3, txtCorreo.getText());
            stmt.setString(4, new String(txtContrasena.getPassword()));
            stmt.setString(5, txtTelefono.getText());
            stmt.setString(6, txtOcupacion.getText());
            stmt.setString(7, (String) comboRol.getSelectedItem());
            stmt.setString(8, txtHorario.getText());

            String seleccion = (String) comboSucursal.getSelectedItem();
            int idSucursal = Integer.parseInt(seleccion.split(" - ")[0]);
            stmt.setInt(9, idSucursal);

            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Empleado registrado con éxito");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }
} 
