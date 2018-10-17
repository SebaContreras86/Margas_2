package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	public static Usuario GetOne(String user_name, String password) throws ClassNotFoundException, SQLException {
		
		CallableStatement cs = DataBase.GetCallableStatement("{call SelectOneUsuario(?, ?)}");
		cs.setString(1, user_name);
		cs.setString(2, password);
		
		ResultSet rs = cs.executeQuery();
		
		Usuario usuario = null;
		if (rs.first()) {
			usuario = new Usuario();
			usuario.setUsuario(rs.getString("nombre_usuario"));
			usuario.setPassword(rs.getString("password"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellido(rs.getString("apellido"));
			usuario.setDni(rs.getString("dni"));
			usuario.setEmail(rs.getString("email"));
			usuario.setTelefono(rs.getString("telefono"));
			usuario.setDireccion(rs.getString("direccion"));
			usuario.setTipo(rs.getString("tipo"));
		}
		
		DataBase.Close();
		rs.close();
		
		return usuario;
	}

	public static void Save(Usuario usuario) throws ClassNotFoundException, SQLException {
		CallableStatement cs = DataBase.GetCallableStatement("{call InsertUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		
		cs.setString(1, usuario.getUsuario());
		cs.setString(2, usuario.getPassword());
		cs.setString(3, usuario.getNombre());
		cs.setString(4, usuario.getApellido());
		cs.setString(5, usuario.getEmail());
		cs.setString(6, usuario.getDni());
		cs.setString(7, usuario.getTelefono());
		cs.setString(8, usuario.getDireccion());
		cs.setString(9, usuario.getTipo());
		
		cs.executeUpdate();
		
		DataBase.Close();
	}
}
