<!--已完成订单父组件.5-->
<template>
  <finished-order :tableOrder5="tableOrder5" @handleEdit="handleEdit"></finished-order>
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
    },
    methods: {
      handleEdit: function (index,row,orderid) {
        var oId = {'Order_ID':orderid.toString()};
        axios.post('/api/getIDOrder',oId).then(response => {
          var changeState = {
            'Order_PK':response.data.Order_PK,
            'Order_ID':response.data.Order_ID,
            'Order_State':2,
            'User_PK':response.data.User_PK,
            'Order_No':response.data.Order_No,
            'Goods_List':response.data.Goods_List,
            'Goods_Num':response.data.Goods_Num,
            'Goods_Prices':response.data.Goods_Prices,
            'Order_Time':response.data.Order_Time,
            'Order_IsPay':response.data.Order_IsPay,
            'Order_PayTime':response.data.Order_PayTime,
            'Order_PayPrice':response.data.Order_PayPrice,
            'Order_TrackNum':response.data.Order_TrackNum,
            'Order_Company':response.data.Order_Company,
            'Order_Website':response.data.Order_Website,
            'Order_Aftersale':response.data.Order_Aftersale,
            'Order_Reserve_1':response.data.Order_Reserve_1
          };
          axios.post('/api/changeOrderState',changeState).then(responseState => {
            let state = {'Order_State':5};
            if(responseState.data.status === 1){
              axios.post('/api/getStateOrderPaging',state).then(response => {
                this.tableOrder5.splice(index,1);
              }).catch(function (error) {
                console.log(error)
              });
            }
          }).catch(function (error) {
            console.log(error)
          })
        }).catch(function (error) {
          console.log(error);
        })
      }
    }
  }

</script>

<style scoped>

</style>
