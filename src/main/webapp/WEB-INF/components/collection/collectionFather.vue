<template>
  <collection :collections="collections"></collection>
</template>

<script>
import myCollection from '../commonComponents/collection'
export default {
  name: 'collectionFather',
  data () {
    return {
      collections: [
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
          name: '砂糖桔',
          prices: '¥5',
          src: '',
          pictureSrc: require('../../../static/goodsTestImage/shatangju.jpg')
        }
      ]
    }
  },
  components: {
    collection: myCollection
  }
}
</script>

<style scoped>

</style>
