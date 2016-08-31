package ru.unlimit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;


@WebServlet(urlPatterns = "/MyServlet", loadOnStartup = 2)
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Info");
		
		String name = request.getParameter("firstName");
		String surname = request.getParameter("secondName");
		//String job = request.getParameter("job");
		String [] jobs = request.getParameterValues("job");
		String gender = request.getParameter("gender");
			
			if (gender == null)
			{
				gender = "-";
			}
			
		String age18 = request.getParameter("age18");
		
		if (age18 == null)
		{
			age18 = "-";
		}
		
		System.out.println("Имя: " + name);
		System.out.println("Фамилия: " + surname);
		//System.out.println("Профессия: " + job);
		System.out.println("Количество ролей: " + jobs.length);
		for (String job : jobs) {
			System.out.print(job + ", ");
		}
		System.out.println("Пол: " + gender);
		System.out.println("Старше 18: " + age18);
		
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h3>Профиль сотрудника</h3>");
		out.println("Имя: " + name + "<br>");
		out.println("Фамилия: " + surname + "<br>");
		//out.println("Профессия: " + job + "<br>");
		out.println("Профессия: " + Arrays.deepToString(jobs) + "<br>");
		out.println("Пол: " + gender + "<br>");
		out.println("Старше 18: " + age18 + "<br>");
		//out.println("<h3>Hello from MyServlet.GET " + name + " " + surname + "</h3>");
		out.close();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Info");
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("firstName");
		String surname = request.getParameter("secondName");
		System.out.println(name);
		System.out.println(surname);
		
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h3>Hello from MyServlet.POST " + name + " " + surname + "</h3>");
		out.close();
		
	}

}
