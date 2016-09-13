<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List" import="java.util.Iterator" import="ru.unlimit.EquipmentsList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="Theme/Style.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Equipment's List</title>
</head>
<body>
<h2><b>Equipment's List</b></h2>
<TABLE>
<THEAD>
<TR>
<TH rowspan="2">Equipment's number
<TH rowspan="2">Title
<TBODY>
<%
List list = (List) request.getAttribute("results");
Iterator iterator =  list.iterator();
int i = 0;
while (iterator.hasNext()) {
i++;
EquipmentsList item = new EquipmentsList();
item = (EquipmentsList)iterator.next();
%>
<TR>
<TD class=trone><%= item.getEquipment_id()%>
<TD class=trtwo><%= item.getEquipment_name()%>
<%}%>
</TABLE>
<p><A href="index.html">Home</A></p>
</body>
</html>