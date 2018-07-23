<template>
<div>
  <morenews :news="news"></morenews>
</div>
</template>

<script>
import mymoreNews from '../commonComponents/moreNews'
export default {
  name: 'moreNewsFather',
  components: {
    morenews: mymoreNews
  },
  data () {
    return {
      news: [
        {
          dataF: '这是第一个数据',
          dataS: '这是第二个数据',
          dataT: '这是第三个数据'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
