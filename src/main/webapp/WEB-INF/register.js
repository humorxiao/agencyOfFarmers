// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Register from './Register.vue'
import router from './router'
import "babel-polyfill"
import {Message} from 'element-ui'

Vue.config.productionTip = false
Vue.prototype.$message = Message
Vue.prototype.$ELEMENT = { size: 'big', zIndex: 3000 }

/* eslint-disable no-new */
new Vue({
  el: '#register',
  router,
  components: { Register },
  template: '<Register/>'
})
