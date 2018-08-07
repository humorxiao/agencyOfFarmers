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
          <a href = "../../../details.html" target="_blank">{{item.title}}</a>
        </li>
      </ul>
    </div>
    <div class = "Page">
      <div class = "pages">
        <ul class="pagination pagination-lg">
          <li><a href="">&laquo;</a></li>
          <li><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">...</a></li>
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
      required: true
    }
  }
}
</script>
