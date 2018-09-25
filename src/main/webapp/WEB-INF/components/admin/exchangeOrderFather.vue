<template>
  <exchange-order :tableOrder6="tableOrder6"></exchange-order>
</template>

<script>
  import exchangeOrder from '../commonComponents/adminExchangeOrder.vue'
  export default {
    name: "exchangeOrderFather",
    components: {
      exchangeOrder: exchangeOrder
    },
    data() {
      return {
        tableOrder6: [{
          date: '2016-05-03',
          name: '我是谁',
          province: '2016-07-12',
          city: 'nusdhfui',
          address: 'cg反对过分个几个环节',
          zip: 200333
        }, {
          date: '2016-05-07',
          name: '啦啦啦',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-07',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-07',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }]
      }
    }
  }

</script>

<style scoped>

</style>
