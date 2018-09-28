<template>
  <div>
    <div  id="Orders8">
      <el-table
        :data="tableData5"
        style="width: 100%"
        max-height="600">
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
      </el-table>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
export default {
  name: 'allorder',
  props: {
    tableData5: {type: Array, required: true},
  },
}
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
