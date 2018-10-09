<!--商品详情页——菜单导航父组件-->
<template>
  <div>
    <goods-menu :indexPage="indexPage" :goodClass="goodClass" :lastMenu = "lastMenu"></goods-menu>
  </div>
</template>

<script>
import goodsMenu from '../commonComponents/goods-menu.vue'
import axios from 'axios'
export default{
  name: 'GoodsMenu',
  data () {
    return {
      indexPage:'首页',
      goodClass:'',
      lastMenu: '',
      goods_pk: location.search.substr(1)
    }
  },
  components: {
    'goodsMenu': goodsMenu
  },
  mounted: function () {
    var goodsType = 0;
    this.Goods_pk = {'Goods_PK':this.goods_pk};
    axios.post('/api/getOneGood',this.Goods_pk).then(response => {
      this.lastMenu = response.data.Goods_Name;
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
      }else{
        alert('error')
      }
    }).catch(function (error) {
      console.log(error)
    })
  }
}
</script>
