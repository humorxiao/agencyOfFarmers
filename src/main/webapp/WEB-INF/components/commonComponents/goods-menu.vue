<template>
  <!-- 商品目录 -->
  <div id="fl-goods-menu" class="fl-container">
    <ol class="breadcrumb">
      <li v-for = "item in menuList" :key="item.id"><a href="#">{{item.address}}</a></li>
      <li class = "active">{{lastMenu}}</li>
    </ol>
  </div>
</template>

<script>
export default{
  props: {
    menuList: {
      type: Array,
      required: true
    },
    lastMenu: {
      type: String,
      required: true
    }
  }
}
</script>
