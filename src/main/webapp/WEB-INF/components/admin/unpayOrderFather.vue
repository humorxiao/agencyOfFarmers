<template>
  <unpay-order :tableOrder="tableOrder"></unpay-order>
</template>

<script>
import unpayOrder from '../commonComponents/adminUnpayOrder.vue'
import axios from 'axios'
export default {
  name: "unpayOrderFather",
  components: {
    unpayOrder: unpayOrder
  },
  data() {
    return {
      tableOrder: []
    }
  },
  mounted: function () {
    var stste = {'Order_State':1}
    axios.post('/api/getStateOrderPaging',stste).then(response =>{
      for(var i = 0; i < response.data.length; i++){
        this.tableOrder.push({
          orderID: response.data[i].Order_ID,
          userPK: response.data[i].User_PK,
          orderTime: response.data[i].Order_Time,
          orderPayPrice: response.data[i].Order_PayPrice
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
