<template>
  <div class="app-container calendar-list-container">
    <div style="margin:0 5%; width: 90%;">
      <editor
        class="editor"
        :value="content"
        :setting="editorSetting"
        @show="editors"
        :url              = "Url"
        :max-size         = "MaxSize"
        :accept           = "Accept"
        :with-credentials = "withCredentials"
        @on-upload-fail         = "onEditorReady"
        @on-upload-success= "onEditorUploadComplete"></editor>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import editor from '../commonComponents/editor' // 根据editor.vue组件位置引入
  export default {
    data() {
      return {
        editorSetting: { // 配置富文本编辑器高
          height: 300
        },
        Url: 'http://localhost:9528/api/PublicTransaction-SYS-Web/upload/singleUpload', // 图片对应的上传地址
        MaxSize: 75765, // 文件大小
        Accept: 'image/jpeg, image/png', // 文件格式
        withCredentials: true,
        content: '' // 富文本编辑器双向绑定的内容
      }
    },
    components: { // 引入组件
      editor
    },
    methods: {
      editors(content) { // editor组件传过来的值赋给content
        console.log(content) // 文本编辑器的内容
        this.content = content
      },
      onEditorReady(ins, ina) { // 上传失败的函数
        console.log('ins')
        console.log(ins)
        console.log(ina)
      },
      onEditorUploadComplete(json) { // 处理上传图片后返回数据，添加img标签到编辑框内
        console.log('json')
        console.log(json)
        console.log(json[0].data.filePath)
        this.content = this.content + '<img src=' + json[0].data.filePath + '>'
      }
    }
  }
</script>
<style scoped >

</style>
