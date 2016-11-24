/**
 * 内容区域
 * @author Franklin
 */
Ext.define('Ushine.base.CenterFrame', {
	extend: 'Ext.panel.Panel',
	id: 'center_frame',
	region: 'center',
	
	margins: '0 0 0 0',
	border: false,
	layout: 'border', // 边界布局
	
	constructor: function() {
		
		this.items = [{ 
				id: 'content_frame',
				xtype: 'panel',
				region: 'center',
				padding:0,
				baseCls: 'panel-body',
				layout: 'fit',
				items:[
//				    Ext.create('Ushine.case.CaseTask')// 检索面板   
//					Ext.create('Ushine.case.Case')
				]
			}
		];
		
		this.callParent(); 
	}
	
});