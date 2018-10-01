package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ClienteDAO;
import modelo.DataBase;

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
				System.out.println("falso");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
