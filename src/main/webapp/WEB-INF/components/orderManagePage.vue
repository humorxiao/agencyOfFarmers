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
          <unpay-order-father></unpay-order-father>
          <unship-order-father></unship-order-father>
          <unsign-order-father></unsign-order-father>
          <unrecept-order-father></unrecept-order-father>
          <finished-order-father></finished-order-father>
          <!--<return-order-father></return-order-father>
          <exchange-order-father></exchange-order-father>-->
          <search-order-father></search-order-father>
        </div>
      </div>
    </div>
  </div>
<!--  <order-msg></order-msg>
  <order-detail></order-detail>
  <revise-order></revise-order>
  <close-order></close-order>-->
  <adminfooter></adminfooter>
</div>
</template>

<script>
import orderNavar from './commonComponents/adminOrderMamageNavar.vue';
import orderTabs from './commonComponents/adminOrderMamageTabs.vue';
import unpayOrderFather from './admin/unpayOrderFather.vue';
import unshipOrderFather from './admin/unshipOrderFather.vue';
import unsignOrderFather from './admin/unsignOrderFather.vue';
import unreceptOrderFather from './admin/unreceptOrderFather.vue';
import finishedOrderFather from './admin/finishedOrderFather.vue'
//import returnOrderFather from './admin/returnOrderFather.vue';
//import exchangeOrderFather from './admin/exchangeOrderFather.vue';
import searchOrderFather from './admin/searchOrderFather.vue';
//import OrderMsg from './commonComponents/adminOrderMsg.vue'
//import OrderDetail from './commonComponents/adminOrderDetail.vue'
//import reviseOrder from './commonComponents/adminReviseOrder.vue'
//import closeOrder from './commonComponents/adminCloseOrder.vue'
import Adminfooter from "./commonComponents/adminfooter.vue";
  export default {
    components: {
      finishedOrderFather,
      searchOrderFather,
//      exchangeOrderFather,
//      returnOrderFather,
      unreceptOrderFather,
      unsignOrderFather,
      unshipOrderFather,
      unpayOrderFather,
//      closeOrder,
//      reviseOrder,
//      OrderDetail,
//      OrderMsg,
      Adminfooter,
      orderNavar,
      orderTabs},
    name: "orderManagePage",
  }
</script>

<style scoped>

</style>
