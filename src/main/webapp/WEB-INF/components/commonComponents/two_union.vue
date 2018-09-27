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
    <div class="modal fade" id="modal-container-Union" role="dialog"
         aria-hidden="true" aria-labelledby="myModalLabel">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button class="close" aria-hidden="true" type="button"
                    data-dismiss="modal">×</button>
            <h4 class="modal-title" id="myModalLabel">合作社资料填写</h4>
          </div>
          <div class="modal-body">
            合作社名称：<input type="text" id="Union_Name" /><br>
            负责人姓名：<input type="text" id="Union_Master" /><br>
            负责人手机：<input type="tel" id="Union_Cell" /><br>
            营业执照号：<input type="text" id="Union_Establish" value="2015-01-01" /><br>
            出资总额：<input type="text" id="Union_Asset" /><br>
            固定电话：<input type="tel" id="Union_Tele" /><br>
            电子邮箱：<input type="email" id="Union_Email" /><br>
            地址：<textarea id="Union_Address" placeholder="精准到街道门牌号的详细地址"></textarea>
            <br> <span id='Union-msg' style="color: red"></span>
          </div>
          <div class="modal-footer">
            <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
            <span id="Union-AddorUpdate"></span>
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
