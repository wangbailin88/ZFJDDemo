var interval = null;//心跳监控定时器
var findHeartbeatStatusInterval= null;//心跳定时器
Ext.define('Ushine.map.MapUtil', {
	
	//单击节点显示的设备信息
	noteEquipmentInfo:function(nodeName){
		//通过ajax到服务器获取单击节点的节点信息
//		 Ext.Ajax.request({
//			   url: 'citys.do',
//			   actionMethods: {
//				   create : 'POST',
//				   read   : 'POST', // by default GET
//				   update : 'POST',
//				   destroy: 'POST'
//			   },
//			   //要修改组织的id的集合
//			   params: {
//				   dataPath:dataPath
//			   },
//			   success: function(response){
//				   
//				   var text = response.responseText;
//				   var obj=Ext.JSON.decode(text);
//					
//			   },
//			   failure: function(form, action) {
//				   Ext.create('Ushine.utils.Msg').onInfo('禁用失败，请联系管理员');
//			   }
//		   });
//		
		
			var win = Ext.create('Ushine.win.Window', {
				title : nodeName+'节点设备', // 标题
				width : 350, // 宽度
				height: 406,
				id:'noteEquipmentInfo',
				maximizable: true,
				autoHeight : true, // 宽度
				collapsible : true, // 是否可以伸缩
				//plain : true, // 强制背景色
			//	iconCls : "houfei-addicon", // 图标样式
				modal : false, // 当前窗体为模态窗体
				bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
				layout:'vbox',
				items:[{//第一大行
					width:'100%',
					height:40,
					border:false,
					region : 'west',
					style:'border-bottom:1px dashed red;',
					layout:'hbox',
					margins:'0 0 5 0',
					items:[{
						flex: 1,
						width:'100%',
						height:'100%',
						border:false,
						region : 'west',
						items:[{
					        xtype: 'image',
					        forId: 'myFieldId',
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        text: '48',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        text: '48',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        text: '48',
					        margin: '8 0 0 10'
					    }]
					}]
				},{//第二大行
					width:'100%',
					height:146,
					border:false,
					region : 'west',
					style:'border-bottom:1px dashed red;',
					margin:'0 0 5 0',
					layout:'vbox',
					items:[{//上海市设备
						flex: 1,
						width:'100%',
						height:'100%',
						border:false,
						region : 'west',
						items:[{
					        xtype: 'label',
					        forId: 'myFieldId',
					        style:'font-size:15px;margin-top:10px;font-weight:bold;',
					        text: nodeName+'设备',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:22,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        style:'font-size:12px;margin-top:10px;',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        style:'font-size:12px;margin-top:10px;',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        style:'font-size:12px;margin-top:10px;',
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
					        style:'background-image:url(img/kafei.png);background-repeat:no-repeat;',
					        width:20,
					        margin: '8 0 0 10'
					    },{
					        xtype: 'label',
					        forId: 'myFieldId',
					        style:'font-size:12px;margin-top:10px;',
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
							flex: 1,
							width:'100%',
							height:'100%',
							layout:'hbox',
							border:false,
							region : 'west',
							items:[{
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        style:'font-size:12px;margin-top:10px;',
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
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        style:'font-size:12px;margin-top:10px;',
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
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin: '8 0 0 10'
						    },{
						        xtype: 'label',
						        forId: 'myFieldId',
						        style:'font-size:12px;margin-top:10px;',
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
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
						        text: '监管中心连通',
						        margin:'2 0 0 10'
						    },{
						    	flex: 1,
						        xtype: 'image',
						        forId: 'myFieldId',
						        style:'background-image:url(img/shan.png);background-repeat:no-repeat;',
						        width:20,
						        margin:'4 0 0 0'
						    }]
						}]
					}]
				}]
	        });
			win.show();
	},
	
	//心跳监控函数
	heartbeatControl:function(isHideHead){
		var height = 0;
		if(isHideHead){
			height = 200;
		}else{
			height = 250;
		}
		var win = Ext.create('Ushine.win.Window', {
			title : "设备连通信息", // 标题
			width : 350, // 宽度
			height: height,
			x:10,
			y:115,
			id:'heartbeatControl',
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			maximizable: true,
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : false, // 当前窗体为模态窗体
			html:'<div id = "chong" style="line-height:10px;overflow :auto;background-color:black;width:360px; height:220px;padding:20px;"><div>',
        });
		win.show();
		//判断是否隐藏头部
		if(isHideHead){
			var hide = Ext.getCmp('heartbeatControl_header');
			hide.hide();
		}
		//查询当前节点及下级节点的心跳信息
		findHeartbeatStatus();
    },
    //当前设备心跳监控
	thisHeartbeatControl:function(body){
//		var chart, timeAxis;
//	    
//	    var generateData = (function() {
//	        var data = [], i = 0,
//	            last = false,
//	            date = new Date(2011, 1, 1),
//	            seconds = +date,
//	            min = Math.min,
//	            max = Math.max,
//	            random = Math.random;
//	        return function() {
//	            data = data.slice();
//	            data.push({
//	                date:  Ext.Date.add(date, Ext.Date.DAY, i++),
//	                visits: min(50, max(last? last.visits + (random() - 0.5) * 10 : random() * 20, 0)),
//	                views: min(50, max(last? last.views + (random() - 0.5) * 5 : random() * 20, 0)),
//	                users: min(50, max(last? last.users + (random() - 0.5) * 10 : random() * 20, 0))
//	            });
//	            last = data[data.length -1];
//	            return data;
//	        };
//	    })();
//
//	    var group = false,
//	        groupOp = [{
//	            dateFormat: 'M d',
//	            groupBy: 'year,month,day'
//	        }, {
//	            dateFormat: 'M',
//	            groupBy: 'year,month'
//	        }];
//
//	    function regroup() {
//	        group = !group;
//	        var axis = chart.axes.get(1),
//	            selectedGroup = groupOp[+group];
//	        axis.dateFormat = selectedGroup.dateFormat;
//	        axis.groupBy = selectedGroup.groupBy;
//	        chart.redraw();
//	    }
//
//	    var store = Ext.create('Ext.data.JsonStore', {
//	        fields: ['date', 'visits', 'views', 'users'],
//	        data: generateData()
//	    });
//
//	    var intr = setInterval(function() {
//	        var gs = generateData();
//	        var toDate = timeAxis.toDate,
//	            lastDate = gs[gs.length - 1].date,
//	            markerIndex = chart.markerIndex || 0;
//	        if (+toDate < +lastDate) {
//	            markerIndex = 1;
//	            timeAxis.toDate = lastDate;
//	            timeAxis.fromDate = Ext.Date.add(Ext.Date.clone(timeAxis.fromDate), Ext.Date.DAY, 1);
//	            chart.markerIndex = markerIndex;
//	        }
//	        store.loadData(gs);
//	    }, 1000);
		var width = document.getElementById(body).style.width;
		var height = document.getElementById(body).style.height;
		width = width.substring(0,width.length-2)-750;
		height = height.substring(0,height.length-2);
		height = height.substring(0,height.length-2);
	    var win = Ext.create('Ext.window.Window', {
	        width: 360,
	        height: 140,//350,250
	        id:'thisHeartbeatControl',
	        collapsible : true, // 是否可以伸缩
	        maximizable: true,
	        title: '设备心跳响应',
	        x:width,
			y:120,
	        autohow: true,
	        html:'<div style="background: url(./img/ping.png) no-repeat;width:350px;height:112px;"></div>'
	    });
	    win.show();
	    //chart = win.child('#chartCmp');
	    //timeAxis = chart.axes.get(1);
    },
    //数据查询统计
    dataSelectCount:function(){
    	
    	 var chart = Ext.create('Ext.chart.Chart', {
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
         });
  

     var win = Ext.create('Ext.Window', {
         width: 350,
         height: 400,
         id:'dataSelectCount',
         collapsible : true, // 是否可以伸缩
         hidden: false,
         maximizable: true,
         title: '数据查询统计',
         renderTo: Ext.getBody(),
         layout: 'fit',
         
         items: chart
     });
    },
    //异常信息统计
    exceptionInfoCount:function(){
    	 store1.loadData(generateData(8));
    	    
    	    var chart = Ext.create('Ext.chart.Chart', {
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
    	        });

    	    var win = Ext.create('Ext.Window', {
    	        width: 350,
    	        height: 400,
    	        id:'exceptionInfoCount',
    	        x:1500,
    	        y:10,
    	        hidden: false,
    	        collapsible : true, // 是否可以伸缩
    	        maximizable: true,
    	        title: '异常信息统计',
    	        renderTo: Ext.getBody(),
    	        layout: 'fit',
    	        items: chart
    	    });
    },
    //关闭指定的窗体
    closeWindow:function(windowIdArr){
    	//清楚定时器timeout
    	
    	try{
    		if(renovateStatus!=null && renovateStatus!=''){
    			clearTimeout(renovateStatus);
    			renovateStatus = null;
    			}	
			}catch(e){
			
			}
    	
			try{
				if(interval!=null && interval!=''){
					clearTimeout(interval);
					interval = null;
				}	
			}catch(e){
			
			}
    	
			try{
				if(findHeartbeatStatusInterval!=null && findHeartbeatStatusInterval!=''){
					clearTimeout(findHeartbeatStatusInterval);
					findHeartbeatStatusInterval = null;
				}
			}catch(e){
			
			}
    	
			try{
				if(bigscreen_renovateStatus!=null && bigscreen_renovateStatus!=''){
					clearTimeout(bigscreen_renovateStatus);
					bigscreen_renovateStatus = null;
				}
			}catch(e){
				
			}
    	//关闭窗口
    		try{
    			//关闭设备连通信息窗体
				Ext.getCmp("heartbeatControl").close();
				//清楚设备连通信息心跳定时器
				clearInterval(interval);
			}catch(e){
				
			}
			try{
				//关闭当前设备心跳窗体
				Ext.getCmp("thisHeartbeatControl").close();
			}catch(e){
				
			}
			try{
				//关闭数据查询统计窗体
				Ext.getCmp("dataSelectCount").close();
			}catch(e){
				
			}
			try{
				//关闭异常信息统计窗体
				Ext.getCmp("exceptionInfoCount").close();
			}catch(e){
				
			}
			try{
				//关闭窗体显示地图窗体
				Ext.getCmp("windowMap").close();
			}catch(e){
				
			}
			try{
				//关闭显示单击地区区域的设备信息窗体
				Ext.getCmp("noteEquipmentInfo").close();
			}catch(e){
				
			}
    },
    //使用窗体显示地图
    windowMap:function(cityName,isHideHade,body){
    	var width = document.getElementById(body).style.width;
		var height = document.getElementById(body).style.height;
		width = width.substring(0,width.length-2)-870;
		height = height.substring(0,height.length-2)-430;
    	var win = Ext.create('Ext.window.Window', {
			title : cityName+'节点监控', // 标题
			width : 500, // 宽度
			height: 500,
			id:'windowMap',
			x:width,
			y:height,
			maximizable: true,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			modal : false, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;',
    	});
    	win.show();
    	//判断是否隐藏头部
		if(isHideHade){
			var hide = Ext.getCmp('windowMap_header');
			hide.hide();
		}
    }
});
//心跳状态数据
var HeartbeatStatus = [];
//查询当前节点及下级节点的心跳信息
function findHeartbeatStatus(){
	 Ext.Ajax.request({
	   url: 'findHeartbeatStatus.do',
	   actionMethods: {
		   create : 'POST',
		   read   : 'POST', // by default GET
		   update : 'POST',
		   destroy: 'POST'
	   },
	   success: function(response){
		   
		   var text = response.responseText;
		   var obj=Ext.JSON.decode(text);
		   HeartbeatStatus = obj;
		   //将HeartbeatStatus数据渲染到页面容器中
		   renovateHeartbeatControl();
	   },
	   failure: function(form, action) {
		   Ext.create('Ushine.utils.Msg').onInfo('请求心跳数据失败,请联系管理员');
	   }
});
}



//记录当前心跳容器div共有多少个数据元素
var renvoateHeartbeatCount = 0;
//记录当前显示数据的下标
var indexHeartbeatStatus = 0;
function renovateHeartbeatControl(){
	if(interval!=null && interval !=''){
		clearTimeout(interval);
		interval = null;
	}
	if(findHeartbeatStatusInterval!=null && findHeartbeatStatusInterval !=''){
		clearTimeout(findHeartbeatStatusInterval);
		findHeartbeatStatusInterval = null;
	}
	//判断indexHeartbeatStatus下标是否大于HeartbeatStatus的总个数据，如果大于，说明该数组数据已完成读取完并显示到页面中了
	if(indexHeartbeatStatus>=HeartbeatStatus.length){
		indexHeartbeatStatus = 0; //记录下标归零
		findHeartbeatStatusInterval = window.setTimeout("findHeartbeatStatus()",3000);
	}else{
		//获取用于展示心跳数据的div
		var chong = document.getElementById("chong");
		//为div新增心跳数据元素
		chong.innerHTML+=HeartbeatStatus[indexHeartbeatStatus];
		indexHeartbeatStatus++;
		//设置div下拉滚动条始终位于最下方
		chong.scrollTop = chong.scrollHeight;
		//获取该div中所有的p标签元素，并判断p标签元素的数据是否大于1000，如果大于1000就删除前990个
		//var d = chong.getElementsByTagName("p");
		if(renvoateHeartbeatCount >=300){
			chong.innerHTML="";
			renvoateHeartbeatCount = 0;
		}
		renvoateHeartbeatCount++;
		interval = window.setTimeout("renovateHeartbeatControl()",3000);
	}
}


var color = [];
function mapShow(dataPath,cx,cy,size,windowMapId){
	if(dataPath == "" ||dataPath == null){
		dataPath = "map_data/china.json";
	}else{
		dataPath = "map_data/geometryProvince/"+dataPath+".json";
	}
	if(cx==null||cx == ""){
		cx = "105";
	}
	if(cy==null||cy == ""){
		cy = "38";
	}
	
	if(size == null || size == ""){
		size = 0;
	}else{
		size = size;
	}
	
	//Ext.getCmp(windowMapId).removeAll();
	
	//声明放置地图容器的宽高
	var width = document.getElementById(windowMapId).style.width;
	var height = document.getElementById(windowMapId).style.height;
	
	width = width.substring(0,width.length-2);
	
	height = height.substring(0,height.length-2);
	document.getElementById(windowMapId).innerHTML="";
	//width = 1900;
	//实例选中地区的名称
	var tooltip = d3.select("#tooltip");
	//声明放置地图容器的宽高
	var svg = d3.select("#"+windowMapId+"").append("svg")
		.attr("width", width)
		.attr("height", height)
		.attr("id","svg")
		.style("background-image","url(img/mapBackground.jpg)")
	var a = "";
	d3.json(dataPath, function(error, toporoot) {
		var projection = d3.geo.mercator()
		 .center(getCenters(toporoot.features))
		.translate([width/2,height/2])
		.scale(//50*15
				getZoomScale(toporoot.features, width, height)*50 	
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
	                    a = this.style.fill;
	                    d3.select(this).style("fill","#FF9302");
	                  //设定各省份的填充色
	                    
	                })
	                .on("mouseout", function (d,i) {   //鼠标离焦事件
	                    d3.select(this).style("fill",a);
	                   
	                })
	                .on("click", function(d){    //鼠标单击事件
	                	var code = d.properties.id;
	                	index_checkThisBlackBoxBaseData(code);
	                })
	                .on("dblclick", function(d, x, y){   //鼠标双击事件
	               
	            		
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
//				if(d.properties.name == '北京市'){
//					return "#F0E124";
//				}
//				var color = computeColor(t);
//				return color.toString();
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
			
			//插入一个圆形形式的标注
//			location.append("circle")
//				.attr("r",4)
//				.attr("fill","#34FA29");
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
	
	
	 
	d3.xml("map_data/southchinasea.svg", function(error, xmlDocument) {
		svg.html(function(d){
			return d3.select(this).html() + xmlDocument.getElementsByTagName("g")[0].outerHTML;
		});
		
		var gSouthSea = d3.select("#southsea");
		
		gSouthSea.attr("transform","translate(540,410)scale(0.5)")
			.attr("class","southsea");

	});

	

}
function mapShow1(dataPath,cx,cy,size,windowMapId){
	if(dataPath == "" ||dataPath == null){
		dataPath = "map_data/china.json";
	}else{
		dataPath = "map_data/geometryProvince/"+dataPath+".json";
	}
	if(cx==null||cx == ""){
		cx = "105";
	}
	if(cy==null||cy == ""){
		cy = "38";
	}
	
	if(size == null || size == ""){
		size = 0;
	}else{
		size = size;
	}
	
	//Ext.getCmp(windowMapId).removeAll();
	
	//声明放置地图容器的宽高
	var width = document.getElementById(windowMapId).style.width;
	var height = document.getElementById(windowMapId).style.height;
	
	width = width.substring(0,width.length-2);
	
	height = height.substring(0,height.length-2);
	document.getElementById(windowMapId).innerHTML="";
	//width = 1900;
	//实例选中地区的名称
	var tooltip = d3.select("#tooltip");
	//声明放置地图容器的宽高
	var svg = d3.select("#"+windowMapId+"").append("svg")
		.attr("width", width)
		.attr("height", height)
		.attr("id","svg")
		.style("background-image","url(img/mapBackground.jpg)")
	var a = "";
	d3.json(dataPath, function(error, toporoot) {
		var projection = d3.geo.mercator()
		 .center(getCenters(toporoot.features))
		.translate([width/2,height/2])
		.scale(//50*15
				getZoomScale(toporoot.features, width, height)*50 	
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
	                    a = this.style.fill;
	                    d3.select(this).style("fill","#FF9302");
	                  //设定各省份的填充色
	                    
	                })
	                .on("mouseout", function (d,i) {   //鼠标离焦事件
	                    d3.select(this).style("fill",a);
	                   
	                })
	                .on("click", function(d){    //鼠标单击事件
	                	var code = d.properties.id;
	                	//查询本机黑匣子数据
	            		bigscreen_checkThisBlackBoxBaseData(code);
	                })
	                .on("dblclick", function(d, x, y){   //鼠标双击事件
	               
	            		
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
//				if(d.properties.name == '北京市'){
//					return "#F0E124";
//				}
//				var color = computeColor(t);
//				return color.toString();
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
			
			//插入一个圆形形式的标注
//			location.append("circle")
//				.attr("r",4)
//				.attr("fill","#34FA29");
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
					return "img/bullet_yellow.png";
				});	
			//初始化地图每一个区域该有的颜色
			var tag = document.getElementById("svg").getElementsByTagName("g")[0].getElementsByTagName("path");
			for(var i =0;i<tag.length;i++){
				color.push(tag[i].style.fill);
			}
			//hello();
        	
			
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
//根据dataStatus判断状态，并返回显示状态相应的图片
function checkDataStatus(dataStatus){
	if(dataStatus=='1'){
		return "img/bullet_yellow.png";
	}else if(dataStatus=='2'){
		return "img/bullet_red.png";
	}
}