<template>
  <div class="row clearfix div-bg-color">
    <div class="col-md-8 col-md-offset-2 column">
      <div class="tabbable" id="tabs-952416">
        <br><br>
        <ul class="nav nav-tabs">
          <li class="active" @click="getOrders()"><a href="#panel-923722" data-toggle="tab">全部订单</a></li>
          <li id="no-pay-order" @click="getNoPayOrder()"><a href="#panel-923723" data-toggle="tab">待支付订单</a></li>
          <li id="yet-pay-order" @click="getUndeliveredOrder()"><a href="#panel-923724" data-toggle="tab">待发货订单</a></li>
          <li @click="getUnSignedOrder()"><a href="#panel-923725" data-toggle="tab">待确认订单</a></li>
          <li @click="getCheckOrder()"><a href="#panel-923727" data-toggle="tab">已完成认订单</a></li>
        </ul>
        <div class="tab-content div-bg-color">
          <div class="tab-pane active" id="panel-923722">
            <div class="col-md-8 col-md-offset-1 column" id="Orders8">
              <allorder :tableData5="tableData5"></allorder>
            </div>
          </div>
          <div class="tab-pane" id="panel-923723">
            <div class="col-md-8 col-md-offset-1 column" id="Orders1">
           <nopay></nopay>
            </div>
          </div>
          <div class="tab-pane" id="panel-923724">
            <div class="col-md-8 col-md-offset-1 column" id="Orders2">
              <nodeliver></nodeliver>
            </div>
          </div>
          <div class="tab-pane" id="panel-923725">
            <div class="col-md-8 col-md-offset-1 column" id="Orders3">
              <nosign></nosign>
            </div>
          </div>
          <div class="tab-pane" id="panel-923727">
            <div class="col-md-8 col-md-offset-1 column" id="Orders5">
              <finish></finish>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import allorder from './allorder.vue'
  import nodeliver from './nodeliveryorder.vue'
  import nopay from './nopayorders'
  import nosign from './nosignorder'
  import finish from './finishorder'
export default {
  name: 'orders',
  components: {allorder,nodeliver,nopay,nosign,finish},
  data () {
    return {
      tableData5: [{
        id: '12987122',
       ordertime:'2013',
       paytime:'2016-52',
        status:'未支付',
        money:'5000',
        goods:[{name:'xxx',price:'11111',sum:'500'}]
      }],
      status:'',
      goods:[]
    }
  },
  mounted:function () {
    axios.post('/api/getUserOrderListPaging', {}).then(response => {
      for (let i = 0; i < response.data.length; i++) {
        if(response.data[i].Order_State === 1)
        {this.status = '已下订单未支付'}
        else if(response.data[i].Order_State === 2)
        {this.status = '取消'}
        else if(response.data[i].Order_State === 3)
        {this.status='卖家已发货，快递正在路上'}
        else if(response.data[i].Order_State === 4)
        {this.status='用户已签收'}
        else if(response.data[i].Order_State === 5)
        {this.status='用户已确认收货，订单完成'}
        else {this.status='已支付未发货'}
        var goodsnameall = response.data[i].Goods_List
       var goodsname = goodsnameall.split("#")
       // this.goods=[]
       // for(let j = 0;j < goodsname.length-1;j++)
       // { var data={"Goods_PK":goodsname[j].toString()}
       //    console.log(goodsname[j])
         // axios.post('/api/getOneGood', data).then(response1 => {
         //   var num = response1.data.Goods_Price*response1.data.Goods_Num
         //   console.log(response1.data)
           // this.goods.push({
           //   name:response1.data.Goods_Name,
           //   price:response1.data.Goods_Price.toString(),
           //   sum:num.toString()
           // })
       //   })
       // }
        //console.log(this.goods)
        console.log('////////////////////')
        this.tableData5.push({
          id:response.data[i].Order_ID,
          ordertime:response.data[i].Order_Time,
          paytime:response.data[i].Order_PayTime,
          status:this.status,
          money:response.data[i].Order_PayPrice,
          goods_list:goodsname
        })
        console.log(this.tableData5)
      }
    })
     for(let i = 0;i<this.tableData5.length;i++) {
      console.log('///////')
       for (let j = 0; j < this.tableData5[i].goods_list.length - 1; j++) {
         console.log(this.tableData5[i].goods_list[j])
         var data = {"Goods_PK": this.tableData5[i].goods_list[j]}
         axios.post('/api/getOneGood', data).then(response1 => {
           console.log(response1.data)
           // this.tableData5.push({
           //   goods:
           // })
         })
       }
     }
  },
  methods: {

  }
}
</script>
<style scoped>
</style>
