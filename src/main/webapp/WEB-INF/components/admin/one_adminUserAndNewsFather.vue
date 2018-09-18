<template>
  <f_adminuserandnewspart :tableData1="tableData1"></f_adminuserandnewspart>
</template>

<script>
  import axios from 'axios'
  import myf_adminUserAndNews from '../commonComponents/one_adminUserAndNews'
    export default {
        name: "f_adminUserAndNewsFather",
      mounted: function () {
        axios.post('/api/getBannedUserInfo', {}).then(response => {
          // console.log(JSON.stringify(response.data))
          for (var i = 0; i < response.data.length; i++) {
            if(response.data[i].User_Sex === 1) {
              this.mysex = '男'
            } else if(response.data[i].User_Sex === 2) {
              this.mysex = '女'
            } else this.mysex = '无'
            this.tableData1.push({
              telephone: response.data[i].User_Cell,
              name: response.data[i].User_Name ,
              sex: this.mysex,
              email: response.data[i].User_Email,
              time: response.data[i].User_RegTime,
              // zip: 200333
            })
          }
        }).catch(function (error) {
          console.log(error)
        })
      },
      components: {
          f_adminuserandnewspart: myf_adminUserAndNews
      },
      data () {
        return {
          tableData1: [],
          mysex: ''
        }
      },
    }
</script>

<style scoped>

</style>
