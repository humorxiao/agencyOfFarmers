<template>
  <div class="tab-pane" id="panel-923724">
    <br>
    <el-input v-model="input" placeholder="请输入动态新闻标题"></el-input>
    <el-button type="primary">搜索</el-button>
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
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <span id='News-msg' style="color:red"></span><br>

  </div>
</template>

<script>
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
      }
    },
    methods: {
      handleDelete(index, row) {
        console.log(index, row);
      }
    }
  }

</script>

<style scoped>
.el-input{
  width: 20%;
}
</style>
