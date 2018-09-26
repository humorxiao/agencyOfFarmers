<template>
  <div class="tab-pane active" id="panel-221256">
    <br>
    <el-input v-model="input" placeholder="搜索用户(昵称、手机)"></el-input>
    <el-button type="primary" @click = "search()">搜索</el-button>
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
            @click.native.prevent="deleteRow(scope.$index,scope.row.telephone,scope.row.name,scope.row.sexid,
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
  import axios from 'axios'
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
      deleteRow(index,telephone,name,sexid,email,time,userPk) {
        // console.log(telephone,name,sexid,email,time,userPk)
        this.data = {
          'User_Name': name,
          'User_PK': userPk,
          'User_Cell': telephone,
          'User_Email': email,
          'User_Sex': sexid,
          'User_RegTime' : time
        }
        this.$confirm('是否添加至白名单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //console.log(newsPk)
          // console.log(this.data)
          axios.post('/api/removeBannedUser', this.data).then(response => {
            console.log(JSON.stringify(response.data))
            if(response.data.status === 1) {
              this.tableData1.splice(index, 1)
              this.$message({
                type: 'success',
                message: '移除成功!'
              })
            } else {
              this.$message({
                type: 'info',
                message: '移除失败!'
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      },
      search() {
        this.searchData = {"likes": this.input}
        axios.post('/api/getLikesBannedUser', this.searchData).then(response => {
          // console.log(JSON.stringify(response.data))
          this.tableData1.splice(0,this.tableData1.length)
          for (var i = 0; i < response.data.length; i++) {
            if(response.data[i].User_Sex === 1) {
              this.mysex = '男'
            } else if(response.data[i].User_Sex === 2) {
              this.mysex = '女'
            } else this.mysex = '无'
            this.tableData1.push({
              telephone: response.data[i].User_Cell,
              name: response.data[i].User_Name ,
              sex: this.mysex,
              email: response.data[i].User_Email,
              time: response.data[i].User_RegTime,
              userPk: response.data[i].User_PK
              // zip: 200333
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      }
    },
    data() {
      return {
        input: '',
        searchData: '',
        data: '',
        telephone: '',
        name: '',
        sex: '',
        mysex: "",
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
