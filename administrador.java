import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class administrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					administrador frame = new administrador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public administrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1000, 600);
		setBackground(new Color(242,239,233));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(180,217,200));
		panel.setBounds(10, 34, 966, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbUser = new JLabel("USER_01");
		lbUser.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 13));
		lbUser.setBounds(825, 32, 64, 17);
		panel.add(lbUser);
		
		JLabel lbFotoPerfil = new JLabel("");
		lbFotoPerfil.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Profile_pic_re_iwgo-removebg-preview (1).png"));
		lbFotoPerfil.setBounds(892, 0, 64, 80);
		panel.add(lbFotoPerfil);
		
		JLabel lbCargo = new JLabel("ADMINISTRADOR");
		lbCargo.setForeground(new Color(70, 140, 120));
		lbCargo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
		lbCargo.setBounds(10, 53, 116, 17);
		panel.add(lbCargo);
		
		JLabel lbTitulo = new JLabel("BIENVENDIDOS A SALUD DE HUELLITAS");
		lbTitulo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(47, 5, 882, 65);
		panel.add(lbTitulo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 124, 446, 429);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEmpleados = new JButton("Empleados");
		btnEmpleados.setHorizontalAlignment(SwingConstants.LEADING);
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEmpleados.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnEmpleados.setBackground(new Color(166,136,118));
		btnEmpleados.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Hire_re_gn5j-removebg-preview (1).png"));
		btnEmpleados.setBounds(69, 22, 257, 57);
		panel_1.add(btnEmpleados);
		
		JButton btnSucursales = new JButton("Sucursales");
		btnSucursales.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnSucursales.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_medicine_b1ol-removebg-preview (1).png"));
		btnSucursales.setBackground(new Color(166,136,118));
		btnSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSucursales.setBounds(69, 165, 257, 57);
		panel_1.add(btnSucursales);
		
		JButton btnCredito = new JButton("Reporte Poliza");
		btnCredito.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnCredito.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_discount_d4bd-removebg-preview (1).png"));
		btnCredito.setBounds(69, 313, 257, 57);
		btnCredito.setBackground(new Color(166,136,118));
		panel_1.add(btnCredito);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
		btnCerrarSesion.setBounds(0, 399, 119, 30);
		panel_1.add(btnCerrarSesion);
		btnCerrarSesion.setBackground(new Color(255, 94, 94));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(487, 124, 489, 429);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Web_search_re_efla-removebg-preview-removebg-preview (1).png"));
		btnInventario.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnInventario.setBackground(new Color(128, 128, 255));
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInventario.setBounds(173, 23, 257, 57);
		btnInventario.setBackground(new Color(166,136,118));
		panel_2.add(btnInventario);
		
		JButton btnCitas = new JButton("Citas");
		btnCitas.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Events_re_98ue-removebg-preview (1).png"));
		btnCitas.setBounds(173, 166, 257, 57);
		btnCitas.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnCitas.setBackground(new Color(166, 136, 118));
		panel_2.add(btnCitas);
		
		JButton btnGestionFinanciera = new JButton("Gestion Financiera");
		btnGestionFinanciera.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Finance_re_gnv2-removebg-preview (1).png"));
		btnGestionFinanciera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestionFinanciera.setBounds(173, 320, 262, 57);
		btnGestionFinanciera.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnGestionFinanciera.setBackground(new Color(166, 136, 118));
		panel_2.add(btnGestionFinanciera);
	}
}
