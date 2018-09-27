<template>
  <div class="tab-pane" id="panel-923726">
    <br>
    <el-input v-model="input" placeholder="搜索合作社人员"  style="width:20%" clearable></el-input>
    <el-button type="primary" icon="el-icon-search" @click="searchUnion(input)">搜索</el-button>
    <el-button type="text" style="font-size: large">新增合作社成员</el-button>
    <el-table
      :data="message"
      style="width: 100%"
      max-height="610">
      <el-table-column
        prop="name"
        label="姓名"
        width="100">
        <template slot-scope="scope">
          <span>{{scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="male"
        label="性别"
        width="80">
        <template slot-scope="scope">
          <span>{{scope.row.male}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="birthday"
        label="出生日期"
        width="150">
        <template slot-scope="scope">
          <span>{{scope.row.birthday}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="230">
        <template slot-scope="scope">
          <span>{{scope.row.address}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        width="150">
        <template slot-scope="scope">
          <span>{{scope.row.phone}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="id"
        label="身份证号码"
        width="180">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱地址"
        width="230">
        <template slot-scope="scope">
          <span>{{scope.row.email}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="union"
        label="所属合作社"
        width="150">
        <template slot-scope="scope">
          <span>{{scope.row.union}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作一"
        width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="editMsg(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="操作二"
        width="120">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="合作社人员信息" :visible.sync="editFormVisible" width="25%">
      <el-form :model="form" size="medium">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-select v-model="form.male" placeholder="性别">
            <el-option label="男" value="man"></el-option>
            <el-option label="女" value="woman"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" :label-width="formLabelWidth">
          <el-date-picker
            v-model="form.birthday"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="电话" :label-width="formLabelWidth">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="身份证号码" :label-width="formLabelWidth">
          <el-input v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址" :label-width="formLabelWidth">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="所属合作社" :label-width="formLabelWidth">
          <el-input v-model="form.union"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="editFormVisible = false">确 定</el-button>
      </div>
    </el-dialog>
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
          input:'',
          editFormVisible: false,
          form: {
            name: '',
            male: '',
            birthday: '',
            address: '',
            phone: '',
            id: '',
            email: '',
            union: '',
          },
          formLabelWidth: '100px'
        }
      },
      methods: {
        searchUnion(input){
          this.$emit('searchUnion',input)
        },
        editMsg: function (index,row) {
          this.editFormVisible=true;
          this.form.name = row.name;
          this.form.male = row.male;
          this.form.birthday = row.birthday;
          this.form.address = row.address;
          this.form.phone = row.phone;
          this.form.id = row.id;
          this.form.email = row.email;
          this.form.union = row.union;
        },
//        addStaff: function () {
//          this.editFormVisible=true;
//          this.$emit('addStaff',this.form.name)
//        }
      }
    }
</script>

<style scoped>

</style>
