<template>
  <div class="modal fade" id="modal-container-Order" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" aria-hidden="true" type="button"
                  data-dismiss="modal">×</button>
        </div>
        <div class="modal-body" id="order">

        </div>
        <div class="modal-footer">
          <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
          <span id="Order"></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "closeOrder"
  }
</script>

<style scoped>

</style>
