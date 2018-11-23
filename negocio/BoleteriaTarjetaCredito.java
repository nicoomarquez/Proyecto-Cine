package negocio;

import java.util.Vector;

import persistencia.AdmPersistenciaVenta;

public class BoleteriaTarjetaCredito extends Boleteria {
private TarjetaCredito tarjeta;

	public BoleteriaTarjetaCredito(float monto, Vector<Entrada> entradas, Vendedor vendedor, TarjetaCredito t) {
	super(monto, entradas, vendedor);
	this.tarjeta=t;
	//AdmPersistenciaVenta.getInstancia().insert(this);
}

	@Override
	public void venderEntradas() {
		// TODO Auto-generated method stub
		//AdmPersistenciaVenta.getInstancia().insert(this);
	}

	@Override
	public void calcularCosto() {
		// TODO Auto-generated method stub
		
	}

	public TarjetaCredito getTarjeta() {
		// TODO Auto-generated method stub
		return tarjeta;
	}

}
