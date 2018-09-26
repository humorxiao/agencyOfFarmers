<template>
  <s_adminuserandnewspart :tableData="tableData"></s_adminuserandnewspart>
</template>

<script>
import mys_adminUserAndNews from '../commonComponents/three_adminUserAndNews'
import axios from 'axios'
export default {
    name: "s_adminUserAndNewsFather",
  mounted: function () {
      axios.post('/api/getNewsNoPage', {}).then(response => {
      // console.log(JSON.stringify(response.data))
      for (var i = 0; i < response.data.length; i++) {
        this.tableData.push({
          name: response.data[i].News_Title,
          date: response.data[i].News_Time,
          url: 'details.html?'+ response.data[i].News_PK,
          news_pk: response.data[i].News_PK,
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
   data () {
    return {
     tableData: []
    }
  },
    components: {
      s_adminuserandnewspart: mys_adminUserAndNews
    }
}
</script>

<style scoped>

</style>
