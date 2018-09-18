<template>
  <div class="tab-pane" id="panel-923725">
    <br><br>
      <div class = "title_table">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标题" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      </el-form>
      </div>
      <editorfather></editorfather>
      <div>
      <span>动态类别：</span>
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary">提交</el-button>
      </div>
  </div>

</template>

<script>
import myeditorfather from '../admin/editorFather.vue'
    export default {
        // name: "t_adminUserAndNews",
      components: {
          editorfather: myeditorfather
      },
      data() {
          return{
            options: [{
              value: '选项1',
              label: '联合社动态'
            }, {
              value: '选项2',
              label: '产供销快报'
            }],
            value: '',
            input: '',
            ruleForm: {
              name: ''
            },
            rules: {
              name: [
                { required: true, message: '请输入新闻标题', trigger: 'blur' },
                { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
              ]
            }
          }
      }
    }
</script>

<style scoped>
  .el-input{
    width: 20%;
  }
  .title_table{
    margin-left: -45px;
  }
</style>
