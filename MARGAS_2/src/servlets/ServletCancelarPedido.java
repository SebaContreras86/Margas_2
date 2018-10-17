package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Pedido;
import modelo.Usuario;

@WebServlet({ "/ServletCancelarPedido", "/servletcancelarpedido" })
public class ServletCancelarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCancelarPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		try {
			ArrayList<Pedido> lista_pedidos = usuario.getPedidosPendientes();
			if (!lista_pedidos.isEmpty()) {
				request.getSession().setAttribute("pedidos_pendiente", lista_pedidos);
				request.getRequestDispatcher("/WEB-INF/CancelarPedido.jsp").forward(request, response);
			}
			else {
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
