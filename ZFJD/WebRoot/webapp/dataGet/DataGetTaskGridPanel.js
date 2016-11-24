/**
 * * 数据获取任务panel
 * @author wangbailin
 */
Ext.define('Ushine.dataGet.DataGetTaskGridPanel', {
	extend: 'Ext.grid.Panel',
	id:'dataGet_DataGetTaskGridPanel',
	flex:1,
	stripeRows:false,		    //True来实现隔行换颜色
	autoHeight : true,
	disableSelection: false,   //是否禁止行选择，默认false
	columnLines:true,		   //添加列的框线样式
    loadMask: true,           //是否在加载数据时显示遮罩效果，默认为false
	selType: 'checkboxmodel', // 复选框
	defaults: {sortable: false},
	height:'100%',
	margin:'0 0 0 0',
	columns:[],
	border:false,
	viewConfig:{
		emptyText: '没有数据',
    	stripeRows:true,//在表格中显示斑马线
    	enableTextSelection:true //可以复制单元格文字
	},
	constructor: function() {
		var self=this;
		var store=Ext.create('Ext.data.JsonStore',{
			pageSize:50,
			model:'DataGetTaskGridPanelModel',
			remoteStore:true,
		   //请求后台服务
			proxy:{
				type:'ajax',
				url:'findDataGetTask.do',
				simpleSortMode:true,
				reader:{
	                type: 'json',
	                root: 'datas',
					totalProperty:'paging.totalRecord'
				}
			},
			listeners: {
				//页面加载事件
				'beforeload':function(thiz, options) {
					
				}
			},
			autoLoad:true
		});
		
		this.columns=[
			//设置列id
			{text:'id',dataIndex:'id',flex: 1,menuDisabled:true,hidden:true},
		    {text: '数据名称',  dataIndex: 'dataName',sortable: false,flex: 1,menuDisabled:true},
		    {text: '数据类型',  dataIndex: 'dataType',sortable: false,flex: 1,menuDisabled:true},
		    {text: '系统名称',  dataIndex: 'systemName',sortable: false,flex: 1,menuDisabled:true},
		    {text: '系统版本',  dataIndex: 'systemVersion',sortable: false,flex: 1 ,menuDisabled:true},
		    {text: '行政区域',  dataIndex: 'administrativeArea',sortable: false,flex: 1,menuDisabled:true},
		    {text: '取货进度',  dataIndex: 'getSchedule',sortable: false,flex: 1,menuDisabled:true},
		    {text: '获取时间',  dataIndex: 'getDate',width:160,menuDisabled:true,renderer:function(value){
	         	   return Ext.Date.format(value, 'Y-m-d H:i:s');
	        }}
		    //{text: '原文',  dataIndex: 'theOriginal',sortable: false,flex: 1,menuDisabled:true},
		];
		this.loadMask =true;
		//创建面板底部的工具条
		this.bbar = new Ext.PagingToolbar({
			//一个要和Ext.data.Store参与绑定并且自动提供翻页控制的工具栏
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
		this.listeners={
			//双击事件
			itemdblclick:function(thiz, record, item, index, e, eOpts){
				var id = record.data.id+"dataGet";
				var dataName = record.data.dataName;
				var systemName = record.data.systemName;
				var dataType = record.data.dataType;
				//得到显示日志选项卡容器
				var dataGetLog = Ext.getCmp('dataGetLog');
				//得到容器集合
				var dataGetLogOptionBolock =  dataGetLog.items.items
				//判断当前选择的身份或者id是否存在选项卡中，如果存在提示用户，该省份或者城市注册系统已存在，
				for(var i = 0; i<dataGetLogOptionBolock.length;i++){
					if(dataGetLogOptionBolock[i].id ==  id){
						Ext.create('Ushine.utils.Msg').onInfo('下列选项卡中已有该任务数据!');
						return;
					}
				}
				dataGetLog.add({
					title:dataName,
					id:id,
					border:false,
					height:'100%',
					layout:'hbox',
					flex: 1,
					closable:true,
					items:new Ushine.dataGet.DataGetTaskLogDataPanel(id,systemName,dataType)
					
				});
				dataGetLog.setActiveTab(dataGetLogOptionBolock.length-1);
			}
		};
		this.callParent();
	}
});
//数据模型
Ext.define('DataGetTaskGridPanelModel', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type:'string', mapping: 'id'},
             {name: 'dataName', type:'string', mapping: 'dataName'},
             {name: 'dataType', type:'string', mapping: 'dataType'},
             {name: 'systemName', type:'string', mapping: 'systemName'},
             {name: 'systemVersion', type:'string', mapping: 'systemVersion'},
             {name: 'administrativeArea', type:'string', mapping: 'administrativeArea'},
             {name: 'getSchedule', type:'string', mapping: 'getSchedule'},
             {name: 'getDate', type:'date', mapping: 'getDate'}],
    idProperty:'id'
});