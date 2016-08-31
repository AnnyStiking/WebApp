package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "/JSPServlet", loadOnStartup = 1)
public class JSPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Integer status;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initialization of servlet number 2");
		status = Integer.parseInt(getServletConfig().getInitParameter("paramname3"));
		int a = Integer.parseInt(getServletConfig().getInitParameter("paramname2"));
		System.out.println("Status 1: " + status);
		System.out.println("Status 2: " + a);
	}


/*	@Override
	public void init(ServletConfig config) throws ServletException {
		status = Integer.parseInt(config.getInitParameter("paramname3"));
		System.out.println("Status 1: " + status);
	}
*/




	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//Передача данных из сервлета на jsp-страницу	
		User user = new User();
		user.setFirstname("Ivan");
		user.setLastname("Ivanov");
		user.setAge(34);
		
		request.setAttribute("user", user);
		request.setAttribute("a", 1);
		request.setAttribute("b", "aaaaaaa");
		request.setAttribute("status", status);
//Как работает GET запрос (складываем a и b)
		
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		int res = a + b;

		request.setAttribute("res", res);
		
	/*	if (a == (Integer) null & b == (Integer) null)
		{
			PrintWriter out = response.getWriter();
			out.println("Введите параметры a и b в Url");
			out.close();
		}
	*/	
		//request.getSession().getServletContext().getInitParameter("Fire");
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Name.jsp");
		requestDispatcher.forward(request, response);
		//getServletContext().getRequestDispatcher("/Name.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		int res = a + b;
		request.setAttribute("res", res);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Post.jsp");
		requestDispatcher.forward(request, response);
	}

	

}
