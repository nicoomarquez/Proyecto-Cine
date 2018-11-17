package controlador;

import java.time.LocalDate;
import java.util.Vector;

import negocio.*;
import persistencia.AdmPersistenciaUsuario;


public class LogInControlador {
	private static LogInControlador instancia=null;
	private Usuario log=null;
	
	private Vector<Usuario>usuarios; 
	
	
	
	
	private LogInControlador(){
		usuarios=AdmPersistenciaUsuario.getInstancia().select();
	}
	
	public static LogInControlador getInstancia(){
		if(instancia==null){
			instancia= new LogInControlador();
		}
		return instancia;
	}
	
	/**Verifica que las credenciales recibidas correspondan a un
	 * empleado.*/
	public Usuario IniciarSesionEmpleado(String us, String pw){
		Usuario u=buscarUsuarioPorNombreUsuario(us);
		if(u!= null && u.getPassword().equals(pw)){
			setUsuarioLogueado(u);
			return u;
		}
		return null;
	}
	
	/**Crea una cuenta de un usuario que no exista en el sistema o que 
	 * si exista pero no con el rol de Cliente. Esta verificacion ya 
	 * fue realizada anteriormente*/
	public void crearCuenta(String mail, String nombreUsuario, String password, String nombre, String domicilio, String dni,
			String apellido, String fechaNac){
		Usuario u= this.buscarUsuarioPorDni(dni);
		if(u==null){
			u=new Usuario(mail,nombreUsuario,password, nombre, domicilio, dni, apellido, LocalDate.parse(fechaNac));
			u.asignarRol(new Cliente(u));
			usuarios.add(u);
		}
		
	}
	
	public Usuario buscarUsuarioPorDni(String dni){
		 
		for(Usuario u:usuarios)
			if(u.getDni().equals(dni))
				return u;

		Usuario res=AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(dni);
		 if(res!=null)
			 usuarios.add(res);
		 
		 return res;
	}
	
	public Usuario buscarUsuarioPorNombreUsuario(String us){
		for(Usuario u:usuarios)
			if(u.getNombreUsuario().equals(us))
				return u;

		Usuario res=AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorNombreUsuario(us);
		
		if(res!=null)
			usuarios.add(res);
		 
		 return res;
	}
	
	/*Estos dos metodos se deberian utilizar a la hora de hacer una operacion
	 * donde se necesite la referencia de el mismo, sea tanto empleado como cliente
	 */
	/**
	 * Este metodo devuelve el usuario logueado actualmente
	 * @return instancia de usuario logueado*/
	public Usuario getUsuarioLogueado() {
		// TODO Auto-generated method stub
		return log;
	}
	
	/**
	 * Este metodo almacena la instancia de usuario que se logueo
	 * */
	public void setUsuarioLogueado(Usuario e) {
		// TODO Auto-generated method stub
		this.log=e;
	}
	
	/**
	 * Realiza la validacion de las credenciales ingresadas y si 
	 * las mismas corresponden a un Cliente
	 * @param usuario
	 * @param contraseña
	 * @return validacion de las credenciales*/
	public boolean IniciarSesionCliente(String us, String pass) {
		Usuario u=this.buscarUsuarioPorNombreUsuario(us);
		if(u!=null && u.getPassword().equals(pass) && u.getCliente()!=null)
			return true; 
		
		return false;
	}

	public void logOut() {
		// TODO Auto-generated method stub
		this.log=null;
		
	}
}
