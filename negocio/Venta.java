package negocio;

import java.time.LocalDate;
import java.util.Vector;


public abstract class Venta { 
	
	/*Cuando se compra una entrada, es siempre del mismo tipo*/
	
	protected float monto;
	protected LocalDate fecha;
	protected Vector<Entrada> entradas;
	protected static long codigo=1;
	public Venta(float monto, Vector<Entrada> entradas) {
		super();
		this.monto = monto;
		fecha=LocalDate.now();
		this.entradas=entradas;
		codigo=getProximoNumero();
		
	}

	public Venta() {
		// TODO Auto-generated constructor stub
	}

	private long getProximoNumero() {
		// TODO Auto-generated method stub
		return ++codigo;
	}
	
	public long getCodigo(){
		return codigo;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	
	public abstract void venderEntradas();
	
	public abstract void calcularCosto();

	public Vector<Entrada> getEntradas() {
		// TODO Auto-generated method stub
		return entradas;
	}
}