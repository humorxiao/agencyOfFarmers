<template>
  <div class="tab-pane" id="panel-923726">
    <br>
    <el-input v-model="input" placeholder="搜索合作社人员"  style="width:20%" clearable></el-input>
    <el-button type="primary" icon="el-icon-search" @click="searchUnion(input)">搜索</el-button>
    <el-button type="text" style="font-size: large"@click="addStaff()">新增合作社成员</el-button>
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

    <el-dialog title="合作社人员信息" :visible.sync="editFormVisible" width="30%">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" size="medium" class="demo-ruleForm">
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth" prop="male">
          <el-select v-model="ruleForm.male" placeholder="性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" :label-width="formLabelWidth" prop="birthday">
          <el-col :span="11">
          <el-date-picker
            v-model="ruleForm.birthday"
            type="date"
            value-format=" yyyy-MM-dd" format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth" prop="address">
          <el-input v-model="ruleForm.address"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" :label-width="formLabelWidth" prop="phone">
          <el-input class="inp" v-model="ruleForm.phone" auto-complete="true"></el-input>
        </el-form-item>
        <el-form-item label="身份证号码" :label-width="formLabelWidth" prop="id">
          <el-input v-model="ruleForm.id"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址" :label-width="formLabelWidth" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="所属合作社" :label-width="formLabelWidth" prop="union">
          <el-select v-model="ruleForm.union" placeholder="所属合作社">
            <el-option v-for="Item in unionList" :key="Item.id" :value="Item.Uname">
              {{ Item.Uname }}
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm',add)">确 定</el-button>
      </div>
    </el-dialog>
    <span id="User-msg" style="color:red"></span><br>
  </div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "three_union",
      props:{
        message:{
          type:Array,
          required:true
        },
        unionList:{
          type: Array,
          required: true
        }
      },
      data(){
        return{
          rules:{
            name: [
              {required: true, message: '成员名字不能为空', trigger: 'blur'}
            ],
            male: [{
              required: true,message:'成员性别不能为空',trigger:'change'
            }],
            birthday: [{
              type: 'string', required: true, message: '请选择日期', trigger: 'change'
            }],
            address: [{
              required: true,message:'成员地址不能为空',trigger:'blur'
            }],
            phone: [
              { required: true,message:'成员联系电话不能为空',trigger:'blur'}],
            id: [{
              required: true,message:'请填写成员的身份证号码',trigger:'blur'
            }],
            email: [
              {required: true,message:'请填写成员的邮箱',trigger:'blur'},
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
            ],
            union: [{
              required: true,message:'请选择成员的所属合作社',trigger:'change'
            }],
          },
          input:'',
          add: '',
          msgs: '',
          editMsgs: '',
          union_pk: 0,
          editFormVisible: false,
          ruleForm: {
            name: '',
            male: '',
            birthday: '',
            address: '',
            phone: '',
            id: '',
            email: '',
            union: '',
            staff_index: ''
          },
          formLabelWidth: '100px',
        }
      },
      methods: {
        searchUnion(input){
          this.$emit('searchUnion',input)
        },
        addStaff: function () {
          this.editFormVisible=true;
          this.add = 1;
          this.ruleForm.name = '';
          this.ruleForm.birthday = '';
          this.ruleForm.address = '';
          this.ruleForm.phone = '';
          this.ruleForm.id = '';
          this.ruleForm.email = '';
        },
        editMsg: function (index,row) {
          this.add = 0;
          this.editFormVisible=true;
          this.ruleForm.name = row.name;
          this.ruleForm.male = row.male;
          this.ruleForm.birthday = row.birthday;
          this.ruleForm.address = row.address;
          this.ruleForm.phone = row.phone;
          this.ruleForm.id = row.id;
          this.ruleForm.email = row.email;
          this.ruleForm.union = row.union;
          this.staff_index = index;
        },
        submitForm: function (formName,add) {
          let up;
          let sex;
          if(this.ruleForm.male === '男'){
            sex = 1;
          }else{
            sex = 0;
          }
          axios.post('/api/getAllUnionInfo',{}).then(response => {
            for(let i = 0; i < response.data.length; i++){
              if(response.data[i].Union_name === this.ruleForm.union){
                this.union_pk = response.data[i].Union_PK;
              }
            }
            this.msgs = {
              'Staff_Name': this.ruleForm.name,
              'Staff_Sex': sex,
              'Staff_Birthday': this.ruleForm.birthday,
              'Staff_Address': this.ruleForm.address,
              'Staff_Phone': this.ruleForm.phone,
              'Staff_ID': this.ruleForm.id,
              'Staff_Email': this.ruleForm.email,
              'Union_PK':this.union_pk
            };
          }).catch(function (error) {
            console.log(error)
          });
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.editFormVisible = false;
              if(add === 1) {
                axios.post('/api/addUnionStaff', this.msgs).then(response => {
                  alert(JSON.stringify(this.msgs))
                  if(response.data.status === 1) {
                    this.message.push({
                      name: this.ruleForm.name,
                      male: this.ruleForm.male,
                      birthday: this.ruleForm.birthday,
                      address: this.ruleForm.address,
                      phone: this.ruleForm.phone,
                      id: this.ruleForm.id,
                      email: this.ruleForm.email,
                      union: this.ruleForm.union
                    });
                    this.$message({
                      type: 'success',
                      message: '添加成功!'
                    })
                  } else {
                    this.$message({
                      type: 'info',
                      message: '添加失败!'
                    })
                  }
                }).catch(function (error) {
                  console.log(error)
                })
              } else if(this.add === 0) {
                axios.post('/api/updateUnionStaff', this.msgs).then(response => {
                  let i = this.staff_index;
                  alert(JSON.stringify(this.msgs))
                  if(response.data.status === 1) {
                    this.message[i].name = this.ruleForm.name;
                    this.message[i].male = this.ruleForm.male;
                    this.message[i].birthday = this.ruleForm.birthday;
                    this.message[i].address = this.ruleForm.address;
                    this.message[i].phone = this.ruleForm.phone;
                    this.message[i].id = this.ruleForm.id;
                    this.message[i].email = this.ruleForm.email;
                    this.message[i].union = this.ruleForm.union;
                    this.$message({
                      type: 'success',
                      message: '修改成功!'
                    })
                  }else {
                    this.$message({
                      type: 'info',
                      message: '修改失败!'
                    })
                  }
                }).catch(function (error) {
                  console.log(error)
                })
              }
            } else {
              return false;
            }
          });
          this.editFormVisible = false
        },
        handleDelete: function (index,row) {
          this.$confirm('此操作将删除该成员, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功'
            });
            this.$emit('handleDelete',index,row)
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '取消操作'
            });
          });
        }
      }
    }
</script>

<style scoped>

</style>
