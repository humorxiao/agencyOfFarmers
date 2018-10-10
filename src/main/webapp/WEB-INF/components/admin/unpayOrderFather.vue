<template>
  <unpay-order :tableOrder="tableOrder" @EditMoney="EditMoney" @EditAddress="EditAddress" @handleDelete="handleDelete"></unpay-order>
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
      axios.post('/api/rewriteAddress',postData).then(response => {
        if(response.data.status === 1){
          axios.post('/api/getStateOrderPaging',state).then(responseMsg => {
            let m = responseMsg.data[index].Order_Reserve_1;
            let a = m.split(";");
            this.tableOrder[index].address = a[2];
          }).catch(function (error) {
            console.log(error);
          })
        }else{
          alert('error');
          this.$message({
            type: 'error',
            message: '修改失败'
          });
        }
      }).catch(function (error) {
        console.log(error);
      })
    },
    handleDelete: function (index,row,orderid) {
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
          let state = {'Order_State':1};
          if(responseState.data.status === 1){
            axios.post('/api/getStateOrderPaging',state).then(response => {
              this.tableOrder.splice(index,1);
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
