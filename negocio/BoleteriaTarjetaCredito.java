package negocio;

import java.util.Vector;

import persistencia.AdmPersistenciaVenta;

public class BoleteriaTarjetaCredito extends Boleteria {
private String codigo,nro,vencimiento;

	public BoleteriaTarjetaCredito(float monto, Vector<Entrada> entradas, Vendedor vendedor, String codigo,
		String nro, String vencimiento) {
	super(monto, entradas, vendedor);
	this.codigo = codigo;
	this.nro = nro;
	this.vencimiento = vencimiento;
	AdmPersistenciaVenta.getInstancia().insert(this);
}

	@Override
	public void venderEntradas() {
		// TODO Auto-generated method stub
		AdmPersistenciaVenta.getInstancia().insert(this);
	}

	@Override
	public float calcularCosto() {
		return monto;
		// TODO Auto-generated method stub
		
	}

}
