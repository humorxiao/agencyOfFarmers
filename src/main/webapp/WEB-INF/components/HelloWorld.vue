<template>
  <div class="hello">
    <div>
      <div class="row clearfix">
        <div class="col-md-12 column">
          <ul class="breadcrumb bg-color">
            <li class="active">首页</li>
          </ul>
        </div>
      </div>

      <div class="row clearfix div-bg-color">
        <div class="col-md-10 col-md-offset-1 column">
          <p>欢迎您，管理员</p>
          <p>
            合作社内部信息管理：<a href="http://localhost:8081/editinfo.html#/unionManagePage" >click here!</a><br>
            订单信息管理：<a href="http://localhost:8081/editinfo.html#/orderManagePage">click here!</a><br>
            用户和动态编辑管理：<a href="http://localhost:8081/editinfo.html#/userAndnewsManagePage">click here!</a><br>

          </p>
          <a class="btn btn-success" onclick=out()>安全退出</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
