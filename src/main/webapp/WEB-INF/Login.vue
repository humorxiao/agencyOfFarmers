<template>
  <div id="login">
    <div id="fl-login" class="container-fluid">
      <div class = "info" >
        <transition name="slide-fade">
          <div class="alert alert-danger" role="alert" id="fl-error" v-if="point == 1" :style="{display: block}">
            <span id="fl-error-icon"></span>
            <span id="fl-error-body" >{{this.msg}}</span>
          </div>
        </transition>
      </div>
      <div class="panel panel-default">
        <!-- 面板主体 -->
        <div class="panel-body">
          <form class="form-horizontal">
            <div class="form-group">
              <label for="inputId" class="col-md-3 control-label">帐号</label>
              <div class="col-md-7">
                <input type="text" class="form-control" id="inputId" placeholder="昵称/手机/邮箱" v-model="id">
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-md-3 control-label" >密码</label>
              <div class="col-md-7">
                <input type="password" class="form-control" id="inputPassword" placeholder="请输入密码" v-model="input_password">
              </div>
            </div>
            <!--用户管理员选择按钮，更改需求-->
            <!--<div id="User_Position" class="form-group">-->
<<<<<<< HEAD
            <!--<label class="col-md-3 control-label">登录身份</label>-->
            <!--<div class="col-md-9">-->
            <!--<label class="radio-inline col-md-3">-->
            <!--<input type="radio" name="User_Position" value="1" v-model="checked"> 用户-->
            <!--</label>-->
            <!--<label class="radio-inline col-md-5">-->
            <!--<input type="radio" name="User_Position" value="2" v-model="checked"> 管理员-->
            <!--</label>-->
            <!--</div>-->
=======
              <!--<label class="col-md-3 control-label">登录身份</label>-->
              <!--<div class="col-md-9">-->
                <!--<label class="radio-inline col-md-3">-->
                  <!--<input type="radio" name="User_Position" value="1" v-model="checked"> 用户-->
                <!--</label>-->
                <!--<label class="radio-inline col-md-5">-->
                  <!--<input type="radio" name="User_Position" value="2" v-model="checked"> 管理员-->
                <!--</label>-->
              <!--</div>-->
>>>>>>> hzq1
            <!--</div>-->
            <div class="form-group">
              <label for="yzm" class="col-md-3 control-label">验证码</label>
              <div class="col-md-9" >
                <input type="text" class="form-control" id="yzm" placeholder="验证码" v-model="inputVCode">
                <img width="78px" :src="picturesSrc" id="img0" vspace=3>
                <a class="btn" @click="getVCode()">换一张</a>
              </div>
            </div>
            <div class="form-group">
              <div id="fl-login-btn">
                <button type="button" class="btn btn-default btn-lg" @click=login()>登录</button>
              </div>
            </div>
          </form>
        </div>
        <!-- 面板脚 -->
        <div class="panel-footer">
          <a href="index.html">返回首页</a>
          <a href="register.html">立即注册</a>
          <a href = "adminLogin.html">管理员登录</a>
          <el-button type="text" @click="open">忘记密码</el-button>
        </div>
      </div>
    </div>


  </div>

</template>

<script>
  import axios from 'axios'
  export default {
    name: 'Login',
    data () {
      return {
        id: '',
        input_password: '',
        password: '',
        datas: '',
        point: '-1',
        msg: '',
        checked: '1',
        block: '',
        picturesSrc: '/api/getVCODE',
        login_information: '',
        login_VCode: '',
        code: '',
        inputVCode: '',
        user_name: '',
        admin_name: ''
      }
    },
    methods: {
      open() {
        if (this.id === '') {
          this.info('账号不能为空，请填写昵称或者手机号码或者邮箱号码')
        } else {
          axios.post().then(response => {
            if(response.status === 1) {
              this.$alert('请前往注册邮箱找回密码', '', {
                confirmButtonText: '确定',
              });
            }
          })
        }
      },
      login: function () {
        if (this.id === '') {
          this.info('账号不能为空，请填写昵称或者手机号码或者邮箱号码')
        } else if (this.input_password === '') {
          this.info('请输入密码')
        } else if (this.inputVCode === '') {
          this.info('请输入验证码')
        } else {
          this.password = hex_md5(this.input_password) // 密码加密
          this.code = {'code': this.inputVCode}
          if (/0?(13|14|15|18|17)[0-9]{9}/.test(this.id) === true) { // 手机
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': this.id,
              'User_Name': '',
              'User_Email': ''
            }
          } else if (/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(this.id) === true) { // 邮箱
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': '',
              'User_Name': '',
              'User_Email': this.id
            }
          } else { // 昵称
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': '',
              'User_Name': this.id,
              'User_Email': ''
            }
          }
          /**
           * 嵌套验证，先发送请求，验证验证码是否正确，再进行验证用户信息
           */
          axios.post('/api/validateVCode', this.code).then(response => { // 验证码验证
            if (response.data.status === 1) {
              axios.post('/api/login', this.datas).then(response => { // 登录信息验证
                if (response.data.reLogin === true) {
                  this.info('您已在线，不可重复登录')
                } else if (response.data.reLogin === false && response.data.isCorrect === true) {
                  window.location.href = 'index.html'
                } else {
                  this.info('用户名或密码错误')
                }
              }).catch(function (error) {
                console.log(error)
              })
            } else {
              this.info('验证码错误')
            }
          }).catch(function (error) {
            console.log(error)
          })
        }
      },
      /**
       * 信息提示
       */
      info: function (msg) {
        this.$message.error(msg)
      },
      /**
       * 获取验证码
       */
      getVCode: function () {
        this.picturesSrc= '/api/getVCODE?operation=getVCode&&='+ Math.random()
      }
    }
  }
</script>

<style scoped>
</style>
