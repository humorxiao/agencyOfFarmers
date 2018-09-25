<template>
    <three :message="message"></three>
</template>

<script>
  import axios from 'axios'
  import three from '../commonComponents/three_union'
    export default {
        name: "three_unionfather",
      components:{three},
      data(){
          return{
            message:[{name:'xxx',male:'girl',birthday:'1000',address:'guangzhou',phone:15235,
            id:'11111',email:'wwww.xxx',union:'ssss'}],
            male:''
          }
      },
      mounted:function () {
        axios.post('/api/getAllUnionStaff', {}).then(response => {
          console.log(response.data)
          for(let i=0;i<response.data.length;i++) {
            if (response.data[i].Staff_Sex === 1)
            {
              this.male = '男'
            }
            else { this.male = '女'}
            this.message.push({
              name: response.data[i].Staff_Name,
              male: this.male,
              birthday: response.data[i].Staff_Birthday,
              address: response.data[i].Staff_Address,
              phone: response.data[i].Staff_Phone,
              id:response.data[i].Staff_ID,
              email:response.data[i].Staff_Email,
              union:response.data[i].Union_Info_Id

            })
          }
        })
      }
    }
</script>

<style scoped>

</style>
