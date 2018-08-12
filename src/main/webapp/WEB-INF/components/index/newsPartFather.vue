<template>
  <!--新闻模块-->
    <newspart :newsparts="newsparts"></newspart>
</template>

<script>
import myNewsPart from '../commonComponents/newsPart'
import axios from 'axios'
export default {
  name: 'newsPartFather',
  mounted: function () {
    axios.post('/api/getNews', {}).then(response => {
      // console.log(JSON.stringify(response.data))
       for (var i = 0; i < response.data.length; i++) {
          this.newsparts.push({
            pictureSrc:'../../../static/goodsImage/'+ response.data[i].News_Picture,
            newsTitle: response.data[i].News_Title,
            newsTime: response.data[i].News_Timeb,
            newsContent: response.data[i].News_Text,
            src: 'details.html?'+ response.data[i].News_PK
          })
       }
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      newsparts: []
    }
  },
  components: {
    newspart: myNewsPart
  }
}
</script>

<style scoped>

</style>
