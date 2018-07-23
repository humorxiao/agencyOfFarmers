<template>
  <!-- 特色区模块 -->
  <div id="fl-special" class="fl-container">
    <div class="container-fluid fl-container-fluid">
      <div class="row">
        <div class="fl-home-model-title">
          特色区
        </div>
        <div class="fl-home-model-title-line">————</div>
      </div>
        <div v-for="(item) in specialparts" :key="item.id">
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
            <div class="fl-panel-like"><span class="glyphicon glyphicon-heart-empty" aria-hidden="true" @click = "addCollect(item.id)"></span> 收 藏</div>
          </div>
        </div>
    </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'specialPart',
  data () {
    return {
      login_status: '0'
    }
  },
  methods: {
    addCollect: function (goodsID) {
      axios.post('').then((response) => {
        console.log(response.status) // 判断登录状态
        this.login_status = response.status
      }).catch(function (error) {
        console.log(error)
      })
      if (this.login_status === 0) {
        window.location.href = 'login.html'
      } else {
        axios.post('', {goodsID}).then((response) => {
          console.log(response.status) // 发送收藏商品数据
          if (response.status.success) {
            this.collectSucced()
          } else {
            this.collectError()
          }
        }).catch(function (error) {
          console.log(error)
        })
      }
    },
    collectSucced () {
      alert('收藏成功！')
    },
    collectError () {
      alert('收藏失败！')
    }
  },
  props: {
    specialparts: {
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
