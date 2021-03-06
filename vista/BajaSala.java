package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.Cine_View;
import controlador.Controlador_Cine;
import negocio.Cine;
import persistencia.AdmPersistenciaCine;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BajaSala extends JFrame {

	private JPanel contentPane;
	private static BajaSala instancia;
	private Vector<Cine_View> cv;
	private JTextField textFieldnombreSala;
	
	public static BajaSala getInstancia() {
		if (instancia == null)
			instancia = new BajaSala();
		return instancia;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaSala frame = new BajaSala();
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
	public BajaSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(114, 28, 138, 20);
		contentPane.add(comboBox_1);
		cv = Controlador_Cine.getInstanciaCine().getCines();
		for (int i = 0; i < cv.size(); i++) {
			comboBox_1.addItem(cv.elementAt(i).getNombre());
		}

		
		JLabel lblCine = new JLabel("Cine:");
		lblCine.setBounds(10, 36, 46, 14);
		contentPane.add(lblCine);
		
		JLabel lblNombreSala = new JLabel("Nombre Sala:");
		lblNombreSala.setBounds(10, 107, 90, 14);
		contentPane.add(lblNombreSala);
		
		textFieldnombreSala = new JTextField();
		textFieldnombreSala.setBounds(135, 104, 151, 20);
		contentPane.add(textFieldnombreSala);
		textFieldnombreSala.setColumns(10);
		
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cine c = Controlador_Cine.getInstanciaCine().
						buscarCineNombre((String) comboBox_1.getSelectedItem());
				c.bajaSala(textFieldnombreSala.getText());
			}
		});
		btnNewButton.setBounds(163, 177, 144, 23);
		contentPane.add(btnNewButton);
	}
}
