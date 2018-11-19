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

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BajaEstablecimiento extends JFrame {

	private JPanel contentPane;
	private Vector<Cine_View> cv;
	private static BajaEstablecimiento instancia;

	
	public static BajaEstablecimiento getInstancia() {
		if (instancia == null)
			instancia = new BajaEstablecimiento();
		return instancia;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaEstablecimiento frame = new BajaEstablecimiento();
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
	public BajaEstablecimiento() {
		setTitle("Baja Cine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel etiquetaCine = new JLabel("Cines:");
		etiquetaCine.setBounds(74, 73, 73, 19);
		contentPane.add(etiquetaCine);
		
		JComboBox<String> comboBoxCines = new JComboBox<String>();
		comboBoxCines.setBounds(176, 73, 196, 19);
		contentPane.add(comboBoxCines);
		contentPane.add(comboBoxCines);
		
		JButton eliminarCine = new JButton("Eliminar");
		eliminarCine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cine c = Controlador_Cine.getInstanciaCine().buscarCineNombre((String) comboBoxCines.getSelectedItem());
				Controlador_Cine.getInstanciaCine().bajaCine(c.getCuit());
			}
		});
		eliminarCine.setBounds(137, 185, 140, 28);
		contentPane.add(eliminarCine);
		cv = AdmPersistenciaCine.getInstancia().select1();
		for (int i = 0; i < cv.size(); i++) {
			comboBoxCines.addItem(cv.elementAt(i).getNombre());
		}
	}
}
