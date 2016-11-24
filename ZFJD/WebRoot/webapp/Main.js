// 填充图片的本地引用  ExtJs 3.4
// Ext.BLANK_IMAGE_URL = 'extjs/resources/images/default/s.gif';

// 配置类路径，动态加载JS文件
// 或者用setPath设置匹配路径，Ext.Loader.setPath('App.ux', 'lib'); 
Ext.Loader.setConfig({enabled: true,
	paths: {//'类名前缀':'所在路径'
		'Ushine': 'webapp'
	}
});

//通过匹配会自动加载JS文件'webapp/Menu.js'
Ext.require([
 	'Ushine.base.TopFrame',
	'Ushine.base.CenterFrame',
	'Ushine.base.Menu',
	'Ushine.base.TitleBar',
	'Ushine.buttons.Button',
	'Ushine.buttons.IconButton',
	'Ushine.buttons.OperButton',
	'Ushine.buttons.MiniButton',
	'Ushine.win.Window',
	'Ushine.system.Operations',
	'Ushine.system.OrganizManage',
	'Ushine.system.RoleManage',
	'Ushine.system.log.Log',
	'Ushine.system.SystemStatus',
	'Ushine.system.log.LogGridPanel',
	'Ushine.dataSearch.DataSearchPenel',
	'Ushine.dataSearch.DataSearchPenelGridPanel',
	'Ushine.map.Map',
	'Ushine.utils.Map',
	'Ushine.businessSysManage.BusinessSysManagePanel',
	'Ushine.businessSysManage.BusinessSysManageGridPanel',
	'Ushine.businessSysManage.BusinessSysPlugIinBoardGridPanel',
	'Ushine.businessSysManage.BusinessSysPlugIinBoardPanel',
	'Ushine.dataGet.DataGetTaskLogDataGridPanel',
	'Ushine.dataGet.DataGetTaskLogDataGridPanel',
	'Ushine.map.MapUtil'
]);

//创建命名空间
Ext.namespace('Ushine');



//创建应用程序
Ushine.Main = function() {
	//监听所有的ajax请求
	Ext.Ajax.on('requestcomplete', function(conn,response,options){
		 var result = Ext.JSON.decode(response.responseText);
		 if(result.status==-999){
			 notLogin(result);
		 }
	} , this);
	Ext.getDoc().on("contextmenu", function(e){
        e.stopEvent();
    });
	return {
		// 初始化应用程序, 创建viewport, 并采用border布局
		init: function() {
			Ext.QuickTips.init();
			Ext.viewport = new Ext.container.Viewport({
				layout: 'border', // 边界布局
				baseCls: 'blue-body',
				//minHeight:955,
				//minWidth:1920,
				autoScroll:false,
				items: [
					new Ushine.base.TopFrame(), 
					Ext.create('Ushine.base.CenterFrame')
				]
//				
			});
			
		}
		
	}; //*** RETURN END ***/
}();

/**
 * Session验证
 */


/**
 * 没有登录跳转到登录页面
 */
function notLogin(obj) {
	Ext.MessageBox.show({
		title: '提示信息',
		msg: obj.msg,
		buttons: Ext.MessageBox.OK,
		icon: Ext.MessageBox.ERROR,
		fn: function() {
			location.href="./login.jsp";
		}
	});
}