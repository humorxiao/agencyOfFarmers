<template>
    <!-- 特色区模块 -->
      <specialpart :specialparts="specialparts"></specialpart>
</template>

<script>
import mySpecialPart from '../commonComponents/specialPart'
import axios from 'axios'
export default {
  name: 'specialPartFather',
  mounted: function () {
    axios.post('/api/getSpecialGoods', {}).then(response => {
      for (var i = 0; i < response.data.length; i++) {
        this.specialparts.push({
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
      specialparts: []
    }
  },
  components: {
    specialpart: mySpecialPart
  }
}
</script>
<style scoped>

</style>
