/**
 * 登录
 */
$(function () {
	getVCode();
});
/**
 * 登陆
 * @returns
 */
function login(){
	var inputId= $("#inputId").val();
	if(inputId==""){
		$("#msg").html(alertdanger('账号不能为空，请填写昵称或者手机号码或者邮箱号码'));
		$("#inputId").focus(); 
		return false;
	}
	var inputPassword= $("#inputPassword").val();
	if(inputPassword==""){
		$("#msg").html(alertdanger('请输入密码'));
		$("#inputPassword").focus(); 
		return false;
	}
	var User_Password=hex_md5(inputPassword);
	var position = $('#User_Position input[name="User_Position"]:checked').val();
	if(!dojudge()){
		return false;
	}
	var ret='';
	var data='';
	if(position==1){//用户
		if(/0?(13|14|15|18|17)[0-9]{9}/.test(inputId)==true){//手机
			data={"isAdmin":false,"User_Password":User_Password,"User_Cell":inputId,"User_Name":"","User_Email":""};
			
		}else if(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(inputId)==true){//邮箱
			data={"isAdmin":false,"User_Password":User_Password,"User_Cell":"","User_Name":"","User_Email":inputId};
			
		}else{//昵称
			data={"isAdmin":false,"User_Password":User_Password,"User_Cell":"","User_Name":inputId,"User_Email":""};
			
		}
		
	}else if(position==2){//管理员
		if(/0?(13|14|15|18|17)[0-9]{9}/.test(inputId)==true){//手机
			data={"isAdmin":true,"Admin_PK":"","Admin_Password":User_Password,"Admin_Cell":inputId,"Admin_Name":"","Admin_Email":""};
			 
		}else if(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(inputId)==true){//邮箱
			data={"isAdmin":true,"Admin_PK":"","Admin_Password":User_Password,"Admin_Cell":"","Admin_Name":"","Admin_Email":inputId};
			
		}else{//昵称
			data={"isAdmin":true,"Admin_PK":"","Admin_Password":User_Password,"Admin_Cell":"","Admin_Name":inputId,"Admin_Email":""};
			
		}
	}
	ret=doAjax("./jsp/operation.jsp",{"data":JSON.stringify(data),'operation':'login'});
	if(ret.isCorrect){
		if(data.isAdmin){
			window.location.href="editinfo.html";
		}
		else{
			window.location.href="index.html";
		}
	}
	else{
		$("#msg").html(alertdanger('登录账号或密码错误'));
		return false;
	}
	
	
}

var alertdanger=function(msg){
	var ret='<div class="alert alert-dismissable alert-danger">'+
	 '<button class="close" aria-hidden="true" type="button" data-dismiss="alert">×</button>'+
	 '<h4>注意!</h4> <strong>Warning!</strong>'+msg+
	 '</div>';
	return ret;
}
/**
 * 验证码判断
 */
var  dojudge=function(){
	var yzm=$("#yzm").val();
	var ret=doAjax("./jsp/operation.jsp",{'operation':'validateVCode','code':yzm});
	if(ret['status']=='1'){
		return true;
	}
	else{
		$("#msg").html(alertdanger('验证码输入错误'));
		$("#yzm").focus(); 
		return false;
	}
}

/**
 * 获得验证码
 * @returns
 */
function getVCode(){
	$("#img0").attr('src',"./jsp/operation.jsp?operation=getVCode&&="+Math.random());
}

function doAjax(url,json){//封装ajax
	var r;
	$.ajax({url:url,
		data:json,
		async:false,
		success:function(data){
			r = jQuery.parseJSON(data);
		}
	});
	
	return r;
}