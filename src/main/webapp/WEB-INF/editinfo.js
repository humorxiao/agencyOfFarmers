// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Editinfo from './Editinfo.vue'
import router from './router'
import {Button,Message,  Table, TableColumn,  Popover, Tag, Input, FormItem,Form , Select,
  Option,MessageBox} from 'element-ui'
import "babel-polyfill"

Vue.config.productionTip = false
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Popover)
Vue.use(Button)
Vue.use(Tag)
Vue.use(Input)
Vue.use( FormItem)
Vue.use(Form)
Vue.use(Select)
Vue.use(Option)
Vue.use(VueQuillEditor)
Vue.prototype.$confirm = MessageBox.confirm;
Vue.prototype.$message = Message;
Vue.prototype.$msgbox = MessageBox;
Vue.prototype.$alert = MessageBox.alert;
Vue.prototype.$confirm = MessageBox.confirm;
Vue.prototype.$prompt = MessageBox.prompt;
/* eslint-disable no-new */
new Vue({
  el: '#editinfo',
  router,
  components: { Editinfo },
  template: '<Editinfo/>'
})
