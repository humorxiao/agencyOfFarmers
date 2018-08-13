<template>
  <goodsSearch :goodsSearchs="goodsSearchs"></goodsSearch>
</template>

<script>
import mygoodsSearch from '../commonComponents/goodsSearch'
import axios from 'axios'
    export default {
      name: "goodsSearchFather",
      data () {
        return {
          searchGoods: '',
          goodsSearchs: [],
        }
      },
      methods: {
        getSearch(name){
          // 用该属性获取页面 URL 地址从问号 (?) 开始的 URL（查询部分）
          var url = window.location.search;
          // 正则筛选地址栏
          var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
          // 匹配目标参数
          var result = url.substr(1).match(reg);
          //返回参数值
          return result ? decodeURIComponent(result[2]) : null;
        }
      },
      mounted: function() {
        var searchGoodsNames = this.getSearch("search")
        this.searchGoods = {"likes" : searchGoodsNames}
        var collectStatus
        var collect_pk
        axios.post('/api/getCollect', {}).then(Collectresponse => {
          axios.post('/api/getLikesGoods', this.searchGoods).then(Searchresponse => {
            for (var i = 0; i < Searchresponse.data.length; i++) {
              if(Collectresponse.data.length === 0) {
                collectStatus = '收藏'
              } else {
                for(var j = 0; j < Collectresponse.data.length; j++) {
                  if(Searchresponse.data[i].Goods_PK === Collectresponse.data[j].Goods_PK) {
                    collectStatus = '取消收藏'
                    collect_pk =  Collectresponse.data[j].Collect_PK
                    break;
                  } else {
                    collectStatus = '收藏'
                    collect_pk = ''
                  }
                }
              }

              //  alert(collectStatus)
              this.goodsSearchs.push({
                id:  Searchresponse.data[i].Goods_PK,
                isCollect: collectStatus,
                name:  Searchresponse.data[i].Goods_Name,
                prices: '¥ ' + Searchresponse.data[i].Goods_Price,
                pictureSrc: '../../../static/goodsImage/' +  Searchresponse.data[i].Goods_Picture,
                Collect_pk:  collect_pk,
                src: 'goodsDetails.html?'+  Searchresponse.data[i].Goods_PK
              })
            }
          }).catch(function (error) {
            console.log(error)
          })
        }).catch(function (error) {
          console.log(error)
        })
      },
      components: {
       goodsSearch: mygoodsSearch
      }
    }

</script>

<style scoped>

</style>
