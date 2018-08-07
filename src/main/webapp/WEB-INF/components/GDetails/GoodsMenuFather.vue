<template>
  <div>
    <goods-menu :menuList= "menuList" :lastMenu = "lastMenu"></goods-menu>
  </div>
</template>

<script>
import goodsMenu from '../commonComponents/goods-menu.vue'
export default{
  name: 'GoodsMenu',
  data () {
    return {
      menuList: [
        {
          address: '首页'
        },
        {
          address: '水果'
        }
      ],
      lastMenu: '鹰嘴桃'
    }
  },
  components: {
    'goodsMenu': goodsMenu
  }
}
</script>
