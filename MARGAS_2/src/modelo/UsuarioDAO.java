package modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class UsuarioDAO {

	public static void Save(Usuario user) throws SQLException, ClassNotFoundException {
		CallableStatement cs = DataBase.GetCallableStatement("{call InsertUsuario(?, ?, ?, ?, ?, ?)}");
		
		cs.setString(1, user.getUsuario());
		cs.setString(2, user.getPassword());
		cs.setString(3, user.getNombre());
		cs.setString(4, user.getApellido());
		cs.setString(5, user.getEmail());
		cs.setString(6, user.getDni());
		
		cs.executeUpdate();
		
		DataBase.Close();
	}
}
