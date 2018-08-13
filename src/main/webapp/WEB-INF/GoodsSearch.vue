<template>
    <div id = "goodsSearch">
      <smallnavbar></smallnavbar>
      <leftmenufather></leftmenufather>
      <goodssearchfather></goodssearchfather>
    </div>
</template>

<script>
import mySmallNavBar from './components/commonComponents/smallNavBar.vue'
import mygoodsSearchFather from './components/goodsSearch/goodsSearchFather'
import myMenuFather from './components/index/myMenuFather'
    export default {
        name: "GoodsSearch",
      components: {
        smallnavbar: mySmallNavBar,
        goodssearchfather: mygoodsSearchFather,
        leftmenufather: myMenuFather
      }
    }
</script>

<style scoped>

</style>
