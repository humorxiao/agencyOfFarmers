<template>
  <unpay-order :tableOrder="tableOrder" @EditMoney="EditMoney" @EditAddress="EditAddress"></unpay-order>
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
      tableOrder: [],
      userMsg: [],
      //state: {'Order_State':1}
    }
  },
  mounted: function () {
    let state = {'Order_State':1};
    axios.post('/api/getStateOrderPaging',state).then(response =>{
      for(let i = 0; i < response.data.length; i++){
        //截断收件人信息
        let msgs = response.data[i].Order_Reserve_1;
        let arr = msgs.split(";");

        this.userMsg[i] = response.data[i].Order_Reserve_1;
        this.tableOrder.push({
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
      console.log(error);
    })
  },
  methods: {
    EditMoney: function(value,index,orderpk) {
      let state = {'Order_State':1};
      let orderPK = {'Order_PK':orderpk,'Order_PayPrice':value.toString()};
      axios.post('/api/rewriteOrderPrice',orderPK).then(response => {
        if(response.data.status === 1){
          axios.post('/api/getStateOrderPaging',state).then(responseMsg => {
            this.tableOrder[index].orderPayPrice = responseMsg.data[index].Order_PayPrice;
          }).catch(function (error) {
            console.log(error);
          })
        }
      }).catch(function (error) {
        console.log(error);
      })
    },
    EditAddress: function (value,index,orderpk) {
      let state = {'Order_State':1};
      let Msgs = this.userMsg[index];
      let Arr = Msgs.split(";");
      let newMsg = Arr[0] + ';' + Arr[1] + ';' + value + ';' + Arr[3];
      let postData = {'Order_PK':orderpk,'User_Messages':newMsg};
      alert(JSON.stringify(postData))
      axios.post('/api/rewriteOrderAddress',postData).then(response => {
        if(response.data.status === 1){
          alert(1)
          axios.post('/api/getStateOrderPaging',state).then(responseMsg => {
            alert(responseMsg.data[index].Order_Reserve_1)
            let msgs = responseMsg.data[index].Order_Reserve_1;
            let arr = msgs.split(";");
            //alert(arr[2]);
            this.tableOrder[index].address = arr[2];
            //alert(this.tableOrder[index].address)
          }).catch(function (error) {
            console.log(error);
          })
        }else{
          this.$message({
            type: 'error',
            message: '修改失败'
          });
        }
      }).catch(function (error) {
        console.log(error);
      })
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
