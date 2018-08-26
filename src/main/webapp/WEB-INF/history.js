import Vue from 'vue'
import History from './History.vue'
import fastclick from 'fastclick'
import router from './router/history'

Vue.config.productionTip = false
fastclick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  el: '#history',
  router,
  components: {History},
  template: '<History/>'
})
