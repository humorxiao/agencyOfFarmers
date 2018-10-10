import Vue from 'vue'
import Router from 'vue-router'
import finishorder from '../components/customers/Historyorders/finishorder.vue'
import nodeliveryorder from '../components/customers/Historyorders/nodeliveryorder.vue'
import nopayorders from '../components/customers/Historyorders/nopayorders.vue'
import nosignorder from '../components/customers/Historyorders/nosignorder.vue'
import allorder from '../components/customers/Historyorders/allorder.vue'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/nodeliveryorder',
      name: 'nodeliveryorder',
      component: nodeliveryorder
    },
    {
      path: '/finishorder',
      name: 'finishorder',
      component: finishorder
    },
    {
      path: '/nopayorders',
      name: 'nopayorders',
      component: nopayorders
    },
    {
      path: '/nosignorder',
      name: 'nosignorder',
      component: nosignorder
    },
    {
      path: '/allorder',
      name: 'allorder',
      component: allorder
    }
  ]
})
