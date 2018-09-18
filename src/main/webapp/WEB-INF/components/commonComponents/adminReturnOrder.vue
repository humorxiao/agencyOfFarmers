<!--申请退货订单.5-->
<template>
  <div class="tab-pane" id="panel-923728">
<<<<<<< HEAD

    <el-table
      :data="tableOrder5"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="date"
        label="订单号"
        width="140">
      </el-table-column>
      <el-table-column
        fixed
        prop="date"
        label="交易号"
        width="140">
      </el-table-column>
      <el-table-column
        prop="name"
        label="用户主键"
        width="140">
      </el-table-column>
      <el-table-column
        prop="province"
        label="订单生成时间"
        width="140">
      </el-table-column>
      <el-table-column
        prop="province"
        label="订单支付时间"
        width="140">
      </el-table-column>
      <el-table-column
        prop="province"
        label="订单支付价格"
        width="140">
      </el-table-column>
      <el-table-column
        prop="province"
        label="订单完成状态"
        width="140">
      </el-table-column>
      <el-table-column
        prop="province"
        label="快递单号"
        width="140">
      </el-table-column>
      <el-table-column
        prop="city"
        label="收货信息"
        width="140">
      </el-table-column>
      <el-table-column
        prop="city"
        label="售后状态"
        width="130">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">修改金额</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">取消订单</el-button>
        </template>
      </el-table-column>
    </el-table>
=======
    <table class="table table-condensed table-hover table-bordered">
      <thead>
      <tr>
        <th>订单号</th>
        <th>交易号</th>
        <th>用户主键</th>
        <th>订单生成时间</th>
        <th>订单支付时间</th>
        <th>订单支付价格</th>
        <th>订单完成状态</th>
        <th>快递单号</th>
        <th>收货信息</th>
        <th>售后状态</th>
        <th>操作一</th>
        <th>操作二</th>
      </tr>
      </thead>
      <tbody id='Orders5'>
      </tbody>
    </table>
>>>>>>> adminOrder updates

  </div>
</template>

<script>
  export default {
<<<<<<< HEAD
    name: "returnOrder",
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
      },
      handleDelete(index, row) {
        console.log(index, row);
      }
    }
=======
    name: "returnOrder"
>>>>>>> adminOrder updates
  }
</script>

<style scoped>

</style>
