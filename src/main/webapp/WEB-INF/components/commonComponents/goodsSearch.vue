<template>
  <div id="fl-special" class="fl-container">
    <div class="container-fluid fl-container-fluid">
      <div class="row">
        <div class="fl-home-model-title-search">
          搜索结果
        </div>
        <div class="fl-home-model-title-line">————</div>
      </div>
      <div v-for="(item, index) in goodsSearchs" :key="item.id">
        <div class="col-md-4">
          <div class="panel panel-default fl-panel">
            <a :href="item.src" target="_blank">
              <div class="fl-home-goods-img">
                <img class="lazy" width="248" height="185"  v-bind:src="item.pictureSrc"  alt="item.name">
              </div>
              <ul class="list-group">
                <li class="list-group-item fl-list-group-item fl-home-goods-name">{{item.name}}</li>
                <li class="list-group-item fl-list-group-item fl-home-price">{{item.prices}}</li>
              </ul>
            </a>
            <div class="fl-panel-like" @click = "removeCollect(item.collectPK,item.id,item.name,index)"><span class="glyphicon glyphicon-heart-empty" aria-hidden="true" ></span>移除</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
      name: "goodsSearch",
      props: {
        goodsSearchs: {
          type: Array,
          default: function () {
            return []
          }
        }
      },
    }
</script>

<style scoped>
  .fl-home-model-title-search{
    padding: 50px;
    padding-top: 80px;
    padding-bottom: 5px;
    text-align: center;
    font-size: 24px;
    font-weight: 500;
  }
  .col-md-4{
    margin-top: 30px;
  }

</style>
