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
            <td colspan="2" id="fl-goods-table-name" >{{goodName}}</td>
          </tr>
          <tr>
            <td id="fl-goods-table-price-label" class="fl-goods-table-item-name">价格</td>
            <td id="fl-goods-table-price">{{price}}</td>
          </tr>
          <tr>
            <td id="fl-goods-buying-number-label" class="fl-goods-table-item-name">数量</td>
            <td>
              <button id="fl-goods-minus" class="btn btn-default" @click = "reduceNum">-</button>
              <span id="fl-goods-buying-number">{{number}}</span>
              <button id="fl-goods-plus" class="btn btn-default" @click = "addNum">+</button>
            </td>
          </tr>
          <tr>
            <td class="fl-goods-table-item-name">库存量</td>
            <td>{{stock}}</td>
          </tr>
          <tr>
            <td colspan="2">
              <button class="btn btn-success" @click="alterCard">加入菜篮子</button>
              <button class="btn btn-primary" @click="Collect(Goods_pk,isCollect,Collect_pk)">{{isCollect}}</button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    props: {
      imgUrl: {
        type: String,
        required: true,
        default: function () {
          return ''
        }
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
      },
      isCollect: {
        type: String,
        required:true
      },
      Collect_pk: {
        type: String,
        required: true
      },
      Goods_pk: {
        type: String,
        required: true
      }
    },
    data() {
      return {
        login_status: '0',
        goods_pk: '',
        collect_pk: '',
        loginStatus: 0
      }
    },
    methods: {
      reduceNum: function () {
        this.$emit('reduceNum')
      },
      addNum: function () {
        this.$emit('addNum')
      },
      Collect: function (goodsID,goodsCollectStatus, collectPk) {
        axios.post('/api/checkLoginRank').then((response) => {
          console.log(response.status) // 判断登录状态
          if (response.data.status === 0) {
            this.info('请先登录')
          } else if (response.data.status === 1) {
            if (goodsCollectStatus === '加入收藏夹') {
              axios.post('/api/addCollect', goodsID).then((response) => {
                this.Collect_pk = response.data.Collect_PK
                this.isCollect = '取消收藏'
              }).catch(function (error) {
                console.log(error)
              })
            }
            this.collect_pk = {'Collect_PK': collectPk}
            if (goodsCollectStatus === '取消收藏') {
              axios.post('/api/removeCollect', this.collect_pk).then((response) => {
                this.isCollect = '加入收藏夹'
              }).catch(function (error) {
                console.log(error)
              })
            }
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      info: function (msg) {
        this.$message.error(msg)
      },
      alterCard: function () {
        axios.post('/api/checkLoginRank').then((response) => {
          if (response.data.status === 0) {
            this.info('请先登录')
            this.loginStatus = 0;
          } else if (response.data.status === 1) {
            this.loginStatus = 1;
          }
          this.$emit('alterCard',this.loginStatus);
        }).catch(function (error) {
          console.log(error)
        })
      }
    }
  }
</script>
