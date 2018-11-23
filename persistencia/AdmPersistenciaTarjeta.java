package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.TarjetaCredito;

public class AdmPersistenciaTarjeta extends AdministradorPersistencia {
	private Connection c;
	private static AdmPersistenciaTarjeta instancia=null;
	
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

	public static AdmPersistenciaTarjeta getInstancia() {
		// TODO Auto-generated method stub
		return instancia==null? instancia=new AdmPersistenciaTarjeta(): instancia;
	}

	public TarjetaCredito buscarTarjeta(String nro) {
		// TODO Auto-generated method stub
		c=PoolConnection.getPoolConnection().getConnection();
		TarjetaCredito t=null;
		try{
			PreparedStatement ps=c.prepareStatement("SELECT * FROM "+PoolConnection.getNameDB()+".Tarjeta where numero=?");
			ps.setString(1, nro);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String cod=rs.getString("codigo");
				String titular=rs.getString("titular");
				String entidad=rs.getString("entidad");
				String tipo=rs.getString("tipo");
				String vencimiento=rs.getString("vencimiento");
				
				t=new TarjetaCredito(cod,nro,titular,vencimiento,entidad,tipo);
			}
		}catch(Exception e){
			System.out.println("Error en buscarTarjeta\n Detalle del error:\n"+e.getMessage()+"\nStackTrace:\n"+e.getStackTrace());
		}
		return t;
	}

}
