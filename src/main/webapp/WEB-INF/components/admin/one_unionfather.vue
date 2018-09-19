<template>
<div>
  <one1 :message="message"></one1>
</div>
</template>

<script>
  import one1 from '../commonComponents/one_union.vue'
    export default {
        name: "one_unionfather",
      components:{
          one1
      },
      data(){
          return{
            message:[{union_name:'xxx',
              user_name:'小李子',
              id:'12000',
              address:'广东省xx市',
              time:'2018-6-58',
              sum:'200',
              fixphone:'12345678',
              phone:'22222',
              email:'www.xxx'
            }]
          }
      }
    }
</script>

<style scoped>

</style>
