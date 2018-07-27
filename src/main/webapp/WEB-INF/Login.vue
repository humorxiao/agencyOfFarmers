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
                <input type="password" class="form-control" id="inputPassword" placeholder="请输入密码" v-model="password">
              </div>
            </div>
            <div id="User_Position" class="form-group">
              <label class="col-md-3 control-label">登录身份</label>
              <div class="col-md-9">
                <label class="radio-inline col-md-3">
                  <input type="radio" name="User_Position" value="1" v-model="checked"> 用户
                </label>
                <label class="radio-inline col-md-5">
                  <input type="radio" name="User_Position" value="2" v-model="checked"> 管理员
                </label>
              </div>
            </div>
            <div class="form-group">
              <label for="yzm" class="col-md-3 control-label">验证码</label>
              <div class="col-md-9">
                <input type="text" class="form-control" id="yzm" placeholder="验证码">
                <!--<IMG width="78px" src='' id="img0" vspace=3>-->
                <a class="btn" onclick=getVCode()>换一张</a>
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
        </div>
      </div>
    </div>
    <router-view/>
  </div>
</template>

<script>
// import axios from 'axios'
// import Bus from './bus.js'
export default {
  name: 'panel',
  data () {
    return {
      id: '',
      password: '',
      datas: '',
      point: '-1',
      msg: '',
      checked: '1',
      block: ''
    }
  },
  methods: {
    login: function () {
      // alert(this.id)
      // alert(this.password)
      if (this.id === '') {
        this.alertError('账号不能为空，请填写昵称或者手机号码或者邮箱号码')
      } else if (this.password === '') {
        this.alertError('请输入密码')
      } else {
        if (this.checked === '1') { // 用户管理员登录数据
          if (/0?(13|14|15|18|17)[0-9]{9}/.test(this.id) === true) { // 手机
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': this.id,
              'User_Name': '',
              'User_Email': ''
            }
            // alert(1)
          } else if (/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(this.id) === true) { // 邮箱
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': '',
              'User_Name': '',
              'User_Email': this.id
            }
            // alert(2)
          } else { // 昵称
            this.datas = {
              'isAdmin': false,
              'User_Password': this.password,
              'User_Cell': '',
              'User_Name': this.id,
              'User_Email': ''
            }
            // alert(3)
          }
        } else if (this.checked === '2') {
          // alert('Hello! 管理员')
          if (/0?(13|14|15|18|17)[0-9]{9}/.test(this.id) === true) { // 手机
            this.datas = {
              'isAdmin': true,
              'Admin_PK': '',
              'Admin_Password': this.password,
              'Admin_Cell': this.id,
              'Admin_Name': '',
              'Admin_Email': ''
            }
          } else if (/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(this.id) === true) { // 邮箱
            this.datas = {
              'isAdmin': true,
              'Admin_PK': '',
              'Admin_Password': this.password,
              'Admin_Cell': '',
              'Admin_Name': '',
              'Admin_Email': this.id}
          } else { // 昵称
            this.datas = {
              'isAdmin': true,
              'Admin_PK': '',
              'Admin_Password': this.password,
              'Admin_Cell': '',
              'Admin_Name': this.id,
              'Admin_Email': ''
            }
          }
        }
        if (this.checked === '1' || this.checked === '2') {
          this.alertError('发送数据成功')
          // Bus.$emit('val', this.login_point)
          window.location.href = 'index.html'
          var obj = JSON.stringify(this.datas)
          alert(obj)
          /* axios.post('login', {obj}).then(response => {
            console.log(response)
            console.log('发送数据成功!')
          }).catch(function (error) {
            console.log(error)
            console.log('发送数据失败!')
          })
          /* if (this.response.isCorrect) {
            if (this.data.isAdmin) {
              window.location.href = 'editinfo.html'
            } else {
              window.location.href = 'index.html'
            }
          } else {
            this.alertError('登录账号或密码错误')
          } */
        }
      }
    },
    alertError: function (msg) {
      this.point = '1'
      this.msg = msg
    }
  }
}

</script>

<style scoped>
  #login {
    height: 100%;
  }
  .info{
    margin-top: 30px;
    text-align: center;
  }
  .slide-fade-enter-active {
    transition: all .4s ease;
  }
  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active 用于 2.1.8 以下版本 */ {
    transform: translateY(-100px);
    opacity: 0;
  }
</style>
