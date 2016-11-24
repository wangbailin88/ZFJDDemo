/**
 * 内容区域
 * @author Franklin
 */
Ext.define('Ushine.base.CenterFrame1', {
	extend: 'Ext.panel.Panel',
	id: 'center_frame1',
	region: 'center',
	
	margins: '0 0 0 0',
	border: false,
	layout: 'border', // 边界布局
	
	constructor: function() {
		
		this.items = [{ 
				id: 'content_frame',
				xtype: 'panel',
				region: 'center',
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