/**
 * 管理员页面 首页
 */

$(function () {
	var ret= doAjax("./jsp/operation.jsp",{'data':'','operation':'checkLoginRank'});
	if(ret.status==0){
		window.location.href="index.html";
	}else if(ret.status==2){
		
	}else if(ret.status==1){
		window.location.href="index.html";
	}
});
function out(){
	doAjax("./jsp/operation.jsp",{'data':'','operation':'out'});
	window.location.href="index.html";
	
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