package negocio;

import java.util.Vector;

public class AsientoFisico {

	private int fila;
	private int columna;
	private boolean estado;

	public AsientoFisico(int fila, int columna) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.estado = true;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean sosElAsiento(int i, int j) {
		// TODO Auto-generated method stub
		return (fila==i)&&(columna==j);
	}

	public static void insertarAsiento(String cuit, String nombre, Vector<AsientoFisico> asientosFi) {
		// TODO Auto-generated method stub
		
	}

}
