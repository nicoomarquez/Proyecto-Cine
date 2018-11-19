package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador_Cine;
import controlador.VentaControlador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;

public class ConfirmarVenta extends JFrame {

	private JPanel contentPane;
	private JTextField pelicula;
	private JTextField cantEntradas;
	private JTextField fecha;
	private JTextField formaPago;
	private JTextField total;
	private JTextField promociones;
	private static ConfirmarVenta instancia=null;
	//variables de ventanas anteriores
	private static String establecimiento;
	private static String peliculaConDetalle;//Nombre + idioma o subtitulos
	private static LocalDate diaDeSemana;
	private static LocalTime horario;
	private static int cantidadEntradas=0;
	private static int [][] asientosSeleccionados;
	private static String metodoPago="";
	static String  nro="", cod="";
	static int mes,anio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmarVenta frame = new ConfirmarVenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static ConfirmarVenta  getInstancia() {
		// TODO Auto-generated method stub
		if(instancia==null)
			instancia=new ConfirmarVenta ();
		return instancia;
	}
	public static void setInfoAnterior(String e, String p, LocalDate d, LocalTime h, int cantEntradas, int mat[][], String mp){
		establecimiento=e;
		peliculaConDetalle=p;
		diaDeSemana=d;
		horario=h;
		cantidadEntradas=cantEntradas;
		asientosSeleccionados=mat;	
		metodoPago=mp;
	}
	
	public static void setDatosTarjeta(String n, String c, int m , int a){
		nro=n;
		cod=c;
		mes=m;
		anio=a;
	}
	/**
	 * Create the frame.
	 */
	public ConfirmarVenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDetalleEntradas = new JLabel("Detalle entradas:");
		lblDetalleEntradas.setBounds(24, 11, 137, 14);
		contentPane.add(lblDetalleEntradas);
		
		pelicula = new JTextField();
		pelicula.setEditable(false);
		pelicula.setBounds(75, 36, 171, 20);
		contentPane.add(pelicula);
		pelicula.setColumns(10);
		pelicula.setText(peliculaConDetalle);
		JLabel lblPelicula = new JLabel("Pelicula: ");
		lblPelicula.setBounds(24, 39, 68, 14);
		contentPane.add(lblPelicula);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(277, 39, 59, 14);
		contentPane.add(lblCantidad);
		
		cantEntradas = new JTextField();
		cantEntradas.setEditable(false);
		cantEntradas.setBounds(334, 36, 40, 20);
		contentPane.add(cantEntradas);
		cantEntradas.setColumns(10);
		cantEntradas.setText(String.valueOf(cantidadEntradas));
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(24, 88, 52, 14);
		contentPane.add(lblFecha);
		
		fecha = new JTextField();
		fecha.setEditable(false);
		fecha.setBounds(75, 85, 86, 20);
		contentPane.add(fecha);
		fecha.setColumns(10);
		fecha.setText(LocalDate.now().toString());
		
		formaPago = new JTextField();
		formaPago.setEditable(false);
		formaPago.setBounds(263, 85, 111, 20);
		contentPane.add(formaPago);
		formaPago.setColumns(10);
		if(metodoPago.equals("Tarjeta de credito"))
			formaPago.setText(metodoPago+"  Termianda en "+nro.substring(nro.length()-4, nro.length()));
		formaPago.setText(metodoPago);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago:");
		lblFormaDePago.setBounds(171, 88, 96, 14);
		contentPane.add(lblFormaDePago);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(24, 138, 52, 14);
		contentPane.add(lblTotal);
		
		total = new JTextField();
		total.setEditable(false);
		total.setBounds(75, 135, 75, 20);
		contentPane.add(total);
		total.setColumns(10);
		total.setText(String.valueOf(Controlador_Cine.getPrecioEntradas()*Integer.parseInt(cantEntradas.getText())));
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(24, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*private static String establecimiento;
	private static String peliculaConDetalle;//Nombre + idioma o subtitulos
	private static LocalDate diaDeSemana;
	private static LocalTime horario;
	private static int cantidadEntradas=0;
	private static int [][] asientosSeleccionados;
	private static String metodoPago="";
	static String  nro="", cod="";
	static int mes,anio;*/
				VentaControlador.getInstancia().crearVenta(
					establecimiento,peliculaConDetalle,diaDeSemana,horario,cantidadEntradas,asientosSeleccionados,metodoPago,nro,cod,mes,anio	
				);
			}
		});
		btnConfirmar.setBounds(285, 227, 89, 23);
		contentPane.add(btnConfirmar);
		
		promociones = new JTextField();
		promociones.setEditable(false);
		promociones.setBounds(263, 128, 161, 58);
		contentPane.add(promociones);
		promociones.setColumns(10);
		
		JLabel lblPromociones = new JLabel("Promociones:");
		lblPromociones.setBounds(171, 138, 96, 14);
		contentPane.add(lblPromociones);
	}

	
}
