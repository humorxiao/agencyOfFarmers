<template>
  <unship-order :tableOrder2="tableOrder2" @EditMoney2="EditMoney2"></unship-order>
</template>

<script>
  import unshipOrder from '../commonComponents/adminUnshipOrder.vue'
  import axios from 'axios'
  export default {
    name: "unshipOrderFather",
    components: {
      unshipOrder:unshipOrder
    },
    data() {
      return {
        tableOrder2: [],
        state: {'Order_State':6}
      }
    },
    mounted: function () {
      //var stste = {'Order_State':6}
      axios.post('/api/getStateOrderPaging',this.state).then(response =>{
        for(let i = 0; i < response.data.length; i++){
          //截断收件人信息
          let msgs = response.data[i].Order_Reserve_1;
          let arr = msgs.split(";");

          this.tableOrder2.push({
            orderID: response.data[i].Order_ID,
            orderPK: response.data[i].Order_PK,
            userName: arr[1],
            userTel: arr[0],
            orderTime: response.data[i].Order_Time,
            orderPayPrice: response.data[i].Order_PayPrice,
            address: arr[2]
          })
        }

      }).catch(function (error) {
        console.log(error)
      })
    },
    methods: {
      EditMoney2: function(value,index,orderpk) {
        //var stste = {'Order_State':6}
        let orderPK = {'Order_PK':orderpk,'Order_PayPrice':value.toString()};
        axios.post('/api/rewriteOrderPrice',orderPK).then(response => {
          if(response.data.status === 1){
            axios.post('/api/getStateOrderPaging',this.state).then(responseMsg => {
              this.tableOrder2[index].orderPayPrice = responseMsg.data[index].Order_PayPrice;
            }).catch(function (error) {
              console.log(error)
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      }
    }
  }
</script>

<style scoped>

</style>
