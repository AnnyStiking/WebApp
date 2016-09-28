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
<TD>Item Id:</TD>
<TD><INPUT type="text" name="id" size="5" value=<%=item.getEquipment_id() %> readonly></TD>
</TR>
<TR>
<TD>Item:</TD>
<TD><TEXTAREA rows="1" cols="60" name="short_name" readonly>
<%=item.getEquipment_name() %></TEXTAREA></TD>
</TR>
<TR>
<TD>Item Description:</TD>
<TD><TEXTAREA rows="3" cols="40" name="description" readonly>
<%=item.getCharacteristic() %></TEXTAREA></TD>
</TR>
<TR>
<TD>Minimum Price:</TD>
<TD><INPUT type="text" name="minPrice" size="20"></TD>
</TR>
</TBODY>
</TABLE>
<INPUT type="submit" name="button_auction" value="Put up for auction">
<!--</form>-->
<p><A href="index.html">Home</A></p>
</body>
</html>