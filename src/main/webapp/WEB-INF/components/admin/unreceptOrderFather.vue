<template>
  <unrecept-order :tableOrder4="tableOrder4"></unrecept-order>
</template>

<script>
  import unreceptOrder from '../commonComponents/adminUnreceptOrder.vue'
  export default {
    name: "unreceptOrderFather",
    components: {
      unreceptOrder: unreceptOrder
    },
    data() {
      return {
        tableOrder4: [{
          date: '2016-05-03',
          name: 'susan',
          province: '2016-07-12',
          city: 'nusdhfui',
          address: '第三方你觉得覅哦额额就怕热给他让他给突然开口',
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
