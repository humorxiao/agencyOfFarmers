<template>
      <div  id="Orders3">
        <el-table
          :data="tableData3"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand" >
                <el-table
                  :data="props.row.goods"
                  style="width: 100%">
                  <el-table-column
                    prop="name"
                    label="商品名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="price"
                    label="单价"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="number"
                    label="数量">
                  </el-table-column>
                  <el-table-column
                    prop="sum"
                    label="小计">
                  </el-table-column>
                </el-table>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="订单号"
            prop="id"
            wide="300">
          </el-table-column>
          <el-table-column
            label="下单时间"
            prop="ordertime"
            wide="250">
          </el-table-column>
          <el-table-column
            label="订单支付时间"
            prop="paytime"
            wide="250">
          </el-table-column>
          <el-table-column
            label="订单状态"
            prop="status"
            wide="150">
          </el-table-column>
          <el-table-column
            label="交易额"
            prop="money"
            wide="150">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                type="warning"
                size="mini"
                @click="receive(scope.$index, scope.row)">确认收货</el-button>
              <el-button type="danger"
                         size="mini"
                         @click="checkdelivery(scope.$index, scope.row)">查看物流</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
</template>

<script>
  import axios from 'axios'
export default {
  name: 'nosignorder',
  props: {
    tableData3: {type: Array, required: true},
    database: {type: Array, required: true}
  },
  methods: {
    receive(index, row) {
      this.$confirm('是否确认收货', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.database[index].Order_State = 5
        axios.post('/api/updateOrder', this.database[index]).then(response => {
          if(response.data.status===1){
            this.$message({
              type: 'success',
              message: '确认收货成功!'
            });
            this.tableData3.splice(index,1)
          }else{
            this.$message({
              type: 'error',
              message: '操作失败!'
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    }
  },

}
</script>

<style scoped>

</style>
