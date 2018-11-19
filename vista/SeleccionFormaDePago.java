package vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;

public class SeleccionFormaDePago extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nro;
	private JTextField cod;
	private static SeleccionFormaDePago instancia=null;
	private JButton btnConfirmar;
	JMonthChooser monthChooser;
	JYearChooser yearChooser;
	//Datos de la pelicula seleccionada
	private static String establecimiento;
	private static String peliculaConDetalle;//Nombre + idioma o subtitulos
	private static LocalDate diaDeSemana;
	private static LocalTime horario;
	private static int cantidadEntradas;
	private static int [][] asientosSeleccionados;
	JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionFormaDePago frame = new SeleccionFormaDePago();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static SeleccionFormaDePago getInstancia(){
		if(instancia==null)
			instancia=new SeleccionFormaDePago();
		return instancia;
	}
	

	
	public static void setInfoAnterior(String e, String p, LocalDate d, LocalTime h, int cantEntradas, int mat[][]){
		establecimiento=e;
		peliculaConDetalle=p;
		diaDeSemana=d;
		horario=h;
		cantidadEntradas=cantEntradas;
		asientosSeleccionados=mat;
		
	}
	
	/**
	 * Create the frame.
	 */
	public SeleccionFormaDePago() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago: ");
		lblFormaDePago.setBounds(10, 21, 106, 14);
		contentPane.add(lblFormaDePago);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(100, 18, 162, 20);
		comboBox.addItem("-");
		comboBox.addItem("Efectivo");
		comboBox.addItem("Tarjeta de credito");
		contentPane.add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(32, 73, 307, 109);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConfirmar.setEnabled(true);
				if(comboBox.getSelectedItem().equals("Tarjeta de credito"))
					panel.setVisible(true);
				else{ 
					panel.setVisible(false);
					if(comboBox.getSelectedItem().equals("-"))
						btnConfirmar.setEnabled(false);
				}	
			}
		});
		JLabel lblNroTarjeta = new JLabel("Nro Tarjeta: ");
		lblNroTarjeta.setBounds(10, 11, 91, 14);
		panel.add(lblNroTarjeta);
		
		nro = new JTextField();
		nro.setBounds(92, 8, 173, 20);
		panel.add(nro);
		nro.setColumns(10);
		
		monthChooser = new JMonthChooser();
		monthChooser.setBounds(92, 34, 116, 20);
		panel.add(monthChooser);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(218, 34, 47, 20);
		panel.add(yearChooser);
		
		JLabel lblVencimiento = new JLabel("Vencimiento:");
		lblVencimiento.setBounds(10, 40, 91, 14);
		panel.add(lblVencimiento);
		
		cod = new JTextField();
		cod.setBounds(102, 67, 54, 20);
		panel.add(cod);
		cod.setColumns(10);
		
		JLabel lblCodSeg = new JLabel("Cod Seguridad:");
		lblCodSeg.setBounds(10, 70, 146, 14);
		panel.add(lblCodSeg);
		
		 btnConfirmar = new JButton("Siguiente");
		 btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().equals("Tarjeta de credito"))
					ConfirmarVenta.setDatosTarjeta(nro.getText(), cod.getText(),monthChooser.getMonth() ,yearChooser.getYear());
				
				ConfirmarVenta.setInfoAnterior(establecimiento, peliculaConDetalle, diaDeSemana, horario, cantidadEntradas, asientosSeleccionados,comboBox.getSelectedItem().toString() );
				ConfirmarVenta.getInstancia().setLocationRelativeTo(null);
				ConfirmarVenta.getInstancia().setVisible(true);
				
				dispose();
			}
		});
		btnConfirmar.setBounds(283, 227, 99, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				SeleccionAsientos.getInstancia().setLocationRelativeTo(null);
				SeleccionAsientos.getInstancia().setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(32, 227, 89, 23);
		contentPane.add(btnAtras);
		this.setTitle("Venta de entradas-Forma de pago");
	}
}
