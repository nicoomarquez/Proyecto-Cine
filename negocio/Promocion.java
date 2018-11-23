package negocio;

import negocio.AgenteComercial;
import negocio.Online;
import negocio.Venta;

/**
 * STRATEGY
 * @author alu802
 *
 */

public abstract class Promocion {
	
	protected String detalle;
	protected boolean estado;
	protected AgenteComercial agente;
	protected float porcentaje;
	protected String entidadBancaria;
	protected String tipoTarjeta;
	
	public Promocion(String detalle, boolean estado, AgenteComercial agente, float porcentaje, String entidadBancaria, String tipoTarjeta) {
		super();
		this.detalle = detalle;
		this.estado = estado;
		this.agente = agente;
		this.porcentaje = porcentaje;
		this.entidadBancaria = entidadBancaria;
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String nombre) {
		this.detalle = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	public AgenteComercial getAgenteComercial() {
		return agente;
	}

	public String getEntidadBancaria() {
		return entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public abstract float aplicarPromocion(Online v);
}
