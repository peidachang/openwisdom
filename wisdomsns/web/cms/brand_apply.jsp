<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
	
<div id="publish_form" align="center">
<form action="/cms/savebrand.do" enctype="multipart/form-data" method="post">
<table>
<tr>
<td>品牌大类</td>
<td>
<select name="brandMainTypeId" id="brandMainTypeId">
			<option value="-1">选择品牌大类</option>
			<ww:iterator value="brandMainTypes" status="status">
				<option
					value="<ww:property value='BrandMainTypes.get(#status.index).brandMainTypeId'/>">
				<ww:property value='BrandMainTypes.get(#status.index).brandMainTypeName' /></option>
			</ww:iterator>
		</select>
</td>
</tr>
<tr>
<td>所属行业</td>
<td>
<select name="brandCategoryId" id="brandCategoryId">
			<option value="-1">选择品牌所属行业</option>
			<ww:iterator value="brandCategories" status="status">
				<option
					value="<ww:property value='brandCategories.get(#status.index).brandCategoryId'/>">
				<ww:property value='brandCategories.get(#status.index).brandCategoryName' /></option>
			</ww:iterator>
		</select>
		</td>
</tr>
<tr>
<td>品牌名称</td><td><input type="text" name="brand.brandName" value="${brand.brandArea }"></td>
</tr>
<tr>
<td>所属地区</td><td><input type="text" name="brand.brandArea" value="${brand.brandName }"></td>
</tr>
<tr>
<td>品牌标志</td><td><input type="file" name="brandLogos" id="brandLogos">【.jpg,.png,.gif...】</td>
</tr>
<tr>
<td>品牌简介</td><td><textarea cols="25" rows="3" name="brand.brandSummary"></textarea></td>
</tr>
<tr>
<td>&nbsp;</td><td><input type="submit" value="提交"></td>
</tr>
</table>
</form>
</div>
</body>
</html>