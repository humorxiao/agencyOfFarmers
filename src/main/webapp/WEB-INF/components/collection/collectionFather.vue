<template>
  <collection :collections="collections"></collection>
</template>

<script>
import myCollection from '../commonComponents/collection'
import axios from 'axios'
export default {
  name: 'collectionFather',
  data () {
    return {
      collections: [],
      goods_pk: '',
      collect_pk: []
    }
  },
  mounted: function() {
    axios.post('/api/getCollect', {}).then(Collectresponse => {
      for (var i = 0; i < Collectresponse.data.length; i++) {
        this.goods_pk = {"Goods_PK" : Collectresponse.data[i].Goods_PK}
        this.collect_pk[i] = Collectresponse.data[i].Collect_PK //保存在数组中，此处防止axios异步回调导致出错
        var j = 0
        axios.post('/api/getOneGood', this.goods_pk).then(response => {
          this.collections.push({
            id: response.data.Goods_PK,
            name: response.data.Goods_Name,
            prices: '¥ '+ response.data.Goods_Price,
            pictureSrc: '../../../static/goodsImage/' + response.data.Goods_Picture,
            src: 'goodsDetails.html?'+ response.data.Goods_PK,
            collectPK: this.collect_pk[j]
          })
          j++
        }).catch(function (error) {
          console.log(error)
        })
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  components: {
    collection: myCollection
  }
}
</script>

<style scoped>

</style>
