<template>
  <div>
    <myinfo1 :username="username"  :name="name" :phone="phone" :email="email" :time="time" :id="id" :male="male"
             :Deliv_Name="Deliv_Name" :Deliv_Cell="Deliv_Cell" :Deliv_code="Deliv_code" :Deliv_Address="Deliv_Address">
    </myinfo1>
  </div>
</template>

<script>
import myinfo1 from './myinfo1.vue'
export default {
  name: 'infofather',
  components: {
    'myinfo1': myinfo1
  },
  data () {
    return {
      username: 'sadsfdfg',
      name: 'aa',
      phone: '1111',
      email: '000',
      time: '111',
      id: '111',
      male: '111',
      Deliv_Name: '',
      Deliv_Cell: '',
      Deliv_code: '',
      Deliv_Address: ''
    }
  }
}
</script>
