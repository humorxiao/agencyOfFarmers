<template>
  <div id="history">

    <comment></comment>
    <Orderfather></Orderfather>
    <router-view/>
  </div>
</template>

<script>
  import comment from './components/customers/Historyorders/comment.vue'
 // import './pages/static/dist/css/bootstrap.min.css'
  import './pages/static/css/NewFile1.css'
  import './pages/static/css/index.css'
  import './pages/static/dist/js/bootstrap.min.js'
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
