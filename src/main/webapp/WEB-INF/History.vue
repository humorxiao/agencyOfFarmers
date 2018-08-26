<template>
  <div id="history">
    <comment></comment>
    <Orderfather></Orderfather>
    <router-view/>
  </div>
</template>

<script>
  import comment from './components/customers/Historyorders/comment.vue'
  import Orderfather from './components/customers/Historyorders/orderfather.vue'
  export default {
    name: 'History',
    components: {
      Orderfather,
      upline,
      pagefoot,
      comment
    }
  }
</script>

<style>
</style>
