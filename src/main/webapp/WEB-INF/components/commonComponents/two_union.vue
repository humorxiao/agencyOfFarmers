<template>
  <div>
    <div class="tab-pane active" id="panel-923725">
      <br>
      <el-input v-model="input" placeholder="搜索商品"  width="20%"></el-input>
      <el-button type="primary" icon="el-icon-search">搜索</el-button>
      <i class="el-icon-circle-plus-outline"></i><el-button type="text" @click="addGoods()"style="front-size:large">新增商品</el-button> <br><br>
      <el-table
        :data="message"
        style="width: 90%"
        max-height="610">
        <el-table-column
          prop="goodsname"
          label="商品名称"
          width="200">
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
          width="200">
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
    <!--添加新产品-->
    <div class="modal fade" id="modal-container-Goods" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    data-dismiss="modal">×</button>
            <h4 class="modal-title" id="myModalLabel">商品资料填写</h4>
          </div>
          <div class="modal-body">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="50px" class="demo-ruleForm">
            <el-form-item label="商品名称" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="商品类别" prop="type">
              <el-select v-model="ruleForm.type" placeholder="请选择商品类别">
                <el-option label="水果" value="1"></el-option>
                <el-option label="加工品" value="2"></el-option>
                <el-option label="粮疏" value="3"></el-option>
                <el-option label="水产" value="4"></el-option>
                <el-option label="禽畜" value="5"></el-option>
                <el-option label="非水果粮蔬农作物" value="6"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="商品库存" prop="leftnum">
              <el-input v-model="ruleForm.leftnum"></el-input>
            </el-form-item>
            <el-form-item label="商品价格" prop="price">
              <el-input v-model="ruleForm.price"></el-input>
            </el-form-item>
            <el-form-item label="商品是否售卖" prop="sales">
              <el-radio-group v-model="ruleForm.sales">
                <el-radio label="是" value="0"></el-radio>
                <el-radio label="否" value="1"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="商品展示位置" prop="type">
              <el-select v-model="ruleForm.type" placeholder="请选择商品类别">
                <el-option label="特色区" value="1"></el-option>
                <el-option label="优惠区" value="2"></el-option>
                <el-option label="无展示" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          </div>
        </div>
      </div>
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
