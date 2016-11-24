/**
 *	主页
 * @author 王百林
 */
var dataPath,cx,cy,size;
Ext.define('Ushine.map.Map', {
	extend: 'Ext.panel.Panel',
	layout: 'border',
	bodyStyle: 'background-color: #ffffff;padding:3px;background-color:#7CDFF4;',
	border:false,
	id:'map_map',
	//bodyStyle: 'background-color: #ffffff; border: none; padding: 10px;background-color:#7CDFF4;',
	//html:'<iframe id="frame-center-left" src = "././map.jsp" name="frame-center-left"  frameborder="0" width="100%" height="100%"></iframe>',
	constructor: function(dataPath1,cx1,cy1,size1) {
		
		if(dataPath1 == "" ||dataPath1 == null){
			dataPath = "map_data/china.json";
		}else if(dataPath1.length==2){
			dataPath = "map_data/geometryProvince/"+dataPath1+".json";
		}else{
			dataPath = "map_data/geometryCouties/"+dataPath1+".json";
		}
		if(cx1==null||cx1 == ""){
			cx = "105";
		}
		if(cy1==null||cy1 == ""){
			cy = "38";
		}
		
		if(size1 == null || size1 == ""){
			size = 0;
		}else{
			size = size1;
		}
		this.callParent(); 
		//this.items = abc();
	},listeners: {
        afterrender:function(){
        	abc(dataPath,cx,cy,size);
        }
    }
});

var color = [];
function abc(dataPath,cx,cy,size){
	Ext.getCmp('content_frame').removeAll();
	//声明放置地图容器的宽高
	var width = document.getElementById("content_frame-body").style.width;
	var height = document.getElementById("content_frame-body").style.height;
	width = width.substring(0,width.length-2);
	width = width - 380;
	
	height = height.substring(0,height.length-2);
	//设置id为content_frame-body的容器内容为空
	document.getElementById("content_frame-body").innerHTML="";
	//width = 1900;
	//实例选中地区的名称
	var tooltip = d3.select("#tooltip");
	//声明放置地图容器的宽高
	var svg = d3.select("#content_frame-body").append("svg")
		.attr("width", width)
		.attr("height", height)
		.attr("id","svg")
		.style("background-image","url(img/mapBackground.jpg)")
		
	//需要拦截的坐标，北京，上海,天津，重庆，香港，澳门,如果当前显示的是前述的这些，将不进行下一级显示
	var beijing = [110228,110116,110111,110229,110109,110114,110115,110113,110117,110112,110105,110108,110106,110107,110102,110101,110104,110103];
	var tianjin = [120225,120114,120115,120223,120221,120109,120107,120111,120113,120110,120108,120112,120103,120102,120104,120105,120106,120101];
	var chongqing=[500242,500236,500238,500234,500243,500235,500101,500229,500116,500240,500237,500102,500230,500232,500119,500241,500114,500117,500222,500233,500228,500113,500223,500118,500231,500112,500115,500225,500224,500226,500227,500109,500110,500107,500106,500108,500105,500104,500111,500103];
	var shanghai = [310230,310119,310120,310115,310116,310118,310117,310114,310113,310112,310110,310107,310104,310105,310108,310109,310101,310103,310106];
	var aomen = [3681];
	var xianggang = [3682,6655,6656,6657,6658,6659,6660,6661,6662,6663,6664,6665,6666,6667,6668,6669,6670,6671];
	var a = "";
	d3.json(dataPath, function(error, toporoot) {
		//用于放大地图	
//		var cx = null;
//		var cy = null;
//		var size = 0;
		//var proj = d3.geo.mercator().center([cx, cy]).scale(size==0?getZoomScale(toporoot.features, width, height)*34:size);
		//var projection = d3.geo.mercator().center([cx, cy]).scale(1600).translate([width / 2, height / 2]);
		var projection = d3.geo.mercator()
		 .center(getCenters(toporoot.features))
		.translate([width/2,height/2])
		.scale(//50*15
				getZoomScale(toporoot.features, width, height)*34 	
		);
		var path = d3.geo.path().projection(projection);
		if (error) 
			return console.error(error);
		
		//将TopoJSON对象转换成GeoJSON，保存在georoot中
		var georoot = toporoot
		
		//包含中国各省路径的分组元素
		var china = svg.append("g");
		//添加中国各种的路径元素
		var provinces = china.selectAll("path")
		
				.data(georoot.features )
				.enter()
				.append("path")
				.attr("class","province")
				.style("fill", "#ccc")
				.attr("d", path )
				.style("fill","#ccc")
				.style("stroke","#fff")
				.style("stroke-width",".5px")	
				.on("mouseover", function (d,i) {   //鼠标聚焦事件
	                    //tooltip.style("display", null);
	                    // name @ d.properties.name
	                   // showText(d.properties.name);
	                    //console.log(d3.select(d));
	                    a = this.style.fill;
	                    d3.select(this).style("fill","#FF9302");
	                  //设定各省份的填充色
	                    
	                })
	                .on("mouseout", function (d,i) {   //鼠标离焦事件
	                    //tooltip.style("display", "none");
	                    d3.select(this).style("fill",a);
	                   
	                })
	                .on("click", function(d){    //鼠标单击事件
	                	//console.log("click:"+d.properties.name);
	                	try{
	                		Ext.getCmp('noteEquipmentInfo').close();
	                	}catch(e){
	                		
	                	}
	                	Ext.create('Ushine.map.MapUtil').noteEquipmentInfo(d.properties.name);
	                })
	                .on("dblclick", function(d, x, y){   //鼠标双击事件
	                	var id = d.properties.id;
	                	var name = d.properties.name;
//	                	for(var i =0; i<beijing.length;i++){
//	                		if(id==beijing[i]){
//	                			return;
//	                		}
//	                	}
//	                	for(var i =0; i<shanghai.length;i++){
//	                		if(id==shanghai[i]){
//	                			return;
//	                		}
//	                	}
//	                	for(var i =0; i<tianjin.length;i++){
//	                		if(id==tianjin[i]){
//	                			return;
//	                		}
//	                	}
//	                	for(var i =0; i<xianggang.length;i++){
//	                		if(id==xianggang[i]){
//	                			return;
//	                		}
//	                	}
//	                	for(var i =0; i<aomen.length;i++){
//	                		if(id==aomen[i]){
//	                			return;
//	                		}
//	                	}
//	                	
//	                	for(var i =0; i<chongqing.length;i++){
//	                		if(id==chongqing[i]){
//	                			return;
//	                		}
//	                	}
	                	
	                	var dataPath = (d.properties.id.length==4?d.properties.id+"00":d.properties.id);
	                	var cx = d.properties.cp[0];
	                	var cy = d.properties.cp[1];
	                	var size = (d.properties.size?d.properties.size:"");
	                	
	                	if(dataPath.length>2){
	                		return;
	                	}
	                	try{
	                		Ext.getCmp('windowMap').close();
	                	}catch(e){
	                		
	                	}
	                	
	                	
	                	
	                	//创建窗体显示map
	                	Ext.create('Ushine.map.MapUtil').windowMap(name);
	                	bbb(dataPath,cx,cy,size,'windowMap-body');
	                	//Ext.getCmp('content_frame').removeAll();
	                	//Ext.getCmp('content_frame').add(new Ushine.map.Map(dataPath,cx,cy,size));
//	            			window.location.href = "map.jsp?code="+(d.properties.id.length==4?d.properties.id+"00":d.properties.id)
//	                		+"&size="+(d.properties.size?d.properties.size:"")
//	                		+"&cx="+d.properties.cp[0]
//	                		+"&cy="+d.properties.cp[1];
	            		
	                });

		//为每个区域设定颜色
		d3.json(dataPath, function(error, valuedata){
			
			//将读取到的数据存到数组values，令其索引号为各省的名称
			var values = [];
			for(var i=0; i<valuedata.features.length; i++){
				var name = valuedata.features[i].properties.name;
				var value = valuedata.features[i].properties.cp[1];
				values[name] = value;
			}
			//求最大值和最小值
			var maxvalue = d3.max(valuedata.features, function(d){ return d.properties.cp[1]; });
			var minvalue = 0;

			//定义一个线性比例尺，将最小值和最大值之间的值映射到[0, 1]
			var linear = d3.scale.linear()
							.domain([minvalue, maxvalue])
							.range([0, 1]);

			//定义最小值和最大值对应的颜色
			var a = d3.rgb(0,255,255);	//浅蓝色
			var b = d3.rgb(0,0,255);	//蓝色
			 
			//颜色插值函数
			var computeColor = d3.interpolate(a,b);

			//设定指定省份的颜色
			provinces.style("fill", function(d,i){
				var t = linear( values[d.properties.name] );
				if(d.properties.name == '北京市'){
					return "#F0E124";
				}
				var color = computeColor(t);
				return color.toString();
			});

		});
		
		
		
		

		
		//为每个区域添加标注，
		d3.json(dataPath, function(error, places ) {
			//插入分组元素
			var location = svg.selectAll("location")
							.data(places.features)
							.enter()
							.append("g")
							.attr("class","location")
							.attr("transform",function(d){
								//计算标注点的位置
								var coor = projection([d.properties.cp[0], d.properties.cp[1]]);
								return "translate("+ coor[0] + "," + coor[1] +")";
							});
			
			//插入一个圆形形式的标注
//			location.append("circle")
//				.attr("r",4)
//				.attr("fill","#34FA29");
//			
			//为每个标注旁添加文字
			location.append("text")
			.attr("font-size","12px")
			.attr("fill","white")
			.text(function(d){
				return d.properties.name;
			});
			
			
			//插入一张图片
			location.append("image")
				.attr("width",13)
				.attr("height",13)
				.attr("xlink:href",function(d){
					return "img/123.gif";
				});
			//初始化地图每一个区域该有的颜色
			//window.setInterval("hello()",10000);
			var tag = document.getElementById("svg").getElementsByTagName("g")[0].getElementsByTagName("path");
			for(var i =0;i<tag.length;i++){
				color.push(tag[i].style.fill);
			}
			hello();
        	
			//关闭所有地图窗体
			//Ext.create('Ushine.map.MapUtil').closeWindow();
			//创建窗体
			Ext.create('Ushine.map.MapUtil').heartbeatControl();
			Ext.create('Ushine.map.MapUtil').thisHeartbeatControl();
			Ext.create('Ushine.map.MapUtil').dataSelectCount();
			Ext.create('Ushine.map.MapUtil').exceptionInfoCount();
			
			
		});
	});
	
	
	 
	d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
		svg.html(function(d){
			return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
		});
		
		var gSouthSea = d3.select("#southsea");
		
		gSouthSea.attr("transform","translate(540,410)scale(0.5)")
			.attr("class","southsea");

	});

	

}
function hello(){
	//试用ajax请求服务器
	   Ext.Ajax.request({
		   url: 'citys.do',
		   actionMethods: {
			   create : 'POST',
			   read   : 'POST', // by default GET
			   update : 'POST',
			   destroy: 'POST'
		   },
		   //要修改组织的id的集合
		   params: {
			   dataPath:dataPath
		   },
		   success: function(response){
			   
			   var text = response.responseText;
			   var obj=Ext.JSON.decode(text);
				//取得34个省市的html元素，用于该表其颜色
				var tag = document.getElementById("svg").getElementsByTagName("g")[0].getElementsByTagName("path");
			   for ( var i = 0; i < color.length; i++) {
					   d3.select(tag[i]).style("fill",color[i]);
			   }
			   for ( var i = 0; i < obj.length; i++) {
				   if(obj[i] == 'false'){
					   d3.select(tag[i]).style("fill","red");
				   }
			   }
			  // Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
			   //禁用成功后刷新数据
			  // self.findClueByProperty();
		   },
		   failure: function(form, action) {
			   //Ext.create('Ushine.utils.Msg').onInfo('禁用失败，请联系管理员');
		   }
	   });
	
	

}
//处理要显示的文字
function showText(_id) {

    tooltip.text(_id);
}
function getZoomScale(features, width, height){
	var longitudeMin = 100000;//最小经度
	var latitudeMin = 100000;//最小维度
	var longitudeMax = 0;//最大经度
	var latitudeMax = 0;//最大纬度
	features.forEach(function(e){  
	    var a = d3.geo.bounds(e);//[[最小经度，最小维度][最大经度，最大纬度]]
	    if(a[0][0] < longitudeMin) {
	    	longitudeMin = a[0][0];
	    }
	    if(a[0][1] < latitudeMin) {
	    	latitudeMin = a[0][1];
	    }
	    if(a[1][0] > longitudeMax) {
	    	longitudeMax = a[1][0];
	    }
	    if(a[1][1] > latitudeMax) {
	    	latitudeMax = a[1][1];
	    }
	});

	var a = longitudeMax-longitudeMin;
	var b = latitudeMax-latitudeMin;
//	if(a > b) {//按照宽度进行缩放
//		return width/a;
//	} else {
//		return height/b;
//	}
	//console.log(Math.min(width/a, height/b));
	return Math.min(width/a, height/b);
}

function getCenters(features){
	var longitudeMin = 100000;//最小经度
	var latitudeMin = 100000;//最小维度
	var longitudeMax = 0;//最大经度
	var latitudeMax = 0;//最大纬度
	features.forEach(function(e){  
	    var a = d3.geo.bounds(e);//[[最小经度，最小维度][最大经度，最大纬度]]
	    if(a[0][0] < longitudeMin) {
	    	longitudeMin = a[0][0];
	    }
	    if(a[0][1] < latitudeMin) {
	    	latitudeMin = a[0][1];
	    }
	    if(a[1][0] > longitudeMax) {
	    	longitudeMax = a[1][0];
	    }
	    if(a[1][1] > latitudeMax) {
	    	latitudeMax = a[1][1];
	    }
	});

	var a = (longitudeMax + longitudeMin)/2;
	var b = (latitudeMax + latitudeMin)/2;

	return [a, b];
}