<template>
  <div>
    <div class="col-md-8 col-md-offset-1 column" id="Orders8"
       v-for="(good, index) in  goods_list" :key="good.id">
      <hr size="55px" noshade="true"><span class="pull-left">订单号：{{good.orderid}}
      <br>下单时间:{{good.ordertime}}<br>订单支付时间:{{good.paytime}}</span>
      <span class="pull-right">订单状态:{{good.orderstate}}<br>交易额:{{good.sum}}元</span>
      <table class="table"><thead><tr>
        <th>商品名</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>评价</th>
      </tr></thead><tbody>
      <tr><td>{{name}}</td><td>{{price}}</td><td>{{num}}</td><td>{{count}}</td></tr>
      </tbody></table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import comment from './comment'
export default {
  name: 'allorder',
  props: {
    goods_list: {type: Array, required: true},
  },
  components: {
    comment
  },
  data () {
    return {
      name:'',
      price:'',
      num:'',
      count:''
    }
  }
}
</script>

<style scoped>

</style>
