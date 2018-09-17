<template>
  <div id="history">
    <smallNavBar></smallNavBar>
    <pagefoot></pagefoot>
    <comment></comment>
    <Orderfather></Orderfather>
    <router-view/>
  </div>
</template>

<script>
  import smallNavBar from './components/commonComponents/smallNavBar'
  import pagefoot from './components/customers/pagefoot'
  import comment from './components/customers/Historyorders/comment.vue'
  import Orderfather from './components/customers/Historyorders/orderfather.vue'
  export default {
    name: 'History',
    components: {
      Orderfather,
      smallNavBar,
      pagefoot,
      comment
    }
  }
</script>

<style>
</style>
