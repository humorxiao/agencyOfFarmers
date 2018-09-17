<template>
  <div>
  <div class="col-xs-8 col-xs-offset-2 column slide"
       style="height: 21px;"></div>
  <edit v-bind:goodsname="goodsname_edit" :goodsid ="goodsid_edit" :input="input" :local_Goods_List="this.local_Goods_List"> </edit>
  <pay v-bind:sum="this.sum" :count="this.count" :Goods_List_pay="this.Goods_List_pay":goods_list="this.goods_list" ></pay>
  <br>
  <br>
  <br>
  <br>
  <div class="row clearfix div-bg-color">
    <div class="col-md-10 col-md-offset-1 column ">
      <h3 class="text-left">菜篮子</h3>
    </div>
  </div>
  <div v-if="goods_list.length">
    <div class="row clearfix div-bg-color" >
      <div class="col-md-10 col-md-offset-1 column div-bg-color" id='bag'>
        <table class="table">
          <thead>
          <tr>
            <th><input type="checkbox"  @click="selectAll()":checked="checked.length==goods_list.length">全选</th>
            <th>商品名称</th>
            <th>单价(单位：元)</th>
            <th>数量(单位：份)</th>
            <th>操作一</th>
            <th>操作二</th>
          </tr>
          </thead>
          <tbody >
          <tr v-for="(good, index) in  goods_list" :key="good.id" >
            <td><input type="checkbox" :value="good" v-model="checked"  ></td>
            <td>{{good.name}}</td>
            <td>{{good.price}}</td>
            <td v-bind:id="good.id">{{good.num}}</td>
            <td>
              <a class="btn yellow"  href="javascript:;"  @click="deleteCartGoods(index)">删除</a></td>
            <td> <a class="btn yellow" href="#modal-container-766237" data-toggle="modal"  @click="showEditPanel(good.id,good.name,good.num)">修改</a></td>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="col-md-12 column">
        <span id='bag-msg' style='color: red'></span>
      </div>
    </div>
  </div>
  <div class="row clearfix div-bg-color">
    <div class="col-md-12 column ">
      <div class="col-md-10 col-md-offset-1 column">
        <br>
        <nav class="navbar navbar-default" role="navigation">
          <div class="collapse navbar-collapse"
               id="bs-example-navbar-collapse-1">

            <ul id="account-panel" class="nav navbar-nav navbar-right">
              <li><a><b>已选产品：<span id="currentcount">{{sum}}</span>项
              </b></a></li>
              <li><a><b>共计：<span id="currentmoney">{{count}}</span>元
              </b></a></li>
              <li>
                <!-- <a onclick=addOrder()><b>提交订单</b></a> -->
                <a id="modal-735678" href="#modal-container-735678" role="button"
                   class="btn yellow" data-toggle="modal" @click="pay()">结算</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
  import axios from 'axios'
import pay from './bag-pay.vue'
import edit from './bag-edit.vue'
export default {
  name: 'bag',
  components: {
    edit,
    pay
  },
  props: {
    goods_list: {type: Array, required: true},
    local_Goods_List: {type: Array, required: true},
  },
  data () {
    return {
      Goods_List_pay: [],
      checked: [],
      goodsname_edit: '',
      goodsnum_edit: '',
      goodsid_edit: '',
      num: '',
      input: '',
      Goods_List:'',
      Goods_Num:''
    }
  },
 computed: {
    count(){
      let price =0
      this.checked.forEach(item =>{
        price += item.price*item.num
      })
      return price.toString()
    },
   sum(){
      let num1 = 0
     this.checked.forEach(item =>{
      num1++
     })
     return num1.toString()
   }
 },
  methods: {
    selectAll: function () {
     if(this.checked.length>0)
     {this.checked = []}
     else{
       this.goods_list.forEach(item=>{
         this.checked.push(item)
       })
     }
    },

    showEditPanel: function (id, name, number) {
      this.input = number.toString()
      this.goodsname_edit = name
      this.goodsnum_edit = number
      this.goodsid_edit = id
    },
    deleteCartGoods: function (index) {
      this.goods_list.splice(index, 1)
      for(let i = 0; i<this.goods_list.length;i++)
      {
          this.Goods_List += this.goods_list[i].id+'#'
          this.Goods_Num += this.goods_list[i].num+'#'
      }
      var data={'Goods_List':this.Goods_List,'Goods_Num':this.Goods_Num}
      console.log(data)
     axios.post('/api/alterCart', data).then(response => {
        if(response.data.status === 1)
        {
          console.log('true')
        }
        else {alert('error')}
      })
    },

    pay: function () {
    this.Goods_List_pay = this.checked
    }
  }
}
</script>

<style scoped>

</style>
