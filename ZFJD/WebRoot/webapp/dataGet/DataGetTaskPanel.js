/**
 * 数据获取任务panel
 */
 Ext.define('Ushine.dataGet.DataGetTaskPanel', {
	extend: 'Ext.panel.Panel',
	id: 'dataGet_DataGetTaskPanel',
	region: 'center',
	height:'100%',
	border:false,
	bodyStyle: 'background-color: #ffffff; border: none; padding: 0px;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	constructor: function() {
		var self = this;
		this.tbar = [{
			id:'field',
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
					        {"text":"数据名称", "value":"clueName"},
					        {"text":"数据类型", "value":"clueSource"},
					        {"text":"系统名称", "value":"findTime"}
					        ]
			}),
			width: 200,
			listeners:{
				 select:function(field,e){
					 Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
                 }
			}
	},{
		id		  :'fieldValue',
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
		id:'fiesdld',
		labelAlign : 'right',
		xtype:'combo',
		allowNegative: false,
		allowBlank: false,
		editable: false,
		hiddenName: 'colnum',
		name:'colnum',
		emptyText: '全部状态',
		valueField: 'value',
		store:Ext.create('Ext.data.Store', {
			fields: ['text', 'value'],
				data : [
				        {"text":"线索名称", "value":"clueName"},
				        {"text":"线索来源", "value":"clueSource"},
				        {"text":"发现时间", "value":"findTime"}
				        ]
		}),
		width: 140,
		margin:'0 0 0 40',
		listeners:{
			 select:function(field,e){
				 Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
             }
		}
}];
		this.items = [{
			// 工具栏
			width:'100%',
			height:'100%',
			flex:1,
			border:false,
			layout:'vbox',
			items:[{
				width:'100%',
				height:'100%',
				flex:1,
				border:false,
				layout:'hbox',
				items:Ext.create('Ushine.dataGet.DataGetTaskGridPanel')
			}]
			
		}
		//线索gridpanel
		//Ext.create('Ushine.dataGet.DataGetTaskGridPanel')
		];
		this.callParent();	
	},
	//搜索
	dataSearch:function(){
		this.remove('c_dataSearch_grid');
		this.add(Ext.create('Ushine.dataSearch.DataSearchPenelGridPanel'));
	}
});
