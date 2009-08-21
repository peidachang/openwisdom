<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.iwisdom.common.util.Constants"%>
<%@ page import="com.iwisdom.common.entity.User"%>
<html>
<head>
<title><ww:text name="page.title.account.management" /></title>
<style>
body {
	margin: 0;
	padding: 0;
	margin-top: 0;
}

#banner {
	width: 100%;
	background-color: #90BADE;
	text-align: right;
	font-size: 12px;
	height: 5%;
}

#content {
	float: left;
	width: 600px;
	height: 200px;
	padding: 5px;
	font-size: 20px;
}

.menu {
	float: left;
	width: 180px;
	height: 90%;
	border-right: 1px solid #000;
	padding: 0 0 1em 0;
	margin-bottom: 1em;
	font-family: 'Trebuchet MS', 'Lucida Grande', Arial, sans-serif;
	font-size: 90%;
	background-color: #90bade;
	color: #333;
}

.menu ul {
	list-style: none;
	margin: 0;
	padding: 0;
	border: none;
}

.menu li {
	border-bottom: 1px solid #90bade;
	margin: 0;
}

.menu li a {
	display: block;
	padding: 5px 5px 5px 8px;
	border-left: 10px solid #1958b7;
	border-right: 10px solid #508fc4;
	background-color: #2175bc;
	text-align: center;
	color: #fff;
	text-decoration: none;
	width: 82%;
}

.menu li a:hover {
	border-left: 10px solid #1c64d1;
	border-right: 10px solid #5ba3e0;
	background-color: #2586d7;
	color: #fff;
}

#sub-label li {
	list-style: inside, circle;
	text-align: center;
}

#sub-label li a {
	display: block;
	padding: 5px 5px 5px 8px;
	border-left: 10px solid #1958b7;
	border-right: 10px solid #508fc4;
	background-color: #6996D7;
	color: #fff;
	text-decoration: none;
	width: 82%;
}

#sub-label li a:hover {
	border-left: 10px solid #1c64d1;
	border-right: 10px solid #5ba3e0;
	background-color: #97a5BC;
	color: #fff;
}
</style>

<script language="javascript" src="../js/jquery-1.3.1.js"></script>
<script type="text/javascript">
	function opens(v){
	var v = document.getElementById(v);
		if(v.style.display=="none")
			v.style.display="inline";
		else
			v.style.display="none";
	}
	</script>

<script language="javascript">
function getXML(addressXML){
	var s = "<%=session.getAttribute("p")%>";
	var s_prefix = null;
	if(s!="null"){
		s_prefix = s.substring(0,2);
	}
	$.ajax({
		type: "GET",
		url: addressXML,
		dataType: "xml",	//大小写敏感
		success: function(myXML){
			$(myXML).find("tag").each(
				function(){
					sName = "<b>"+$(this).find("label").text()+"</b>";
					sId = $(this).find("id").text();
					status = $(this).find("status").text();
					var style ;
					if(s_prefix!=null&&sId==s_prefix){
						style = "display:inline";
					}else{
						style = "display:none";
					}
					v = "<li><a href='javascript:opens("+sId+")'>"+sName+"</a><span id='"+sId+"' style='"+style+"'><ul id='sub-label'>";
										
					$(this).find("sub").each(function(){
					subLabel = $(this).find("sub-label").text();
					href = $(this).find("href").text();
					id = $(this).find("sub-id").text();
					if(s==id){
					v+="<li><a style='background-color:#97a5BC' href='"+href+"'>"+subLabel+"</a></li>";
					}else{
					v+="<li><a href='"+href+"'>"+subLabel+"</a></li>";
					}
					});
					v+="</ul></li>";
					$("#member").append($(v));
				}
			);
		}
	});
}
</script>
</head>
<body onload="getXML('menu.xml');">
<div id="banner"><br>
<span style="color: #fffff"> <% 
User user = (User)session.getAttribute(Constants.USER_SESSION);
if(user!=null)
	out.print("["+user.getUserName()+"]"); 
%> </span> &nbsp;<a style="text-decoration: none" href="/logout.do"><ww:text
	name="page.button.logout" /></a>&nbsp;</div>
<div class="menu">
<ul id="member">
</ul>
</div>
<div style="width: 960px; margin: 0 auto;"><decorator:body /></div>
</body>
</html>