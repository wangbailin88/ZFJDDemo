/**
 * 鉴权认证管理panel
 * @author wangbailin
 */
Ext.define('Ushine.droitAttestationManage.DroitAttestationManage', {
	extend : 'Ext.panel.Panel',
	region: 'center',
	id : 'droitAttestationManage_DroitAttestationManage',
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
				border:false,
				width:600,
				height:50,
				html:'<span style="font-size:22px;font-weight:bold;color:blue;"><marquee>《鉴权认证管理》模块提示你:该模块正在研发中....</marquee></span>',
				style:'margin:0px auto;text-align:center;',
				
			}]
		}]
		this.callParent();
		
	}
});