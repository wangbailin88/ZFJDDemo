Ext.define('Ushine.system.Operations', {
	extend: 'Ext.panel.Panel',
	layout: 'border',
	border:false,
	bodyStyle: 'background-color: #ffffff; border: none; padding: 10px;',
	constructor: function(id) {
		var self=this;
		this.items=[{
			region : 'north',
			border:false,
			items:
			Ext.create('Ushine.base.TitleBar', {
				cTitle: '系统设置',
			}),
			bodyStyle: 'background-color: #ffffff;'
		},{
			width:200,
			border:true,
			title:'系统管理菜单',
			region : 'west',
			margins:'5 5 0 0',
			xtype:'treepanel',
			// header:false,
			//collapsible: true,
			store: new Ext.data.TreeStore({
		        proxy: {
		            type: 'ajax',
		            url:'systemMenus.do?rt=t&id='+id,
		        }
		    }),
		    lines: true,
			autoScroll: true,
			rootVisible: false,
			listeners: {
				itemclick: function(view, record, item, index, e) {
					var className = record.raw.className;
		        	var mainPanel = self.getComponent(2);
		        	mainPanel.removeAll(true);
		        	mainPanel.add(Ext.create(className));
		        	//mainPanel.doLayout();
		        }
		    }
		},{
			id:'main_panel',
			border:true,
			layout: 'fit',
			margins:'5 0 0 5',
			region : 'center',
			bodyStyle:'border:solid 1px #cbcbcb;',
			items:Ext.create("Ushine.system.SystemStatus"),
			addOrgPanel:function(){
        		this.removeAll(true);
	        	this.add(Ext.create("Ushine.system.OrganizManage"));
			}
		}]
		this.callParent(); 
	}
});