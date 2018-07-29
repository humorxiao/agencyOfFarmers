<template>
  <div id="morenews">
    <news :newlists = "newlists"></news>
    <router-view/>
  </div>
</template>

<script>
import news from '../../components/commonComponents/news'
export default {
  name: 'News',
  data () {
    return {
      newlists: [
        {
          title: '我是新闻标题1', time: '2018-07-15'
        },
        {
          title: '我是新闻标题2', time: '2018-07-21'
        }
      ]
    }
  },
  components: {
    'news': news
  }
}
/* axios.post('api/getNews', {}).then(response => {
  // alert(response.data)
  console.log('shoudao数据成功!')
}).catch(function (error) {
  console.log(error)
})  */
</script>

<style>

</style>
