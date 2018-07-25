<template>
    <!-- 特色区模块 -->
      <specialpart :specialparts="specialparts"></specialpart>
</template>

<script>
import mySpecialPart from '../commonComponents/specialPart'
import axios from 'axios'
export default {
  name: 'specialPartFather',
  data () {
    axios.post('/api/getSpecialGoods', {}).then(response => {
      for (var i = 0; i < response.data.length; i++) {
        this.specialparts.push({
          id: response.data[i].Goods_PK,
          name: response.data[i].Goods_Name,
          prices: response.data[i].Goods_Price,
          src: '',
          pictureSrc: require('../../../static/goodsImage/' + response.data[0].Goods_Picture)
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
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
