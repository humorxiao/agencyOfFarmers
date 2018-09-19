<template>
<two :message="message"></two>
</template>

<script>
  import two from '../commonComponents/two_union'
    export default {
        name: "two_unionfather",
      components:{
          two
      },
      data(){
          return {
            message: [{goodsname:'xxx',goodstype:'shucai',goodsleft:'222',goodsprice:'10',
            sale:'1',goodsplace:'none',pictures:'xxxxx',product:'xxxxxx'}]
          }
      }
    }
</script>

<style scoped>

</style>
