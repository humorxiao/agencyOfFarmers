<!--未发货订单.2-->
<template>
  <div class="tab-pane" id="panel-923725">

    <el-table
      :data="tableOrder2"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="orderID"
        label="订单号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="orderPK"
        label="订单主键"
        width="130">
      </el-table-column>
      <el-table-column
        prop="userName"
        label="收件人"
        width="130">
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
        prop="orderPayPrice"
        label="支付价格"
        width="180">
        <template slot-scope="scope">
          <span>{{scope.row.orderPayPrice}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="收货地址"
        width="260">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="320">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="EditMoney2(scope.$index, scope.row)">修改金额</el-button>
          <el-button
            size="mini"
            type="primary"
            @click="EditAddress2">修改地址</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete2(scope.$index, scope.row)">取消订单</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: "unshipOrder",
    props: {
      tableOrder2: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    data() {
      return {

      }
    },
    methods: {
      EditMoney2: function(index,row) {
        this.$prompt('请输入金额', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          this.$emit('EditMoney2',value,index,row.orderPK)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      EditAddress2() {

      },
      handleDelete2() {

      }
    }
  }
</script>

<style scoped>

</style>
