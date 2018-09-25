<template>
  <div>
    <div class="tab-pane" id="panel-923728">
      <br>
      <a class="btn" href="#modal-container-UnionGoods" @click=addProd1()
         data-toggle="modal"><img style="width: 25px;" src="../ico-img/add.jpg">新增生产关系</a> <br><br>
      <el-table
        :data="message"
        style="width: 100%"
        margin="10px"
        max-height="510">
        <el-table-column
          prop="union"
          label="合作社"
          width="150">
        </el-table-column>
        <el-table-column
          prop="product"
          label="农产品"
          width="150">
        </el-table-column>
        <el-table-column
          prop="area"
          label="面积"
          width="150">
        </el-table-column>
        <el-table-column
          prop="treesnum"
          label="株数"
          width="150">
        </el-table-column>
        <el-table-column
          prop="production"
          label="产量"
          width="150">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          width="150">
        </el-table-column>
        <el-table-column
          prop="time"
          label="上市时间"
          width="150">
        </el-table-column>
        <el-table-column
          label="操作一"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
        <el-table-column
          label="操作二"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <span id="User-msg" style="color:red"></span><br>
    </div>
  </div>
</template>

<script>
    export default {
        name: "six_union",
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
