<!--订单已完成.5-->
<template>
  <div class="tab-pane" id="panel-923728">

    <el-table
      :data="tableOrder5"
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
        fixed
        prop="orderPK"
        label="交易号"
        width="130">
      </el-table-column>
      <el-table-column
        prop="userPK"
        label="用户主键"
        width="130">
      </el-table-column>
      <el-table-column
        prop="orderTime"
        label="订单生成时间"
        width="160">
      </el-table-column>
      <el-table-column
        prop="orderPayPrice"
        label="订单支付价格"
        width="160">
      </el-table-column>
      <el-table-column
        prop="orderTrackNum"
        label="快递单号"
        width="160">
      </el-table-column>
      <el-table-column
        prop="userMsg"
        label="收货信息"
        width="260">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="300">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">关闭订单</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: "finishedOrder",
    props: {
      tableOrder5: {
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
      handleEdit: function(index,row) {
        this.$confirm('此操作将永久关闭该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(() => {
          this.$message({
            type: 'success',
            message: '关闭订单成功'
          });
          this.$emit('handleEdit',index,row,row.orderID)
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

</style>
