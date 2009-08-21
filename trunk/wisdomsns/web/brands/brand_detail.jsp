<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />

<div>
<table>

<tr>
<td>
<span><img width="100" height="90" src="<ww:property value="brand.brandLogos"/>" /></span>
<span>
<ww:property value="brand.brandSummary"/>
</span>
</td>
</tr>
<tr>
<th class="content_header">品牌关注点</th>
</tr>
<tr>
<td>
<select>
<option>航班信息</option>
</select>
<table width="100%" border="1">
<tr>
<td>航班号</td><td>机型</td><td>票价</td><td>起飞城市</td><td>到达城市</td><td>起飞时间</td><td>到达时间</td><td>历时</td><td>航空公司</td>
</tr>
<tr>
<td>BJ9875</td><td>A320</td><td>758</td><td>北京</td><td>上海</td><td>17:29</td><td>20:10</td><td>2.41</td><td>东方航空</td>
</tr>
<tr>
<td>SA9875</td><td>波音747</td><td>879</td><td>上海</td><td>深圳</td><td>11:40</td><td>15:37</td><td>3.53</td><td>南方航空</td>
</tr>
</table>
</td>
</tr>


<tr>
<th class="content_header">品牌互动</th>
</tr>
<tr>
<td>
<div>
<ul>
<li>品牌创意大赛</li>
<li>品牌投诉</li>
<li>品牌建议</li>
</ul>
</div>
</td>
</tr>

<tr>
<th class="content_header">相关品牌资讯</th>
</tr>
<tr>
<td><h3>...</h3></td>
</tr>

<tr>
<th class="content_header">品牌社群</th>
</tr>
<tr>
<td><h3>...</h3></td>
</tr>
</table>
</div>
</body>
</html>