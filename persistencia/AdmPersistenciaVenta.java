package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.*;

public class AdmPersistenciaVenta extends AdministradorPersistencia {
	private static AdmPersistenciaVenta instancia=null;
	private Connection c;
	
	public static AdmPersistenciaVenta getInstancia(){
		if(instancia==null)
			instancia=new AdmPersistenciaVenta();
		return instancia;
	}
	@Override
	public void insert(Object o) {
		// TODO Auto-generated method stub
		Connection c=PoolConnection.getPoolConnection().getConnection();
		try{
			
		}catch(Exception e){
			System.out.println("Error insertVenta "+ e.getMessage());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Venta buscarVenta(long codigo){
		Venta v=null;
		
		try{
			c=PoolConnection.getPoolConnection().getConnection();
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("Select *  from Online where codigoVenta="+codigo);
			if(rs.next()){
				/*Es una venta Online*/
			}
			else{
				rs=s.executeQuery("Select *  from BoleteriaEfectivo where codigoVenta="+codigo);
				if(rs.next()){
					/*Es una venta en Boleteria en efectivo*/
				}
				else{
					rs=s.executeQuery("Select *  from BoleteriaTarjetaCredito where codigoVenta="+codigo);
					if(rs.next()){
						/*Es una venta en Boleteria en tarjeta*/
					}
				}
			}
		}catch(Exception e){
			System.out.println("Falla en buscarVenta()"+e.getMessage());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return v;
	}
	
}
