<template>
  <div id="details">
    <detail :title="title" :timeAndSource="timeAndSource" :content="content"></detail>
    <router-view/>
  </div>
</template>

<script>
import detail from '../commonComponents/detail.vue'
import axios from 'axios'
export default {
  name: 'detailNewsFather',
  mounted: function () {
    axios.post('/api/getNewsNoPage', {}).then(response => {
      alert(JSON.stringify(response.data))
        // alert(response.data[i].Goods_Picture)
        this.discountparts.push({
          title: response.data.News_Title,
          timeAndSource: response.data.News_Time,
          content: response.data.News_Text,
        })
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      title: '',
      timeAndSource: '',
      content: ''
    }
  },
  components: {
    'detail': detail
  }
}
</script>

<style>
</style>
