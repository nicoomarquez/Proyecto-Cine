package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				Cliente c=(Cliente) AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(rs.getString("idCliente")).getCliente();
				TarjetaCredito t=AdmPersistenciaTarjeta.getInstancia().buscarTarjeta(rs.getString("idTarjeta"));
				float monto=rs.getFloat("monto");
				Vector<Entrada>entradas=AdmPersistenciaEntrada.getInstancia().getEntradas(rs.getInt("codigoVenta"));
				v=new Online(monto,entradas,c,t);
			}
			else{
				rs=s.executeQuery("Select *  from Boleteria where codigoVenta="+codigo);
				if(rs.next()){
					/*Es una venta en Boleteria en efectivo*/
					Vendedor vendedor=(Vendedor)AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(rs.getString("idVendedor")).getVendedor();
					float monto=rs.getFloat("monto");
					Vector<Entrada>entradas=AdmPersistenciaEntrada.getInstancia().getEntradas(rs.getInt("codigoVenta"));
					v=new BoleteriaEfectivo(monto,entradas,vendedor);
				}
				else{
					rs=s.executeQuery("Select *  from BoleteriaTarjetaCredito where codigoVenta="+codigo);
					if(rs.next()){
						/*Es una venta en Boleteria en tarjeta*/
						TarjetaCredito t=AdmPersistenciaTarjeta.getInstancia().buscarTarjeta(rs.getString("idTarjeta"));
						Vendedor vendedor=(Vendedor)AdmPersistenciaUsuario.getInstancia().buscarUsuarioPorDni(rs.getString("idVendedor")).getVendedor();						
						float monto=rs.getFloat("monto");
						Vector<Entrada>entradas=AdmPersistenciaEntrada.getInstancia().getEntradas(rs.getInt("codigoVenta"));
						v=new BoleteriaTarjetaCredito(monto,entradas,vendedor,t);
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
	
	public void insertarOnline(Online online) {
		// TODO Auto-generated method stub
		c=PoolConnection.getPoolConnection().getConnection();
		try{
			String sql="INSERT INTO "+PoolConnection.getNameDB()+"values (?,?,?)";
			PreparedStatement ps=c.prepareStatement(sql);
			
			ps.setInt(1, online.getCodigo());
			ps.setString(2,online.getComprador().getUsuario().getDni());
			ps.setString(3,online.getTarjeta().getNumero());
			
			
			ps.execute();
			
		}catch(Exception e){
			System.out.println("Falla en insertarOnline()\n"+e.getMessage()+"\n"+e.getStackTrace());
		}
		finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
	}
	public int getCodigoMaximo() {
		// TODO Auto-generated method stub
		Integer nro=null;
		c=PoolConnection.getPoolConnection().getConnection();
		try{
			Statement s=c.createStatement();
			String sql="Select max(codigoVenta) as codigo from "+PoolConnection.getNameDB()+".Venta";
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()){
				nro=rs.getInt("codigo");
			}
		}catch(Exception e){
			System.out.println("Falla en getCodigoMaximo()\n"+e.getMessage()+"\n"+e.getStackTrace());
		}finally{
			PoolConnection.getPoolConnection().realeaseConnection(c);
		}
		return nro==null?0:nro;
	}
	
}
