// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import "babel-polyfill"
import VueLazyload from 'vue-lazyload'
import {Button,Message} from 'element-ui'

Vue.config.productionTip = false
Vue.use(Button)
Vue.use(VueLazyload, {
  loading: '../../static/image/loading-spin.svg',
  // try: 3 // default 1
})
Vue.prototype.$message = Message
Vue.prototype.$ELEMENT = { size: 'big', zIndex: 3000 }

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
