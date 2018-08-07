<template>
  <!-- 导航栏 -->
  <div id="fl-navbar">
    <nav id="fl-navbar-nav" class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div id="fl-navbar-header" class="navbar-header">
          <a class="navbar-brand" href="../../../index.html">
            <span id="fl-logo"></span>
          </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav" id="fl-home-navbar-tab">
            <li><a href="../../../index.html" target="_blank">商城首页</a></li>
            <li><a href="../../../contactus.html" target="_blank">联系我们</a></li>
          </ul>
          <div class="input-group navbar-form navbar-left"  id="fl-home-search-input-group">
            <input id="fl-home-search-input" type="text" class="form-control" placeholder="搜索 商品">
            <span id="fl-home-search-btn" class="input-group-addon">
             <span class="glyphicon glyphicon-search"></span>
            </span>
        </div>
          <ul class="nav navbar-nav navbar-right">
            <li id="user-1" v-if="login_status == 0" :style="{display: block}"><a href="login.html" target="_blank">登录</a></li>
            <li id="user-2" v-if="login_status == 0" :style="{display: block}"><a href="register.html" target="_blank">注册</a></li>
            <li id="user-4" v-if="login_status == 1" :style="{display: block}">林天真 欢迎您！</li>
            <li id="user-3" class="dropdown" v-if="show">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="fl-user-name"></span>个人中心<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">信息管理</a></li>
                <li><a href="#">菜篮子</a></li>
                <li><a href="#">收藏</a></li>
                <li><a href="#">历史订单</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#" onclick="out()">安全退出</a></li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
  </div>
</template>

<script>
export default{
  name: 'navBar',
  data () {
    return {
      login_status: '0',
      show: false,
      block: ''
    }
  }
}
</script>
