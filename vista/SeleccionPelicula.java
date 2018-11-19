package vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.Controlador_Cine;
import negocio.Cine;
import negocio.Funcion;
import negocio.Operador;
import negocio.Pelicula;
import persistencia.AdmPersistenciaCine;
import persistencia.AdmPersistenciaPelicula;
import persistencia.AdmPersistenciaSala;
import persistencia.AdmPersistenciaUsuario;
import view.Cine_View;
import view.Pelicula_View;

import javax.swing.event.ListSelectionEvent;


public class SeleccionPelicula extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static SeleccionPelicula instancia;
	private JButton btnSiguiente;
	JList<String> listaEstablecimientos = new JList<String>();
	JList<String> listaPeliculas = new JList<String>();
	JList<String> listaDias = new JList<String>();
	JList<String> listaHorarios = new JList<String>();
	Vector<Pelicula_View>peliculas;
	public static SeleccionPelicula getInstancia()
	{
		if (instancia == null){
			instancia = new SeleccionPelicula();
		}
		return instancia;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionPelicula frame = new SeleccionPelicula();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					SeleccionPelicula.getInstancia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public SeleccionPelicula() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblEstablecimiento = new JLabel("Establecimiento:");
		lblEstablecimiento.setBounds(20, 30, 97, 14);
		getContentPane().add(lblEstablecimiento);
		
		JLabel lblPelicula = new JLabel("Pelicula: ");
		lblPelicula.setBounds(178, 30, 97, 14);
		getContentPane().add(lblPelicula);
		
		JLabel lblDia = new JLabel("Dia: ");
		lblDia.setBounds(338, 30, 97, 14);
		getContentPane().add(lblDia);
		
		listaEstablecimientos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {			
			toEmptyList(listaHorarios);
			toEmptyList(listaDias);
			toEmptyList(listaPeliculas);
			
			//Agrego peliculas
				DefaultListModel<String> dlm2=new DefaultListModel<String>();
				peliculas=Controlador_Cine.getInstanciaCine().getPeliculas();
				for(Pelicula_View e:peliculas)
					dlm2.addElement(e.getNombreIdioma());
				
				listaPeliculas.setModel(dlm2);
				btnSiguiente.setEnabled(false);
				
			//fin de agregado de peliculas
			}
		});
		
		listaEstablecimientos.setBounds(20, 55, 140, 249);
		getContentPane().add(listaEstablecimientos);
		
		//Agrego establecimientos
			
			DefaultListModel<String> dlm=new DefaultListModel<String>();
			Vector<Cine_View>establecimientos=Controlador_Cine.getInstanciaCine().getCines();
			for(Cine_View c:establecimientos)
				dlm.addElement(c.getNombre());	
			listaEstablecimientos.setModel(dlm);
		//fin de agregado de establecimientos
			
		listaPeliculas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				toEmptyList(listaDias);
				toEmptyList(listaHorarios);
				//Agrego dias
				Integer cineSelected=listaEstablecimientos.getSelectedIndex();
				Integer peliculaSelected=listaPeliculas.getSelectedIndex();
				if(cineSelected>-1 && peliculaSelected>-1){
					DefaultListModel<String> dlm3=new DefaultListModel<String>();
					Vector<LocalDate>dias=Funcion.getDias(
										establecimientos.get(cineSelected),
										peliculas.get(peliculaSelected)
										);
					
					for(LocalDate e1:dias)
						dlm3.addElement(e1.toString());	
					//fin de agregado de dias
					listaDias.setModel(dlm3);
				}
				btnSiguiente.setEnabled(false);
				
				
			}
		});
		listaPeliculas.setBounds(178, 55, 140, 249);
		getContentPane().add(listaPeliculas);
			
				
		listaDias.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e2) {
				toEmptyList(listaHorarios);
				//Agrego horarios
				Integer cineSelected=listaEstablecimientos.getSelectedIndex();
				Integer peliculaSelected=listaPeliculas.getSelectedIndex();
				String diaSelected=listaDias.getSelectedValue();
				if(cineSelected>-1 && peliculaSelected>-1 && diaSelected!=null && !diaSelected.isEmpty()){
					DefaultListModel<String> dlm4=new DefaultListModel<String>();
					Vector<LocalTime>horarios=Funcion.getHorarios(
										establecimientos.get(cineSelected),
										peliculas.get(peliculaSelected),
										LocalDate.parse(diaSelected)
										);
					for(LocalTime e:horarios)
						dlm4.addElement(e.toString());	
					listaHorarios.setModel(dlm4);
				}
				btnSiguiente.setEnabled(false);
			//fin de agregado de horarios
			}
		});
		listaDias.setBounds(338, 55, 140, 249);
		getContentPane().add(listaDias);
		listaHorarios.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnSiguiente.setEnabled(true);
			}
		});
		
			
		listaHorarios.setBounds(496, 55, 140, 249);
		getContentPane().add(listaHorarios);
		
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setBounds(496, 30, 46, 14);
		getContentPane().add(lblHorario);
		
		 btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setEnabled(false);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SeleccionAsientos.getInstancia().setVisible(true);
				SeleccionAsientos.getInstancia().setLocationRelativeTo(null);
				SeleccionAsientos.getInstancia().setDetallesPelicula(
						listaEstablecimientos.getSelectedValue(), 
						listaPeliculas.getSelectedValue(), 
						LocalDate.parse(listaDias.getSelectedValue()), 
						LocalTime.parse(listaHorarios.getSelectedValue())
				);
				dispose();
			}
		});
		btnSiguiente.setBounds(366, 353, 127, 38);
		getContentPane().add(btnSiguiente);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(105, 353, 127, 38);
		getContentPane().add(btnCancelar);
		//this.setPreferredSize(new java.awt.Dimension(800, 600));
		this.setTitle("Venta de entradas-Seleccion de pelicula");
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//this.setMinimumSize(new java.awt.Dimension(800, 600));
		//this.setResizable(false);
		setBounds(100, 100, 672, 453);

	}
	
	private void toEmptyList(JList t){
		ListModel<?> l=t.getModel();
		if(l.getSize()>0){
			DefaultListModel<?> dlm=(DefaultListModel<?>) l;
			dlm.removeAllElements();
		}
	}
}
