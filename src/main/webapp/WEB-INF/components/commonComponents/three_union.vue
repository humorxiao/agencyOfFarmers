<template>
  <div class="tab-pane" id="panel-923726">
    <br>
    <el-input v-model="input" placeholder="搜索合作社人员"  style="width:20%"></el-input>
    <a class="btn" href="#modal-container-Staff" @click=addStaff1()
       data-toggle="modal"><img style="width: 25px;"
        src="../ico-img/add.jpg">新增合作社成员</a> <br><br>
    <el-table
      :data="message"
      style="width: 100%"
      max-height="610">
      <el-table-column
        prop="name"
        label="姓名"
        width="150">
      </el-table-column>
      <el-table-column
        prop="male"
        label="性别"
        width="150">
      </el-table-column>
      <el-table-column
        prop="birthday"
        label="出生日期"
        width="150">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="150">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        width="150">
      </el-table-column>
      <el-table-column
        prop="id"
        label="身份证号码"
        width="150">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱地址"
        width="150">
      </el-table-column>
      <el-table-column
        prop="union"
        label="所属合作社"
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
</template>

<script>
    export default {
        name: "three_union",
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
