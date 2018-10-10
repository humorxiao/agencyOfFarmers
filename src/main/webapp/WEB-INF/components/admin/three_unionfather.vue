<template>
    <three :message="message" :unionList="unionList" @submitForm="submitForm" @searchUnion="searchUnion" @editMsg="editMsg" @addStaff="addStaff" @handleDelete="handleDelete"></three>
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
            staffNum: 0,
            unionList: []
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
              union:response.data[i].Union_name,
              union_pk: response.data[i].Union_PK
            });
          }
        }).catch(function (error) {
          console.log(error);
        })
        axios.post('/api/getAllUnionInfo',{}).then(response => {
          for(let i = 0; i < response.data.length; i++){
            this.unionList.push({
              Uname: response.data[i].Union_name,
              Upk: response.data[i].Union_PK
            })
          }
        }).catch(function (error) {
          console.log(error);
        })
      },
      methods: {
        searchUnion: function (input) {
          let inputS = {'likes':input.toString()};
          let union_name
          axios.post('/api/getLikesStaffs',inputS).then(response => {
            for(let i = 0;i < this.staffNum; i++){
              this.message.pop();
            }
            for (var i = 0; i < response.data.length; i++) {
              if(response.data[i].Staff_Sex === 1) {
                this.male = '男'
              } else if(response.data[i].Staff_Sex === 2) {
                this.male = '女'
              } else this.male = '无'
              this.message.push({
                name: response.data[i].Staff_Name,
                male: this.male,
                birthday: response.data[i].Staff_Birthday,
                address: response.data[i].Staff_Address,
                phone: response.data[i].Staff_Phone,
                id:response.data[i].Staff_ID,
                email:response.data[i].Staff_Email,
                union:response.data[i].Union_Name
                // zip: 200333
              })
            }
            // if (response.data[0].Staff_Sex === 1) {
            //   this.male = '男'
            // } else { this.male = '女'}
            // axios.post('/api/getAllUnionInfo',{}).then(res => {
            //   for(let j = 0; j < res.data.length; j++){
            //     if(res.data[j].Union_PK === response.data[0].Union_PK){
            //       union_name = res.data[j].Union_name;
            //       alert(union_name)
            //       break;
            //     }
            //   }
            //   this.message.push({
            //     name: response.data[0].Staff_Name,
            //     male: this.male,
            //     birthday: response.data[0].Staff_Birthday,
            //     address: response.data[0].Staff_Address,
            //     phone: response.data[0].Staff_Phone,
            //     id:response.data[0].Staff_ID,
            //     email:response.data[0].Staff_Email,
            //     union:union_name
            //   })
            // }).catch(function (error) {
            //   console.log(error)
            // });
          }).catch(function (error) {
            console.log(error);
          })
        },
        submitForm: function () {

        },
        editMsg: function (index,row) {

        },
        addStaff: function () {

        },
        handleDelete: function (index,row) {
          axios.post('/api/getAllUnionStaff', {}).then(response => {
            let staffPK = response.data[index].Staff_PK;
            let data = {'Staff_PK':staffPK};
            axios.post('/api/deleteUnionStaff',data).then(responsed => {
              if(responsed.data.status === 1){
                this.message.splice(index,1);
              }else{
                this.$message({
                  type:"error",
                  message:"删除失败"
                })
              }
            }).catch(function (error) {
              console.log(error);
            })
          }).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>

</style>
