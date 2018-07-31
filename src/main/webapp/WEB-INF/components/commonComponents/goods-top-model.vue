<template>
  <!-- 商品价格、数量模块 -->
  <div id="fl-goods-top-model" class="container fl-container">
    <div class="row">
      <div id="fl-goods-top-img" class="col-md-6">
        <img :src = "imgUrl" alt="">
      </div>
      <div id="fl-goods-top-detail" class="col-md-6">
        <table class="table">
          <tr>
            <td colspan="2" id="fl-goods-table-name">{{goodName}}</td>
          </tr>
          <tr>
            <td id="fl-goods-table-price-label" class="fl-goods-table-item-name">价格</td>
            <td id="fl-goods-table-price">{{price}}</td>
          </tr>
          <tr>
            <td id="fl-goods-buying-number-label" class="fl-goods-table-item-name">数量</td>
            <td>
              <button id="fl-goods-minus" class="btn btn-default">-</button>
              <span id="fl-goods-buying-number">{{number}}</span>
              <button id="fl-goods-plus" class="btn btn-default">+</button>
            </td>
          </tr>
          <tr>
            <td class="fl-goods-table-item-name">库存量</td>
            <td>{{stock}}</td>
          </tr>
          <tr>
            <td colspan="2">
              <button class="btn btn-success">加入菜篮子</button>
              <button class="btn btn-primary">加入收藏夹</button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default{
  props: {
    imgUrl: {
      type: String,
      required: true
    },
    goodName: {
      type: String,
      required: true
    },
    price: {
      type: String,
      required: true
    },
    number: {
      type: String,
      required: true
    },
    stock: {
      type: String,
      required: true
    }
  }
}
</script>
