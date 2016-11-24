/**
 * logGrid Panel
 * @author 陈曦
 */
Ext.define('Ushine.system.log.LogGridPanel', {
	extend: 'Ext.grid.Panel',
	//enableColumnResize:是否允许改变列宽，默认为true
	id:"log_grid_panel_id",
	flex: 1,
	itemId:'logGrid',
	height:200,
	stripeRows:false,		    //True来实现隔行换颜色
	autoHeight : true,
	disableSelection: false,   //是否禁止行选择，默认false
	columnLines:true,		   //添加列的框线样式
    loadMask: true,           //是否在加载数据时显示遮罩效果，默认为false
	selType: 'checkboxmodel', // 复选框
	defaults: {sortable: false},
	columns:[],
	viewConfig:{
		emptyText: '没有数据',
    	stripeRows:true,//在表格中显示斑马线
    	enableTextSelection:true //可以复制单元格文字
	},
	constructor: function() {
		var self = this;
		var store = Ext.create('Ext.data.JsonStore',{
			pageSize:40,
			model:'LogModel',
			remoteSort:true,
			proxy:{
				type:'ajax',
				url:'findLog.do',
				extraParams:{
					userName:null,
					userCode:null,
					runTime:null,
					endTime:null,
					type:null,
					IP:null
				},
				simpleSortMode:true,
				reader:{
	                type: 'json',
	                root: 'datas',
					totalProperty:'paging.totalRecord'
				}
			},
			autoLoad:true
		});
		this.columns=[
		    {text:'ID',dataIndex:'id',hidden:true},
		    {text: '人员编号',  dataIndex: 'userCode',width:100,menuDisabled:true},
		    {text: '操作人员',  dataIndex: 'userName',width:100,menuDisabled:true},
		    {text: '操作者IP',  dataIndex: 'IP',width:100,menuDisabled:true},
		    {text: '操作类型',  dataIndex: 'typeStr',width:100,menuDisabled:true},
		    {text: '操作结果',  dataIndex: 'result',width:180,menuDisabled:true},
		    {text: '操作时间',  dataIndex: 'dateTime',width:180,menuDisabled:true},
		    {text: '操作参数',  dataIndex: 'params',flex: 1,menuDisabled:true},
		    {text: '操作',sortable: false,width:130,xtype:'actioncolumn',
		    	items:[{
		            icon: 'images/page_white_text.png',
		            tooltip: '查看详情',
		            handler: function(grid, rowIndex, colIndex){
		            	var data = self.store.getAt(rowIndex).data;
		                self.logWithRecord(data);
		            }
	             }
	            ],menuDisabled:true
		    }];
		this.loadMask =true;
		//创建面板底部的工具条
		this.bbar = new Ext.PagingToolbar({//一个要和Ext.data.Store参与绑定并且自动提供翻页控制的工具栏
			store : store,  		//数据源
			displayInfo: true,   		//是否显示信息(true表示显示信息)
			firstText: '首页', 	 		//第一页文本 显示第一页按钮的快捷提示文本
			lastText: '末页', 	 		//最后一页的文本 显示最后一页按钮快捷提示文本
			prevText: '上一页',   		//上一个导航按钮工具条
			nextText: '下一页',  			//下一个导航按钮工具条
			refreshText: '刷新',			//刷新的文本 显示刷新按钮的快捷提示文本
			beforePageText: '当前第', 	//输入项前的文字 输入项前的文本显示。
			afterPageText: '/{0}页', 	//输入项后的文字 可定制的默认输入项后的文字
			displayMsg: '本页显示第{0}条到第{1}条, 共有{2}条记录', //显示消息 显示分页状态的消息
			emptyMsg: '没有查找记录'  		//空消息 没有找到记录时，显示该消息
		});
		this.store = store;
		this.callParent(); 
	},
	listeners: {
		'itemdblclick':function(obj,record,index, eOpts){
			this.logWithRecord(record.data);
		}
	},
	logWithRecord:function(data){
		var win = Ext.create('Ushine.win.Window',{
			title : '日志信息',
			border : false,
			closable : true,
			draggable:true,
			resizable : false,
			modal:true,
			width : 610,
			height : 350,
			plain : true,
		    layout: {
		        type: 'vbox',
		        padding:'0 10 0 10',
		        align: 'stretch'
		    },
		    border:false,
		    items:[{
		    	flex:4,
		    	title:'基本信息',
		    	xtype:'fieldset',
		    	height:425,
		    	layout:{
		    		type:'vbox',
		    		align:'stretch'
		    	},
		    	items:[{
		    		flex: 1,
			    	border:false,
			    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
				    layout: {
				        type: 'hbox',
				        align: 'stretch'
				    },
				    items:[
				        {
				        	flex: 1,
				        	xtype:'label',
				        	text:'人员编号：'+data.userCode
				        },{
				        	flex: 1,
				        	xtype:'label',
				        	text:'操作人员：'+data.userName
				        },{
				        	flex: 1,
				        	xtype:'label',
				    		text:'操作类型：'+data.typeStr
				        }]
		    	},{
		    		flex: 1,
			    	layout:'hbox',
			    	border:false,
			    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
			    	items:[{
			    		flex: 1,
			    		xtype:'label',
			    		text:'操作结果：'+data.result
			    	}
			    	]
		    	},{
		    		flex: 1,
			    	layout:'hbox',
			    	border:false,
			    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
			    	items:[{
			    		flex: 3,
			    		xtype:'label',
			    		text:'操作路径：'+data.url
			    	}
			    	]
		    	},{
		    		flex: 1,
			    	layout:'hbox',
			    	border:false,
			    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
			    	items:[{
			    		flex: 1,
			    		xtype:'label',
			        	text:'操作者IP：'+data.IP
			    	}
			    	]
		    	},{
		    		flex: 1,
		    		xtype:'label',
		    		text:'操作时间：'+data.dateTime
		    	},{
		    		flex: 1,
			    	layout:'hbox',
			    	border:false,
			    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
			    	items:[{
			    		flex: 1,
			    		xtype:'label',
			    		text:'请求参数：'
			    	}]
		    	},{
			    	xtype: 'fieldset',
					flex: 4,
				    layout: {
				        type: 'vbox',
				        align: 'stretch'
				    },
				    items:[{
				    	flex:4,
				    	layout:'hbox',
				    	border:false,
				    	margins: '8 0 0 0',
				    	bodyStyle: "background: transparent;font-family: '新宋体', Arial;font-size: 12px;color: #333;",
				    	items:[{
				    		flex: 1,
				    		xtype:'label',
				    		border:false,
				    		text:data.params
				    	}]
				    }]
			    }
		    	]
		    }]
		});
		win.show();
	}
});
// 数据模型
Ext.define('LogModel', {
	extend: 'Ext.data.Model',
    fields: [
    	'userName',
    	'userCode',
    	'IP','dateTime','type','id','params','url','typeStr','result'],
    idProperty:'id'
});
