/**
 * 管理员页面之订单管理
 */

$(function () {
	getNoPayOrder();
});
//一些缓存的全局变量
var orders=[];
var searchResult;
var isHighestPage=[false,false,false,false,false,false,false];

//业务函数

/*页码条*/
/**
 * 下一页
 * @param index
 * @returns
 */
function add(index){
	if(!isHighestPage[index-1]){
		var number=parseInt($("#orders-page"+index).text());
		$("#orders-page"+index).html(number+1);
		refreshPage(index);
	}
	
}
/**
 * 前一页
 * @param index
 * @returns
 */
function minus(index){
	var number=parseInt($("#orders-page"+index).text());
	if(number>1){
		$("#orders-page"+index).html(number-1);
		refreshPage(index);
	}
}
/**
 * 局部刷新页面函数
 * @param index
 * @returns
 */
function refreshPage(index){
	switch(index){
	case 1:getNoPayOrder();break;
	case 2:getUndeliveredOrder();break;
	case 3:getUnSignedOrder();break;
	case 4:getUnCheckOrder();break;
	case 5:getApplyBackOrder();break;
	case 6:getApplyChangeOrder();break;
	default:;
	}
}
/**
 * 订单详情
 * @param index_i
 * @param index_j
 * @returns
 */
function orderDetail(index_i,index_j){
	var obj;
	var pay="无";
	if(index_i==6){
		obj=orders[index_i];
	}
	else{
		obj=orders[index_i][index_j];
	}
	
	var str='<dl class="dl-horizontal"><dt>订单号</dt><dd>'+obj['Order_ID']+'</dd><dt>交易号</dt><dd>'+obj['Order_No']+'</dd><dt>下单用户</dt><dd>'+obj['User_Name']+'</dd>'+
				'<dt>购买清单</dt><dd id="good_list"></dd>';
	if(obj['Order_IsPay']){
		pay=obj['Order_PayTime'];
	}
	str+='<dt>合计</dt><dd>'+obj['Order_PayPrice']+'元</dd>'+
	'<dt>生成时间</dt><dd>'+obj['Order_Time']+'</dd>'+
	'<dt>支付时间</dt><dd>'+pay+'</dd>'+
	'<dt>快递单号</dt><dd>'+obj['Order_TrackNum']+'</dd>';
	var array=obj['Order_Reserve_1'].split(';');
	str+='<dt>收货信息</dt><dd>(手机)'+array[0]+'</dd><dd>(联系人)'+array[1]+'</dd><dd>(地址)'+array[2]+'</dd><dd>(邮编)'+array[3]+
	'</dd>';
	
	var Order_State=obj['Order_State'];
	var Order_Aftersale=obj['Order_Aftersale'];
	var state='';
	if(Order_State!=null&&Order_State!=''){
		switch(Order_State){//1，已下订单未支付。2，取消。3，卖家已发货，快递正在路上。4，用户已签收。5，用户已确认收货，订单完成。6，已支付未发货。
		case 1:state='已下订单未支付';break;
		case 2:state='已取消订单';break;
		case 3:state='卖家已发货，快递正在路上';break;
		case 4:state='用户已签收';break;
		case 5:state='用户已确认收货，订单完成';break;
		case 6:state='用户已支付，卖家未发货';break;
		default:;
		}
	}
	if(Order_Aftersale!=null&&Order_Aftersale!=''){
		switch(Order_Aftersale){//0、无 1、申请退货 2、退货成功3、申请换货4、换货成功
		case 1:state+='申请退货';break;
		case 2:state+='退货成功';break;
		case 3:state+='申请换货';break;
		case 4:state+='换货成功';break;
		default:;
		}
	}
	str+='<dt>订单状态</dt><dd>'+state+'</dd>';
	str+='</dl>';
	$('#orderdetail').html(str);
	
	$('#modal-container-OrderDetail').on('shown.bs.modal', function (e) {
		getGoodList(obj);
		$('#modal-container-OrderDetail').unbind('shown.bs.modal');
	})
}

function getGoodList(obj) {
	var detail_goodspks=obj['Goods_List'].split("#");
	var detail_goodsnums=obj['Goods_Num'].split("#");
	var detail_goodsprices=obj['Goods_Prices'].split("#");
	var str = '';
	for(var i=0;i<detail_goodspks.length-1;i++){
		if(detail_goodspks[i]=='') continue;
		var data={'Goods_PK':detail_goodspks[i],'Goods_Name':'','Goods_Type':'','Goods_Num':'',
				'Goods_Price':'','Goods_Mark':'','Goods_Show':'','Goods_Picture':''};
		var detail_goods=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'getOneGood'});
		if(detail_goods!=undefined){
//			$('#good_list').html(detail_goods['Goods_Name']+':'+detail_goodsprices[i]+'元   X '+detail_goodsnums[i]+'份');
			str+=detail_goods['Goods_Name']+':'+detail_goodsprices[i]+'元   X '+detail_goodsnums[i]+'份<br/>';
		}
	}
	$('#good_list').html(str);
}

//未支付订单
/**
 * 得到当前页码的未支付订单
 * @returns
 */
function getNoPayOrder(){
	var str="";
	var page=parseInt($("#orders-page1").text());
	var data={"state":1};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getStateOrderPaging'});
	
	orders[0]=list;
	for(var i=1;i<list.length;i++ ){
		str+='<tr id="orders0_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+
		'<a class="btn" onclick=cancelOrder(0,"'+i+'") data-toggle="modal">取消订单</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderChange1" onclick=orderChange1(0,"'+i+'") data-toggle="modal">修改价格</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(0,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders1').html(str);
	
	$('#page1').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page){
			isHighestPage[0]=true;
		}
		else{
			isHighestPage[0]=false;
		}
	}
	else{
		return false;
	}
}
/**
 * 取消订单
 * @param index_i
 * @param index_j
 * @returns
 */
function cancelOrder(index_i,index_j){
	var data={'Order_ID':orders[index_i][index_j]['Order_ID'],'Order_State':2};
	var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'changeOrderState'});
	if(ret.status==1){
		alert('成功取消订单');
		$("#orders"+index_i+"_"+index_j).hide();
	}
}

function orderChange1(index_i,index_j){
	var obj=orders[index_i][index_j];
	$('#PayPrice').val(obj['Order_PayPrice']);
	$('#Order-Change1').html('<button class="btn btn-primary" type="button" onclick=orderChange2("'+index_i+'","'+index_j+'")>保存</button>');
}

function orderChange2(index_i,index_j){
	var obj=orders[index_i][index_j];
	var PayPrice= $("#PayPrice").val();
	$('#order').html('<h3"><p align="center">是否确认修改！</p></h3>');
	$('#Order').html('<button class="btn btn-primary" type="button" onclick=orderChange("'+index_i+'","'+index_j+'","'+PayPrice+'")>保存</button>');
	$('#modal-container-OrderChange1').modal('hide');
	$('#modal-container-Order').modal('show');
}

/**
 * 修改订单价格
 * @param index_i
 * @param index_j
 * @returns
 */
function orderChange(index_i,index_j,PayPrice){
	var obj=orders[index_i][index_j];
	var data={'Order_PK':obj['Order_PK'],'Order_ID':obj['Order_ID'],'User_PK':obj['User_PK'],
			'Goods_List':obj['Goods_List'],'Goods_Num':obj['Goods_Num'],'Goods_Prices':obj['Goods_Prices'],
			'Order_Time':obj['Order_Time'],'Order_IsPay':obj['Order_IsPay'],'Order_PayTime':obj['Order_PayTime'],
			'Order_PayPrice':PayPrice,'Order_State':obj['Order_State'],'Order_TrackNum':obj['Order_TrackNum'],
			'Order_No':obj['Order_No'],'Order_Company':obj['Order_Company'],'Order_Website':obj['Order_Website'],
			'Order_Aftersale':obj['Order_Aftersale'],'Order_Reserve_1':obj['Order_Reserve_1']};
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'updateOrder'});
	if(ret['status']==1){
		$('#order').html('<h3><p align="center">修改成功！</p></h3>');
		$('#Order').html('');
		getNoPayOrder();
	}else{
		$('#order').html('<h3><p align="center">修改失败，请重试！</p></h3>');
		$('#Order').html('');
		getNoPayOrder();
	}
}

//未发货订单
/**
 * 得到当前页码的未发货订单
 * @returns
 */
function getUndeliveredOrder(){
	var str="";
	var page=parseInt($("#orders-page2").text());
	var data={"state":6};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getStateOrderPaging'});
	
	orders[1]=list;
	for(var i=1;i<list.length;i++ ){
		str+='<tr id="orders1_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['Order_No']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+'</td><td>'+list[i]['Order_PayTime']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+list[i]['Order_Reserve_1']+'</td><td>'+
		'<a class="btn" href="#modal-container-Orders" onclick=updateOrder1(1,"'+i+'") data-toggle="modal">发货</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(1,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders2').html(str);
	
	$('#page2').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page) {
			isHighestPage[1]=true;
		}
		else{
			isHighestPage[1]=false;
		}
	}
	else{
		return false;
	}
}
//未签收订单
/**
 * 得到当前页码的未签收订单
 * @returns
 */
function getUnSignedOrder(){
	var str="";
	var page=parseInt($("#orders-page3").text());
	var data={"state":3};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getStateOrderPaging'});
	$('#page3').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page){
			isHighestPage[2]=true;
		}else{
			isHighestPage[2]=false;
		}
	}
	else{
		return false;
	}
	orders[2]=list;
	for(var i=1;i<list.length;i++ ){
		str+='<tr id="orders2_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['Order_No']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+'</td><td>'+list[i]['Order_PayTime']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+list[i]['Order_TrackNum']+'</td><td>'+
		list[i]['Order_Reserve_1']+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(2,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders3').html(str);
}
//未确认收货订单
/**
 * 得到当前页码的未确认收货订单
 * @returns
 */
function getUnCheckOrder(){
	var str="";
	var page=parseInt($("#orders-page4").text());
	var data={"state":4};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getStateOrderPaging'});
	$('#page4').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page) {
			isHighestPage[3]=true;
		}else{
			isHighestPage[3]=false;
		}
	}
	else{
		return false;
	}
	orders[3]=list;
	for(var i=1;i<list.length;i++ ){
		str+='<tr id="orders3_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['Order_No']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+'</td><td>'+list[i]['Order_PayTime']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+list[i]['Order_TrackNum']+'</td><td>'+
		list[i]['Order_Reserve_1']+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(3,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders4').html(str);
}
//申请退货订单
/**
 * 得到当前页码的申请退货订单
 * @returns
 */
function getApplyBackOrder(){
	var str="";
	var page=parseInt($("#orders-page5").text());
	var data={"afterSale":1};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getAfterSaleOrderPaging'});
	$('#page5').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page){
			isHighestPage[4]=true;
		}else{
			isHighestPage[4]=false;
		}
	}
	else{
		return false;
	}
	orders[4]=list;
	for(var i=1;i<list.length;i++ ){
		var statestr="";
		var state=list[i]['Order_State'];
		if(state=='1'){statestr="已下订单未支付。";}
		else if(state=='2'){statestr="取消。";}
		else if(state=='3'){statestr="卖家已发货，快递正在路上。";}
		else if(state=='4'){statestr="用户已签收。";}
		else if(state=='5'){statestr="用户已确认收货，订单完成。";}
		var aftersale="无";
		state=list[i]['Order_Aftersale'];
		if(state=='1'){aftersale="申请退货";}
		else if(state=='2'){aftersale="退货成功";}
		else if(state=='3'){aftersalev="申请换货";}
		else if(state=='4'){aftersale="换货成功";}
		str+='<tr id="orders4_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['Order_No']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+'</td><td>'+list[i]['Order_PayTime']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+statestr+
		'</td><td>'+list[i]['Order_TrackNum']+'</td><td>'+
		list[i]['Order_Reserve_1']+'</td><td>'+aftersale+'</td><td>'+
		'<a class="btn" onclick=back("'+i+'")>已退货</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(4,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders5').html(str);
}
/**
 * 退货成功
 * @param index
 * @returns
 */
function back(index){
	var obj=orders[4][index];
	var data={'Order_ID':obj['Order_ID'],'Order_Aftersale':2};
	var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'changeOrderAfterSale'});
	if(ret.status==1){
		$("#orders4_"+index_i).hide();
	}
}

//6、申请换货订单
/**
 * 得到当前页码的申请换货订单
 * @returns
 */
function getApplyChangeOrder(){
	var str="";
	var page=parseInt($("#orders-page6").text());
	var data={"afterSale":3};
	var pageinfo={"Page":page,"NumPerPage":10};
	var list=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'pageinfo':JSON.stringify(pageinfo),'operation':'getAfterSaleOrderPaging'});
	$('#page6').html('共'+list[0]['PageNum']+'页');
	if(list!=undefined&&list!=null){
		if(list[0]['PageNum']==page){
			isHighestPage[5]=true;
		}else{
			isHighestPage[5]=false;
		}
	}
	else{
		return false;
	}
	orders[5]=list;
	for(var i=1;i<list.length;i++ ){
		var statestr="";
		var state=list[i]['Order_State'];
		if(state=='1'){statestr="已下订单未支付。";}
		else if(state=='2'){statestr="取消。";}
		else if(state=='3'){statestr="卖家已发货，快递正在路上。";}
		else if(state=='4'){statestr="用户已签收。";}
		else if(state=='5'){statestr="用户已确认收货，订单完成。";}
		var aftersale="无";
		state=list[i]['Order_Aftersale'];
		if(state=='1'){aftersale="申请退货";}
		else if(state=='2'){aftersale="退货成功";}
		else if(state=='3'){aftersalev="申请换货";}
		else if(state=='4'){aftersale="换货成功";}
		str+='<tr id="orders5_'+i+'"><td>'+list[i]['Order_ID']+'</td><td>'+list[i]['Order_No']+'</td><td>'+list[i]['User_Name']+
		'</td><td>'+list[i]['Order_Time']+'</td><td>'+list[i]['Order_PayTime']+
		'</td><td>'+list[i]['Order_PayPrice']+'</td><td>'+statestr+
		'</td><td>'+list[i]['Order_TrackNum']+'</td><td>'+
		list[i]['Order_Reserve_1']+'</td><td>'+aftersale+'</td><td>'+
		'<a class="btn" onclick=change("'+i+'")>已换货</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(5,"'+i+'") data-toggle="modal">详情</a>'+
		'</td></tr>';
	}
	$('#Orders6').html(str);
}
/**
 * 换货成功
 * @param index
 * @returns
 */
function change(index){
	var obj=orders[5][index];
	var data={'Order_ID':obj['Order_ID'],'Order_Aftersale':4};
	var ret = doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'changeOrderAfterSale'});
	if(ret.status==1){
		$("#orders5_"+index).hide();
	}
}
//在所有订单中搜索
/**
 * 根据订单ID搜索全部订单
 * @returns
 */
function getIDOrder(){
	var Order_ID=$('#order_search').val();
	if(Order_ID==''){
		$('#search-msg').html("*订单ID不能为空。");
		return false;
	}
	else if(Order_ID.length<18){
		$('#search-msg').html("*订单ID长度不小于18位");
		return false;
	}
	var data={"Order_ID":Order_ID};
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'getIDOrder'});
	orders[6]=ret;
	if(ret!=undefined&&ret!=''){
		var str="<table class='table table-condensed table-hover table-bordered'><thead>"+
					"<tr><th>订单号</th><th>交易号</th><th>用户主键</th>"+
					"<th>订单生成时间</th><th>订单是否已支付</th><th>订单支付时间</th><th>订单支付价格</th><th>订单完成状态</th>"+
					"<th>快递单号</th><th>收货信息</th><th>售后状态</th><th>操作一</th><th>操作二</th></tr></thead><tbody>";
		str+=getOneOrderTr(ret);
				str+="</tbody></table>";
		$('#orderResult').html(str);
	}else{
		$('#orderResult').html("没有搜索到相关订单。");
	}
	
}
/**
 * 得到一笔订单的一行
 * @param order
 * @returns
 */
function getOneOrderTr(order){
	var str='';
	var paystate="否";
	var pay="无";
	if(order['Order_IsPay']){paystate="是";pay=order['Order_PayTime'];}
	var statestr="";
	var state=order['Order_State'];
	if(state=='1'){statestr="已下订单未支付。";}
	else if(state=='2'){statestr="取消。";}
	else if(state=='3'){statestr="卖家已发货，快递正在路上。";}
	else if(state=='4'){statestr="用户已签收。";}
	else if(state=='5'){statestr="用户已确认收货，订单完成。";}
	var aftersale="无";
	state=order['Order_Aftersale'];
	if(state=='1'){aftersale="申请退货";}
	else if(state=='2'){aftersale="退货成功";}
	else if(state=='3'){aftersalev="申请换货";}
	else if(state=='4'){aftersale="换货成功";}
	
		str+='<tr><td>'+order['Order_ID']+'</td><td>'+order['Order_No']+'</td><td>'+order['User_Name']+'</td><td>'+order['Order_Time']+
		'</td><td>'+paystate+'</td><td>'+pay+
		'</td><td>'+order['Order_PayPrice']+'</td><td>'+statestr+
		'</td><td>'+order['Order_TrackNum']+'</td><td>'+order['Order_Reserve_1']+'</td><td>'+aftersale+'</td><td>'+
		'<a class="btn" href="#modal-container-Orders" onclick=updateOrder1(6,0) data-toggle="modal">修改</a>'
		+'</td><td>'+
		'<a class="btn" href="#modal-container-OrderDetail" onclick=orderDetail(6,0) data-toggle="modal">详情</a>'+
		'</td></tr>';
	return str;
}

/**
 * 修改订单初始化输入框
 * @param index_i
 * @param index_j
 * @returns
 */
function updateOrder1(index_i,index_j){
	var obj;
	if(index_i==6){
		obj=orders[index_i];
	}
	else{
		obj=orders[index_i][index_j];
	}
	$('#Order_ID').html(obj['Order_ID']);
	$('#Order_No').html(obj['Order_No']);
	$('#Order_User_Pk').html(obj['User_Name']);
	$('#Order_Time').html(obj['Order_Time']);
	$('#Order_IsPay').html(obj['Order_IsPay']?"是":"否");
	$('#Order_PayTime').html(obj['Order_IsPay']?obj['Order_PayTime']:"无");
	$('#Order_PayPrice').html(obj['Order_PayPrice']);
	$('#Order_State').val(obj['Order_State']);
	$('#Order_TrackNum').val(obj['Order_TrackNum']);
	$('#Order_Company').val(obj['Order_Company']);
	$('#Order_Aftersale').val(obj['Order_Aftersale']);
	$('#Order_Reserve_1').html(obj['Order_Reserve_1']);
	$('#Orders-update').html('<button class="btn btn-primary" type="button" onclick=updateOrder2("'+index_i+'","'+index_j+'")>保存</button>');
	
}
/**
 * 修改订单
 * @param index_i
 * @param index_j
 * @returns
 */
function updateOrder2(index_i,index_j){
	var obj;
	if(index_i==6){
		obj=orders[index_i];
	}
	else{
		obj=orders[index_i][index_j];
	}
	var Order_State=$('#Order_State').val();
	var Order_TrackNum=$('#Order_TrackNum').val();
	var Order_Aftersale=$('#Order_Aftersale').val();
	var Order_Company=$('#Order_Company').val();
	if(Order_State==''||Order_TrackNum==''||Order_Aftersale==''||Order_Company==''){
		$('#Order-msg').html('请把订单信息填写完整。');
		return false;
	}
	var company="";
	var website="";
	if(Order_Company=='1'){company="申通快递";website="http://www.sto.cn/?from=openv";}
	else if(Order_Company=='2'){company="中通快递";website="http://www.zto.com/?from=openv";}
	else if(Order_Company=='3'){company="圆通速递";website="http://www.yto.net.cn/gw/index/index.html";}
	else if(Order_Company=='4'){company="EMS";website="http://www.ems.com.cn/ems/order/singleQuery_t";}
	else if(Order_Company=='5'){company="韵达速递";website="http://www.yundaex.com/cn/index.php";}
	else if(Order_Company=='6'){company="顺丰速运";website="http://www.sf-express.com/cn/sc/";}
	var data={'Order_PK':obj['Order_PK'],'Order_ID':obj['Order_ID'],'User_PK':obj['User_PK'],
			'Goods_List':obj['Goods_List'],'Goods_Num':obj['Goods_Num'],'Goods_Prices':obj['Goods_Prices'],
			'Order_Time':obj['Order_Time'],'Order_IsPay':obj['Order_IsPay'],'Order_PayTime':obj['Order_PayTime'],
			'Order_PayPrice':obj['Order_PayPrice'],'Order_State':Order_State,'Order_TrackNum':Order_TrackNum,
			'Order_No':obj['Order_No'],'Order_Company':company,'Order_Website':website,
			'Order_Aftersale':Order_Aftersale,'Order_Reserve_1':obj['Order_Reserve_1']};
	var ret= doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'updateOrder'});
	if(ret['status']==1){
		$('#modal-container-Orders').modal('hide');
		$('#modal-container-Order').modal('show');
		$('#order').html('<h3><p align="center">操作成功！</p></h3>');
		$('#Order').html('');
	}else{
		$('#modal-container-Orders').modal('hide');
		$('#modal-container-Order').modal('show');
		$('#order').html('<h3><p align="center">操作成功！</p></h3>');
		$('#Order').html('');
	}
	if(index_i==1){
		getUndeliveredOrder();
	}
	else if(index_i==6){
		getIDOrder();
	}
}
/**
 * 根据用户主键获取用户昵称显示
 * @param userpk
 * @returns
 */
function getName(userpk){
	var data={'User_PK':userpk};
	var user=doAjax("./jsp/operation.jsp",{'data':JSON.stringify(data),'operation':'getUserInfo'});
	if(user!=undefined){
		return user['User_Name'];
	}else{
		return "";
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