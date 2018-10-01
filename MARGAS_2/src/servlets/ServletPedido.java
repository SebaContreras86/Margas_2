package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorPedido;
import modelo.Cliente;
import modelo.TipoGarrafa;

@WebServlet({ "/ServletPedido", "/servletpedido" })
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("cliente") != null) {
			ControladorPedido controlador = (ControladorPedido) request.getSession().getAttribute("ctrl_pedido"); 
			if (controlador == null) {
				controlador = new ControladorPedido();
				request.getSession().setAttribute("ctrl_pedido", controlador);
				
				String dni = ((Cliente) request.getSession().getAttribute("cliente")).getDni();
				try {
					ArrayList<TipoGarrafa> lista_productos = controlador.iniciarPedido(dni);
					request.getSession().setAttribute("lista_productos", lista_productos);
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				if (request.getParameter("agregar") != null) {
					int id_producto = Integer.parseInt(request.getParameter("id"));
					int cantidad = Integer.parseInt(request.getParameter("cantidad"));
					try {
						controlador.agregarProducto(id_producto, cantidad);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					request.getSession().setAttribute("lineas_de_pedido", controlador.getPedido().getLineas());
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				}
				else if (request.getParameter("quitar") != null) {
					int id_producto = Integer.parseInt(request.getParameter("id_producto"));
					controlador.eliminarLinea(id_producto);
					request.getSession().setAttribute("lineas_de_pedido", controlador.getPedido().getLineas());
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				}
				else if (request.getParameter("enviar") != null) {
					if (!controlador.getPedido().getLineas().isEmpty()) {
						try {
							controlador.enviar();
							response.sendRedirect("http://localhost:8080/MARGAS_2/ConfirmacionPedido.jsp");
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}	
					}
					else {
						request.getRequestDispatcher("/WEB-INF/InicioCliente.jsp").forward(request, response);
					}
					request.getSession().removeAttribute("ctrl_pedido");
					//request.getRequestDispatcher("/WEB-INF/ConfirmacionPedido.jsp").forward(request, response);
					
				}
				else {
					request.getSession().setAttribute("lineas_de_pedido", controlador.getPedido().getLineas());
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				}
			}
		}
		else {
			response.sendRedirect("http://localhost:8080/MARGAS_2/IniciarSesion.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
