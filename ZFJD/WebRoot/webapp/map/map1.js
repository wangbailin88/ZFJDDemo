
//声明放置地图容器的宽高
var width = 960, height = 800;
//实例选中地区的名称
var tooltip = d3.select("#tooltip");
var svg = d3.select("div").append("svg")
	.attr("width", width)
	.attr("height", height);

var cx = null;
var cy = null;
var size = "";
var projection = d3.geo.mercator()
						.center([107, 31])
						.scale(600)
    					.translate([width/2, height/2]);
//需要拦截的坐标，北京，上海,天津，重庆，香港，澳门,如果当前显示的是前述的这些，将不进行下一级显示
var noclick = [116.4551,117.4219,121.4648,107.7539,114.1178,111.5547];

d3.json(dataPath, function(error, toporoot) {
	//用于放大地图	
	var proj = d3.geo.mercator().center([cx, cy]).scale(size==0?getZoomScale(toporoot.features, width, height)*34:size).translate([width / 2, height / 2]);
	var path = d3.geo.path().projection(proj);
	if (error) 
		return console.error(error);
	
	//输出china.topojson的对象
	//console.log(toporoot);
	
	//将TopoJSON对象转换成GeoJSON，保存在georoot中
	var georoot = toporoot
	//console.log(toporoot.objects.china);
	
	//输出GeoJSON对象
	//console.log(georoot);
	
	//包含中国各省路径的分组元素
	var china = svg.append("g");
	var a = "";
	//添加中国各种的路径元素
	var provinces = china.selectAll("path")
	
			.data(georoot.features )
			.enter()
			.append("path")
			.attr("class","province")
			.style("fill", "#ccc")
			.attr("d", path )
			.on("mouseover", function (d,i) {   //鼠标聚焦事件
                    tooltip.style("display", null);
                    // name @ d.properties.name
                    showText(d.properties.name);
                    //console.log(d3.select(d));
                    a = this.style.fill;
                    d3.select(this).style("fill","#FF9302");
                   
                  //设定各省份的填充色
                    
                })
                .on("mouseout", function (d,i) {   //鼠标离焦事件
                    tooltip.style("display", "none");
                    d3.select(this).style("fill",a);
                   
                })
                .on("click", function(d){    //鼠标单击事件
                	console.log("click:"+d.properties.name);
                })
                .on("dblclick", function(d, x, y){   //鼠标双击事件
                	console.log(d.type);
                	//声明变量标识是否转发,默认为转发
//                		//获取当前点击的节点坐标
//                		var coord = d.cp[0];
//                		alert(coord);
//                		//判断左边coord是否属于需要拦截的，如果不是需要拦截的直接转发
//                		for(var i = 0; i < noclick.length;i++){
//                    		if(noclick[i] == coord){
//                    			boo = false;
//                    			return;
//                    		}
//                    	}
                	//如果为true,进行转发
            			window.location.href = "map.jsp?code="+(d.properties.id.length==4?d.properties.id+"00":d.properties.id)
                		+"&size="+(d.properties.size?d.properties.size:"")
                		+"&cx="+d.properties.cp[0]
                		+"&cy="+d.properties.cp[1];
            		
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
							var coor = proj([d.properties.cp[0], d.properties.cp[1]]);
							return "translate("+ coor[0] + "," + coor[1] +")";
						});
		
		//插入一个圆形形式的标注
//		location.append("circle")
//			.attr("r",4)
//			.attr("fill","#34FA29");
//		
		//为每个标注旁添加文字
		location.append("text")
		.attr("font-size","12px")
		.attr("fill","#FFF7B2")
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
		//window.setInterval("hello('"+location+"')",1000);
	});
});
function hello(location){
	var a = d3.select(".location");
	console.log(a);
	var date = new Date();
	//console.log(date.getSeconds());
	if(date.getSeconds() % 2 ==0){
		location.append("circle")
		.attr("r",4)
		.attr("fill","#34FA29");
	}else{
		location.append("circle")
		.attr("r",4)
		.attr("fill","red");
	
	}
	//插入一个圆形形式的标注

}
d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
	svg.html(function(d){
		return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
	});
	
	var gSouthSea = d3.select("#southsea");
	
	gSouthSea.attr("transform","translate(540,410)scale(0.5)")
		.attr("class","southsea");

});

//处理要显示的文字
function showText(_id) {

    tooltip.text(_id);
}
function getZoomScale(features, width, height){
	console.log(features);
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
	/*if(a > b) {//按照宽度进行缩放
		return width/a;
	} else {
		return height/b;
	}*/

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



