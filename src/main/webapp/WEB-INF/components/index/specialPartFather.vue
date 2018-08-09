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
    var collectStatus
    var collect_pk
    axios.post('/api/getCollect', {}).then(Collectresponse => {
      axios.post('/api/getSpecialGoods', {}).then(Specialresponse => {
      for (var i = 0; i < Specialresponse.data.length; i++) {
        if(Collectresponse.data.length === 0) {
          collectStatus = '收藏'
        } else {
          for(var j = 0; j < Collectresponse.data.length; j++) {
            if(Specialresponse.data[i].Goods_PK === Collectresponse.data[j].Goods_PK) {
              collectStatus = '取消收藏'
              collect_pk =  Collectresponse.data[j].Collect_PK
              break;
            } else {
              collectStatus = '收藏'
              collect_pk = ''
            }
          }
        }

    //  alert(collectStatus)
        this.specialparts.push({
          id: Specialresponse.data[i].Goods_PK,
          isCollect: collectStatus,
          name: Specialresponse.data[i].Goods_Name,
          prices: '¥ ' +Specialresponse.data[i].Goods_Price,
          pictureSrc: '../../../static/goodsImage/' + Specialresponse.data[i].Goods_Picture,
          Collect_pk:  collect_pk,
          src: 'goodsDetails.html?'+ Specialresponse.data[i].Goods_PK
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
