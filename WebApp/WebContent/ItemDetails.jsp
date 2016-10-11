<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ru.unlimit.EquipmentsList"%>
<html>
<head>
<title>Equipment Item Detail</title>
<link rel="stylesheet" href="Theme/Style.css">
</head>
<body>
<%EquipmentsList item = (EquipmentsList)request.getAttribute("results");%>
<!--  <form name="newAuctionListing" method="post" action="createNewAuctionListing">-->
<TABLE border="0">
<TBODY>
<TR>
<TD>Характеристика:</TD>
<TD><TEXTAREA rows="1" cols="60" name="short_name" readonly>
<%=item.getCNAME() %></TEXTAREA></TD>
</TR>
</TBODY>
</TABLE>
<INPUT type="submit" name="button_auction" value="Put up for auction">
<!--</form>-->
<p><A href="index.html">Home</A></p>
</body>
</html>