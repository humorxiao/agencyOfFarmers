<template>
  <div class="tab-pane active" id="panel-221257">
    <br>
    <el-input v-model="input" placeholder="搜索用户(昵称、手机)"></el-input>
    <el-button type="primary">搜索</el-button>
    <el-table
      :data="tableData4"
      style="width: 100%"
      max-height="510">
      <el-table-column
        fixed
        prop="date"
        label="手机号码"
        width="300">
      </el-table-column>
      <el-table-column
        prop="name"
        label="昵称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="province"
        label="性别"
        width="200">
      </el-table-column>
      <el-table-column
        prop="city"
        label="邮箱"
        width="350">
      </el-table-column>
      <el-table-column
        prop="address"
        label="注册时间"
        width="300">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="deleteRow(scope.$index, tableData4)"
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
      tableData4: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    methods: {
      deleteRow(index, rows) {
        rows.splice(index, 1);
      }
    },
    data() {
      return {
        input: '',
        tableData4: []
      }
    }
  }
</script>

<style scoped>
  .el-input{
    width: 20%;
  }
</style>
