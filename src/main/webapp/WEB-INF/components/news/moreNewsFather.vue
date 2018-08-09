<template>
  <div id="morenews">
    <news :newlists = "newlists" :pageLists = "pageLists" @switchPage = "switchPage" @lastPage = "lastPage"></news>
    <router-view/>
  </div>
</template>

<script>
import news from '../../components/commonComponents/news'
import axios from 'axios'
export default {
  name: 'News',
  data() {
    return {
      newlists: [],
      pageLists: [],
      page: '1'
    }
  },
  components: {
    'news': news
  },
  methods: {
    switchPage: function (data) {
      var NumPerPage = 2
      var Page = data
      this.page = data
      axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
        for(var j = 0; j < NumPerPage; j++){
          this.newlists.pop()
        }
        for (var i = 1; i < response.data.length; i++) {
          this.newlists.push({
            title: response.data[i].News_Title,
            time: response.data[i].News_Time,
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    lastPage: function () {
      var NumPerPage = 2
      var Page = this.page - 1
      axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
        for(var j = 0; j < NumPerPage; j++){
          this.newlists.pop()
        }
        for (var i = 1; i < response.data.length; i++) {
          this.newlists.push({
            title: response.data[i].News_Title,
            time: response.data[i].News_Time,
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
    }
  },
  mounted: function() {
      var NumPerPage = 2
      var Page = this.page
      axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
        for (var i = 1; i < response.data.length; i++) {
          this.newlists.push({
            title: response.data[i].News_Title,
            time: response.data[i].News_Time,
          })
        }
        for (var j = 0; j < response.data[0].PageNum; j++) {
          this.pageLists.push({
            page: j + 1
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
    }
}
</script>

<style>

</style>
