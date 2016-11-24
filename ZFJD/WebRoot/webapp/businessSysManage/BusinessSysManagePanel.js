/**
 * 业务系统管理panel
 */
 Ext.define('Ushine.businessSysManage.BusinessSysManagePanel', {
	extend: 'Ext.panel.Panel',
	border:false,
	height:'100%',
	width:'100%',
	bodyStyle: 'background-color: #ffffff;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	region: 'center',
	constructor: function(id) {
		this.tbar = [{
            iconCls: 'icon-user-add',
            text: '注册系统',
            style:'margin-left:30px;',
            scope: this,
            handler:function(){
				//调用注册业务系统函数
				self.registerVocationalWork();
			}
        },{
			fieldLabel: '条件筛选',
			labelAlign : 'right',
			xtype:'textfield',
			allowNegative: false,
			allowBlank: false,
			editable: false,
			hiddenName: 'colnum',
			name:'colnum',
			margin:'0 0 0 20',
			emptyText: '行政区域筛选',
			valueField: 'value',
			width: 240,
			listeners:{
				 specialkey:function(field,e){
                    if(e.getKey() == e.ENTER){
                    	Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
                    }  
                 }
			}
	},{
		labelAlign : 'right',
		fieldLabel: '日期筛选',
		maxValue:new Date(),
		value:new Date(),
		xtype : 'datefield',
		allowNegative: false,
		allowBlank: false,
		editable: false,
		hiddenName: 'colnum',
		margin:'0 0 0 0',
		name:'colnum',
		emptyText: '开始日期',
		valueField: 'value',
		width: 240,
		listeners:{
			 select:function(field,e){
				 Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
             }
		}
	},{
		labelAlign : 'right',
		maxValue:new Date(),
		value:new Date(),
		xtype : 'datefield',
		allowNegative: false,
		allowBlank: false,
		editable: false,
		hiddenName: 'colnum',
		name:'colnum',
		emptyText: '结束日期',
		valueField: 'value',
		width: 140,
		margin:'0 0 0 20',
		listeners:{
			 select:function(field,e){
				 Ext.create('Ushine.utils.Msg').onInfo('内部测试!');
             }
		}
	}];
		var self = this;
		this.items = [{//展示数据
			height:'100%',
			border:false,
			style:'1px solid red;',
			flex:1,
			region : 'west',
			layout:'hbox',
			items:new Ushine.businessSysManage.BusinessSysManageGridPanel(id)
		}
		//Ext.create('Ushine.dataGet.DataGetTaskLogDataGridPanel')
		];
		this.callParent();	
	},
	//注册业务系统函数
	registerVocationalWork:function(){
		var id = "";
		var win = Ext.create('Ushine.win.Window', {
			title : "注册业务系统", // 标题
			width : 300, // 宽度
			height: 260,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
			items:[{
				//表单
				xtype:'form',
				border:false,
				height:40,
				baseCls: 'form-body1',
				items:[{
						layout: "column", //行1
						height: 25,
						baseCls: 'panel-body',
						items:[{
							fieldLabel: '行政区域',
							labelAlign : 'right',
							labelWidth: 60,
							xtype:'textfield',
							allowNegative: false,
							allowBlank: false,
							editable: false,
							id:'administrationDistrict',
							name:'administrationDistrict',
							emptyText: '请输入行政区域',
							valueField: 'value',
							width: 260,
							listeners : {
								'focus' : function(thiz,the,eObj) {
									Ext.create('Ushine.utils.WinUtils').regCityWin(thiz,the,eObj,'administrationDistrictId');
								}
							}
						},{
							labelAlign : 'right',
							labelWidth: 60,
							xtype:'textfield',
							hidden:true,
							allowNegative: false,
							allowBlank: false,
							id:'administrationDistrictId',
							editable: false,
							emptyText: '请输入行政区域',
							valueField: 'value',
							allowBlank:false,
							blankText:'此选项不能为空',
							width: 260
						},{
							fieldLabel: '系统名称',
							labelAlign : 'right',
							labelWidth: 60,
							xtype:'textfield',
							allowNegative: false,
							allowBlank: false,
							editable: false,
							allowBlank:false,
							blankText:'此选项不能为空',
							name:'systemName',
							id:'systemName',
							margin:'14 0 0 0',
							emptyText: '请输入系统名称',
							valueField: 'value',
							width: 260
						},{
							fieldLabel: '备注信息',
							labelAlign : 'right',
							labelWidth: 60,
							xtype:'textarea',
							allowNegative: false,
							allowBlank: false,
							editable: false,
							margin:'14 0 0 0',
							name:'remarks',
							id:'remarks',
							emptyText: '请输入备注信息',
							valueField: 'value',
							width: 260,
						},Ext.create('Ushine.buttons.Button', {
							text : '注册',
							style:"margin:14 0 0 80",
							width:100,
							labelWidth: 60,
							height:22,
							baseCls: 't-btn-yellow',
							handler:function(){
								//行政区域名称和id
								var administrationDistrict = Ext.getCmp('administrationDistrict').value;
								var administrationDistrictId = Ext.getCmp('administrationDistrictId').value;
								//系统名称
								var systemName = Ext.getCmp('systemName').value;
								//备注信息
								var remarks = Ext.getCmp('remarks').value;
								
								
								//判断选项是否为空如果为空，提示用户不能为空
								if(administrationDistrict == null){
									Ext.create('Ushine.utils.Msg').onInfo('行政区域不能为空!');
									return;
								}else if(systemName==null){
									Ext.create('Ushine.utils.Msg').onInfo('系统名称不能为空!');
									return;
								}
								//试用ajax请求服务器
			    				   Ext.Ajax.request({
			    					   url: 'saveVocationWork.do',
			    					   actionMethods: {
			    						   create : 'POST',
			    						   read   : 'POST', // by default GET
			    						   update : 'POST',
			    						   destroy: 'POST'
			    					   },
			    					   //要修改公司的id的集合
			    					   params: {
			    						   administrationDistrictId:administrationDistrictId,
			    						   systemName:systemName,
			    						   remarks:remarks
			    					   },
			    					   success: function(response){
			    						   var text = response.responseText;
			    						   var obj=Ext.JSON.decode(text);
			    						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
			    						   win.close();
			    						   //启用成功后刷新数据
			    						   Ext.getCmp(administrationDistrictId+'_BusinessSysManageGridPanel').getStore().reload();
			    					   },
			    					   failure: function(form, action) {
			    						   Ext.create('Ushine.utils.Msg').onInfo('请求失败，请联系管理员');
			    					   }
			    				   });
						}
					})]
				}]
				}]
        });
		win.show();
	}
});
