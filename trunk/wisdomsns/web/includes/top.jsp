<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>


<link href="../style/menu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#search-results a {
	color: #385F95;
	font: bold 11px tahoma, arial, helvetica, sans-serif;
	text-decoration: none;
}

#search-results a:hover {
	text-decoration: underline;
}

#search-results .search-item {
	padding: 5px;
}

#search-results p {
	margin: 3px !important;
}

#search-results {
	border-bottom: 1px solid #ddd;
	margin: 0 1px;
	height: 300px;
	overflow: auto;
}

#search-results .x-test {
	border: 0 none;
}

.search-text {
	line-height: 16px;
	vertical-align: middle;
}

.login-text {
	line-height: 16px;
	vertical-align: middle;
	color: #777777;
}

.x-test {
	border-color: #a9bfd3;
	border-style: solid;
	border-width: 0 0 1px 0;
	display: block;
	padding: 2px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
</style>
<script type="text/javascript">

	function pass_focus(event)
	{
	//event.srcElement.value="";
	if(event.target.type!="password")
		event.target.value="";
		event.target.type="password";
	}

	function pass_blur(event)
	{
		if(event.target.value=="")
		{
			event.target.type="text";
			event.target.value="password";
		}
	}

	function acc_focus(event,name)
	{
	//event.srcElement.value="";
		if(event.target.value==name)
		{
			event.target.value="";
		}
	}

	function acc_blur(event,name)
	{
		if(event.target.value=="")
		{
			event.target.value=name;
		}
	}
</script>
<div id="topMain">
<div id="top">

<div id="topLogin"><input type="text" size="13"
	style="color: #777777; font-size: 12px" value="account"
	onfocus="acc_focus(event,'account')" onblur="acc_blur(event,'account')">
<input type="text" name="password" id="password" size="13"
	style="color: #777777; font-size: 12px" value="password"
	onfocus="pass_focus(event)" onblur="pass_blur(event)"> <span
	class="tmpstyle"> &nbsp;<a href="" style="text-decoration: none">Login</a>
&nbsp;<a href="${pageContext.request.contextPath}/user/registration.jsp"
	style="text-decoration: none; font-size: 9px; color: #777777">Sign
on</a> </span></div>
<div class="x-test x-small-editor" id="ext-comp-1005">
<div class="x-form-field-wrap" id="ext-gen16" style="overflow: hidden;">
<input type="text" name="ext-comp-1002" id="ext-comp-1002"
	autocomplete="off" size="16" class="search-text"
	style="width: 303px; color: #777777" value="search"
	onfocus="acc_focus(event,'search')" onblur="acc_blur(event,'search')" />
<a href="#"
	style="text-decoration: none; font-size: 9px; color: #777777">search</a>

</div>

</div>
</div>
<div class="menucontainer">
<div class="menu">
<ul class="nav">
	<li><a href="${pageContext.request.contextPath}/index.jsf"
		class="hover">首页</a></li>
	<li><a href="${pageContext.request.contextPath}/vote/index.jsf">公众投票</a></li>
	<li><a href="#">服务枢纽</a></li>
	<li><a href="#">移动频道</a></li>
	<li><a href="#">互动协作</a></li>
	<li><a href="#">客户中心</a></li>
	<li><a href="#">即时通信</a></li>
	<li><a href="#">分享</a></li>
	<li><a href="#">日志</a></li>

</ul>
</div>
</div>
</div>

