<template>
  <!-- 导航栏 -->
  <div id="fl-navbar">
    <nav id="fl-navbar-nav" class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
        <div id="fl-navbar-header" class="navbar-header">
          <a class="navbar-brand" href="index.html">
            <span id="fl-logo"></span>
          </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav" id="fl-home-navbar-tab">
            <li><a href="index.html" target="_blank">商城首页</a></li>
          </ul>
          <div class="input-group navbar-form navbar-left"  id="fl-home-search-input-group">
            <input id="fl-home-search-input" type="text" class="form-control" placeholder="搜索 商品">
            <span id="fl-home-search-btn" class="input-group-addon">
              <span class="glyphicon glyphicon-search"></span></span>
          </div>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
  </div>

</template>

<script>
export default {
  name: 'contactNavbar'
}
</script>

<style scoped>

</style>
