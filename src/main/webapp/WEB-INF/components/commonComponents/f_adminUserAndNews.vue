<template>
  <div class="tab-pane active" id="panel-221256">
    <input type="text" id="user_search" placeholder="搜索用户" onkeyup="getUserInfo()">（昵称、真实姓名、手机）
    <br><br>
    <table class="table table-condensed table-hover table-bordered">
      <thead>
      <tr><th>昵称</th>
        <th>真实姓名</th>
        <th>性别</th>
        <th>手机</th>
        <th>邮箱</th>
        <th>身份证号码</th>
        <th>注册时间</th>
        <th>操作一</th>
      </tr>
      </thead>
      <tbody id='Users'>

      </tbody>
    </table>

    <span id="User-msg" style="color:red"></span><br>

  </div>
</template>

<script>
    export default {
        name: "f_adminUserAndNews"
    }
</script>

<style scoped>

</style>
