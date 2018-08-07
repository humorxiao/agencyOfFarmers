<template>
  <div>
   <goods-top-model :imgUrl="imgUrl" :goodName="goodName" :price = "price" :number="number" :stock="stock"></goods-top-model>
  </div>
</template>

<script>
import goodsTopModel from '../commonComponents/goods-top-model.vue'
export default {
  name: 'GoodsTopModel',
  data () {
    return {
      imgUrl: '../../static/image/taozi.png',
      goodName: '鹰嘴桃',
      price: '￥ 66元',
      number: '3',
      stock: '10000'
    }
  },
  components: {
    'goodsTopModel': goodsTopModel
  }
}
</script>
