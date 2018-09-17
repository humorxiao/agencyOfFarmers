<template>
  <div id = "shoppingbag">
    <smallNavBar></smallNavBar>
    <pagefoot></pagefoot>
    <Bagfather></Bagfather>
  </div>
</template>

<script>
  import smallNavBar from './components/commonComponents/smallNavBar'
  import pagefoot from './components/customers/pagefoot'
  import Bagfather from './components/customers/Shoppingbag/Bagfather'
    export default {
        name: "shoppingbag",
      components: {
        Bagfather,
        smallNavBar,
        pagefoot
      }
    }
</script>

<style scoped>

</style>
