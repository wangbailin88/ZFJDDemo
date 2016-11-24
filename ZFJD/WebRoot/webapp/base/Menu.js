/**
 * 内容区域
 * @author Franklin
 */
Ext.define('Ushine.base.Menu', {
	extend: 'Ext.view.View',
	id: 'menu_frame',
	//region: 'west',
	
	border: false,
	baseCls: 'blue-body',
	constructor:function(){
		var me = this;
		this.store = Ext.create('Ext.data.Store',{
			model:'Ushine.MenuModel',
			proxy:{
				type:'ajax',
				url:'getMenus.do?rt=t',
				simpleSortMode:true
			}
		});
		
		this.store.load();
		this.callParent(); 
	},
	
	tpl: Ext.create('Ext.XTemplate',
	    '<tpl for=".">',
			'<div id="{icon}" role="{icon}" role="{url}" class="m-btn" style="float:left;">',
				'<i class="normal-icon" ></i>',
				'<span class="title">{name}</span>',
			'</div>',
		'</tpl>'
	),
	
	itemSelector: 'div.m-btn',
	overItemCls: 'm-btn-hover',
	
	listeners: {
		// 点击“菜单项”的事件响应操作
		selectionchange: function(dataView, selectNodes) {
			Ext.create('Ushine.map.MapUtil').closeWindow();
			var url = selectNodes[0].get('url');
			var selNodeId = selectNodes[0].get('icon');
			var m_class = "m-btn m-btn-hover x-item-selected"; // 未选中背景
			var a_class = "m-btn m-btn-hover x-item-selected m-btn-activity"; // 选中背景
			
			var mIcon = "normal-icon";
			var aIcon = "activity-icon";
				
			var menuItemss = Ext.query('.m-btn');
			for(var i=0; i<menuItemss.length; i++) {
				if(selNodeId == menuItemss[i].id) {
					//选中菜单后出现箭头标志
					//menuItemss[i].setAttribute("class", a_class);
					menuItemss[i].childNodes[0].setAttribute("class", aIcon);
				} else {
					menuItemss[i].setAttribute("class", m_class);
					menuItemss[i].childNodes[0].setAttribute("class", mIcon);
				}
			}
        	/* 创建Panel并添加到Panel中
        	 * Ext.getCmp('content_frame').add(Ext.create('Ushine.search.SearchTaskPanel'));
        	 */
			Ext.getCmp('content_frame').removeAll();
			if(selectNodes[0].get('name')=="系统设置"){
				Ext.getCmp('content_frame').add(new Ushine.system.Operations(selectNodes[0].get('id')));
			}else{
				Ext.getCmp('content_frame').add(Ext.create(url));	
			}
			
		},
		refresh:function(view, e){
			view.select(0);
		}
	}
});

/**
 * 菜单数据模型
 */
Ext.define('Ushine.MenuModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'className', type: 'string'},
        {name: 'name', type: 'string',mapping:'text'},
		{name: 'icon', type: 'string',mapping:'iconCls'},
        {name: 'url', type: 'string'},
        {name: 'id', type: 'string'}
    ]
});