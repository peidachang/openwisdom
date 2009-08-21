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
<link href="/style/custom.css"
	rel="stylesheet" type="text/css" />
<div style="margin-top:20px;">
<table cellpadding="3" cellspacing="0" border="1" bordercolor="#c6c6c6" width="100%">
<tr bgcolor="#F0F8FF">
<th><input type="checkbox"></th><th>服务名称</th><th>服务简介</th><th>价格</th><th>免费期限</th><th>当前版本</th><th>当前状态</th><th>提供者</th><th>有效期限</th><th>操作</th>
</tr>
<ww:iterator value="services" status="status">
<tr>
<td><input type="checkbox" name="serviceId" value="<ww:property value="services.get(#status.index).serviceId"/>"></td>
<td><ww:property value="services.get(#status.index).serviceName"/></td>
<td><ww:property value="services.get(#status.index).serviceDesc"/></td>
<td><ww:property value="services.get(#status.index).price"/></td>
<td><ww:property value="services.get(#status.index).freeCredit"/></td>
<td><ww:property value="services.get(#status.index).version"/></td>
<td><ww:property value="services.get(#status.index).status"/></td>
<td><ww:property value="services.get(#status.index).provider.userName"/></td>
<td><ww:property value="services.get(#status.index).expireDate"/></td>
<td><a href="">开通</a>&nbsp;<a href="">查看</a></td>
</tr>
</ww:iterator>
<tr>
<td colspan="10">
<a href="">全选</a>&nbsp;<a href="">开通选中服务</a>
</td>
</tr>
</table>
</div>

</body>
</html>