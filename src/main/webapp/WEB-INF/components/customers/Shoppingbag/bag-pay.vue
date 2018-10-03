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
             type="text" v-model="Deliv_Name"/><br> 收货人手机:<input
             type="text" v-model="Deliv_Cell"/><br>
            邮政编码：<input  type="text" v-model="Deliv_code"/><br>
            <br> 详细地址：
            <textarea type="text" v-model="Deliv_Address"></textarea>
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
                    @click="refresh()" data-dismiss="modal">×
            </button>
            <h4 class="modal-title" id="edit_title">订单详情(请24小时内支付订单）</h4>
          </div>
          <p class="modal-body" id="edit_body">
            <p id="order_detail"> 订单号：{{orderid}}<br>订单状态:已下订单未支付<br>
              交易额：{{ordercost}}<br>收货信息：<br>手机：{{phone}}<br>联系人：{{name}}<br>
              地址：{{orderaddress}} <br>邮编：{{code}}<br>商品清单: <li v-for="good in Goods_List_pay">
            {{good.name}}&nbsp&nbsp({{good.price}}元/斤){{good.num}}斤,小计{{count}}元</li><br>
           <p id="pagway">选择支付方式： <select id="payWay1">
            <option value="2">支付宝支付</option>
            <option value="3">微信支付</option>
           </select><br></p> <span style="color: red" id="order-msg"></span>
          </div>
          <div class="modal-footer">
           <button class="btn yellow" type="button" @click="payOrder()">支付</button>
            <button class="btn yellow" type="button" @click="refresh()"
                    data-dismiss="modal">
              暂不支付<br></button>
            <span id="updateState-btn"></span>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
  import axios from 'axios'
export default {
  name: 'pay',
  props: {
    Goods_List_pay: {type: Array, required: true},
    goods_list:{type: Array, required:true},
    sum: {type: String, required: true},
    count: {type: String, required: true}
  },
  data() {
    return {
      Deliv_Name: '',
      Deliv_Address: '',
      Deliv_Cell: '',
      Deliv_code: '',
      Address: '',
      Goods_List: '',
      Goods_Num: '',
      Goods_List_New: '',
      Goods_price: '',
      Goods_Num_New: '',
      orderid: '',
      ordercost: '',
      phone: '',
      name: '',
      orderaddress: '',
      code: '',
      goods: [],
      userPK: ''
    }
  },
  mounted: function () {
    axios.post('/api/getUserDeliveryAddress', {}).then(response => {
      this.Deliv_Name = response.data.Deliv_Name;
      this.Deliv_Address = response.data.Deliv_Address;
      this.Deliv_Cell = response.data.Deliv_Cell;
      this.Deliv_code = response.data.Deliv_Zipcode;
    })
    axios.post('/api/checkLoginRank').then((response) => {
      // console.log(response.data) // 判断登录状态
      if (response.data.status === 1) {
        this.userPK = response.data.User_PK
      }
    })
  },
  methods: {
    refresh:function(){
      for(let i = 0;i<this.Goods_List_pay.length;i++) {
        this.goods_list.splice(this.goods_list.indexOf(this.Goods_List_pay[i]),1)
      }
      console.log(this.goods_list)
      this.alterCart()
      window.location.href = 'shoppingbag.html'
    },
   formatDate : function(){  //获取格式化时间
     var currentDate = new Date();
     let y=currentDate.getFullYear()
     let mm = currentDate.getMonth()+1
     mm = mm < 10?('0' +mm):mm
     let d = currentDate.getDate()
     d = d < 10?('0'+d):d
     let h = currentDate.getHours()
     h = h < 10?('0'+h):h
     var strMinute = currentDate.getMinutes() < 10 ? ("0" + currentDate.getMinutes()) : currentDate.getMinutes();
     var strSecond = currentDate.getSeconds() < 10 ? ("0" + currentDate.getSeconds()) : currentDate.getSeconds();
     var time
     time = y.toString() + mm.toString() + d.toString()
       + h.toString() + strMinute.toString() + strSecond.toString() + this.userPK
     return time
   },
    alterCart: function () {
      for (var i = 0; i < this.goods_list.length; i++) {
        this.Goods_List_New += this.goods_list[i].id + '#'
        this.Goods_Num_New += this.goods_list[i].num + '#'
      }
      var data = { 'Goods_List': this.Goods_List_New, 'Goods_Num': this.Goods_Num_New}
      axios.post('/api/alterCart', data).then(response => {
        if (response.data.status === 1) {
          window.location.href = 'shoppingbag.html'
        }
        else {
          window.location.href = 'shoppingbag.html'
        }
      })
    },
    initorder:function (){
     this.orderid = this.formatDate()
      this.ordercost = this.count
      this.phone = this.Deliv_Cell
      this.name = this.Deliv_Name
      this.orderaddress = this.Deliv_Address
      this.code = this.Deliv_code
    },
    addOrder: function () {
      for (var i = 0; i < this.Goods_List_pay.length; i++) {
        this.Goods_List += this.Goods_List_pay[i].id + '#'
        this.Goods_Num += this.Goods_List_pay[i].num + '#'
        this.Goods_Price +=this.Goods_List_pay[i].price + '#'
      }
       var data = {
         "User_PK":this.userPK,
         "Order_ID":this.formatDate(),
         "Order_No":"",
         "Goods_List":this.Goods_List,
         "Goods_Num":this.Goods_Num,
         "Goods_Prices":this.Goods_Price,
         "Goods_Type":"2",
         "Order_Time":"2018-09-15 20:03:46",
         "Order_IsPay":false,
         "Order_PayTime":"2018-09-15 20:03:46",
         "Order_TrackNum":"",
         "Order_Company":"",
         "Order_Website":"",
         "Order_Aftersale":"0",
         "Order_PayPrice":"20",
         "Order_State":1,
         "Order_Reserve_1":"13416137226;65;65;656565;"
      }
     console.log(data)
      this.initorder()
     axios.post('/api/addOrder', data).then(response => {
         if(response.data)
         {document.getElementById('edit_body').innerHTML = '添加订单成功'}
         else{document.getElementById('edit_body').innerHTML = '添加订单失败'}
      })
    },

    updateAddress: function (n) {
      if (this.Deliv_Name === '') {
        document.getElementById('msg').innerHTML = '请填写收件人姓名'
        return false
      }
      if (this.Deliv_Cell === '') {
        document.getElementById('msg').innerHTML = '请填写联系号码'
        return
      } else if (this.Deliv_Cell.length < 11) {
        document.getElementById('msg').innerHTML = '请填写正确的手机号码'
        return
      }

      if (this.Deliv_code === '') {
        document.getElementById('msg').innerHTML = '请填写邮政编码'
        return
      }

      if (this.Deliv_Address === '') {
        document.getElementById('msg').innerHTML = '请填写详细地址'
        return
      }
      var data = {
        'Deliv_Cell': this.Deliv_Cell,
        'Deliv_Name': this.Deliv_Name,
        'Deliv_Address': this.Deliv_Address,
        'Deliv_Zipcode': this.Deliv_code
      }
      this.address = this.Deliv_Cell + ';' + this.Deliv_Name + ';' + this.Deliv_Address + ';' + this.Deliv_Zipcode + ';';
      if (n === 1) {
        axios.post('/api/updateUserDeliveryAddress', data).then((response) => {
          console.log(response.data.status)
          if (response.data.status === 1) {
            var b = document.getElementById('modal-container-735678')
            b.style.display = 'none'
            var c = document.getElementById('modal-container-766236')
            c.style.display = 'block'
            this.addOrder()
          } else alert('更新默认收货地址失败')
        })
      } else {
        var d = document.getElementById('modal-container-735678')
        d.style.display = 'none'
        var f = document.getElementById('modal-container-766236')
        f.style.display = 'block'
        this.addOrder()
      }
    },
  }
  }
</script>
<style>
</style>
