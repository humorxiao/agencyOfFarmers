<template>
  <div class="hello">
    <div>
      <div class="row clearfix">
        <div class="col-md-12 column">
          <ul class="breadcrumb bg-color">
            <li class="active">首页</li>
          </ul>
        </div>
      </div>

      <div class="row clearfix div-bg-color content">
        <div class="col-md-10 col-md-offset-1 column">
          <h2>欢迎您，管理员</h2>
          <p>
            合作社内部信息管理：<a href="http://localhost:8081/editinfo.html#/unionManagePage" >click here!</a><br>
            订单信息管理：<a href="http://localhost:8081/editinfo.html#/orderManagePage">click here!</a><br>
            用户和动态编辑管理：<a href="http://localhost:8081/editinfo.html#/userAndnewsManagePage">click here!</a><br>

          </p>
          <el-button type="success" @click = "out()">安全退出</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HelloWorld',
  data () {

    return {
      msg: 'Welcome to Your Vue.js App',
      Admin_Pk: ''
    }
  },
  mounted: function () {
    axios.post('/api/checkLoginRank').then((response) => {
      //console.log(JSON.stringify(response.data))
      if(response.data.status !== 2) {
        window.location.href = 'http://localhost:8081/#/'
      }
    }).catch(function (error) {
      console.log(error)
    })
  },
  methods : {
    out() {
      axios.post('/api/checkLoginRank').then((response) => {
       // console.log(JSON.stringify(response.data))
        if (response.data.status === 2) {
          this.Admin_Pk = {"Admin_PK": response.data.Admin_PK}
          axios.post('/api/out', this.Admin_Pk).then((response) => {
            if (response.data.status === 1) {
              window.location.href = 'http://localhost:8081/#/'
            } else {
              alert('Error!')
            }
          }).catch(function (error) {
            console.log(error)
          })
        } else {
          alert('Error!')
        }
      }).catch(function (error) {
        console.log(error)
      })

    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
  .content{
    margin: 100px auto;
    text-align: center;
  }
  h2{
    margin: 0 20px 20px
  }
  p{
    font-size: 20px
  }
.el-button{
  margin: 0 20px 20px
}
</style>
