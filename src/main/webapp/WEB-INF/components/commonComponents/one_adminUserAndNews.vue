<template>
  <div class="tab-pane active" id="panel-221256">
    <br>
    <el-input v-model="input" placeholder="搜索用户(昵称、手机)"></el-input>
    <el-button type="primary">搜索</el-button>
    <el-table
      :data="tableData1"
      style="width: 100%"
      max-height="510">
      <el-table-column
        fixed
        prop="telephone"
        label="手机号码"
        width="300">
      </el-table-column>
      <el-table-column
        prop="name"
        label="昵称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        width="200">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="350">
      </el-table-column>
      <el-table-column
        prop="time"
        label="注册时间"
        width="300">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="deleteRow(scope.$index,scope.row.telephone,scope.row.name,scope.row.sex,
            scope.row.email,scope.row.time,scope.row.userPk)"
            type="text"
            size="small">
            移除至白名单
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <span id="User-msg" style="color:red"></span><br>

  </div>
</template>

<script>
    export default {
        name: "f_adminUserAndNews",
      props: {
        tableData1: {
          type: Array,
          default: function () {
            return []
          }
        }
      },
      methods: {
        deleteRow(index,telephone,name,sex,email,time,userPk) {
          // rows.splice(index, 1);
          console.log(telephone,name,sex,email,time,userPk)
        }
      },
      data() {
        return {
          input: '',
          telephone: '',
          name: '',
          sex: '',
          email: '',
          time: '',
          userPk: ''
        }
      }
    }
</script>

<style scoped>
  .el-input{
    width: 20%;
  }
</style>
