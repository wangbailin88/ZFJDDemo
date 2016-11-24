var renovateStatus = null;
/**
 * 主要地图展示
 */
Ext.define('Ushine.map.Map', {
	extend: 'Ext.panel.Panel',
	border:false,
	id:'screenShow_ScreenShow',
	bodyStyle: 'background-color: #ececec;',
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
			html:'<div style="height:25px;background-color:#E6E6E6;color:#000000;line-height:2;border-radius:10px;"></div>',
			id:'map',
			region: 'center',
			listeners: {
				provinceover: function(data){
					//Ext.Msg.alert(data.properties.name);
				},
				afterlayout: function(p){
					/**
					 * 以下功能代码注释详看大屏展示地图功能注释，两者功能是一样的
					 */
					if(p.fgDiv) return;
					p.fgDiv = p.body.createChild({
						tag: 'div',
						html:'<div style="height:25px;background-color:#E6E6E6;color:#000000;border-radius:10px;line-height:2;margin-left:10px;">节点状态图</div>',
						id: 'screenShow_map'
					});
					var width = p.getWidth();
					var height = p.getHeight();
				    var svg = d3.select('#map-innerCt').append('svg').style("background-color","#A0BFDE");
				    d3.json("map_data/world_3m.json", function(data) {
				     
				      var projection = d3.geo.mercator();
				      var oldScala = projection.scale();
				      var oldTranslate = projection.translate();

				      xy = projection.center([105, 38]).scale(oldScala * (width / oldTranslate[0] / 2) * 4)
				        .translate([width / 2, height / 2]);
				      path = d3.geo.path().projection(xy);
				      svg.attr('width', width).attr('height', height);
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
		                    d3.select(this).style("fill",a);
		                   
		                })
		                .on("click", function(d){    //鼠标单击事件
		                	var name = d.properties.name;
		                	var code = d.properties.id;
		                	
		                	//查询当前点击节点的黑匣子最新数据
		                	index_checkThisBlackBoxBaseData(code);
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
		                	Ext.create('Ushine.map.MapUtil').windowMap(name,false,"center_frame-body");
		                	mapShow(dataPath,cx,cy,size,'windowMap-body');
		            		
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
						index_checkThisBlackBoxBaseData();
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
			region : 'west',
			layout:'vbox',
			items:[{//设备
				width:350,
				flex:2,
				height:'100%',
				border:true,
				region : 'west',
				title:'设备状态信息',
				headerOverCls :'panelHeadCss',
				style:'background-color:red;',
				layout:'hbox',
				items:[{
					width:350,
					flex:1,
					height:'100%',
					region : 'west',
					padding:'0 0 0 10',
					layout:'vbox',
					border:false,
					overflowY:'auto',
					items:[
				       {//第一大行
						width:'100%',
						height:42,
						border:false,
						region : 'west',
						style:'border-bottom:2px dashed #ccc;',
						layout:'hbox',
						margins:'0 0 8 0',
						items:[{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							style:'background-color:#ccc;',
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/disconnect.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        text: '2513',
						        style:'font-size:20px;font-weight:bold;',
						        margin: '8 0 0 10'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/disconnect.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        text: '12',
						        style:'font-size:20px;font-weight:bold;',
						        margin: '8 0 0 10'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							 
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/error.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        style:'font-size:20px;font-weight:bold;',
						        text: '0',
						        margin: '8 0 0 10'
						    }]
						}]
					},{//第二大行
						width:'100%',
						height:146,
						border:false,
						region : 'west',
						style:'border-bottom:2px dashed #ccc;',
						margin:'0 0 5 0',
						layout:'vbox',
						items:[{//设备
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							style:'background-color:#ccc',
							region : 'west',
							items:[{
						        xtype: 'label',
						        id:'index_deviceNmae',
						        style:'font-size:15px;margin-top:10px;font-weight:bold;',
						        text: '设备',
						        margin: '8 0 0 10'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/processor.png);background-repeat:no-repeat;',
						        width:22,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        id:'index_cpu',
						        forId: 'myFieldId',
						        style:'font-size:12px;margin-top:10px;margin-left:10px;',
						        text: 'Cord(TM) i7-5500U CPU @ 204GHz (16)'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							layout:'hbox',
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/ddr_memory.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        id:'index_memory',
						        style:'font-size:12px;margin-top:10px;margin-left:10px;',
						        text: 'DDR3 1600 MHz 0.8/8G(10%)'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							layout:'hbox',
							border:false,
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/database_server.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        id:'index_hardDisk',
						        style:'font-size:12px;margin-top:10px;margin-left:10px;',
						        text: 'HGST HTS541010A7E630 103G/932GB (10%)'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							layout:'hbox',
							border:false,
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/temperature_5.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        id:'index_theTemperature',
						        style:'font-size:12px;margin-top:10px;margin-left:10px;',
						        text: '79C° / 120°F'
						    }]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							layout:'hbox',
							border:false,
							region : 'west',
							margin:'4 0 6 0',
							items:[{
								flex: 1,
								width:'100%',
								height:'100%',
								layout:'hbox',
								border:false,
								region : 'west',
								items:[{
									xtype: 'image',
							        forId: 'myFieldId',
							        style:'background-image:url(img/bullet_yellow.png);background-repeat:no-repeat;',
							        width:20,
							        margin: '8 0 0 10'
							    },{
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;margin-top:10px;margin-left:10px;',
							        text: '1'
							    }]
							},{
								flex: 1,
								width:'100%',
								height:'100%',
								layout:'hbox',
								border:false,
								region : 'west',
								items:[{
							        xtype: 'image',
							        forId: 'myFieldId',
							        style:'background-image:url(img/bullet_red.png);background-repeat:no-repeat;',
							        width:20,
							        margin: '8 0 0 10'
							    },{
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;margin-top:10px;margin-left:10px;',
							        text: '2'
							    }]
							},{
								flex: 1,
								width:'100%',
								height:'100%',
								layout:'hbox',
								border:false,
								region : 'west',
								items:[{
							        xtype: 'image',
							        forId: 'myFieldId',
							        style:'background-image:url(img/bullet_yellow.png);background-repeat:no-repeat;',
							        width:20,
							        margin: '8 0 0 10'
							    },{
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;margin-top:10px;margin-left:10px;',
							        text: '3'
							    }]
							}]
						}]
					},{//第三大行
						width:'100%',
						height:140,
						border:false,
						region : 'west',
						//style:'border-bottom:1px dashed red;',
						margins:'6 0 5 0',
						layout:'vbox',
						items:[{
							flex: 1,
							height:'100%',
							width:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '监管中心连通:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_manageCenterConnected',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '审计系统:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_theAuditSystem',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: 'RAID状态监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_RAIDStatusMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '报送操作日志:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_toSubmitTheOperation',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '开箱监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_openBoxMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '旁路监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_theBypassMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: 'CPU监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_cpuMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '无流量监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_noTrafficMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '内存监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_memoryMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '磁盘A监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_diskAMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						},{
							flex: 1,
							width:'100%',
							height:'100%',
							border:false,
							region : 'west',
							layout:'hbox',
							items:[{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '磁盘容量监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_diskCapacityMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							},{
								flex: 1,
								height:'100%',
								width:'100%',
								border:false,
								region : 'west',
								layout:'hbox',
								items:[{
									flex: 5,
							        xtype: 'label',
							        forId: 'myFieldId',
							        style:'font-size:12px;font-weight:bold;',
							        text: '磁盘B监测:',
							        margin:'2 0 0 10'
							    },{
							    	flex: 1,
							        xtype: 'image',
							        id:'index_diskBMonitoring',
							        src:'img/bullet_yellow.png',
							        margin:'-4 0 0 0'
							    }]
							}]
						}]
					}]
				}]
			},{//数据查询统计
				width:350,
				flex:1,
				title:'数据查询统计',
				height:'100%',
				border:true,
				margin:'10 0 0 0',
				region : 'west',
		        layout: 'fit',
				items: Ext.create('Ext.chart.Chart', {
				    style: 'background:#fff',
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
			},{//异常信息统计图
				width:350,
				title:'异常信息统计',
				height:'100%',
				flex:1,
				border:true,
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
			}]
		}]
		this.callParent(); 
	},listeners: {
        afterrender:function(){
        	screenShowMap();
        	Ext.create('Ushine.map.MapUtil').heartbeatControl();
        	Ext.create('Ushine.map.MapUtil').thisHeartbeatControl("center_frame-body");
        }
    },
    //注册节点窗口
    registerNode:function(thisNodeInfo){
    	var _form = new Ext.FormPanel({
			layout: {
                    type: 'vbox'
                },
            bodyPadding: 8,
            margin:0,
            border: false,
			baseCls: 'form-body',
			buttonAlign:"center",
			items : [{
				fieldLabel:'节点名称',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入节点名称',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'nodeName',
				value:thisNodeInfo.nodeName==''?'':thisNodeInfo.nodeName
			},{
				fieldLabel:'节点编码',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入节点编码',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'nodeCode',
				value:thisNodeInfo.nodeCode==''?'':thisNodeInfo.nodeCode
			},{
				fieldLabel:'IP地址',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入IP地址',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'nodeIp',
				value:thisNodeInfo.nodeIp==''?'':thisNodeInfo.nodeIp
			},{
				fieldLabel:'上级IP',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入上级IP',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'baseNodeIp',
				value:thisNodeInfo.baseNodeIp==''?'':thisNodeInfo.baseNodeIp
			},{
				fieldLabel:'上级名称',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入上级名称',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'baseNodeName',
				value:thisNodeInfo.baseNodeName==''?'':thisNodeInfo.baseNodeName
			},{
				fieldLabel:'设备名称',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入设备名称',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'blackBoxName',
				value:thisNodeInfo.blackBoxName==''?'':thisNodeInfo.blackBoxName
			},{
				fieldLabel:'设备编码',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入设备编码',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'blackBoxCode',
				value:thisNodeInfo.blackBoxCode==''?'':thisNodeInfo.blackBoxCode
			},{
				fieldLabel:'设备IP',
				allowBlank:false,
				xtype : 'textfield',
				emptyText:'请输入设备IP',
				blankText:'此选项不能为空',
				width: 260,
				labelAlign : 'right',
				labelWidth : 75,
				height:22,
				id:'blackBoxIp',
				value:thisNodeInfo.blackBoxIp==''?'':thisNodeInfo.blackBoxIp
			}],
				buttons : [
			   		Ext.create('Ushine.buttons.Button', {
				   		text: '修改',
				   		baseCls: 't-btn-red',
				   		handler: function () {
//				   		    private String id;
//			            	private String nodeCode;//节点编码
//			            	private String nodeName;//节点名称
//			            	private String nodeIp;//节点ip
//			            	private String baseNodeIp;//上级节点ip，如果是部中心可为空
//			            	private String baseNodeName;//上级节点名称，如果是部中心可为空
//			            	private String registerTime;//注册时间
//			            	//黑盒记录信息
//			            	private String blackBoxName;//黑盒名称
//			            	private String blackBoxCode;//黑盒编号
//			            	private String blackBoxIp;//黑盒ip
				   			var nodeCode = Ext.getCmp('nodeCode').value;
				   			var nodeName = Ext.getCmp('nodeName').value;
				   			var nodeIp = Ext.getCmp('nodeIp').value;
				   			var baseNodeIp = Ext.getCmp('baseNodeIp').value;
				   			var baseNodeName = Ext.getCmp('baseNodeName').value;
				   			var blackBoxCode = Ext.getCmp('blackBoxCode').value;
				   			var blackBoxName = Ext.getCmp('blackBoxName').value;
				   			var blackBoxIp = Ext.getCmp('blackBoxIp').value;
				   			//判断必填选项是否为空
				   			if(nodeCode==''){
				   			 Ext.create('Ushine.utils.Msg').onInfo('节点编码不能为空!');
				   			 return ;
				   			}
				   			if(nodeName==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('节点名称不能为空!');
					   			 return ;
					   		}
				   			if(nodeIp==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('节点IP不能为空!');
					   			 return ;
					   		}
				   			if(blackBoxCode==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('设备编码不能为空!');
					   			 return ;
					   		}
				   			if(blackBoxName==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('设备名称不能为空!');
					   			 return ;
					   		}
				   			if(blackBoxIp==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('设备IP不能为空!');
					   			 return ;
					   		}
				   		//试用ajax请求服务器
		    				   Ext.Ajax.request({
		    					   url: 'saveThisNodeInfo.do',
		    					   actionMethods: {
		    						   create : 'POST',
		    						   read   : 'POST', // by default GET
		    						   update : 'POST',
		    						   destroy: 'POST'
		    					   },
		    					   //要修改公司的id的集合
		    					   params: {
		    						   nodeCode:nodeCode,
		    						   nodeName:nodeName,
		    						   nodeIp:nodeIp,
		    						   baseNodeIp:baseNodeIp,
		    						   baseNodeName:baseNodeName,
		    						   blackBoxCode:blackBoxCode,
		    						   blackBoxName:blackBoxName,
		    						   blackBoxIp:blackBoxIp
		    					   },
		    					   success: function(response){
		    						   var text = response.responseText;
		    						   var obj=Ext.JSON.decode(text);
		    						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
		    					   },
		    					   failure: function(form, action) {
		    						   Ext.create('Ushine.utils.Msg').onInfo('请求服务器失败,请联系管理员!');
		    					   }
		    				   });
				   		}
				   	}),
				   Ext.create('Ushine.buttons.Button', {
				   		text: '重置',
				   		baseCls: 't-btn-yellow',
				   		handler: function () {
				   			_form.getForm().reset();
				   		}
				   	})
			   ]
		});
    	var win = Ext.create('Ushine.win.Window', {
			title : "注册节点信息",
			modal : true,
			layout : 'fit',
			border : false,
			closable : true,
			draggable:true,
			resizable : false,
			plain : true,
			borer:false,
			height:465,
			width:580,
			items : [_form],
		});
		win.show();
    }
});


//选择地图到id为screenShow_map
function screenShowMap(){
//	var screenShowMapId = "screenShow_map1";
//		var dataPath = "map_data/china.json";
//		var cx = "105";
//		var cy = "38";
//		var size = 0;
//		//声明放置地图容器的宽高
//		var width = document.getElementById("center_frame-body").style.width;
//		var height = document.getElementById("center_frame-body").style.height;
//		width = width.substring(0,width.length-2)-380;
//		height = height.substring(0,height.length-2)-0;
//		console.log(document.getElementById(screenShowMapId));
//		document.getElementById(screenShowMapId).innerHTML="";
//		
//		//声明放置地图容器的宽高
//		var svg = d3.select("#"+screenShowMapId+"")
//			.append("svg")
//			.attr("width", width)
//			.attr("height", height)
//			.attr("id","svg")
//			.style("border","1px solid blue");
//		
//		
//		d3.json("map_data/world_3m.json", function(error, toporoot) {
//			size = width/2;
//			
//			//用于放大地图	
//			var proj = d3.geo.mercator().center([cx, cy]).scale(size==0?getZoomScale(toporoot.features, width, height)*100:size).translate([width / 2, height / 2]);
//	    	var path = d3.geo.path().projection(proj);
//			if (error) 
//				return console.error(error);
//			
//			//将TopoJSON对象转换成GeoJSON，保存在georoot中
//			var georoot = toporoot
//			//包含中国各省路径的分组元素
//			var china = svg.append("g");
//			//添加中国各种的路径元素
//			china.selectAll("path")
//					.data(georoot.features)
//					.enter()
//					.append("path")
//					.attr("width",width)
//					.attr("height", height)
//					//.attr("class","province")
//					.style("fill", "#ccc")
//					.attr("d", path )
//					.style("fill","#ccc")
//					.style("stroke","#fff")
//					//.style("stroke-width",".5px")
//		});
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		var screenShowMapId = "screenShow_map";
//		var dataPath = "map_data/china.json";
//		var cx = "105";
//		var cy = "38";
//		var size = 0;
//		//声明放置地图容器的宽高
//		var width = document.getElementById("center_frame-body").style.width;
//		var height = document.getElementById("center_frame-body").style.height;
//		width = width.substring(0,width.length-2)-380;
//		height = height.substring(0,height.length-2)-0;
//		console.log(screenShowMapId);
//		document.getElementById(screenShowMapId).innerHTML="";
//		//width = 1900;
//		//实例选中地区的名称
//		var tooltip = d3.select("#tooltip");
//		d3.select("#"+screenShowMapId+"").append("div")
//		.text("" +
//				"节点状态图")
//		.attr("width","100%")
//		.style("height","25")
//		.style("line-height","2")
//		.style("background-color","#E6E6E6")
//		.style("color","#000000");
//		//声明放置地图容器的宽高
//		var svg = d3.select("#"+screenShowMapId+"")
//			.append("svg")
//			.attr("width", width)
//			.attr("height", height)
//			.attr("id","svg")
//			.style("border","1px solid blue")
//			//.style("background-image","url(img/mapBackground.jpg)")
//			//.style("background-size",""+(width+200)+" "+(height+50)+"")
//		
//		var a = "";
//		d3.json(dataPath, function(error, toporoot) {
//			//用于放大地图	
//			var projection = d3.geo.mercator()
//			 .center(getCenters(toporoot.features))
//			.translate([width/2.1,height/2])
//			.scale(//50*15
//					getZoomScale(toporoot.features, width, height)*41	
//			);
//			var path = d3.geo.path().projection(projection);
//			if (error) 
//				return console.error(error);
//			
//			//将TopoJSON对象转换成GeoJSON，保存在georoot中
//			var georoot = toporoot
//			//包含中国各省路径的分组元素
//			var china = svg.append("g");
//			//添加中国各种的路径元素
//			var provinces = china.selectAll("path")
//			
//					.data(georoot.features )
//					.enter()
//					.append("path")
//					.attr("class","province")
//					.style("fill", "#ccc")
//					.attr("d", path )
//					.style("fill","#ccc")
//					.style("stroke","#fff")
//					.style("stroke-width",".5px")	
//					.on("mouseover", function (d,i) {   //鼠标聚焦事件
//		                    //tooltip.style("display", null);
//		                    // name @ d.properties.name
//		                   // showText(d.properties.name);
//		                    //console.log(d3.select(d));
//		                    a = this.style.fill;
//		                    d3.select(this).style("fill","#FF9302");
//		                    
//		                })
//		                .on("mouseout", function (d,i) {   //鼠标离焦事件
//		                    //tooltip.style("display", "none");
//		                    d3.select(this).style("fill",a);
//		                   
//		                })
//		                .on("click", function(d){    //鼠标单击事件
//		                	var name = d.properties.name;
//		                	var code = d.properties.id;
//		                	
//		                	//查询当前点击节点的黑匣子最新数据
//		                	index_checkThisBlackBoxBaseData(code);
//		                	//Ext.getCmp('equipmentInfo').setText(name+'设备信息');
//		                })
//		                .on("dblclick", function(d, x, y){   //鼠标双击事件
//		                	var id = d.properties.id;
//		                	var name = d.properties.name;
//		                	var dataPath = (d.properties.id.length==4?d.properties.id+"00":d.properties.id);
//		                	var cx = d.properties.cp[0];
//		                	var cy = d.properties.cp[1];
//		                	var size = (d.properties.size?d.properties.size:"");
//		                	try{
//		                		Ext.getCmp('windowMap').close();
//		                	}catch(e){
//		                		
//		                	}
//		                	
//		                	
//		                	
//		                	//创建窗体显示map
//		                	Ext.create('Ushine.map.MapUtil').windowMap(name,false,"center_frame-body");
//		                	mapShow(dataPath,cx,cy,size,'windowMap-body');
//		            		
//		                });
//			
//			//为每个区域设定颜色
//			d3.json(dataPath, function(error, valuedata){
//				//设定指定省份的颜色
//				provinces.style("fill", function(d,i){
//					return "#0581c0";
//				});
//
//			});
//			
//			
//			
//
//			
//			//为每个区域添加标注，
//			d3.json(dataPath, function(error, places ) {
//				//插入分组元素
//				var location = svg.selectAll("location")
//								.data(places.features)
//								.enter()
//								.append("g")
//								.attr("class","location")
//								.attr("transform",function(d){
//									//处理甘肃省和内蒙古标注地址
//									if(d.properties.id=='620000'){
//										var coor = projection([(d.properties.cp[0]+1), (d.properties.cp[1]+0.7)]);
//										return "translate("+ coor[0] + "," + coor[1] +")";
//									}
//									if(d.properties.id=='150000'){
//										var coor = projection([d.properties.cp[0], (d.properties.cp[1]-3)]);
//										return "translate("+ coor[0] + "," + coor[1] +")";
//									}
//									
//									//计算标注点的位置
//									var coor = projection([d.properties.cp[0], d.properties.cp[1]]);
//									return "translate("+ coor[0] + "," + coor[1] +")";
//								});
//			
//				//为每个标注旁添加文字
//				location.append("text")
//				.attr("font-size","12px")
//				.attr("fill","white")
//				.text(function(d){
//					return d.properties.name;
//				});
//				
//				
//				//插入一张图片
//				location.append("image")
//					.attr("width",13)
//					.attr("height",13)
//					.attr("xlink:href",function(d){
//						return "img/bullet_yellow.png";
//					});
//				
//			});
//		});
//		//setInterval
//		//查询本机黑匣子数据
//		index_checkThisBlackBoxBaseData();
//		d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
//			svg.html(function(d){
//				return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
//			});
//			
//			var gSouthSea = d3.select("#southsea");
//			
//			gSouthSea.attr("transform","translate(540,410)scale(0.5)")
//				.attr("class","southsea");
//
//		});
		
		

	
} 

//查询本机的黑匣子数据并渲染到页面容器中
function index_checkThisBlackBoxBaseData(code){
	if(renovateStatus!=null && renovateStatus!=''){
		clearTimeout(renovateStatus);
		renovateStatus = null;
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
				  Ext.getCmp('index_deviceNmae').setText('该节点无权限查看或未注册');
				  // Ext.create('Ushine.utils.Msg').onInfo('该节点无权限查看或未注册');
				   return ;
			   }
			   //设置黑匣子数据到div容器中
			   Ext.getCmp('index_deviceNmae').setText(obj.deviceNmae);//设备名称
			   Ext.getCmp('index_theTemperature').setText(obj.theTemperature);//当前温度
			   Ext.getCmp('index_hardDisk').setText(obj.hardDiskName+'('+obj.hardDiskUnilizationRatio+')');//硬盘信息
			   Ext.getCmp('index_memory').setText(obj.memoryName+'('+obj.memoryUnilizationRatio+')');//内存信息
			   Ext.getCmp('index_cpu').setText(obj.cpuName+'('+obj.cpuUnilizationRatio+')');//cpu信息
			   Ext.getCmp('index_manageCenterConnected').setSrc(checkDataStatus(obj.manageCenterConnected));//管理中心连通状态  
			   Ext.getCmp('index_theAuditSystem').setSrc(checkDataStatus(obj.theAuditSystem));//审计系统状态
			   Ext.getCmp('index_RAIDStatusMonitoring').setSrc(checkDataStatus(obj.RAIDStatusMonitoring));//RAID状态监测状态
			   Ext.getCmp('index_toSubmitTheOperation').setSrc(checkDataStatus(obj.toSubmitTheOperation));//报送操作日志状态
			   Ext.getCmp('index_openBoxMonitoring').setSrc(checkDataStatus(obj.openBoxMonitoring));//开箱监测状态
			   Ext.getCmp('index_theBypassMonitoring').setSrc(checkDataStatus(obj.theBypassMonitoring));//旁路监测状态
			   Ext.getCmp('index_cpuMonitoring').setSrc(checkDataStatus(obj.cpuMonitoring));//cpu状态
			   Ext.getCmp('index_noTrafficMonitoring').setSrc(checkDataStatus(obj.noTrafficMonitoring));//无流量监测
			   Ext.getCmp('index_memoryMonitoring').setSrc(checkDataStatus(obj.memoryMonitoring));//内存监测
			   Ext.getCmp('index_diskAMonitoring').setSrc(checkDataStatus(obj.diskAMonitoring));//磁盘A监测
			   Ext.getCmp('index_diskBMonitoring').setSrc(checkDataStatus(obj.diskBMonitoring));//磁盘B监测
			   Ext.getCmp('index_diskCapacityMonitoring').setSrc(checkDataStatus(obj.diskCapacityMonitoring));//磁盘容量监测
			   //取得34个省市的html元素，用于该表其颜色
				//var tag = document.getElementById("svg").getElementsByTagName("g")[0].getElementsByTagName("path");

		   },
		   failure: function(form, action) {
			   Ext.create('Ushine.utils.Msg').onInfo('请求服务失败，请联系管理员');
		   }
	   });
	   renovateStatus = window.setTimeout("index_checkThisBlackBoxBaseData()",500000);
}