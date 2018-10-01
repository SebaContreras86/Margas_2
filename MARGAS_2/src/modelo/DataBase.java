package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
	private static String Driver = "org.gjt.mm.mysql.Driver";
	private static String Url = "jdbc:mysql://sql10.freemysqlhosting.net/sql10257011";
	private static String Usuario = "sql10257011";
	private static String Password = "gKU9CxzHIC";
	private static Connection Conexion = null;
	private static CallableStatement Cs = null;
	private static ResultSet Rs = null;
	
	private static void Init () throws SQLException, ClassNotFoundException {
		Class.forName(Driver);
		Conexion = DriverManager.getConnection(Url, Usuario, Password);
	}
	
	public static ResultSet CallStoredProcedure(String consulta) throws ClassNotFoundException, SQLException {
		Init();
		Cs = Conexion.prepareCall(consulta);
		ResultSet Rs = Cs.executeQuery();
		
		return Rs;
	}
	
	public static CallableStatement GetCallableStatement(String consulta) throws ClassNotFoundException, SQLException {
		Init();
		Cs = Conexion.prepareCall(consulta);
		
		return Cs;
	}
	
	public static void Close() throws SQLException {
		if (Conexion != null) {
			Conexion.close();
		}
		if (Cs != null) {
			Cs.close();
		}
		if (Rs != null) {
			Rs.close();
		}
	}

	public static boolean Existe(String usuario) throws ClassNotFoundException, SQLException {
		boolean Existe = false;
		
		Init();
		Cs = Conexion.prepareCall("{call SelectUsuario(?)}");
		Cs.setString(1, usuario);
		
		Rs = Cs.executeQuery();
		Existe = Rs.first();
		
		Close();
		
		return Existe;
	}
}
