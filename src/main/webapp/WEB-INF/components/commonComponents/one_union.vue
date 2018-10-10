<template>
    <div>
      <div class="tab-pane active" id="panel-221256">
        <br>
        <el-input v-model="input" placeholder="搜索合作社（名称、负责人、地址、负责人电话）"  style="width: 20%;" ></el-input>
        <el-button type="primary" icon="el-icon-search" @click = "search()">搜索</el-button>
        <!-- Form -->
        <el-button type="text" @click="addUnion()">添加合作社</el-button>
        <el-dialog title="合作社编辑" :visible.sync="dialogFormVisible">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="活动名称" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="负责人" prop="person">
              <el-input v-model="ruleForm.person"></el-input>
            </el-form-item>
            <el-form-item label="营业执照号" prop="number">
              <el-input v-model="ruleForm.number"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
              <el-input v-model="ruleForm.address"></el-input>
            </el-form-item>
            <el-form-item label="出资总额" prop="money">
              <el-input v-model.number="ruleForm.money"></el-input>
            </el-form-item>
            <el-form-item label="固定电话" prop="fix_phone">
              <el-input v-model="ruleForm.fix_phone"></el-input>
            </el-form-item>
            <el-form-item label="负责人手机" prop="telephone">
              <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="电子邮件" prop="email">
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item label="成立时间" required>
              <el-col :span="11">
                <el-form-item prop="date1">
                  <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1" value-format=" yyyy-MM-dd" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
                </el-form-item>
              </el-col>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="point('ruleForm',add)">确 定</el-button>
          </div>
        </el-dialog>
        <!--information-->
        <el-table
          :data="message"
          style="width: 100%"
          max-height="610">
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
              @click="handleDelete(scope.$index, scope.row.pk)">删除</el-button>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作二"
            width="120">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row.pk)">编辑</el-button>
            </template>
          </el-table-column>
        </el-table>
        <span id="User-msg" style="color:red"></span><br>
      </div>
    </div>
</template>

<script>
  import axios from 'axios'
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
            ruleForm: {
              name: '',
              person: '',
              number: '',
              address: '',
              data1: '',
              money: '',
              fix_phone: '',
              telephone: '',
              email: ''
            },
            rules: {
              name: [
                { required: true, message: '请输入活动名称', trigger: 'blur' },
              ],
              person: [
                { required: true, message: '请填写负责人姓名', trigger: 'blur' }
              ],
              number: [
                { required: true, message: '请输入营业执照号码', trigger: 'blur' }
              ],
              address: [
                { required: true, message: '请输入地址', trigger: 'blur' }
              ],
              date1: [
                { type: 'string', required: true, message: '请选择日期', trigger: 'change' }
              ],
              money: [
                { required: true, message: '请输入出资总额', trigger: 'blur' },
                { type: 'number', message: '出资总额必须为数字值'}
              ],
              fix_phone: [
                { required: true, message: '请填写固定电话', trigger: 'blur' }
              ],
              telephone: [
                { required: true, message: '请填写负责人手机', trigger: 'blur' }
              ],
              email: [
                { required: true, message: '请填写电子邮箱', trigger: 'blur' }
              ],
            },
            input:'',
            dataSearch: '',
            datas: '',
            dataDelete: '',
            dataUpdate: '',
            add: '',
            commonpks: '',
            rewrite: '',
            dialogFormVisible: false,
            formLabelWidth: '120px'
          }
      },
      methods: {
        // 搜索合作社
        search() {
          this.dataSearch = {"likes" : this.input}
          axios.post('/api/getLikesUnions', this.dataSearch).then(response => {
            //console.log(JSON.stringify(response.data))
            this.message.splice(0,this.message.length)
            for (var i = 0; i < response.data.length; i++) {
              this.message.push({
                user_name: response.data[i].Union_Master,
                union_name:response.data[i].Union_Name,
                id:response.data[i].Union_License,
                address:response.data[i].Union_Address,
                time:response.data[i].Union_Establish,
                sum:response.data[i].Union_Asset,
                fixphone:response.data[i].Union_Cell,
                phone:response.data[i].Union_Tel,
                email:response.data[i].Union_Email
                // zip: 200333
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        },
        // 添加合作社
        addUnion() {
          this.add = 1;
          this.dialogFormVisible = true
        },
        // 处理添加或修改合作社操作
        point(formName, adds) {
          //console.log(adds)
          this.datas = {
            'Union_Name':this.ruleForm.name,
            'Union_Master': this.ruleForm.person,
            'Union_License': this.ruleForm.number,
            'Union_Address':this.ruleForm.address,
            'Union_Establish': this.ruleForm.date1,
            'Union_Asset': this.ruleForm.money,
            'Union_Tele': this.ruleForm.fix_phone,
            'Union_Cell': this.ruleForm.telephone,
            'Union_Email': this.ruleForm.email,
            'Union_Mark': '0',
            'Union_PK' : this.commonpks
          }
            this.$refs[formName].validate((valid) => {
              if (valid) {
                this.dialogFormVisible = false
                if(this.add === 1) {
                  // console.log(JSON.stringify(this.dataAdd))
                  axios.post('/api/addUnionInfo', this.datas).then(response => {
                    //console.log(JSON.stringify(response.data))
                    if(response.data.status === 1) {
                      this.message.push({
                        user_name: this.ruleForm.person,
                        union_name:this.ruleForm.name,
                        id:this.ruleForm.number,
                        address:this.ruleForm.address,
                        time:this.ruleForm.date1,
                        sum:this.ruleForm.money,
                        fixphone: this.ruleForm.fix_phone,
                        phone:this.ruleForm.telephone,
                        email:this.ruleForm.email,
                        pk: response.data.Union_PK
                        // zip: 200333
                      })
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
                  // console.log(JSON.stringify(this.dataAdd))
                  axios.post('/api/updateUnionInfo', this.datas).then(response => {
                   // console.log(JSON.stringify(response.data))
                    if(response.data.status === 1) {
                      this.message.splice(this.rewrite, 1)
                      this.message.push({
                        user_name: this.ruleForm.person,
                        union_name:this.ruleForm.name,
                        id:this.ruleForm.number,
                        address:this.ruleForm.address,
                        time:this.ruleForm.date1,
                        sum:this.ruleForm.money,
                        fixphone: this.ruleForm.fix_phone,
                        phone:this.ruleForm.telephone,
                        email:this.ruleForm.email,
                        pk: response.data.Union_PK
                        // zip: 200333
                      })
                      this.$message({
                        type: 'success',
                        message: '修改成功!'
                      })
                    } else {
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
            })
        },
        // 删除合作社
        handleDelete(index, pks) {
          this.dataDelete = {'Union_PK' : pks}
          //console.log(JSON.stringify(this.dataDelete))
          axios.post('/api/deleteUnionInfo', this.dataDelete).then(response => {
            //console.log(JSON.stringify(response.data))
            if(response.data.status === 1) {
              this.message.splice(index, 1)
              this.$message({
                type: 'success',
                message: '删除成功!'
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        },
        // 修改合作社
        handleEdit(index, pks) {
          this.add = 0;
          this.dialogFormVisible = true
          this.commonpks = pks
          this.rewrite = index
        }
      }
    }
</script>

<style scoped>
  .el-input{
    width: 40%;
  }
</style>
