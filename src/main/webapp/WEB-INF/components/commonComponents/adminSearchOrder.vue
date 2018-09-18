<!--在所有订单中搜索.7-->
<template>
  <div class="tab-pane" id="panel-923730">

    <el-input
      placeholder="根据订单id搜索订单"
      v-model="input10"
      clearable>
    </el-input>
    <el-button type="primary" icon="el-icon-search" @click="searchID(input10)">搜索</el-button>

    <el-table
      :data="tableOrder7"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="orderID"
        label="订单号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="收件人"
        width="180">
      </el-table-column>
      <el-table-column
        prop="userTel"
        label="联系电话"
        width="180">
      </el-table-column>
      <el-table-column
        prop="orderTime"
        label="订单生成时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="orderPayTime"
        label="订单支付时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="orderPayPrice"
        label="订单支付价格"
        width="180">
      </el-table-column>
      <el-table-column
        prop="orderState"
        label="订单状态"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="收货地址"
        width="320">
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
  export default {
    name: "searchOrder",
    props: {
      tableOrder7: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    data() {
      return {
        input10: ''
      }
    },
    methods: {
      searchID(input10){
        this.$emit('searchID',input10)
        //alert(input10)
      }
    }
  }
</script>

<style scoped>
.el-input{
  width:30%;
}
</style>
