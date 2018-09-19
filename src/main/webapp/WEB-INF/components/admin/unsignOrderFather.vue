<template>
  <unsign-order :tableOrder3="tableOrder3"></unsign-order>
</template>

<script>
  import unsignOrder from '../commonComponents/adminUnsignOrder.vue'
  export default {
    name: "unsignOrderFather",
    components: {
      unsignOrder: unsignOrder
    },
    data() {
      return {
        tableOrder3: [{
          date: '2016-05-03',
          name: '嘻嘻嘻',
          province: '刚好',
          city: '多少你多少',
          address: '啊是的你哦手机费第三届佛寺',
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
