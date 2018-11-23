package negocio;

import negocio.AgenteComercial;
import negocio.Online;
import persistencia.AdmPersistenciaPromocion;

/**
 * STRATEGY-2
 * @author alu802
 *
 */
public class Descuento extends Promocion {

	public Descuento(String detalle, boolean estado, AgenteComercial agente, float porcentaje, String entidadBancaria, String tipoTarjeta) {
		super(detalle, estado, agente, porcentaje, entidadBancaria, tipoTarjeta);
		
		AdmPersistenciaPromocion.getInstancia().insert(this);
	
	}

	/**
	 * Algoritmo para aplicar el descuento a la venta
	 * @param ventaOnline
	 * @return float con el precioFinal
	 */
	
	@Override
	public float aplicarPromocion(Online v) {
		//obtengo la tarjeta con la que se hizo la venta
		TarjetaCredito t = v.getTarjeta();
		if(t.getEntidadBancaria().equals(entidadBancaria) && t.getTipoTarjeta().equals(tipoTarjeta)) {
			//me devuelve el descuento que se aplica
			return v.calcularCosto() * porcentaje; //sea asume 0<porcentaje<1, 
		}
		//no se aplica el descuento
		return 0;
	}
}
