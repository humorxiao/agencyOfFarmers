<!--suppress ALL -->
<template>
  <!-- 平台介绍模块 -->
  <div id="fl-into">
    <div id="fl-into-bg"></div>
    <div class="container-fluid fl-container">
      <div class="row">
        <div class="fl-home-model-title">
          连平县农产品电子商务平台
        </div>
        <div class="fl-home-model-title-line">————</div>
      </div>
      <transition name = "fade">
      <div id="fl-mitao" class="row fl-home-into " v-if="point_one == 1" :style="{display: block}">
        连平鹰嘴蜜桃是我国南方最优质的桃，具有皮脆肉爽的特色，由于得天独厚的地理环境，在连平种出来的鹰嘴桃都含有特殊的蜜味，故称之为鹰嘴蜜桃。连平县丰隆生态种养专业合作社联合社共有50家农民专业合作社为成员单位，种植的鹰嘴蜜桃上万亩，经科技管理，科学治理，利用物理疗法诱杀桃虫，使鹰嘴蜜桃达到了生态绿色的水平，是广大销费者的放心果。2018年为回馈朋友们多年的支持，联合社决定以最低的价格向广大市民产地直销，往年138元每箱省内包邮的同类鹰嘴蜜桃，现预订价为98元每箱广东省内包邮，预订时间从即日起至7月10号结束，一次性购50箱以上的为9.5折。机会难得，欲购者请尽快下单。
      </div>
      </transition>
      <transition name = "fade">
      <div id="fl-lizi" class="row fl-home-into"  v-if="point_two == 1" :style="{display:block}">
        连平“三华李”称得上是正宗三华李，是韶关翁源正宗三华李的品种。六十年代，连平陂头属翁源县管辖范围，后来又改为连平管辖范围，在翁源管辖期间，政府大力宣传种植三华李，所以，陂头镇也种有一定的量，至今逐年增加面积，与翁源相邻的陂头镇与翁源有同样的土质土地及气候，种出来的三华李也称之一绝，脆皮甜心，不酸不苦，且具有养颜美容的功效，也是敬老人、送朋友的优选水果。三华李每粒一至二两左右，大小味道相同，树型高、硬枝，称之为岭南十大佳果之一的优质水果，是古代进贡皇宫的必备之选。
      </div>
      </transition>
      <div id="fl-home-into-btn" class="row">
        <span id="fl-home-into-mitao" @click="fintroduce(1)" :class="{active: 1 == fintroduceSpan}"></span>
        <span id="fl-home-into-lizi"  @click="sintroduce(1)" :class="{active: 1 == sintroduceSpan}"></span>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'introducePart',
  data: function () {
    return {
      fintroduceSpan: '1',
      sintroduceSpan: '-1',
      point_one: '1',
      point_two: '-1',
      block: ''
    }
  },
  methods: {
    fintroduce (index) {
      this.fintroduceSpan = '1'
      this.sintroduceSpan = '-1'
      this.point_one = '1'
      this.point_two = '-1'
    },
    sintroduce (index) {
      this.sintroduceSpan = '1'
      this.fintroduceSpan = '-1'
      this.point_one = '-1'
      this.point_two = '1'
    }
  }
}
</script>

<style scoped>
  .fade-enter-active, .fade-leave-active {
    transition: opacity 1s
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active, 2.1.8 版本以下 */ {
    opacity: 0
  }
</style>
