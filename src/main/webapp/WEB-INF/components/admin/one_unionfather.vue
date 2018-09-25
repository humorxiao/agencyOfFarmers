<template>
<div>
  <one1 :message="message"></one1>
</div>
</template>

<script>
  import axios from 'axios'
  import one1 from '../commonComponents/one_union.vue'
    export default {
        name: "one_unionfather",
      components:{
          one1
      },
      mounted:function() {
        axios.post('/api/getAllUnionInfo',{}).then(response => {
          for(let i=0;i<response.data.length;i++) {
            this.message.push({
              user_name: response.data[i].Union_Master,
              union_name:response.data[i].Union_name,
              id:response.data[i].Union_License,
              address:response.data[i].Union_Address,
              time:response.data[i].Union_Establish,
              sum:response.data[i].Union_Asset,
              fixphone:response.data[i].Union_Cell,
              phone:response.data[i].Union_Tel,
              email:response.data[i].Union_Email
            })
          }
        })
      },
      data(){
          return{
            message:[]
          }
      }
    }
</script>

<style scoped>

</style>
