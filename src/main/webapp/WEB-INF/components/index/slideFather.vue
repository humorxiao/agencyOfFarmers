<template>
  <div id="fl-carousel">
    <slide :slides="slides" :inv="invTime"></slide>
  </div>
</template>
<script>
import mySlide from '../commonComponents/slide.vue'
export default {
  data () {
    return {
      invTime: 3000, // 轮播图速度
      slides: [
        {
          src: require('../../../static/image/2_meitu_1.jpg')
        },
        {
          src: require('../../../static/image/chayou_meitu_2.jpg')
        },
        {
          src: require('../../../static/image/timg_meitu_1.jpg')
        }
      ]
    }
  },
  components: {
    slide: mySlide
  }
}
</script>
<style scoped>

</style>
