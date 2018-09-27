<template>
  <div>
    <div class="tab-pane" id="panel-923729">
      <br>
      <a class="btn" href="#modal-container-GoodsLog" @click=addProd1()
         data-toggle="modal"><img style="width: 25px;" src="../ico-img/add.jpg">新增日志</a> <br><br>
      <el-table
        :data="message"
        style="width: 60%"
        margin="10px"
        max-height="510">
        <el-table-column
          prop="goodsname"
          label="商品"
          width="150">
        </el-table-column>
        <el-table-column
          prop="buynum"
          label="进货量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="salenum"
          label="销量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="price"
          label="更改价格（填写新价格）"
          width="200">
        </el-table-column>
        <el-table-column
          prop="time"
          label="日志时间"
          width="200">
        </el-table-column>

      </el-table>
      <span id="User-msg" style="color:red"></span><br>
    </div>
  </div>
</template>

<script>
    export default {
        name: "five_union",
      props:{
        message:{type:Array,required:true}
      },
      data(){
        return{
          input:''
        }
      }
    }
</script>

<style scoped>

</style>
