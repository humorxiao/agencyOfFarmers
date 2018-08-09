<!--新闻列表页——父组件-->
<template>
  <div id="morenews">
    <news :newlists = "newlists" :pageLists = "pageLists" @switchPage = "switchPage" @lastPage = "lastPage"></news>
    <router-view/>
  </div>
</template>

<script>
  import news from '../commonComponents/news'//导入子组件
  import axios from 'axios'
  export default {
    name: 'News',
    data() {
      return {
        newlists: [],              //存储新闻列表标题、日期的数组
        pageLists: [],             //存储分页列表的数组
        page: '1',                 //打开新闻列表页，显示分页的第1页
        nowInPage: '',             //从后台拿取当前分页
        pageNum: ''                //从后台拿取分页的总页数
      }
    },
    components: {
      'news': news
    },
    methods: {
      //转换分页
      switchPage: function (data) {
        var NumPerPage = 2         //每个分页的新闻条数
        var Page = data            //从子组件拿取用户点击的分页
        this.page = data
        axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
          this.nowInPage = response.data[0].NowPage
          for(var j = 0; j < NumPerPage; j++){
            this.newlists.pop()
          }
          for (var i = 1; i < response.data.length; i++) {
            this.newlists.push({
              title: response.data[i].News_Title,
              time: response.data[i].News_Time,
              src: 'details.html?'+response.data[i].News_PK
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      //上一分页
      lastPage: function () {
        var NumPerPage = 2
        var Page = this.nowInPage - 1
        if(Page > 0){
          axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
            this.nowInPage = response.data[0].NowPage
            for(var j = 0; j < NumPerPage; j++){
              this.newlists.pop()
            }
            for (var i = 1; i < response.data.length; i++) {
              this.newlists.push({
                title: response.data[i].News_Title,
                time: response.data[i].News_Time,
                src: 'details.html?'+response.data[i].News_PK
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      //下一分页
      nextPage: function () {
        var NumPerPage = 2
        var Page = this.nowInPage + 1
        if(Page <= this.pageNum){
          axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
            this.nowInPage = response.data[0].NowPage
            for(var j = 0; j < NumPerPage; j++){
              this.newlists.pop()
            }
            for (var i = 1; i < response.data.length; i++) {
              this.newlists.push({
                title: response.data[i].News_Title,
                time: response.data[i].News_Time,
                src: 'details.html?'+response.data[i].News_PK
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      toNewsDetails: function () {

      }
    },
    //打开新闻列表页，向后台请求数据
    mounted: function() {
      var NumPerPage = 2
      var Page = this.page
      axios.post('/api/getBulletin', {Page, NumPerPage}).then(response => {
        this.nowInPage = response.data[0].NowPage
        this.pageNum = response.data[0].PageNum
        for (var i = 1; i < response.data.length; i++) {
          this.newlists.push({
            title: response.data[i].News_Title,
            time: response.data[i].News_Time,
            src: 'details.html?'+response.data[i].News_PK
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
