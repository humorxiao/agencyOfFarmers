<template>
  <return-order :tableOrder5="tableOrder5"></return-order>
</template>

<script>
  import returnOrder from '../commonComponents/adminReturnOrder.vue'
  export default {
    name: "returnOrderFather",
    components: {
      returnOrder: returnOrder
    },
    data() {
      return {
        tableOrder5: [{
          date: '2016-05-03',
          name: 'lilian',
          province: '2016-07-12',
          city: 'nusdhfui',
          address: 'cg反对过分个几个环节',
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

