package negocio;

import java.util.Vector;

public abstract class Boleteria extends Venta{
	protected Vendedor vendedor;

	public Boleteria(float monto, Vector<Entrada> entradas, Vendedor vendedor) {
		super(monto, entradas);
		this.vendedor = vendedor;
	}
	
}
