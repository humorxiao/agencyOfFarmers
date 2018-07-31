<template>
  <!-- 优惠区模块 -->
  <div id="fl-discount">
  <div  class="fl-container">
    <div class="container-fluid fl-container-fluid">
      <div class="row">
        <div class="fl-home-model-title">
          优惠区
        </div>
        <div class="fl-home-model-title-line">————</div>
      </div>
      <div v-for="(item) in discountparts" :key="item.id">
        <div class="col-md-4">
          <div class="panel panel-default fl-panel">
            <a href="item.src" target="_blank">
              <div class="fl-home-goods-img">
                <img class="lazy" width="248" height="185"  v-bind:src="item.pictureSrc"  alt="item.name">
              </div>
              <ul class="list-group">
                <li class="list-group-item fl-list-group-item fl-home-goods-name">{{item.name}}</li>
                <li class="list-group-item fl-list-group-item fl-home-price">{{item.prices}}</li>
              </ul>
            </a>
            <div class="fl-panel-like"><span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span> 收 藏</div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: 'discountPart',
  props: {
    discountparts: {
      type: Array,
      default: function () {
        return []
      }
    }
  }
}
</script>

<style scoped>
  .col-md-4{
    margin-top: 30px;
  }
</style>
