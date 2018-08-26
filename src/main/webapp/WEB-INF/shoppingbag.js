import Vue from 'vue'
import Shoppingbag from './Shoppingbag.vue'
import fastclick from 'fastclick'

Vue.config.productionTip = false
fastclick.attach(document.body)
/* eslint-disable no-new */
new Vue({
  el: '#shoppingbag',
  components: { Shoppingbag },
  template: '<Shoppingbag/>'
})
