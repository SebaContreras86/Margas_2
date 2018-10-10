package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

	public static Cliente GetOne(String usuario, String password) throws ClassNotFoundException, SQLException {
		
		CallableStatement cs = DataBase.GetCallableStatement("{call ClienteMasUsuario(?, ?)}");
		cs.setString(1, usuario);
		cs.setString(2, password);
		
		ResultSet rs = cs.executeQuery();
		
		Cliente cliente = null;
		if (rs.first()) {
			cliente = new Cliente();
			cliente.setUsuario(rs.getString("nombre_usuario"));
			cliente.setPassword(rs.getString("password"));
			cliente.setNombre(rs.getString("nombre"));
			cliente.setApellido(rs.getString("apellido"));
			cliente.setDni(rs.getString("dni"));
			cliente.setEmail(rs.getString("email"));
			cliente.setTelefono(rs.getString("telefono"));
		}
		
		DataBase.Close();
		rs.close();
		
		return cliente;
	}

	public static void Save(Cliente cliente) throws ClassNotFoundException, SQLException {
		CallableStatement cs = DataBase.GetCallableStatement("{call InsertClienteMasUsuario(?, ?, ?, ?, ?, ?, ?, ?)}");
		cs.setString(1, cliente.getUsuario());
		cs.setString(2, cliente.getPassword());
		cs.setString(3, cliente.getNombre());
		cs.setString(4, cliente.getApellido());
		cs.setString(5, cliente.getEmail());
		cs.setString(6, cliente.getDni());
		cs.setString(7, cliente.getTelefono());
		cs.setString(8, cliente.getDireccion());
		
		cs.executeQuery();
		
		DataBase.Close();
	}
}
