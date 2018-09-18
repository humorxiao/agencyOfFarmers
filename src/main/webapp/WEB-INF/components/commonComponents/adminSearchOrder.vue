<!--在所有订单中搜索.7-->
<template>
  <div class="tab-pane" id="panel-923730">
    <input type="text" id="order_search" placeholder="根据订单id搜索订单">
    <button role="button" onclick=getIDOrder()>搜索</button>
    <br>
    <br>
    <div id="orderResult"></div>
    <br> <span id="search-msg" style="color: red;"></span>
  </div>
</template>

<script>
  export default {
    name: "searchOrder"
  }
</script>

<style scoped>

</style>
