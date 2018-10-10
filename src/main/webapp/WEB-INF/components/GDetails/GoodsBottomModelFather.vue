<!--商品详情页——底部父组件-->
<template>
  <div>
    <goods-bottom-model :name = "name" :goodClass = "goodClass" :period="period" :flower = "flower" :result="result" :mature = "mature" :quality="quality" :msg="msg" :imgList="imgList" :commandList = "commandList"></goods-bottom-model>
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
        goodClass: '',
        period: '无',
        flower: '',
        result: '',
        mature: '',
        quality: '',
        msg: '',
        imgList: [],
        commandList: [],
        goods_pk: location.search.substr(1)
      }
    },
    components: {
      'goodsBottomModel': goodsBottomModel
    },
    mounted: function () {
      var goodsType = 0;
      this.Goods_pk = {'Goods_PK':this.goods_pk};
      axios.post('/api/getOneGood', this.Goods_pk).then(response => {
        this.name = response.data.Goods_Name;
        goodsType = response.data.Goods_Type;
        if(goodsType === 1){
          this.goodClass = '水果';
        }else if(goodsType === 2){
          this.goodClass = '加工品'
        }else if(goodsType === 3){
          this.goodClass = '粮蔬'
        }else if(goodsType === 4){
          this.goodClass = '水产'
        }else if(goodsType === 5){
          this.goodClass = '禽畜'
        }else if(goodsType === 6){
          this.goodClass = '植物'
        }
        //this.goodClass = response.data.
        //this.period = response.data.
        this.flower = response.data.Goods_Blossom;
        this.result = response.data.Goods_Fruit;
        this.mature = response.data.Goods_Mature;
        this.quality = response.data.Goods_Expiration;
        this.msg = response.data.Goods_Reserve_1;
        var imgs = response.data.Goods_Reserve_2;
        var arr = imgs.split("#");
        for(var i = 0; i < arr.length; i++){
          this.imgList.push({
            img: "../../static/image/" + arr[i]
          })
        }
        //alert(arr)
        //alert(arr[0])
      }).catch(function (error) {
        console.log(error)
      });
      axios.post('/api/getGoodsComments', this.Goods_pk).then(response => {
        for (var i = 0; i < response.data.length; i++) {
          var starList = [];
          var starOfRank = [];
          starList[i] = response.data[i].Comm_Rank;
          starOfRank[i] = '';
          for(var j = 0; j < starList[i]; j++){
            starOfRank[i] = starOfRank[i] + '★';
          }
          //alert(starOfRank[0])
          this.commandList.push({
            userName: response.data[i].User_Name,
            content: '评价:  ' + response.data[i].Comm_Text,
            stars: starOfRank[i],
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
