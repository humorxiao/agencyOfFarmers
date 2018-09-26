<template>
  <div>
    <navar></navar>
  <div class="row clearfix div-bg-color">
    <div class="col-md-10 col-md-offset-1 column">
      <div class="tabbable" id="tabs-952416">
        <br><unionall></unionall>
        <div class="tab-content div-bg-color">
          <div class="tab-pane active" id="panel-221256">
          <one></one>
          </div>
          <div class="tab-pane" id="panel-923725">
          <two></two>
          </div>
          <div class="tab-pane" id="panel-923726">
            <three></three>
          </div>
          <div class="tab-pane" id="panel-923727">
            <four></four>
          </div>
          <div class="tab-pane" id="panel-923729">
            <five></five>
          </div>
        </div>
      </div>
    </div>
  </div>
 <adminfooter></adminfooter>
 </div>
</template>

<script>
  import unionall from './commonComponents/union_all.vue'
  import one from './admin/one_unionfather.vue'
  import navar from './commonComponents/unionmanageNavar.vue'
  import adminfooter from './commonComponents/adminfooter.vue'
  import two from './admin/two_unionfather'
  import three from './admin/three_unionfather'
  import four from './admin/four_unionfather'
  import five from './admin/five_unionfather'
    export default {
        name: "unionManagePage",
      components:{
          unionall,
        one,
        navar,
        adminfooter,
        two,
        three,
        four,
        five
      }
    }
</script>

<style scoped>

</style>
