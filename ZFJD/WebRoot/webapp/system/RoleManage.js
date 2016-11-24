Ext.define('Ushine.system.RoleManage', {
	extend: 'Ext.panel.Panel',
	layout: 'border',
	title:'角色及权限信息配置管理',
	border:false,
	bodyStyle: 'background-color: #ffffff; border: none; padding: 10px;',
	constructor: function() {
		var self=this;
		this.items=[{
			border:false,
			region : 'north',
			layout: {
				type: 'vbox',
				align : 'stretch',
				pack  : 'start'
			},
			bodyStyle: 'background-color: #ffffff;',
			items:[{
				// 工具栏
				xtype: 'panel',
				height: 30,
				baseCls: 'tar-body',
				// 工具栏 -- 左侧
				lbar: {
					flex: 1,
					layout: "column", //列1
					baseCls: 'panel-body',
            		items: [
						// 新建任务按钮
						Ext.create('Ushine.buttons.IconButton', {
							id: 'createNewBtn',
							btnText: '添加角色',
							baseCls: 't-btn-red',
							handler: function () {
								self.onAddRole();
							}
						}),
                		// 任务对比按钮
						Ext.create('Ushine.buttons.IconButton', {
							id: 'comparisonBtn',
							btnText: '删除角色',
							handler: function () {
						    	Ext.Msg.confirm('提示','是否确定要删除选中角色?',function(btn) {
									if (btn == 'yes') {
										var rolePanel=self.getComponent(1);
										if(rolePanel.getSelectionModel().hasSelection()){
							    			Ext.Ajax.request({
							    			    url: 'role/delRole.do?id='+rolePanel.getSelectionModel().selected.items[0].data.id,
							    			    success: function(response){
							    			    	 var text = response.responseText;
								    			     var obj=Ext.JSON.decode(text);
								    			     if(obj.status){
								    			    	 rolePanel.getStore().reload();
								    			     }
								    			     Ext.Msg.alert('提示',obj.msg);
							    			    },
							                    failure: function(form, action) {
							                        Ext.Msg.alert('提示', "服务器连接失败，请联系管理员");
							                    }
							    			});
										}
									}
				                  });
							}
						})
					]
				}
			}]
		},{
			border:true,
			region : 'west',
			width:200,
			header:false,
			margins:'5 5 0 0',
			collapsible: true,
			xtype:'treepanel',
			store: new Ext.data.TreeStore({
		        proxy: {
		            type: 'ajax',
		            url:'role/findAll.do',
		        }
		    }),
		    lines: true,
			autoScroll: true,
			rootVisible: false,
			listeners: {
				select: function(view, record, item, index, e) {
					self.findPermit();
				},
				load:function(thiz,records){
					this.getSelectionModel().select(this.getStore().tree.root.childNodes[0]); 
				}
			}
		},{
			border:true,
			margins:'5 0 0 5',
			layout: 'border',
			region : 'center',
			bodyStyle: 'background-color: #ffffff;border:solid 1px #cbcbcb;',
			items:[{
				id:'menus',
				width:230,
				margins:'5 5',
				region : 'west',
				//split: true,
				header:false,
				collapsible: true,
				xtype:'treepanel',
				store: new Ext.data.TreeStore({
			        proxy: {
			            type: 'ajax',
			            url:'findMenuTree.do',
			        },
			        autoLoad:false
			    }),
			    lines: true,
				autoScroll: true,
				rootVisible: false,
				listeners: {
					select: function(view, record, item, index, e) {
		    			self.findPermit();
			        },
					load:function(thiz,records){
						this.getSelectionModel().select(this.getStore().tree.root.childNodes[0]); 
					}
			    }
			},{
				region : 'center',
				border:true,
				margins:'5 5 5 0',
				layout: 'fit',
				bodyStyle:'border:solid 1px #cbcbcb;',
				items:{
					layout: 'form',
					margin:'5 5 5 5',
					xtype:'form',
					border:false,
					autoScroll:true,
					items:[{
						id:'permits',
						xtype:'fieldset',
						title:'功能权限'
					}]
				}
			},{
				height:40,
				region : 'south',
				border:true,
                layout: {
                    type: 'hbox',
                    padding:'5',
                    pack:'end',
                    align:'middle'
                },
                margins:'0 5 5 5',
                defaults:{margins:'0 5 0 0'},
                bodyStyle:'border:solid 1px #cbcbcb;',
				items:[{
					text:'保存权限',
					xtype:'u_btn',
					baseCls: 't-btn-red',
					handler:function(){
						var form=self.getComponent(2).getComponent(1).getComponent(0).getForm();
						var rolePanel=self.getComponent(1);
						var values=form.getValues();
						var resId=[];
						var operId=[];
						var i=0;
						for(var key in values){
							resId[i]=key;
							operId[i]=values[key];
							i++;
						}
						if(rolePanel.getSelectionModel().hasSelection()){
			    			Ext.Ajax.request({
			    				url:'setPermit.do',
			    			    params: {
			    			    	role: rolePanel.getSelectionModel().selected.items[0].data.id,
			    			    	resId:resId,
			    			    	operId:operId
			    			    },
			    			    success: function(response){
			    			    	 var text = response.responseText;
				    			     var obj=Ext.JSON.decode(text);
//				    			     if(obj.status){
//				    			    	 rolePanel.getStore().reload();
//				    			     }
				    			     Ext.Msg.alert('提示',obj.msg);
			    			    },
			                    failure: function(form, action) {
			                        Ext.Msg.alert('提示', "服务器连接失败，请联系管理员");
			                    }
			    			});
						}else{
							
						}
					}
				},{
					text:'重置',
					xtype:'u_btn',
					baseCls: 't-btn-yellow',
					sytle:'float:right',
					handler:function(){
						self.getComponent(2).getComponent(1).getComponent(0).getForm().reset();
					}
				}]
			}]
		}],
		this.callParent(); 
	},
	findPermit:function(){
		var self=this;
		var menuPanel=Ext.getCmp("menus");
		var rolePanel=this.getComponent(1);
		if(rolePanel.getSelectionModel().hasSelection()==true&&menuPanel.getSelectionModel().hasSelection()==true){
			var rid=rolePanel.getSelectionModel().selected.items[0].data.id;
			var mid=menuPanel.getSelectionModel().selected.items[0].data.id;
			Ext.Ajax.request({
			    url: 'getres.do?mId='+mid+"&rId="+rid,
			    success: function(response){
			        var text = response.responseText;
			        var array=Ext.JSON.decode(text);
			        var centerPanel=self.getComponent(2).getComponent(1).getComponent(0).getComponent(0);
			        centerPanel.removeAll(true);
			        centerPanel.add(array);
			    }
			});
		}
	},
	onAddRole:function(){
		var self=this;
		var win = Ext.create('Ushine.win.Window',{
			title : "添加角色", // 标题
			width : 400, // 宽度
			id:"run",
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			resizable : false, // 不允许改变窗体大小
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
				items:{
				    title:'基本信息',
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
		            items :[{
	            		xtype: 'textfield',
	            		fieldLabel: '角色名称',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'name',
	            		emptyText: '请输入角色名称',
	            	},{
	            		xtype: 'textarea',
	            		fieldLabel: '描述信息',
	            		name: 'dec',
	            		//allowBlank: false,
	            	}/*,{
	            		xtype:'combo',
	            		fieldLabel: '状态',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'roleType',
	            		name:'roleType',
	            		emptyText: '-------请选择状态-----------',
	            		valueField: 'value',
	            		store:Ext.create('Ext.data.Store', {
	            		    fields: ['text', 'value'],
	            		    data : [
	            		        {"text":"禁用", "value":"0"},
	            		        {"text":"启用", "value":"1"},
	            		    ]
	            		})
	            	}*/]
				},
				buttons:[{
					text: '保存信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	var rolePanel=self.getComponent(1);
			        	if (form.isValid()) {
			                form.submit({
			                	url:'role/addRole.do',
			                    success: function(form, action) {
			                    	if(action.result.status){
			                    		rolePanel.getStore().reload();
			                    	}
			                       win.close();
			                       Ext.Msg.alert('提示', action.result.msg);
			                    },
			                    failure: function(form, action) {
			                    	win.close();
			                        Ext.Msg.alert('提示', "服务器连接失败，请联系管理员！");
			                    }
			                });
			            }
			        	
			        }
				},{
					text: '重置',
					xtype:'u_btn',
					baseCls: 't-btn-yellow',
			        handler: function(){
			        	win.getComponent(0).getForm().reset();
			        }
				}]
			})
        });
		win.show();
	}
	
});