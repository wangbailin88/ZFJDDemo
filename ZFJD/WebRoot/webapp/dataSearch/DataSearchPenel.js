/**
 * 数据检索
 */
 Ext.define('Ushine.dataSearch.DataSearchPenel', {
	extend: 'Ext.panel.Panel',
	id: 'dataSearchPenel-panel',
	region: 'center',
	bodyStyle: 'background-color: #ffffff; border: none; padding: 10px;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	constructor: function() {
		var self = this;
		this.items = [
		              // 标题栏
			Ext.create('Ushine.base.TitleBar', {
				cTitle: '数据搜索'
			}),{
			// 工具栏
			xtype : 'panel',
		    baseCls : 'tar-body',
			height:80,
			border:true,
			style:'border:0px solid red;text-align:center;',
			layout:{
				type:'hbox',
				align:'middle',
				pack:'center'
			},
			region:'center',
			items:[{
					id		  :'content',
	            	xtype     : 'textfield',
	            	name      : 'filtrateKeyword',
	            	emptyText : '请输入搜索关键词....',
					height: 45,
					size:20,
					labelAlign : 'right',
					labelWidth: 100,
					width: 400,
					listeners:{
						 specialkey:function(field,e){
                           if(e.getKey() == e.ENTER){
                        	   self.dataSearch();
//                        	   Ext.Ajax.request({
//            					   url: 'bbbbbbbbbbbbbb.do',
//            					   actionMethods: {
//            						   create : 'POST',
//            						   read   : 'POST', // by default GET
//            						   update : 'POST',
//            						   destroy: 'POST'
//            					   },
//            					   success: function(response){
//            						   var text = response.responseText;
//            						   var obj=Ext.JSON.decode(text);
//            						   console.log(response);
//            						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
//            						   //禁用成功后刷新数据
//            						   //self.findOrganizeByProperty();
//            					   },
//            					   failure: function(form, action) {
//            						   Ext.create('Ushine.utils.Msg').onInfo('请联系管理员');
//            					   }
//            				   });
                           }  
                        }
					}
			},Ext.create('Ushine.buttons.IconButton2', {
		    	   border: false,
		    	   id: 'creatseNewBtn',
		    	   //width:80,
		    	   btnText: '搜  索',
		    	   handler: function() {
		    		   self.dataSearch();
//		    		   Ext.Ajax.request({
//    					   url: 'bbbbbbbbbbbbbb.do',
//    					   actionMethods: {
//    						   create : 'POST',
//    						   read   : 'POST', // by default GET
//    						   update : 'POST',
//    						   destroy: 'POST'
//    					   },
//    					   success: function(response){
//    						   var text = response.responseText;
//    						   var obj=Ext.JSON.decode(text);
//    						   console.log(response);
//    						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
//    						   //禁用成功后刷新数据
//    						   //self.findOrganizeByProperty();
//    					   },
//    					   failure: function(form, action) {
//    						   Ext.create('Ushine.utils.Msg').onInfo('请联系管理员');
//    					   }
//    				   });
		    	   }
		       })]
				
			
				
		},
		//数据检索gridpanel
		Ext.create('Ushine.dataSearch.DataSearchPenelGridPanel')
		];	
		this.callParent();		
	},
	//搜索
	dataSearch:function(){
		this.remove('c_dataSearch_grid');
		this.add(Ext.create('Ushine.dataSearch.DataSearchPenelGridPanel'));
	}
});
