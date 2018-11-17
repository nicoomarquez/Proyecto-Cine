package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LogInControlador;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInCliente extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private static LogInCliente instancia=null;
	
	public static LogInCliente getInstancia()
	{
		if (instancia == null)
			instancia = new LogInCliente();
		return instancia;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInCliente frame = new LogInCliente();
					frame.setLocationRelativeTo(null);
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
	public LogInCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 403, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(131, 84, 119, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 135, 119, 20);
		contentPane.add(passwordField);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblUsuario.setBounds(58, 87, 87, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblContrasea.setBounds(58, 138, 87, 14);
		contentPane.add(lblContrasea);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String us=textField.getText(),pass=passwordField.getText();
				if(us.isEmpty() || pass.isEmpty())
					JOptionPane.showMessageDialog(null, "ERROR: COMPLETE LOS CAMPOS PARA INGRESAR");
				else{
					if(LogInControlador.getInstancia().IniciarSesionCliente(us, pass))
						//agregar codigo del menucliente
						JOptionPane.showMessageDialog(null, "INICIANDO SESION COMO CLIENTE");
					else
						JOptionPane.showMessageDialog(null, "ERROR AL INICIAR SESION COMO CLIENTE");
				}
			}
		});
		btnIniciarSesion.setBounds(211, 197, 119, 35);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CrearCliente.getInstancia().setVisible(true);
				CrearCliente.getInstancia().setLocationRelativeTo(null);
			}
		});
		btnCrearCuenta.setBounds(26, 197, 119, 35);
		contentPane.add(btnCrearCuenta);
		
		JLabel lblCine = new JLabel("Cine");
		lblCine.setFont(new Font("ISOCPEUR", Font.PLAIN, 33));
		lblCine.setBounds(137, 11, 113, 35);
		contentPane.add(lblCine);
	}
}
