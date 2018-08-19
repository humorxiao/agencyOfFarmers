<template>
    <div id = "goodsSearch">
      <smallnavbar></smallnavbar>
      <leftmenufather></leftmenufather>
      <goodssearchfather></goodssearchfather>
      <bottom></bottom>
    </div>
</template>

<script>
import mySmallNavBar from './components/commonComponents/smallNavBar.vue'
import mygoodsSearchFather from './components/goodsSearch/goodsSearchFather'
import myMenuFather from './components/index/myMenuFather'
import myBottom from './components/commonComponents/bottom.vue'
export default {
  name: 'GoodsSearch',
  components: {
    smallnavbar: mySmallNavBar,
    goodssearchfather: mygoodsSearchFather,
    leftmenufather: myMenuFather,
    bottom: myBottom
  }
}
</script>

<style scoped>

</style>
