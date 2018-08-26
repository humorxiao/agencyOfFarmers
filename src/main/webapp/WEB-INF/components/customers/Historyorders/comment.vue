<template>
  <div>
  <div class="row clearfix div-bg-color ">
    <br>
    <div class="modal fade" id="modal-container-391496" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    data-dismiss="modal">×</button>
            <h4 class="modal-title" id="myModalLabel">您的满意，是我们宝贵的动力</h4>
          </div>
          <div class="modal-body">
            <textarea id="comments" placeholder="评价内容……"></textarea>
            <br><span id="msg" style="color: red"></span>
          </div>
          <div class="modal-footer">
            <button class="btn btn-default" type="button"
                    data-dismiss="modal">关闭</button>
            <span id='saveComments'></span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="row clearfix div-bg-color ">
    <div class="modal fade" id="modal-container-391497" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    data-dismiss="modal">×</button>
            <h3 class="modal-title" id="myModalLabel"><p align="center">物流信息</p></h3>
          </div>
          <div class="modal-body">
            <dl class="dl-horizontal" id="msg1">
            </dl>
          </div>
          <div class="modal-footer">
            <button class="btn btn-default" type="button"
                    data-dismiss="modal">关闭</button>
            <span id='saveComments'></span>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'comment'
}
</script>

<style scoped>

</style>
