Ext.define('Ushine.dataSearch.DataSearchPenelGridPanel',{
	extend:'Ext.grid.Panel',
	id:'dataSearchPenelGridPanelgridpanel',
	itemId:'c_dataSearch_grid',
	flex:1,
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
			model:'DataSearchInfoModel',
			remoteSort:true,
			proxy:{
				type:'ajax',
				url:'dataSearch.do',
				simpleSortMode:true,
				reader:{
	                type: 'json',
	                root: 'datas',
					totalProperty:'paging.totalRecord'
				},
			},
			listeners: {
				//页面加载事件
				'beforeload':function(thiz, options) {
					var content = Ext.getCmp("content").value;  //开始时间
					//设置查询参数
					if(!options.params) options.params = {};
					options.params.content=content;
				}
			},
			autoLoad:true
			
		});
		this.columns=[
		    {text:'id',dataIndex:'id',flex: 1,menuDisabled:true,hidden:true},
		    //{text: '类型',  dataIndex: 'type',sortable: false,flex: 1,menuDisabled:true},
	        {text: '数据类型',  dataIndex: 'type',width:120,menuDisabled:true,renderer:function(value){
	        	if(value=='personStore'){
	         		  return "人员";
	         	  }else if(value=='organizStore'){
	         		  return "组织";
	         	  }else if(value=='clueStore'){
	         		  return "线索";
	         	  }else if(value=='leadSpeakStore'){
	         		  return "领导讲话";
	         	  }else if(value=='websiteJournalStore'){
	         		  return "媒体网刊";
	         	  }else if(value=='outsideDocStore'){
	         		  return "外来文档";
	         	  }else if(value=='vocationalWorkStore'){
	         		  return "业务文档";
	         	  }else{
	         		  return value;
	         	  }
	        }},
		    {text: '简介',dataIndex: 'value',sortable: false,flex: 1,menuDisabled:true}
		];
		this.loadMask =false;
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
	},listeners: {
		'itemdblclick':function(obj,record,index, eOpts){
			var c = this;
			//获得双击的数据
			var data=record.data;
			//获得当前数据的基础库类型
			var type = data.type;
			if(type=='personStore'){
				Ext.create('Ushine.utils.WinUtils').abc('PersonStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.personInfo.PersonInfoManage(data.id));
	        	
			}else if(type=='organizStore'){
				Ext.create('Ushine.utils.WinUtils').abc('PersonStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.personInfo.OrganizeInfoManage(data.id));
	        	
			}else if(type=='clueStore'){
				Ext.create('Ushine.utils.WinUtils').abc('ClueStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.cluesInfo.CluesPenel(data.id));
	        	
			}else if(type=='leadSpeakStore'){
				Ext.create('Ushine.utils.WinUtils').abc('DocStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.docInfo.LeaderTalk(data.id));
			}else if(type=='websiteJournalStore'){
				Ext.create('Ushine.utils.WinUtils').abc('PersonStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.docInfo.MediaNetworkBook(data.id));
	        	
			}else if(type=='outsideDocStore'){
				Ext.create('Ushine.utils.WinUtils').abc('DocStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.docInfo.ForeignDocument(data.id));
	        	
			}else if(type=='vocationalWorkStore'){
				Ext.create('Ushine.utils.WinUtils').abc('DocStoreManage');
				//sss
				Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.docInfo.ServiceDocument(data.id));
	        	
			}
		},
		'cellcontextmenu':function( thiz, td, cellIndex, record, tr, rowIndex, e, eOpts){
			this.rightClick( thiz, td, cellIndex, record, tr, rowIndex, e, eOpts);
		}
	},rightClick:function( thiz, td, cellIndex, record, tr, rowIndex, e, eOpts){
		var self=this;
		if(this.menu){
			this.menu.hide(true);
			this.menu.removeAll(true);
		}else{
			this.menu = new Ext.menu.Menu();
		}
		this.menu.addCls('context-menu');
	    this.menu.add({
	    	text:'线索人员',
	    	cls:'context-btn',
	    	handler:function(){
	    		Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.cluesInfo.CluePerson(record.data.id));
	    	}
	    },{
	    	text:'线索组织',
	    	cls:'context-btn',
	    	handler:function(){
	    		Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.cluesInfo.ClueOrganiz(record.data.id));
	    	}
	    },{
	    	text:'线索媒体网站',
	    	cls:'context-btn',
	    	handler:function(){
	    		Ext.getCmp('content_frame').removeAll();
	        	Ext.getCmp('content_frame').add(new Ushine.cluesInfo.ClueMediaNetworkBook(record.data.id));
	    	}
	    });
	    this.menu.showAt(e.getPoint());
	}
});
//数据模型
Ext.define('DataSearchInfoModel', {
	extend: 'Ext.data.Model',
    fields: [
             {name: 'id', type:'string', mapping: 'id'},
             {name: 'type', type:'string', mapping: 'type'},
             {name: 'value', type:'string', mapping: 'value'}
             ],idProperty:'id'
});

//显示详细信息
Ext.define('ServiceClueDetailWin',{
	extend:'Ushine.win.Window',
	title:'线索详情信息',
	modal : true,
	layout:{
		type : 'vbox',
		align:'stretch'
	},
	id:'serviceCluedetailwin',
	border : false,
	closable : true,
	draggable:true,
	resizable : false,
	plain : true,
	borer:false,
	height:250,
	width:600,
	bodyPadding:20,
	autoScroll:true,
	constructor:function(config){
		this.config=config
		this.items=[{
			//基本信息
			xtype:'fieldset',
			layout:'vbox',
			autoScroll:true,
			//height:100,
			flex:1,
			items:[{
				layout:'hbox',
				margin:'10 0 0 0',
				border:false,
				items:[{
					xtype:'displayfield',
					fieldLabel:'线索名称',
					value:config.record.get('clueName'),
					width: 250,
					labelAlign : 'right',
					labelWidth : 60
				},{
					xtype:'displayfield',
					fieldLabel:'线索来源',
					value:config.record.get('clueSource'),
					width: 250,
					labelAlign : 'right',
					labelWidth : 60
				}]
			},{
				//第二行
				layout:'hbox',
				margin:'10 0 0 0',
				border:false,
				items:[{
					xtype:'displayfield',
					fieldLabel:'发现时间',
					value:Ext.Date.format(config.record.get('findTime'), 'Y-m-d H:i'),
					width: 250,
					labelAlign : 'right',
					labelWidth : 60
				}]
			},{
				//第三行
				layout:'hbox',
				margin:'10 0 0 0',
				border:false,
				items:[{
					xtype:'displayfield',
					fieldLabel:'线索内容',
					//value:config.record.get('time'),
					width: 250,
					labelAlign : 'right',
					labelWidth : 60,
					value:"<a href='javascript:void(0)' onclick=showServiceClueUmentDetail()>" +
				  			"点击查看线索内容<a/>"
				}]
			},{
				//第三行
				layout:'hbox',
				margin:'10 0 0 0',
				border:false,
				items:[{
					xtype:'displayfield',
					fieldLabel:'进展情况',
					//value:config.record.get('time'),
					width: 250,
					labelAlign : 'right',
					labelWidth : 60,
					value:"<a href='javascript:void(0)' onclick=showServiceClueUmentDetailjin()>" +
				  			"点击查看进展情况<a/>"
				}]
			}]
		}]
		this.callParent();
	}
});



//显示进展
function showServiceClueUmentDetailjin(){
	//query函数能够获得多个符合条件的
	var window=Ext.getCmp('serviceCluedetailwin');
	var config=window.config.record.data;
	Ext.create('ShowServiceClueUmentTheOriginaljin',{
		clueName:config.clueName,
		arrangeAndEvolveCondition:config.arrangeAndEvolveCondition
	}).show();
}


//线索进展
Ext.define('ShowServiceClueUmentTheOriginaljin',{
	width:1000,
	height:800,
	autoScroll:true,
	extend:'Ushine.win.Window',
	constructor:function(config){
		this.title=config.clueName+'进展情况',
		this.html=config.arrangeAndEvolveCondition;
		this.callParent();
	}
});
//显示线索内容
function showServiceClueUmentDetail(){
	//query函数能够获得多个符合条件的
	var window=Ext.getCmp('serviceCluedetailwin');
	var config=window.config.record.data;
	Ext.create('ShowServiceClueUmentTheOriginal',{
		clueName:config.clueName,
		clueContent:config.clueContent
	}).show();
}


//线索内容
Ext.define('ShowServiceClueUmentTheOriginal',{
	width:1000,
	height:800,
	autoScroll:true,
	extend:'Ushine.win.Window',
	constructor:function(config){
		this.title=config.clueName+'线索内容',
		this.html=config.clueContent;
		this.callParent();
	}
});