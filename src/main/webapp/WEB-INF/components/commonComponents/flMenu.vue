<template>
  <!-- 菜单目录 -->
  <div id="fl-menu">
    <ul class="list-group">
      <li id="fl-menu-fruit-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=1" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-fruit-icon"></span>
          水果
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
      <li id="fl-menu-product-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=2" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-product-icon"></span>
          加工品
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
      <li id="fl-menu-vegetable-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=3" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-vegetable-icon"></span>
          粮蔬
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
      <li id="fl-menu-fish-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=4" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-fish-icon"></span>
          水产
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
      <li id="fl-menu-livestock-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=5" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-livestock-icon"></span>
          禽畜
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
      <li id="fl-menu-plant-item" class="list-group-item fl-list-group-item">
        <a class="fl-menu-main-item" href="../../../collection.html?type=6" target="_blank">
          <span style="display: inline-block;width: 20px;height: 25px;" id="fl-menu-plant-icon"></span>
          植物
        </a>
        <ul class="dropdown-menu"></ul>
      </li>
    </ul>
  </div>
</template>

<script>
export default{
  props: {
    nav: {
    }
  }
}
</script>
