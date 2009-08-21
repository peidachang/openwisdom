<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>互动智慧-<decorator:title default="Welcome!" /></title>

<decorator:head />
</head>

<body>
<table align="center">
	<tr>
		<td>
		<div id="header"><%@ include file="/includes/top1.jsp"%>
		</div>
		</td>
	</tr>
	<tr>

		<td style="background: url(/images/pagebg.gif) 0 0 repeat-x #D7E2E8;">
		<div style="width: 788px;"><decorator:body /></div>

		</td>
	</tr>
	<tr>
		<td>
		<div id="foot"><%@ include file="/includes/footer.jsp"%>
		</div>
		</td>
	</tr>
</table>

</body>
</html>

