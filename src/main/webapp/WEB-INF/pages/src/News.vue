<template>
<div id = "morenews">
  <morenewsfather></morenewsfather>
  <router-view/>
</div>
</template>

<script>
import mymoreNewsFather from './components/news/moreNewsFather'
export default {
  name: 'moreNews',
  components: {
    morenewsfather: mymoreNewsFather
  }
}
</script>

<style scoped>

</style>
