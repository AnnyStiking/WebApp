<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>How works JSP</title>
</head>
<body>

	<!--  	<form method = "GET" action = "JSPServlet">
			Пользователь:<input type = "text" name = "firstname">
			<input type = "submit" value = "Отправить">
		</form>
		-->
	<h1>
		<%
	String name = request.getParameter("firstname");
		if (name == null || name.length() == 0){
			%>
			Heeeey!!!
			<%			} else {
				%>
			Heeeeey!!! I'm <%= name%>
			<%
		}
		%>
	</h1>

  	<br>
	a: <%= request.getAttribute("a") %>
	b: <%= request.getAttribute("b") %>
	status: <%= request.getAttribute("status") %>
	
	
	<br>
  	<%
		//User user = (User)request.getAttribute("user");
		//out.println(user.getFirstname());
		//out.println(user.getLastname());
		//out.println(user.getAge());
	%>
	
	
	Имя: ${user.firstname}</br>
	Фамилия: ${user.lastname}</br>
	Возраст: ${user.age}</br>
	Результат сложения параметров a и b: ${res}</br> 
	По умолчанию параметры a = 0 и b = 0
</body>
</html>