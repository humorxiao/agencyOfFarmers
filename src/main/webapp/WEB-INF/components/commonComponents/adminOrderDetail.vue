<template>
  <div class="modal fade" id="modal-container-OrderDetail" role="dialog"
       aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" aria-hidden="true" type="button"
                  data-dismiss="modal">×</button>
          <h4 class="modal-title" id="myModalLabel">订单详情</h4>
        </div>
        <div class="modal-body" id="orderdetail"></div>
        <div class="modal-footer">
          <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "OrderDetail"
  }
</script>

<style scoped>

</style>
