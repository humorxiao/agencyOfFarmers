<template>
  <div>
    <div class="tab-pane" id="panel-923729">
      <br>
      <el-button type="text" @click="dialogFormVisible=true">添加商品修改日志</el-button>
      <el-dialog title="添加商品修改日志" :visible.sync="dialogFormVisible">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="进货量" prop="goodsin">
            <el-input v-model="ruleForm.goodsin"></el-input>
          </el-form-item>
          <el-form-item label="销量" prop="goodsout">
          <el-input v-model="ruleForm.goodsout"></el-input>
        </el-form-item>
          <el-form-item label="更改价格（填写新价格）" prop="price">
            <el-input v-model="ruleForm.price"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')">确认</el-button>
        </div>
      </el-dialog>
      <el-table
        :data="message"
        style="width: 60%"
        margin="10px"
        max-height="510">
        <el-table-column
          prop="goodsname"
          label="商品"
          width="150">
        </el-table-column>
        <el-table-column
          prop="buynum"
          label="进货量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="salenum"
          label="销量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="price"
          label="更改价格（填写新价格）"
          width="200">
        </el-table-column>
        <el-table-column
          prop="time"
          label="日志时间"
          width="200">
        </el-table-column>
      </el-table>
      <span id="User-msg" style="color:red"></span><br>
    </div>
  </div>
</template>

<script>
    export default {
        name: "five_union",
      props:{
        message:{type:Array,required:true}
      },
      data() {
        return {
          input: '',
          dialogFormVisible: false,
          ruleForm: {
            name: '',
            goodsin: '',
            goodsout: '',
            price: ''
          },
          rules: {
            name: [
              {required: true, message: '请输入商品名称', trigger: 'blur'},
            ],
            goodsin: [
              {required: true, message: '请输入商品进货量', trigger: 'blur'}
            ],
            goodsout: [
              {required: true, message: '请输入商品销量', trigger: 'blur'}
            ],
            price: [
              {required: true, message: '请输入修改后的价格', trigger: 'blur'}
            ]
          }
        }
      },
      methods:{
      }
    }
</script>

<style scoped>

</style>
