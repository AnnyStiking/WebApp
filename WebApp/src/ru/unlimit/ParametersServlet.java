package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/ParametersServlet", loadOnStartup = 0)
public class ParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Initialization of servlet");
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		if (user!="" && user!=null){
			session.setAttribute("user", user);
			context.setAttribute("user", user);
		}
	//	session.setMaxInactiveInterval(5);
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		out.println("Request parameter: " + user + "<br>");
		out.println("Session parameter: " + session.getAttribute("user") + "<br>");
		out.println("Context parameter: " + context.getAttribute("user") + "<br>");
		out.println("<a href = 'http://localhost:8080/WebApp/Parameters.html'>Назад</a><br>");
		out.close();
		
		System.out.println("session time in seconds: " + session.getMaxInactiveInterval());
		
	}

}
