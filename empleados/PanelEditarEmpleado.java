package empleados;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexion.ConexionBD;

public class PanelEditarEmpleado extends JDialog {

    private JTextField txtNombre, txtApellido, txtCorreo, txtTelefono, txtOcupacion, txtHorario;
    private JComboBox<String> comboRol, comboSucursal;
    private int idEmpleado;
    private int idSucursalActual;

    public PanelEditarEmpleado(JFrame parent, int id) {
        super(parent, "Editar Empleado", true);
        setLayout(null);
        setSize(400, 500);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.WHITE);

        this.idEmpleado = id;

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

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 150, 100, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 150, 200, 25);
        add(txtTelefono);

        JLabel lblOcupacion = new JLabel("Ocupación:");
        lblOcupacion.setBounds(30, 190, 100, 25);
        add(lblOcupacion);
        txtOcupacion = new JTextField();
        txtOcupacion.setBounds(140, 190, 200, 25);
        add(txtOcupacion);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(30, 230, 100, 25);
        add(lblRol);
        comboRol = new JComboBox<>(new String[]{"Empleado"});
        comboRol.setBounds(140, 230, 200, 25);
        add(comboRol);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setBounds(30, 270, 100, 25);
        add(lblHorario);
        txtHorario = new JTextField();
        txtHorario.setBounds(140, 270, 200, 25);
        add(txtHorario);

        JLabel lblSucursal = new JLabel("Sucursal:");
        lblSucursal.setBounds(30, 310, 100, 25);
        add(lblSucursal);
        comboSucursal = new JComboBox<>();
        comboSucursal.setBounds(140, 310, 200, 25);
        add(comboSucursal);

        cargarDatosEmpleado();
        cargarSucursales();

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.setBounds(120, 370, 140, 30);
        btnGuardar.setBackground(new Color(166, 135, 118));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.addActionListener(e -> actualizarEmpleado());
        add(btnGuardar);
    }

    private void cargarDatosEmpleado() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT nombre, apellido, correo, telefono, ocupacion, horario, id_sucursal FROM usuarios_huellitas WHERE id_usuario = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, idEmpleado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                txtCorreo.setText(rs.getString("correo"));
                txtTelefono.setText(rs.getString("telefono"));
                txtOcupacion.setText(rs.getString("ocupacion"));
                txtHorario.setText(rs.getString("horario"));
                idSucursalActual = rs.getInt("id_sucursal");
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void cargarSucursales() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "SELECT id_sucursal, nombre FROM sucursales";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String item = rs.getInt("id_sucursal") + " - " + rs.getString("nombre");
                comboSucursal.addItem(item);
                if (rs.getInt("id_sucursal") == idSucursalActual) {
                    comboSucursal.setSelectedItem(item);
                }
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sucursales: " + e.getMessage());
        }
    }

    private void actualizarEmpleado() {
        try {
            Connection con = ConexionBD.conectar();
            String query = "UPDATE usuarios_huellitas SET nombre=?, apellido=?, correo=?, telefono=?, ocupacion=?, rol=?, horario=?, id_sucursal=? WHERE id_usuario=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, txtNombre.getText());
            stmt.setString(2, txtApellido.getText());
            stmt.setString(3, txtCorreo.getText());
            stmt.setString(4, txtTelefono.getText());
            stmt.setString(5, txtOcupacion.getText());
            stmt.setString(6, (String) comboRol.getSelectedItem());
            stmt.setString(7, txtHorario.getText());

            String seleccion = (String) comboSucursal.getSelectedItem();
            int idSucursal = Integer.parseInt(seleccion.split(" - ")[0]);
            stmt.setInt(8, idSucursal);
            stmt.setInt(9, idEmpleado);

            stmt.executeUpdate();
            stmt.close();
            con.close();

            JOptionPane.showMessageDialog(this, "Empleado actualizado con éxito");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }
} 
