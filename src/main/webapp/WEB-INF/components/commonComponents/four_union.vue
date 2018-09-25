<template>
  <div>
    <div class="tab-pane" id="panel-923727">
      <br>
      <el-input v-model="input" placeholder="搜索农产品"  style="width:20%"></el-input>
      <a class="btn" href="#modal-container-Prod" @click=addProd1()
         data-toggle="modal"><img style="width: 25px;"
          src="../ico-img/add.jpg">新增农产品</a> <br><br>
      <el-table
        :data="message"
        style="width: 90%"
        max-height="610">
        <el-table-column
          prop="goodsname"
          label="产品名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodstype"
          label="类别"
          width="150">
        </el-table-column>
        <el-table-column
          prop="season"
          label="一年几季"
          width="150">
        </el-table-column>
        <el-table-column
          prop="flower"
          label="开花期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="fruit"
          label="挂果期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="grownup"
          label="成熟期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="fresh"
          label="保质期"
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
        name: "four_union",
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
