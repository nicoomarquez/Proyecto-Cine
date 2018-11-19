package negocio;

public class AsientoVendido {
	
	private boolean estado;
	private AsientoFisico asientoF;
	
	public AsientoVendido(AsientoFisico f,boolean estado) {
		super();
		asientoF=f;
		this.estado = true;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public AsientoFisico getAsientoF() {
		return asientoF;
	}

	public void setAsientoF(AsientoFisico asientoF) {
		this.asientoF = asientoF;
	}
	
	
}
