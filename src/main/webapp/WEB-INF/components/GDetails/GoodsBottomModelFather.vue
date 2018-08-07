<template>
  <div>
    <goods-bottom-model :name = "name" :goodClass = "goodClass" :period="period" :flower = "flower" :result="result" :mature = "mature" :quality="quality" :msg="msg" :img1="img1" :img2 = "img2" :commandList = "commandList"></goods-bottom-model>
  </div>
</template>

<script>
import goodsBottomModel from '../commonComponents/goods-bottom-model.vue'
export default {
  name: 'GoodsBottomModel',
  data () {
    return {
      name: '鹰（省内）',
      goodClass: '水果',
      period: '一年一季',
      flower: '5',
      result: '4-6月份',
      mature: '7月份',
      quality: '5-7天',
      msg: '每箱8斤',
      img1: '../../static/image/yingzuitao.jpg',
      img2: '../../static/image/timg1.jpg',
      commandList: [
        {userName: '用户名1', content: 'ws评论内容1,这是评论内容，它超级长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长长', stars: '★★★★', time: '2017-5-25 1:05:35'},
        {userName: '用2', content: '评论内容2,它不太长长长长长长长长长长长长长长长长', stars: '★★★', time: '2017-5-10 1:05:35', seller: '我是商家', reply: '你的评论真的很长', replyTime: '2017-5-25 13:34:35'},
        {userName: '用户名3', content: '评论内容34567', stars: '★★', time: '2017-5-20 9:20:10'}
      ]
    }
  },
  components: {
    'goodsBottomModel': goodsBottomModel
  }
}
</script>
