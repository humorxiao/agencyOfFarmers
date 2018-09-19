<template>
    <three :message="message"></three>
</template>

<script>
  import three from '../commonComponents/three_union'
    export default {
        name: "three_unionfather",
      components:{three},
      data(){
          return{
            message:[{name:'xxx',male:'girl',birthday:'1000',address:'guangzhou',phone:15235,
            id:'11111',email:'wwww.xxx',union:'ssss'}]
          }
      }
    }
</script>

<style scoped>

</style>
