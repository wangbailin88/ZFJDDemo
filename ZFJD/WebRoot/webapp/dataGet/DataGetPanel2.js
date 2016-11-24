/**
 * 数据获取管理panel
 * @author wangbailin
 */
Ext.define('Ushine.dataGet.DataGetPanel2', {
	extend : 'Ext.panel.Panel',
	region: 'center',
	id : 'dataGet_dataGetPanel',
	region: 'center',
	border:false,
	bodyStyle: 'background-color: #E8E8E8;',
	layout: 'border',
    rtl: true,
	constructor : function() {
		var thiz = this;
		this.items=[{
	            region: 'west',
	            id: 'west-region',
	            title: '获取数据表单',
	            width:320,
	            collapsible: true,
	            split: true,
	            items:[new Ext.FormPanel({
				layout: {
                    type: 'vbox'
                },
                border:false,
                padding:'20 0 0 0',
				buttonAlign:"center",
				items : [{
					fieldLabel:'数据名称',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入数据名称',
					blankText:'此选项不能为空',
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					id:'dataName',
					name : 'dataName'
				},{
					fieldLabel:'行政区域',
					allowBlank:false,
					xtype : 'combo',
					emptyText:'请选择行政区域',
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					style:'margin-top:8px;',
					name : 'administrativeArea',
					listeners : {
						'focus' : function(thiz,the,eObj) {
							Ext.create('Ushine.utils.WinUtils').asingleCityWin(thiz,the,eObj,'administrationDistrictId2');
						}
					}
				},{
					labelAlign : 'right',
					labelWidth: 60,
					xtype:'textfield',
					hidden:true,
					allowNegative: false,
					allowBlank: false,
					id:'administrationDistrictId2',
					editable: false,
					emptyText: '请输入行政区域',
					valueField: 'value',
					allowBlank:false,
					blankText:'此选项不能为空',
					width: 260
				},{
					fieldLabel:'业务系统',
					allowBlank:false,
					xtype: 'combo',
					id:'vocationWorkSys',
					editable:false,
					typeAhead:false,
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					style:'margin-top:8px;',
					store:['FY','SJFWPT','JK','QB','DR'],
					emptyText: '请选择业务系统',
				},{
					fieldLabel:'插件版本',
					allowBlank:false,
					xtype: 'combo',
					id:'workVersion',
					editable:false,
					typeAhead:false,
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					store:['V1.0.2','V2.3.4','V3.0.2','V4.3.5'],
					style:'margin-top:8px;',
					emptyText: '请选择业务系统',
				},{
					fieldLabel:'数据类型',
					allowBlank:false,
					xtype: 'combo',
					id:'dataType',
					editable:false,
					typeAhead:false,
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					store:['查询','新增','删除','修改'],
					style:'margin-top:8px;',
					emptyText: '请选择数据类型',
				},{
					fieldLabel:'开始日期',
					allowBlank:false,
					maxValue:new Date(),
					value:new Date(),
					xtype : 'datefield',
					editable:false,
					id:'startTime',
					typeAhead:false,
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					style:'margin-top:8px;',
					emptyText: '请选择开始日期',
				},{
					fieldLabel:'结束日期',
					allowBlank:false,
					maxValue:new Date(),
					value:new Date(),
					xtype : 'datefield',
					editable:false,
					id:'endTime',
					typeAhead:false,
					width: 280,
					labelWidth : 100,
					labelAlign : 'right',
					height:24,
					style:'margin-top:8px;',
					emptyText: '请选择结束日期',
				}],
				buttons : [
			   		Ext.create('Ushine.buttons.Button', {
				   		text: '获取数据',
				   		width:100,
				   		height:30,
				   		margin:'20 0 0 0',
				   		baseCls: 't-btn-yellow',
				   		handler: function () {
				   			var dataName = Ext.getCmp('dataName').value;
				   			var administrationDistrictId = Ext.getCmp('administrationDistrictId2').value;
				   			var vocationWorkSys = Ext.getCmp('vocationWorkSys').value;
				   			var workVersion = Ext.getCmp('workVersion').value;
				   			var dataType = Ext.getCmp('dataType').value;
				   			var startTime = Ext.getCmp('startTime').value;
				   			var endTime = Ext.getCmp('endTime').value;
				   		//试用ajax请求服务器
		    				   Ext.Ajax.request({
		    					   url: 'saveDataGetTask.do',
		    					   actionMethods: {
		    						   create : 'POST',
		    						   read   : 'POST', // by default GET
		    						   update : 'POST',
		    						   destroy: 'POST'
		    					   },
		    					   //要修改公司的id的集合
		    					   params: {
		    						   dataName:dataName,
		    						   administrationDistrictId:administrationDistrictId,
		    						   vocationWorkSys:vocationWorkSys,
		    						   workVersion:workVersion,
		    						   dataType:dataType,
		    						   startTime:startTime,
		    						   endTime:endTime
		    					   },
		    					   success: function(response){
		    						   var text = response.responseText;
		    						   var obj=Ext.JSON.decode(text);
		    						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
		    						   Ext.getCmp('dataGet_DataGetTaskGridPanel').getStore().reload();
		    					   },
		    					   failure: function(form, action) {
		    						   Ext.create('Ushine.utils.Msg').onInfo('请求失败，请联系管理员');
		    					   }
		    				   });
				   		}
				   	})
			   ]
			})]
	        }, {
	            region: 'center',
	            width: 200,
	        	title:'数据获取任务',
	            collapsible: true,
	            split: true,
	            layout:'fit',
				items:Ext.create('Ushine.dataGet.DataGetTaskPanel')
	        },{
	            region: 'south',
	            flex:1,
	            collapsible: true,
	            split: true,
	            layout:'hbox',
	            xtype:'tabpanel',
	            style:'background-color:red',
				id:'dataGetLog',
				items:[{
					title:'default',
					border:false,
					height:'100%',
					style:'background-color:red',
					layout:'hbox',
					flex: 1,
					items:Ext.create('Ushine.dataGet.DataGetTaskLogDataPanel')
				}]
	        }]
    
        
		this.callParent();
		
	}
});