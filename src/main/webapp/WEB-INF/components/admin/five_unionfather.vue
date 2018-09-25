<template>
  <five :message="message"></five>
</template>

<script>
  import axios from 'axios'
  import five from '../commonComponents/five_union'
    export default {
        name: "five_unionfather",
      components:{
          five
      },
      data(){
        return {
          message: [{goodsname:'xxx',buynum:'shucai',salenum:'222',price:'10',
            time:'1'}]
        }
      },
      mounted:function () {
        axios.post('/api/getAllGoods', {}).then(response => {
          for (let i = 0; i < response.data.length; i++) {
            this.message.push({
            })
          }
        })
      }
    }
</script>

<style scoped>

</style>
