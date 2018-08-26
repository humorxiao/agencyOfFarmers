<template>
  <div class="row clearfix div-bg-color">
  <br><br>
<div class="col-md-8 col-md-offset-2 column">
<div class="tabbable" id="tabs-952416">
  <br>
  <ul class="nav nav-tabs">
    <li class="active" ><router-link to="/nopayorders" href="#panel-923722" data-toggle="tab">全部订单</router-link></li>
    <li id="no-pay-order" ><router-link to="/nopayorders"  href="#panel-923723" data-toggle="tab">待支付订单</router-link></li>
    <li id="yet-pay-order"> <router-link to="/nodeliveryorder"  href="#panel-923724" data-toggle="tab">待发货订单</router-link></li>
    <li><router-link to="/nosignorder" href="#panel-923725" data-toggle="tab">待确认订单</router-link></li>
    <li ><router-link to="/finishorder" href="#panel-923727" data-toggle="tab">已完成认订单</router-link></li>
  </ul>
</div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'orders',
  components: {},
  data () {
    return {
      isHighestPage: [false, false, false, false, false, false, false, false]
    }
  },
  methods: {

  }
}
</script>
<style scoped>
</style>
