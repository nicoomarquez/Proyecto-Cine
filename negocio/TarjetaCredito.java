package negocio;

public class TarjetaCredito  {
	private String tipoTarjeta,codigoSeguridad,numero,titular,vencimiento,tipoPago,entidadBancaria;

	public TarjetaCredito(String codigoSeguridad, String numero, String titular, int mes, int anio,
			String entidadBancaria, String tipoTarjeta) {
		super();
		this.codigoSeguridad = codigoSeguridad;
		this.numero = numero;
		this.titular = titular;
		this.vencimiento = getFormat(mes,anio);
		this.tipoPago="Tarjeta";
		this.entidadBancaria=entidadBancaria;
		this.tipoTarjeta=tipoTarjeta;
	}
	
	public TarjetaCredito(String codigoSeguridad, String numero, String titular, String vencimiento,
			String entidadBancaria,String tipoTarjeta) {
		super();
		this.codigoSeguridad = codigoSeguridad;
		this.numero = numero;
		this.titular = titular;
		this.vencimiento = vencimiento;
		this.tipoPago="Tarjeta";
		this.entidadBancaria=entidadBancaria;
		this.tipoTarjeta=tipoTarjeta;
	}
	
	private String getFormat(int mes, int anio){
		String res="";
		if(mes<10)
			res="0";
		return res=res+mes+"/"+anio;
			
	}
	
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getEntidadBancaria() {
		return entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}
	
	
}
