package negocio;


public interface ObservableVenta {//clase que avisa a todos sus observers
//	static ArrayList<ObserverTDA> observadores=new ArrayList<ObserverTDA>();
	
	public void add(ObserverTDA vr);
	
	public void remove(ObserverTDA vr);

	public void notifyAll(MailPendiente mail);
	 
}
