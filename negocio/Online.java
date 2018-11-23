package negocio;

public class Online extends Venta {
	
	private Cliente comprador;
	private TarjetaCredito tarjeta;
	private MailPendiente mail;
	private static Vector<ObserverTDA> ov;
	
	public Online(float monto, Vector<Entrada> entradas, Cliente comprador, TarjetaCredito tarjeta) {
		super(monto, entradas);
		this.comprador = comprador;
		this.tarjeta = tarjeta;
		this.mail = new MailPendiente(comprador.getUsuario().getMail(), null);
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



	public static Vector<ObserverTDA> getOv() {
		return ov;
	}



	public static void setOv(Vector<ObserverTDA> ov) {
		Online.ov = ov;
	}



	@Override
	public void venderEntradas() {
		// TODO Auto-generated method stub

	}

	@Override
	public float calcularCosto() {
		return monto;
		// TODO Auto-generated method stub

	}
	
	

}
