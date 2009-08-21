<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Vote</title>
<link rel="stylesheet" type="text/css" href="/js/ext-2.0/resources/css/ext-all.css" />
<script type="text/javascript" src="/js/ext-2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/js/ext-2.0/ext-all.js"></script>

<script type="text/javascript" src="/vote/js/popupwindow.js"></script>
</head>
<br>
<div id="vote_index">
<div id="topic" style="display: none;"><span id="title">公众投票</span>-参与你关心的问题的讨论和听证会...</div>

<div id="voteList"><span
	style="margin-left: 19px; margin-bottom: 3px; font-size: 15px; font-weight: bold;">
最受关注的热门话题</span> <a href="/account/index.jsf">创建新的讨论话题</a>
<ul>
	<li><a href="javascript:show_('铁路')">沪昆高铁湖南段走向问题?</a></li>
	<li><a href="javascript:show_('高校')">高校收费问题?</a></li>
	<li><a href="javascript:show_('三农')">三农政策?</a></li>
	<li><a href="javascript:show_('就业')">大学生就业</a></li>
</ul>


<span
	style="margin-left: 19px; margin-bottom: 3px; font-size: 15px; font-weight: bold;">
最新话题</span>
<ul>
	<li><a href="javascript:show_('环境')">环境问题</a></li>
	<li><a href="javascript:show_('高校')">娱乐</a></li>

</ul>
</div>
</div>
<div id="helloBtn"></div>

</html>
