<template>
  <search-order :tableOrder7="tableOrder7" @searchID="searchID"></search-order>
</template>

<script>
  import searchOrder from '../commonComponents/adminSearchOrder.vue';
  import axios from 'axios'
  export default {
    name: "searchOrderFather",
    components: {
      searchOrder: searchOrder
    },
    data() {
      return{
        tableOrder7: [],
        userMsg: []
      }
    },
    mounted: function () {
    },
    methods: {
      searchID: function (input10) {
        var ordID = {'Order_ID':input10}
        var orderstate
        axios.post('/api/getIDOrder',ordID).then(response =>{
          //订单号不存在——提示
          if(JSON.stringify(response.data) === '{}'){
            this.info();
          }
          //截断收件人信息
          var msgs = response.data.Order_Reserve_1;
          var arr = msgs.split(";");
          for(var i = 0; i < arr.length; i++){
            this.userMsg[i] = arr[i];
          }
          //判断订单状态
          if(response.data.Order_State === 1){
            orderstate = '未支付'
          }else if(response.data.Order_State === 2){
            orderstate = '已取消订单'
          }else if(response.data.Order_State === 3){
            orderstate = '卖家已发货'
          }else if(response.data.Order_State === 4){
            orderstate = '已签收'
          }else if(response.data.Order_State === 5){
            orderstate = '已确认收货'
          }else if(response.data.Order_State === 6){
            orderstate = '等待卖家发货'
          }else{
            alert('error')
          }
          //订单详情
          this.tableOrder7.push({
            orderID: response.data.Order_ID,
            userName: arr[1],
            userTel: arr[0],
            orderTime: response.data.Order_Time,
            orderPayTime: response.data.Order_PayTime,
            orderPayPrice: response.data.Order_PayPrice,
            orderState: orderstate,
            address: arr[2]
          })
        }).catch(function (error) {
          console.log(error)
        })
      },
      info: function () {
        this.$message.error('搜索不到该订单')
      },
    }
  }
</script>
