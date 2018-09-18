<template>
  <div class="modal fade" id="modal-container-Orders" role="dialog"
       aria-hidden="true" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" aria-hidden="true" type="button"
                  data-dismiss="modal">×</button>
          <h4 class="modal-title" id="myModalLabel">订单资料填写</h4>
        </div>
        <div class="modal-body">
          订单号：<span id='Order_ID'></span><br>
          交易号：<span id='Order_No'></span><br>
          用户主键：<span id='Order_User_Pk'></span><br>
          订单生成时间：<span id='Order_Time'></span><br>
          订单是否支付：<span id='Order_IsPay'></span><br>
          订单支付时间：<span id='Order_PayTime'></span><br>
          订单支付价格：<span id='Order_PayPrice'></span><br>
          订单完成状态：<select id="Order_State">
          <option value="1">已下订单未支付。</option>
          <option value="2">取消。</option>
          <option value="6">已支付未发货。</option>
          <option value="3">卖家已发货，快递正在路上。</option>
          <option value="4">用户已签收。</option>
          <option value="5">用户已确认收货，订单完成。</option>
        </select><br>
          订单收货信息：<span id="Order_Reserve_1"></span><br>
          快递单号：<input type="text" id="Order_TrackNum" /><br>
          快递公司：<select id="Order_Company">
          <option value="1">申通快递</option>
          <option value="2">中通快递</option>
          <option value="3">圆通速递</option>
          <option value="4">EMS</option>
          <option value="5">韵达速递</option>
          <option value="6">顺丰速运</option>
        </select><br>
          售后状态：<select id="Order_Aftersale">
          <option value="0">无</option>
          <option value="1">申请退货</option>
          <option value="2">退货成功</option>
          <option value="3">申请换货</option>
          <option value="4">换货成功</option>
        </select><br>
          <span id='Order-msg' style="color: red"></span><br>
          <br>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
          <span id="Orders-update"></span>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
  export default {
    name: "OrderMsg"
  }
</script>

<style scoped>

</style>
