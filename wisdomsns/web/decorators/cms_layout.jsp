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
<style>
body {
	margin: 0;
	padding: 0;
	margin-top: 0;
}

#banner {
	width: 100%;
	background-color: #B2BAE8;
	font-size: 12px;
	height: 5%;
}

#left {
	float: left;
	width: 20%;
	height: 95%;
	font-family: 'Trebuchet MS', 'Lucida Grande', Arial, sans-serif;
	font-size: 90%;
	background-color: #F0F8FF;
	font-weight:bold;
	color: #333;
}
</style>
<title>内容管理</title>
<link rel="stylesheet" href="/style/dtree.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="/style/dhtmlXTree.css">
<script src="/js/dhtmlxtree/dhtmlXCommon.js"></script>
<script src="/js/dhtmlxtree/dhtmlXTree.js"></script>
<script src="/js/dhtmlxtree/dhtmlXTreeExtend.js"></script>

<script type="text/javascript" src="/js/dtree.js"></script>
<script type="text/javascript">
		function loadXMLDoc(dname) 
		{
		var xmlDoc;
		if (window.XMLHttpRequest)
		  {
		  xmlDoc=new window.XMLHttpRequest();
		  xmlDoc.open("GET",dname,false);
		  xmlDoc.send("");
		  return xmlDoc.responseXML;
		  }
		// IE 5 and IE 6
		else if (window.ActiveXObject)
		  {
		  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		  xmlDoc.async=false;
		  xmlDoc.load(dname);
		  return xmlDoc;
		  }
		alert("Error loading document");
		return null;
		}
		var d = new dTree('d');
		d.add(0,-1,'<ww:text name="page.cms.tree.root"/>',"index.do");
		
		xmlDoc=loadXMLDoc("/data/menu/menu_2.xml");
		notes = xmlDoc.getElementsByTagName("item");
		for(var i=0 ;i <notes.length;i++){
		//alert("id="+notes[i].getAttribute("id")+",pid="+notes[i].getAttribute("pid")+",name="+notes[i].getAttribute("name"));
		d.add(notes[i].getAttribute("id"),notes[i].getAttribute("pid"),notes[i].getAttribute("name"),notes[i].getAttribute("url"));
		}
	</script>
</head>
<body>
<div id="banner">
<span style="color: #fffff">
<% 
User user = (User)session.getAttribute(Constants.USER_SESSION);
if(user!=null)
	out.print("Welcome ["+user.getUserName()+"]"); 
%>  &nbsp;<a style="text-decoration: none" href="/logout.do"><ww:text
	name="page.button.logout" /></a>
</span>
</div>
<div id="left">

<script type="text/javascript">
document.write(d);
</script>

</div>
<div style="width:80%;margin-left:20%;"><decorator:body /></div>
</body>
</html>