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
<th><input type="checkbox"></th><th>服务名称</th><th>申请时间</th><th>当前状态</th><th>有效期限</th><th>操作</th>
</tr>
<ww:iterator value="userSystemServices" status="status">
<tr>
<td><input type="checkbox"></td>
<td><ww:property value="userSystemServices.get(#status.index).systemService.serviceName"/></td>
<td><ww:property value="userSystemServices.get(#status.index).createDate"/></td>
<td>正在审核</td>
<td>2009-08-31</td>
<td><a href="">取消</a>&nbsp;<a href="/cms/publishcontent.do">查看</a></td>
</tr>
</ww:iterator>
<tr>
<td colspan="10">
<a href="">全选</a>&nbsp;<a href="">取消选中服务</a>
</td>
</tr>
</table>
</div>
</body>
</html>