<template>
<div class="modal fade" id="modal-container-766237" role="dialog"
     aria-hidden="true" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
        <h4 class="modal-title" id="edit_title1">修改</h4>
      </div>
      <div class="modal-body" id="edit_body1"> 商品名：{{goodsname}}<br><br>数量：<input type="text" size="6" v-model="input" ></div>
      <div class="modal-footer">
        <button class="btn yellow" type="button" data-dismiss="modal">关闭</button>
        <span id="save"><button class="btn btn-primary" @click="editCart(goodsid)" type="button">保存</button></span>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'edit',
  props: {
    goodsname: {type: String, requeired: true},
    goodsid: {type: String, requeired: true},
    input: {type: String, requeired: true},
    local_Goods_Num: {type: Array, requeired: true},
    local_Goods_List: {type: Array, requeired: true}
  },
  data () {
    return {
      Goods_List: '',
      Goods_Num: ''
    }
  },
  methods: {
    editCart: function (id) {
      var newnum = this.input
      var index = this.local_Goods_List.indexOf(id)
      if (index > -1) {
        this.local_Goods_Num[index] = newnum
        var ret = this.alterCart()
        if (ret === 1) {
          document.getElementById(id).innerHTML = this.input
          var s = document.getElementById('modal-container-766237')
          s.style.display = 'none'
        } else {
          alert('修改商品数量失败')
        }
      }
    },
    alterCart: function () {
      for (var i = 0; i < this.local_Goods_List.length - 1; i++) {
        this.Goods_List += this.local_Goods_List[i] + '#'
        this.Goods_Num += this.local_Goods_Num[i] + '#'
      }
      var data = {'Cart_PK': this.local_Cart_PK, 'Goods_List': this.Goods_List, 'Goods_Num': this.Goods_Num}
      var ret = '' // 修改购物车
      if (ret !== undefined && ret.status === 1) {
        return 1
      }
      else {
        return 0
      }
    }
  }
}
</script>
<style scoped>

</style>
