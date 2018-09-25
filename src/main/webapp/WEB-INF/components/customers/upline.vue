<template>
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
					  <li><a href="contactus.html" target="_blank">联系我们</a></li>
				  </ul>
				  <div class="input-group navbar-form navbar-left"  id="fl-home-search-input-group">
					  <input id="fl-home-search-input" type="text" class="form-control" placeholder="搜索 商品">
					  <span id="fl-home-search-btn" class="input-group-addon">
						  <span class="glyphicon glyphicon-search"></span>
					  </span>
				  </div>
				  <ul class="nav navbar-nav navbar-right">
					  <li id="user-1"><a href="login.html" target="_blank">登录</a></li>
					  <li id="user-2"><a href="reg.html" target="_blank">注册</a></li>
					  <li id="user-3" class="dropdown">
						  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="fl-user-name"></span>个人中心<span class="caret"></span></a>
						  <ul class="dropdown-menu">
							  <li><a href="myinfo.html">信息管理</a></li>
							  <li><a href="shoppingbag.html">菜篮子</a></li>
							  <li><a href="collection.html">收藏</a></li>
							  <li><a href="history.html">历史订单</a></li>
							  <li role="separator" class="divider"></li>
							  <li><a href="#" onclick="out()">安全退出</a></li>
						  </ul>
					  </li>
				  </ul>
			  </div>
		  </div>
 	  </nav>
 </div>
</template>

<script>
export default {
  name: 'upline'
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
