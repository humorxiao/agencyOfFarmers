<template>
  <div>
    <div class="modal fade" id="modal-container-735678" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    data-dismiss="modal">×
            </button>
            <h4 class="modal-title" id="myModalLabel">请认真确认以下信息</h4>
          </div>
          <div class="modal-body">
            <span id='msg' style='color: red'></span> 收货人名字:<input
            id='Deliv_Name' type="text" placeholder="姓名"/><br> 收货人手机:<input
            id='Deliv_Cell' type="text" placeholder="（重要）手机号码"/><br>
            邮政编码：<input id="Deliv_Zipcode" type="text" placeholder="邮编"/><br>
            <br> 详细地址：
            <textarea id="Deliv_Address" placeholder="详细地址"></textarea>
          </div>
          <div class="modal-footer">
            <button class="btn btn-default" type="button" data-dismiss="modal">取消购物</button>
            <a id="modal-766236" href="#modal-container-766236" @click="updateAddress(2)" role="button"
               class="btn yellow" data-toggle="modal">仅用于<br>本次购物
            </a> <a id="modal-766236" href="#modal-container-766236" @click="updateAddress(1)" role="button"
                    class="btn yellow" data-toggle="modal">保存为<br>默认收货信息
          </a>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="modal-container-766236" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    onclick="refresh()" data-dismiss="modal">×
            </button>
            <h4 class="modal-title" id="edit_title">订单详情(请24小时内支付订单）</h4>
          </div>
          <p class="modal-body" id="edit_body">
            <p id="order_detail"> 订单号：{{orderid}}<br>订单状态:已下订单未支付<br>
              交易额:{{ordercost}}<br>收货信息:<br>手机:{{phone}}<br>联系人:{{name}}<br>
              地址:{{orderaddress}} <br>邮编：{{code}}<br>商品清单:<br> <li v-for="good in goods">
            {{good.name}}({{good.prize}}元/斤){{good.num}}斤,小计{{good.count}}元</li><br>
            选择支付方式：<select id="payWay1">
            <option value="2">支付宝支付</option>
            <option value="3">微信支付</option>
          </select><br> <span style="color: red" id="order-msg"></span>
          </div>
          <div class="modal-footer">
           <button class="btn yellow" type="button" @click="payOrder()">支付</button>
            <button class="btn yellow" type="button" onclick="refresh()"
                    data-dismiss="modal">
              暂不支付<br></button>
            <span id="updateState-btn"></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'pay',
  props: {
    Goods_List_BackUp: {type: Array, required: true},
    Goods_Num_BackUp: {type: Array, required: true},
    sum: {type: String, required: true},
    count: {type: String, required: true}
  },
  data () {
    return {
      Address: '',
      local_Goods_List: [],
      local_Goods_Num: [],
      Goods_List: '',
      Goods_Num: '',
      orderid: '0000',
      ordercost: '111',
      phone: '111',
      name: 'xxx',
      orderaddress: '1111',
      code: '4444',
      goods: [{name: '李子', prize: '11', num: '44', count: '44'}]
    }
  },
  methods: {
    getname: function (name) {
      var ret = '' // doAjax("../jsp/operation.jsp", {'data': JSON.stringify({'Goods_PK': Goods_PK}), 'operation': 'getOneGood'});
      if (ret !== '') {
        return ret['Goods_Name']
      }
      return ''
    },
    initOrder: function (order) {
      var array = order['Order_Reserve_1'].split(';')
      var list = order['Goods_List'].split('#')
      var num = order['Goods_Num'].split('#')
      var prices = order['Goods_Prices'].split('#')
      this.orderid = order['Order_ID']
      this.ordercost = order['Order_PayPrice']
      this.phone = array[0]
      this.name = array[1]
      this.orderaddress = array[2]
      this.code = array[3]
      for (var j = 0; j < list.length && j < prices.length - 1 && j < num.length - 1; j++) {
        this.goods.name[j] = this.getName(list[j])
        this.goods.prize[j] = prices[j]
        this.goods.num = num[j]
        this.goods.count = num[j] * prices[j]
      }
    },
    alterCart: function () {
      for (var i = 0; i < this.local_Goods_List.length - 1; i++) {
        this.Goods_List += this.local_Goods_List[i] + '#'
        this.Goods_Num += this.local_Goods_Num[i] + '#'
      }
      var data = {'Cart_PK': this.local_Cart_PK, 'Goods_List': this.Goods_List, 'Goods_Num': this.Goods_Num}
      var ret = 1 // 修改购物车
      if (ret !== undefined && ret.status === 1) {
        return 1
      } else {
        return 0
      }
    },
    addOrder: function () {
      var data = {
        'Order_PK': '',
        'Order_ID': '',
        'Order_No': '',
        'User_PK': '',
        'Goods_List': this.Goods_List_BackUp,
        'Goods_Num': this.Goods_Num_BackUp,
        'Goods_Prices': this.count,
        'Order_Time': '',
        'Order_IsPay': false,
        'Order_PayTime': '',
        'Order_PayPrice': this.sum,
        'Order_State': 1,
        'Order_TrackNum': '',
        'Order_Company': '',
        'Order_Website': '',
        'Order_Aftersale': 0,
        'Order_Reserve_1': this.address
      }
      var ret = 1 // doAjax("./jsp/operation.jsp", {'data': JSON.stringify(data), 'operation': 'addOrder'});
      this.initOrder(ret)
      if (ret !== undefined && ret !== '') {
        this.local_Goods_List = this.Goods_List_BackUp
        this.local_Goods_Num = this.Goods_Num_BackUp
        this.alterCart()
      } else {
        document.getElementById('order-msg').innerHTML('生成订单失败')
        //  $("#order-msg").html('生成订单失败');
        return false
      }
    },
    updateAddress: function (n) {
      var s = document.getElementById('modal-container-735678')
      s.style.display = 'none'
      var Name = (document.getElementById('Deliv_Name').value)
      if (Name === '') {
        document.getElementById('msg').innerHTML = '请填写收件人姓名'
        return false
      }
      var Cell = (document.getElementById('Deliv_Cell').value)
      if (Cell === '') {
        document.getElementById('msg').innerHTML = '请填写联系号码'
        return
      } else if (Cell.length < 11) {
        document.getElementById('msg').innerHTML = '请填写正确的手机号码'
        return
      }

      var code = (document.getElementById('Deliv_code').value)
      if (code === '') {
        document.getElementById('msg').innerHTML = '请填写邮政编码'
        return
      }

      var Address = (document.getElementById('Deliv_Address').value)
      if (Address === '') {
        document.getElementById('msg').innerHTML = '请填写详细地址'
        return
      }
      this.Deliv_Name = Name
      this.Deliv_Cell = Cell
      this.Deliv_code = code
      this.Deliv_Address = Address
      var data = {
        'Deliv_PK': '',
        'Deliv_Cell': this.Deliv_Cell,
        'Deliv_Name': this.Deliv_Name,
        'Deliv_Address': this.Deliv_Address,
        'Deliv_Zipcode': this.Deliv_Zipcode
      }
      this.address = this.Deliv_Cell + ';' + this.Deliv_Name + ';' + this.Deliv_Address + ';' + this.Deliv_Zipcode + ';';

      if (n === 1) {
        /* doAjax('./jsp/operation.jsp', {
            'data': JSON.stringify(data),
            'operation': 'updateUserDeliveryAddress'
          }) */
        var ret = ''
        if (ret.status === 1) {
          var b = document.getElementById('modal-container-735678')
          b.style.display = 'none'
          var c = document.getElementById('modal-container-766236')
          c.style.display = 'block'
        } else alert('更新默认收货地址失败')
      } else {
        var d = document.getElementById('modal-container-735678')
        d.style.display = 'none'
        var f = document.getElementById('modal-container-766236')
        f.style.display = 'block'
        this.addOrder()
      }
    }}}
</script>
<style>
</style>
