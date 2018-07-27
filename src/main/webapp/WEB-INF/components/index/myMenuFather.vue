<template>
  <mymenu :menus="menus"></mymenu>
</template>

<script>
import mymyMenu from '../commonComponents/myMenu'
import axios from 'axios'
export default {
  name: 'myMenuFather',
  mounted: function() {
    alert(988888888)
    axios.post('/api/getAllTypeGoods', {}).then(response => {
      alert(JSON.stringify(response.data))
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {

    return {
      menus: [
        /*{
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
        }*/
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
