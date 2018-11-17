package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.LogInControlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import negocio.*;
import java.util.*;
public class LogInEmpleado extends JFrame {

	private static LogInEmpleado instancia=null;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static LogInEmpleado getInstancia(){
		if(instancia==null)
			instancia=new LogInEmpleado();
		return instancia;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInEmpleado frame = new LogInEmpleado();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInEmpleado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(80, 52, 83, 14);
		contentPane.add(lblUsuario);
		
		textField = new JTextField();
		textField.setBounds(162, 49, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(80, 107, 83, 14);
		contentPane.add(lblContrasea);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String us=textField.getText();
				String pw=passwordField.getText();
				
				if(us.isEmpty()||pw.isEmpty())
					JOptionPane.showMessageDialog(null,"Complete los campos solicitados");
				
				else{
					Usuario user=LogInControlador.getInstancia().IniciarSesionEmpleado(us, pw);
					if(user!=null){
						Vector<Rol> vEmpleado=user.getVectorEmpleados();
						if(vEmpleado.size()>1){
							SeleccionRolEmpleado.getInstancia(vEmpleado);
							SeleccionRolEmpleado.getInstancia().setLocationRelativeTo(null);
							SeleccionRolEmpleado.getInstancia().setVisible(true);
							dispose();
						}
						else{
							SeleccionRolEmpleado.getInstancia().mostrarRol(vEmpleado.get(0));
							dispose();
						}
						
					}
					else{
					 JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
					}
						
				}
			}
		});
		btnIniciarSesion.setBounds(116, 172, 126, 23);
		contentPane.add(btnIniciarSesion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 104, 107, 20);
		contentPane.add(passwordField);
	}

}
