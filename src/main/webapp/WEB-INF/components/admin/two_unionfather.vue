<template>
<two :message="message"></two>
</template>

<script>
  import axios from 'axios'
  import two from '../commonComponents/two_union'
    export default {
        name: "two_unionfather",
      components:{
          two
      },
      mounted:function () {
        axios.post('/api/getAllGoods', {}).then(response => {
          for(let i=0;i<response.data.length;i++) {
            if (response.data[i].Goods_Show === "1") {
             this.goodsplace = '特色区'
            } else if (response.data[i].Goods_Show === "2") {
              this.goodsplace = '优惠区'
            } else {
              this.goodsplace = '无展示'
            }
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
            if(response.data[i].Goods_Mark === "0") {
              this.sale = '正常'
            }
            else {this.sale = '已下架'}
            this.message.push({
              goodsname: response.data[i].Goods_Name,
              goodstype:this.goodstype,
              goodsleft: response.data[i].Goods_Num.toString(),
              goodsprice: response.data[i].Goods_Price.toString(),
              pictures: response.data[i].Goods_Picture,
              goodsplace:this.goodsplace,
              sales:this.sale
            })
          }
        })
      },
      data(){
          return {
            message: [{goodsname:'xxx',goodstype:'shucai',goodsleft:'222',goodsprice:'10',
            sale:'1',goodsplace:'none',pictures:'xxxxx'}],
            goodstype:'',
            goodsplace:'',
            sale:''
          }
      }
    }
</script>

<style scoped>

</style>
