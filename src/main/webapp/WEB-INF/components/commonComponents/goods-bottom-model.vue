<!--商品详情页——底部子组件-->
<template>
  <!-- 商品详情、评论模块 -->
  <div id="fl-goods-bottom-model" class="container fl-container">
    <ul class="nav nav-tabs">
      <li id="fl-goods-bottom-detail-btn" role="presentation" class="active" onclick="triggleDetailAndComment('detail')"><a href="#none">详情</a></li>
      <li id="fl-goods-bottom-commemt-btn" role="presentation" onclick="triggleDetailAndComment('comment')"><a href="#none">评价</a></li>
    </ul>
    <div id="fl-goods-bottom-detail">
      <div id="fl-goods-bottom-detail-description">
        <div class="row">
          <div class="col-md-4">
            名称:<span>{{name}}</span>
          </div>
          <div class="col-md-4">
            类别:<span>{{goodClass}}</span>
          </div>
          <div class="col-md-4">
            生长周期:<span>{{period}}</span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            开花期:<span>{{flower}}</span>
          </div>
          <div class="col-md-4">
            挂果期:<span>{{result}}</span>
          </div>
          <div class="col-md-4">
            成熟期:<span>{{mature}}</span>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            保质期:<span>{{quality}}</span>
          </div>
          <div class="col-md-4">
            详情:<span>{{msg}}</span>
          </div>
        </div>
      </div>
      <div id="fl-goods-bottom-detail-pictures">
        <ul v-for="item in imgList" :key="item.id">
          <li><img :src="item.img" alt=""></li>
        </ul>
      </div>
    </div>
    <div id="fl-goods-bottom-commemt">
      <ul>
        <li v-for = "item in commandList" :key="item.id">
          <div class="fl-goods-bottom-commemt-name">{{item.userName}}</div>
          <div class="fl-goods-bottom-commemt-body">{{item.content}}</div>
          <div class="fl-goods-bottom-commemt-rank">{{item.stars}}</div>
          <div class="fl-goods-bottom-commemt-time">{{item.time}}</div>
          <ul class="fl-goods-bottom-commemt-rely">
            <div class="fl-goods-bottom-commemt-rely-name" >{{item.seller}}</div>
            <div class="fl-goods-bottom-commemt-rely-body">{{item.reply}}</div>
            <div class="fl-goods-bottom-commemt-rely-time">{{item.replyTime}}</div>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default{
  props: {
    name: {
      type: String,
      required: true
    },
    goodClass: {
      type: String,
      required: true
    },
    period: {
      type: String,
      required: true
    },
    flower: {
      type: String,
      required: true
    },
    result: {
      type: String,
      required: true
    },
    mature: {
      type: String,
      required: true
    },
    quality: {
      type: String,
      required: true
    },
    msg: {
      type: String,
      required: true
    },
    imgList: {
      type: Array,
      required: true
    },
    img1: {
      type: String,
      required: true
    },
    img2: {
      type: String,
      required: true
    },
    commandList: {
      type: Array,
      required: true
    }
  }
}
</script>
