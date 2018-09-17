<template>
<div>
<bag :goods_list="goods" :local_Goods_List="Goods_List"></bag>
</div>
</template>

<script>
import bag from './bag.vue'
import axios from 'axios'
export default {
  name: 'Bagfather',
  components: {
    bag
  },
  data () {
    return {
      Goods_List: [],
      goods: []
    }
  },
  mounted: function () {
    axios.post('/api/getCart', {}).then(response => {
      console.log(response.data)
      for (var i = 0; i < response.data.length; i++) {
        this.goods.push({
          name: response.data[i].Goods_Name,
          num: response.data[i].Goods_Num,
          price: response.data[i].Goods_Price,
          id: response.data[i].Goods_PK
        })
        console.log(response.data)
      }
      this.Goods_List = this.goods
    }).catch(function (error) {
      console.log(error)
    })
  }
}
</script>

<style scoped>

</style>
