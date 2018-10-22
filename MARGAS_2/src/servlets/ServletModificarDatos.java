package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

@WebServlet({ "/ServletModificarDatos", "/servletmodificardatos" })
public class ServletModificarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletModificarDatos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
