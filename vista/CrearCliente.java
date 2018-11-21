package vista;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.LogInControlador;
import negocio.Cliente;
import negocio.Rol;
import negocio.Usuario;

public class CrearCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CrearCliente instancia = null;
	private JPanel contentPane;
	private JTextField dni;
	private JTextField nom;
	private JTextField ape;
	private JTextField nac;
	private JTextField dom;
	private JTextField mail;
	private JTextField us;
	private JLabel lblContrasea;
	private JButton btnCrear;
	private JButton btnCancelar;
	private JPanel panel;
	private JPasswordField pass;
	private boolean existeUsuario=false;
	private Usuario u;
	/**
	 * Launch the application.
	 */
	public static CrearCliente getInstancia(){
		if(instancia==null)
			instancia=new CrearCliente();
		
		return instancia;
	}
	
	/**
	 * Create the frame.
	 */
	private CrearCliente() {
		reiniciarComponentes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dni = new JTextField();
		dni.setBounds(58, 11, 165, 20);
		contentPane.add(dni);
		dni.setColumns(10);
		JButton btnValidarDni = new JButton("Validar DNI");
		btnValidarDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				u=LogInControlador.getInstancia().buscarUsuarioPorDni(dni.getText());
				if(u!=null){
					existeUsuario=true;
					nom.setText(u.getNombre());
					ape.setText(u.getApellido());
					nac.setText(u.getFechaNac().toString());
					mail.setText(u.getMail());
					dom.setText(u.getDom());
					us.setText(u.getNombreUsuario());
					pass.setText(u.getPassword());
				}
				else{
					existeUsuario=false;
					vaciarCampos();
				}
				//JOptionPane.showMessageDialog(null, "CLIENTE BUSCADO");
				panel.setVisible(true);
				btnCrear.setVisible(true);
			}
		});
		btnValidarDni.setBounds(244, 10, 104, 23);
		contentPane.add(btnValidarDni);
		
		panel = new JPanel();
		panel.setBounds(10, 56, 414, 153);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(10, 11, 65, 14);
		panel.add(lblNewLabel);
		
		nom = new JTextField();
		nom.setBounds(85, 8, 111, 20);
		panel.add(nom);
		nom.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(10, 47, 65, 14);
		panel.add(lblApellido);
		
		ape = new JTextField();
		ape.setBounds(85, 44, 111, 20);
		panel.add(ape);
		ape.setColumns(10);
		
		nac = new JTextField();
		nac.setBounds(85, 77, 111, 20);
		panel.add(nac);
		nac.setColumns(10);
		
		JLabel lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setBounds(10, 80, 99, 14);
		panel.add(lblNacimiento);
		
		dom = new JTextField();
		dom.setBounds(85, 108, 111, 20);
		panel.add(dom);
		dom.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio: ");
		lblDomicilio.setBounds(10, 111, 99, 14);
		panel.add(lblDomicilio);
		
		JLabel lblMail = new JLabel("Mail: ");
		lblMail.setBounds(213, 41, 46, 14);
		panel.add(lblMail);
		
		mail = new JTextField();
		mail.setBounds(258, 38, 146, 20);
		panel.add(mail);
		mail.setColumns(10);
		
		us = new JTextField();
		us.setBounds(293, 64, 111, 20);
		panel.add(us);
		us.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(213, 67, 70, 14);
		panel.add(lblUsuario);
		
		lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(211, 98, 83, 14);
		panel.add(lblContrasea);
		
		pass = new JPasswordField();
		pass.setBounds(293, 95, 111, 20);
		panel.add(pass);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(26, 227, 99, 23);
		contentPane.add(btnCancelar);
		
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(308, 227, 99, 23);
		contentPane.add(btnCrear);
		btnCrear.setVisible(false);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(existeUsuario){
					/**
					 * Si existe el usuario debo verificar que no tenga el rol cliente asignado
					 */
					
					Rol c=u.getCliente();
					if(c==null){
						LogInControlador.getInstancia().crearCuenta(u);
					}
					else{
						JOptionPane.showMessageDialog(null, "Ya existe como cliente");
					}
				}
				else{
					LogInControlador.getInstancia().crearCuenta(
							mail.getText(),
							us.getText(),
							pass.getText(),
							nom.getText(),
							dom.getText(),
							dni.getText(),								
							ape.getText(),
							nac.getText()
						);
				}
				
				vaciarCampos();
				reiniciarComponentes();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarComponentes();
				dispose();				
			}
		});
	}
	private void reiniciarComponentes() {
		// TODO Auto-generated method stub
		if(instancia!=null){
			dni.setText("");
			panel.setVisible(false);
		}
		
	}

	private void vaciarCampos(){
		mail.setText("");
		us.setText("");
		pass.setText("");
		nom.setText("");
		dom.setText("");							
		ape.setText("");
		nac.setText("");
	}
}
