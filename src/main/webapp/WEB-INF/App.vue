<template>
  <div id="app">
    <navbar></navbar>
    <leftmenufather></leftmenufather>
    <slidefather></slidefather>
    <specialpartfather></specialpartfather>
    <discount></discount>
    <introducepart></introducepart>
    <newspartfather></newspartfather>
    <bottom></bottom>
    <returnbottom></returnbottom>
    <router-view/>

  </div>

</template>

<script>
import myNavbar from './components/commonComponents/Navbar'
import myMenuFather from './components/index/myMenuFather'
import mySlideFather from './components/index/slideFather'
import mySpecialPartFather from './components/index/specialPartFather'
import myDiscount from './components/index/discountPartFather'
import myIntroducePart from './components/index/introducePart'
import myNewsPartFather from './components/index/newsPartFather'
import myBottom from './components/commonComponents/bottom'
import myReturnBottom from './components/commonComponents/returnTop'
export default {
  name: 'App',
  data () {
    return {
      login_point_data: '-1'
    }
  },
  components: {
    navbar: myNavbar,
    leftmenufather: myMenuFather,
    specialpartfather: mySpecialPartFather,
    discount: myDiscount,
    introducepart: myIntroducePart,
    newspartfather: myNewsPartFather,
    bottom: myBottom,
    returnbottom: myReturnBottom,
    slidefather: mySlideFather
  }
}
</script>

<style>
#app {
}
</style>
