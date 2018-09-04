<template>
  <div>
    <goods-bottom-model :name = "name" :goodClass = "goodClass" :period="period" :flower = "flower" :result="result" :mature = "mature" :quality="quality" :msg="msg" :img1="img1" :img2 = "img2" :commandList = "commandList"></goods-bottom-model>
  </div>
</template>

<script>
import goodsBottomModel from '../commonComponents/goods-bottom-model.vue'
import axios from 'axios'
export default {
  name: 'GoodsBottomModel',
  data () {
    return {
      name: '',
      goodClass: '无',
      period: '无',
      flower: '',
      result: '',
      mature: '',
      quality: '',
      msg: '',
      img1: '../../static/image/yingzuitao.jpg',
      img2: '../../static/image/timg1.jpg',
      commandList: [],
      goods_pk: location.search.substr(1)
    }
  },
  components: {
    'goodsBottomModel': goodsBottomModel
  },
  mounted: function () {
    this.Goods_pk = {'Goods_PK':this.goods_pk}
    //alert(JSON.stringify(this.Goods_pk))
    axios.post('/api/getOneGood', this.Goods_pk).then(response => {
      //alert(JSON.stringify(response.data))
      this.name = response.data.Goods_Name
      //this.goodClass = response.data.
      //this.period = response.data.
      this.flower = response.data.Goods_Blossom
      this.result = response.data.Goods_Fruit
      this.mature = response.data.Goods_Mature
      this.quality = response.data.Goods_Expiration
      this.msg = response.data.Goods_Reserve_1

    }).catch(function (error) {
      console.log(error)
    })
    axios.post('/api/getGoodsComments', this.Goods_pk).then(response => {
      for (var i = 0; i < response.data.length; i++) {
        this.commandList.push({
          useName: response.data[i].User_PK,
          content: response.data[i].Comm_Text,
          stars: response.data[i].Comm_Rank,
          time: response.data[i].Comm_Time
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  methods: {

  }
}
</script>
