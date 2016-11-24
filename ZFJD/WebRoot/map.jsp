<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String code = request.getParameter("code");
	String size = request.getParameter("size");
	String cx = request.getParameter("cx");
	String cy = request.getParameter("cy");
	System.out.println(code);
	System.out.println(size);
	System.out.println(cx);
	System.out.println(cy);
	
	if(code==null||code.equals("")){
		code = "";
	}
	if(size==null||size.equals("")){
		if(code.length()==6){
			size = "0";
		}else{
			size = "750";
		}
	}else if(code.length()==2){
		size = (Integer.parseInt(size) * 2.7) + "";
	}
	
	String dataPath = "";
	if(code.equals("")){
		dataPath = "map_data/china.json";
	}else if(code.length()==2){
		dataPath = "map_data/geometryProvince/"+code+".json";
	}else{
		dataPath = "map_data/geometryCouties/"+code+".json";
	}
	if(cx==null||cx.equals("")){
		cx = "105";
	}
	if(cy==null||cy.equals("")){
		cy = "38";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="extjs/bootstrap.js"></script>
	<script type="text/javascript" src="extjs/ux/form/MultiSelect.js"></script>
	<script type="text/javascript" src="extjs/ux/form/ItemSelector.js"></script>
	<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="css/webapp.css" /><!-- 自定义样式 -->
	<script type="text/javascript" src="webapp/Main.js"></script>
	<script type="text/javascript" src="webapp/utils/Msg.js"></script>
	<script type="text/javascript" src="webapp/utils/WinUtils.js"></script>
	<script type="text/javascript" src="webapp/utils/StringUtils.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css"  />
<script type="text/javascript" src="d3/d3.js"></script>
<script type="text/javascript" src="d3/topojson.v1.min.js"></script>
<script type="text/javascript">
var dataPath = '<%=dataPath%>';
var size = <%=size%>;
var cx = <%=cx%>;
var cy = <%=cy%>;

</script>
</head>
<body>

	<div id="div" style="border:1px solid red;">
		<div class="tooltip">
			<span id="tooltip"></span>
		</div>
		
		<div class="southsea">
			<span id="southsea"></span>
		</div>
	</div>
</body>

<script type="text/javascript" src="webapp/map/map1.js"></script>
</html>