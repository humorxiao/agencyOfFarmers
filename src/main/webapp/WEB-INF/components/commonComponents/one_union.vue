<template>
    <div>
      <div class="tab-pane active" id="panel-221256">
        <br>
        <el-input v-model="input" placeholder="搜索合作社（名称、负责人、地址、负责人电话）"  width="20%"></el-input>

     <a class="btn" href="#modal-container-Union" @click=addUnion1()
          data-toggle="modal"><img style="width: 25px;"
           src="../ico-img/add.jpg">添加合作社</a> <br><br>
        <el-table
          :data="message"
          style="width: 100%"
          max-height="510">
          <el-table-column
            fixed
            prop="union_name"
            label="合作社名称"
            width="200">
          </el-table-column>
          <el-table-column
            prop="user_name"
            label="负责人姓名/法人"
            width="200">
          </el-table-column>
          <el-table-column
            prop="id"
            label="营业执照号码"
            width="250">
          </el-table-column>
          <el-table-column
            prop="address"
            label="地址/住所"
            width="200">
          </el-table-column>
          <el-table-column
            prop="time"
            label="成立日期"
            width="200">
          </el-table-column>
          <el-table-column
            prop="sum"
            label="出资总额"
            width="200">
          </el-table-column>
          <el-table-column
            prop="fixphone"
            label="固定电话"
            width="200">
          </el-table-column>
          <el-table-column
            prop="phone"
            label="负责人手机"
            width="200">
          </el-table-column>
          <el-table-column
            prop="email"
            label="电子邮件"
            width="200">
          </el-table-column>
          <el-table-column
            fixed="right"
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
            fixed="right"
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
        name: "one_union",
      props:{
       message:{
         type:Array,
         required: true
       }
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
