/**
 * 标题栏
 * @author Franklin
 */
Ext.define('Ushine.base.TitleBar', {
	extend: 'Ext.panel.Panel',
	
	config: {
		cTitle: '信息标题',
		btnItems: [
			
		]
	},  
	
	border: false,
	height: 40,
	baseCls: 'conent_title_box',
	
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},
	
	constructor: function(config) {
		
		this.cTitle = config.cTitle;
		
		this.items = [{
			// 标题
			xtype: 'panel',
			border: false,
			bodyStyle: 'background: none;',
			html: '<span class="conent_title">' + this.cTitle + '</span>'
		},{
			// 按钮
			xtype: 'panel',
			flex: 1,
			border: false,
			bodyStyle: 'background: none; text-align: right; padding: 6px 10px 0px 0px;',
			items: config.btnItems
		}];
		
		
		
		this.callParent(); 
	}
	
});



/**
 * 标题栏
 * @author Franklin
 */
Ext.define('Ushine.base.TitleBar1', {
	extend: 'Ext.panel.Panel',
	
	config: {
		cTitle: '信息标题',
		btnItems: [
			
		]
	},  
	
	border: true,
	height: 25,
	width:730,
	baseCls: 'conent_title_box1',
	
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},
	
	constructor: function(config) {
		
		this.cTitle = config.cTitle;
		
		this.items = [{
			// 标题
			xtype: 'panel',
			border: false,
			bodyStyle: 'background: none;',
			html: '<span class="conent_title1">' + this.cTitle + '</span>'
		},{
			// 按钮
			xtype: 'panel',
			flex: 1,
			border: false,
			bodyStyle: 'background: none;font-size:12px;text-align: right;',
			items: config.btnItems
		}];
		
		
		
		this.callParent(); 
	}
	
});