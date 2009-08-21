<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />

<script language="javascript" src="../js/jquery-1.3.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
        $(".stripe tr").mouseover(function(){
                $(this).addClass("over");}).mouseout(function(){
                $(this).removeClass("over");})
        $(".stripe tr:even").addClass("alt");
});

function test(){
alert("test");
}
</script>
<div id="cms_header"><ww:text
	name="cms.content.search.label.keyword" /><input type="text"
	name="resurcename"> 所属行业 <select name="brandCategoryId"
	id="brandCategoryId">
	<option>互联网</option>
	<option>软件</option>
	<option>电子</option>
	<option>汽车</option>
	<option>餐饮</option>
</select> <input type="button" value="<ww:text name='page.button.search'/>">
</div>
<div id="content" style="font-size: 12px; padding-top: 4px">
<table class="stripe" border="1" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<th><input type="checkbox" name="all"></th>
			<th>品牌编号</th>
			<th>品牌名称</th>
			<th>品牌Logos</th>
			<th>所属地区</th>
			<th>所属行业</th>
			<th>品牌大类</th>
			<th>品牌状态</th>
			<th>有效日期</th>
			<th>操作</th>

		</tr>

		<ww:iterator value="brands" status="status">
			<tr>
				<td><input type="checkbox"></td>
				<td><ww:property value="brands.get(#status.index).brandId" /></td>
				<td><ww:property value="brands.get(#status.index).brandName" /></td>
				<td><a href="" onmouseover="test()"><img width="20"
					height="16"
					src="<ww:property value="brands.get(#status.index).brandLogos"/>" /></a></td>
				<td><ww:property value="brands.get(#status.index).brandArea" /></td>
				<td><ww:property
					value="brands.get(#status.index).brandCategory.brandCategoryName" /></td>
				<td><ww:property
					value="brands.get(#status.index).brandMainType.brandMainTypeName" /></td>
				<td><ww:if
					test="brands.get(#status.index).status==0">
					<ww:text name="cms.content.status.0" />
				</ww:if> <ww:else>
					<ww:text name="cms.content.status.1" />
				</ww:else>
				</td>
				<td><ww:property value="brands.get(#status.index).expireDate" /></td>
				<td><a href="">编辑</a>&nbsp;<a href="">删除</a></td>
			</tr>
		</ww:iterator>
	</tbody>
</table>
</div>
</body>
</html>