/**
 * @author xyt
 * 窗体工具类
 */
Ext.define('Ushine.utils.WinUtils', {
	/**
	 * @author xyt
	 * 公司选择器(存公司省略名字的控件,(获得焦点事件的三个参数),网点控件)
	 */
	companyWin:function(o,thiz,the,eObj,spot){
		var win = Ext.create('Ushine.win.Window', {
			title : '选择公司',
			closable : true,
			draggable:false,
			modal:true,
			width : 400,
			height : 400,
			plain : true,
			layout : "fit",
			border:false,
			buttonAlign:"center",
			items :Ext.create('Ushine.utils.Company'),
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var records = this.ownerCt.ownerCt.getComponent(0).getSelectionModel().getSelection();
					var names = [], ids = [];
					thiz.companyDate=[];
					for(var i = 0; i < records.length; i++){  
						ids.push(records[i].get("companyCode"));
						names.push(records[i].get("companyName"));
						thiz.companyDate.push(records[i].data);
                    }
					o.setValue(ids);
					thiz.setValue(names);
					if(spot){//如果有网点
						if(names.length>0){
							spot.setValue("");
							spot.setReadOnly(false);
						}else{
							spot.setValue("");
							spot.setReadOnly(true);
						}
					}
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	
	abc:function(icon){
		var selNodeId = icon;
		
		var m_class = "m-btn m-btn-hover x-item-selected"; // 未选中背景
		var a_class = "m-btn m-btn-hover x-item-selected m-btn-activity"; // 选中背景
		
		var mIcon = "normal-icon";
		var aIcon = "activity-icon";
			
		var menuItemss = Ext.query('.m-btn');
		for(var i=0; i<menuItemss.length; i++) {
			if(selNodeId == menuItemss[i].id) {
				menuItemss[i].setAttribute("class", a_class);
				menuItemss[i].childNodes[0].setAttribute("class", aIcon);
			} else {
				menuItemss[i].setAttribute("class", m_class);
				menuItemss[i].childNodes[0].setAttribute("class", mIcon);
			}
		}
		var a = Ext.getCmp('menu_frame');
		if(icon=='DataSearch'){
    		a.select(0);
    	}else if(icon=='PersonStoreManage'){
    		a.select(1);
    	}else if(icon=='ClueStoreManage'){
    		a.select(2);
    	}else if(icon=='DocStoreManage'){
    		a.select(3);
    	}else if(icon=='SystemSet'){
    		a.select(4);
    	}
	},
	spotWin:function(thiz,the,eObj,companyDate){
		var win = Ext.create('Ushine.win.Window', {
			title : '选择网点',
			closable : true,
			draggable:false,
			modal:true,
			width : 600,
			height : 400,
			plain : true,
			border:false,
		    layout: {
		        type: 'hbox',
		        align: 'stretch'
		    },
		    buttonAlign:"center",
			items :[new Ushine.utils.CopyCompany(companyDate),new Ushine.utils.Spot()],
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var records = this.ownerCt.ownerCt.getComponent(1).getSelectionModel().getSelection();
					var names = [], ids = [];
					for(var i = 0; i < records.length; i++){    
						ids.push(records[i].get("id"));
						names.push(records[i].get("brName"));
                    }  
					thiz.setValue(names);
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	middleTableWin:function(TaskId,taskType,filterField,filterValue,sortField,sortType){
		var win = Ext.create('Ushine.win.Window', {
			title : '保存中间库',
			closable : true,
			draggable:false,
			modal:true,
			width : 400,
			height : 200,
			plain : true,
			border:false,
		    buttonAlign:"center",
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
		            items :[{
			            	name: 'taskId',
			            	xtype: 'hidden',
			            	value:TaskId
		        		},{
			            	name: 'taskId',
			            	xtype: 'hidden',
			            	value:TaskId
		        		},{
			            	name: 'filterField',
			            	xtype: 'hidden',
			            	value:filterField
		        		},{
			            	name: 'filterValue',
			            	xtype: 'hidden',
			            	value:filterValue
		        		},{
			            	name: 'sortField',
			            	xtype: 'hidden',
			            	value:sortField
		        		},{
			            	name: 'sortType',
			            	xtype: 'hidden',
			            	value:sortType
		        		},{
		            		xtype: 'textfield',
		            		fieldLabel: '中间库名称',
		            		allowBlank: false,
		            		name: 'tableName'
		        		},{
		            		xtype: 'textarea',
		            		fieldLabel: '备注',
		            		allowNegative: false,
		            		//allowBlank: false,
		            		name: 'dec'
		        		}],
					buttons:[{
						text: '保存',
						xtype:'u_btn',
						baseCls: 't-btn-red',
				        handler: function(){
				        	var form = win.getComponent(0).getForm();
				        	if (form.isValid()) {
				                form.submit({
				                	url:'addMiddleTable.do',
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
	    	
		}).show();
	},
	personWin:function(thiz,records){
		var win = Ext.create('Ushine.win.Window', {
			title : '选择人员',
			closable : true,
			draggable:false,
			modal:true,
			width:600,
			height:500,
			plain : true,
			layout : "fit",
			border:false,
			bodyStyle: 'padding: 10px',
			buttonAlign:"center",
			items :Ext.create('Ushine.utils.PersonSelect'),
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var personGrid=this.ownerCt.ownerCt.getComponent(0).getComponent(1);
					if(personGrid.getSelectionModel().hasSelection()){
						//record 选择的人员数组
						var record = personGrid.getSelectionModel().getSelection();
						var personIds=[];
						//userNames 所有选择的案件的共享人员数组
						var userNames=[];
						for(var i = 0; i<records.length;i++){
							userNames.push(records[i].get("userNames"));
						}
						for(var i = 0; i < record.length; i++){
							for(var j = 0;j < records.length;j++){
								//userName 一个案件里的共享人员数组
								var userName=[];
								userName = userNames[j].split(",");
								for(var k = 0;k<userName.length;k++){
									if(record[i].get("trueName").trim() == userName[k].trim()){
										Ext.create('Ushine.utils.Msg').onInfo("请不要选择已共享人员!");
										win.close();
										thiz.getComponent(2).getSelectionModel().deselectAll();
										return;
									}
								}
								personIds.push(record[i].get("id"));
							}
	                    }
						thiz.invoke(personIds);
					}else{
						Ext.create('Ushine.utils.Msg').onInfo("对不起，请选择一行记录。");
					}
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	createTask:function(searchPanel,taskId,taskName,field,value,personType,phoneType){
		var self=this;
		var win = Ext.create('Ushine.win.Window', {
			title : "创建子任务", // 标题
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
				layout: 'form',
				items:{
					title:"查询时间",
				    margins:'5 5 0 5',
				    xtype:'fieldset',
				    defaults: {width: 300},
		            items :[{
			            	name: 'id',
			            	xtype: 'hidden',
			            	value:taskId
		        		},{
			            	name: 'field',
			            	xtype: 'hidden',
			            	value:field
		        		},{
			            	name: 'value',
			            	xtype: 'hidden',
			            	value:value
		        		},{
			            	name: 'taskName',
			            	xtype: 'hidden',
			            	value:taskName
		        		},{
			            	name: 'fieldType',
			            	xtype: 'hidden',
			            	value:personType
		        		},{
			            	name: 'checkType',
			            	xtype: 'hidden',
			            	value:phoneType
		        		},{
		        			name:'startDate',
		        			xtype: 'datefield',
		            		fieldLabel: '开始时间',
							format : 'Y-m-d',
							value:new Date()
		        		},{
		        			name:'endDate',
		        			xtype: 'datefield',
		            		fieldLabel: '结束时间',
							format : 'Y-m-d',
							value:new Date()
			        	},{
	                        fieldLabel: '日期类型',
							labelAlign : 'right',
							name: 'dateTyle',
	                        xtype: 'checkboxgroup',
	                        columns: [80, 80],
	                        items: [
	                            {name:'dateTyle',boxLabel: '发货时间', inputValue: "0",checked: true},
								{name: 'dateTyle',boxLabel: '收货时间', inputValue: "1"}
	                        ]
	                    }]
				},
				buttons:[{
					text: '提交',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	if (form.isValid()) {
			        		form.submit({
			   					url:'addSubSearchTask.do',
			   					method:'post',
			   					success:function(from,action){
			   						var values=from.getValues();
			   						if(action.result.status){
			   							searchPanel.goBack(action.result.object.id,values["taskName"],values["startDate"],values["endDate"],values["dateTyle"],personType,phoneType,value);	
			   						}
				    				Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
			   						win.close();
			   					},
			   					failure:function(from,action){
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
			   					}
			   				});
			            }
			        }
				}]
			})
        });
		win.show();
	},
	exportWin:function(ids){
		//console.log(ids);
		var win = Ext.create('Ushine.win.Window', {
			title : '导出excel',
			closable :true,
			draggable:false,
			modal:true,
			width : 350,
			height : 150,
			plain : true,
			border:false,
		    buttonAlign:"center",
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
		            items :[{
			          		labelWidth:60,
		            		xtype: 'textfield',
		            		fieldLabel: '任务名称',
		            		width:260,
		            		allowBlank: false,
		            		name: 'tableName'
		        		}],
					buttons:[{
						text: '保存',
						xtype:'u_btn',
						baseCls: 't-btn-red',
				        handler: function(){
				        	var btn=this;
				        	btn.disable();
				        	var form = win.getComponent(0).getForm();
				        	if(form.isValid()){
				        		form.submit({
				   					url:'exportByIds.do',
				   					method:'post',
				   					params: {
					        	        ids: ids
					        	    },
				   					success:function(from,action){
					    				Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
				   						win.close();
				   					},
				   					failure:function(from,action){
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
	    	
		}).show();
	},
	/**
	 * 选择多城市窗体
	 * @param thiz
	 * @param the
	 * @param eObj
	 */
	cityWin:function(thiz,the,eObj){
		var win=Ext.create('Ushine.win.Window', {
			title : '选择城市',
			closable : true,
			draggable:false,
			modal:true,
			width : 300,
			height : 400,
			plain : true,
			layout : "fit",
			border : false, // 不要边框
			buttonAlign:"center",
			items : [{
				xtype : "treepanel", // 指定类型为树面板
				autoScroll : true, // 自动添加滚动条
				border : true, // 不要边框
				rootVisible : false,
				store: new Ext.data.TreeStore({
			        proxy: {
			            type: 'ajax',
			            url:'findCitys.do'
			        }
			    }),
				//父节点状态改变，字节点跟着改变
				listeners:{
					'checkchange':function(node,state){
						node.expand();  
					    node.checked = state;  
					    node.eachChild(function (child) {  
					        child.set('checked', state);  
					        child.fireEvent('checkchange', child, state);  
					    });  
					},
					'load':function(store,records){
//						if(names[j]==records.childNodes[i].get("text")){
//							records.childNodes.set('checked', true);
//						}
						if(thiz.ids&&thiz.ids[0]!=""){
							for(var i=0;i<thiz.ids.length;i++){
								var record = this.getStore().getNodeById(thiz.ids[i]);
								record.set('checked', true);  
							}
						}
					}
				}
			}],
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var records =  this.ownerCt.ownerCt.getComponent(0).getChecked();
					var names = new Array(),ids=new Array();
					Ext.each(records, function(node){
						if(node.data.expanded==false){
							ids.push(node.data.id);
							//console.log(node.data);
							names.push(node.data.text);
						}
				    });
					if(names.length>0){
						thiz.ids=ids;
					}else{
						thiz.ids=null;
					}
					thiz.setValue(names);
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	/**
	 * 选择单城市窗体
	 */
	asingleCityWin:function(thiz,the,eObj,id){
		var win=Ext.create('Ushine.win.Window', {
			title : '选择户籍地',
			closable : true,
			draggable:false,
			modal:true,
			width : 300,
			height : 400,
			plain : true,
			layout : "fit",
			border : false, // 不要边框
			buttonAlign:"center",
			items : [{
				xtype : "treepanel", // 指定类型为树面板
				autoScroll : true, // 自动添加滚动条
				border : true, // 不要边框
				rootVisible : false,
				store: new Ext.data.TreeStore({
			        proxy: {
			            type: 'ajax',
			            url:'findCitys.do'
			        }
			    }),
				//父节点状态改变，字节点跟着改变
				listeners:{
					'checkchange':function(node,state){
						node.expand();  
					    node.checked = state;  
					    node.eachChild(function (child) {  
					        child.set('checked', state);  
					        child.fireEvent('checkchange', child, state);  
					    });  
					},
					'load':function(store,records){
//						if(names[j]==records.childNodes[i].get("text")){
//							records.childNodes.set('checked', true);
//						}
						if(thiz.ids&&thiz.ids[0]!=""){
							for(var i=0;i<thiz.ids.length;i++){
								var record = this.getStore().getNodeById(thiz.ids[i]);
								record.set('checked', true);  
							}
						}
					}
				}
			}],
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var records =  this.ownerCt.ownerCt.getComponent(0).getChecked();
					var names = new Array();
					var ids=new Array();
					Ext.each(records, function(node){
							ids.push(node.data.id);
							//console.log(node.data);
							names.push(node.data.text);
						
				    });
					//判断用户的选择城市数，如果选择的城市数>=2就提示用户只能选择一个户籍城市地址
					if(names.length>=2){
						Ext.create('Ushine.utils.Msg').onInfo("只能选择一个城市!");
						return null;
					}
					if(names.length>0){
						thiz.ids=ids;
						
					}else{
						thiz.ids=null;
					}
					thiz.setValue(names);
					Ext.getCmp(id).setValue(ids[0]);
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	/**
	 * 选择注册区域
	 */
	regCityWin:function(thiz,the,eObj,id){
		var win=Ext.create('Ushine.win.Window', {
			title : '注册区域',
			closable : true,
			draggable:false,
			modal:true,
			width : 300,
			height : 400,
			plain : true,
			layout : "fit",
			border : false, // 不要边框
			buttonAlign:"center",
			items : [{
				xtype : "treepanel", // 指定类型为树面板
				autoScroll : true, // 自动添加滚动条
				border : true, // 不要边框
				rootVisible : false,
				store: new Ext.data.TreeStore({
			        proxy: {
			            type: 'ajax',
			            url:'loadingNode.do'
			        }
			    }),
				//父节点状态改变，字节点跟着改变
				listeners:{
					'checkchange':function(node,state){
						node.expand();  
					    node.checked = state;  
					    node.eachChild(function (child) {  
					        child.set('checked', state);  
					        child.fireEvent('checkchange', child, state);  
					    });  
					},
					'load':function(store,records){
//						if(names[j]==records.childNodes[i].get("text")){
//							records.childNodes.set('checked', true);
//						}
						if(thiz.ids&&thiz.ids[0]!=""){
							for(var i=0;i<thiz.ids.length;i++){
								var record = this.getStore().getNodeById(thiz.ids[i]);
								record.set('checked', true);  
							}
						}
					}
				}
			}],
			buttons : [ {
				text : "确定", // 按钮上面的字
				xtype:'u_btn',
				baseCls: 't-btn-red',
				handler : function(){
					var records =  this.ownerCt.ownerCt.getComponent(0).getChecked();
					var names = new Array();
					var ids=new Array();
					Ext.each(records, function(node){
							ids.push(node.data.id);
							//console.log(node.data);
							names.push(node.data.text);
						
				    });
					//判断用户的选择城市数，如果选择的城市数>=2就提示用户只能选择一个户籍城市地址
					if(names.length>=2){
						Ext.create('Ushine.utils.Msg').onInfo("只能选择一个城市!");
						return null;
					}
					if(names.length>0){
						thiz.ids=ids;
						
					}else{
						thiz.ids=null;
					}
					thiz.setValue(names);
					Ext.getCmp(id).setValue(ids[0]);
					win.close();
				}
			}, {
				text : "取消",
				xtype:'u_btn',
				baseCls: 't-btn-yellow',
				handler : function(){
					win.close();
				}
			} ]
	    	
		}).show();
	},
	/**
	 * @author xyt
	 * 电子面单(选中行数据)
	 */
	orderWin1:function(data){
		Ext.create('Ushine.win.Window',{
			width: 880,
			height: 600,
			title: '电子面单',
			closable: true,
			draggable: false,
			shadow: false,
			resizable: false,
			modal: true,
			bodyStyle: 'padding: 5px;', //ccd8e8
			layout: 'fit',
	        contentEl : Ext.DomHelper.append(document.body, {
	        	tag : 'iframe',
	
	        	style : "border 0px none;scrollbar:true",
	
	        	src : "getOrder.do?orderNo=" + data.orderNo,
	        	
	        	frameborder:0,
	
	        	height : "100%",
	
	        	width : "100%"
	        	})
		}).show();
	},
	orderWin:function(data){
		Ext.create('Ushine.win.Window',{
			width: 880,
			height: 600,
			title: '电子面单',
			closable: true,
			draggable: false,
			shadow: false,
			resizable: false,
			modal: true,
			bodyStyle: 'padding: 5px;', //ccd8e8
			layout: 'fit',
	        contentEl : Ext.DomHelper.append(document.body, {
	        	tag : 'iframe',
	
	        	style : "border 0px none;scrollbar:true",
	
	        	src : "getOrderById.do?id=" + data.id,
	        	
	        	frameborder:0,
	
	        	height : "100%",
	
	        	width : "100%"
	        	})
		}).show();
	},
	/**
	 * grid需要刷新的grid
	 * data原来的数据
	 */
	setDec:function(grid,data){
		var win = Ext.create('Ushine.win.Window', {
			title : '编辑备注信息',
			closable : true,
			draggable:false,
			modal:true,
			width : 380,
			height : 260,
			plain : true,
			items:new Ext.FormPanel({
				bodyStyle: 'padding: 10px',
				baseCls: 'x-plain',
				border:false,
				buttonAlign:"center",
	            items :[{
	        		xtype: 'hidden',
	        		name: 'id',
	        		value: data.id
	        	},{
	        		width:350,
	        		height:150,
            		name: 'dec',
            		xtype: 'textarea',
            		allowBlank: false,
            		value:data.dec
            	}],
				buttons:[{
					text: '确定',
					xtype:'u_btn',
					baseCls: 't-btn-red',
			        handler: function(){
			        	var form = win.getComponent(0).getForm();
			        	var btn=this;
			        	if (form.isValid()) {
			                form.submit({
			                	url:'modifyDec.do',
			                    success: function(form, action) {
			                    		win.close();
			                    		Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
			                    		grid.getSelectionModel().deselectAll();
			                    		grid.getStore().load();
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
            			            	Ext.create('Ushine.utils.Msg').onError(action.result.msg);
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
		})
		win.show();
	},
	mapWin:function(start,end,scity,rcity){
		var map;
		var win = Ext.create('Ushine.win.Window', {
			title : '地图信息',
			closable : true,
			draggable:false,
			modal:true,
			width : 800,
			height : 600,
			plain : true,
			html:'<div id="map" style="width:100%;height:100%;border:solid 1px cbcbcb;"></div>',
			listeners: {
				boxready: function(){
					this.initMap();
					this.getStartPostion(start);
					this.getEndPostion(end);
				}
			},
			initMap:function(){
				var position = new LD.LngLat(121.47470, 31.23069);
				//var bounds = new LD.LngLatBounds(new LD.LngLat(120.84987,30.67979), new LD.LngLat(122.06278,31.84125));
				var opt = new LD.MapOptions();
				opt.backgroundImg = null;
				opt.minZoom = 1;
				opt.maxZoom = 18;
				opt.zoom = 15;
				opt.dragable = true;
				opt.scrollWheel = true;
				opt.dbclickZoom = true;
				opt.hotspot = true;
				opt.animation = true;
				opt.inertial = true;
				opt.center = position;
				map = new LD.Map("map", opt);
				//map.setMaxLimit(bounds);
				//导航条
				var navi = new LD.NavigationControl({
					anchor: LD.Constants.LEFT_TOP
				});
				map.addControl(navi);
				//标尺
				var scale = new LD.ScaleControl({
					offset: new LD.Pixel(10, -25)
				});
				map.addControl(scale);
			},
			startMarker:function(x,y,address){
				var position = new LD.LngLat(x,y);
				var mo = new LD.MarkerOptions();
				//mo.editabled = true;
				mo.icon = new LD.Icon("images/marker_set.png", new LD.Size(22, 35),
								new LD.Pixel(-234, -07));
				mo.markerColor = "#000000";
				mo.pointType = LD.Constants.OVERLAY_MARKER_POINT;
				var marker = new LD.Marker(position, mo);
				map.getOverlayLayer().addOverlay(marker, true);
				var vlOpt = new LD.LabelOptions();
				vlOpt.position = position;
				vlOpt.offset = new LD.Pixel(12, -18);
				vlOpt.anchor = LD.Constants.LEFT_TOP;
				vlOpt.fontSize = 12;
				var label = new LD.Label(address, vlOpt);
				map.getOverlayLayer().addOverlay(label, false);
			},
			endMarKer:function(x,y,address){
				var position = new LD.LngLat(x,y);
				var mo = new LD.MarkerOptions();
				mo.icon = new LD.Icon("images/marker_set.png", new LD.Size(22, 35),new LD.Pixel(-256, -07));
				//mo.editabled = true;
				mo.markerColor = "#000000";
				mo.pointType = LD.Constants.OVERLAY_MARKER_POINT;
				var marker = new LD.Marker(position, mo);
				map.getOverlayLayer().addOverlay(marker, true);
			 	var vlOpt = new LD.LabelOptions();
				vlOpt.position = position;
				vlOpt.offset = new LD.Pixel(12, -18);
				vlOpt.anchor = LD.Constants.LEFT_TOP;
				vlOpt.fontSize = 12;
				var label = new LD.Label(address, vlOpt);
				map.getOverlayLayer().addOverlay(label, false); 
			},
			getStartPostion:function(address){
				var self=this;
				Ext.data.JsonP.request({
					url: 'http://12.40.10.3:8780/v2/truecloud/search',
					params: {
						access_token: 'abc',
						region:scity,
						uid: 1355,
						//ac: '310000',
						page_size: 1,
						page_num: 1,
						q: address
					},
					success: function(result){
						//console.log(result);
						if(result.total<=0) return;
						if(result.results[0].location.lng){
							var lng = result.results[0].location.lng;
							var lat = result.results[0].location.lat;
							self.startMarker(lng, lat, address);
						}else{
							var center = result.records[0].center.split(",");
							self.startMarker(center[0], center[1], address);
						}
					}
				});
			},
			getEndPostion:function(address){
				var self=this;
				Ext.data.JsonP.request({
					url: 'http://12.40.10.3:8780/v2/truecloud/search',
					params: {
						access_token: 'abc',
						region:rcity,
						uid: 1355,
						//ac: '310000',
						page_size: 1,
						page_num: 1,
						q: address
					},
					success: function(result){
						//console.log(result);
						if(result.total<=0) return;
						if(result.results[0].location.lng){
							//var lng = result.records[0].posX/100000;
							//var lat = result.records[0].posY/100000;
							var lng = result.results[0].location.lng;
							var lat = result.results[0].location.lat;
							self.endMarKer(lng, lat, address);
						}else{
							var center = result.records[0].center.split(",");
							self.endMarKer(center[0],center[1], address);
						}
					}
				});
			}
		})
		win.show();
	}
})