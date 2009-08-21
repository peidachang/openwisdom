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
</script>
<div id="content" style="font-size:12px;padding-top:4px">
<ww:text name="cms.content.search.label.keyword"/><input type="text" name="resurcename">
<ww:text name="cms.content.label.type"/>
<select name="contentMainTypeId" id="contentMainTypeId"
			onchange="showSubTypes()">
			<option value="-1"><ww:text name='cms.content.label.type'/></option>
			<ww:iterator value="contentMainTypes" status="status">
				<option
					value="<ww:property value='contentMainTypes.get(#status.index).contentMainTypeId'/>">
				<ww:property value='contentMainTypes.get(#status.index).labels' /></option>
			</ww:iterator>
		</select>
<input type="button" value="<ww:text name='page.button.search'/>">
<br>
<h3><ww:text name="page.cms.contents.pendding"/></h3>
<table class="stripe" width="80%" border="1" cellspacing="0" cellpadding="0"> 
<thead>
  <tr>
    <th><ww:text name="page.cms.contents.content.id"/></th>
    <th><ww:text name="page.cms.contents.content.name"/></th>
    <th><ww:text name="page.cms.contents.content.status"/></th>
    <th><ww:text name="page.cms.contents.content.version"/></th>
    <th><ww:text name="page.cms.contents.content.description"/></th>
    <th><ww:text name="page.cms.contents.content.maintype"/></th>
    <th><ww:text name="page.common.label.operate"/></th>
  </tr>
</thead>
<tbody>
<ww:iterator value="contents" status="status">
  <tr>
    <td><ww:property value="contents.get(#status.index).contentId"/></td>
    <td><ww:property value="contents.get(#status.index).contentName"/></td>
    <td>
    <ww:if test="contents.get(#status.index).status==0">
    <ww:text name="cms.content.status.0"/>
    </ww:if>
    <ww:else><ww:text name="cms.content.status.1"/></ww:else>
    </td>
    <td><ww:property value="contents.get(#status.index).version"/></td>
    <td><ww:property value="contents.get(#status.index).description"/></td>
    <td><a href=""><ww:property value="contents.get(#status.index).contentMainType.labels"/></a></td>
    <td><a href=""><ww:text name="page.common.label.operate.edit"/></a> <a href=""><ww:text name="page.common.label.operate.delete"/></a></td>
  </tr>
</ww:iterator>
</tbody>
</table>
</div>
</body>
</html>