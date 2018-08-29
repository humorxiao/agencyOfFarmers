import Vue from 'vue'
import History from './History.vue'
import router from './router/history'

Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#history',
  router,
  components: {History},
  template: '<History/>'
})
