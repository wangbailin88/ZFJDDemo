/**
 * * 业务系统插件管理gridPanel
 * @author wangbailin
 */
Ext.define('Ushine.businessSysManage.BusinessSysPlugIinBoardGridPanel', {
	extend: 'Ext.grid.Panel',
	flex:1,
	id:'businessSysPlugIinBoardGridPanel',
	stripeRows:false,		    //True来实现隔行换颜色
	autoHeight : true,
	disableSelection: false,   //是否禁止行选择，默认false
	columnLines:true,		   //添加列的框线样式
    loadMask: true,           //是否在加载数据时显示遮罩效果，默认为false
	selType: 'checkboxmodel', // 复选框
	defaults: {sortable: false},
	height:'100%',
	columns:[],
	viewConfig:{
		emptyText: '没有数据',
    	stripeRows:true,//在表格中显示斑马线
    	enableTextSelection:true //可以复制单元格文字
	},
	constructor: function(id) {
		if(id==null || id==''){
			id = 'd';
		}
		var self=this;
		var store=Ext.create('Ext.data.JsonStore',{
			pageSize:50,
			model:'BusinessSysPlugIinBoardGridPanelModel',
			remoteStore:true,
		   //请求后台服务
			proxy:{
				type:'ajax',
				url:'findVocationalWorkVersionBySystemId.do?systemId='+id,
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
		    {text: '文件名称',  dataIndex: 'fileName',sortable: false,flex: 1,menuDisabled:true},
		    {text: '插件版本',  dataIndex: 'version',sortable: false,flex: 1,menuDisabled:true},
		    {text: '上传日期',  dataIndex: 'uploadTime',flex: 1,menuDisabled:true,renderer:function(value){
	         	   return Ext.Date.format(value, 'Y-m-d H:i:s');
	        }},
	        {text: '备注',  dataIndex: 'remarks',sortable: false,flex: 1,menuDisabled:true}
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
				//详细信息
				//console.log(record);
				Ext.create('ServiceDocDetailWin',{
					record:record
				}).show();
			}
		};
		this.callParent();
	}
});
//数据模型
Ext.define('BusinessSysPlugIinBoardGridPanelModel', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type:'string', mapping: 'id'},
             {name: 'fileName', type:'string', mapping: 'fileName'},
             {name: 'version', type:'string', mapping: 'version'},
             {name: 'uploadTime', type:'date', mapping: 'uploadTime'},
             {name: 'remarks', type:'string', mapping: 'remarks'}
             ],
    idProperty:'id'
});