<template>
  <mymenu :menus="menus"></mymenu>
</template>

<script>
import mymyMenu from '../commonComponents/myMenu'
import axios from 'axios'
export default {
  name: 'myMenuFather',
  mounted: function () {
    axios.post('/api/getAllGoods', {}).then(response => {
      //  alert(JSON.stringify(response.data))
      for (var i = 0; i < response.data.length; i++) {
        if (response.data[i].Goods_Type === 1) {
          this.menus.push({
            fruit: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        } else if (response.data[i].Goods_Type === 2) {
          this.menus.push({
            processedGoods: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        } else if (response.data[i].Goods_Type === 3) {
          this.menus.push({
            vegetable: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        } else if (response.data[i].Goods_Type === 4) {
          this.menus.push({
            fish: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        } else if (response.data[i].Goods_Type === 5) {
          this.menus.push({
            livestock: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        } else if (response.data[i].Goods_Type === 6) {
          this.menus.push({
            plant: response.data[i].Goods_Name,
            src: 'goodsDetails.html?'+ response.data[i].Goods_PK
          })
        }
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      menus: []
    }
  },
  components: {
    mymenu: mymyMenu
  }
}
</script>

<style scoped>

</style>
