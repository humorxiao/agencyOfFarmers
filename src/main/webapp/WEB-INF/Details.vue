<template>
  <div id="details">
    <detailnewsfather></detailnewsfather>
    <bottom></bottom>

  </div>
</template>

<script>
import myBottom from './components/commonComponents/bottom'
import detailNewsFather from './components/news/detailNewsFather.vue'
export default {
  name: 'Details',
  components: {
    bottom: myBottom,
    detailnewsfather: detailNewsFather
  }
}
</script>

<style>
</style>
