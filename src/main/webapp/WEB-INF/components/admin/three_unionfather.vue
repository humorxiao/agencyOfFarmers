<template>
    <three :message="message" @searchUnion="searchUnion" @editMsg="editMsg" @addStaff="addStaff"></three>
</template>

<script>
  import axios from 'axios'
  import three from '../commonComponents/three_union'
    export default {
        name: "three_unionfather",
      components:{three},
      data(){
          return{
            message:[],
            male:'',
            staffNum: 0
          }
      },
      mounted:function () {
        axios.post('/api/getAllUnionStaff', {}).then(response => {
          this.staffNum = response.data.length;
          for(let i=0;i<response.data.length;i++) {
            if (response.data[i].Staff_Sex === 1) {
              this.male = '男'
            } else { this.male = '女'}
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
        }).catch(function (error) {
          console.log(error);
        })
      },
      methods: {
        searchUnion: function (input) {
          let inputS = {'likes':input.toString()};
          axios.post('/api/getLikesStaffs',inputS).then(response => {
            for(let i = 0;i < this.staffNum; i++){
              this.message.pop();
            }
            if (response.data.Staff_Sex === 1) {
              this.male = '男'
            } else { this.male = '女'}
            this.message.push({
              name: response.data[0].Staff_Name,
              male: this.male,
              birthday: response.data[0].Staff_Birthday,
              address: response.data[0].Staff_Address,
              phone: response.data[0].Staff_Phone,
              id:response.data[0].Staff_ID,
              email:response.data[0].Staff_Email,
              union:response.data[0].Union_Info_Id

            })
          }).catch(function (error) {
            console.log(error);
          })
        },
        editMsg: function (index,row) {

        },
        addStaff: function () {
        }
      }
    }
</script>

<style scoped>

</style>
