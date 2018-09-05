<!--商品详情页——顶部父组件-->
<template>
  <div>
    <goods-top-model :goodsMsg="goodsMsg" :imgUrl="imgUrl" :goodName="goodName" :price = "price" :number="number" :stock="stock" :Goods_pk="Goods_pk" @reduceNum="reduceNum" @addNum = "addNum" :isCollect="isCollect" :Collect_pk="Collect_pk" @alterCard="alterCard" :addCard="addCard"></goods-top-model>
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
        number: '1',
        stock: '',
        Goods_pk: '',
        isCollect: '',
        Collect_pk:'',
        addCard: '加入菜篮子',
        goods_pk: location.search.substr(1)
      }
    },
    components: {
      'goodsTopModel': goodsTopModel
    },
    mounted: function () {
      this.Goods_pk = {'Goods_PK':this.goods_pk}
      //获取该商品的信息
      axios.post('/api/getOneGood', this.Goods_pk).then(response => {
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
      //减少购买数量
      reduceNum: function(){
        if(this.number > 1){
          this.number--;
        }
      },
      //增加购买数量
      addNum: function () {
        if(this.number < this.stock){
          this.number++;
        }
      },
      //加入菜篮子
      alterCard: function (loginStatus) {
        //先判断用户是否已登录
        axios.post('/api/checkLoginRank').then((response) => {
          if (response.data.status === 0) {
            this.info('请先登录')
            loginStatus = response.data.status;
          } else if (response.data.status === 1) {
            var goodsPk = {'Goods_List':this.goods_pk,'Goods_Num':this.number}
            axios.post('/api/alterCart', goodsPk).then(response => {
              if(response.data.status === 1){
                this.ifo('成功加入菜篮子');
              }else{
                this.info('加入菜篮子失败');
              }
            }).catch(function (error) {
              console.log(error)
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      //出错提示
      info: function (msg) {
        this.$message.error(msg)
      },
      //成功提示
      ifo:function (msg) {
        this.$message.success(msg)
      }
    }
  }
</script>
