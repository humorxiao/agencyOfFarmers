<template>
  <div>
    <ul class="nav nav-tabs">
      <li class="active" onclick="getNoPayOrder()"><a
        href="#panel-221256" data-toggle="tab">未支付订单</a></li>
      <li onclick="getUndeliveredOrder()"><a href="#panel-923725"
                                             data-toggle="tab">未发货订单</a></li>
      <li onclick="getUnSignedOrder()"><a href="#panel-923726"
                                          data-toggle="tab">未签收订单</a></li>
      <li onclick="getUnCheckOrder()"><a href="#panel-923727"
                                         data-toggle="tab">未确认收货订单</a></li>
      <li onclick="getApplyBackOrder()"><a href="#panel-923728"
                                           data-toggle="tab">申请退货订单</a></li>
      <li onclick="getApplyChangeOrder()"><a href="#panel-923729"
                                             data-toggle="tab">申请换货订单</a></li>
      <li><a href="#panel-923730" data-toggle="tab">在所有订单中搜索</a></li>
    </ul>
  </div>
</template>

<script>
  export default {
    name: "orderTabs"
  }
</script>

<style scoped>

</style>
