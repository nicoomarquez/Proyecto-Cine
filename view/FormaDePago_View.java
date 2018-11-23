package view;

import negocio.TarjetaCredito;

public class FormaDePago_View{
	private String tipoDePago;
	private String titular, nro,codSeg, banco,tipoTarjeta;
	private int anio,mes;
	
	public FormaDePago_View(String detalle,String titular, String nro, String codSeg, String banco, int anio, int mes,String tipoTarjeta) {
		super();
		tipoDePago=detalle;
		this.titular = titular;
		this.nro = nro;
		this.codSeg = codSeg;
		this.banco=banco;
		this.anio = anio;
		this.mes = mes;
		this.tipoTarjeta=tipoTarjeta;
	}
	
	public FormaDePago_View(String detalle){
		tipoDePago=detalle;
	}
	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getCodSeg() {
		return codSeg;
	}

	public void setCodSeg(String codSeg) {
		this.codSeg = codSeg;
	}

	public int getAnioo() {
		return anio;
	}

	public void setAnioo(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getUltimosDigitos() {
		// TODO Auto-generated method stub
		return nro.substring(nro.length()-4);
	}

	public String getResumenDePago() {
		// TODO Auto-generated method stub
		if(tipoDePago.equals("Tarjeta de credito"))
			return getTipoDePago() +" terminada en "+getUltimosDigitos();
		
		return tipoDePago;
	}

	public TarjetaCredito toTarjeta() {
		// TODO Auto-generated method stub
		TarjetaCredito t=new TarjetaCredito(codSeg,nro,titular,mes,anio,banco,tipoTarjeta);
		return t;
	}

	public boolean sosTarjeta() {
		// TODO Auto-generated method stub
		return anio!=0;
	}
	
	public boolean sosEfectivo(){
		return !sosTarjeta();
	}
}
