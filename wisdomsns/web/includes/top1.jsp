<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="ww" uri="/webwork"%>
<html>
<head>


<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>i-wisdom</title>

<link href="../style/menu.css" rel="stylesheet" type="text/css" />
<link href="../style/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>

<script type="text/javascript">

	function pass_focus(event)
	{
	//event.srcElement.value="";
	if(event.target.type!="password")
		event.target.value="";
		event.target.type="password";
	}

	function pass_blur(event,password)
	{
		if(event.target.value=="")
		{
			event.target.type="text";
			event.target.value=password;
		}
	}

	function acc_focus(event)
	{
	//event.srcElement.value="";
		
		if(event.target.id=="account")
		{
			event.target.value="";
		}
	}

	function acc_blur(event,name)
	{
		if(event.target.value=="")
		{
			event.target.value=name;
			event.target.id="account";
		}else{
			event.target.id="account_";
		}
	}
	function login(){
		document.loginform.submit();
	}

	function current(event){
	alert(event.target);
	//event.target.className="current";
	}

</script>
</head>

<%
String uri = request.getRequestURI();

String uri_sub = uri.substring(1,uri.length()-3);
uri_sub = "tab_"+uri_sub;
int index = uri_sub.indexOf("/");
if(index!=-1){
	uri_sub = uri_sub.substring(0,index);
}

//out.print(uri_sub);
%>
<script type="text/javascript">
window.onload=function(){
document.getElementById("<%=uri_sub%>").className = "current";
};
</script>
<div id="topLogin">
<table width="100%">
<tr>
<td width="56%"></td>
<td>
<form name="loginform" action="login.do" method="post">
<input type="text" size="19" id="account"
		style="color: #777777; font-size: 12px" name="user.email"
		value="<ww:text name="page.login.label.email"/>"
		onfocus="acc_focus(event)"
		onblur="acc_blur(event,'<ww:text name="page.login.label.email"/>')">
		
		<input type="text" name="user.password" id="password"
		size="14" style="color: #777777; font-size: 12px"
		value="<ww:text name="page.login.label.password"/>"
		onfocus="pass_focus(event)"
		onblur="pass_blur(event,'<ww:text name="page.login.label.password"/>')">
		
		<span> &nbsp;<a href="javascript:login()"
		style="padding-top: 5px; text-decoration: none"><ww:text
		name="page.button.login" /></a> &nbsp;<a
		href="${pageContext.request.contextPath}/registration.do"
		style="text-decoration: none; font-size: 9px; color: #777777"> <ww:text
		name="page.button.signup" /></a> </span>
</form></td>
</tr>
</table>
</div>
<div id="dolphincontainer">
<div id="dolphinnav">
<ul>
	<li><a id="tab_index"
		href="/index.do"> <span><ww:text
		name='page.top.menu.item1' /></span></a></li>

	<li><a id="tab_service" href="/service.do"><span><ww:text
		name='page.top.menu.item3' /></span></a></li>
	<li><a id="tab_mobile"
		href="/mobile.do"><span><ww:text
		name='page.top.menu.item4' /></span></a></li>
	<li><a id="tab_brand" href="/brand.do"><span><ww:text
		name='page.top.menu.item6' /></span></a></li>
	<li><a id="tab_share"
		href="/share.do"><span><ww:text
		name='page.top.menu.item8' /></span></a></li>
	<%-- 
	<li><a id="tab_vote"
		href="${pageContext.request.contextPath}/vote.do"
		onclick="javascript:current()"><span><ww:text
		name='page.top.menu.item2' /></span></a></li>
	<li><a id="tab_interactive"
		href="${pageContext.request.contextPath}/interactive.do"><span><ww:text
		name='page.top.menu.item5' /></span></a></li>
	<li><a id="tab_im" href="#"><span><ww:text
		name='page.top.menu.item7' /></span></a></li>
	<li><a id="tab_daily" href="#"><span><ww:text
		name='page.top.menu.item9' /></span></a></li>
	--%>

</ul>
</div>
</div>
<div id="current_menu">品牌中心>公共品牌>首都机场</div>
</html>