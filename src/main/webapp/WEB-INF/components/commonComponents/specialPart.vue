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
        <div v-for="(item,index) in specialparts" :key="item.id">
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
            <div class="fl-panel-like" @click = "Collect(item.id,item.isCollect,item.Collect_pk,index)"><span class="glyphicon glyphicon-heart-empty" aria-hidden="true" ></span>{{item.isCollect}}</div>
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
      login_status: '0',
      goods_pk: '',
      collect_pk: ''
    }
  },
  methods: {
   Collect: function (goodsID,goodsCollectStatus,collectPk,index) {
      // alert(goodsID)
      // axios.post('').then((response) => {
      //   console.log(response.status) // 判断登录状态
      //   this.login_status = response.status
      // }).catch(function (error) {
      //   console.log(error)
      // })
      this.goods_pk = {'Goods_PK': goodsID}
      if(goodsCollectStatus === '收藏') {
        axios.post('/api/addCollect',this.goods_pk).then((response) => {
          // console.log(response.data)
          this.specialparts[index].Collect_pk = response.data.Collect_PK
          alert(response.data.Collect_PK + '1')
          this.specialparts[index].isCollect = '取消收藏'
        }).catch(function (error) {
          console.log(error)
        })
      }
      this.collect_pk = {'Collect_PK':collectPk}
      if(goodsCollectStatus === '取消收藏') {
        axios.post('/api/removeCollect',this.collect_pk).then((response) => {
        //  console.log(response.data)
          this.specialparts[index].isCollect = '收藏'
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
