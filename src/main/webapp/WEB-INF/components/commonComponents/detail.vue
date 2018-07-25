<template>
  <div>
    <div class = "head_picture">
      <img src = "../../../static/image/details.jpg" class="img-thumbnail">
    </div>
    <hr style="height:5px;border:none;border-top:8px groove skyblue;width:52%;margin-top:10px;" />

    <div class = "test">
      <h2>{{title}}</h2>
      <div class = "content">
        <h4>{{timeAndSource}}</h4>
        <p>{{content}}</p>
      </div>
    </div>
  </div>
</template>
<script>
export default{
  props: {
    title: {
      type: String,
      required: true
    },
    timeAndSource: {
      type: String,
      required: true
    },
    content: {
      type: String,
      required: true
    }
  }
}
</script>
