import Vue from 'vue'
import History from './History.vue'
import router from './router/history.js'
import {Button,Message,  Table, TableColumn,  Popover, Tag, Input, FormItem,Form , Select,
  Option,MessageBox} from 'element-ui'
Vue.config.productionTip = false
/* eslint-disable no-new */
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
new Vue({
  el: '#history',
  router,
  components: {History},
  template: '<History/>'
})
