<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<html>
<head>
<style>
body {
	padding: 0;
	margin-top: 0;
	margin-bottom: 0;
	margin-left: 0;
	margin-right: 0;
}

#banner {
	width: 100%;
	background-color: #CCCCFF;
	text-align: right;
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
	color: #333;
}
</style>
<title>管理员页面</title>
<link rel="stylesheet" href="/style/dtree.css" type="text/css" />
<script type="text/javascript" src="/js/xmltree.js"></script>
<link type="text/css" rel="stylesheet" href="/style/xmltree.css" />
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
		//d.add(0,-1,'<ww:text name="page.cms.tree.root"/>',"index.do");
		xmlDoc=loadXMLDoc("/admin/xmltree1.xml");
		notes = xmlDoc.getElementsByTagName("node");
		for(var i=0 ;i <notes.length;i++){
		var name = notes[i].getElementsByTagName("name")[0].childNodes[0].nodeValue;
		var url = "";
		if(typeof notes[i].getElementsByTagName("url")[0].childNodes[0]!="undefined"&&notes[i].getElementsByTagName("url")[0].childNodes[0]!=null){
		url = notes[i].getElementsByTagName("url")[0].childNodes[0].nodeValue;
		}
		var id = notes[i].getAttribute("id");
		var pid = notes[i].getAttribute("parentid");
		
		//alert("name="+name+",id="+id+",pid="+pid);
		//alert("id="+notes[i].getAttribute("id")+",pid="+notes[i].getAttribute("pid")+",name="+notes[i].getAttribute("name"));
		d.add(id,pid,name,url);
		}
	</script>
</head>
<body>
<div id="banner"><span style="color: #fffff"> Test </span></div>
<div id="left"><script type="text/javascript">
/**
var tree = new xmlTree('tree','xmltree1.xml');
tree.mode = 0;
tree.createTree();
*/
document.write(d);
</script></div>
<div style="width: 80%; margin-left: 20%;"><decorator:body /></div>
</body>
</html>