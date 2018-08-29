import Vue from 'vue'
import Myinfo from './Myinfo.vue'


Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#myinfo',
  components: {Myinfo},
  template: '<Myinfo/>'
})
