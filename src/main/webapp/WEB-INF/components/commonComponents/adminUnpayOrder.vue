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
        width="240">
      </el-table-column>
      <el-table-column
        prop="userPK"
        label="用户主键"
        width="240">
      </el-table-column>
      <el-table-column
        prop="orderTime"
        label="订单生成时间"
        width="240">
      </el-table-column>
      <el-table-column
        prop="orderPayPrice"
        label="订单应支付价格"
        width="240">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="320">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="EditMoney">修改金额</el-button>
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
        showEdit: [], //显示编辑框
        showBtn: [],
        showBtnOrdinary: true,
        visible2: false,
      }
    },
    methods: {
      EditMoney() {
        this.$prompt('请输入金额', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          this.$message({
            type: 'success',
            message: '修改成功'+ value
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
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
