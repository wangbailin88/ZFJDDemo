Ext.define('Ushine.system.OrganizManage', {
	extend: 'Ext.panel.Panel',
	id:"oraganiz",
	title:'组织机构信息管理',
	layout: 'border',
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
			items:[ {
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
							btnText: '添加组织',
							baseCls: 't-btn-red',
							handler: function () {
								self.addOrganiz();
							}
						}),
                		// 任务对比按钮
						Ext.create('Ushine.buttons.IconButton', {
							id: 'comparisonBtn',
							btnText: '添加部门',
							handler: function () {
								self.addDemp();
							}
						}),
						// 任务收藏按钮
						Ext.create('Ushine.buttons.IconButton', {
							id: 'addPerson',
							btnText: '添加人员',
							handler: function () {
								self.addPerson();
							}
						})
					]
				}
			}]
		},{
			border:true,
			region : 'west',
			width:200,
			margins:'5 5 0 0',
			header:false,
			collapsible: true,
			xtype:'treepanel',
			id:'orgTreeId',
			store: new Ext.data.TreeStore({
				fields:['text', 'id','type'],
		        proxy: {
		            type: 'ajax',
		            url:'getOrgsTree.do',
		        },
			    autoDestroy: true,
			    autoLoad:true
		    }),
		    lines: true,
			autoScroll: true,
			rootVisible: false,
			listeners: {
				select: function(view, record, item, index, e) {
					self.getComponent(2).removeAll();
					self.getComponent(2).add(Ext.create('webapp.operation.organizManage.Person'));
					self.getComponent(2).getComponent(0).store.load();
				},
				load:function(thiz,records){
					this.getSelectionModel().select(this.getStore().tree.root.childNodes[0].childNodes[0]);
				},
				itemcontextmenu:function(view, record, item, index, e){
					var data=record.data;
					if(this.menu){
						this.menu.hide(true);
						this.menu.removeAll(true);
					}else{
						this.menu = new Ext.menu.Menu();
					}
					this.menu.addCls('context-menu');
				    this.menu.add({
				    	text:'修改组织/部门',
				    	cls:'context-btn',
				    	handler:function(view, record, item, index, e){
				    		if(data.type=="0"){
				    			self.validateOrg(data.id);
				    		}else{
				    			self.validateDemp(data.id);
				    		}
				    	}
				    },{
				    	text:'删除组织/部门',
				    	cls:'context-btn',
				    	handler:function(view, record, item, index, e){
				    		self.deleteOrgAndDempt(data.id,data.type);
				    	}
				    });
				    this.menu.showAt(e.getPoint());
				}
			}
		},{
			border:true,
			itemId:'person',
			region : 'center',
			layout:'fit',
			margins:'5 0 0 5',
			bodyStyle:'border:solid 1px #cbcbcb;',
			items:Ext.create('webapp.operation.organizManage.Person')
		}],
		this.callParent(); 
	},
	validateOrg:function(id){
		var self=this;
		Ext.Ajax.request({
		    url: 'findOrgByOrId.do',
		    params: {
		    	id:id
		    },
		    success: function(response){
		        var text = response.responseText;
		        var obj=Ext.JSON.decode(text);
		        self.updateOrganiz(obj,id);
		    },
		    failure: function(form, action) {
            	Ext.create('Ushine.utils.Msg').onInfo('获取修改信息失败，请联系管理员');
            }
		}); 
	},
	validateDemp:function(id){
		var self=this;
		Ext.Ajax.request({
		    url: 'findDeptByOrId.do',
		    params: {
		    	id:id
		    },
		    success: function(response){
		        var text = response.responseText;
		        var obj=Ext.JSON.decode(text);
		        self.updateDemp(obj,id);
		    },
		    failure: function(form, action) {
            	Ext.create('Ushine.utils.Msg').onInfo('获取修改信息失败，请联系管理员');
            }
		}); 
	},
	updateDemp:function(obj,id){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "修改部门", // 标题
			width : 400, // 宽度
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			resizable : false, // 不允许改变窗体大小
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				buttonAlign:"center",
				border:false,
				//defaults: {labelWidth: 60,width: 300,height:22},
	            items :{
	                title:'基本信息',
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
					items :[{
            		xtype: 'textfield',
            		fieldLabel: '部门名称',
            		allowNegative: false,
            		allowBlank: false,
            		name: 'name',
            		emptyText: '请输入部门名称',
            		blankText:'部门名称不能为空',
            		value:''+obj.deptName
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '所属组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'所属组织不能为空',
	            		queryMode:'local', 
	            		value:''+obj.org,
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=dep"
							}),
							fields:['text', 'value'],
						    autoLoad:true,
						    autoDestroy: true
						}),
					    listeners:{
					        select: function(combo, record, index) {
					        	var id=combo.getValue();
					        	win.getComponent(0).getComponent(0).getComponent(2).store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        },
					        render:function(combo, record, index){
					        	var id=combo.getValue();
					        	Ext.getCmp('topDeptId').store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        }
					    }	
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级部门',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'dept',
	            		id:'topDeptId',
	            		name:'dept',
	            		valueField: 'value',
	            		queryMode:'local', 
	            		blankText:'上级部门不能为空',
	            		value:obj.topDept,
	            		emptyText: '----请选择部门----',
						store :new Ext.data.Store({
							fields : [ "text", "value" ],
						    proxy: new Ext.data.HttpProxy({
						    	url : "getDepts.do?scope=all"
						    }),
						    autoLoad:false,
						    autoDestroy: true
						}),
						listeners:{
							render:function(){
								//console.log(obj);
								this.setValue(obj.topDept)
							}
						}
	            	}
	            	]},
				buttons:[{
					text: '修改信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	if (form.isValid()) {
			                form.submit({
			                	url:'updateDept.do?id='+id,
			                    success: function(form, action) {
			                    	if(action.result.status){
			                    		self.ownerCt.addOrgPanel();
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
	},
	updateOrganiz:function(obj,id){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "修改组织", // 标题
			width : 400, // 宽度
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
			//	defaults: {labelWidth: 100,width: 380,height:22},
				items:{
				    title:'基本信息',
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
					items :[{
		            	name: 'name',
	            		xtype: 'textfield',
	            		fieldLabel: '组织名称',
	            		allowBlank: false,
	            		blankText:'组织名称不能为空',
	            		value:''+obj.orgName,
	            		emptyText: '请输入组织名称',
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'上级组织不能为空',
	            		queryMode:'local', 
	            		value:''+obj.topOrg,
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=all"
							}),
							fields:['text', 'value'],
						    autoLoad:true,
						    autoDestroy: true,
						    
						})
	            	},{
	            		name: 'city',
	            		xtype: 'textfield',
	            		fieldLabel: '所属地区',
	            		allowBlank: false,
	            		blankText:'所属地区不能为空',
	            		value:''+obj.region,
	            		emptyText: '请选择所属地区',
						listeners : {
							'focus' : function(thiz,the,eObj) {
								Ext.create('Ushine.utils.WinUtils').cityWin(thiz,the,eObj);
							}
						}
	            	},{
	            		name: 'contacts',
	            		xtype: 'textfield',
	            		fieldLabel: '联系人',
	            		//allowBlank: false,
	            		blankText:'联系人不能为空',
	            		emptyText: '请输入联系人',
	            		value:''+obj.contacts
	            	},{
	            		name: 'tel',
	            		xtype: 'textfield',
	            		fieldLabel: '联系电话',
	            		//allowBlank: false,
	            		emptyText: '请输入联系电话',
	            		blankText:'联系电话不能为空',
	            		value:''+obj.tel
            	}]},
				buttons:[{
					text: '修改信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	var btn=this;
			        	if (form.isValid()) {
			        		btn.disable();
			                form.submit({
			                	url:'updateOrg.do?id='+id,
			                	submitEmptyText:false,
			                    success: function(form, action) {
			                    		win.close();
			                    		Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
			                    		self.ownerCt.addOrgPanel();
			                    },
			                    failure: function(form, action) {
			                    	switch (action.failureType) {
            			            case Ext.form.Action.CLIENT_INVALID:
            			            	Ext.create('Ushine.utils.Msg').onWarn('操作失败，请重试');
            			                break;
            			            case Ext.form.Action.CONNECT_FAILURE:
            			            	Ext.create('Ushine.utils.Msg').onWarn('连接服务器失败，请重试！');
            			                break;
            			            case Ext.form.Action.SERVER_INVALID:
            			            	Ext.create('Ushine.utils.Msg').onWarn(action.result.msg);
                			        }
			                    	btn.enable();
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
	},
	deleteOrgAndDempt:function(id,type){
		var self=this;
		Ext.Msg.confirm('提示','是否要删除该组织/部门?',function(btn) {
		    if (btn == 'yes'){
				Ext.Ajax.request({
				    url: 'deleteOrgAndDempt.do',
    			    params: {
    			    	id:id,
    			    	type:type
    			    },
				    success: function(response){
				        var text = response.responseText;
				        var obj=Ext.JSON.decode(text);
				        Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
				        self.ownerCt.addOrgPanel();
				    },
				    failure: function(form, action) {
                    	Ext.create('Ushine.utils.Msg').onInfo('删除失败，请联系管理员');
                    }
				}); 
		    }
		});
	},
	addOrganiz:function(){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "添加组织", // 标题
			width : 400, // 宽度
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
			//	defaults: {labelWidth: 100,width: 380,height:22},
				items:{
				    title:'基本信息',
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
					items :[{
		            	name: 'name',
	            		xtype: 'textfield',
	            		fieldLabel: '组织名称',
	            		allowBlank: false,
	            		emptyText: '请输入组织名称',
	            		blankText:'组织名称不能为空'
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'上级组织不能为空',
	            		queryMode:'local', 
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=all"
							}),
							fields:['text', 'value'],
						    autoLoad:true,
						    autoDestroy: true
						})
	            	},{
	            		name: 'city',
	            		xtype: 'textfield',
	            		fieldLabel: '所属地区',
	            		allowBlank: false,
	            		emptyText: '请选择所属地区',
	            		blankText:'所属地区不能为空',
						listeners : {
							'focus' : function(thiz,the,eObj) {
								Ext.create('Ushine.utils.WinUtils').cityWin(thiz,the,eObj);
							}
						}
	            	},{
	            		name: 'contacts',
	            		xtype: 'textfield',
	            		fieldLabel: '联系人',
	            		//allowBlank: false,
	            		emptyText: '请输入联系人',
	            		blankText:'联系人不能为空'
	            	},{
	            		name: 'tel',
	            		xtype: 'textfield',
	            		fieldLabel: '联系电话',
	            		//allowBlank: false,
	            		emptyText: '请输入联系人电话',
	            		blankText:'联系电话不能为空'
            	}]},
				buttons:[{
					text: '保存信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	var btn=this;
			        	if (form.isValid()) {
			        		btn.disable();
			                form.submit({
			                	url:'addOrg.do',
			                	submitEmptyText:false,
			                    success: function(form, action) {
			                    		win.close();
			                    		Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
			                    		self.ownerCt.addOrgPanel();
			                    },
			                    failure: function(form, action) {
			                    	switch (action.failureType) {
            			            case Ext.form.Action.CLIENT_INVALID:
            			            	Ext.create('Ushine.utils.Msg').onWarn('操作失败，请重试');
            			                break;
            			            case Ext.form.Action.CONNECT_FAILURE:
            			            	Ext.create('Ushine.utils.Msg').onWarn('连接服务器失败，请重试！');
            			                break;
            			            case Ext.form.Action.SERVER_INVALID:
            			            	Ext.create('Ushine.utils.Msg').onWarn(action.result.msg);
                			        }
			                    	btn.enable();
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
	},
	addDemp:function(){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "添加部门", // 标题
			width : 400, // 宽度
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			resizable : false, // 不允许改变窗体大小
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				buttonAlign:"center",
				border:false,
				//defaults: {labelWidth: 60,width: 300,height:22},
	            items :{
	                title:'基本信息',
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
					items :[{
            		xtype: 'textfield',
            		fieldLabel: '部门名称',
            		allowNegative: false,
            		allowBlank: false,
            		name: 'name',
            		emptyText: '请输入部门名称',
            		blankText:'部门名称不能为空',
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '所属组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'所属组织不能为空',
	            		queryMode:'local', 
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=dep"
							}),
							fields:['text', 'value'],
						    autoLoad:true,
						    autoDestroy: true
						}),
					    listeners:{
					        select: function(combo, record, index) {
					        	var id=combo.getValue();
					        	win.getComponent(0).getComponent(0).getComponent(2).store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        }
					    }	
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级部门',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'dept',
	            		name:'dept',
	            		valueField: 'value',
	            		queryMode:'local', 
	            		blankText:'上级部门不能为空',
	            		emptyText: '----请选择部门----',
						store :new Ext.data.Store({
							fields : [ "text", "value" ],
						    proxy: new Ext.data.HttpProxy({
						    	url : "getDepts.do?scope=all"
						    }),
						    autoLoad:false,
						    autoDestroy: true
						})
	            	}/*,{
	            		xtype:'combo',
	            		fieldLabel: '状态',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'status',
	            		name:'status',
	            		valueField: 'value',
	            		blankText:'部门状态不能为空',
	            		emptyText: '----请选择状态----',
	            		store:Ext.create('Ext.data.Store', {
	            		    fields: ['text', 'value'],
	            		    data : [
	            		        {"text":"禁用", "value":"0"},
	            		        {"text":"启用", "value":"1"}
	            		    ]
	            		})}*/
	            	]},
				buttons:[{
					text: '保存信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	var treePanel=self.getComponent(1);
			        	if (form.isValid()) {
			                form.submit({
			                	url:'addDept.do',
			                	submitEmptyText:false,
			                    success: function(form, action) {
			                    	if(action.result.status){
			                    		self.ownerCt.addOrgPanel();
			                    	}
			                       win.close();
			                       Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
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
	},
	addPerson:function(){
		var self=this;
	    Ext.apply(Ext.form.field.VTypes, {
	        name: function(val, field) {
	        	var me = this;
	    		Ext.Ajax.request({
	    		    url: 'VUserName.do',
	    		    async:false,
	    		    params: {
	    		    	name:val
	    		    },
	    		    success: function(response){
	    		    	 var text = response.responseText;
	    			     var obj=Ext.JSON.decode(text);
	    			     //if(obj.status) return true;
	    			     //return true;
	    			     if(obj.status==1){
	    			    	 me.nameExist = false;
	    			     }else{
	    			    	 me.nameExist = true;
	    			     }
	    			     me.status = true;
	    		    },
	                failure: function(form, action) {
	                    Ext.Msg.alert('提示', "服务器连接失败，请联系管理员");
	                }
	    		});
	    		while(!this.status){
	    		}
	    		return this.nameExist;
	        },
	        nameText: '改用户已被使用'
	    });
		var win = Ext.create('Ushine.win.Window', {
			title : "添加人员", // 标题
			width : 400, // 宽度
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
	            		fieldLabel: '真实姓名',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'trueName',
	            		blankText:'真实姓名不能为空',
	            		emptyText: '请输入真实姓名',
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '警员编号',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'number',
	            		emptyText: '请输入警员编号',
	            		blankText:'警员编号不能为空',
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '登录IP',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'ip',
	            		emptyText: '请输入登录IP',
	            		blankText:'登录IP不能为空',
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '联系电话',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'tel',
	            		emptyText: '请输入联系电话',
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '用户名',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'userName',
	            		vtype:'name',
	            		blankText:'用户名不能为空',
	            		emptyText: '请输入用户名'
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '身份证号码',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'IDCard',
	            		emptyText: '请输入身份证',
	            		blankText:'身份证不能为空',
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '所属组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'所属组织不能为空',
	            		queryMode:'local', 
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=person"
							}),
							fields:['text', 'value'],
						    autoLoad:true,
						    autoDestroy: true
						}),
					    listeners:{
					        select: function(combo, record, index) {
					        	var id=combo.getValue();
					        	win.getComponent(0).getComponent(0).getComponent(7).store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        }
					    }	
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级部门',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'dept',
	            		name:'dept',
	            		valueField: 'value',
	            		queryMode:'local', 
	            		blankText:'上级部门不能为空',
	            		emptyText: '----请选择部门----',
						store :new Ext.data.Store({
							fields : [ "text", "value" ],
						    proxy: new Ext.data.HttpProxy({
						    	url : "getDepts.do?scope=dep"
						    }),
						    autoLoad:false,
						    autoDestroy: true
						})
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '是否启用IP验证',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'status',
	            		name:'status',
	            		valueField: 'value',
	            		blankText:'人员状态不能为空',
	            		emptyText: '----请选择状态----',
	            		store:Ext.create('Ext.data.Store', {
	            		    fields: ['text', 'value'],
	            		    data : [
	            		        {"text":"是", "value":1},
	            		        {"text":"否", "value":0}
	            		    ]
	            		})
	            	}]
				},
				buttons:[{
					text: '保存信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
//				        	var dep=win.getComponent(0).getValues().dept;
			        	var personPanel=self.getComponent(2).getComponent(0);
			        	if (form.isValid()) {
			                form.submit({
			                	url:'addPerson.do',
			                	submitEmptyText:false,
			                    success: function(form, action) {
			                       if(action.result.status){
			                    	   personPanel.getStore().reload();
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
			}),
		    listeners: {
				close: function(thiz, options) {
					this.destroy();
				}
		    }
        });
		win.show();
	}
});
Ext.define('webapp.operation.organizManage.Person', {
	extend: 'Ext.grid.Panel',
	layout: 'border',
	border:false,
	columnLines:true,
	bodyStyle: 'background:#FFFFFF;',
	viewConfig:{
		emptyText: '没有数据',
    	stripeRows:true,//在表格中显示斑马线
    	enableTextSelection:true //可以复制单元格文字
	},
	constructor: function() {
		var self=this;
		this.store=Ext.create('Ext.data.Store', {
			pageSize : 50,
	    	fields:[
	    	        {name: 'id', type:'string', mapping: 'id'},
	    	        {name: 'number', type:'string', mapping: 'number'},
	    	        {name: 'userName', type:'string', mapping: 'userName'},
	    	        {name: 'trueName', type:'string', mapping: 'trueName'},
	    	        {name: 'createDate', type:'date', mapping: 'createDate'},
	    	        {name: 'IDCard', type:'string', mapping: 'IDCard'},
	    	        {name: 'tel', type:'string', mapping: 'tel'},
	    	        {name: 'ip', type:'string', mapping: 'ip'},
	    	        {name: 'orgId', type:'string', mapping: 'orgId'},
	    	        {name: 'deptId', type:'string', mapping: 'deptId'},
	    	        {name: 'orgName', type:'string', mapping: 'orgName'},
	    	        {name: 'deptName', type:'string', mapping: 'deptName'},
	    	        {name: 'loginLastDate', type:'date', mapping: 'loginLastDate'},
	    	        {name: 'loginLastIP', type:'string', mapping: 'loginLastIP'},
	    	        {name: 'status', type:'int', mapping: 'status'}
	            ],
		    proxy: {
	            type: 'ajax',
	            url:'getPersons.do',
	            reader: {
	                type: 'json',
	                root: 'datas',
	                totalProperty: 'paging.totalRecord'
	            }
		    },
		    listeners: {
				beforeload: function(thiz, options) {
					var depPanel=self.ownerCt.ownerCt.getComponent(1);
					if(depPanel.getSelectionModel().hasSelection()){
						var did=depPanel.getSelectionModel().selected.items[0].data.id;
						if(!options.params) options.params = {};
						options.params.dempId=did;
					}
				}
		    },
		    autoDestroy: true,
		    autoLoad:false
		});
		this.columns=[
		               { header: '编号',  dataIndex: 'number',menuDisabled:true,flex: 1 },
		               { header: '真实姓名',  dataIndex: 'trueName',menuDisabled:true,flex: 1 },
		               { header: '用户名', dataIndex: 'userName',menuDisabled:true,flex: 1 },
		               { header: '组织ID', dataIndex: 'orgId',menuDisabled:true,flex: 1,hidden:true },
		               { header: '部门ID', dataIndex: 'deptId',menuDisabled:true,flex: 1 ,hidden:true},
		               { header: '组织名称', dataIndex: 'orgName',menuDisabled:true,flex: 1,hidden:true },
		               { header: '部门名称', dataIndex: 'deptName',menuDisabled:true,flex: 1,hidden:true },
		               { header: '是否启用IP验证', dataIndex: 'status',flex: 1,renderer: function(value){
		                	  if(value==0) return '否';
		                	  else if(value==1) return '是';
		                  }},
		               { header: '最后登录时间', dataIndex: 'loginLastDate',menuDisabled:true,flex: 1,renderer: function(value){
		            	   	return Ext.Date.format(value, 'Y-m-d H:i:s');
		                  }},
		               { header: '绑定IP', dataIndex: 'ip',menuDisabled:true,flex: 1 },
		               { header: '身份证号', dataIndex: 'IDCard',menuDisabled:true,flex: 1 },
		               { header: '最后登录IP', dataIndex: 'loginLastIP',menuDisabled:true,flex: 1 },
		               {xtype: 'actioncolumn', menuDisabled:true,width:130,
	                	  //align: 'center',
	                	  header: '操作',
	                	  items: [{
		                		  icon: 'images/lock_edit.png',
		                		  tooltip: '设置角色',
		                		  handler: function(grid, rowIndex, colIndex){
		                			  	var id = self.store.getAt(rowIndex).data.id;
			                  			Ext.Ajax.request({
			                			    url: 'getPersonsRoles.do?id='+id,
			                			    success: function(response){
			                			    	 var text = response.responseText;
			            	    			     var obj=Ext.JSON.decode(text);
			            	    			     if(obj.status){
			            	    			    	 self.setRole(obj);
			            	    			     }else{
			            	    			    	 Ext.Msg.alert('提示', obj.msg);
			            	    			     }
			                			    },
			                                failure: function(form, action) {
			                                    Ext.Msg.alert('提示', "服务器连接失败，请联系管理员");
			                                }
			                			});
		                		  }
	                	  		},"&nbsp;",{
		                		  icon: 'images/cancel.png',
		                		  tooltip: '删除',
		                		  handler: function(grid, rowIndex, colIndex){
		                			var person = self.store.getAt(rowIndex).data;
		                			self.delPerson(person.id);
		                		  }
	                		  },"&nbsp;",{
		                		  icon: 'images/pencil.png',
		                		  tooltip: '修改用户',
		                		  handler: function(grid, rowIndex, colIndex){
		                			var person = self.store.getAt(rowIndex).data;
		                			self.updatPerson(person);
		                		  }
	                		  }]
		                }
		           ];
		this.loadMask =true;
		//创建面板底部的工具条
		this.bbar = new Ext.PagingToolbar({//一个要和Ext.data.Store参与绑定并且自动提供翻页控制的工具栏
			pageSize : 50, //每页显示记录数
			store : this.store, //数据源
			displayInfo: true,
			firstText: '首页',
			lastText: '末页',
			prevText: '上一页',
			nextText: '下一页',
			refreshText: '刷新',
			beforePageText: '当前第',
			afterPageText: '/{0}页',
			displayMsg: '本页显示第{0}条到第{1}条, 共有{2}条记录',
			emptyMsg: '没有查找记录'
		});
		this.callParent(); 
	},
	setRole:function(obj){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "设置角色", // 标题
			width : 400, // 宽度
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			resizable : false, // 不允许改变窗体大小
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				//baseCls: 'x-plain',
				buttonAlign:"center",
				//layout: 'form',
		            items :[{
			            	name: 'id',
			            	xtype: 'hidden',
			            	value:obj.object.id
		        		},{
		            	height:70,
		            	xtype:'fieldset',
		            	title:'基本信息',
		                layout: {
		                    type: 'table',
		                    columns: 2
		                },
		                defaults: {padding:'10 25',marings:'10 0 0 0'},
		            	items:[{
		            		xtype: 'label',
		            		border:true,
		            		text: '编号:'+obj.object.number,
		            		name: 'number'
		            	},{
		            		xtype: 'label',
		            		text: '用户名:'+obj.object.name,
		            		name: 'name'
		            	},{
		            		xtype: 'label',
		            		text: '真实姓名:'+obj.object.relName,
		            		name: 'relName'
		            	},{
		            		xtype: 'label',
		            		text: '电话:'+obj.object.tel,
		            		name: 'tel'
		            	}]
		            },{
		            	xtype:'fieldset',
		            	title:'用户角色',
		            	height:210,
		            	items:[{
		                    xtype: 'itemselector',
		                    name: 'itemselector',
		                   // id: 'itemselector-field',
		                    anchor: '100%',
		                    //fieldLabel: 'ItemSelector',
		                    imagePath: '../ux/images/',
		                    store:Ext.create('Ext.data.ArrayStore', {
		            		    fields: ['value', 'text'],
		            		    data : obj.object.data
		            		}),
		                    displayField: 'text',
		                    valueField: 'value',
		                    value: obj.object.indata,
		                    allowBlank: false,
		                    msgTarget: 'side',
		                    fromTitle: '未选择',
		                    toTitle: '已选择'
		                }]
	            	}],
					buttons:[{
						text: '保存信息',
						xtype:'u_btn',
						baseCls: 't-btn-red',
				        handler: function(){
				        	var form = win.getComponent(0).getForm();
				        	if (form.isValid()) {
				                form.submit({
				                	url:'setRole.do',
				                    success: function(form, action) {
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
						text: '取消',
						xtype:'u_btn',
						baseCls: 't-btn-yellow',
				        handler: function(){
				        	//win.getComponent(0).getForm().reset();
				        	win.close();
				        }
					}]
			})
        });
		win.show();
	},
	delPerson:function(id){
		var self=this;
    	Ext.Msg.confirm('提示','是否确定要删除该人员?',function(btn) {
			if (btn == 'yes') {
    			Ext.Ajax.request({
    			    url: 'delPerson.do?id='+id,
    			    success: function(response){
    			    	 var text = response.responseText;
	    			     var obj=Ext.JSON.decode(text);
	    			     if(obj.status){
	    			    	 self.getComponent(0).getStore().reload();
	    			     }
	    			     Ext.Msg.alert('提示',obj.msg);
    			    },
                    failure: function(form, action) {
                        Ext.Msg.alert('提示', "服务器连接失败，请联系管理员");
                    }
    			});
			}
          });
	},
	//修改人员方法
	updatPerson:function(person){
		var self=this;
		var i=0;
		var win = Ext.create('Ushine.win.Window', {
			title : "修改人员", // 标题
			width : 400, // 宽度
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
	            		fieldLabel: '真实姓名',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'trueName',
	            		emptyText: '请输入真实姓名',
	            		blankText:'真实姓名不能为空',
	            		value:person.trueName,
	            		
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '警员编号',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'number',
	            		emptyText: '请输入警员编号',
	            		blankText:'警员编号不能为空',
	            		value:person.number,
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '登录IP',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'ip',
	            		emptyText: '请输登录IP',
	            		blankText:'登录IP不能为空',
	            		value:person.ip
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '联系电话',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'tel',
	            		emptyText: '请输入联系电话',
	            		value:person.tel,
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '用户名',
	            		allowNegative: false,
	            		allowBlank: false,
	            		name: 'userName',
	            		blankText:'用户名不能为空',
	            		emptyText: '请输入用户名',
	            		value:person.userName,
	            	},{
	            		xtype: 'textfield',
	            		fieldLabel: '身份证号码',
	            		allowNegative: false,
	            		//allowBlank: false,
	            		name: 'IDCard',
	            		blankText:'身份证不能为空',
	            		emptyText: '请输入身份证',
	            		value:person.IDCard,
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '所属组织',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'org',
	            		name:'org',
	            		valueField: 'value',
	            		emptyText: '----请选择组织----',
	            		blankText:'所属组织不能为空',
	            		queryMode:'local', 
						store : new Ext.data.JsonStore({
							proxy: new Ext.data.HttpProxy({
								url : "getOrgs.do?scope=person"
							}),
	            		    fields: [
	             		            {name: 'text', type:'string', mapping: 'text'},
	             		        	{name: 'value', type:'string', mapping: 'value'}
	 	            		    ],
						    autoLoad:true,
						    autoDestroy: true
						}),
						value:person.orgId,
					    listeners:{
					        select: function(combo, record, index) {
					        	var id=combo.getValue();
					        	win.getComponent(0).getComponent(0).getComponent(7).setValue(null);
					        	person.orgId=id;
					        	win.getComponent(0).getComponent(0).getComponent(7).store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        },
					        render:function(combo, record, index){
					        	var id=combo.getValue();
					        	win.getComponent(0).getComponent(0).getComponent(7).store.load({
					    			params: {
					    				orgId:id
					    			}
					        	});
					        }
					    }	
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '上级部门',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		hiddenName: 'dept',
	            		name:'dept',
	            		valueField: 'value',
	            		displayField:'text',
	            		queryMode:'local', 
	            		blankText:'上级部门不能为空',
	            		emptyText: '----请选择部门----',
						store :new Ext.data.Store({
	            		    fields: [
	             		            {name: 'text', type:'string', mapping: 'text'},
	             		        	{name: 'value', type:'string', mapping: 'value'}
	 	            		    ],
						    proxy: new Ext.data.HttpProxy({
						    	url : "getDepts.do?scope=dep"
						    }),
						    autoLoad:false,
						    autoDestroy: true
						}),
						listeners:{
							render:function(){
								this.setValue(person.deptId)
							}
						}
	            	},{
	            		xtype:'combo',
	            		fieldLabel: '是否启用IP验证',
	            		allowNegative: false,
	            		allowBlank: false,
	            		editable: false,
	            		id:'status',
	            		hiddenName: 'status',
	            		name:'status',
	            		valueField: 'value',
	            		displayField:'text',
	            		store:Ext.create('Ext.data.Store', {
	            		    fields: [
            		            {name: 'text', type:'string', mapping: 'text'},
            		        	{name: 'value', type:'string', mapping: 'value'}
	            		    ],
	            		    data : [
	            		        {"text":"是", "value":"1"},
	            		        {"text":"否", "value":"0"}
	            		    ]
	            		}),
	            		value:person.status+""
	            	}]
				},
				buttons:[{
					text: '修改信息',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	if (form.isValid()) {
			                form.submit({
			                	url:'updatePerson.do?personId='+person.id,
			                	submitEmptyText:false,
			                	actionMethods: {
			    	                create : 'POST',
			    	                read   : 'POST', // by default GET
			    	                update : 'POST',
			    	                destroy: 'POST'
			    	            },
			                    success: function(form, action) {
			                       if(action.result.status){
			                    	   self.getComponent(0).getStore().reload();
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
			}),
		    listeners: {
				close: function(thiz, options) {
					this.destroy();
				}
		    }
        });
		win.show();
	}
	
});
