<template>
  <div>
   <orders></orders>
  </div>
</template>

<script>
import orders from './orders'
export default {
  name: 'orderfather',
  components: {orders},
  data () {
    return {
      message: '5555'
    }
  }
}
</script>

<style scoped>

</style>
