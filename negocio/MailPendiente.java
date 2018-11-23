package negocio;

public class MailPendiente {
	private String mailCliente, resumenVta;

	public MailPendiente(String mailCliente, String resumenVta) {
		super();
		this.mailCliente = mailCliente;
		this.resumenVta = resumenVta;
	}

	public String getMailCliente() {
		return mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getResumenVta() {
		return resumenVta;
	}

	public void setResumenVta(String resumenVta) {
		this.resumenVta = resumenVta;
	}
	
	
}
