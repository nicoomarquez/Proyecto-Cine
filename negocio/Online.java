package negocio;

import java.util.Vector;

import persistencia.AdmPersistenciaVenta;

public class Online extends Venta implements ObservableVenta {
	
	private Cliente comprador;
	private TarjetaCredito tarjeta;
	private MailPendiente mail;
	private static Vector<ObserverTDA> ov;
	
	public Online(float monto, Vector<Entrada> entradas, Cliente comprador, TarjetaCredito tarjeta) {
		super(monto, entradas);
		this.comprador = comprador;
		this.tarjeta = tarjeta;
		this.mail = new MailPendiente(comprador.getUsuario().getMail(),null);
		
	}
	
	public Online(float monto, Vector<Entrada> entradas, TarjetaCredito tarjeta, Cliente comprador){
		super(monto, entradas);
		this.comprador = comprador;
		this.tarjeta = tarjeta;
		AdmPersistenciaVenta.getInstancia().insertarOnline(this);
	}

	public Cliente getComprador() {
		return comprador;
	}



	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}



	public TarjetaCredito getTarjeta() {
		return tarjeta;
	}



	public void setTarjeta(TarjetaCredito tarjeta) {
		this.tarjeta = tarjeta;
	}



	public MailPendiente getMail() {
		return mail;
	}



	public void setMail(MailPendiente mail) {
		this.mail = mail;
	}



	@Override
	public void venderEntradas() {
		// TODO Auto-generated method stub
		
		notifyAll(mail);
	}

	@Override
	public void calcularCosto() {
		// TODO Autxo-generated method stub
																								
	}

	@Override
	public void add(ObserverTDA vr) {
		// TODO Auto-generated method stub
		ov.add(vr);
	}



	@Override
	public void remove(ObserverTDA vr) {
		// TODO Auto-generated method stub
		ov.remove(vr);
	}



	@Override
	public void notifyAll(MailPendiente mail) {
		// TODO Auto-generated method stub
		for(ObserverTDA o:ov){
			o.enviarMail(mail);
		}
	}

}
