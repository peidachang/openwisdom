<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/contentService.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>

<script type="text/javascript">
			function showSubTypes(){
				var mainTypeId = document.getElementById("contentMainTypeId").value;
				if(mainTypeId!=null && mainTypeId!="")
				{	
					
					contentService.getContentSubTypes(mainTypeId,back);
				}
			}
			function back(data){

				DWRUtil.removeAllOptions("contentSubType");
				DWRUtil.addOptions("contentSubType",{"-1":"<ww:text name='cms.content.label.subtype'/>"});
				DWRUtil.addOptions("contentSubType",data,"contentSubTypeId","labels");

			}
</script>
<br>
<div align="center" id="publish_form">
<form action="/cms/savecontent.do" enctype="multipart/form-data" method="post">
<table border="0" cellpadding="2" cellspacing="0">
	<tr>
		<td><ww:text name='cms.content.label.type'/></td>
		<td><select name="contentMainTypeId" id="contentMainTypeId"
			onchange="showSubTypes()">
			<option value="-1"><ww:text name='cms.content.label.type'/></option>
			<ww:iterator value="contentMainTypes" status="status">
				<option
					value="<ww:property value='contentMainTypes.get(#status.index).contentMainTypeId'/>">
				<ww:property value='contentMainTypes.get(#status.index).labels' /></option>
			</ww:iterator>
		</select>
		
		<ww:text name='cms.content.label.subtype'/>
		<select name="content.contentSubTypeId" id="contentSubType">
			<option value="-1"><ww:text name='cms.content.label.subtype'/></option>
		</select>
		<ww:text name='cms.content.label.needtype'/>
		</td>
	</tr>
	
	<!-- 
	<tr>
		<td>版权</td>
		<td><input type="file" name="copyrightfile" id="copyrightfile" /></td>
	</tr>
	 -->
	<tr>
		<td><ww:text name='cms.content.label.file'/></td>
		<td><input type="file" size="30" name="contentfile" id="contentfile" /></td>
	</tr>
	<tr>
		<td><ww:text name='cms.content.label.contentname'/></td>
		<td><ww:textfield theme="simple" name="content.contentName"id="contentName" /><ww:text name='cms.content.label.contentname.attention'/></td>
	</tr>
	<tr>
		<td><ww:text name='cms.content.label.contentlogos'/></td>
		<td><input type="file" size="30" name="contentfile" id="contentfile" /></td>
	</tr>
	<tr>
		<td><ww:text name='cms.content.label.contentlabels'/></td>
		<td><input type="file" size="30" name="contentfile" id="contentfile" /></td>
	</tr>
	<tr>
		<td valign="top"><ww:text name='cms.content.label.description'/></td>
		<td><ww:textarea cols="30" rows="5" theme="simple" name="content.description"
			id="description" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" value="<ww:text name='page.button.publish'/>"> <a href="">批量发布引擎</a></td>
	</tr>
</table>
</form>
</div>
</body>
</html>