/**
 * 商品详情页
 */


$(function () {
	checkRank();
	getMenuList();

	$('#fl-home-search-btn').click(search);
	
	var url=window.location.search;
	var index=url.indexOf("id=");
	var Goods_PK='';
	if(index!= -1){
		Goods_PK=url.substring(index+3,url.length);
		var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify({'Goods_PK':Goods_PK}),'operation':'getOneGood'});
		goodsdetail(ret);
		goodsdetail2(ret);
		getComments(Goods_PK);
	}
});
var Goods_Num=0;
var flag=0;

/**
 * 判断是否登录
 */
function checkRank(){
	var ret= doAjax("./jsp/operation.jsp",{'data':'','operation':'checkLoginRank'});
	if(ret.status==0){
		flag=0;
		$('#user-1').show();
		$('#user-2').show();
		$('#user-3').hide();
	}else if(ret.status==2){
		flag=2;
		window.location.href="editinfo.html";
	}else if(ret.status==1){
		flag=1;
		ret= doAjax("./jsp/operation.jsp",{'data':'','operation':'getSessionUserInfo'});
		$('#fl-user-name').html(ret['User_Name']);
		$("#user-3").show();
		$('#user-1').hide();
		$('#user-2').hide();
	}
}

/**
 * 菜单目录
 * @returns
 */
function getMenuList(){
	var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify({'Goods_Type':1}),'operation':'getAllTypeGoods'});
	var goodsList = [[],[],[],[],[],[]];
	var elementIds = ['fl-menu-fruit-item', 'fl-menu-product-item', 'fl-menu-vegetable-item', 'fl-menu-fish-item', 'fl-menu-livestock-item', 'fl-menu-plant-item']
	for(var i=0; i<= ret.length; i++) {
		if (typeof(ret[i]) == "undefined") {
			continue;
		} else {
			switch(ret[i]['Goods_Type']) {
				case 1:
					goodsList[0].push(ret[i]);
					break;
				case 2:
					goodsList[1].push(ret[i]);
					break;
				case 3:
					goodsList[2].push(ret[i]);
					break;
				case 4:
					goodsList[3].push(ret[i]);
					break;
				case 5:
					goodsList[4].push(ret[i]);
					break;
				case 6:
					goodsList[5].push(ret[i]);
					break;
			}
		}
	}
	var elementIdsLength = elementIds.length;
	var goodsListLength = 0;
	var str = '';
	for (var i = 0; i < elementIdsLength; i++) {
		goodsListLength = goodsList[i].length;
		str = '';
		for (var j = 0; j < goodsListLength; j++) {
			str += '<li><a href="details.html?id=' + goodsList[i][j]['Goods_PK'] + '" target=_blank>' + goodsList[i][j]['Goods_Name'] + '</a></li>'
		}
		$('#' + elementIds[i] + ' .dropdown-menu').html(str);
	}
}

function triggleDetailAndComment(arg) {
	if (arg === 'detail') {
		$('#fl-goods-bottom-detail-btn').addClass('active');
		$('#fl-goods-bottom-commemt-btn').removeClass('active');
		$('#fl-goods-bottom-detail').css('display', 'block');
		$('#fl-goods-bottom-commemt').css('display', 'none');
	} else {
		$('#fl-goods-bottom-detail-btn').removeClass('active');
		$('#fl-goods-bottom-commemt-btn').addClass('active');
		$('#fl-goods-bottom-detail').css('display', 'none');
		$('#fl-goods-bottom-commemt').css('display', 'block');
	}
}

/**
 * 获取商品详情
 * @param Goods_PK
 * @returns
 */
function goodsdetail(ret){
	var str='';
	if(ret!=''){
		var Goods_Mark=ret['Goods_Mark'];
		var Goods_Type=ret['Goods_Type'];
		Goods_Num=ret['Goods_Num'];
		var type='';
		if(Goods_Type==1) type='水果';
		else if(Goods_Type==2) type='加工品';
		else if(Goods_Type==3) type='粮蔬';
		else if(Goods_Type==4) type='水产';
		else if(Goods_Type==5) type='禽畜';
		else if(Goods_Type==6) type='植物';
		$('#type').html('<a href="collection.html?type='+Goods_Type+'">'+type+'</a>');
		$('#goodsname').html(ret['Goods_Name']);
		$('#img').html('<img alt="300X200" class="img-style" src="img/'+ret['Goods_Picture']+'" /><br>');
		str='<table class="detail-table"><tbody><tr><td>名称</td><td>'+ret['Goods_Name']+'</td></tr><tr><td>价格</td><td>';
		if(Goods_Mark!=1){
			str+='¥ '+ret['Goods_Price']+'元';
		}else{
			str+='商品已下架';
		}
		str+='</td></tr><tr><td>数量</td><td>'+
			'<a class="btn btn-sm"><img onclick=minus() style="width:20px;" src="icon-img/delete.jpg"/></a>'+
			'<span id="goodsnum">1</span>'+
			'<a class="btn btn-sm"><img onclick=add() style="width:20px;" src="icon-img/add.jpg"/></a>'+
			'</td></tr><tr><td>库存量</td><td>'+Goods_Num+
			'</td></tr><tr><td><button class="btn detail-table-btn yellow" onclick=addGoods("'+ret['Goods_PK']+'")>加入菜篮子</button>'+
			'</td><td><a class="btn detail-table-btn yellow" onclick=collectGoods("'+ret['Goods_PK']+'")>加入收藏夹</a></td></tr></tbody></table>';
	}
	$('#details').html(str);
}
/**
 * 获取产品详情
 * @param Prod_PK
 * @param type
 * @returns
 */
function goodsdetail2(ret){
	var type='';
	var Goods_Type=ret['Goods_Type'];
	if(Goods_Type==1) type='水果';
	else if(Goods_Type==2) type='加工品';
	else if(Goods_Type==3) type='粮蔬';
	else if(Goods_Type==4) type='水产';
	else if(Goods_Type==5) type='禽畜';
	else if(Goods_Type==6) type='植物';
	var str='<dt>名称</dt><dd>'+ret['Goods_Name']+'</dd><dt>类别</dt><dd>'+type+'</dd><dt>生长周期</dt><dd>一年';
		str+=ret['Goods_Season']+'季</dd><dt>开花期</dt><dd>'+ret['Goods_Blossom']+'</dd><dt>挂果期</dt><dd>'+ret['Goods_Fruit'];
		str+='</dd><dt>成熟期</dt><dd>'+ret['Goods_Mature']+'</dd>';
		str+='<dt>保质期</dt><dd>'+ret['Goods_Expiration']+'</dd>';
	    str+='<dt>详情</dt><dd>'+ret['Goods_Reserve_1']+'</dd>';
	    if (ret.Goods_Reserve_2 != undefined) {
			var Goods_Picture=ret.Goods_Reserve_2.split("#");
			for (var i = 0; i < Goods_Picture.length; i++ )
				str+='<img alt="300X200" width="600px" max-height="400px" src="img/'+Goods_Picture[i]+'" /><br><br><br>';
		}
	$('#details2').html(str);
}
/**
 * 收藏商品
 * @param Goods_PK
 * @returns
 */
function collectGoods(Goods_PK){
	if(flag!=1){
		window.location.href="login.html";
		return false;
	}
	var ret = doAjax("./jsp/operation.jsp",{'data':'','operation':'getCollect'});
	var collectFlag=true;
	if(ret!=""){
		for(var i = 0; ret != undefined && i < ret.length; i++) {
			if(Goods_PK==ret[i]['Goods_PK']) collectFlag=false;
		}
	}
	if(collectFlag){
		var data={'Collect_PK':'','User_PK':'','Goods_PK':Goods_PK,'Collect_Time':''};
		var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'addCollect'});
		if(ret.status==1){
		}
	}
	$("#addSuccess").show();
	window.setTimeout("$('#addSuccess').hide()",2000);
}
/**
 * 加入购物车
 * @param Goods_PK
 * @returns
 */
function addGoods(Goods_PK){
	if(flag!=1){
		window.location.href="login.html";
		return false;
	}
	var num=parseInt($('#goodsnum').text());	//加入数量，是int
	if(num=='0'&&num>Goods_Num) {
		$('#msg').html('无法加入菜篮子，可能库存不足');
		return false;
	}
	var ret = doAjax("./jsp/operation.jsp",{'data':'','operation':'getCart'});
	if(ret!=undefined&&ret!=''){
		var Goods_List_str=ret['Goods_List'];	//菜篮子里的商品名称，带#
		var Goods_Num_str=ret['Goods_Num'];		//菜篮子里的商品数量，带#
		//加入StringUtils.split测试
		var Goods_List=Goods_List_str.split('#');	//菜篮子里的商品名称，带#，这是数组
		var Goods_Num=Goods_Num_str.split('#');		//菜篮子里的商品数量，带#，这是数组
		var index=$.inArray(Goods_PK,Goods_List);	//$.inArray() 函数用于在数组中查找指定值，并返回它的索引值（如果没有找到，则返回-1）
		if(index!=-1){
			var pre_num = parseInt(Goods_Num[index]);
			Goods_Num[index]=pre_num + num;
			Goods_Num_str='';
			for(var i=0;i<Goods_List.length-1;i++){
				if(Goods_Num[i]!='#')
					Goods_Num_str=Goods_Num_str+Goods_Num[i]+'#';
			}
		}else{
			Goods_List_str=Goods_List_str+Goods_PK+'#';
			Goods_Num_str=Goods_Num_str+num+'#';
		}
	}else{
		var Goods_List_str=Goods_PK+'#';
		var Goods_Num_str=num+'#';}
		var data={'Cart_PK':ret['Cart_PK'],'Goods_List':Goods_List_str,'Goods_Num':Goods_Num_str}
		var ret2= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'alterCart'});
		$("#addSuccess").show();
		window.setTimeout("$('#addSuccess').hide()",2000);
}

/**
 * 减购买量
 * @returns
 */
function minus(){
	var number=parseInt($('#goodsnum').text());
	if(number<=1) $('#goodsnum').html('1');
	else{
		$('#goodsnum').html(number-1);
	}
}
/**
 * 加购买量
 * @returns
 */
function add(){
	var number=parseInt($('#goodsnum').text());
	if(number<Goods_Num){
		$('#goodsnum').html(number+1);
	}else{
		$('#goodsnum').html(Goods_Num);
	}
	
}
/**
 * 获取商品评论
 * @param Goods_PK
 * @returns
 */
function getComments(Goods_PK){
	var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify({'Goods_PK':Goods_PK}),'operation':'getGoodsComments'});
	var str='';
	if(ret==''){
		str='暂时没有相关评论~~';
	}else{
		for(var i=0;i!=undefined&&i<ret.length;i++){
			str+='<dt>'+ret[i]['Comm_Time']+'</dt><dd>'+ret[i]['Comm_Text']+'</dd>';
		}
	}
	$('#comments').html(str);
	
}


/**
 * 搜索框
 * @returns
 */
function search1(){
	var str=$('#search').val();
	if(str!=""){
		window.location.href='collection.html?searchstr='+str;
	}
}
/**
 * 安全退出
 * @returns
 */
function out(){
	doAjax("./jsp/operation.jsp",{'data':'','operation':'out'});
	location.reload();
	
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