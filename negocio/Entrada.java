package negocio;

public class Entrada {
	
	private Funcion funcion;
	private AsientoVendido asiento;
	private float precio;
	private boolean estadoRetiro;
	
	public Entrada(Funcion funcion, AsientoVendido asiento, float precio) {
		super();
		this.funcion = funcion;
		this.asiento = asiento;
		this.precio = precio;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public AsientoVendido getAsiento() {
		return asiento;
	}

	public void setAsiento(AsientoVendido asiento) {
		this.asiento = asiento;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
