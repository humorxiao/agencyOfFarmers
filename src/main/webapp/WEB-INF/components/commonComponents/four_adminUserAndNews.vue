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

     <div id="vueapp">
      <quill-editor v-model="content"
                    ref="quillEditorA"
                    :options="editorOption"
                    @blur="onEditorBlur($event)"
                    @focus="onEditorFocus($event)"
                    @ready="onEditorReady($event)">
      </quill-editor>
      <!--<div class="content ql-editor" v-html="content"></div>-->
    </div>

      <div>
      <el-button type="primary" @click = "tosubmit()">提交</el-button>
      </div>
  </div>

</template>

<script>
    export default {
      components: {
          LocalQuillEditor: VueQuillEditor.quillEditor
      },
      data() {
          return{
            value: '',
            input: '',
            content: '',
            ruleForm: {
              name: ''
            },
            rules: {
              name: [
                { required: true, message: '请输入新闻标题', trigger: 'blur' },
                { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
              ]
            },
            message: '',
            // content: '<h2>I am Example A</h2>',
            // content2: '<h2>I am Example B</h2>',
            editorOption: {
              theme: 'snow'
            }
          }
      },
      methods: {
        onEditorBlur(quill) {
          console.log('editor blur!', quill)
        },
        onEditorFocus(quill) {
          console.log('editor focus!', quill)
        },
        onEditorReady(quill) {
          console.log('editor ready!', quill)
        },
        tosubmit() {
          console.log(this.content)
          console.log(this.ruleForm.name)
          var time = this.gettime()
          console.log(time)
        },
        gettime() {
          var myDate = new Date();//获取系统当前时间
          // console.log(myDate.getFullYear()) //获取完整的年份(4位,1970-????)
          // console.log(myDate.getMonth()) //获取当前月份(0-11,0代表1月)
          // console.log(myDate.getDate()) //获取当前日(1-31)
          // console.log(myDate.getHours())// 时
          // console.log( myDate.getMinutes()) //分
          // console.log( myDate.getSeconds()) //秒
          var time = myDate.getFullYear() + '-' + (myDate.getMonth()+1) + '-' + myDate.getDate() + ' ' + myDate.getHours() + ':' +  myDate.getMinutes() + ':' + myDate.getSeconds()
          return time
        }
      },
      computed: {
        editorA() {
          return this.$refs.quillEditorA.quill
        },
        editorB() {
          return this.$refs.quillEditorB.quill
        }
      },
      mounted() {
        console.log('this is quill A instance object', this.editorA, 'B instance', this.editorB)
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
  .quill-editor,
  .content {
    background-color: white;
  }
</style>
