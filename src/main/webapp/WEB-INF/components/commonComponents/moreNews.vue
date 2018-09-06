<!--新闻列表页子组件-->
<template>
  <div>
     <div id="top">
       <img src="../../../static/goodsImage/timg.jpg">
     </div>
  </div>
</template>

<script>
export default {
  name: 'moreNews',
  props: {
    news: {
      type: Array,
      default: function () {
        return []
      }
    }
  }
}
</script>

<style scoped>

</style>
