<template>
  <div>
  <div class="col-xs-8 col-xs-offset-2 column slide"
       style="height: 21px;"></div>
  <edit v-bind:goodsname="goodsname_edit" :goodsid ="goodsid_edit" :input="input" :local_Goods_List="this.local_Goods_List" :local_Goods_Num="this.local_Goods_Num"></edit>
  <pay v-bind:sum="sum" :count="count" :Goods_List_BackUp="Goods_List_BackUp" :Goods_Num_BackUp="Goods_Num_BackUp"></pay>
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
            <th><input type="checkbox"  @click="selectAll()">全选</th>
            <th>商品名称</th>
            <th>单价(单位：元)</th>
            <th>数量(单位：份)</th>
            <th>操作一</th>
            <th>操作二</th>
          </tr>
          </thead>
          <tbody >
          <tr v-for="(good,index) of goods_list" >
            <td><input type="checkbox" v-bind:value="index"  v-model="checked" @click="countTotal()" ></td>
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
              <li><a><b>已选产品：<span id="currentcount">{{count}}</span>项
              </b></a></li>
              <li><a><b>共计：<span id="currentmoney">{{sum}}</span>元
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
    local_Goods_Num: {type: Array, required: true}

  },
  data () {
    return {
      Goods_List_pay: '',
      Goods_Num_pay: '',
      Goods_Prices_pay: '',
      checked: [],
      isCheckedall: false,
      goodsname_edit: '',
      goodsnum_edit: '',
      goodsid_edit: '',
      num: '',
      input: '',
      sum: '',
      count: '',
      Goods_List_BackUp: [],
      Goods_Num_BackUp: []
    }
  },
  methods: {
    selectAll: function () {
      this.isCheckedall = !this.isCheckedall
      if (this.isCheckedall) {
        this.checked = []
        for (let i = 0; i < this.goods_list.length; i++) {
          this.checked.push(i)
        }
      } else {
        this.checked = []
      }
    },

    showEditPanel: function (id, name, number) {
      this.input = number
      this.goodsname_edit = name
      this.goodsnum_edit = number
      this.goodsid_edit = id
    },
    deleteCartGoods: function (index) {
      this.goods_list.splice(index, 1)
      // var index = local_Goods_List.indexOf(Goods_PK);
      //  if (index > -1) {
      //   local_Goods_List.splice(index, 1);
      //  local_Goods_Num.splice(index, 1);
      //   var ret = alterCart();
      //   if (ret == 1) {

      //     this.goods_list.splice(index, 1)
      //   } else {
      //     alert("删除商品失败");
      //
      //   }
      //   countTotal();
      // }
    },

    alterCart: function () {

    },
    countTotal: function () {
      var sum1 = 0
      var count1 = 0
      for (let i = 0; i < this.checked.length; i++) {
        var n = this.checked[i]
        sum1 += this.goods_list[n].num * this.goods_list[n].price
        count1++
      }
      this.sum = sum1
      this.count = count1
    },
    pay: function () {
      this.Goods_List_BackUp = this.local_Goods_List
      this.Goods_Num_BackUp = this.local_Goods_Num
      for (let i = 0; i < this.checked.length; i++) {
        this.Goods_List_pay += this.goods_list[i].id + '#'
        this.Goods_Num_pay += this.goods_list[i].num + '#'
        this.Goods_Prices_pay += this.goods_list[i].price + '#'
        var index = this.Goods_List_BackUp.indexOf(this.goods_list[i].id)
        if (index > -1) {
          this.Goods_List_BackUp.splice(index, 1)
          this.Goods_Num_BackUp.splice(index, 1)
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
