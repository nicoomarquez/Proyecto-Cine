package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.AsientoFisico;
import negocio.AsientoVendido;

public class AdmPersistenciaAsientos extends AdministradorPersistencia {
	private static AdmPersistenciaAsientos instancia=null;
	
	public static AdmPersistenciaAsientos getInstancia(){
		if(instancia==null)
			instancia=new AdmPersistenciaAsientos();
		return instancia;
	}
	
	private AdmPersistenciaAsientos(){
		
	}
	
	@Override
	public void insert(Object o) {
		// TODO Auto-generated method stub
		
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

	public int getCantAsientos(String nombreSala, String cine) {
		// TODO Auto-generated method stub
		int res=-1;
		Connection c=PoolConnection.getPoolConnection().getConnection();
		try{
			PreparedStatement ps=c.prepareStatement("Select count(*) from "+PoolConnection.getNameDB()+
					".AsientoFisico where nombreSala=? and nombreCineSala=?");
			ps.setString(1, nombreSala);
			ps.setString(2, cine);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				res=rs.getInt(1);
		}catch(Exception e){
			System.out.println("Error en getCantAsientos: "+e.getStackTrace());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return res;
	}

	public AsientoVendido getAsientoVendido(int idAsientoV) {
		AsientoVendido res=null;
		Connection c=PoolConnection.getPoolConnection().getConnection();
		try{
			PreparedStatement ps=c.prepareStatement("Select * from "+PoolConnection.getNameDB()+".AsientoVendido where idASientoV=?");
			ps.setInt(1, idAsientoV);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				res=new AsientoVendido(new AsientoFisico(rs.getInt("filaFisico"),rs.getInt("columnaFisico")));
			}
		}catch(Exception e){
			System.out.println("Error en getAsientoVendido: "+e.getStackTrace());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return res;
	}
	
	

}
