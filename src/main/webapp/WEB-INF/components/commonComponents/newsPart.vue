<template>
  <!-- 新闻模块 -->
  <div id="fl-news">
    <div class="container-fluid fl-container">
      <div class="row">
        <div class="fl-home-model-title">
          平台新闻
        </div>
        <div class="fl-home-model-title-line">————</div>
      </div>
      <div id="fl-news-list" class="row">
        <div v-for="(item) in newsparts" :key="item.id">
        <div class="fl-news-col-item">
          <div class="col-md-4 fl-news-img-wrap">
            <img class="lazy fl-news-img img-thumbnail" width="248" height="185"  alt="" v-bind:src="item.pictureSrc" >
          </div>
          <div class="col-md-8">
            <ul>
              <li class="fl-news-title"><a href="item.src">{{item.newsTitle}}</a></li>
              <li class="fl-news-time">{{item. newsTime}}</li>
              <li class="fl-news-body">{{item.newsContent}}</li>
            </ul>
          </div>
        </div>
      </div>
      </div>
    </div>
    <div id="more_news">
      <a href="moreNews.html" target="_blank">更多</a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'newsPart',
  props: {
    newsparts: {
      type: Array,
      default: function () {
        return []
      }
    }
  }
}
</script>

<style scoped>
#more_news{
  width: 890px;
  margin: auto;
  padding-right: 15px;
  padding-left: 825px;
  font-size: medium;
}
#more_news a {
}
</style>
