<template>
      <div id="Orders5">
        <el-table
          :data="tableData1">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left"  class="demo-table-expand" >
                <el-table
                  :data="props.row.goods">
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
                  <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button
                        type="warning"
                        size="mini"
                        @click="dialogFormVisible = true">添加评价</el-button>
                      <el-dialog title="添加评价" :visible.sync="dialogFormVisible">
                        <el-form :model="ruleForm"  ref="ruleForm" label-width="100px">
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
                          <el-button type="primary" @click="submitForm('ruleForm',scope.row.pk)">添加评价</el-button>
                        </div>
                      </el-dialog>
                    </template>
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
</template>

<script>
import axios from 'axios'
export default {
  name: 'finishorder',
  props: {
    tableData1: {type: Array, required: true},
  },
  methods: {
    submitForm(formName,pk) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var data={"Goods_PK":pk,
            "Comm_Rank":Number(this.ruleForm.region),
            "Comm_Text":this.ruleForm.desc}
          axios.post('/api/addComments', data).then(response => {
           if(response.data.status===1)
           {
             this.dialogFormVisible = false
             this.$message({
               type: 'success',
               message: '评论添加成功！！！'
             });
           }
           else {
             this.$message({
               type: 'warning',
               message: 'error'
             });
             return false;
           }
          })
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
