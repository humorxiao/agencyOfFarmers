<template>
  <search-order></search-order>
</template>

<script>
  import searchOrder from '../commonComponents/adminSearchOrder.vue';
  export default {
    name: "searchOrderFather",
    components: {
      searchOrder: searchOrder
    },
    data() {

    }
  }
</script>
