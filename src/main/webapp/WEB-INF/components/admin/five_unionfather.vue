<template>
  <five :message="message":database="database"></five>
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
          message: [],
          database:[]
        }
      },
      mounted:function () {
        axios.post('/api/getAllGoodsLogPaging', {}).then(response => {
         for (let i = 0; i < response.data.length; i++) {
            this.message.push({
              goodsname:response.data[i].Goods_Name,
              buynum:response.data[i].Goods_In,
              salenum:response.data[i].Goods_Out,
              price:response.data[i].Goods_Price.toString(),
              time:response.data[i].GL_Time
           })
          }
        })
        axios.post('/api/getAllGoods', {}).then(response1 => {
          for (let i = 0; i < response1.data.length; i++) {
            this.database.push(response1.data[i])
          }
        })
      }
    }
</script>

<style scoped>

</style>
