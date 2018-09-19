<!--在所有订单中搜索.7-->
<template>
  <div class="tab-pane" id="panel-923730">

    <el-input
      placeholder="根据订单id搜索订单"
      v-model="input10"
      clearable>
    </el-input>
    <el-button type="primary" icon="el-icon-search">搜索</el-button>

  </div>
</template>

<script>
  export default {
    name: "searchOrder",
    data() {
      return {
        input10: ''
      }
    }
  }
</script>

<style scoped>
.el-input{
  width:30%;
}
</style>
