/**
 * 头部信息
 * @author Franklin
 */
Ext.define('Ushine.base.TopFrame', {
	extend: 'Ext.panel.Panel',
	id: 'top_frame',
	region: 'north',
	height: 66,
	border: false,
	layout: {
		type: 'hbox',
		pack: 'start',
		align: 'stretch'
	},
	constructor: function(obj) {
		var me=this;
		this.items = [{
			// 左侧区域
			xtype: 'panel',
			flex: 1,
			border: true,
			bodyCls: 'top-body',
			bodyStyle: 'padding-left: 10px;background: url(img/header.png) no-repeat;',
			
		},{
			// 右边区域
			xtype: 'panel',
			border: true,
			bodyCls: 'top-body',
			bodyStyle: 'text-align: center; padding-right: 20px;background: url(img/headRight.png) no-repeat;',
			items: [
			        // 个人操作
//				Ext.create('Ushine.buttons.OperButton', {
//					id: 'infoTypeManage',
//					tooltip:'基础库类型管理',
//					handler:function(){
//						me.abc();
//					}
//				}),
				Ext.create('Ushine.base.Menu')]
		}];
		
		this.callParent(); 
	},
	
	setting:function(){
		var win = Ext.create('Ushine.win.Window', {
			title : "个人设置", // 标题
			width : 500, // 宽度
			height: 300,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
			items:{
            	xtype:'tabpanel',
            	items:[{
            		title:'修改密码',
            		items:Ext.create('Ushine.base.PasPanel')
    			}]
		 
			}
        });
		win.show();
    },
    infTypeManage:function(){
		var win = Ext.create('Ushine.win.Window', {
			title : "基础库类型管理", // 标题
			width : 700, // 宽度
			height: 600,
			autoHeight : true, // 宽度
			collapsible : true, // 是否可以伸缩
			plain : true, // 强制背景色
			layout:'fit',
		//	iconCls : "houfei-addicon", // 图标样式
			modal : true, // 当前窗体为模态窗体
			bodyStyle: 'background-color: #ffffff; border: none;padding:10px;',
			items:new Ushine.infoTypeManage.InfoTypeManage
		});
		win.show();
    },
    PDFStyleSetUp:function(url){
    	Ext.getCmp('content_frame').removeAll();
		Ext.getCmp('content_frame').add(new Ushine.pDFStyleSetUp.PDFStyleSetUp("",url));
	
    }
});

Ext.define('Ushine.base.PasPanel', {
	extend: 'Ext.panel.Panel',
	//bodyStyle: 'background-color: #f5f5f5;',
	border:false,
	constructor: function(){
		var self=this;
		this.items=new Ext.FormPanel({
			bodyStyle: 'padding: 10px',
			border:false,
			buttonAlign:"center",
			//defaults: {labelWidth: 60,width: 280,height:22},
			items:{ 
				title:'基本信息',
			    margins:'5 5 0 5',
			    xtype:'fieldset',
			    defaults: {width: 300},
				items:[{
	            	name: 'oldPass',
	        		xtype: 'textfield',
	        		fieldLabel: '旧密码',
	        		allowNegative: false,
	        		inputType:"password",
	        		allowBlank: false
	        	},{
	        		name: 'pass',
	        		xtype: 'textfield',
	        		fieldLabel: '新设密码',
	        		inputType:"password",
	        		allowBlank: false,
	        	},{
	        		name: 'pass1',
	        		xtype: 'textfield',
	        		fieldLabel: '确认密码',
	        		inputType:"password",
	        		allowBlank: false,
	        	}]
			},
			buttons:[{
				text: '保存信息',
				xtype:'u_btn',
				//margins:'0 0 0 80',
				baseCls: 't-btn-red',
		        handler: function(){
		        	//self.ownerCt.getComponent(0).getComponent(0).getForm()
		        	//console.log(self.getComponent(0).getForm());
		        	var form = self.getComponent(0).getForm();
		        	var btn=this;
		        	if (form.isValid()) {
		        		btn.disable();
		                form.submit({
		                	url:'setPassword.do',
		                    success: function(form, action) {
		                    		self.ownerCt.ownerCt.ownerCt.close();
		                    		Ext.create('Ushine.utils.Msg').onInfo(action.result.msg);
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
				xtype:'u_btn',
				text: '重置',
				baseCls: 't-btn-yellow',
		        handler: function(){
		        	self.getComponent(0).getForm().reset();
		        }
			}]
		});
		this.callParent();
	}
});
function getZoomScale(features, width, height){
	var longitudeMin = 100000;//最小经度
	var latitudeMin = 100000;//最小维度
	var longitudeMax = 0;//最大经度
	var latitudeMax = 0;//最大纬度
	features.forEach(function(e){  
	    var a = d3.geo.bounds(e);//[[最小经度，最小维度][最大经度，最大纬度]]
	    if(a[0][0] < longitudeMin) {
	    	longitudeMin = a[0][0];
	    }
	    if(a[0][1] < latitudeMin) {
	    	latitudeMin = a[0][1];
	    }
	    if(a[1][0] > longitudeMax) {
	    	longitudeMax = a[1][0];
	    }
	    if(a[1][1] > latitudeMax) {
	    	latitudeMax = a[1][1];
	    }
	});

	var a = longitudeMax-longitudeMin;
	var b = latitudeMax-latitudeMin;
	/*if(a > b) {//按照宽度进行缩放
		return width/a;
	} else {
		return height/b;
	}*/

	return Math.min(width/a, height/b);
}

function getCenters(features){
	var longitudeMin = 100000;//最小经度
	var latitudeMin = 100000;//最小维度
	var longitudeMax = 0;//最大经度
	var latitudeMax = 0;//最大纬度
	features.forEach(function(e){  
	    var a = d3.geo.bounds(e);//[[最小经度，最小维度][最大经度，最大纬度]]
	    if(a[0][0] < longitudeMin) {
	    	longitudeMin = a[0][0];
	    }
	    if(a[0][1] < latitudeMin) {
	    	latitudeMin = a[0][1];
	    }
	    if(a[1][0] > longitudeMax) {
	    	longitudeMax = a[1][0];
	    }
	    if(a[1][1] > latitudeMax) {
	    	latitudeMax = a[1][1];
	    }
	});

	var a = (longitudeMax + longitudeMin)/2;
	var b = (latitudeMax + latitudeMin)/2;

	return [a, b];
}
