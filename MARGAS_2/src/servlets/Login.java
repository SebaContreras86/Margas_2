package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorPedido;
import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet({ "/Login", "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		try {
			Usuario usuario = UsuarioDAO.GetOne(user_name, password);
			request.getSession().setAttribute("usuario", usuario);
			
			if (usuario == null) {
				request.setAttribute("mensaje", "Usuario no encontrado");
				request.getRequestDispatcher("/IniciarSesion.jsp").forward(request, response);
			}
			else if (usuario.getTipo().equals("admin")) {
				request.getRequestDispatcher("WEB-INF/InicioAdmin.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("WEB-INF/InicioCliente.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
