package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorPedido;
import modelo.Cliente;
import modelo.ClienteDAO;

@WebServlet({ "/Login", "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		/* Acá dejamos la ultima ves
		Usuario usuario = UsuarioDAO.GetOne(usuario, password);
		
		if (usuario.getTipo().equals("admin")) {
			
		}
		else {
			ClienteDAO.GetOne(usuario, password);
		}
		
		*/
		
		try {
			Cliente cliente = ClienteDAO.GetOne(usuario, password);
			if (cliente != null) {
				request.getSession().setAttribute("cliente", cliente);
				request.getRequestDispatcher("/WEB-INF/InicioCliente.jsp").forward(request, response);
			}
			else {
				request.getSession().invalidate();
				request.setAttribute("mensaje", "Usuario no encontrado");
				request.getRequestDispatcher("/IniciarSesion.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "Parece que hubo un problema al acceder a la base de datos");
			request.getRequestDispatcher("/IniciarSesion.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
