
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	Date date = new Date();
	String errormessage = (String) request.getAttribute("error");
	if(errormessage==null){
		errormessage = "";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>用户登录</title>
<script type="text/javascript" src="extjs/bootstrap.js"></script>
<script type="text/javascript" src="extjs/locale/ext-lang-zh_CN.js"></script>
<style type="text/css">

   .logo{
      position: relative;
      left: 175px;
      top: 170px;
      background: url(images/image2/logo.png);
      width:311px;
      height:353px;
   }
   .font{
      position: relative;
      left: 166px;
      top: 170px;
     /**** background: url(images/image2/1.png) no-repeat;****/
      width:375px;
      height:40px;
   }
   .form_bj{
       position: relative;
      left: 40px;
      top: 180px;
      background: url(images/image2/form_bj.png) ;
      width:623px;
      height:70px;
   }
   .ca_form_bj{
   	  display : none;
      position: relative;
      left: 40px;
      top: 180px;
      background: url(images/image2/form_bj.png) ;
      width:623px;
      height:70px;
   }
   #username{
     position: relative;
     left:40px;
     top:16px;
     width:180px;
     height:27px;
   }
   #password{
     position: relative;
     left:46px;
     top:16px;
     width:180px;
     height:27px;
   }
   #causername{
     position: relative;
     left:40px;
     top:16px;
     width:180px;
     height:27px;
   }
   #capassword{
     position: relative;
     left:46px;
     top:16px;
     width:180px;
     height:27px;
   }
   #btn{
     position: relative;
     left:56px;
     top:16px;
     width:100px;
     height:30px;
   }
   #cabtn{
     position: relative;
     left:56px;
     top:16px;
     width:100px;
     height:30px;
   }
   .errormessage{
     position: relative;
      left: 50px;
      top: 180px;
      width:600px;
      height:20px;
      color:#A61E1C;
      font-size:15px;
      text-align: center;
   }
body{
   background-repeat: no-repeat;
   margin:0 auto;
   text-align: center;
   
}
.main{
   margin:auto;
   width:700px;
   height:640px;
}
</style>
  </head>
  
  <body background="images/image2/bj.jpg">
       <div class = "main">
       
       <div class = "logo">
       
       </div>
       <div class = "font">
       
       </div>
       <input type="hidden" id="RootCADN" value="" width="30" />
	   <input type="hidden" id="signed_data" name="signed_data" /> 
	   <input type="hidden" id="original_jsp" name="original_jsp" />
       <div class = "form_bj" id = "form_bj">
              <form name="loginForm" method="post" action="login.do">
	         <table width="500" border="0">
	        <tr>
	          <td><input id="username" name="un" type="text" /></td>
	          <td><input id="password" name="pa" type="password" /></td>
	          <td><input id="btn" type="image" src="images/image2/btn.png" onclick="datacheck()"/></td>	        
	        </tr>
	      </table>
	      </form>
       </div>
       <div class = "ca_form_bj" id = "ca_form_bj">
         	<table width="500" border="0">
       	 		<tr>
       	 			<td><input id="causer" name="ca" type="hidden" /></td>
          			<td><input id="causername" name="caun" type="text" disabled="true" /></td>
          			<td><input id="capassword" name="capa" type="password" disabled="true" /></td>
          			<td><input id="cabtn" type="image" src="images/image2/btn.png" onclick="CALogin()"/></td>
        		</tr>
      		</table>
       </div>
       <div class ="errormessage" id = "errormessage">
       <%=errormessage %>
       </div>
       </div>
       <div style='position: absolute;bottom: 10px;right: 10px;z-index:9;'>
  		 <a href="tool/chrome_25.0.1364.152.exe" style="font-family: '宋体';color: #EDF0F2;font-size: 15px;">系统指定浏览器:chrome_25.0.1364.152.exe</a>
		</div>
  </body>
</html>
<script language="javascript">
/*      document.onkeypress	= function(e){
	e = window.event||e; 
	var code = e.keyCode || e.which;
	if(code==13){
		datacheck();
	}
	if(code==27){
		document.getElementById('username').value='';
		document.getElementById('password').value='';
	}

} ;  */
function checkNoEmpty(documentId,alertInfo){
	var doc = document.getElementById(documentId);
	if(doc.value==''||doc.value==null){
		alert('['+alertInfo+']不能为空');
		doc.focus();
		return false;
	}
	return true;
}
function chongzhi(){
	
	document.getElementById('username').value='';
	document.getElementById('password').value='';
}
function datacheck(){
	var browser=navigator.appName
	var b_version=navigator.appVersion
	var version=parseFloat(b_version)
	if (browser!="Netscape" || version<5) {
		alert("请使用系统指定浏览器");
		return 0;
	}
	var un=document.getElementById('username').value;
	var pa=document.getElementById('password').value;

		 document.loginForm.action="login.do";
		 document.loginForm.submit();
	}
</script>
