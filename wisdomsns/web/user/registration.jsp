<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="ww" uri="/webwork"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><ww:text name='page.title.registration' /></title>
<link href="${pageContext.request.contextPath}/style/style.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/js/ext/resources/css/ext-all.css" />
<link href="${pageContext.request.contextPath}/style/custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/ext/adapter/ext/ext-base.js"></script>  
<script type="text/javascript" src="/js/ext/ext-all.js"></script>  
<script type="text/javascript">  
Ext.onReady(function(){  
    var md = new Ext.form.DateField({  
      name: 'user.birthday',  
      width: 110,  
      format: 'Y-m-d',  
      emptyText: '请选择日期 ...'   
     });  
     md.render('divDate');   
});  
</script>  
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/interface/utilinvoke.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/dwr/util.js'></script>
<script type="text/javascript">
				function selectProvince(){
				var p = document.getElementById("province").value ;
				utilinvoke.getProvinceCities(p,back);
				}
				function back(data){
				DWRUtil.removeAllOptions("city");
				DWRUtil.removeAllOptions("district");
				DWRUtil.addOptions("city",{"-10000":"<ww:text name='page.registration.label.city'/>"});
				DWRUtil.addOptions("district",{"-10000":"<ww:text name='page.registration.label.district'/>"});
				DWRUtil.addOptions("city",data,"CITY_CARD_PREFIX","CITY_CN_NAME");
				}
				function selectCity(){
				var p = document.getElementById("city").value ;
				utilinvoke.getCityDistricts(p,back1);
				}
				function back1(data){
				DWRUtil.removeAllOptions("district");
				DWRUtil.addOptions("district",{"-10000":"<ww:text name='page.registration.label.district'/>"});
				DWRUtil.addOptions("district",data,"DISTRICT_CARD_PREFIX","DISTRICT_CN_NAME");
				}
				function setlocation(){
				var p = document.getElementById("province").value ;
				var c = document.getElementById("city").value ;
				var d = document.getElementById("district").value ;
				
				}
				function submit1(){
				var p = document.getElementById("province").value ;
				var c = document.getElementById("city").value ;
				var d = document.getElementById("district").value ;
				var location = document.getElementById("user.location");
				if(d!="-10000"){
				location.value = d ;
				}else if(c!="-10000"){
				location.value = c ;
				}else if(p!="-10000"){
				location.value = p ;
				}
				return true;
				}
</script>

</head>
<body>
<form action="saveuser.do" method="post" onsubmit="return submit1();">
<table align="center" width="60%" border="0" cellspacing="3"
	cellpadding="3">
	<tr>
		<td class="td1">用户类型
		</td>
		<td class="td2">
		<select name="groupId" id="groupId">
			<option value="-1"><ww:text name='cms.content.label.type'/></option>
			<ww:iterator value="groups" status="status">
				<option
					value="<ww:property value='groups.get(#status.index).groupId'/>">
				<ww:property value='groups.get(#status.index).groupName' /></option>
			</ww:iterator>
		</select>
		</td>
	</tr>
	<tr>
		<td class="td1"><ww:text name="page.registration.form.email"></ww:text>
		</td>
		<td class="td2"><ww:textfield name="user.email" theme="simple"
			value="${user.email }" /><span id="loading" style=""><img
			src="/images/loading.gif"></span></td>
	</tr>
	<tr>
		<td class="td1"><ww:text name="page.registration.form.mobile"></ww:text></td>
		<td class="td2"><ww:textfield name="user.mobile" theme="simple"
			value="${user.mobile }" /></td>
	</tr>
	
	<tr>
		<td class="td1"><ww:text name="page.registration.form.nickname"></ww:text></td>
		<td class="td2"><ww:textfield name="user.userName" theme="simple"
			value="${user.userName }" /></td>
	</tr>
	<tr>
		<td class="td1"><ww:text name="page.registration.form.password"></ww:text></td>
		<td class="td2"><ww:password name="user.password" theme="simple"
			value="${user.password }" /></td>
	</tr>
	<tr>
		<td class="td1"><ww:text name="page.registration.form.gender"></ww:text></td>
		<td class="td2"><input type="radio" name="user.gender" value="0" /><ww:text
			name="page.registration.form.gender.male" /> <input type="radio"
			name="user.gender" value="1" /><ww:text
			name="page.registration.form.gender.female" /></td>
	</tr>

	<tr>
		<td class="td1"><ww:text name="page.registration.form.birthday"></ww:text></td>
		<td class="td2">
			<div id='divDate'></div>
			</td>
	</tr>

	<tr>
		<td class="td1"><ww:text name="page.registration.form.location"></ww:text>
		</td>
		<td class="td2"><ww:select theme="simple" name="province"
			id="province" list="provinces" listKey="PROVINCE_CARD_PREFIX"
			listValue="PROVINCE_CN_NAME" headerKey="-10000"
			headerValue="%{getText('page.registration.label.province')}"
			onchange="selectProvince()">
		</ww:select> <select name="city" id="city" onchange="selectCity()">
			<option value="-10000"><ww:text
				name="page.registration.label.city"></ww:text></option>
		</select> <select name="district" id="district">
			<option value="-10000"><ww:text
				name="page.registration.label.district"></ww:text></option>
		</select> <ww:hidden name="user.location" id="user.location"
			value="${user.location}"></ww:hidden></td>
	</tr>

	<tr>
		<td class="td1">&nbsp;</td>
		<td class="td2"><input type="submit" onclick="submit1()"
			value="<ww:text name='page.button.signup'/>" /></td>
	</tr>
</table>

</form>

</body>

</html>
