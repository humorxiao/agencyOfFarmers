<!--卖家已发货父组件.3-->
<template>
  <unsign-order :tableOrder3="tableOrder3"></unsign-order>
</template>

<script>
  import unsignOrder from '../commonComponents/adminUnsignOrder.vue'
  import axios from 'axios'
  export default {
    name: "unsignOrderFather",
    components: {
      unsignOrder: unsignOrder
    },
    data() {
      return {
        tableOrder3: []
      }
    },
    mounted: function () {
      var stste = {'Order_State':3}
      axios.post('/api/getStateOrderPaging',stste).then(response =>{
        for(var i = 0; i < response.data.length; i++){

          this.tableOrder3.push({
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
