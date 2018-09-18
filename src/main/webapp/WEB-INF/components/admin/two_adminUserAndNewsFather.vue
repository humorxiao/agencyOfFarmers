<template>
  <forth_adminuserandnewspart :tableData4="tableData4"></forth_adminuserandnewspart>
</template>

<script>
  import myforth_adminUserAndNews from '../commonComponents/two_adminUserAndNews'
  import axios from 'axios'
  export default {
    name: "forth_adminUserAndNewsFather", mounted: function () {
      axios.post('/api/getCommonUserInfo', {}).then(response => {
        // console.log(JSON.stringify(response.data))
        for (var i = 0; i < response.data.length; i++) {
          if(response.data[i].User_Sex === 1) {
            this.mysex = '男'
          } else if(response.data[i].User_Sex === 2) {
            this.mysex = '女'
          } else this.mysex = '无'
          this.tableData4.push({
            telephone: response.data[i].User_Cell,
            name: response.data[i].User_Name ,
            sex: this.mysex,
            email: response.data[i].User_Email,
            time: response.data[i].User_RegTime,
            userPk: response.data[i].User_PK
            // zip: 200333
          })
        }
      }).catch(function (error) {
        console.log(error)
      })
    },
    components: {
      forth_adminuserandnewspart: myforth_adminUserAndNews
    },
    data () {
      return {
        tableData4: [],
        mysex: ''
      }
    }
  }
</script>

<style scoped>

</style>

