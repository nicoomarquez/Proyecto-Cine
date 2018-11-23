package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.AsientoVendido;
import negocio.Entrada;
import negocio.Funcion;

public class AdmPersistenciaEntrada extends AdministradorPersistencia{
	private static AdmPersistenciaEntrada instancia=null;
	private Connection c;
	
	public static AdmPersistenciaEntrada getInstancia() {
		// TODO Auto-generated method stub
		if(instancia==null)
			instancia= new AdmPersistenciaEntrada();
		return instancia;
	}
	
	@Override
	public void insert(Object o) {
		
		
	}
	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Object d) {
		// TODO Auto-generated method stub
	}
	public void delete(Entrada e,Long codigoVta) {
		try{
			c=PoolConnection.getPoolConnection().getConnection();
		
			ResultSet rs=c.createStatement().executeQuery("SELECT * FROM "+PoolConnection.getNameDB()+". EntradaVenta where idVenta="+codigoVta);
			while(rs.next()){
				int idEntrada=rs.getInt("idEntrada");
				c.createStatement().execute("UPDATE FROM "+PoolConnection.getNameDB()+".Entrada set estadoRetiro=0 where idEntrada="+idEntrada);
			}
		}
		catch(Exception ex){
			System.out.println("Error delete entrada");
		}
	}
	
	@Override
	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector<Entrada> getEntradas(Funcion f,int idFuncion){
		Vector<Entrada> res=new Vector<Entrada>();
		c=PoolConnection.getPoolConnection().getConnection();
		try{
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT * FROM "+PoolConnection.getNameDB()+".Entrada where idFuncion="+idFuncion);
			while(rs.next()){
				AsientoVendido av=AdmPersistenciaAsientos.getInstancia().getAsientoVendido(rs.getInt("idAsientoV"));
				Entrada e=new Entrada(f,av,rs.getFloat("precio"));
				res.add(e);
			}
		}catch(Exception e){
			System.out.println("Error en getEntradas(idfuncion). Detalle:\n"+e.getMessage()+"\nStackTrace:\n"+e.getStackTrace());
		}finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return res;
	}
	
	/**Obtengo todas las entradas vendidas asociadas al idVenta*/
	public Vector<Entrada> getEntradas(int idVenta) {
		// TODO Auto-generated method stub
		Vector<Entrada>entradas=new Vector<Entrada>();
		c=PoolConnection.getPoolConnection().getConnection();
		try{
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT * FROM EntradaVenta where idVenta="+idVenta);
			while(rs.next()){
				Entrada e=buscarEntrada(rs.getInt("idEntrada"));
				entradas.add(e);
			}
		}catch(Exception e){
			System.out.println("Error en getEntradas(idVenta). Detalle:\n"+e.getMessage()+"\nStackTrace:\n"+e.getStackTrace());
		}finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		
		return entradas;
	}
	
	public Entrada buscarEntrada(int idEntrada){
		Entrada res=null;
		c=PoolConnection.getPoolConnection().getConnection();
		try{
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("SELECT * FROM "+PoolConnection.getNameDB()+".Entrada where idEntrada="+idEntrada);
			while(rs.next()){
				Funcion f=AdmPersistenciaFuncion.getInstancia().buscarFuncion(rs.getInt("idFuncion"));
				AsientoVendido av=AdmPersistenciaAsientos.getInstancia().getAsientoVendido(rs.getInt("idAsientoV"));
				res=new Entrada(f,av,rs.getFloat("precio"));
			}
		}catch(Exception e){
			System.out.println("Error en getEntradas(idfuncion). Detalle:\n"+e.getMessage()+"\nStackTrace:\n"+e.getStackTrace());
		}finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return res;
	}

}
