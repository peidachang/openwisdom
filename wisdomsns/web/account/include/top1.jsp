<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<html>
<head>


<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>i-wisdom</title>
<link type="text/css" rel="stylesheet" href="../innerstyle.css" />
<link href="../style/menu.css" rel="stylesheet" type="text/css" />
<link href="../style/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
<style charset="utf-8" type="text/css">
/* See license.txt for terms of usage */
.firebugHighlight {
	z-index: 2147483647;
	position: absolute;
	background-color: #3875d7;
}

.firebugLayoutBoxParent {
	z-index: 2147483647;
	position: absolute;
	background-color: transparent;
	border-right: 1px dashed #BBBBBB;
	border-bottom: 1px dashed #BBBBBB;
}

.firebugRulerH {
	position: absolute;
	top: -15px;
	left: 0;
	width: 100%;
	height: 14px;
	background: url(chrome://firebug/skin/rulerH.png) repeat-x;
	border-top: 1px solid #BBBBBB;
	border-right: 1px dashed #BBBBBB;
	border-bottom: 1px solid #000000;
}

.firebugRulerV {
	position: absolute;
	top: 0;
	left: -15px;
	width: 14px;
	height: 100%;
	background: url(chrome://firebug/skin/rulerV.png) repeat-y;
	border-left: 1px solid #BBBBBB;
	border-right: 1px solid #000000;
	border-bottom: 1px dashed #BBBBBB;
}

.overflowRulerX>.firebugRulerV {
	left: 0;
}

.overflowRulerY>.firebugRulerH {
	top: 0;
}

.firebugLayoutBoxOffset {
	z-index: 2147483647;
	position: absolute;
	opacity: 0.8;
}

.firebugLayoutBoxMargin {
	background-color: #EDFF64;
}

.firebugLayoutBoxBorder {
	background-color: #666666;
}

.firebugLayoutBoxPadding {
	background-color: SlateBlue;
}

.firebugLayoutBoxContent {
	background-color: SkyBlue;
}

.firebugLayoutLine {
	z-index: 2147483647;
	background-color: #000000;
	opacity: 0.4;
}

.firebugLayoutLineLeft,.firebugLayoutLineRight {
	position: fixed;
	width: 1px;
	height: 100%;
}

.firebugLayoutLineTop,.firebugLayoutLineBottom {
	position: absolute;
	width: 100%;
	height: 1px;
}

.firebugLayoutLineTop {
	margin-top: -1px;
	border-top: 1px solid #999999;
}

.firebugLayoutLineRight {
	border-right: 1px solid #999999;
}

.firebugLayoutLineBottom {
	border-bottom: 1px solid #999999;
}

.firebugLayoutLineLeft {
	margin-left: -1px;
	border-left: 1px solid #999999;
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
</head>
<div style="width: 960px; margin: 0 auto;">
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


<a href="/index.jsf"><img height="100" width="219"
	title="individual" class="logo" alt="individual"
	src="../images/i-wisdom.png" /></a> <br class="spacer" />
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
</html>