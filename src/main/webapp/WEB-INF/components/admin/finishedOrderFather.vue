<!--已完成订单父组件.5-->
<template>
  <finished-order :tableOrder5="tableOrder5"></finished-order>
</template>

<script>
  import finishedOrder from '../commonComponents/adminFinishedOrder.vue'
  import axios from 'axios'
  export default {
    name: "finishedOrderFather",
    components: {
      finishedOrder: finishedOrder
    },
    data() {
      return {
        tableOrder5: []
      }
    },
    mounted: function () {
      var stste = {'Order_State':5}
      axios.post('/api/getStateOrderPaging',stste).then(response =>{
        for(var i = 0; i < response.data.length; i++){

          this.tableOrder5.push({
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
