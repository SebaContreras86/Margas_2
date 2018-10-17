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
import modelo.Usuario;
import modelo.TipoGarrafa;

@WebServlet({ "/ServletPedido", "/servletpedido" })
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("usuario") != null) {
			
			/* =========== Si la sesión no tiene controlador de pedido se le asigna uno =========== */		
			ControladorPedido controlador = null;
			if (request.getSession().getAttribute("ctrl_pedido") == null) {
				controlador = new ControladorPedido();
				request.getSession().setAttribute("ctrl_pedido", controlador);
				
				/* =========== Se inicia el pedido y se envían todos los productos de la BD a la UI  =========== */		
				String dni = ((Usuario) request.getSession().getAttribute("usuario")).getDni();
				try {
					ArrayList<TipoGarrafa> lista_productos = controlador.iniciarPedido(dni);
					request.getSession().setAttribute("lista_productos", lista_productos);
					request.getSession().setAttribute("lineas_de_pedido", controlador.getPedido().getLineas());
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				controlador = (ControladorPedido) request.getSession().getAttribute("ctrl_pedido");
			}
			
			String opcion = request.getParameter("opcion");
			if (opcion == null) {
				opcion = "";
			}
			
			switch (opcion) {
			case "Agregar":
				int id_producto = Integer.parseInt(request.getParameter("id"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				try {
					controlador.agregarProducto(id_producto, cantidad);
					request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "Quitar":
				int id_producto_eliminar = Integer.parseInt(request.getParameter("id_producto"));
				controlador.eliminarLinea(id_producto_eliminar);
				request.getRequestDispatcher("/WEB-INF/RealizarPedido.jsp").forward(request, response);
				break;
			case "Enviar pedido":
				try {
					if (controlador.enviar()) {
						request.getSession().removeAttribute("ctrl_pedido");
						request.getRequestDispatcher("/ConfirmacionPedido.jsp").forward(request, response);
					}
					else  {
						request.getRequestDispatcher("/WEB-INF/InicioCliente.jsp").forward(request, response);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		/* =========== Si la sesión no tiene cliente, se redirige a la página de inicio de sesión  =========== */
		else {
			response.sendRedirect("http://localhost:8080/MARGAS_2/IniciarSesion.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
