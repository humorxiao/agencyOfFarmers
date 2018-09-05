<template>
  <div>
    <goods-top-model :goodsMsg="goodsMsg" :imgUrl="imgUrl" :goodName="goodName" :price = "price" :number="number" :stock="stock" :Goods_pk="Goods_pk" @reduceNum="reduceNum" @addNum = "addNum" :isCollect="isCollect" :Collect_pk="Collect_pk" @alterCard="alterCard"></goods-top-model>
  </div>
</template>

<script>
  import goodsTopModel from '../commonComponents/goods-top-model.vue'
  import axios from 'axios'

  export default {
    name: 'GoodsTopModel',
    data () {
      return {
        goodsMsg: [],
        imgUrl: '',
        goodName: '',
        price: '',
        number: '0',
        stock: '',
        Goods_pk: '',
        isCollect: '',
        Collect_pk:'',
        goods_pk: location.search.substr(1)
      }
    },
    components: {
      'goodsTopModel': goodsTopModel
    },
    mounted: function () {
      this.Goods_pk = {'Goods_PK':this.goods_pk}
      //alert(JSON.stringify(this.Goods_pk))
      //获取该商品的信息
      axios.post('/api/getOneGood', this.Goods_pk).then(response => {
        //alert(JSON.stringify(response.data))
        var img
        this.imgUrl = '../../static/image/' + response.data.Goods_Picture
        this.goodName = response.data.Goods_Name
        this.price = response.data.Goods_Price
        this.stock = response.data.Goods_Num

      }).catch(function (error) {
        console.log(error)
      })

      //加入收藏夹
      var collectStatus
      var collect_pk
      axios.post('/api/getCollect', {}).then(Collectresponse => {
        if (Collectresponse.data.length === 0) {
          collectStatus = '加入收藏夹'
        } else {
          for (var j = 0; j < Collectresponse.data.length; j++) {
            if (this.goods_pk === Collectresponse.data[j].Goods_PK) {
              collectStatus = '取消收藏'
              collect_pk = Collectresponse.data[j].Collect_PK
              break
            } else {
              collectStatus = '加入收藏夹'
              collect_pk = ''
            }
          }
        }
        this.isCollect = collectStatus,
          this.Collect_pk = collect_pk
      }).catch(function (error) {
        console.log(error)
      })
    },
    methods: {
      reduceNum: function(){
        if(this.number > 0){
          this.number--;
        }
      },
      addNum: function () {
        if(this.number < this.stock){
          this.number++;
        }
      },
      alterCard: function () {
        
      }
    }
  }
</script>
