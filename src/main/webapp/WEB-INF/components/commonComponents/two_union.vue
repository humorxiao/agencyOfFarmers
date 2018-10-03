<template>
  <div>
    <div class="tab-pane active" id="panel-923725">
      <br>
      <el-input v-model="input" placeholder="搜索商品"  style="width: 20%;" ></el-input>
      <el-button type="primary" icon="el-icon-search" @click = "search()">搜索</el-button>
      <el-button type="text" @click="addgoods()">添加商品</el-button>
      <el-dialog title="商品信息编辑" :visible.sync="dialogFormVisible">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="商品名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="商品类别">
          <el-select v-model="ruleForm.type" placeholder="选择商品类别">
            <el-option label="水果" value="水果"></el-option>
            <el-option label="加工品" value="加工品"></el-option>
            <el-option label="粮疏" value="粮疏"></el-option>
            <el-option label="水产" value="水产"></el-option>
            <el-option label="禽畜" value="禽畜"></el-option>
            <el-option label="植物" value="植物"></el-option>
          </el-select>
        </el-form-item>
          <el-form-item label="商品库存" prop="left">
          <el-input v-model="ruleForm.left"></el-input>
          </el-form-item>
          <el-form-item label="商品价格" prop="price">
          <el-input v-model="ruleForm.price"></el-input>
          </el-form-item>
          <el-form-item label="商品是否售卖" prop="sale">
          <el-select v-model="ruleForm.sales" placeholder="商品是否售卖">
          <el-option label="正常" value="正常"></el-option>
          <el-option label="下架" value="下架"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="商品展示位置" prop="position">
          <el-select v-model="ruleForm.position" placeholder="选择商品类别">
          <el-option label="特色区" value="特色区"></el-option>
          <el-option label="优惠区" value="优惠区"></el-option>
          <el-option label="无展示" value="无展示"></el-option>
          </el-select>
          </el-form-item>
          <el-form-item label="图片路径" prop="pictures">
          <el-input v-model="ruleForm.pictures"></el-input>
          </el-form-item>
          <el-form-item label="一年几季" prop="season">
          <el-input v-model="ruleForm.season"></el-input>
          </el-form-item>
          <el-form-item label="开花期" prop="flower">
          <el-input v-model="ruleForm.flower"></el-input>
          </el-form-item>
          <el-form-item label="挂果期" prop="fruit">
          <el-input v-model="ruleForm.fruit"></el-input>
          </el-form-item>
          <el-form-item label="成熟期" prop="growup">
          <el-input v-model="ruleForm.growup"></el-input>
          </el-form-item>
          <el-form-item label="保质期" prop="fresh">
          <el-input v-model="ruleForm.fresh"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')">确认</el-button>
        </div>
      </el-dialog>
      <el-table
        :data="message"
        style="width: 100%"
        max-height="600">
        <el-table-column
          fixed
          prop="goodsname"
          label="商品名称"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodstype"
          label="商品类别"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsleft"
          label="商品库存"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsprice"
          label="商品价格"
          width="150">
        </el-table-column>
        <el-table-column
          prop="sales"
          label="商品是否售卖"
          width="150">
        </el-table-column>
        <el-table-column
          prop="goodsplace"
          label="商品展示位置"
          width="150">
        </el-table-column>
        <el-table-column
          prop="pictures"
          label="商品图片路径"
          width="150">
        </el-table-column>
        <el-table-column
          prop="season"
          label="一年几季"
          width="150">
        </el-table-column>
        <el-table-column
          prop="flower"
          label="开花期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="fruit"
          label="挂果期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="grownup"
          label="成熟期"
          width="150">
        </el-table-column>
        <el-table-column
          prop="fresh"
          label="保质期"
          width="150">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作一"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作二"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    </div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "two_union",
      props: {
        message: {type: Array, required: true},
        goodsinfo:{type: Array, required: true}
      },
      data() {
        return {
          index:'',
          input: '',
           dialogFormVisible: false,
          ruleForm: {
            name: '',
            type: '',
             left: '',
             price: '',
            sales: '',
            position: '',
            pictures:'',
            fruit:'',
            growup:'',
            fresh:'',
            flower:'',
            season:''
          },
          rules: {
            name: [
              {required: true, message: '请输入商品名称', trigger: 'blur'},
            ],
            price: [
              {required: true, message: '请输入商品价格', trigger: 'blur'}
            ],
            left: [
              {required: true, message: '请输入商品库存', trigger: 'blur'}
            ],
            type: [
              {type: 'string', required: true, message: '请选择商品类型', trigger: 'change'}
            ],
            goodsplace: [
              {required: true, message: '请选择商品展示位置', trigger: 'change'}
            ],
            pictures: [
              {required: true, message: '请输入图片路径', trigger: 'blur'}
            ],
            season: [
              {required: true, message: '请输入商品一年几季', trigger: 'blur'},
            ],
            flowers: [
              {required: true, message: '请输入商品开花期', trigger: 'blur'},
            ],
            fruits: [
              {required: true, message: '请输入商品结果期', trigger: 'blur'},
            ],
            fresh: [
              {required: true, message: '请输入商品保质期', trigger: 'blur'},
            ],
            growup: [
              {required: true, message: '请输入商品成熟期', trigger: 'blur'},
            ],
          },
          Goods_Mark:'',
          Goods_Show:'',
          Goods_Type:'',
          action:''
        }
      },
      methods: {
        addgoods() {
          this.index = this.goodsinfo.length
          this.action = 1
          this.dialogFormVisible = true
          console.log(this.index)
        },
        handleEdit(index, row) {
          this.ruleForm.name = row.goodsname
          this.ruleForm.type = row.goodstype
          this.ruleForm.left = row.goodsleft
          this.ruleForm.price = row.goodsprice
          this.ruleForm.sales = row.sales
          this.ruleForm.position= row.goodsplace
          this.ruleForm.pictures = row.pictures
          this.ruleForm.season = row.season
          this.ruleForm.flower = row.flower
          this.ruleForm.fresh = row.fresh
          this.ruleForm.growup = row.grownup
          this.ruleForm.fruit = row.fruit
          this.index = index
          this.action = 2
           this.dialogFormVisible = true
        },
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if (this.ruleForm.sales === "正常") {
                this.Goods_Mark = '0'
              }
              else {
                this.Goods_Mark = '1'
              }
              if (this.ruleForm.position === "特色区") {
                this.Goods_Show = '1'
              } else if (this.ruleForm.position === "优惠区") {
                this.Goods_Show = '2'
              } else {
                this.Goods_Show = '3'
              }
              if (this.ruleForm.type === "水果") {
                this.Goods_Type = 1
              }
              else if (this.ruleForm.type === "加工品") {
                this.Goods_Type = 2
              }
              else if (this.ruleForm.type === "粮疏") {
                this.Goods_Type = 3
              }
              else if (this.ruleForm.type === "水产") {
                this.Goods_Type = 4
              }
              else if (this.ruleForm.type === "禽畜") {
                this.Goods_Type = 5
              }
              else {
                this.Goods_Type = 6
              }
              if (this.action === 2) {
                var data = {
                  "Goods_PK": this.goodsinfo[this.index].Goods_PK,
                  "Goods_Blossom": this.ruleForm.flower,
                  "Goods_Num": parseInt(this.ruleForm.left),
                  "Goods_Fruit": this.ruleForm.fruit,
                  "Goods_Name": this.ruleForm.name,
                  "Goods_Price": parseFloat(this.ruleForm.price),
                  "Goods_Mark": this.Goods_Mark,
                  "Goods_Season": this.ruleForm.season,
                  "Goods_Picture": this.ruleForm.pictures,
                  "Goods_Reserve_1": this.goodsinfo[this.index].Goods_Reserve_1,
                  "Goods_Mature": this.ruleForm.growup,
                  "Goods_Expiration": this.ruleForm.fresh,
                  "Goods_Show": this.Goods_Show,
                  "Goods_Type": this.Goods_Type
                }
                axios.post('/api/updateGoodsInfo', data).then(response => {
                  if (response.data.status === 1) {
                    this.dialogFormVisible = false
                    this.message[this.index].goodsname = this.ruleForm.name
                    this.message[this.index].goodstype = this.ruleForm.type
                    this.message[this.index].goodsleft = this.ruleForm.left
                    this.message[this.index].goodsprice = this.ruleForm.price
                    this.message[this.index].sales = this.ruleForm.sales
                    this.message[this.index].goodsplace = this.ruleForm.position
                    this.message[this.index].pictures = this.ruleForm.pictures
                    this.message[this.index].flower = this.ruleForm.flower
                    this.message[this.index].fruit = this.ruleForm.fruit
                    this.message[this.index].fresh = this.ruleForm.fresh
                    this.message[this.index].season = this.ruleForm.season
                    this.$message({
                      type: 'success',
                      message: '修改商品信息成功！！！'
                    });
                  } else {
                    this.dialogFormVisible = false
                    this.$message({
                      type: 'error',
                      message: '修改商品信息失败！！！'
                    });
                  }
                })
              }
              else if(this.action === 1)
              {
                var data1 = {
                  "Goods_Blossom": this.ruleForm.flower,
                  "Goods_Num": parseInt(this.ruleForm.left),
                  "Goods_Fruit": this.ruleForm.fruit,
                  "Goods_Name": this.ruleForm.name,
                  "Goods_Price": parseFloat(this.ruleForm.price),
                  "Goods_Mark": this.Goods_Mark,
                  "Goods_Season": this.ruleForm.season,
                  "Goods_Picture": this.ruleForm.pictures,
                  "Goods_Mature": this.ruleForm.growup,
                  "Goods_Expiration": this.ruleForm.fresh,
                  "Goods_Show": this.Goods_Show,
                  "Goods_Type": this.Goods_Type
                }
                axios.post('/api/addGoods', data1).then(response => {
                  if(response.data.status === 1) {
                    this.dialogFormVisible = false
                    this.message[this.index].goodsname = this.ruleForm.name
                    this.message[this.index].goodstype = this.ruleForm.type
                    this.message[this.index].goodsleft = this.ruleForm.left
                    this.message[this.index].goodsprice = this.ruleForm.price
                    this.message[this.index].sales = this.ruleForm.sales
                    this.message[this.index].goodsplace = this.ruleForm.position
                    this.message[this.index].pictures = this.ruleForm.pictures
                    this.message[this.index].flower = this.ruleForm.flower
                    this.message[this.index].fruit = this.ruleForm.fruit
                    this.message[this.index].fresh = this.ruleForm.fresh
                    this.message[this.index].season = this.ruleForm.season
                    this.$message({
                      type: 'success',
                      message: '添加商品信息成功！！！'
                    });
                  } else {
                    this.dialogFormVisible = false
                    this.$message({
                      type: 'error',
                      message: '添加商品信息失败！！！'
                    });
                  }
                })
              }
            }
          });
        },

      }
    }
</script>

<style scoped>
  .el-input{
    width: 20%;
  }
</style>
