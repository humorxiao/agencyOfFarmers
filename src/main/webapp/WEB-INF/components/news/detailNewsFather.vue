<!--新闻详情页——父组件-->
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
    axios.post('/api/getNewsNoPage').then(response => {
      for(var i = 0; i < response.data.length; i++){
        if(this.titleOfLink === response.data[i].News_PK){
          this.title = response.data[i].News_Title;
          this.timeAndSource = response.data[i].News_Time;
          this.content = response.data[i].News_Text;
        }
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      title: '',
      timeAndSource: '',
      content: '',
      titleOfLink:location.search.substr(1)
    }
  },
  components: {
    'detail': detail
  }
}
</script>

<style>
</style>
