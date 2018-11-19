package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import conexionBD.PoolConnection;
import view.*;
import negocio.*;

public class AdmPersistenciaCine extends AdministradorPersistencia {

	private static AdmPersistenciaCine instancia;

	public AdmPersistenciaCine() {

	}

	public static AdmPersistenciaCine getInstancia() {
		if (instancia == null)
			instancia = new AdmPersistenciaCine();
		return instancia;
	}

	public void insert(Object o) {
		try {
			Cine c = (Cine) o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert "+PoolConnection.getNameDB()+".Cine values (?,?,?,?,?,?,?)");
			s.setString(1, c.getCuit());
			s.setString(2, c.getNombre());
			s.setString(3, c.getDomicilio());
			s.setInt(4, c.getCantSalas());
			s.setInt(5, c.getCapTotal());
			s.setString(6, c.getAdmin().getUsuario().getDni());
			s.setBoolean(7, c.isEstado());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("Error en insertCine "+e.getMessage());
		}
	}

	public void update(Object o) {

	}

	@Override
	public Vector<Object> select(Object o) {

		return null;
	}

	public void delete(Object d) {
		try {
			Cine c = (Cine) d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update "+PoolConnection.getNameDB()+".Cine "
					+ "set estado = ? where cuit = ?");
			s.setString(2, c.getCuit());
			s.setBoolean(1, false);
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("Error en selectCine "+e.getMessage());
		}
	}

	public Vector<Cine_View> select1() {
		try {
			Vector<Cine_View> cines = new Vector<Cine_View>();
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where estado = ?");
			s.setBoolean(1, true);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Cine_View c = new Cine_View(result.getString("nombre"), result.getString("cuit"), result.getString("domicilio"),
						result.getInt("cantidadSalas"), result.getInt("cantidadTotal"), result.getBoolean("estado"));
				cines.add(c);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return cines;
		} catch (Exception e) {
			System.out.println("Error en select1Cine "+e.getMessage());
		}
		return null;
	}
	
	public void update1(String cuit,String cuitNuevo, String nombre, String domicilio,
			int cantSalas, int capTotal) {
		try{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update "+PoolConnection.getNameDB()+".Cine " +
					"set cuit = ?," +
					"nombre = ?,"+
					"domicilio = ?," +
					"cantidadSalas =?," +
					"capacidadTotal =?"+
					" where cuit = ?");
			s.setString(1, cuitNuevo);
			s.setString(2, nombre);
			s.setString(3, domicilio);
			s.setInt(4, cantSalas);
			s.setInt(5, capTotal);
			s.setString(6, cuit);
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e) {
			System.out.println("Error en update1 "+e.getMessage());
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public Cine buscarCine(String cuit) {
		try {
			Cine c = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where cuit = ? and estado = ?");
			s.setString(1, cuit);
			s.setBoolean(2, true);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				String cuitbd = result.getString(2);
				String nombre = result.getString(3);
				String domicilio = result.getString(4);
				int cantSalas = result.getInt(5);
				int capTotal = result.getInt(6);
				String dniAdm = result.getString(7);
				boolean estado = result.getBoolean(8);
				Usuario u = AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(dniAdm);
				Administrador ad = (Administrador)u.getAdministrador();
				c = new Cine(cuitbd, nombre, domicilio,cantSalas, capTotal,  ad, estado);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return c;
		} catch (Exception e) {
			System.out.println("Error en buscarCine "+e.getMessage());
		}
		return null;
	}

	public Cine buscarCinePorNombre(String nombre) {
		try {
			Cine c = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where nombre = ? and estado = ?");
			s.setString(1, nombre);
			s.setBoolean(2, true);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				String cuit = result.getString(2);
				String nombrebd = result.getString(3);
				String domicilio = result.getString(4);
				int cantSalas = result.getInt(5);
				int capTotal = result.getInt(6);
				String dniAdm = result.getString(7);
				boolean estado = result.getBoolean(8);
				Usuario u = AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(dniAdm);
				Administrador ad = (Administrador) u.getAdministrador();
				c = new Cine(cuit, nombrebd, domicilio,cantSalas, capTotal,  ad, estado);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return c;
		} catch (Exception e) {
			System.out.println("Error en buscarCinePorNombre "+e.getMessage());
		}
		return null;
	}

	public int getId(String cuit) {
		int res = 0;
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where cuit = ?");
			s.setString(1, cuit);
			ResultSet result = s.executeQuery();
			while (result.next())
				res = result.getInt(1);
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("Error en idCine "+e.getMessage());
		}
		return res;
	}

	public String getCuit(int idCine) {
		String res = null;
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where idCine = ?");
			s.setInt(1, idCine);
			ResultSet result = s.executeQuery();
			while (result.next())
				res = result.getString(2);
			PoolConnection.getPoolConnection().realeaseConnection(con);
		} catch (Exception e) {
			System.out.println("Error en getCuitCine "+e.getMessage());
		}
		return res;
	}
	
	public Vector<Pelicula_View> select2() {
		try {
			Vector<Pelicula_View> peliculas = new Vector<Pelicula_View>();
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from"+PoolConnection.getNameDB()+".Pelicula where estado = ?");
			s.setBoolean(1, true);
			ResultSet result = s.executeQuery();
			while (result.next()) {
				Pelicula_View p = /*new Pelicula_View(result.getString(1), result.getString(2),
						result.getString(3), result.getInt(4), result.getString(5),
						result.getBoolean(6), result.getString(7), result.getString(8))*/null;
				peliculas.add(p);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return peliculas;
		} catch (Exception e) {
			System.out.println("Error en select2Cine "+e.getMessage());
		}
		return null;
	}

	
	
	public Vector<Cine> getCines() {
		// TODO Auto-generated method stub
		Vector<Cine> cines=null;
		try{
			cines=new Vector<Cine>();
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from "+PoolConnection.getNameDB()+".Cine where estado = ?");
			s.setBoolean(1, true);
			
			ResultSet rs=s.executeQuery();
			/*String cuit, String nombre, String domicilio, int cantSalas, int capTotal, 
			Administrador admin*/
			while(rs.next()){
				String idAdmin=rs.getString("idAdministrador");
				Rol admin=AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(idAdmin).getAdministrador();
				Cine c=new Cine(
					rs.getString("cuit"),
					rs.getString("nombre"),
					rs.getString("domicilio"),
					rs.getInt("cantidadSalas"),
					rs.getInt("cantidadTotal" ),
					(Administrador)admin,
					true
				);
				
				/*
				 *Codigo para recuperar las salas de este cine
				 */
				
				/*
				 * Codigo para recuperar las funciones de este cine
				 */
							
				cines.add(c);
			}
		}catch(Exception e){
			System.out.println("Error en getCines "+e.getMessage());
		}
		return cines;
	}

	
}
