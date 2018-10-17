package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {

	public static void Save(Pedido pedido) throws ClassNotFoundException, SQLException {
		CallableStatement cs = DataBase.GetCallableStatement("call InsertPedido(?, ?)");
		cs.setString(1, "pendiente");
		cs.setString(2, pedido.getDni());
		
		cs.executeUpdate();
		
		ArrayList<LineaDePedido> lineas = pedido.getLineas();
		for (LineaDePedido lineaDePedido : lineas) {
			int id_tg = lineaDePedido.getTipoGarrafa().getId();
			int cantidad = lineaDePedido.getCantidad();
			
			cs = DataBase.GetCallableStatement("call InsertLineaDePedido(?, ?)");
			cs.setInt(1, id_tg);
			cs.setInt(2, cantidad);
			
			cs.executeUpdate();
		}
	}

	public static ArrayList<Pedido> getAllByUsuario(String dni) throws ClassNotFoundException, SQLException {
		ArrayList<Pedido> lista_pedidos = new ArrayList<Pedido>();
		
		CallableStatement cs = DataBase.GetCallableStatement("{call PedidosPendientes(?)}");
		cs.setString(1, dni);
		
		ResultSet rs = cs.executeQuery();
		
		Pedido pedido = null;
		while (rs.next()) {
			pedido = new Pedido(dni);
			pedido.setFecha(rs.getDate("fecha"));
			pedido.setHora(rs.getTime("hora"));
			pedido.setEstado(rs.getString("estado"));
			
			lista_pedidos.add(pedido);
		}
		
		return lista_pedidos;
	}
}
