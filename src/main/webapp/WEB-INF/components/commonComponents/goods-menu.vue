<!--商品详情页——菜单导航子组件-->
<template>
  <!-- 商品目录 -->
  <div id="fl-goods-menu" class="fl-container">
    <ol class="breadcrumb">
      <li><a href="index.html">{{indexPage}}</a></li>
      <li>{{goodClass}}</li>
      <li class = "active">{{lastMenu}}</li>
    </ol>
  </div>
</template>

<script>
export default{
  props: {
    indexPage: {
      type: String,
      required: true
    },
    goodClass: {
      type: String,
      required: true
    },
    lastMenu: {
      type: String,
      required: true
    }
  }
}
</script>
