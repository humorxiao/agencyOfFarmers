<!--未支付订单.1-->
<template>
  <div class="tab-pane active" id="panel-221256">

    <el-table
      :data="tableOrder"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="orderID"
        label="订单号"
        width="180">
        <template slot-scope="scope">
          <span>{{scope.row.orderID}}</span>
        </template>
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
        <template slot-scope="scope">
          <span>{{scope.row.address}}</span>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="320">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="EditMoney(scope.$index, scope.row)">修改金额</el-button>
          <el-button
            size="mini"
            type="primary"
            @click="EditAddress(scope.$index, scope.row)">修改地址</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">取消订单</el-button>
        </template>

      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "unpayOrder",
    props: {
      tableOrder: {
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
      EditMoney: function(index,row) {
        this.$prompt('请输入金额', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          this.$emit('EditMoney',value,index,row.orderPK)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      EditAddress: function(index,row) {
        this.$prompt('请输入地址', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          this.$emit('EditAddress',value,index,row.orderPK)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      handleDelete: function(index,row) {
        this.$confirm('此操作将取消该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(() => {
          this.$message({
            type: 'success',
            message: '取消订单成功'
          });
          this.$emit('handleDelete',index,row,row.orderID)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消操作'
          });
        });
      }
    }
  }
</script>

<style scoped>
  .tb-edit .el-input {
    display: none
  }
  .tb-edit .current-row .el-input {
    display: block
  }
  .tb-edit .current-row .el-input+span {
    display: none
  }
</style>
