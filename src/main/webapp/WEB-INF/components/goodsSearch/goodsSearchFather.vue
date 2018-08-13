<template>
  <goodsSearch :goodsSearchs="goodsSearchs"></goodsSearch>
</template>

<script>
import mygoodsSearch from '../commonComponents/goodsSearch'
    export default {
      name: "goodsSearchFather",
      data () {
        return {
         goodsSearchs: [{
           id: '222',
           name: '222',
           prices: '¥ '+ '1',
           src: 'goodsDetails.html?'+ '1',
           collectPK: '12'
         }],
        }
      },
      mounted: function() {
        // axios.post('/api/', {}).then(Collectresponse => {
        //
        // }).catch(function (error) {
        //   console.log(error)
        // })
      },
      components: {
       goodsSearch: mygoodsSearch
      }
    }

</script>

<style scoped>

</style>
