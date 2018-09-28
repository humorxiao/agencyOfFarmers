<template>
      <div id="Orders5">
        <el-table
          :data="tableData1"
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
                @click="dialogFormVisible = true">添加评价</el-button>
              <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
                <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm">
                  <el-form-item label="满意度">
                    <el-select v-model="ruleForm.region" placeholder="请选择满意度">
                      <el-option label="非常满意" value="1"></el-option>
                      <el-option label="还行" value="2"></el-option>
                      <el-option label="一般" value="3"></el-option>
                      <el-option label="不满意" value="4"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="填写评价">
                    <el-input type="textarea" v-model="ruleForm.desc"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="resetForm('ruleForm')">取 消</el-button>
                  <el-button type="primary" @click="submitForm('ruleForm')">添加评价</el-button>
                </div>
              </el-dialog>
            </template>
          </el-table-column>
        </el-table>
      </div>
</template>

<script>
export default {
  name: 'finishorder',
  props: {
    tableData1: {type: Array, required: true},
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false
          this.$message({
            type: 'success',
            message: '评论添加成功！！！'
          });
          console.log(this.ruleForm.desc)
          console.log(this.ruleForm.region)
        } else {
          this.$message({
            type: 'warning',
            message: 'error'
          });
          return false;
        }
      });
    },
    resetForm(formName) {
      this.dialogFormVisible = false
      this.$message({
        type: 'info',
        message: '取消输入'
      });
    }
  },
  data(){
    return {
      dialogTableVisible: false,
      dialogFormVisible: false,
      ruleForm: {
        region: '',
        desc: ''
      }
    }
  }
}
</script>

<style scoped>

</style>
