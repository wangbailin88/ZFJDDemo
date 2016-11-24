var bigscreen_renovateStatus = null;
/**
* 大屏展示
 */
Ext.define('Ushine.screenShow.ScreenShow', {
	extend: 'Ext.panel.Panel',
	border:false,
	id:'screenShow_ScreenShow',
	bodyStyle: 'background-color: #1d50a1;padding-right:10px;',
	layout: {
		type: 'hbox',
		align : 'stretch',
		pack  : 'start'
	},
	region: 'center',
	constructor: function() {
		var self = this;
		this.items=[{//左侧地图
			border:true,
			flex:1,
			style:"background-color:#000000;",
			id:'map',
			region: 'center',
			listeners: {
				provinceover: function(data){
					//Ext.Msg.alert(data.properties.name);
				},
				afterlayout: function(p){
					if(p.fgDiv) return;
					p.fgDiv = p.body.createChild({
						tag: 'div',
						id: 'screenShow_map'
					});
					var width = p.getWidth();
					var height = p.getHeight();
					
					//声明绘制地图的div容器
				    var svg = d3.select('#map-innerCt').append('svg').style("background-color","#A0BFDE");
				    //开始使用d3js绘制地图
				    d3.json("map_data/world_3m.json", function(data) {
				    	
				    	
				      //地图比例缩放
				      var projection = d3.geo.mercator();
				      var oldScala = projection.scale();
				      var oldTranslate = projection.translate();
				      xy = projection.center([105, 38]).scale(oldScala * (width / oldTranslate[0] / 2) * 4)
				        .translate([width / 2, height / 2]);
				      path = d3.geo.path().projection(xy);
				      
				      
				      //地图的宽高，由页面预留显示地图的div容器宽高决定
				      svg.attr('width', width).attr('height', height);
				      
				      //开始绘制地图
				      svg.selectAll('path').data(data.features).enter().append('svg:path')
				        .attr('d', path)
				        .on('mouseover', function(data) {
				        })
				        .on('mouseout', function(data) {
				        })
				        .attr('fill', 'rgba(230,232,231,1)')
				        .attr('stroke', 'rgba(206,208,195,1)')
				        .attr('stroke-width', 1);
				    });
				    
				    
				    var svg1 = d3.select('#screenShow_map').append('svg');
				    var a="";
				    d3.json("map_data/china.json", function(data) {
				        /* Antarctica will not shown on the map */
				        var projection = d3.geo.mercator();
				        var oldScala = projection.scale();
				        var oldTranslate = projection.translate();

				        xy = projection.center([105, 38]).scale(oldScala * (width / oldTranslate[0] / 2) * 4)
				          .translate([width / 2, height / 2]);
				        
				        path = d3.geo.path().projection(xy);
				        
				        svg1.attr('width', width).attr('height', height);
				        svg1.selectAll('path').data(data.features).enter().append('svg:path')
				          .attr('d', path)
				          .attr('fill', '#0581c0')
				          .attr('stroke', 'rgba(206,208,195,1)')
				          .attr('stroke-width', 1)
				          .on("mouseover", function (d,i) {   //鼠标聚焦事件
		                    a = this.style.fill;
		                    d3.select(this).style("fill","#FF9302");
		                    
		                })
		                .on("mouseout", function (d,i) {   //鼠标离焦事件
		                    //tooltip.style("display", "none");
		                    d3.select(this).style("fill",a);
		                   
		                })
		                .on("click", function(d){    //鼠标单击事件
		                	var name = d.properties.name;
		                	var code = d.properties.id;
		                	
		                	//查询本机黑匣子数据
							bigscreen_checkThisBlackBoxBaseData(code);
		                })
		                .on("dblclick", function(d, x, y){   //鼠标双击事件
		                	var id = d.properties.id;
		                	var name = d.properties.name;
		                	var dataPath = (d.properties.id.length==4?d.properties.id+"00":d.properties.id);
		                	var cx = d.properties.cp[0];
		                	var cy = d.properties.cp[1];
		                	var size = (d.properties.size?d.properties.size:"");
		                	try{
		                		Ext.getCmp('windowMap').close();
		                	}catch(e){
		                		
		                	}
		                	
		                	//创建窗体显示map
		                	Ext.create('Ushine.map.MapUtil').windowMap(name,true,"center_frame1-body");
		                	mapShow1(dataPath,cx,cy,size,'windowMap-body');
		                	
		            		
		                });
				        
				        
				        
				        
				        
				        
				        
				      //为每个区域添加标注，
						d3.json("map_data/china.json", function(error, places ) {
							//插入分组元素
							var location = svg1.selectAll("location")
											.data(places.features)
											.enter()
											.append("g")
											.attr("class","location")
											.attr("transform",function(d){
												//处理甘肃省和内蒙古标注地址
												if(d.properties.id=='620000'){
													var coor = projection([(d.properties.cp[0]+1), (d.properties.cp[1]+0.7)]);
													return "translate("+ coor[0] + "," + coor[1] +")";
												}
												if(d.properties.id=='150000'){
													var coor = projection([d.properties.cp[0], (d.properties.cp[1]-3)]);
													return "translate("+ coor[0] + "," + coor[1] +")";
												}
												
												//计算标注点的位置
												var coor = projection([d.properties.cp[0], d.properties.cp[1]]);
												return "translate("+ coor[0] + "," + coor[1] +")";
											});
						
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
									return "img/bullet_yellow.png";
								});
							
						});
						
//						//setInterval
//						//查询本机黑匣子数据
						bigscreen_checkThisBlackBoxBaseData();
						d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
							svg.html(function(d){
								return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
							});
							
							var gSouthSea = d3.select("#southsea");
							
							gSouthSea.attr("transform","translate(540,410)scale(0.5)")
								.attr("class","southsea");
				
						});
				      });
				}
			}
		},{//右侧监控信息
			width:370,
			height:'100%',
			border:false,
			padding:'0 10 0 10',
			style:'background-color: #1d50a1;',
			region : 'west',
			layout:'vbox',
			items:[{//设备
				width:350,
				flex:3,
				height:'100%',
				border:false,
				//autoScroll: true,
				style:'background-color:#1d50a1;overflow-x:hidden;',
				region : 'west',
				//autoScroll:true,
				padding:'20 0 0 10',
				html:'<div style="width:340px;height:100%;background-color:#1d50a1;overflow-y:auto;overflow-x:none;">'+
					'<table>'+
					'<tr>'+
					'<td><img  src = "img/disconnect.png"/></td>'+
					' <td style="width:30%;font-size:20px;font-weight:bold;"><span style="color:yellow;">2513</span></td>'+
					'<td ><img  src = "img/disconnect.png"/></td>'+
					' <td style="width:30%;font-size:20px;font-weight:bold;"><span style="color:red;">12</span></td>'+
					'<td><img  src = "img/error.png"/></td>'+
					' <td style="width:30%;font-size:20px;font-weight:bold;"><span style="color:red;">0</span></td>'+
					' </tr>'+
					' </table>'+
					'<hr style="border:1px dashed #ccc;margin-top:10px;"/>'+
					'<p id = "bigscreen_deviceNmae" style="font-size:16px;font-weight:bold;color:yellow;">设备</p>'+
					'<table  style="color:#FFFFFF;line-height:20px;font-size:13px;">'+
			    	'<tr>'+
			    	' <td><img style="float:right;" src = "img/processor.png"/></td>'+
			    	'<td id="bigscreen_cpuName">Cord(TM) i7-5500U CPU @ 204GHz <span style="color:yellow;">(16%)</span></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	' <td><img style="float:right;" src = "img/ddr_memory.png"/></td>'+
			    	'<td id = "bigscreen_memory">DDR3 1600 MHz 0.8/8G <span style="color:yellow;">(10%)</span></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	' <td><img style="float:right;" src = "img/database_server.png"/></td>'+
			    	'<td id = "bigscreen_hardDisk">HGST HTS541010A7E630 103G/932GB <span style="color:yellow;">(10%)</span></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	' <td><img style="float:right;" src = "img/temperature_5.png"/></td>'+
			    	'<td id = "bigscreen_theTemperature">79C° / 120°F</td>'+
			    	'</tr>'+
			    	'</table>'+
			    	'<table style="width:100%;color:#FFFFFF;font-size:13px;">'+
			    	'<tr>'+
			    	'<td><img src="img/bullet_yellow.png"/>1</td>'+
			    	'<td><img src="img/bullet_red.png"/>0</td>'+
			    	'<td><img src="img/bullet_yellow.png"/>2</td>'+
			    	'</tr>'+
			    	'</table>'+
			    	'<hr style="border:1px dashed #ccc;margin-top:10px;"/>'+
			    	'<table style="color:#FFFFFF;line-height:20px;font-size:13px;">'+
			    	'<tr>'+
			    	'<td>管理中心联通:&nbsp;&nbsp;</td>'+
			    	'<td id="bigscreen_manageCenterConnected"><img src = "img/bullet_yellow.png"/></td>'+
			    	'<td>审计系统:</td>'+
			    	'<td id="bigscreen_theAuditSystem"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	'<td>RAID状态监测:</td>'+
			    	'<td id="bigscreen_RAIDStatusMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'<td>报送操作日志:&nbsp;&nbsp;</td>'+
			    	'<td id="bigscreen_toSubmitTheOperation"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	'<td>开箱监测:</td>'+
			    	'<td id="bigscreen_openBoxMonitoring"><img src = "img/bullet_red.png"/></td>'+
			    	'<td>旁路监测:</td>'+
			    	'<td id="bigscreen_theBypassMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	'<td>CPU监测:</td>'+
			    	'<td id="bigscreen_cpuMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'<td>无流量监测:</td>'+
			    	'<td id="bigscreen_noTrafficMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	'<td>内存监测:</td>'+
			    	'<td id="bigscreen_memoryMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'<td>磁盘B监测:</td>'+
			    	'<td id="bigscreen_diskBMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'<tr>'+
			    	'<td>磁盘容量监测:</td>'+
			    	'<td id="bigscreen_diskCapacityMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'<td>磁盘A监测:</td>'+
			    	'<td id="bigscreen_diskAMonitoring"><img src = "img/bullet_yellow.png"/></td>'+
			    	'</tr>'+
			    	'</table>'+
			    	'</div>'
			},{
				height:32,
				width:350,
				border:false,
				html:'<div style="height:30px;background-color:#1d50a1;line-height:40px;color:#FFFFFF;">数据获取统计</div>',
			},{//数据查询统计
				width:350,
				flex:1,
				height:'100%',
				border:false,
				margin:'10 0 0 0',
				region : 'west',
		        layout: 'fit',
				items: Ext.create('Ext.chart.Chart', {
				    animate: true,
				    theme: 'Category1',
				    store: store1,
				    axes: [{
				        type: 'Numeric',
				        position: 'left',
				        fields: ['data1', 'data2', 'data3'],
				        grid: true
				    }, {
				        type: 'Category',
				        position: 'bottom',
				        fields: ['name'],
				    }],
				    series: [{
				        type: 'column',
				        axis: 'left',
				        xField: 'name',
				        yField: 'data1',
				        markerConfig: {
				            type: 'cross',
				            size: 3
				        }
				    }, {
				        type: 'column',
				        axis: 'left',
				        xField: 'name',
				        yField: 'data2',
				        markerConfig: {
				            type: 'circle',
				            size: 5
				        }
				    }, {
				        type: 'line',
				        axis: 'left',
				        smooth: true,
				        fill: true,
				        fillOpacity: 0.5,
				        xField: 'name',
				        yField: 'data3'
				    }]
				})
			},{
				height:32,
				width:350,
				border:false,
				html:'<div style="height:30px;background-color:#1d50a1;line-height:40px;color:#FFFFFF;">异常发送频率</div>',
			},{//异常信息统计图
				width:350,
				height:'100%',
				flex:1,
				border:false,
				margin:'10 0 0 0',
				region : 'west',
				flex:1,
				layout:'fit',
				items:Ext.create('Ext.chart.Chart', {
    	            style: 'background:#fff',
    	            animate: true,
    	            store: store1,
    	            shadow: true,
    	            theme: 'Category1',
    	            legend: {
    	                position: 'right'
    	            },
    	            axes: [{
    	                type: 'Numeric',
    	                minimum: 0,
    	                position: 'left',
    	                fields: ['data1', 'data2', 'data3'],
    	                title: '',
    	                minorTickSteps: 1,
    	                grid: {
    	                    odd: {
    	                        opacity: 1,
    	                        fill: '#ddd',
    	                        stroke: '#bbb',
    	                        'stroke-width': 0.5
    	                    }
    	                }
    	            }, {
    	                type: 'Category',
    	                position: 'bottom',
    	                fields: ['name'],
    	                title: ''
    	            }],
    	            series: [{
    	                type: 'line',
    	                highlight: {
    	                    size: 7,
    	                    radius: 7
    	                },
    	                axis: 'left',
    	                xField: 'name',
    	                yField: 'data1',
    	                markerConfig: {
    	                    type: 'cross',
    	                    size: 4,
    	                    radius: 4,
    	                    'stroke-width': 0
    	                }
    	            }, {
    	                type: 'line',
    	                highlight: {
    	                    size: 7,
    	                    radius: 7
    	                },
    	                axis: 'left',
    	                smooth: true,
    	                xField: 'name',
    	                yField: 'data2',
    	                markerConfig: {
    	                    type: 'circle',
    	                    size: 4,
    	                    radius: 4,
    	                    'stroke-width': 0
    	                }
    	            }, {
    	                type: 'line',
    	                highlight: {
    	                    size: 7,
    	                    radius: 7
    	                },
    	                axis: 'left',
    	                smooth: true,
    	                fill: true,
    	                xField: 'name',
    	                yField: 'data3',
    	                markerConfig: {
    	                    type: 'circle',
    	                    size: 4,
    	                    radius: 4,
    	                    'stroke-width': 0
    	                }
    	            }]
    	        })
			},{
				height:32,
				width:350,
				border:false,
				html:'<div style="height:30px;background-color:#1d50a1;line-height:40px;color:#FFFFFF;">异常类型统计</div>',
			},{
				width:350,
				height:'100%',
				border:false,
				flex:1,
				region : 'west',
				layout:'fit',
				items: Ext.create('Ext.chart.Chart', {
		            xtype: 'chart',
		            animate: true,
		            store: store1,
		            shadow: true,
		            legend: {
		                position: 'right'
		            },
		            insetPadding: 2,
		            theme: 'Base:gradients',
		            series: [{
		                type: 'pie',
		                field: 'data1',
		                showInLegend: true,
		                donut: false,
		                tips: {
		                  trackMouse: true,
		                  width: 140,
		                  height: 28,
		                  renderer: function(storeItem, item) {
		                    //calculate percentage.
		                    var total = 0;
		                    store1.each(function(rec) {
		                        total += rec.get('data1');
		                    });
		                    this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data1') / total * 100) + '%');
		                  }
		                },
		                highlight: {
		                  segment: {
		                    margin: 20
		                  }
		                },
		                label: {
		                    field: 'name',
		                    display: 'rotate',
		                    contrast: true,
		                    font: '13px Arial'
		                }
		            }]
		        })
			}]
		}]
		this.callParent(); 
	},listeners: {
        afterrender:function(){
        	//显示心跳函数
        	Ext.create('Ushine.map.MapUtil').heartbeatControl(true);
        }
    }
});


//选择地图到id为screenShow_map
function screenShowMap(){
	var screenShowMapId = "screenShow_map-body";
		var dataPath = "map_data/china.json";
		var cx = "105";
		var cy = "38";
		var size = 0;
		//声明放置地图容器的宽高
		var width = document.getElementById("center_frame1-body").style.width;
		var height = document.getElementById("center_frame1-body").style.height;
		width = width.substring(0,width.length-2)-380;
		height = height.substring(0,height.length-2)-0;
		document.getElementById(screenShowMapId).innerHTML="";
		//width = 1900;
		//实例选中地区的名称
		var tooltip = d3.select("#tooltip");
		//声明放置地图容器的宽高
		var svg = d3.select("#"+screenShowMapId+"").append("svg")
			.attr("width", width)
			.attr("height", height)
			.attr("id","svg")
			.style("background-image","url(img/mapBackground.jpg)")
			.style("background-size",""+(width+200)+" "+(height+50)+"")
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
			var projection = d3.geo.mercator()
			 .center(getCenters(toporoot.features))
			.translate([width/2.3,height/2])
			.scale(//50*15
					getZoomScale(toporoot.features, width, height)*45 	
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
		                	var name = d.properties.name;
		                	var code = d.properties.id;
		                	//查询本机黑匣子数据
		            		bigscreen_checkThisBlackBoxBaseData(code);
		                })
		                .on("dblclick", function(d, x, y){   //鼠标双击事件
		                	var id = d.properties.id;
		                	var name = d.properties.name;
		                	var dataPath = (d.properties.id.length==4?d.properties.id+"00":d.properties.id);
		                	var cx = d.properties.cp[0];
		                	var cy = d.properties.cp[1];
		                	var size = (d.properties.size?d.properties.size:"");
		                	
		                	try{
		                		Ext.getCmp('windowMap').close();
		                	}catch(e){
		                		
		                	}
		                	
		                	
		                	
		                	//创建窗体显示map
		                	Ext.create('Ushine.map.MapUtil').windowMap(name,true,"center_frame1-body");
		                	mapShow1(dataPath,cx,cy,size,'windowMap-body');
		            		
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
//					if(d.properties.name == '北京市'){
//						return "#F0E124";
//					}
					var color = computeColor(t);
//					return color.toString();
					return "#0581c0";
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
						return "img/bullet_yellow.png";
					});
				//初始化地图每一个区域该有的颜色
				//window.setInterval("hello()",10000);
				var tag = document.getElementById("svg").getElementsByTagName("g")[0].getElementsByTagName("path");
				for(var i =0;i<tag.length;i++){
					color.push(tag[i].style.fill);
				}
				//hello();
				
				
			});
		});
		
		//查询本机黑匣子数据
		bigscreen_checkThisBlackBoxBaseData();
		 
		d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
			svg.html(function(d){
				return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
			});
			
			var gSouthSea = d3.select("#southsea");
			
			gSouthSea.attr("transform","translate(540,410)scale(0.5)")
				.attr("class","southsea");

		});
} 
//查询本机的黑匣子数据并渲染到页面容器中
function bigscreen_checkThisBlackBoxBaseData(code){
	if(bigscreen_renovateStatus!=null && bigscreen_renovateStatus!=''){
		clearTimeout(bigscreen_renovateStatus);
		bigscreen_renovateStatus = null;
	}
	//请求ajax请求服务器，作用于查询本机黑匣子数据
	   Ext.Ajax.request({
		   url: 'findThisBlackBoxBaseData.do',
		   actionMethods: {
			   create : 'POST',
			   read   : 'POST', // by default GET
			   update : 'POST',
			   destroy: 'POST'
		   },
		   params: {
			   code:code==null||code==''?'':code
		   },
		   success: function(response){
			   var text = response.responseText;
			   var obj=Ext.JSON.decode(text);
			   //判断当前节点是否注册，
			   if(obj.id==''){
				   document.getElementById("bigscreen_deviceNmae").innerHTML = "该节点无权限查看或未注册";
				   return ;
			   }
			   //设置黑匣子数据到div容器中
			   document.getElementById("bigscreen_deviceNmae").innerHTML = obj.deviceNmae;//设备名称
			   document.getElementById("bigscreen_theTemperature").innerHTML = obj.theTemperature;////当前温度
			   
			   document.getElementById("bigscreen_cpuName").innerHTML = obj.cpuName+"<span style='color:yellow;'>("+obj.cpuUnilizationRatio+")</span>";//cpu信息
			   document.getElementById("bigscreen_hardDisk").innerHTML = obj.hardDiskName+"<span style='color:yellow;'>("+obj.hardDiskUnilizationRatio+")</span>";//硬盘信息
			   document.getElementById("bigscreen_memory").innerHTML = obj.memoryName+"<span style='color:yellow;'>("+obj.memoryUnilizationRatio+")</span>";//内存信息
			  // <img src = "img/bullet_yellow.png"/>
			   document.getElementById("bigscreen_manageCenterConnected").innerHTML="<img src = '"+checkDataStatus(obj.manageCenterConnected)+"'/>";//管理中心连通状态  
			   document.getElementById("bigscreen_theAuditSystem").innerHTML="<img src = '"+checkDataStatus(obj.theAuditSystem)+"'/>";//审计系统状态
			   document.getElementById("bigscreen_RAIDStatusMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.RAIDStatusMonitoring)+"'/>";//RAID状态监测状态
			   document.getElementById("bigscreen_toSubmitTheOperation").innerHTML="<img src = '"+checkDataStatus(obj.toSubmitTheOperation)+"'/>";////报送操作日志状态
			   document.getElementById("bigscreen_openBoxMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.openBoxMonitoring)+"'/>";//开箱监测状态
			   document.getElementById("bigscreen_theBypassMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.theBypassMonitoring)+"'/>";//旁路监测状态 
			   document.getElementById("bigscreen_cpuMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.cpuMonitoring)+"'/>";//cpu状态
			   document.getElementById("bigscreen_noTrafficMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.noTrafficMonitoring)+"'/>";//无流量监测
			   document.getElementById("bigscreen_memoryMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.memoryMonitoring)+"'/>";//内存监测
			   document.getElementById("bigscreen_diskAMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.diskAMonitoring)+"'/>";//磁盘A监测
			   document.getElementById("bigscreen_diskBMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.diskBMonitoring)+"'/>";//磁盘B监测
			   document.getElementById("bigscreen_diskCapacityMonitoring").innerHTML="<img src = '"+checkDataStatus(obj.diskCapacityMonitoring)+"'/>";//磁盘容量监测
		   },
		   failure: function(form, action) {
			   Ext.create('Ushine.utils.Msg').onInfo('请求服务失败，请联系管理员');
		   }
	   });
	   bigscreen_renovateStatus = window.setTimeout("bigscreen_checkThisBlackBoxBaseData()",500000);
}
