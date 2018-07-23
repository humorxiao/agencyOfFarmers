<template>
  <mymenu :menus="menus"></mymenu>
</template>

<script>
import mymyMenu from '../commonComponents/myMenu'
export default {
  name: 'myMenuFather',
  data () {
    return {
      menus: [
        {
          fruit: '苹果',
          processedGoods: '蜂蜜',
          vegetable: '菠菜',
          fish: '三文鱼',
          livestock: '生猪',
          plant: '果苗'
        },
        {
          fruit: '苹果',
          processedGoods: '蜂蜜',
          vegetable: '菠菜',
          fish: '三文鱼',
          livestock: '生猪',
          plant: '果苗'
        }
      ]
    }
  },
  components: {
    mymenu: mymyMenu
  }
}
</script>

<style scoped>

</style>
