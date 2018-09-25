<template>
<four :message="message"></four>
</template>

<script>
  import axios from 'axios'
  import four from '../commonComponents/four_union'
    export default {
        name: "four_unionfather",
      data(){
        return {
          message: [{goodsname:'xxx',goodstype:'shucai',season:'222',flower:'10',
            fruit:'1',grownup:'none',fresh:'xxxxx'}],
          goodstype:''
        }
      },
      mounted:function () {
        axios.post('/api/getAllGoods', {}).then(response => {
          for (let i = 0; i < response.data.length; i++) {
            if (response.data[i].Goods_Type === 1) {
              this.goodstype = '水果'
            }
            else if (response.data[i].Goods_Type === 2) {
              this.goodstype = '加工品'
            }
            else if (response.data[i].Goods_Type === 3) {
              this.goodstype = '粮疏'
            }
            else if(response.data[i].Goods_Type === 4) {
              this.goodstype = '水产'
            }
            else if(response.data[i].Goods_Type === 5) {
              this.goodstype = '禽畜'
            }
            else { this.goodstype = '植物' }
            this.message.push({
              goodsname:response.data[i].Goods_Name,
              goodstype:this.goodstype,
              season:response.data[i].Goods_Season,
              flower:response.data[i].Goods_Blossom,
              fruit:response.data[i].Goods_Fruit,
              grownup:response.data[i].Goods_Mature,
              fresh:response.data[i].Goods_Expiration
            })
          }
        })
      },
      components:{four}
    }
</script>

<style scoped>

</style>
