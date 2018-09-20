<template>
  <div>
    <myinfo1 :username="username"  :name="name" :phone="phone" :email="email" :time="time" :id="id" :male="male"
             :Deliv_Name="Deliv_Name" :Deliv_Cell="Deliv_Cell" :Deliv_code="Deliv_code" :Deliv_Address="Deliv_Address">
    </myinfo1>
  </div>
</template>

<script>
import myinfo1 from './myinfo1.vue'
import axios from 'axios'
export default {
  name: 'infofather',
  mounted:function () {
     axios.post('/api/getUserDeliveryAddress',{}).then(response => {
       this.Deliv_Name = response.data.Deliv_Name;
       this.Deliv_Address = response.data.Deliv_Address;
       this.Deliv_Cell = response.data.Deliv_Cell;
       this.Deliv_code = response.data.Deliv_Zipcode;
     })
    axios.post('/api/checkLoginRank').then((response) => {
      // console.log(response.data) // 判断登录状态
      if (response.data.status === 1) {
        this.userPK = {'User_PK': response.data.User_PK}
        axios.post('/api/getUserInfo', this.userPK).then((response) => {
          console.log(response.data)
          this.username = response.data.User_Realname
          this.name = response.data.User_Name
          this.phone = response.data.User_Cell
          this.email = response.data.User_Email
          this.id = response.data.User_ID
          this.time = response.data.User_RegTime
          if(response.data.User_Sex === 2)
          {this.male = '女'}
          else {this.male = '男'}
         // this.usesName = response.data.User_Name
        }).catch(function (error) {
          console.log(error)
        })
      }
    })

  },
  components: {
    'myinfo1': myinfo1
  },
  data () {
    return {
      username: '',
      name: '',
      phone: '',
      email: '',
      time: '',
      id: '',
      male: '',
      Deliv_Name: '',
      Deliv_Cell: '',
      Deliv_code: '',
      Deliv_Address: ''
    }
  }
}
</script>
