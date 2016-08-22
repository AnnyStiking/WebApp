<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>How works JSP</title>
</head>
<body>

		<form method = "GET" action = "JSPServlet">
			Пользователь:<input type = "text" name = "firstname">
			<input type = "submit" value = "Отправить">
		</form>
		
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
</body>
</html>