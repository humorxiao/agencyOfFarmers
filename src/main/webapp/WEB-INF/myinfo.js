import Vue from 'vue'
import Myinfo from './Myinfo.vue'
import fastclick from 'fastclick'

Vue.config.productionTip = false
fastclick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  el: '#myinfo',
  components: {Myinfo},
  template: '<Myinfo/>'
})
