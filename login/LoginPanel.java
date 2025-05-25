package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import conexion.ConexionBD;
import proyecto_SaludHuellitas.VentanaPrincipal;

public class LoginPanel extends JPanel {
    private VentanaPrincipal ventanaPrincipal;
    private JTextField txUsuario;
    private JPasswordField psContraseña;

    public LoginPanel(VentanaPrincipal vp) {
        this.ventanaPrincipal = vp;
        setLayout(null);
        setBackground(new Color(168, 171, 171));
//        setVisible(true);
        
        // Label Usuario
        JLabel lbUsuario = new JLabel("Usuario:");
        lbUsuario.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 22));
        lbUsuario.setBounds(497, 248, 130, 41);
        add(lbUsuario);

        // Campo Usuario
        txUsuario = new JTextField("Usuario@sena.edu.co", SwingConstants.CENTER);
        txUsuario.setFont(new Font("Roboto", Font.PLAIN, 14));
        txUsuario.setForeground(new Color(128, 128, 128));
        txUsuario.setBounds(701, 255, 212, 33);
        txUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txUsuario.getText().equals("Usuario@sena.edu.co")) {
                    txUsuario.setText("");
                    txUsuario.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txUsuario.getText().isEmpty()) {
                    txUsuario.setText("Usuario@sena.edu.co");
                    txUsuario.setForeground(new Color(128, 128, 128));
                }
            }
        });
        add(txUsuario);

        // Label Contraseña
        JLabel lbContraseña = new JLabel("Contraseña:");
        lbContraseña.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 22));
        lbContraseña.setBounds(497, 323, 139, 41);
        add(lbContraseña);

        // Campo Contraseña
        psContraseña = new JPasswordField("Contraseña");
        psContraseña.setFont(new Font("Roboto", Font.PLAIN, 14));
        psContraseña.setForeground(new Color(128, 128, 128));
        psContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        psContraseña.setBounds(701, 330, 212, 33);
        psContraseña.setEchoChar((char)0);
        psContraseña.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(psContraseña.getPassword()).equals("Contraseña")) {
                    psContraseña.setText("");
                    psContraseña.setEchoChar('*');
                    psContraseña.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (psContraseña.getPassword().length == 0) {
                    psContraseña.setText("Contraseña");
                    psContraseña.setEchoChar((char)0);
                    psContraseña.setForeground(new Color(128, 128, 128));
                }
            }
        });
        add(psContraseña);
        

        // Mostrar contraseña
        JCheckBox chbxShowPassword = new JCheckBox("Mostrar Contraseña");
        chbxShowPassword.setBackground(new Color(168, 171, 171));
        chbxShowPassword.setBounds(497, 389, 425, 21);
        chbxShowPassword.addActionListener(e -> {
            if (chbxShowPassword.isSelected()) {
                psContraseña.setEchoChar((char)0);
            } else {
                psContraseña.setEchoChar('*');
            }
        });
        add(chbxShowPassword);

        // Botón Ingresar
        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("GeoSlab703 Md BT", Font.BOLD, 16));
        btnIngresar.setBounds(342, 473, 252, 50);
        
     // Listener para ENTER en campo de usuario
        txUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnIngresar.doClick();  
                }
            }
        });

        // Listener para ENTER en campo de contraseña
        psContraseña.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnIngresar.doClick(); 
                }
            }
        });

        btnIngresar.addActionListener(e -> {
            String correo = txUsuario.getText().trim();
            String contrasena = new String(psContraseña.getPassword()).trim();

            if (validarLogin(correo, contrasena)) {
                String rol = obtenerRol(correo);
                JOptionPane.showMessageDialog(this, "Bienvenido " + rol);

                if ("Administrador".equalsIgnoreCase(rol)) {
                    ventanaPrincipal.mostrarPanel("admin");
                } else if ("Empleado".equalsIgnoreCase(rol)) {
                    ventanaPrincipal.mostrarPanel("empleado");
                } else {
                    JOptionPane.showMessageDialog(this, "Rol no reconocido");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
      
        });
        

        add(btnIngresar);
    }

    private boolean validarLogin(String correo, String contraseña) {
        try (Connection conn = ConexionBD.conectar()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
                return false;
            }
            String sql = "SELECT rol FROM usuarios_huellitas WHERE correo = ? AND contraseña = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al validar el login: " + e.getMessage());
            return false;
        }
    }
//
    private String obtenerRol(String correo) {
        try (Connection conn = ConexionBD.conectar()) {
            String sql = "SELECT rol FROM usuarios_huellitas WHERE correo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("rol");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
