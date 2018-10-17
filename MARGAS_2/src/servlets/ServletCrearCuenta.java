package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.DataBase;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet({ "/ServletCrearCuenta", "/servletcrearcuenta" })
public class ServletCrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("usuario");
		try {
			if (DataBase.Existe(user_name)) {
				request.getRequestDispatcher("/CrearCuenta.jsp").forward(request, response);
			}
			else {
				String password = request.getParameter("password");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String dni = request.getParameter("dni");
				String tipo = request.getParameter("tipo");
				String direccion = request.getParameter("direccion");
				String telefono = request.getParameter("telefono");
				
				Usuario usuario = new Usuario();
				
				usuario.setUsuario(user_name);
				usuario.setPassword(password);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setEmail(email);
				usuario.setTelefono(telefono);
				usuario.setDni(dni);
				usuario.setDireccion(direccion);
				usuario.setTipo(tipo);
					
				UsuarioDAO.Save(usuario);
				
				response.sendRedirect("http://localhost:8080/MARGAS_2/IniciarSesion.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
