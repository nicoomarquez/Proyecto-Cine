package controlador;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.Vector;

import javax.swing.JOptionPane;

import persistencia.*;
import view.Funcion_View;
import view.Venta_View;
import negocio.*
;public class VentaControlador {
	private static VentaControlador instancia=null;
	private Vector<Venta> ventas;
	//private Vector<Promocion>promociones;
	
	private VentaControlador(){
		ventas=new Vector<Venta>();
		//promociones=new Vector<Promocion>();
	}
	
	public static VentaControlador getInstancia(){
		if(instancia==null)
			instancia=new VentaControlador();
		return instancia;
	}
	
	public Venta buscarVenta(long codigo){
		for(Venta v:ventas){
			if(v.getCodigo()==codigo)
				return v;
		}
		return AdmPersistenciaVenta.getInstancia().buscarVenta(codigo);
	}

	public Vector<Venta> getVentas(){
		return ventas;
	}
	
	/*public void crearVenta(String establecimiento, String peliculaConDetalle, LocalDate dia, LocalTime horario, int cantidadEntradas, int[][] asientosSeleccionados, String metodoPago, String nro, String cod, int mes, int anio) {
		// TODO Auto-generated method stub
		Boleteria b;
		Vector<Entrada> x=createVectorEntradas( establecimiento,  peliculaConDetalle,  dia,  horario,asientosSeleccionados, cantidadEntradas);
		if(metodoPago.equals("Efectivo")){
			b=new BoleteriaEfectivo(
					Controlador_Cine.getPrecioEntradas()*cantidadEntradas,//monto
					x,
					new Vendedor(LogInControlador.getInstancia().getUsuarioLogueado()));
		}
		else
			b=new BoleteriaTarjetaCredito(Controlador_Cine.getPrecioEntradas()*cantidadEntradas,
					x,
					new Vendedor(LogInControlador.getInstancia().getUsuarioLogueado()),
					cod,
					nro,
					mes+"/"+anio
			);
		
	}*/

	/*private Vector<Entrada> createVectorEntradas(String establecimiento, String peliculaConDetalle, LocalDate dia, LocalTime horario,int[][] asientosSeleccionados,int cant) {
		Vector<Entrada> res=new Vector<Entrada> ();
		String pelicula;
		int pos=peliculaConDetalle.indexOf("Subtitulado");
		if(pos!=-1)
			pelicula=peliculaConDetalle.substring(0, peliculaConDetalle.length()-12);
		else{
			pelicula=peliculaConDetalle.substring(0, peliculaConDetalle.length()-11);
		}
		Funcion f=AdmPersistenciaFuncion.getInstancia().buscarFuncionPorPelicula(pelicula, horario, dia, AdmPersistenciaCine.getInstancia().buscarCinePorNombre(establecimiento));
		for(int i=0;i<cant;i++){
			for(int j=0;j<cant;j++){
				if(asientosSeleccionados[i][j]==1){
					Entrada e=new Entrada(
						f,
						new AsientoVendido(new AsientoFisico(i,j),true),
						Controlador_Cine.getPrecioEntradas()
						);
					res.add(e);
					
				}
			}
		}
		return res;
		
	}*/

	public Vector<AsientoVendido> getAsientosVendidos(Venta_View vta) {
		Vector<AsientoVendido> vendidos=new Vector<AsientoVendido>();
		Funcion f=getFuncionPorView(vta);
		for(Entrada e:f.getEntradas())
			vendidos.add(e.getAsiento());
		
		return vendidos;
	}

	public void crearVenta(Venta_View v) {
		Venta venta=null;
		Rol logueado=LogInControlador.getInstancia().getRol();
		float precio=v.getTotal();
		Vector<AsientoFisico> pedidos=v.getAsientosSeleccionados();
		Vector<Entrada>entradas=new Vector<Entrada>();
		Funcion f=getFuncionPorView(v);//pone operador en null
		for(AsientoFisico af:pedidos){
			AsientoVendido av=new AsientoVendido(af);
			Entrada e=new Entrada(f,av,precio);
			entradas.add(e);
		}
		/*Si el rol iniciado es de un cliente, es una venta online*/
		if(logueado.sosElCliente()){
			venta=new Online(precio,entradas,(Cliente) logueado,v.getFormaPago().toTarjeta());
		}
		else{/*De lo contrario, es una venta por boleteria*/
			if(v.getFormaPago().sosTarjeta())
				venta=new BoleteriaTarjetaCredito(precio, entradas,(Vendedor)logueado,v.getFormaPago().toTarjeta());
			
			else
				venta=new BoleteriaEfectivo(precio, entradas, (Vendedor)logueado);
		}
		ventas.add(venta);
		f.agregarEntradas(entradas);
		
	}
	
	private Funcion getFuncionPorView(Venta_View vta){
		String sala,horario,dia,cine;
		sala=vta.getFuncion().getS().getNombre();
		horario=vta.getFuncion().getHorario();
		dia=vta.getFuncion().getDia();
		cine=vta.getEstablecimiento();
		
		return AdmPersistenciaFuncion.getInstancia().buscarFuncion(sala, LocalTime.parse(horario), LocalDate.parse(dia), cine);
		
	}
}
