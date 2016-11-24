/**
 * 业务系统插件管理panel
 */
 Ext.define('Ushine.businessSysManage.BusinessSysPlugIinBoardPanel', {
	extend: 'Ext.panel.Panel',
	id:'businessSysPlugIinBoardPanel',
	border:false,
	height:'100%',
	width:'100%',
	bodyStyle: 'background-color: #ffffff; border: none;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	
	
	
	region: 'center',
	constructor: function(id) {
		var self = this;
		this.tbar = [{
            iconCls: 'icon-user-add',
            style:'margin-left:30px;',
            text: '上传插件',
            scope: this,
            handler:function(){
				if(id!=null && id!=''){
					self.uploadSystemJar(id);
				}else{
					Ext.create('Ushine.utils.Msg').onInfo("请双击上方业务系统数据");
				}
			}
        },{
            iconCls: 'icon-user-add',
            text: '下载插件',
            style:'margin-left:30px;',
            scope: this,
            handler:function(){
				Ext.create('Ushine.utils.Msg').onInfo("暂不支持!");
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
		name:'colnum',
		emptyText: '开始日期',
		valueField: 'value',
		width: 240,
		margin:'0 0 0 20',
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
		this.items = [{
			height:'100%',
			border:false,
			style:'1px solid red;',
			flex:1,
			region : 'west',
			layout:'hbox',
			items:new Ushine.businessSysManage.BusinessSysPlugIinBoardGridPanel(id)
		}
		//线索gridpanel
		//Ext.create('Ushine.dataGet.DataGetTaskLogDataGridPanel')
		];
		this.callParent();	
	},
	//搜索
	dataSearch:function(){
		this.remove('c_dataSearch_grid');
		this.add(Ext.create('Ushine.dataSearch.DataSearchPenelGridPanel'));
	},
	//上传系统插件
	uploadSystemJar:function(id){
		var sjnum = parseInt(Math.random()*1000);//产生一个三位数的随机数
		var win = Ext.create('Ushine.win.Window', {
			title : "上传系统插件", // 标题
			width : 300, // 宽度
			height: 260,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
			items:[{
				xtype:'form',
				id:'jarForm',
				border:false,
				height:40,
				baseCls: 'form-body1',
					items:[{
						fieldLabel: '解读插件',
						labelAlign : 'right',
						labelWidth: 60,
						xtype:'filefield',
						buttonText:'选择插件',
						allowNegative: false,
						allowBlank: false,
						editable: false,
						id:'jarId',
						name:'jar',
						emptyText: '请选择解读插件',
						//valueField: 'value',
						width: 260,
						listeners:{
							afterrender:function(cmp){
						cmp.fileInputEl.set({
							accept:'file/*'
						});
					}
					,'change':function(){
					var arr = this.value.split(".");
					var str = arr[arr.length-1];
					var types =["jar"];
					var flag = false;
					Ext.Array.each(types,function(type){
						if(type==str){
							flag = true;
							return false;
						}
					});
					if(!flag){
						Ext.create('Ushine.utils.Msg').onInfo("文件格式不正确!");
					}else{
						Ext.getCmp('jarForm').getForm().submit({
							url:'addTempVocationalWorkSystemJar.do?number='+sjnum,
							mothed:'POST',
							waitMsg:'文件上传中',
							success : function(form, action) {
								Ext.getCmp('jar2').setValue(action.result.msg); 
								Ext.getCmp('jarId').hide(); 
								//Ext.getCmp('jar2').show();
							},
							// 提交失败的回调函数
							failure : function() {
								Ext.create('Ushine.utils.Msg').onInfo("服务器出现错误请稍后再试!");
							}
						});
					}
				}
					}
			}]
			},{
				fieldLabel: '选择插件',
				labelAlign : 'right',
				labelWidth: 60,
				xtype:'textfield',
				allowNegative: false,
				allowBlank: false,
				editable: false,
				hidden:true,
				name:'version',
				id:'jar2',
				margin:'14 0 0 0',
				emptyText: '请选择插件',
				valueField: 'value',
				width: 260
			},{
				fieldLabel: '插件版本',
				labelAlign : 'right',
				labelWidth: 60,
				xtype:'textfield',
				allowNegative: false,
				allowBlank: false,
				editable: false,
				name:'version',
				id:'version2',
				margin:'14 0 0 0',
				emptyText: '插件版本',
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
				id:'remarks2',
				name:'remarks',
				emptyText: '请输入备注信息',
				valueField: 'value',
				width: 260,
			},Ext.create('Ushine.buttons.Button', {
				text : '提交',
				style:"margin:14 0 0 80",
				width:100,
				labelWidth: 60,
				height:22,
				baseCls: 't-btn-yellow',
				handler:function(){
					var remarks = Ext.getCmp('remarks2').value;
					var version = Ext.getCmp('version2').value;
					var jar = Ext.getCmp('jar2').value;
					//试用ajax请求服务器
 				   Ext.Ajax.request({
 					   url: 'saveVocationalWorkSystemVersion.do',
 					   actionMethods: {
 						   create : 'POST',
 						   read   : 'POST', // by default GET
 						   update : 'POST',
 						   destroy: 'POST'
 					   },
 					   //要修改公司的id的集合
 					   params: {
 						   version:version,
 						   remarks:remarks,
 						   jar:jar,
 						   systemId:id
 					   },
 					   success: function(response){
 						   var text = response.responseText;
 						   var obj=Ext.JSON.decode(text);
 						   Ext.create('Ushine.utils.Msg').onInfo(obj.msg);
 						   win.close();
 						   //启用成功后刷新数据
 						   Ext.getCmp('businessSysPlugIinBoardGridPanel').getStore().reload();
 					   },
 					   failure: function(form, action) {
 						   Ext.create('Ushine.utils.Msg').onInfo('请求失败，请联系管理员');
 					   }
 				   });
			}
		})]
        });
		win.show();
	}
});
