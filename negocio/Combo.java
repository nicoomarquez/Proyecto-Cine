package negocio;

import java.util.Vector;

import negocio.AgenteComercial;
import negocio.Online;
import persistencia.AdmPersistenciaPromocion;

public class Combo extends Promocion {

	private Vector<Promocion> promociones;
	
	
	public Combo(String detalle, boolean estado, AgenteComercial agente, float porcentaje,
			Vector<Promocion> promociones, String entidadBancaria, String tipoTarjeta) {
		super(detalle, estado, agente, porcentaje, entidadBancaria, tipoTarjeta);
		
	}
	
	//Constructor para recuperar
	public Combo(String detalle, boolean estado, AgenteComercial agente, float porcentaje, String entidadBancaria, String tipoTarjeta) {
		super(detalle, estado, agente, porcentaje, entidadBancaria, tipoTarjeta);
		this.promociones = getPromociones();
	}
	
	public void agregarPromocion(Promocion p){
		promociones.add(p);
		AdmPersistenciaPromocion.getInstancia().insert(p);
	}
	
	public void quitarPromocion(Promocion p){
		promociones.remove(p);
		AdmPersistenciaPromocion.getInstancia().update(p);
	}
	
	public Vector<Promocion> getPromociones(){
		if(promociones == null)
			promociones = AdmPersistenciaPromocion.getInstancia().selectAll();
		return promociones;
	}

	@Override
	public float aplicarPromocion(Online v) {
		float descuentoTotal = 0;
		for(int i = 0; i < promociones.size(); i++) {
			//voy acumulando los descuentos
			descuentoTotal += promociones.elementAt(i).aplicarPromocion(v);
		}
		return descuentoTotal; //la cantidad en pesos que se va a descontar del valor total calculado
	}
}
