<!--未确认收货.4-->
<template>
  <div class="tab-pane" id="panel-923727">

    <el-table
      :data="tableOrder4"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="orderID"
        label="订单号"
        width="180">
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
            @click="handleEdit(scope.$index, scope.row)">确认收货</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: "unreceptOrder",
    props: {
      tableOrder4: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    data() {
      return {
        showEdit: [], //显示编辑框
        showBtn: [],
        showBtnOrdinary: true
      }
    },
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
        this.showEdit[index] = true;
        this.showBtn[index] = true;
        this.$set(this.showEdit,row,true)
        this.$set(this.showBtn,row,true)
      }
    }
  }
</script>

<style scoped>

</style>
