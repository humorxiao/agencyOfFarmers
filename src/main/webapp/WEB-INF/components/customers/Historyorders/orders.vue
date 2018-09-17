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
              <allorder :goods_list="this.goods"></allorder>
            </div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(8)">上一页</a></li>
                <li><a class="btn" id="orders-page8">1</a></li>
                <li><a class="btn" onclick="add(8)">下一页</a></li>
                <li><a class="btn" id="page8">共1页</a></li>
              </ul>
            </div>
          </div>
          <div class="tab-pane" id="panel-923723">
            <div class="col-md-8 col-md-offset-1 column" id="Orders1"></div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(1)">上一页</a></li>
                <li><a class="btn" id="orders-page1">1</a></li>
                <li><a class="btn" onclick="add(1)">下一页</a></li>
                <li><a class="btn" id="page1">共1页</a></li>
              </ul>
            </div>
          </div>
          <div class="tab-pane" id="panel-923724">
            <div class="col-md-8 col-md-offset-1 column" id="Orders2">
              <nodeliver></nodeliver>
            </div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(2)">上一页</a></li>
                <li><a class="btn" id="orders-page2">1</a></li>
                <li><a class="btn" onclick="add(2)">下一页</a></li>
                <li><a class="btn" id="page2">共1页</a></li>
              </ul>
            </div>
          </div>
          <div class="tab-pane" id="panel-923725">
            <div class="col-md-8 col-md-offset-1 column" id="Orders3"></div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(3)">上一页</a></li>
                <li><a class="btn" id="orders-page3">1</a></li>
                <li><a class="btn" onclick="add(3)">下一页</a></li>
                <li><a class="btn" id="page3">共1页</a></li>
              </ul>
            </div>
          </div>
          <div class="tab-pane" id="panel-923726">
            <div class="col-md-8 col-md-offset-1 column" id="Orders4"></div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(4)">上一页</a></li>
                <li><a class="btn" id="orders-page4">1</a></li>
                <li><a class="btn" onclick="add(4)">下一页</a></li>
                <li><a class="btn" id="page4">共1页</a></li>
              </ul>
            </div>
          </div>
          <div class="tab-pane" id="panel-923727">
            <div class="col-md-8 col-md-offset-1 column" id="Orders5"></div>
            <div class="col-md-10 column">
              <ul class="pagination pull-right">
                <li><a class="btn" onclick="minus(5)">上一页</a></li>
                <li><a class="btn" id="orders-page5">1</a></li>
                <li><a class="btn" onclick="add(5)">下一页</a></li>
                <li><a class="btn" id="page5">共1页</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import allorder from './allorder.vue'
  import nodeliver from './nodeliveryorder.vue'
export default {
  name: 'orders',
  components: {allorder,nodeliver},
  data () {
    return {
      goods:[{orderid:1000,ordertime:1000 },
        {orderid:2000,ordertime:100}]
    }
  },
  methods: {

  }
}
</script>
<style scoped>
</style>
