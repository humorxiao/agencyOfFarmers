<template>
  <!-- 菜单目录 -->
<transition name = "fade">
  <div id="fl-menu"  v-if="showMenu == 1" :style="{display:block}">
    <ul class="list-group">
      <li id="fl-menu-logo-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="#">
        </a>
      </li>
      <li id="fl-menu-fruit-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=1" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-fruit-icon"></span>
          水果
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.fruit" >
          <li class = "goods">{{item.fruit}}</li>
          </div>
        </ul>
      </li>
      <li id="fl-menu-product-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=2" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-product-icon"></span>
          加工品
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.processedGoods">
          <li class = "goods">{{item.processedGoods}}</li>
        </div></ul>
      </li>
      <li id="fl-menu-vegetable-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=3" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-vegetable-icon"></span>
          粮蔬
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.vegetable">
            <li class = "goods">{{item.vegetable}}</li>
          </div>
        </ul>
      </li>
      <li id="fl-menu-fish-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=4" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-fish-icon"></span>
          水产
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.fish">
          <li class = "goods">{{item.fish}}</li>
        </div></ul>
      </li>
      <li id="fl-menu-livestock-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=5" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-livestock-icon"></span>
          禽畜
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.livestock">
          <li class = "goods">{{item.livestock}}</li>
        </div></ul>
      </li>
      <li id="fl-menu-plant-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="collection.html?type=6" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-plant-icon"></span>
          植物
        </a>
        <ul class="dropdown-menu">
          <div v-for="(item) in menus" :key="item.id" v-if="item.plant">
          <li class = "goods">{{item.plant}}</li>
        </div></ul>
      </li>
    </ul>
  </div>
  </transition>
</template>

<script>
export default {
  name: 'myMenu',
  data () {
    return {
      showMenu: 1,
      block: ''
    }
  },
  props: {
    menus: {
      type: Array,
      default: function () {
        return []
      }
    }
  },
   mounted () {
    window.addEventListener('scroll', this.scrollToTop)
  },

  methods: {
   scrollToTop () {
     var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      console.log(scrollTop)
     if (scrollTop >= 545) {
       this.showMenu = 1
        console.log(this.data().showMenu)
     } else {
       this.showMenu = -1
     }
      console.log(this.showMenu)
   },
   destroyed () {
     window.removeEventListener('scroll', this.scrollToTop)
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
  #fl-menu{
    margin-top: 200px;
  }
  .goods{
    padding: 4px 20px
  }
</style>
