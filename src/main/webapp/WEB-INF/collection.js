// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Collection from './Collection.vue'
import router from './router'
import { Button, MessageBox, Message } from 'element-ui';

Vue.config.productionTip = false
Vue.use(Button)
Vue.prototype.$confirm = MessageBox.confirm;
Vue.prototype.$message = Message;
Vue.prototype.$ELEMENT = { size: 'small', zIndex: 3000 };

/* eslint-disable no-new */
new Vue({
  el: '#collection',
  router,
  components: { Collection },
  template: '<Collection/>'
})
