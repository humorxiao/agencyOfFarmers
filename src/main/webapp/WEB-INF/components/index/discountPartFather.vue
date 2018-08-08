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
    var collectStatus
    var collect_pk
    axios.post('/api/getCollect', {}).then(Collectresponse => {
      axios.post('/api/getDiscountGoods', {}).then(Discountresponse => {
        // alert(JSON.stringify(response.data))
        for (var i = 0; i < Discountresponse.data.length; i++) {

          if(Collectresponse.data.length === 0) {
            collectStatus = '收藏'
          } else {
            for(var j = 0; j < Collectresponse.data.length; j++) {
              if(Discountresponse.data[i].Goods_PK === Collectresponse.data[j].Goods_PK) {
                collectStatus = '取消收藏'
                collect_pk =  Collectresponse.data[j].Collect_PK
                break;
              } else {
                collectStatus = '收藏'
                collect_pk = ''
              }
            }
          }


          // alert(response.data[i].Goods_Picture)
          this.discountparts.push({
            id: Discountresponse.data[i].Goods_PK,
            isCollect: collectStatus,
            name: Discountresponse.data[i].Goods_Name,
            prices: '¥ '+ Discountresponse.data[i].Goods_Price,
            pictureSrc: '../../../static/goodsImage/' + Discountresponse.data[i].Goods_Picture,
            Collect_pk:  collect_pk,
            src: 'goodsDetails.html?'+ Discountresponse.data[i].Goods_PK
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
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
