<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌中心</title>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	function openWindow(){
	window.open("/brand/brandSummary.do","width:500;height:400");
	}
	</script>
	
		
	
	
</head>
<body>
<div id="brand_show">
<table border="1" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<th class="content_header">公共品牌</th>
		<th class="content_header">热门关注</th>
	</tr>

	<tr>
		<td valign="top">
		<div class="brandshow">
		<table class="stripe" width="100%" border="0">
			<tbody>
			<ww:iterator value="brands" status="status">
				<ww:if test="brands.get(#status.index).brandMainType.brandMainTypeId==1">
				<tr>
					<td width="40%"><a href=""><img width="60" height="50"
						src="<ww:property value="brands.get(#status.index).brandLogos"/>" /></a>
						<br><a href="/brand/branddetail.do?brand.brandId=<ww:property value="brands.get(#status.index).brandId"/>"><ww:property value="brands.get(#status.index).brandName"/></a>
						</td>
					<td><a href="">品牌介绍</a>&nbsp;<a href="">关注此品牌</a></td>
				</tr>
			</ww:if>
			</ww:iterator>
			</tbody>
		</table>

		</div>
		</td>
		<td rowspan="3" width="40%">
		<div>
		<ul>
			<li><a href="">首都机场</a></li>
			<li><a href="">星巴克</a></li>
			<li><a href="">北京火车西站</a></li>
			<li><a href="">深圳机场</a></li>
		</ul>
		</div>
		</td>
	</tr>

	<tr>
		<th class="content_header">企业品牌</th>
	</tr>

	<tr>
		<td>
		<div class="brandshow">
		<table class="stripe" width="100%" border="0">
			<tbody>
				<ww:iterator value="brands" status="status">
				<ww:if test="brands.get(#status.index).brandMainType.brandMainTypeId==2">
				<tr>
					<td width="40%"><a href=""><img width="60" height="50"
						src="<ww:property value="brands.get(#status.index).brandLogos"/>" /></a>
						<br><a href="/brand/branddetail.do?brand.brandId=<ww:property value="brands.get(#status.index).brandId"/>"><ww:property value="brands.get(#status.index).brandName"/></a>
						</td>
					<td><a href="">品牌介绍</a>&nbsp;<a href="">关注此品牌</a></td>
				</tr>
				</ww:if>
				</ww:iterator>
				
			</tbody>
		</table>
		</div>
		</td>
	</tr>

</table>
</div>
</body>
</html>