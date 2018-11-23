package negocio;

import negocio.AgenteComercial;
import negocio.Online;
import persistencia.AdmPersistenciaPromocion;

/**
 * STRATEGY-1
 * @author alu802
 *
 */

public class DosPorUno extends Promocion {
	private String entidadBancaria;
	private String tipoTarjeta;

	public DosPorUno(String detalle, boolean estado, AgenteComercial agente, float porcentaje, String entidadBancaria, String tipoTarjeta) {
		super(detalle, estado, agente, porcentaje, entidadBancaria, tipoTarjeta);
		
		
		
		AdmPersistenciaPromocion.getInstancia().insert(this);
		
	}

	/**
	 * Algoritmo para aplicar un 2x1 a una venta
	 * @param ventaOnline
	 * @return float
	 */
	
	@Override
	public float aplicarPromocion(Online v) {
		//obtengo la tarjeta con la que se hizo la venta
		TarjetaCredito t = v.getTarjeta();
		if(t.getEntidadBancaria().equals(entidadBancaria) && t.getTipoTarjeta().equals(tipoTarjeta)) {
			//aplico el descuento
			return v.calcularCosto() * porcentaje; //sea sume 0<porcentaje<1
		}
		//no se aplica el descuento
		return 0;
	}
	
}
