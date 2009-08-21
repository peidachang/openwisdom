<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ww" uri="/webwork"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>群组菜单管理</title>
<style>
body {
	font-size: 12px
}

.{
font-family
:arial
;font-size:12px
}
h1 {
	cursor: hand;
	font-size: 16px;
	margin-left: 10px;
	line-height: 10px
}

xmp {
	color: green;
	font-size: 12px;
	margin: 0px;
	font-family: courier;
	background-color: #e6e6fa;
	padding: 2px
}

.hdr {
	background-color: lightgrey;
	margin-bottom: 10px;
	padding-left: 10px;
}
</style>

</head>

<body>
<link rel="stylesheet" type="text/css" href="/style/dhtmlXTree.css">
<script src="/js/dhtmlxtree/dhtmlXCommon.js"></script>
<script src="/js/dhtmlxtree/dhtmlXTree.js"></script>
<script src="/js/dhtmlxtree/dhtmlXTreeExtend.js"></script>
<script>
	function tonclick(id){
				alert("Item "+tree.getItemText(id)+" was selected");
			};
			
			function tonopen(id,mode){
				return confirm("Do you want to "+(mode>0?"close":"open")+" node "+tree.getItemText(id)+"?");
			};
			function toncheck(id,state){
				alert("Item "+tree.getItemText(id)+" was " +((state)?"checked":"unchecked"));
			};
			function test(v){
			alert(v+":"+v.length);
			alert(tree2.getParentId(v))
			}
			function save(v){
			alert(v);
			document.getElementById("menuIdString").value = v ;
			document.form1.submit();
			}
			function check(e){
			tree1.loadXML("/data/menu/menu_"+e.target.value+".xml");
			tree1.refreshItem(0);
			}
	</script>
<form name="form1" action="admin!addMenus.do" method="post">
<div>
<fieldset style="padding: 8px; width: 400px; border: 1px solid #CC0000; color: #CC3300; line-height: 2.0; font-size: 12px;">
		<legend>群组</legend>
<ww:iterator value="roles" status="status">
	<input type="radio" name="roleId" id="roleId" onclick="check(event);"
		value="<ww:property value='roles.get(#status.index).roleId'/>">
	<ww:property value="roles.get(#status.index).roleName" />
</ww:iterator>
</fieldset>
</div>
<div class="hdr">菜单数据</div>

<table>

	<tr>
		<td valign="top">
		<div id="menudata"
			style="width: 300px; height: 218px; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;"></div>
		</td>
		<td valign="center">
		>>
		<br>
		<<
		</td>
		<td style="padding-left: 25" valign="top">
		<div id="groupmenu"
			style="width: 300px; height: 218px; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;"></div>
		</td>
	</tr>
</table>
<hr>
<script>
			tree2=new dhtmlXTreeObject("menudata","100%","100%",0);
			tree2.setImagePath("images/");
			tree2.enableCheckBoxes(1);
			tree2.enableThreeStateCheckboxes(true);
			tree2.enableSmartXMLParsing(true);
			tree2.enableDragAndDrop(true);
			tree2.setDragBehavior("sibling");
			tree2.loadXML("/data/menu/menu.xml");
			
			tree1=new dhtmlXTreeObject("groupmenu","100%","100%",0);
			tree1.setImagePath("images/");
			tree1.enableCheckBoxes(1);
			tree1.enableThreeStateCheckboxes(true);
			tree1.loadXML("/data/menu/menu_3.xml");
						
	</script>
	<input type="hidden" name="menuIdString" id="menuIdString" value="">
	<input type="button" value="Save" onclick="save(tree2.getAllChecked())">
	</form>
</body>
</html>