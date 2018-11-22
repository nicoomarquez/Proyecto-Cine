package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.*;

public class AdmPersistenciaPromocion extends AdministradorPersistencia {
	
	private static AdmPersistenciaPromocion instancia;
	
	private AdmPersistenciaPromocion()
	{
		
	}
	public static AdmPersistenciaPromocion getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPersistenciaPromocion();
		return instancia;
	}

	@Override
	public void insert(Object o) {
		try
		{
			DosPorUno a = (DosPorUno)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into "+PoolConnection.getNameDB()+".Promocion values (?,?,?,?)");
			s.setString(1, a.getAgenteComercial().getUsuario().getDni());
			s.setBoolean(2, a.isEstado());
			s.setString(3, a.getDetalle());
			s.setFloat(4, a.getPorcentaje());
			s.execute();
			
			System.out.println("Promoción agregada");
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Error: "+e.getMessage());
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
	
	/**Devuelve todas las promociones activas
	 * @param void
	 * @return Vector*/
	
	public Vector<Promocion> selectAll() {
		Vector<Promocion> promociones = new Vector<Promocion>();
		Connection con = PoolConnection.getPoolConnection().getConnection();
		try
		{
			PreparedStatement s = con.prepareStatement("select * from"+PoolConnection.getNameDB()+".Promocion where estado = ?");
			s.setBoolean(1, true);
			ResultSet rs = s.executeQuery();
			if(rs.next()){
				Promocion p=
						new Promocion(
							rs.getString("detalle"),
							true,
							rs.getString("idAgente"),
							rs.getFloat("porcentaje")
						);
				promociones.add(p);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		return promociones;
	}
	@Override
	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
