package negocio;

import java.util.Vector;

import persistencia.AdmPersistenciaVenta;

public class BoleteriaEfectivo extends Boleteria {

	public BoleteriaEfectivo(float monto, Vector<Entrada> entradas, Vendedor vendedor) {
		super(monto, entradas, vendedor);
		AdmPersistenciaVenta.getInstancia().insert(this);
	}

	@Override
	public void venderEntradas() {
		// TODO Auto-generated method stub

	}

	@Override
	public void calcularCosto() {
		// TODO Auto-generated method stub

	}

}
