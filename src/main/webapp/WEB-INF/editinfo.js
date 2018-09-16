// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Editinfo from './Editinfo.vue'
import router from './router'
import "babel-polyfill"

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#editinfo',
  router,
  components: { Editinfo },
  template: '<Editinfo/>'
})
