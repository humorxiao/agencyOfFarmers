// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import GoodsSearch from './GoodsSearch.vue'
import router from './router'
import "babel-polyfill"
import {Button,Message} from 'element-ui'

Vue.config.productionTip = false
Vue.use(Button)
Vue.prototype.$message = Message
Vue.prototype.$ELEMENT = { size: 'small', zIndex: 3000 }

/* eslint-disable no-new */
new Vue({
  el: '#goodsSearch',
  router,
  components: { GoodsSearch },
  template: '<GoodsSearch/>'
})
