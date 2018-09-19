<template>
<four :message="message"></four>
</template>

<script>
  import four from '../commonComponents/four_union'
    export default {
        name: "four_unionfather",
      data(){
        return {
          message: [{goodsname:'xxx',goodstype:'shucai',season:'222',flower:'10',
            fruit:'1',grownup:'none',fresh:'xxxxx'}]
        }
      },
      components:{four}
    }
</script>

<style scoped>

</style>
