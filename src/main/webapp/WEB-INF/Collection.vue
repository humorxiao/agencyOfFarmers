<template>
<div id ="collection">
  <smallnavbar></smallnavbar>
  <leftmenufather></leftmenufather>
  <collectionfather></collectionfather>
</div>
</template>

<script>
import mySmallNavBar from './components/commonComponents/smallNavBar.vue'
import myCollectionFather from './components/collection/collectionFather'
import myMenuFather from './components/index/myMenuFather'
export default {
  name: 'collection',
  components: {
    smallnavbar: mySmallNavBar,
    collectionfather: myCollectionFather,
    leftmenufather: myMenuFather
  }
}
</script>

<style scoped>

</style>
