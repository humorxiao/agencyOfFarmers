<template>
  <div class="tab-pane" id="panel-923724">
    <input type="text" id="news_search" placeholder="搜索动态" onkeyup="getNewsInfo()">（标题）
    <br><br>
    <table class="table table-condensed table-hover table-bordered">
      <thead>
      <tr>
        <th>动态时间</th>
        <th>动态标题</th>
        <th>操作一</th>
      </tr>
      </thead>
      <tbody id='News'>
      </tbody>
    </table>
    <span id='News-msg' style="color:red"></span><br>

  </div>
</template>

<script>
    export default {
        name: "s_adminUserAndNews"
    }
</script>

<style scoped>

</style>
