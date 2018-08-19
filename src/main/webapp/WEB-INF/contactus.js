// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Contactus from './Contactus.vue'
import router from './router'
import "babel-polyfill"

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#contactus',
  router,
  components: { Contactus },
  template: '<Contactus/>'
})
