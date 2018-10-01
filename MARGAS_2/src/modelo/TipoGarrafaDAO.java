package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoGarrafaDAO {
	
	/*	Este método devuelve un ArrayList con todos los tipos de garrafas
	 	registrados en la base de datos invocando un procedimiento almacenado*/
	public static ArrayList<TipoGarrafa> GetAll() throws ClassNotFoundException, SQLException {
		ArrayList<TipoGarrafa> lista_tipos_garrafa = new ArrayList<TipoGarrafa>();
		
		ResultSet rs = DataBase.CallStoredProcedure("{call SelectAllTipoGarrafa()}");
		while (rs.next()) {
			TipoGarrafa tipoGarrafa = new TipoGarrafa(rs.getInt("id"), rs.getString("descripcion"), rs.getInt("stock"), rs.getDouble("precio"));
			lista_tipos_garrafa.add(tipoGarrafa);
		}
		
		DataBase.Close();
		
		return lista_tipos_garrafa;
	}
	
	/*	Este método devuelve un tipo de garrafa
 			registrado en la base de datos que coincida con el id que se le pasa por parámetro
 			invocando un procedimiento almacenado o null si no lo encuentra*/
	public static TipoGarrafa GetOne(int id_producto) throws ClassNotFoundException, SQLException {
		
		CallableStatement cs = DataBase.GetCallableStatement("{call SelectOneTipoGarrafa(?)}");
		cs.setInt(1, id_producto);
		
		ResultSet rs = cs.executeQuery();
		
		TipoGarrafa tipoGarrafa = null;
		if(rs.first()) {
			tipoGarrafa = new TipoGarrafa(rs.getInt("id"), rs.getString("descripcion"), rs.getInt("stock"), rs.getDouble("precio"));
		}
		
		return tipoGarrafa;
	}

	public static void UpdateStock(TipoGarrafa tipoGarrafa) throws ClassNotFoundException, SQLException {
		CallableStatement cs = DataBase.GetCallableStatement("call UpdateStock(?, ?)");
		cs.setInt(1, tipoGarrafa.getStock());
		cs.setInt(2, tipoGarrafa.getId());
		
		cs.executeUpdate();
	}

}
