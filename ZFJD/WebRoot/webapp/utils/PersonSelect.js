Ext.define('Ushine.utils.PersonSelect', {
	extend: 'Ext.panel.Panel',
	layout: 'border',
	border:false,
	bodyStyle: 'background-color: #f5f5f5;',
	constructor: function() {
		var self=this;
		this.items=[{
				region : 'west',
				width:200,
				header:false,
				collapsible: true,
				xtype:'treepanel',
				store: new Ext.data.TreeStore({
			        proxy: {
			            type: 'ajax',
			            url:'getOrgsTree.do',
			        }
			    }),
			    lines: true,
				autoScroll: true,
				rootVisible: false,
				listeners: {
					itemclick: function(view, record, item, index, e) {
						self.getComponent(1).store.load({
							params:{
									dempId:record.raw.id
							}
						});
					}
				}
			},Ext.create('Ushine.utils.personGid')];
		this.callParent();
	}
});
Ext.define('Ushine.utils.personGid', {
	extend: 'Ext.grid.Panel',
	region : 'center',
	margins:'0 0 0 10',
	viewConfig:{
		emptyText: '没有数据',
    	stripeRows:true,//在表格中显示斑马线
    	enableTextSelection:true //可以复制单元格文字
	},
	constructor: function() {
		this.selModel = Ext.create('Ext.selection.CheckboxModel');
		this.store=Ext.create('Ext.data.Store', {
	    	fields:[
	    	        {name: 'id', type:'string', mapping: 'id'},
	    	        {name: 'number', type:'string', mapping: 'number'},
	    	        {name: 'userName', type:'string', mapping: 'userName'},
	    	        {name: 'trueName', type:'string', mapping: 'trueName'},
	    	        {name: 'createDate', type:'date', mapping: 'createDate'},
	    	        {name: 'tel', type:'string', mapping: 'tel'},
	    	        {name: 'loginLastDate', type:'date', mapping: 'loginLastDate'},
	    	        {name: 'loginLastIP', type:'string', mapping: 'loginLastIP'},
	    	        {name: 'status', type: 'int', mapping: 'status'}
	            ],
		    proxy: {
	            type: 'ajax',
	            url:'getPersons.do',
	            reader: {
	                type: 'json',
	                root: 'datas'
	            }
		    },
		    autoDestroy: true,
		    autoLoad:false
		});
		this.columns=[
		               { header: '警员编号',  dataIndex: 'number',flex: 1 },
		               { header: '真实姓名',  dataIndex: 'trueName',flex: 1 },
		               { header: '联系电话',  dataIndex: 'tel',flex: 1 }
		           ];
		this.callParent(); 
	}
});






