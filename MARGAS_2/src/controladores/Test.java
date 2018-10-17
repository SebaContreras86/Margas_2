package controladores;

import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pedido;
import modelo.Usuario;
import modelo.UsuarioDAO;

public class Test {

	public static void main(String[] args) {
		try {
			Usuario usuario = UsuarioDAO.GetOne("juan", "111");
			
			System.out.println(usuario.getDni());
			System.out.println(usuario.getNombre());
			System.out.println(usuario.getApellido());
			
			ArrayList<Pedido> lista_pedidos = usuario.getPedidosPendientes();
			
			if (lista_pedidos.isEmpty()) {
				System.out.println("No hay pedidos pendientes");
			}
			else{
				for (Pedido pedido : lista_pedidos) {
					System.out.println(pedido.getDni());
					System.out.println(pedido.getEstado());
					System.out.println(pedido.getFecha());
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
