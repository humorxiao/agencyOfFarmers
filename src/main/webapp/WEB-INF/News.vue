<template>
  <div id="morenews">
    <morenewsfather></morenewsfather>
    <bottom></bottom>
    <router-view/>
  </div>
</template>

<script>
import moreNewsFather from './components/news/moreNewsFather.vue'
import myBottom from './components/commonComponents/bottom'
export default {
  name: 'News',
  components: {
    bottom: myBottom,
    morenewsfather: moreNewsFather
  }
}

</script>

<style>

</style>
