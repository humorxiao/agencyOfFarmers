<!--商品详情_父组件-->
<template>
  <div id="goodsDetails">
    <nav-bar></nav-bar>
    <my-menu-father></my-menu-father>
    <GoodsMenu></GoodsMenu>
    <goods-top-model></goods-top-model>
    <goods-bottom-model></goods-bottom-model>
    <bottom></bottom>

  </div>
</template>

<script>
import navBar from './components/commonComponents/smallNavBar.vue'
import myMenuFather from './components/index/myMenuFather.vue'
import GoodsMenu from './components/GDetails/GoodsMenuFather.vue'
import GoodsTopModel from './components/GDetails/GoodsTopModelFather.vue'
import GoodsBottomModel from './components/GDetails/GoodsBottomModelFather.vue'
import bottom from './components/commonComponents/bottom.vue'
export default {
  name: 'goodsDetails',
  data () {
    return {
    }
  },
  components: {
    'navBar': navBar,
    'myMenuFather': myMenuFather,
    'GoodsMenu': GoodsMenu,
    'GoodsTopModel': GoodsTopModel,
    'GoodsBottomModel': GoodsBottomModel,
    'bottom': bottom
  }
}
</script>

<style>

</style>
