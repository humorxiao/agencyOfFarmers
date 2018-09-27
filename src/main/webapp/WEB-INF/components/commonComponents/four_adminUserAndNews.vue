<template>
  <!--<div class="tab-pane" id="panel-923725">-->
    <!--<br><br>-->
    <!--<div class = "title_table">-->
      <!--<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">-->
        <!--<el-form-item label="标题" prop="name">-->
          <!--<el-input v-model="ruleForm.name"></el-input>-->
        <!--</el-form-item>-->
      <!--</el-form>-->
    <!--</div>-->

    <!--<div id="vueapp">-->
      <!--<quill-editor v-model="content"-->
                    <!--ref="quillEditorA"-->
                    <!--:options="editorOption"-->
                    <!--@blur="onEditorBlur($event)"-->
                    <!--@focus="onEditorFocus($event)"-->
                    <!--@ready="onEditorReady($event)">-->
      <!--</quill-editor>-->
      <!--&lt;!&ndash;<div class="content ql-editor" v-html="content"></div>&ndash;&gt;-->
    <!--</div>-->
    <!--<div>-->
      <!--<el-button type="primary" @click = "tosubmit()">提交</el-button>-->
    <!--</div>-->
  <!--</div>-->

</template>

<script>
  import axios from 'axios'
  export default {
    components: {
      // LocalQuillEditor: VueQuillEditor.quillEditor
    },
    data() {
      return{
        value: '',
        input: '',
        content: '',
        data: '',
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
        // console.log('editor blur!', quill)
      },
      onEditorFocus(quill) {
        // console.log('editor focus!', quill)
      },
      onEditorReady(quill) {
        // console.log('editor ready!', quill)
      },
      tosubmit() {
        var time = this.gettime()
        this.data = {
          'News_Title' : this.ruleForm.name,
          'News_Text' : this.content,
          'News_Time' : time
        }
        axios.post('/api/addNews', this.data).then(response => {
          // console.log(JSON.stringify(response.data))
          if(response.data.status === 1) {
            this.$message({
              type: 'info',
              message: '新闻发布成功！'
            })
          } else {
            this.$message({
              type: 'info',
              message: '新闻发布失败！'
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
        // console.log(this.data)
        // console.log(this.content)
        // console.log(this.ruleForm.name)
        // console.log(time)
      },
      gettime() {
        var myDate = new Date();//获取系统当前时间
        var time = myDate.getFullYear() + '-' + (myDate.getMonth()+1) + '-' + myDate.getDate() + ' ' + myDate.getHours() + ':' +  myDate.getMinutes() + ':' + myDate.getSeconds()
        return time
      }
    },
    computed: {
      editorA() {
        return this.$refs.quillEditorA.quill
      },
      // editorB() {
      //   return this.$refs.quillEditorB.quill
      // }
    },
    // mounted() {
    //   console.log('this is quill A instance object', this.editorA, 'B instance', this.editorB)
    // }
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
