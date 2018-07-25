<template>
  <!-- 优惠区模块 -->
    <discountpart :discountparts="discountparts"></discountpart>
</template>

<script>
import myDiscountPart from '../commonComponents/discountPart'
export default {
  name: 'discountPartFather',
  data () {
    return {
      discountparts: [
        {
          name: '鹰嘴桃(省内)',
          prices: '¥88',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/timg.jpg')
        },
        {
          name: '三华李',
          prices: '¥288',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/sanhuali.jpg')
        },
        {
          name: '砂糖桔',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/shatangju.jpg')
        },
        {
          name: '荔枝',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/lizhi.jpg')
        },
        {
          name: '砂糖桔',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/shatangju.jpg')
        },
        {
          name: '鹰嘴桃(省内)',
          prices: '¥88',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/timg.jpg')
        }
      ]
    }
  },
  components: {
    discountpart: myDiscountPart
  }
}
</script>
<style scoped>

</style>
