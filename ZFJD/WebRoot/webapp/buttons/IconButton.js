/**
 * 普通按钮：80X22 一般用于列表操作
 * @author Franklin
 */
Ext.define('Ushine.buttons.IconButton', {
	extend: 'Ext.button.Button',
	
	config: {
		btnText: '按钮名称'	
	},
	
	width: 90,
	height: 22,
	baseCls: 'min-btn',
	
	constructor: function(config) {
		this.id = config.id;
		this.html = '<i class="min-btn-icon"></i><h3 class="min-btn-text">' + config.btnText + '</h3>';
		if(config.baseCls != null) {
			this.baseCls = config.baseCls;
		}
		
		this.handler = config.handler;
		
		this.callParent(); 
	}
	
});




/**
 * wbl 添加
 * 普通按钮：120X22 
 * @author Franklin
 */
Ext.define('Ushine.buttons.IconButton1', {
	extend: 'Ext.button.Button',
	
	config: {
		btnText: '按钮名称'	
	},
	
	width: 120,
	height: 22,
	baseCls: 'min-btn',
	
	constructor: function(config) {
		this.id = config.id;
		this.html = '<i class="min-btn-icon"></i><h3 class="min-btn-text">' + config.btnText + '</h3>';
		if(config.baseCls != null) {
			this.baseCls = config.baseCls;
		}
		
		this.handler = config.handler;
		
		this.callParent(); 
	}
	
});


/**
 * wbl 添加
 * 普通按钮：120X40 
 * @author Franklin
 */
Ext.define('Ushine.buttons.IconButton2', {
	extend: 'Ext.button.Button',
	
	config: {
		btnText: '按钮名称'	
	},
	
	width: 120,
	height: 45,
	baseCls: 'min-btn2',
	
	constructor: function(config) {
		this.id = config.id;
		this.html = '<span style="text-align:center;line-height:45px;font-size:22px;font-weight:bold;">'+config.btnText+'</span>';
		this.handler = config.handler;
		this.callParent(); 
	}
	
});