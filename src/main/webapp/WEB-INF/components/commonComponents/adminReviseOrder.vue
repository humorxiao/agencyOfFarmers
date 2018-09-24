<template>
  <div class="modal fade" id="modal-container-OrderChange1" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" aria-hidden="true" type="button"
                  data-dismiss="modal">×</button>
          <h4 class="modal-title" id="myModalLabel"><p align="center">修改价格</p></h4>
        </div>
        <div class="modal-body" id="order_change1">
          订单支付价格：<input id='PayPrice' type="text" placeholder="0" />
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
          <span id="Order-Change1"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "reviseOrder"
  }
</script>

<style scoped>

</style>
