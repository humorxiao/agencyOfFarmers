<template>
  <!--新闻模块-->
    <newspart :newsparts="newsparts"></newspart>
</template>

<script>
import myNewsPart from '../commonComponents/newsPart'
import axios from 'axios'
export default {
  name: 'newsPartFather',
  mounted: function () {
    axios.post('/api/getNews', {}).then(response => {
      alert(JSON.stringify(response.data))
      // for (var i = 0; i < response.data.length; i++) {
      //
      // }
    }).catch(function (error) {
      console.log(error)
    })
  },
  data () {
    return {
      newsparts: [
        {
          pictureSrc: require('../../../static/goodsImage/lizhi.jpg'),
          newsTitle: '92名中国民工被欠薪受困塞班岛 华裔中介扣钱逃匿',
          newsTime: '时间：2017-05-09',
          newsContent: '近日，有中国工人反映，他们在美国塞班岛上一个中国公司项目工作半年后，今年4月3日开始被迫停工，停工的原因是上一个月工资没拿到，公司紧急宣布停工，并调查原因，这也导致92位民工至今滞留在岛上。被卷入这件事的中国公司是国内的知名装饰公司金螳螂（002081.SZ）。但金螳螂的回应是，他们也很冤枉：工人工资早已经付给中介公司，中介公司因为招这批员工时没有给他们办工作签证，其所持的只是旅游签证，在美国属于非法劳工，因为中介公司被调查，所以卷款潜逃了。',
          src: ''
        },
        {
          pictureSrc: require('../../../static/goodsImage/lizhi.jpg'),
          newsTitle: '92名中国民工被欠薪受困塞班岛 华裔中介扣钱逃匿',
          newsTime: '时间：2017-05-09',
          newsContent: '近日，有中国工人反映，他们在美国塞班岛上一个中国公司项目工作半年后，今年4月3日开始被迫停工，停工的原因是上一个月工资没拿到，公司紧急宣布停工，并调查原因，这也导致92位民工至今滞留在岛上。被卷入这件事的中国公司是国内的知名装饰公司金螳螂（002081.SZ）。但金螳螂的回应是，他们也很冤枉：工人工资早已经付给中介公司，中介公司因为招这批员工时没有给他们办工作签证，其所持的只是旅游签证，在美国属于非法劳工，因为中介公司被调查，所以卷款潜逃了。',
          src: ''
        },
        {
          pictureSrc: require('../../../static/goodsImage/lizhi.jpg'),
          newsTitle: '92名中国民工被欠薪受困塞班岛 华裔中介扣钱逃匿',
          newsTime: '时间：2017-05-09',
          newsContent: '近日，有中国工人反映，他们在美国塞班岛上一个中国公司项目工作半年后，今年4月3日开始被迫停工，停工的原因是上一个月工资没拿到，公司紧急宣布停工，并调查原因，这也导致92位民工至今滞留在岛上。被卷入这件事的中国公司是国内的知名装饰公司金螳螂（002081.SZ）。但金螳螂的回应是，他们也很冤枉：工人工资早已经付给中介公司，中介公司因为招这批员工时没有给他们办工作签证，其所持的只是旅游签证，在美国属于非法劳工，因为中介公司被调查，所以卷款潜逃了。',
          src: ''
        },
        {
          pictureSrc: require('../../../static/goodsImage/lizhi.jpg'),
          newsTitle: '92名中国民工被欠薪受困塞班岛 华裔中介扣钱逃匿',
          newsTime: '时间：2017-05-09',
          newsContent: '近日，有中国工人反映，他们在美国塞班岛上一个中国公司项目工作半年后，今年4月3日开始被迫停工，停工的原因是上一个月工资没拿到，公司紧急宣布停工，并调查原因，这也导致92位民工至今滞留在岛上。被卷入这件事的中国公司是国内的知名装饰公司金螳螂（002081.SZ）。但金螳螂的回应是，他们也很冤枉：工人工资早已经付给中介公司，中介公司因为招这批员工时没有给他们办工作签证，其所持的只是旅游签证，在美国属于非法劳工，因为中介公司被调查，所以卷款潜逃了。',
          src: ''
        }
      ]
    }
  },
  components: {
    newspart: myNewsPart
  }
}
</script>

<style scoped>

</style>
