package negocio;

import java.util.Vector;

import view.Sala_View;
import persistencia.AdmPersistenciaSala;

public class Sala {

	private String nombre;
	private Administrador admin;
	private boolean estado;
	private Vector<AsientoFisico> asientosFi;
	private Vector<Funcion> funciones;

	// constructor para dar de alta y persistir bd
	public Sala(String nombre, int capacidad, Administrador admin) {
		super();
		this.nombre = nombre;
		this.admin = admin;
		estado=true;
		asientosFi = new Vector<AsientoFisico>(capacidad);
		funciones = new Vector<Funcion>();
		
	}

	// constructor para recuperar desde bd
	public Sala(int capacidad, String nombre, Administrador admin) {
		this.nombre = nombre;
		this.admin=admin;
		asientosFi = new Vector<AsientoFisico>(capacidad);
		funciones = new Vector<Funcion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Vector<AsientoFisico> getAsientosFi() {
		return asientosFi;
	}

	public void setAsientosFi(Vector<AsientoFisico> asientosFi) {
		this.asientosFi = asientosFi;
	}

	public Vector<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(Vector<Funcion> funciones) {
		this.funciones = funciones;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////

	/*public Vector<AsientoVendido> getEsquema() {
		Vector<AsientoVendido> res = new Vector<AsientoVendido>();
		int cantAsientos = asientosFi.size();
		for (int i = 0; i < cantAsientos; i++)
			res.add(new AsientoVendido(asientosFi.elementAt(i).isEstado()));
		if (cantAsientos == 0)
			return null;
		return res;
	}*/

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean sosLaSala(String name) {
		return nombre.equalsIgnoreCase(name);
	}
	
	public Sala_View getView() {
		return new Sala_View(nombre, asientosFi.size());
	}
	
	public void borrarSalaBD(String cuitCine) {
		AdmPersistenciaSala.getInstancia().delete1(this, cuitCine);
	}
	
	public void actualizarSalaBD(String nombreNuevo, int capacidad, String nombre, String cuitCine) {
		AdmPersistenciaSala.getInstancia().update1(nombreNuevo, capacidad, nombre, cuitCine);
	}
	
	public static void insertarSala(String cuit, Sala s){
		AdmPersistenciaSala.getInstancia().insert1(s, cuit);

	}

	public int getCapacidad() {
		// TODO Auto-generated method stub
		return asientosFi.size();
	}
}