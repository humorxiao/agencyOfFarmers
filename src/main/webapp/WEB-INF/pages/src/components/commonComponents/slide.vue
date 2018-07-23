<template>
  <div class="slide-show" @mouseover="clear" @mouseout="run">
    <div class="slide-img">
      <a>
        <transition name="fade">
          <img v-if="isShow" :src="slides[nowIndex].src">
        </transition>
        <transition name="fade">
          <img v-if="!isShow" :src="slides[nowIndex].src">
        </transition>
      </a>
    </div>
    <!--<h2>{{ slides[0].title }}</h2>-->
     <ul class="slide-pages">
      <li v-for="(item, index) in slides" @click="goto(index)" :key="item.id">
        <a :class="{on: index === nowIndex}">_</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    slides: {
      type: Array,
      default: function () {
        return []
      }
    },
    inv: {
      type: Number,
      default: 1000
    }
  },
  data () {
    return {
      nowIndex: 0,
      isShow: true
    }
  },
  computed: {
    prevIndex () {
      if (this.nowIndex === 0) {
        return this.slides.length - 1
      } else {
        return this.nowIndex - 1
      }
    },
    nextIndex () {
      if (this.nowIndex === this.slides.length - 1) {
        return 0
      } else {
        return this.nowIndex + 1
      }
    }
  },
  methods: {
    goto (index) {
      this.isShow = false
      setTimeout(() => {
        this.isShow = true
        this.nowIndex = index
      }, 1)
    },
    run () {
      this.invId = setInterval(() => {
        this.goto(this.nextIndex)
      }, this.inv)
    },
    clear () {
      clearInterval(this.invId)
    }
  },
  mounted () {
    this.run()
  }
}
</script>

<style scoped>
  /*.slide-trans-enter-active {
    transition: all 0.10s;
  }
  .slide-trans-enter {
    transform: translateX(100%);
  }
  .slide-trans-old-leave-active {
    transition: all .5s;
    transform: translateX(-100%);
  } */
  .fade-enter-active, .fade-leave-active {
    transition: opacity 1s
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active, 2.1.8 版本以下 */ {
    opacity: 0
  }
  .slide-show {
    position: relative;
    margin-top: 50px;
    width: 100%;
    height: 587px;
    overflow: hidden;
  }
  .slide-show h2 {
    position: absolute;
    width: 100%;
    height: 100%;
    color: #fff;
    opacity: .5;
    bottom: 0;
    height: 30px;
    text-align: left;
    padding-left: 15px;
  }
  .slide-img {
    width: 100%;
  }
  .slide-img img {
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
  }
  .slide-pages {
    font-size:25px;
    position: absolute;
    bottom: 10px;
    left:45%;
    opacity: 0.5;
  }
  .slide-pages li {
    display: inline-block;
    padding: 0 10px;
    cursor: pointer;
    color: #fff;
    font-size: 50px;
    font-weight: bolder;
  }
  .slide-pages li a{
    color: #000;
  }
  .slide-pages li .on {
    color: #fff;
  }
  .slide-pages li a:hover{
    text-decoration: none;
  }
</style>
