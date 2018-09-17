<template>
  <div class="tab-pane" id="panel-923725">

    <br><br>
    <form name="example" method="post" action="">
      标题：<input type="text" name="News_Title" placeholder="输入文章标题">(不能为空)<br>
      <br>
      <editorfather></editorfather>
      <br />  动态类别：<select name="News_Mark"><option value="1">联合社动态</option>
      <option value="2">产供销快报</option></select>
      <input type="submit" name="button" value="提交内容" />
      (提交快捷键: Ctrl + Enter)<br>
    </form>

  </div>

</template>

<script>
import myeditorfather from '../admin/editorFather.vue'
    export default {
        name: "t_adminUserAndNews",
      components: {
          editorfather: myeditorfather
      }
    }
</script>

<style scoped>

</style>
