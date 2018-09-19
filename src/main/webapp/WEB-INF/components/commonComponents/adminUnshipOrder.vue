<!--未发货订单.2-->
<template>
  <div class="tab-pane" id="panel-923725">

    <el-table
      :data="tableOrder2"
      style="width: 100%"
      max-height="620">
      <el-table-column
        fixed
        prop="date"
        label="订单号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="交易号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="用户主键"
        width="180">
      </el-table-column>
      <el-table-column
        prop="province"
        label="订单生成时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="city"
        label="订单支付时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="city"
        label="订单支付价格"
        width="180">
      </el-table-column>
      <el-table-column
        prop="city"
        label="收货信息"
        width="180">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="320">
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
  }
</script>

<style scoped>

</style>
