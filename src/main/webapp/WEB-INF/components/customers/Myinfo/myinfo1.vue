<template>
  <div>
    <div class="col-xs-8 col-xs-offset-2 column slide"
         style="height: 21px;"></div>
    <br><br><br>
    <div class="row1 clearfix div-bg-color">
<<<<<<< HEAD
      <div class="col-md-11 col-md-offset-5 column div-bg-color">
=======
      <div class="col-md-11 col-md-offset-1 column div-bg-color">
>>>>>>> src\main\webapp\WEB-INF\History.vue
        <h3 class="text-left">个人信息资料管理</h3>
      </div>
    </div>
    <div class="row1 clearfix div-bg-color">
<<<<<<< HEAD
      <div class="col-md-5 col-md-offset-4 column" id="info">
=======
      <div class="col-md-5 col-md-offset-0 column" id="info">
>>>>>>> src\main\webapp\WEB-INF\History.vue
        <dl class="dl-horizontal">
          <dt>{{username}}</dt>
          <dd>用户昵称：{{name}}</dd>
          <dd>用户手机：{{phone}}</dd>
          <dd>用户邮箱：{{email}}</dd>
          <dd>注册时间：{{time}}</dd>
          <dd>身份证号码：{{id}}</dd>
          <dd>用户性别：{{male}}</dd>
        </dl>
      </div>
    </div>
    <br>
    <div class="row1 clearfix div-bg-color">
<<<<<<< HEAD
      <div class="col-md-11  col-md-offset-5 column">
=======
      <div class="col-md-11  col-md-offset-1 column">
>>>>>>> src\main\webapp\WEB-INF\History.vue
        <h3 class="text-left">
          默认收货信息管理
        </h3>
      </div>
    </div>
    <div class="row1 clearfix div-bg-color">
<<<<<<< HEAD
      <div class="col-md-10  col-md-offset-4  column" id="addr">
=======
      <div class="col-md-10  col-md-offset-0  column" id="addr">
>>>>>>> src\main\webapp\WEB-INF\History.vue
        <dl class="dl-horizontal">
          <dt>地址1</dt>
          <dd>收件人姓名:{{Deliv_Name}}</dd>
          <dd>收件人电话：{{Deliv_Cell}}</dd>
          <dd>邮政编码：{{Deliv_code}}</dd>
          <dd>详细地址：{{Deliv_Address}}</dd>
          <br>
          <dd><a class="btn btn-lg yellow" id="modal-735678"
<<<<<<< HEAD
                 @click=initAddr() role="button" href="#modal-container-735678"
=======
                 onclick=initAddr() role="button" href="#modal-container-735678"
>>>>>>> src\main\webapp\WEB-INF\History.vue
                 data-toggle="modal">修改默认地址</a>
          </dd>
        </dl>
      </div>
    </div>

    <div class="row1 clearfix div-bg-color">
      <div class="col-md-12 column ">
        <br>
        <div class="modal fade" id="modal-container-735678" role="dialog"
             aria-hidden="false" aria-labelledby="myModalLabel">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button class="close" aria-hidden="true" type="button"
                        data-dismiss="modal">×
                </button>
                <h4 class="modal-title" id="myModalLabel">填写默认收货信息</h4>
              </div>
              <div class="modal-body">
<<<<<<< HEAD
                收货人名字:<input id='Deliv_Name' placeholder="姓名" /><br>
                收货人手机:<input id='Deliv_Cell' type="text" placeholder="手机号"/><br>
                邮政编码：<input id="Deliv_code" type="text" placeholder="邮编"/><br>
                <br> 详细地址：<textarea id="Deliv_Address" placeholder="详细地址"></textarea>
=======
                收货人名字:<input id='Deliv_Name' type="text"/><br> 收货人手机:<input
                id='Deliv_Cell' type="text"/><br> 邮政编码：<input id="Deliv_code" type="text" placeholder="邮编"/><br>
                <br> 详细地址：
                <textarea id="Deliv_Address" placeholder="详细地址"></textarea>
>>>>>>> src\main\webapp\WEB-INF\History.vue
                <br>
                <span id="msg" style="color: red;"></span>
              </div>
              <div class="modal-footer">
                <button class="btn btn-default" type="button"
                        data-dismiss="modal">关闭
                </button>
                <button class="btn green" type="button" @click=updateAddress()>保存</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
<<<<<<< HEAD
  import axios from 'axios'
=======
>>>>>>> src\main\webapp\WEB-INF\History.vue
export default {
  name: 'myinfo1',
  props: {
    username: {type: String, required: true},
    name: {type: String, required: true},
    phone: {type: String, required: true},
    email: {type: String, required: true},
    time: {type: String, required: true},
    id: {type: String, required: true},
    male: {type: String, required: true},
    Deliv_Name: {type: String, required: true},
    Deliv_Cell: {type: String, required: true},
    Deliv_code: {type: String, required: true},
    Deliv_Address: {type: String, required: true}
  },
  methods: {
<<<<<<< HEAD
    initAddr: function () {

   },
=======
>>>>>>> src\main\webapp\WEB-INF\History.vue
    updateAddress: function () {
      var Name = (document.getElementById('Deliv_Name').value)
      if (Name === '') {
        document.getElementById('msg').innerHTML = '请填写收件人姓名'
        return false
      }

      var Cell = (document.getElementById('Deliv_Cell').value)
      if (Cell === '') {
        document.getElementById('msg').innerHTML = '请填写联系号码'
        return
      } else if (Cell.length < 11) {
        document.getElementById('msg').innerHTML = '请填写正确的手机号码'
        return
      }

      var code = (document.getElementById('Deliv_code').value)
      if (code === '') {
        document.getElementById('msg').innerHTML = '请填写邮政编码'
        return
      }

      var Address = (document.getElementById('Deliv_Address').value)
      if (Address === '') {
        document.getElementById('msg').innerHTML = '请填写详细地址'
        return
      }
<<<<<<< HEAD
      var data = {'Deliv_Cell':this.Cell,'Deliv_Name':this.Name,
        'Deliv_Address':this.Address,'Deliv_Zipcode':this.code}
      axios.post('/api/updateUserDeliveryAddress', data).then((response) => {
        console.log(response.data.status)
        if(response.data.status === 1)
        { var s = document.getElementById('modal-container-735678')
        s.style.display = 'none'
        this.Deliv_Name = Name
        this.Deliv_Cell = Cell
        this.Deliv_code = code
        this.Deliv_Address = Address
      } else {alert('error')}
      })
=======
      var s = document.getElementById('modal-container-735678')
      s.style.display = 'none'
      this.Deliv_Name = Name
      this.Deliv_Cell = Cell
      this.Deliv_code = code
      this.Deliv_Address = Address
>>>>>>> src\main\webapp\WEB-INF\History.vue
    }
  }
}
</script>
