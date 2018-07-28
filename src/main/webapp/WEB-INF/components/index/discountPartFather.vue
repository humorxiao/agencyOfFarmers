<template>
  <!-- 优惠区模块 -->
    <discountpart :discountparts="discountparts"></discountpart>
</template>

<script>
import myDiscountPart from '../commonComponents/discountPart'
import axios from 'axios'
export default {
  name: 'discountPartFather',
  mounted: function () {
    axios.post('/api/getDiscountGoods', {}).then(response => {
     // alert(JSON.stringify(response.data))
      for (var i = 0; i < response.data.length; i++) {
       // alert(response.data[i].Goods_Picture)
        this.discountparts.push({
          id: response.data[i].Goods_PK,
          name: response.data[i].Goods_Name,
          prices: response.data[i].Goods_Price,
          pictureSrc: '../../../static/goodsImage/' + response.data[i].Goods_Picture
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      discountparts: []
    }
  },
  components: {
    discountpart: myDiscountPart
  }
}
</script>
<style scoped>

</style>
