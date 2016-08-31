<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello world
	<!--<a href = "http://localhost:8080/WebApp/Name.html?a=10&b=5">Link</a> -->
	<a href = "http://localhost:8080/WebApp/Name.html?a=0&b=0">Link</a>
	<!-- Как работает метод POST -->
	 
	<form method="POST" action="/WebApp/Name.html">
		<table>
			<tr>
				<td>a:</td>
	<td><input type="text" name="a" value=""/></td>
			</tr>
			<tr>
				<td>b:</td>
	<td><input type="text" name="b" value=""/></td>
			</tr>
			<tr>
	<td><input type="submit" name="submit" value="Go"/></td>
			</tr>
	</table>
	</form>
</body>
</html>