<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />

<script language="javascript" src="/js/jquery-1.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
        $(".stripe tr").mouseover(function(){
                $(this).addClass("over");}).mouseout(function(){
                $(this).removeClass("over");})
        $(".stripe tr:even").addClass("alt");
});
</script>
</head>
<body>
<div id="content_show">
<table border="1" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<th height="30px" class="content_header">热门订阅资讯</th>
		<td rowspan="4" width="40%">
		<div id="mobilepaperphotos">
		<ul>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/hzrb.jpg" /></a></li>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/hqjs.jpg" /></a></li>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/21cj.jpg" /></a></li>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/tyxw.jpg" /></a></li>
				
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/cxp_1.jpg" /></a></li>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/cxp_3.jpg" /></a></li>
			<li><a href=""><img
				src="/data/contentdatas/mobilepaperphotos/cxp_4.jpg" /></a></li>
		</ul>
		</div>
		</td>
	</tr>

	<tr>
		<td valign="top">
		<table class="stripe" width="100%" border="0">
			<tbody>
				<tr>
					<td>1</td>
					<td>H1N1流感最新报道</td>
				</tr>
				<tr>
					<td>2</td>
					<td>伊朗国内暴乱</td>
				</tr>
				<tr>
					<td>3</td>
					<td>欧冠杯联赛</td>
				</tr>
			</tbody>
		</table>
		</td>
	</tr>

	<tr>
		<th height="30px" class="content_header">热门资讯列表</th>
	</tr>

	<tr>
		<td>3</td>
	</tr>

</table>
</div>
</body>
</html>