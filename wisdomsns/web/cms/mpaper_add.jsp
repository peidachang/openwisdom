<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="/js/ext/resources/css/ext-all.css" />
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/ext/adapter/ext/ext-base.js"></script>  
<script type="text/javascript" src="/js/ext/ext-all.js"></script>  
<script type="text/javascript">  
Ext.onReady(function(){  
    var md = new Ext.form.DateField({  
      name: 'testDate',  
      width: 110,  
      format: 'Y-m-d',  
      emptyText: '请选择日期 ...'   
     });  
       
     md.render('divDate');   
});  
</script>  

<div id="cms_header">新增手机资讯</div>
<div id="publish_form">
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>资讯标题</td>
		<td><input type="text" name="mpaper_name"></td>
		<td>资讯类别</td>
		<td><select>
			<option>财经</option>
		</select></td>
	</tr>
	<tr>
	<td>发送日期</td>
	<td><div id='divDate'></div></td>
	<td>发送对象</td>
	<td>
	<select>
	<option>定制用户</option>
	<option>随机试用用户(1000人)</option>
	</select>
	</td>
	</tr>
	<tr>
		<td>资讯主体</td>
		<td colspan="3"><textarea cols="60" rows="7"></textarea></td>
	</tr>

	<tr>
		<td colspan="4"><input type="button" value="预览"> <input
			type="button" value="保存为短信"> <input type="button"
			value="保存为彩信"> <input type="button" value="重置"></td>
	</tr>
</table>
</div>
</body>
</html>