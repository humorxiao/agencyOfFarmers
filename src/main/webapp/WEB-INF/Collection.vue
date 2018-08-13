<template>
<div id ="collection">
  <smallnavbar></smallnavbar>
  <collectionfather></collectionfather>
  <bottom></bottom>
</div>
</template>

<script>
import mySmallNavBar from './components/commonComponents/smallNavBar.vue'
import myCollectionFather from './components/collection/collectionFather'
import myBottom from './components/commonComponents/bottom.vue'
export default {
  name: 'collection',
  components: {
    smallnavbar: mySmallNavBar,
    collectionfather: myCollectionFather,
    bottom: myBottom
  }
}
</script>

<style scoped>

</style>
