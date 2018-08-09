<template>
  <div>
    <div class = "head_picture">
      <img src = "../../../static/image/head_picture.jpg" class="img-thumbnail">
    </div>
    <hr style="height:5px;border:none;border-top:8px groove skyblue;width:52%;margin-top:10px;margin-bottom: 10px;" />

    <div class = "test">

      <ul class="list-group">
        <li class="list-group-item" v-for="item in newlists" :key="item.id">
          <span class="badge">{{item.time}}</span>
          <a href = "../../pages/details.html" target="_blank">{{item.title}}</a>
        </li>
      </ul>
    </div>
    <div class = "Page">
      <div class = "pages">
        <ul class="pagination pagination-lg">
          <li><a href="" @click = "lastPage">&laquo;</a></li>
          <li v-for="item in pageLists" :key="item.id"><span @click="switchPage(item.page)">{{item.page}}</span></li>
          <li><a href="#">&raquo;</a></li>
        </ul>
      </div>
    </div>
    <div style = "clear:both;"></div>
  </div>
</template>

<script>
export default{
  props: {
    newlists: {
      type: Array,
      required: true,
      default: function () {
        return []
      }
    },
    pageLists: {
      type: Array,
      required: true,
      default: function () {
        return []
      }
    }
  },
  methods: {
    getNews: function () {
      this.$emit('getNews')
    },
    switchPage: function (nowPage) {
      this.$emit('switchPage', nowPage)
    },
    lastPage: function () {
      this.$emit('lastPage')
    }
  },
  data () {
    return {
    }
  }
}
</script>
