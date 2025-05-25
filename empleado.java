
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class empleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empleado frame = new empleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public empleado() {
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
		
		JLabel lbTitulo = new JLabel("BIENVENDIDOS A SALUD DE HUELLITAS");
		lbTitulo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 20));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(292, 29, 386, 25);
		panel.add(lbTitulo);
		
		JLabel lbUser = new JLabel("USER_02");
		lbUser.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 13));
		lbUser.setBounds(825, 32, 64, 17);
		panel.add(lbUser);
		
		JLabel lbFotoPerfil = new JLabel("");
		lbFotoPerfil.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Profile_pic_re_iwgo-removebg-preview (1).png"));
		lbFotoPerfil.setBounds(892, 0, 64, 80);
		panel.add(lbFotoPerfil);
		
		JLabel lbCargo = new JLabel("EMPLEADO");
		lbCargo.setForeground(new Color(70, 140, 120));
		lbCargo.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
		lbCargo.setBounds(10, 53, 84, 17);
		panel.add(lbCargo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 145, 966, 408);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnConsulta = new JButton("Consulta Medica");
		btnConsulta.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_the_search_s0xf-removebg-preview (1).png"));
		btnConsulta.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnConsulta.setBackground(new Color(200, 200, 255));
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsulta.setBounds(372, 231, 257, 57);
		btnConsulta.setBackground(new Color(166,136,118));
		panel_1.add(btnConsulta);
		
		
		JButton btnCitas = new JButton("Agendar Cita");
		btnCitas.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Events_re_98ue-removebg-preview (1).png"));
		btnCitas.setBounds(372, 97, 257, 57);
		btnCitas.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnCitas.setBackground(new Color(166, 136, 118));
		panel_1.add(btnCitas);
		
		JButton btnInventario = new JButton("Inventario");
		btnInventario.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_Web_search_re_efla-removebg-preview-removebg-preview (1).png"));
		btnInventario.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnInventario.setBackground(new Color(128, 128, 255));
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInventario.setBounds(78, 35, 257, 57);
		btnInventario.setBackground(new Color(166,136,118));
		panel_1.add(btnInventario);
		
		JButton btnCredito = new JButton("Reporte poliza");
		btnCredito.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnCredito.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_discount_d4bd-removebg-preview (1).png"));
		btnCredito.setBounds(78, 163, 257, 57);
		btnCredito.setBackground(new Color(166,136,118));
		panel_1.add(btnCredito);
		
		JButton btnSucursales = new JButton("Tu sucursal");
		btnSucursales.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 15));
		btnSucursales.setIcon(new ImageIcon("C:\\Users\\Daniel\\OneDrive\\Documentos\\REPOSITORIOS\\Proyecto_saludHuellitas\\src\\img\\undraw_medicine_b1ol-removebg-preview (1).png"));
		btnSucursales.setBackground(new Color(166,136,118));
		btnSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSucursales.setBounds(78, 287, 257, 57);
		panel_1.add(btnSucursales);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 12));
		btnCerrarSesion.setBounds(10, 378, 119, 30);
		panel_1.add(btnCerrarSesion);
		btnCerrarSesion.setBackground(new Color(255, 94, 94));
		
		JTextArea txaTareasPendientes = new JTextArea();
		txaTareasPendientes.setBackground(new Color(185, 182, 177));
		txaTareasPendientes.setBounds(657, 53, 299, 345);
		panel_1.add(txaTareasPendientes);
		
		JLabel lblNewLabel = new JLabel("Tareas Pendientes");
		lblNewLabel.setForeground(new Color(104, 65, 65));
		lblNewLabel.setFont(new Font("GeoSlab703 Md BT", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(657, 10, 299, 33);
		panel_1.add(lblNewLabel);
		
		
		
	}
}
