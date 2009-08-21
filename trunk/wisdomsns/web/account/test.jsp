<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="ww" uri="/webwork"%>

<html>
<head>
<title>Hello Page</title>
</head>
<body>
<%
String p = request.getParameter("p");
session.setAttribute("p",p);
%>

What's wrong? from
<%=p %>
</body>
</html>