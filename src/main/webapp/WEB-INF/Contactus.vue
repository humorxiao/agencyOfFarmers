<template>
  <div id="contactus">
    <contactnavbar></contactnavbar>
    <aboutus></aboutus>
    <menberintroduce></menberintroduce>

  </div>
</template>

<script>
import mycontactNavbar from './components/contactus/contactNavbar'
import myaboutUs from './components/contactus/aboutUs'
import mymenberIntroduce from './components/contactus/menberIntroduce'
export default {
  name: 'contactus',
  components: {
    contactnavbar: mycontactNavbar,
    aboutus: myaboutUs,
    menberintroduce: mymenberIntroduce
  }
}
</script>

<style scoped>

</style>
