package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.Cine_View;
import controlador.Controlador_Cine;
import negocio.Cine;
import persistencia.AdmPersistenciaCine;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ModificarEstablecimiento extends JFrame {

	private JPanel contentPane;
	private static ModificarEstablecimiento instancia;
	private JTextField textFieldCuit;
	private JTextField textFieldNombre;
	private JTextField textFieldDomicilio;
	private JTextField textFieldCantSalas;
	private JTextField textFieldCapTotal;
	private Vector<Cine_View> cv;
	
	public static ModificarEstablecimiento getInstancia() {
		if (instancia == null){
			instancia = new ModificarEstablecimiento();
		}
		return instancia;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarEstablecimiento frame = new ModificarEstablecimiento();
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
	public ModificarEstablecimiento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(290, 18, 63, 14);
		contentPane.add(lblCuit);
		
		
		textFieldCuit = new JTextField();
		textFieldCuit.setBounds(268, 36, 108, 20);
		contentPane.add(textFieldCuit);
		textFieldCuit.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(290, 67, 86, 14);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(268, 87, 108, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(290, 118, 73, 14);
		contentPane.add(lblDomicilio);
		
		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(268, 139, 106, 20);
		contentPane.add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);
		
		JLabel lblSalas = new JLabel("Salas:");
		lblSalas.setBounds(30, 188, 63, 14);
		contentPane.add(lblSalas);
		
		textFieldCantSalas = new JTextField();
		textFieldCantSalas.setBounds(85, 185, 86, 20);
		contentPane.add(textFieldCantSalas);
		textFieldCantSalas.setColumns(10);
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(191, 188, 86, 14);
		contentPane.add(lblCapacidad);
		
		textFieldCapTotal = new JTextField();
		textFieldCapTotal.setBounds(268, 185, 86, 20);
		contentPane.add(textFieldCapTotal);
		textFieldCapTotal.setColumns(10);
		
		JComboBox<String> comboBoxCines = new JComboBox<String>();
		comboBoxCines.setBounds(50, 36, 118, 20);
		contentPane.add(comboBoxCines);
		cv = AdmPersistenciaCine.getInstancia().select1();
		for (int i = 0; i < cv.size(); i++) {
			comboBoxCines.addItem(cv.elementAt(i).getNombre());
		}
	
		Cine c = Controlador_Cine.getInstanciaCine().buscarCineNombre((String) comboBoxCines.getSelectedItem());
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cine c = Controlador_Cine.getInstanciaCine().buscarCineNombre((String) comboBoxCines.getSelectedItem());
				Controlador_Cine.getInstanciaCine().modificarCine(c.getCuit(), textFieldCuit.getText(),
										textFieldNombre.getText(), textFieldDomicilio.getText(),
									Integer.parseInt(textFieldCantSalas.getText()), Integer.parseInt(textFieldCapTotal.getText()));
			}
		});
		btnModificar.setBounds(139, 227, 118, 23);
		contentPane.add(btnModificar);
		
	
		
	}
}
