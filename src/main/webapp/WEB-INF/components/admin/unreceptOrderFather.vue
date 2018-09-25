<!--未确认收货父组件.4-->
<template>
  <unrecept-order :tableOrder4="tableOrder4"></unrecept-order>
</template>

<script>
  import unreceptOrder from '../commonComponents/adminUnreceptOrder.vue'
  import axios from 'axios'
  export default {
    name: "unreceptOrderFather",
    components: {
      unreceptOrder: unreceptOrder
    },
    data() {
      return {
        tableOrder4: []
      }
    },
    mounted: function () {
      var stste = {'Order_State':4}
      axios.post('/api/getStateOrderPaging',stste).then(response =>{
        for(var i = 0; i < response.data.length; i++){

          this.tableOrder4.push({
            orderID: response.data[i].Order_ID,
            orderPK: response.data[i].Order_PK,
            userPK: response.data[i].User_PK,
            orderTime: response.data[i].Order_Time,
            orderPayPrice: response.data[i].Order_PayPrice,
            orderTrackNum: response.data[i].Order_TrackNum,
            userMsg: response.data[i].Order_Reserve_1
          })
        }

      }).catch(function (error) {
        console.log(error)
      })
    }
  }

</script>

<style scoped>

</style>
