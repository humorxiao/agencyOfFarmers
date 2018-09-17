/**
 * 管理员页面之用户与动态管理
 */

$(function () {
	
});
//一些全局缓存变量
var users;
//用户黑名单管理
/**
 * 获取搜索到的用户信息
 * @returns
 */
function getUserInfo(){
	var searchstr=$('#user_search').val();
	var ret= doAjax("./jsp/operation.jsp",{'likes':searchstr,'operation':'getLikesUser'});
	var str="";
	if(ret==undefined||ret==''){
		//str+='<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
	}
	else{
		users=ret;
		for(var i=0;i<ret.length;i++){
			var sex=ret[i]['User_Sex'];
			var sexStr="";
			var isBanned=false;
			switch(sex){
			case 1:sexStr="男";break;
			case 2:sexStr="女";break;
			case 3:sexStr="保密";break;
			default:;
			}
			if(ret[i]['User_Mark']==1){
				isBanned=true;
			}
			str+='<tr id="user'+i+'"><td>'+ret[i]['User_Name']+'</td><td>'+ret[i]['User_Realname']+
				'</td><td>'+sexStr+
				'</td><td>'+ret[i]['User_Cell']+'</td><td>'+ret[i]['User_Email']+'</td><td>'+
				ret[i]['User_ID']+'</td><td>'+ret[i]['User_RegTime']+'</td><td>';
			if(isBanned){
				str+='<a class="btn" onclick=unBannedUser("'+i+'")>解禁</a>';
			}else{
				str+='<a class="btn" onclick=bannedUser("'+i+'")>禁止</a>';
			}
				
				+'</td></tr>';	
		}
	}
	$('#Users').html(str);
}
/**
 * 解禁用户
 * @param index
 * @returns
 */
function unBannedUser(index){
	var obj=users[index];
	obj['User_Mark']=0;
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(obj),'operation':'updateUserInfo'});
	if(ret.status==1){
		getUserInfo();
	}
}
/**
 * 禁止用户
 * @param index
 * @returns
 */
function bannedUser(index){
	var obj=users[index];
	obj['User_Mark']=1;
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(obj),'operation':'updateUserInfo'});
	if(ret.status==1){
		getUserInfo();
	}
}
//动态管理
/**
 * 获取搜索到的动态信息
 * @returns
 */
function getNewsInfo(){
	var searchstr=$('#news_search').val();
	var ret= doAjax("./jsp/operation.jsp",{'likes':searchstr,'operation':'getLikesNews'});
	var str="";
	if(ret==undefined||ret==''){
	}
	else{
		for(var i=0;i<ret.length;i++){
				str+='<tr id="news'+i+'"><td>'+ret[i]['News_Time']+'</td><td>'+ret[i]['News_Title']+
				'</td><td>'+
				'<a class="btn" target="_blank" href="./news.html?id='+ret[i]['News_PK']+'">查看</a>'
				+'</td></tr>';	
		}
	}
	$('#News').html(str);
}

function createNews(){
	var News_Title=$('#News_Title').val();
	var News_Text=$('#content1').val();
	var News_Mark=$('#News_Mark').val();
	if(News_Title==''||News_Text==''){
		$('#News-msg').html('标题和内容不能为空。');
		return false;
	}
	var data={'News_PK':'','News_Title':News_Title,'News_Text':News_Text,'News_Time':'','News_Mark':News_Mark};
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'addNews'});
	if(ret.status==1){
		$('#News-msg').html('提交成功。');
	}
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