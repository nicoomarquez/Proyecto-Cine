package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import conexionBD.PoolConnection;
import negocio.Sala;
import negocio.Administrador;
import negocio.Usuario;
import view.Sala_View;

public class AdmPersistenciaSala extends AdministradorPersistencia {

	private static AdmPersistenciaSala instancia;

	public AdmPersistenciaSala() {

	}

	public static AdmPersistenciaSala getInstancia() {
		if (instancia == null)
			instancia = new AdmPersistenciaSala();
		return instancia;
	}

	public void insert1(Object o, String cuit) {
		try {
			Sala s = (Sala) o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("insert into "+PoolConnection.getNameDB()+".Sala values (?,?,?,?,?)");
			int idCine = AdmPersistenciaCine.getInstancia().getId(cuit);
			ps.setInt(1, idCine);
			ps.setString(2, s.getNombre());
			ps.setString(3, s.getAdmin().getUsuario().getDni());
			ps.setBoolean(4,s.isEstado());
			ps.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}

		catch (Exception e) {
			System.out.println("Error insert1Sala "+e.getMessage());
		}
	}

	@Override
	public void insert(Object o) {

	}

	public void update(Object o) {

	}
	
	public void delete(Object d) {
		
	}
	
	public void delete1(Object d, String cuit) {
		try {
			Sala s = (Sala) d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("update api.dbo.Sala "
					+ "set estado = ? where idCine = ? and nombre = ?");
			int idCine = AdmPersistenciaCine.getInstancia().getId(cuit);
			ps.setInt(2, idCine);
			ps.setString(3, s.getNombre());
			ps.setBoolean(1, false);
			ps.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("Error delete1 sala "+e.getMessage());
		}
	}

	public Vector<Object> select(Object o) {

		return null;
	}
	
	public void update1(String nombreNuevo, int capacidad, String nombre, String cuitCine) {
		try{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update api.dbo.Sala " +
					"set nombre = ?, " +
					"capacidad = ? " +
					"where (idCine = ? and nombre = ?)");
			s.setString(1, nombreNuevo);
			s.setInt(2, capacidad);
			int idCine = AdmPersistenciaCine.getInstancia().getId(cuitCine);
			s.setInt(3, idCine);
			s.setString(4, nombre);
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e) {
			System.out.println("Error update1 sala "+e.getMessage());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	public Sala buscarSala(String nombre, String nombreCine) {
		try {
			Sala s = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Sala "
					+ "where nombreCine = ? and nombre = ? and estado = ?");
			
			ps.setString(1, nombreCine);
			ps.setString(2, nombre);
			ps.setBoolean(3, true);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String nombrebd = result.getString(2);
				int capacidad = result.getInt(3);
				String dniAdm = result.getString(4);
				Usuario u = AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(dniAdm);
				Administrador ad = (Administrador)u.getAdministrador();
				s = new Sala(capacidad, nombrebd, ad);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return s;
		} catch (Exception e) {
			System.out.println("Error buscarSala "+e.getMessage());
		}
		return null;
	}
	
	public Vector<Sala_View> getSalas(String cine) {
		Vector<Sala_View> res=new Vector<Sala_View>();
		Connection con = PoolConnection.getPoolConnection().getConnection();
		try{
			//Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Sala where nombreCine=? and estado=?");
			s.setString(1, cine);
			s.setBoolean(2, true);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				res.add(new Sala_View(result.getString(2),10));
			}
			
		} catch (Exception e) {
			System.out.println("Error en select1Cine "+e.getMessage());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		
		return res;
	}
}
