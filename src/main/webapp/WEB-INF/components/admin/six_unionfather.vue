<template>
<six :message="message"></six>
</template>

<script>
  import axios from 'axios'
  import six from '../commonComponents/six_union'
    export default {
        name: "six_unionfather",
      components:{six},
      data(){
        return {
          message: [{union:'xxx',product:'shucai',area:'222',treesnum:'10',
            production:'1',price:'10',time:'2018-6-10'}]
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
