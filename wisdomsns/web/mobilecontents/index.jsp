<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>移动内容</title>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function display(id){
	if(document.getElementById(id).style.display=="")
	{
		document.getElementById(id).style.display="none";
		document.getElementById(id+"_link").innerHTML = "+";
	}
	else
	{
		document.getElementById(id).style.display="";
		document.getElementById(id+"_link").innerHTML = "-";
	}
	}
	</script>
</head>
<body>
手机软件下载，在线游戏,自定义手机报，自己选择组合形成，可以和朋友共享
<div id="content_show">
<table width="100%" border="0" cellpadding="3" cellspacing="2">
	<tbody>
		<tr>
			<th class="content_header"><a id="info_link"
				href="javascript:display('info')">-</a>热门资讯
				<a style="padding-left:650px;" href="/mobile/mobilenews.do">全部资讯</a></th>
		</tr>
		<tr id="info">
			<td>
			<ul>
				<li><a href="" title="环球时报">伊朗局势1</a>-2009-06-22</li>
				<li><a href="" title="环球时报">伊朗局势2</a>-2009-06-22</li>
				<li><a href="" title="环球时报">伊朗局势3</a>-2009-06-22</li>
				<li><a href="" title="环球时报">伊朗局势4</a>-2009-06-22</li>
			</ul>
			</td>
		</tr>

		<tr>
			<th class="content_header"><a id="ringtone_link"
				href="javascript:display('ringtone')">-</a>热门铃音
			<a style="padding-left:650px;" href="">全部铃音</a></th>
		</tr>
		<tr id="ringtone">
			<td>
			<ul>
				<li><a href="" title="光良">童话</a></li>
				<li><a href="" title="黄家驹">大地</a></li>
				<li><a href="" title="刘德华">练习</a></li>
				<li><a href="" title="Westboy">Better Man</a></li>
				<li><a href="" title="刘德华">男人哭吧不是罪</a></li>
				<li><a href="" title="Westboy">Never have a dream come true</a>
				</li>
			</ul>
			</td>
		</tr>
		<tr>
			<th class="content_header"><a id="video_link"
				href="javascript:display('video')">-</a>热门视频
				<a style="padding-left:650px;" href="">全部视频</a></th>
		</tr>
		<tr id="video">
			<td>
			<ul>
				<li><a href="">变形金刚花絮</a></li>
				<li><a href="">赤壁</a></li>
				<li><a href="">钢琴教程</a></li>
			</ul>
			</td>
		</tr>
		<tr>
			<th class="content_header"><a id="wallpaper_link"
				href="javascript:display('wallpaper')">-</a>热门墙纸
				<a style="padding-left:650px;" href="">全部壁纸</a></th>
		</tr>
		<tr id="wallpaper">
			<td>
			<ul>
				<li><a href=""><img
					src="/data/contentdatas/wallpapers/338004_128x128.gif" /></a></li>
				<li><a href=""><img
					src="/data/contentdatas/wallpapers/56881_128x128.gif" /></a></li>
				<li><a href=""><img
					src="/data/contentdatas/wallpapers/58529_128x128.gif" /></a></li>
			</ul>
			</td>
		</tr>


		<tr>
			<th class="content_header"><a id="game_link"
				href="javascript:display('game')">-</a>热门游戏&nbsp;&nbsp;
				<a style="padding-left:650px;" href="">全部游戏</a></th>
		</tr>
		<tr id="game">
			<td>
			<ul>
				<li><a href=""><img
					src="/data/contentdatas/gamephotos/eng_BattleOfEmpires2_4f_en.gif" /></a></li>
				<li><a href=""><img
					src="/data/contentdatas/gamephotos/eng_KOKickboxing2_en_4f.gif" /></a></li>
				<li><a href=""><img
					src="/data/contentdatas/gamephotos/eng_MagicMahjong_128x128_4f_en.gif" /></a>
				</li>
				<li><a href=""><img
					src="/data/contentdatas/gamephotos/eng_QuestCraft_4f_en.gif" /></a></li>
				<li><a href=""><img
					src="/data/contentdatas/gamephotos/eng_Wiz_LostInHalloween_ani_en.gif" /></a>
				</li>
			</ul>
			</td>
		</tr>
		<tr>
			<th class="content_header"><a id="apps_link"
				href="javascript:display('apps')">-</a>热门应用软件
				<a style="padding-left:580px;" href="">全部手机应用软件</a></th>
		</tr>
		<tr id="apps">
			<td>
			<ul>
				<li><a href="">手机QQ</a></li>
				<li><a href="">掌上理财助手</a></li>
				<li><a href="">网上银行手机版</a></li>
				<li><a href="">手机PDF阅读器</a></li>
				<li><a href="">文档管理</a></li>
			</ul>
			</td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>