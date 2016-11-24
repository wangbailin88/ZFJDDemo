/**
 * @author xyt
 * 窗体工具类
 */
Ext.define('Ushine.utils.StringUtils', {
	setChecked:function(type,obj){
		if(type.length==2){
			obj.getComponent(0).setValue(true);
			obj.getComponent(1).setValue(true);
		}else if(type[0]==0){
			obj.getComponent(0).setValue(true);
			obj.getComponent(1).setValue(false);
		}else if(type[0]==1){
			obj.getComponent(0).setValue(false);
			obj.getComponent(1).setValue(true);
		}else{
			obj.getComponent(0).setValue(false);
			obj.getComponent(1).setValue(false);
		}
	},
	getType:function(type){
		if(type.length==2){
			return "收/发";
		}else if(type[0]==1){
			return "收";
		}else if(type[0]==2){
			return "发";
		}
		return "";
	},
	getLogString:function(str){
		console.log(str.length);
		if(str.length>10){
			return str.substring(0,10)+"...";
		}
		return str;
	},
	existOrg:function(citys){
		var orgAdd=Ext.util.Cookies.get('orgAdd');
		if(orgAdd=="全国"){
			return true;
		}else{
			var array=citys.split(",");
			for(var i=0;i<array.length;i++){
				if(this.existOrg1(array[i])==false){
					return false;
				}
			}
			return true;
		}
	},
	existOrg1:function(city){
		var orgAdd=Ext.util.Cookies.get('orgAdd').split(",");
		for(var i=0;i<orgAdd.length;i++){
			if(city==orgAdd[i]){
				return true;
			}
		}
		return false;
	},
	convertParam:function(param){
		params=params.replaceAll(">", "");
		params=params.replaceAll("<", "");
		params=params.replaceAll("=", "");
		params=params.replaceAll("(", "");
		params=params.replaceAll(")", "");
		params=params.replaceAll(".", "");
		params=params.replaceAll("sName", "");
		params=params.replaceAll("rName", "");
		params=params.replaceAll("rCity", "");
		params=params.replaceAll("sCity", "");
		params=params.replaceAll("rMobile", "");
		params=params.replaceAll("sMobile", "");
		params=params.replaceAll("rAdd", "");
		params=params.replaceAll("sAdd", "");
		params=params.replaceAll("gName", "");
		params=params.replaceAll("gWt", "");
		params=params.replaceAll("insureValue", "");
		//params=params.replaceAll("~~TO_CHAR(DTBIZDATE~YYYY-MM-DD)", "");
//		//替换运算符
		params=params.replace("same", "");
		params=params.replaceAll("List", "");
		params=params.replaceAll(":", "");
		params=params.replaceAll("length", "");
	},
	getStatus:function(value){
  	   if(value==0){
 		   return '等待中';
 	   }else if(value==2||value==1){
 		   return '执行中';
 	   }else if(value==3){
 		   return '完成';
 	   }else if(value==4){
 		   return '异常关闭';
 	   }else if(value==5){
 		   return '未审批';
 	   }else if(value==6){
 		   return '已审批';
 	   }else if(value==7){
 		   return '审批未通过';
 	   }else if(value==8){
 		   return '未提交审批';
 	   }else{
 		   return '<span style="coler:red">异常</span>';
 	   }
	}
})