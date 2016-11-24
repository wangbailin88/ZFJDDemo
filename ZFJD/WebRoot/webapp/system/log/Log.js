/**
 *log面板
 * @author 陈曦
 */
Ext.define('Ushine.system.log.Log', {
	extend: 'Ext.panel.Panel',
	id: 'log-panel',
	region: 'center',
	title:'执法监督管理',
	bodyStyle: 'background-color: #ffffff; border: none; padding: 10px;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	}, 
	constructor: function() {
		var self = this;
		this.items = [  
			{
				// 工具栏
				id:'labl',
				xtype: 'form',
				border:false,
				height: 110,
				baseCls: 'tar-body',
				
				// 工具栏 -- 左侧
				lbar: {
					//表单
					xtype: 'form',
					border: false,
					id:'logform',
					baseCls: 'form-body',
					items:[{
						layout: "column", //行1
						height: 26,
						baseCls: 'panel-body',
						items:[{
							labelAlign : 'right',
							fieldLabel: '日志类型',
							labelWidth: 60,
							xtype: 'combo',
							name: 'type',
							editable: false,
//							margins: '0 10 0 30',
							id:'type',
							valueField: 'value',
		            		store:Ext.create('Ext.data.Store', {
		            		    fields: ['text', 'value'],
		            		    data : [
		            		        {"text":"查看全部", "value":""},
		            		        {"text":"登录系统", "value":"1"},
		            		        {"text":"登出系统", "value":"2"},
		            		        {"text":"新增记录", "value":"3"},
		            		        {"text":"删除记录", "value":"4"},
		            		        {"text":"查询记录", "value":"5"},
		            		        {"text":"修改记录", "value":"6"},
		            		    ]
		            		}),
							emptyText: '请选择日志类型',
							height: 22,
							width: 240,
						},{
							labelAlign : 'right',
							fieldLabel: '操作人员:',
							labelWidth: 100,
							id:'userName',
							name:'userName',
							xtype: 'textfield',
							height: 22,
							width: 280,
							emptyText: '请输入操作人员',
						},{
							labelAlign : 'right',
							fieldLabel: '人员编号:',
							labelWidth: 100,
							name:'userCode',
							id:'userCode',
							xtype: 'textfield',
							height: 22,
							width: 280,
							emptyText: '请输入操作人员',
						}]
					},{
						layout: "column", //行2
						height: 60,
						baseCls: 'panel-body',
						items:[{
							labelAlign : 'right',
							fieldLabel: '操作参数:',
							labelWidth: 60,
							id:'IP',
							name:'IP',
							xtype: 'textfield',
							height: 22,
							width: 240,
							emptyText: '请输入操作参数',
						},{
							labelAlign : 'right',
							fieldLabel: '开始时间:',
							id:'start-time',
							labelWidth: 100,
							format: 'Y-m-d', 
							//editable:false,
							xtype: 'datefield',
							maxValue: new Date(),
							height: 22,
							width: 280,
							emptyText: '请选择开始时间',
						},{
							labelAlign : 'right',
							fieldLabel: '结束时间:',
							labelWidth: 100,
							format: 'Y-m-d', 
							xtype: 'datefield',
							//editable:false,
							height: 22,
							width: 280,
							id:'end-time',
							emptyText: '请选择结束时间',
						}]
					},{
						layout: "column", //行3
						height: 30,
						margin:'-25 0 0 0 ',
						baseCls: 'panel-body',
						items: [
						        Ext.create('Ushine.buttons.Button', {
						        	text : '查询日志',
						        	width:100,
						        	id : 'search-Button',
						        	baseCls: 't-btn-red',
						        	handler:function(){
						        		if(self.getComponent(0).getForm().isValid()){
						        			var logGrid = Ext.getCmp('log_grid_panel_id');
						        			logGrid.getSelectionModel().deselectAll(true);
						        			var s = Ext.getCmp('start-time').value;
							        		var m = Ext.getCmp('end-time').value;
							        		if(m<s){
							        			Ext.Msg.show({
							        				title:'提示',
							        				msg: '时间输入不正确（开始日期不能大于结束日期）',
							        				buttons: Ext.Msg.OK,
							        				icon: Ext.Msg.ERROR
							        			});
							        		}else{
							        			var type = Ext.getCmp("type").value;
							        			if(type=="查看全部")
													type=null;
							        			var userName = Ext.getCmp("userName").value;
							        			var userCode = Ext.getCmp("userCode").value;
							        			var IP = Ext.getCmp("IP").value;
							        			var logGrid = Ext.getCmp('log_grid_panel_id');
							        			var store = logGrid.getStore();
							        			var proxy = store.getProxy();
							        			proxy.extraParams={
							        					userName:userName,
							        					userCode:userCode,
							        					runTime:s,
							        					endTime:m,
							        					type:type,
							        					IP:IP
							        			};
							        			store.loadPage(1);
							        		}
						        		}
						        	}
						        })
						        ,
						        Ext.create('Ushine.buttons.Button', {
						        	text : '条件重置',
						        	width:100,
						        	id : 'reset-Button',
						        	baseCls: 't-btn-yellow',
						        	handler:function(){
						        		Ext.getCmp("labl").getForm().reset();
						        		Ext.getCmp('end-time').setDisabled(true);
						        	}
						        })	
						        ]
						}]
					}
				},
				Ext.create('Ext.panel.Panel',{
					border: false,
					height: 1,
					baseCls: 'conent_title_thin',
					layout: {
						type: 'hbox',
						pack: 'start',
						align: 'stretch'
					}
				}),Ext.create('Ushine.system.log.LogGridPanel') 
		];	
		this.callParent();		
	}
});
Ext.onReady(function(){
	var store = new Ext.data.JsonStore({//定义我们用的数据集，也就是Store
		proxy:{
			type:'ajax',//指定了我们的数据是通过AJAX异步申请获得的
			url:'findPersonNames.do?scope=',//远程服务器的地址
			reader:{type:'json'}
		},
		//fields表示该类拥有的字段都是什么
		fields:[{type:'string',name:'text'},{type:'string',name:'value'}]
	});
	
});