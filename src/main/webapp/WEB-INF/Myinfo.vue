<template>
  <div id="myinfo">
    <smallNavBar></smallNavBar>
    <pagefoot></pagefoot>
    <myinfofather></myinfofather>
    <!--<router-view/>-->
  </div>
</template>

<script>
import smallNavBar from './components/commonComponents/smallNavBar'
import pagefoot from './components/customers/pagefoot'
import myinfofather from './components/customers/Myinfo/infofather.vue'
//import '../static/dist/css/bootstrap.min.css'
/*import '../static/css/NewFile1.css'
import '../static/css/index.css'
import '../static/dist/js/bootstrap.min.js'*/
export default {
  name: 'myinfo',
  components:{
    smallNavBar,
    pagefoot,
    myinfofather
  }
}
</script>
<style>
</style>
