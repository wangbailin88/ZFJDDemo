/**
 * 系统基础设置管理panel
 * @author wangbailin
 */
Ext.define('Ushine.systenBaseSetUp.SystenBaseSetUp', {
	extend : 'Ext.panel.Panel',
	region: 'center',
	id : 'systenBaseSetUp_SystenBaseSetUp',
	region: 'center',
	bodyStyle: 'background-color: #E8E8E8;',
	layout: 'hbox',
	constructor : function() {
		var thiz = this;
		this.items=[{
			flex:1,
			width:'100%',
			height:'100%',
			border:false,
			layout:{
				type:'hbox',
				align:'middle',
				pack:'center'
			},
			items:[{
				border:true,
				width:300,
				height:350,
				//html:'<span style="font-size:22px;font-weight:bold;color:blue;"><marquee>《系统基础设置》模块提示你:该模块正在研发中....</marquee></span>',
				style:'margin:0px auto;text-align:center;',
				items:new Ext.FormPanel({
					layout: {
		                type: 'vbox',
		                border:false
		            },
		        bodyPadding: 8,
		        margin:0,
		        border: false,
		        region: 'center',
				buttonAlign:"center",
				items : [{
					fieldLabel:'节点名称',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入节点名称',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					height:22,
					margin:'10 0 0 0',
					id:'nodeName',
					value:''
				},{
					fieldLabel:'节点编码',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入节点编码',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					height:22,
					margin:'10 0 0 0',
					id:'nodeCode',
					value:''
				},{
					fieldLabel:'IP地址',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入IP地址',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					height:22,
					margin:'10 0 0 0',
					id:'nodeIp',
					value:''
				},{
					fieldLabel:'上级IP',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入上级IP',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					labelStyle:'color:red;',
					height:22,
					margin:'10 0 0 0',
					id:'baseNodeIp',
					value:''
				},{
					fieldLabel:'上级名称',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入上级名称',
					blankText:'此选项不能为空',
					width: 260,
					labelStyle:'color:red;',
					labelAlign : 'right',
					labelWidth : 75,
					margin:'10 0 0 0',
					height:22,
					id:'baseNodeName',
					value:''
				},{
					fieldLabel:'上级编码',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入上级编码',
					blankText:'此选项不能为空',
					width: 260,
					labelStyle:'color:red;',
					labelAlign : 'right',
					labelWidth : 75,
					margin:'10 0 0 0',
					height:22,
					id:'baseNodeCode',
					value:''
				},{
					fieldLabel:'设备名称',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入设备名称',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					margin:'10 0 0 0',
					height:22,
					id:'blackBoxName',
					value:''
				},{
					fieldLabel:'设备编码',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入设备编码',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					height:22,
					margin:'10 0 0 0',
					id:'blackBoxCode',
					value:''
				},{
					fieldLabel:'设备IP',
					allowBlank:false,
					xtype : 'textfield',
					emptyText:'请输入设备IP',
					blankText:'此选项不能为空',
					width: 260,
					labelAlign : 'right',
					labelWidth : 75,
					margin:'10 0 0 0',
					height:22,
					id:'blackBoxIp',
					value:''
				}],
					buttons : [
				   		Ext.create('Ushine.buttons.Button', {
					   		text: '修改',
					   		baseCls: 't-btn-red',
					   		handler: function () {
//					   		    private String id;
//				            	private String nodeCode;//节点编码
//				            	private String nodeName;//节点名称
//				            	private String nodeIp;//节点ip
//				            	private String baseNodeIp;//上级节点ip，如果是部中心可为空
//				            	private String baseNodeName;//上级节点名称，如果是部中心可为空
//				            	private String registerTime;//注册时间
//				            	//黑盒记录信息
//				            	private String blackBoxName;//黑盒名称
//				            	private String blackBoxCode;//黑盒编号
//				            	private String blackBoxIp;//黑盒ip
					   			var nodeCode = Ext.getCmp('nodeCode').value;
					   			var nodeName = Ext.getCmp('nodeName').value;
					   			var nodeIp = Ext.getCmp('nodeIp').value;
					   			var baseNodeIp = Ext.getCmp('baseNodeIp').value;
					   			var baseNodeName = Ext.getCmp('baseNodeName').value;
					   			var blackBoxCode = Ext.getCmp('blackBoxCode').value;
					   			var blackBoxName = Ext.getCmp('blackBoxName').value;
					   			var blackBoxIp = Ext.getCmp('blackBoxIp').value;
					   			var baseNodeCode = Ext.getCmp('baseNodeCode').value;
					   			//判断必填选项是否为空
					   			if(nodeCode==''){
					   			 Ext.create('Ushine.utils.Msg').onInfo('节点编码不能为空!');
					   			 return ;
					   			}
					   			if(nodeName==''){
						   			 Ext.create('Ushine.utils.Msg').onInfo('节点名称不能为空!');
						   			 return ;
						   		}
					   			
					   			if(nodeIp==''){
						   			 Ext.create('Ushine.utils.Msg').onInfo('节点IP不能为空!');
						   			 return ;
						   		}
					   			if(blackBoxCode==''){
						   			 Ext.create('Ushine.utils.Msg').onInfo('设备编码不能为空!');
						   			 return ;
						   		}
					   			if(blackBoxName==''){
						   			 Ext.create('Ushine.utils.Msg').onInfo('设备名称不能为空!');
						   			 return ;
						   		}
					   			if(blackBoxIp==''){
						   			 Ext.create('Ushine.utils.Msg').onInfo('设备IP不能为空!');
						   			 return ;
						   		}
					   			
					   		//试用ajax请求服务器
			    				   Ext.Ajax.request({
			    					   url: 'saveThisNodeInfo.do',
			    					   actionMethods: {
			    						   create : 'POST',
			    						   read   : 'POST', // by default GET
			    						   update : 'POST',
			    						   destroy: 'POST'
			    					   },
			    					   //要修改公司的id的集合
			    					   params: {
			    						   nodeCode:nodeCode,
			    						   nodeName:nodeName,
			    						   nodeIp:nodeIp,
			    						   baseNodeIp:baseNodeIp,
			    						   baseNodeName:baseNodeName,
			    						   blackBoxCode:blackBoxCode,
			    						   blackBoxName:blackBoxName,
			    						   blackBoxIp:blackBoxIp,
			    						   baseNodeCode:baseNodeCode
			    					   },
			    					   success: function(response){
			    						   var text = response.responseText;
			    						   var obj=Ext.JSON.decode(text);
			    						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
			    					   },
			    					   failure: function(form, action) {
			    						   Ext.create('Ushine.utils.Msg').onInfo('请求服务器失败,请联系管理员!');
			    					   }
			    				   });
					   		}
					   	}),
					   Ext.create('Ushine.buttons.Button', {
					   		text: '重置',
					   		baseCls: 't-btn-yellow',
					   		handler: function () {
					   			_form.getForm().reset();
					   		}
					   	})
				   ]
			})
			}]
		}]
		this.callParent();
		
	},listeners:{
		afterrender:function(){
			Ext.Ajax.request({
				   url: 'findThisNodeInfo.do',
				   actionMethods: {
					   create : 'POST',
					   read   : 'POST', // by default GET
					   update : 'POST',
					   destroy: 'POST'
				   },
				   success: function(response){
					   var text = response.responseText;
					   var thisNodeInfo=Ext.JSON.decode(text);
					   Ext.getCmp('nodeName').setValue(thisNodeInfo.nodeName);
					   Ext.getCmp('nodeIp').setValue(thisNodeInfo.nodeIp);
					   Ext.getCmp('nodeCode').setValue(thisNodeInfo.nodeCode);
					   Ext.getCmp('blackBoxName').setValue(thisNodeInfo.blackBoxName);
					   Ext.getCmp('baseNodeName').setValue(thisNodeInfo.baseNodeName);
					   Ext.getCmp('baseNodeIp').setValue(thisNodeInfo.baseNodeIp);
					   Ext.getCmp('blackBoxIp').setValue(thisNodeInfo.blackBoxIp);
					   Ext.getCmp('blackBoxCode').setValue(thisNodeInfo.blackBoxCode);
					   Ext.getCmp('baseNodeCode').setValue(thisNodeInfo.baseNodeCode);
				   },
				   failure: function(form, action) {
					   Ext.create('Ushine.utils.Msg').onInfo('请求服务器失败，请联系管理员');
				   }
			   });
			
		}
	}
});