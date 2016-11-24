<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>全屏节点状态</title>
    
	<!-- link rel="stylesheet" type="text/css" href="extjs/ux/css/ItemSelector.css" /-->
	<!-- ExtJS4 基础文件  -->
	<link rel="stylesheet" type="text/css" href="extjs/resources/ext-theme-gray/ext-theme-gray-all.css" />
	<!-- <link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all-neptune.css" /> -->
	<script type="text/javascript" src="extjs/bootstrap.js"></script>
	<script type="text/javascript" src="extjs/ux/form/MultiSelect.js"></script>
	<script type="text/javascript" src="extjs/ux/form/ItemSelector.js"></script>
	<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
	 <link rel="stylesheet" type="text/css" href="extjs/shared/example.css"/>
	<!--script type="text/javascript" src="http://12.40.10.3:8980/load?v=1.5"></script--> 
	<!-- 应用系统 -->
	<!-- link rel="stylesheet" type="text/css" href="images/icons.css" /><!-- 图标 -->
	<link rel="stylesheet" type="text/css" href="css/webapp.css" /><!-- 自定义样式 -->
	<script type="text/javascript" src="webapp/Main1.js"></script>
	<script type="text/javascript" src="webapp/utils/Msg.js"></script>
	<script type="text/javascript" src="webapp/utils/WinUtils.js"></script>
	<script type="text/javascript" src="webapp/utils/StringUtils.js"></script>
	<!-- 
	<script type="text/javascript" src="js/util.js"></script> -->
	<script type="text/javascript" src="d3/d3.js"></script>
	<script type="text/javascript" src="d3/topojson.v1.min.js"></script>
	<script type="text/javascript" src="extjs/example-data.js"></script>
	<script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script>
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		Ext.onReady(Ushine.Main1.init);
	</script>

  </head>
  
  <body>
    <div id=""></div>
  </body>
</html>
