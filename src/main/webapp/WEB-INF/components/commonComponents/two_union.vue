<template>
  <div>
    <div class="tab-pane active" id="panel-923725">
      <br>
      <el-input v-model="input" placeholder="搜索商品"  width="20%"></el-input>
      <a class="btn" href="#modal-container-Goods" @click=addUnion1()
         data-toggle="modal"><img style="width: 25px;"
         src="../ico-img/add.jpg">新增商品</a> <br><br>
      <el-table
        :data="message"
        style="width: 90%"
        max-height="610">
        <el-table-column
          prop="goodsname"
          label="商品名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodstype"
          label="商品类别"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsleft"
          label="商品库存"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsprice"
          label="商品价格"
          width="150">
        </el-table-column>
        <el-table-column
          prop="sales"
          label="商品是否售卖"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsplace"
          label="商品展示位置"
          width="150">
        </el-table-column>
        <el-table-column
          prop="pictures"
          label="商品图片路径"
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
        name: "two_union",
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
  .el-input{
    width: 20%;
  }
</style>
