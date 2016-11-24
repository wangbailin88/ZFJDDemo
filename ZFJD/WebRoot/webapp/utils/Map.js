// JavaScript Document

Ext.define('Ushine.utils.Map', {
	map:function(){
		var win = Ext.create('Ushine.win.Window', {
			title : "个人设置", // 标题
			width : 500, // 宽度
			height: 300,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
			items:{
            	xtype:'tabpanel',
            	items:[{
            		title:'修改密码',
            		items:Ext.create('Ushine.base.PasPanel')
    			}]
		 
			}
        });
		win.show();
    }
});


