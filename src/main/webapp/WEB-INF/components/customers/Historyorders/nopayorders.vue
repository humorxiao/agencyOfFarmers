<template>
    <div class="tab-pane" id="panel-923723">
      <br>
      <div class="col-md-8 col-md-offset-1 column" id="Orders1"></div>
      <div class="col-md-12 column">
        <ul class="pagination pull-right">
          <li><a class="btn" onclick="minus(1)">上一页</a></li>
          <li><a class="btn" id="orders-page1">1</a></li>
          <li><a class="btn" onclick="add(1)">下一页</a></li>
          <li><a class="btn" id="page1">共1页</a></li>
        </ul>
      </div>
    </div>
</template>

<script>
export default {
  name: 'nopayorders'
}
</script>

<style scoped>

</style>
