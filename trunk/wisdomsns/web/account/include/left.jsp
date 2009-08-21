<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<html>
<head>
<title>New Document</title>
<link rel="stylesheet" type="text/css"
	href="../js/ext-2.0/resources/css/ext-all.css" />

<!-- GC -->
<!-- LIBS -->
<script type="text/javascript"
	src="../js/ext-2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->

<script type="text/javascript" src="../js/ext-2.0/ext-all.js"></script>

</head>

<body>
<script>
Ext.onReady(function(){
          new Ext.Panel({
                renderTo:'show',
                title:'个人管理中心',
                width:200,
                height:560,
                layout:'accordion',
                layoutConfig: {
                     animate: true 
                },
                items:[{
                         title:'服务项一',
                         html:'<span><a href="${pageContext.request.contextPath}/account/test.jsf">测试1</a></span><br><span><a href="">测试2</a></span><br><span><a href="">测试3</a></span><br>'
                        }, {
                         title:'服务项二',
                         html:'测试二'
                        }]
          });
});

   </script>

</body>
</html>