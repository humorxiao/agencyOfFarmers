<template>
  <div id = "registerSuccess">
    <!-- 注册成功界面-->
    <div id = "success">
      <h3><p align="center">注册成功，请登陆！</p></h3>
      <a href="login.html" ><button id = "returnLogin">立即登录</button></a>
    </div>
  </div>
</template>

<script>
    export default {
        name: "RegisterSuccess"
    }
</script>

<style scoped>
  #registerSuccess{
  }
  #success {
    margin-top: 200px;
    text-align: center;
  }
  #returnLogin{
    background-color:#6db11b;
    border: 1px #6db11b solid;
    border-radius: 4px;
    box-shadow: 0 5px 8px 0 rgba(24,95,255,.1);
    color: #fff;
    text-align: center;
    font-weight: lighter;
    width: 135px;
    height: 40px;
    margin: 40px 0 8px;
    font-size: 18px;
    cursor: pointer;
  }
</style>
