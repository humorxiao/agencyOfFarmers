<template>
<div>
<bag :goods_list="goods" :local_Goods_List="Goods_List" :local_Goods_Num="local_Goods_Num"></bag>
</div>
</template>

<script>
import bag from './bag.vue'
export default {
  name: 'Bagfather',
  components: {
    bag
  },
  data () {
    return {
      Goods_List: [],
      goods: [
        {name: '测试商品1', num: 1, price: 10.0, id: 0},
        {name: '测试商品2', num: 2, price: 20.0, id: 1},
        {name: '测试商品3', num: 3, price: 30.0, id: 2},
        {name: '测试商品4', num: 4, price: 40.0, id: 3}
      ],
      local_Cart_PK: [],
      local_Goods_Num: []
    }
  }
}
</script>

<style scoped>

</style>
