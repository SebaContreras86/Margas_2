package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.DataBase;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet({ "/ServletCrearCuenta", "/servletcrearcuenta" })
public class ServletCrearCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		try {
			if (DataBase.Existe(usuario)) {
				request.getRequestDispatcher("/CrearCuenta.jsp").forward(request, response);
			}
			else {
				String password = request.getParameter("password");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String dni = request.getParameter("dni");
				
				String tipo = request.getParameter("tipo");
				if (tipo.equals("cliente")) {
					String telefono = request.getParameter("telefono");
					String direccion = request.getParameter("direccion");
					
					Cliente cliente = new Cliente();
					cliente.setUsuario(usuario);
					cliente.setPassword(password);
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setEmail(email);
					cliente.setTelefono(telefono);
					cliente.setDni(dni);
					cliente.setDireccion(direccion);
					
					ClienteDAO.Save(cliente);
					
					//response.sendRedirect("localhost:8080/MARGAS_2/IniciarSesion.jsp");
					//request.getRequestDispatcher("/WEB-INF/InicioCliente.jsp").forward(request, response);
				}
				else {
					Usuario user = new Usuario();
					user.setUsuario(usuario);
					user.setPassword(password);
					user.setNombre(nombre);
					user.setApellido(apellido);
					user.setDni(dni);
					user.setEmail(email);
					
					UsuarioDAO.Save(user);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
