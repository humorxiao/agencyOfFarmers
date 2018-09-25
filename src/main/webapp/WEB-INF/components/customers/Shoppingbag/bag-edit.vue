<template>
<div class="modal fade" id="modal-container-766237" role="dialog"
     aria-hidden="true" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
        <h4 class="modal-title" id="edit_title1">修改</h4>
      </div>
      <div class="modal-body" id="edit_body1"> 商品名：{{goodsname}}<br>原数量：{{input}}<br>修改后的数量：<input type="text" size="6" v-model="newinput" ></div>
      <div class="modal-footer">
        <button class="btn yellow" type="button" data-dismiss="modal">关闭</button>
        <span id="save"><button class="btn btn-primary" @click="editCart(goodsid)" type="button">保存</button></span>
      </div>
    </div>
  </div>
</div>
</template>

<script>
  import axios from 'axios'
export default {
  name: 'edit',
  props: {
    goodsname: {type: String, requeired: true},
    goodsid: {type: String, requeired: true},
    input: {type: String, requeired: true},
    local_Goods_List: {type: Array, requeired: true}
  },
  data() {
    return {
      newinput: this.input,
      Goods_List: '',
      Goods_Num: '',
      goods_list: []
    }
  },
  methods: {
    editCart: function (id) {
      var newnum = this.newinput
      for (let i = 0; i < this.local_Goods_List.length; i++) {
        if (this.local_Goods_List[i].id === id) {
          this.local_Goods_List[i].num = newnum
          break
        }
      }
      for (let i = 0; i < this.local_Goods_List.length; i++) {
        this.Goods_List += this.local_Goods_List[i].id + '#'
        this.Goods_Num += this.local_Goods_List[i].num + '#'
      }
      var data = {'Goods_List': this.Goods_List, 'Goods_Num': this.Goods_Num}
      axios.post('/api/alterCart', data).then(response => {
        if (response.data.status === 1) {
          console.log('成功')
          document.getElementById(id).innerHTML = this.newinput
          var s = document.getElementById('modal-container-766237')
          s.style.display = 'none'
          window.location.href = 'shoppingbag.html'
        }
        else {
          console.log('失败')
          window.location.href = 'shoppingbag.html'
        }
      })
    }
  }
}
</script>
<style scoped>

</style>
