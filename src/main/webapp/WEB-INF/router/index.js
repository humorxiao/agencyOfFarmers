import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import unionManagePage from '@/components/unionManagePage'
import orderManagePage from '@/components/orderManagePage'
import userAndnewsManagePage from '@/components/userAndnewsManagePage'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/unionManagePage',
      name: 'unionManagePage',
      component: unionManagePage
    },
    {
      path: '/orderManagePage',
      name: 'orderManagePage',
      component: orderManagePage
    },
    {
      path: '/userAndnewsManagePage',
      name: 'userAndnewsManagePage',
      component:userAndnewsManagePage
    }
  ]
})
