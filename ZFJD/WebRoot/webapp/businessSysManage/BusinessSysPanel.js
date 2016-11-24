/**
 * 业务系统管理管理panel
 * @author wangbailin
 */
Ext.define('Ushine.businessSysManage.BusinessSysPanel', {
	extend : 'Ext.panel.Panel',
	region: 'center',
	id : 'dataGet_BusinessSysPanel',
	region: 'center',
	bodyStyle: 'background-color: #ffffff;padding:3px;background-color:#7CDFF4;',
	layout: {
		type: 'vbox',
		align : 'stretch',
		pack  : 'start'
	},
	constructor : function() {
		var thiz = this;
		this.items=[{//上半部分
			width:'100%',
			flex: 1,
			height:'100%',
			border:false,
			split: true,
			region : 'west',
			layout:'hbox',
			items:[{//行政区域
				width:240,
				height:'100%',
				border:true,
				title:'行政区域',
				autoScroll:true,
				region : 'west',
				layout:'hbox',
				items:[{
					border:false,
					region : 'west',
					width:220,
					header:false,
					rootVisible : false,
					xtype:'treepanel',
					store: new Ext.data.TreeStore({
						proxy: {
							type: 'ajax',
							url:'findCitys.do',
						},
						autoDestroy: true,
						autoLoad:true
		    }),
		    lines: true,
			autoScroll: true,
			rootVisible: false,
			listeners: {
				select: function(view, record, item, index, e) {
					//得到当前选择的省份或者城市的名称和id
					var cityName = record.data.text;
					var cityId = 'optionNote'+record.data.id;
					var id = record.data.id;
					//得到选项卡容器
					var businessSysOptionBlock = Ext.getCmp('businessSysOptionBlock');
					//得到容器集合
					var businessSysOptionBlockList =  businessSysOptionBlock.items.items
					//判断当前选择的身份或者id是否存在选项卡中，如果存在提示用户，该省份或者城市注册系统已存在，
					for(var i = 0; i<businessSysOptionBlockList.length;i++){
						if(businessSysOptionBlockList[i].id ==  cityId){
							Ext.create('Ushine.utils.Msg').onInfo('右侧选项卡中已有该区域数据!');
							return;
						}
					}
					businessSysOptionBlock.add({
						title:cityName,
						id:cityId,
						border:false,
						height:'100%',
						layout:'hbox',
						flex: 1,
						closable:true,
						items:new Ushine.businessSysManage.BusinessSysManagePanel(id)
					});
					//设置右侧选项卡中显示最后一个选项
					businessSysOptionBlock.setActiveTab(businessSysOptionBlockList.length-1);
				},
				load:function(thiz,records){
					this.getSelectionModel().select(this.getStore().tree.root.childNodes[0]);
				}
			}
		}]
			},{//业务系统
				width:'100%',
				flex: 1,
				height:'100%',
				border:false,
				padding:4,
				split: true,
				region : 'west',
				layout:'vbox',
				items:[{
					width:'100%',
					flex: 1,
					xtype:'tabpanel',
					id:'businessSysOptionBlock',
					border:false,
					height:'100%',
					layout:'hbox',
				},{//业务系统插件管理
					width:'100%',
					flex: 1,
					height:'100%',
					border:false,
					collapsible: true,
					split: true,
					title:'系统解析插件',
					padding:'4 0 0 0',
					id:'systemVersion',
					region : 'west',
					layout:'hbox',
					items:Ext.create('Ushine.businessSysManage.BusinessSysPlugIinBoardPanel')
				}]
			}]
		}];
		this.callParent();
		
	}
});