<template>
  <div class="tab-pane" id="panel-923724">
    <br>
    <el-input v-model="input" placeholder="请输入动态新闻标题"></el-input>
    <el-button type="primary" @click = "search()">搜索</el-button>
    <br><br>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        label="动态日期"
        width="500">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="动态标题"
        width="800">
        <template slot-scope="scope">
          <!--<el-popover trigger="hover" placement="top">-->
            <!--<p>姓名: {{ scope.row.name }}</p>-->
            <!--<p>住址: {{ scope.row.address }}</p>-->
          <a :href="scope.row.url">
            <div slot="reference" class="name-wrapper">
             <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div></a>
          <!--</el-popover>-->
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!--<el-button-->
            <!--size="mini"-->
            <!--type="danger"-->
            <!--@click="handleDelete(scope.row.news_pk,scope.$index, scope.row)">删除-->
          <!--</el-button>-->
          <el-button type="text" @click="handleDelete(scope.row.news_pk,scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <span id='News-msg' style="color:red"></span><br>

  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "s_adminUserAndNews",
    props: {
      tableData: {
        type: Array,
        default: function () {
          return []
        }
      }
    },
    data() {
      return {
        input: '',
        data: '',
        deleteData: ''
      }
    },
    methods: {
      handleDelete(newsPk, index, row) {
        this.deleteData = {"News_PK": newsPk},
        this.$confirm('此操作将永久删除该新闻, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //console.log(newsPk)
          axios.post('/api/deleteNews', this.deleteData).then(response => {
            if(response.data.status === true) {
              this.tableData.splice(index, 1)
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
            } else {
              this.$message({
                type: 'info',
                message: '删除失败!'
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      open2(newsPk, index, row) {
        // this.$confirm('此操作将永久删除该新闻, 是否继续?', '提示', {
        //   confirmButtonText: '确定',
        //   cancelButtonText: '取消',
        //   type: 'warning'
        // }).then(() => {
        //   if(this.handleDelete(newsPk)) {
        //     this.tableData.splice(index, 1)
        //     this.$message({
        //       type: 'success',
        //       message: '删除成功!'
        //     });
        //   } else{
        //     this.$message({
        //       type: 'info',
        //       message: '删除失败!'
        //     });
        //   }
        //
        // }).catch(() => {
        //   this.$message({
        //     type: 'info',
        //     message: '已取消删除'
        //   });
        // });
      },
      search(){
        this.data = {"likes": this.input}
        // console.log(this.input)
        // console.log(this.data)
        axios.post('/api/getLikesNews', this.data).then(response => {
          this.tableData.splice(0,this.tableData.length)
          for (var i = 0; i < response.data.length; i++) {
            this.tableData.push({
              name: response.data[i].News_Title,
              date: response.data[i].News_Time,
              url: 'details.html?'+ response.data[i].News_PK
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      }
    }
  }

</script>

<style scoped>
.el-input{
  width: 20%;
}
</style>
