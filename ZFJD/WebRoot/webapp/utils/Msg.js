// JavaScript Document

Ext.define('Ushine.utils.Msg', {
	/**
	 * 询问提示窗
	 * @熊永涛增加
	 */
	onQuest:function(msgStr, fnStr) {
		Ext.MessageBox.show({
			title: '疑问信息',
			//iconCls: Ext.MessageBox.INFO,
			msg: msgStr ,
			fn: fnStr,
			buttons: Ext.MessageBox.YESNO,
			icon: Ext.MessageBox.QUESTION,
		});
	},
	/*
	 * 错误信息（消息, 返回方法）
	 */
	onError: function (txt, fn) {
        Ext.MessageBox.show({
           title: '错误信息提示',
           msg: txt,
           buttons: Ext.MessageBox.OK,
           fn: fn,
           icon: Ext.MessageBox.ERROR,
       });
    },
	
	/*
	 * 警告信息（消息, 返回方法）
	 */
	onWarn: function (txt, fn) {
        Ext.MessageBox.show({
           title: '警告信息提示',
           msg: txt,
           buttons: Ext.MessageBox.OK,
           fn: fn,
           icon: Ext.MessageBox.WARNING,
       });
    },
	
	/*
	 * 提醒信息（消息, 返回方法）
	 */
	onInfo: function (txt, fn) {
        Ext.MessageBox.show({
           title: '提示信息',
           msg: txt,
           buttons: Ext.MessageBox.OK,
           fn: fn,
           icon: Ext.MessageBox.INFO,
       });
    },
	
	/**
	 * 等待提示框（信息, 等待时间：秒）
	 */
	onWait: function(txt, s) {
		return Ext.MessageBox.show({
           msg: txt,
           progressText: '操作正在处理中...',
           width:300,
           wait:true,
           waitConfig: {interval:200},
           icon: 'ext-mb-download', //custom class in msg-box.html
           iconHeight: 50,
       });
        
	}
	
	/*onWait: function(txt, s) {
		Ext.MessageBox.show({
           title: '请等待操作处理中',
           msg: txt,
           progressText: '操作处理中...',
           width:300,
           progress:true,
           closable:false,
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == 12){
                    Ext.MessageBox.hide();
                    Ext.example.msg('Done', 'Your fake items were loaded!');
                }else{
                    var i = v/11;
                    Ext.MessageBox.updateProgress(i, Math.round(100 * i)+'% 完成进度');
                }
           };
       };
	   
       for(var i = 1; i < 13; i++){
           setTimeout(f(i), i*500);
       }
	}*/
	
});