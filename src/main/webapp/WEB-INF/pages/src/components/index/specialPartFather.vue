<template>
    <!-- 特色区模块 -->
      <specialpart :specialparts="specialparts"></specialpart>
</template>

<script>
import mySpecialPart from '../commonComponents/specialPart'
export default {
  name: 'specialPartFather',
  data () {
    return {
      specialparts: [
        {
          id: '',
          name: '鹰嘴桃(省内)',
          prices: '¥88',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/timg.jpg')
        },
        {
          id: '',
          name: '三华李',
          prices: '¥288',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/sanhuali.jpg')
        },
        {
          id: '',
          name: '砂糖桔',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/shatangju.jpg')
        },
        {
          id: '',
          name: '荔枝',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/lizhi.jpg')
        },
        {
          id: '',
          name: '砂糖桔',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/shatangju.jpg')
        },
        {
          id: '',
          name: '鹰嘴桃(省内)',
          prices: '¥88',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/timg.jpg')
        }
      ]
    }
  },
  components: {
    specialpart: mySpecialPart
  }
}
</script>
<style scoped>

</style>
