<template>
  <div class="row clearfix div-bg-color">
    <div class="col-md-8 col-md-offset-2 column">
      <div class="tabbable" id="tabs-952416">
        <br><br>
        <ul class="nav nav-tabs">
          <li class="active"><a href="#panel-923722" data-toggle="tab">全部订单</a></li>
          <li id="no-pay-order"><a href="#panel-923723" data-toggle="tab">待支付订单</a></li>
          <li id="yet-pay-order" ><a href="#panel-923724" data-toggle="tab">待发货订单</a></li>
          <li><a href="#panel-923725" data-toggle="tab">待确认订单</a></li>
          <li ><a href="#panel-923727" data-toggle="tab">已完成认订单</a></li>
        </ul>
        <div class="tab-content div-bg-color">
          <div class="tab-pane active" id="panel-923722">
            <div id="Orders8">
              <allorder :tableData5="tableData5"></allorder>
            </div>
          </div>
          <div class="tab-pane" id="panel-923723">
            <div id="Orders1">
           <nopay :tableData4="tableData4"></nopay>
            </div>
          </div>
          <div class="tab-pane" id="panel-923724">
            <div id="Orders2">
              <nodeliver :tableData2="tableData2"></nodeliver>
            </div>
          </div>
          <div class="tab-pane" id="panel-923725">
            <div  id="Orders3">
              <nosign :tableData3="tableData3" :database="database"></nosign>
            </div>
          </div>
          <div class="tab-pane" id="panel-923727">
            <div id="Orders5">
              <finish :tableData1="tableData1"></finish>
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
      tableData5: [],
      status:'',
      goods:[],
      tableData4:[],
      tableData3:[],
      tableData2:[],
      tableData1:[],
      database:[]
    }
  },
  mounted:function () {
    axios.post('/api/getUserOrderListPaging', {}).then(response => {
      var j=0
      for (let i = 0; i < response.data[0].length; i++) {
        var goodsnameall = response.data[0][i].Goods_List
       var goodsname = goodsnameall.split("#")
        var goodsnumall = response.data[0][i].Goods_Num
        var goodsnum = goodsnumall.split("#")
        this.goods=[]
       for(let m=0;m<goodsname.length-1;m++)
       {
         var num = response.data[1][j].Goods_Price*goodsnum[m]
           this.goods.push({
             pk:response.data[1][j].Goods_PK,
           name: response.data[1][j].Goods_Name,
           price:response.data[1][j].Goods_Price.toString(),
           number:goodsnum[m].toString(),
           sum:num.toString()
         })
         j++
       }
        if(response.data[0][i].Order_State === 1)
        {
          this.status = '已下订单未支付'
          this.tableData4.push({
          id:response.data[0][i].Order_ID,
          ordertime:response.data[0][i].Order_Time,
          paytime:response.data[0][i].Order_PayTime,
          status:this.status,
          money:response.data[0][i].Order_PayPrice,
          goods:this.goods
          })
        }
        else if(response.data[0][i].Order_State === 2)
        {this.status = '取消'}
        else if(response.data[0][i].Order_State === 3)
        {
          this.status='卖家已发货，快递正在路上'
          this.tableData3.push({
            id:response.data[0][i].Order_ID,
            ordertime:response.data[0][i].Order_Time,
            paytime:response.data[0][i].Order_PayTime,
            status:this.status,
            money:response.data[0][i].Order_PayPrice,
            goods:this.goods
          })
          this.database.push(response.data[0][i])
        }
        else if(response.data[0][i].Order_State === 4)
        {
          this.status='用户已签收'
          this.tableData3.push({
            id:response.data[0][i].Order_ID,
            ordertime:response.data[0][i].Order_Time,
            paytime:response.data[0][i].Order_PayTime,
            status:this.status,
            money:response.data[0][i].Order_PayPrice,
            goods:this.goods
          })
          this.database.push(response.data[0][i])
        }
        else if(response.data[0][i].Order_State === 5)
        {
          this.status='用户已确认收货，订单完成'
          this.tableData1.push({
            id:response.data[0][i].Order_ID,
            ordertime:response.data[0][i].Order_Time,
            paytime:response.data[0][i].Order_PayTime,
            status:this.status,
            money:response.data[0][i].Order_PayPrice,
            goods:this.goods
          })
        }
        else {
          this.status='已支付未发货'
          this.tableData2.push({
            id:response.data[0][i].Order_ID,
            ordertime:response.data[0][i].Order_Time,
            paytime:response.data[0][i].Order_PayTime,
            status:this.status,
            money:response.data[0][i].Order_PayPrice,
            goods:this.goods
          })
        }
        this.tableData5.push({
          id:response.data[0][i].Order_ID,
          ordertime:response.data[0][i].Order_Time,
          paytime:response.data[0][i].Order_PayTime,
          status:this.status,
          money:response.data[0][i].Order_PayPrice,
          goods:this.goods
        })
      }
    })

  },
  methods: {

  }
}
</script>
<style scoped>
</style>
