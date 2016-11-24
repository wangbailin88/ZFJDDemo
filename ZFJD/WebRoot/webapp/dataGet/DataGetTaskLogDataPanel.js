/**
 * 数据获取任务日志数据panel
 */
 Ext.define('Ushine.dataGet.DataGetTaskLogDataPanel', {
	extend: 'Ext.panel.Panel',
	border:false,
	height:'100%',
	width:'100%',
	bodyStyle: 'background-color: #ffffff; border: none;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	region: 'center',
	constructor: function(id,systemName,dataType) {
		if(id==null || id == ''){
			id = "21587455";
		}
		var self = this;
		this.tbar = [{
			fieldLabel: '条件筛选',
			labelAlign : 'right',
			labelWidth: 60,
			xtype:'combo',
			allowNegative: false,
			allowBlank: false,
			editable: false,
			hiddenName: 'colnum',
			name:'colnum',
			emptyText: '行政区域筛选',
			valueField: 'value',
			store:Ext.create('Ext.data.Store', {
				fields: ['text', 'value'],
					data : [
					        {"text":"线索名称", "value":"clueName"},
					        {"text":"线索来源", "value":"clueSource"},
					        {"text":"发现时间", "value":"findTime"}
					        ]
			}),
			width: 200,
			listeners:{
				 select:function(field,e){
                    	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
                 }
			}
	},{
    	xtype     : 'textfield',
    	name      : 'filtrateKeyword',
    	emptyText : '业务系统名称筛选',
		height: 24,
		labelAlign : 'right',
		width: 140,
		margin:'0 0 0 40',
		listeners:{
			 specialkey:function(field,e){
                if(e.getKey() == e.ENTER){
                	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
                }  
             }
		}
	},{
    	xtype     : 'textfield',
    	name      : 'filtrateKeyword',
    	emptyText : '操作人员筛选',
		height: 24,
		labelAlign : 'right',
		width: 140,
		margin:'0 0 0 40',
		listeners:{
			 specialkey:function(field,e){
                if(e.getKey() == e.ENTER){
                	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
                }  
             }
		}
	},{
		labelAlign : 'right',
		fieldLabel: '日期筛选',
		maxValue:new Date(),
		value:new Date(),
		xtype : 'datefield',
		allowNegative: false,
		allowBlank: false,
		editable: false,
		hiddenName: 'colnum',
		name:'colnum',
		emptyText: '开始日期',
		valueField: 'value',
		width: 240,
		margin:'0 0 0 40',
		listeners:{
			 select:function(field,e){
                	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
             }
		}
	},{
		labelAlign : 'right',
		maxValue:new Date(),
		value:new Date(),
		xtype : 'datefield',
		allowNegative: false,
		allowBlank: false,
		editable: false,
		hiddenName: 'colnum',
		name:'colnum',
		emptyText: '结束日期',
		valueField: 'value',
		width: 140,
		margin:'0 0 0 40',
		listeners:{
			 select:function(field,e){
                	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
             }
		}
	},{
        iconCls: 'icon-user-add',
        text: '显示报文',
        id:id+'showMessageButton',
        scope: this,
        style:'margin-left:30px;',
        handler:function(){
        	console.log(Ext.getCmp(id+'showMessageButton'));
			var showMessage =  Ext.getCmp(id+'showMessage');
			if(showMessage.hidden==true){
				showMessage.show();
				Ext.getCmp(id+'showMessageButton').setText("隐藏报文");
			}else{
				showMessage.hide();
				Ext.getCmp(id+'showMessageButton').setText("显示报文");
			}
	}
    }];
		this.items = [{
			height:'100%',
			border:false,
			style:'1px solid red;',
			flex:1,
			region : 'west',
			layout:'hbox',
			
			items:[{//显示日志数据
				height:'100%',
				border:false,
				flex:1,
				region : 'west',
				layout:'hbox',
				items:new Ushine.dataGet.DataGetTaskLogDataGridPanel(systemName,dataType)
			},{//显示报文
				height:'100%',
				border:false,
				id:id+'showMessage',
				style:'1px solid red;padding:0 0 0 10;',
				flex:1,
				hidden:true,
				layout:'vbox',
				items:[{
					height:'100%',
					width:'100%',
					border:false,
					autoScroll: true,
					style:'border-bottom:0px solid red;border-top:1px solid #AAAAAA;border-left:1px solid #AAAAAA;border-right:1px solid #AAAAAA;',
					flex:1,
					padding:'13 0 13 13',
					region : 'west',
					html:'<div><p><h1>HTTP 请求报文</h1><p><p>GET /search?hl=zh&source=hp&q=domety&aq=f&op= HTP/1.1</p>'+
						'<p>Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-exce;, application/vnd. ms-powerpoint,</p>'+
					'<p>application/msword, application/x-silverlight, appication/x-shockwave-flash, */*</p>'+
					'<p>Referer: "<a href="http://www.google.com/">http://www.goole.cm</a>"</p>'+
					'<p>Accept_Language: zh-cn,</p>'+
					'<p>Accept_Encoding: gzipm deflate</p>'+
					'<p>User-Agent: mozilla/4.0 (compatible: MSITE 6.0; Windows NT 5.1; SV1; .NET CLR2.0.507272L)</p></div>'
				},{
					height:'100%',
					width:'100%',
					border:false,
					style:'border:1px solid #AAAAAA;',
					autoScroll: true,
					flex:1,
					region : 'west',
					padding:'13 0 13 13',
					html:'<div><p><h1>HTTP 抓取报文</h1><p><p>GET /search?hl=zh&source=hp&q=domety&aq=f&op= HTP/1.1</p>'+
					'<p>Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-exce;, application/vnd. ms-powerpoint,</p>'+
				'<p>application/msword, application/x-silverlight, appication/x-shockwave-flash, */*</p>'+
				'<p>Referer: "<a href="http://www.google.com/">http://www.goole.cm</a>"</p>'+
				'<p>Accept_Language: zh-cn,</p>'+
				'<p>Accept_Encoding: gzipm deflate</p>'+
				'<p>User-Agent: mozilla/4.0 (compatible: MSITE 6.0; Windows NT 5.1; SV1; .NET CLR2.0.507272L)</p></div>'
				}]
			}]
		}
		//线索gridpanel
		//Ext.create('Ushine.dataGet.DataGetTaskLogDataGridPanel')
		];
		this.callParent();	
	},
	//搜索
	dataSearch:function(){
		this.remove('c_dataSearch_grid');
		this.add(Ext.create('Ushine.dataSearch.DataSearchPenelGridPanel'));
	}
});
