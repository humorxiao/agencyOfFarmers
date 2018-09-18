<!--管理者订单-->
<template>
<div>
  <order-navar></order-navar>
  <div class="row clearfix div-bg-color">
    <div class="col-md-10 col-md-offset-1 column">
      <div class="tabbable" id="tabs-952416">
        <br>
        <order-tabs></order-tabs>
        <div class="tab-content div-bg-color">
          <unpay-order></unpay-order>
          <unship-order></unship-order>
          <unsign-order></unsign-order>
          <unrecept-order></unrecept-order>
          <return-order></return-order>
          <exchange-order></exchange-order>
          <search-order></search-order>
        </div>
      </div>
    </div>
  </div>
  <order-msg></order-msg>
  <order-detail></order-detail>
  <revise-order></revise-order>
  <close-order></close-order>
  <adminfooter></adminfooter>
</div>
</template>

<script>
import orderNavar from './commonComponents/adminOrderMamageNavar.vue';
import orderTabs from './commonComponents/adminOrderMamageTabs.vue';
import unpayOrder from './commonComponents/adminUnpayOrder.vue';
import unshipOrder from './commonComponents/adminUnshipOrder.vue';
import unsignOrder from './commonComponents/adminUnsignOrder.vue';
import unreceptOrder from './commonComponents/adminUnreceptOrder.vue';
import returnOrder from './commonComponents/adminReturnOrder.vue';
import exchangeOrder from './commonComponents/adminExchangeOrder.vue';
import searchOrder from './commonComponents/adminSearchOrder.vue';
import OrderMsg from './commonComponents/adminOrderMsg.vue'
import OrderDetail from './commonComponents/adminOrderDetail.vue'
import reviseOrder from './commonComponents/adminReviseOrder.vue'
import closeOrder from './commonComponents/adminCloseOrder.vue'
import Adminfooter from "./commonComponents/adminfooter.vue";
  export default {
    components: {
      closeOrder,
      reviseOrder,
      OrderDetail,
      OrderMsg,
      Adminfooter,
      searchOrder,
      exchangeOrder,
      returnOrder,
      unreceptOrder,
      unsignOrder,
      unshipOrder,
      unpayOrder,
      orderNavar,
      orderTabs},
    name: "orderManagePage",
  }
</script>

<style scoped>

</style>
