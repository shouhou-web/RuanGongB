<template>
  <div class="mypost">
    <div class="mypost-title">
      我的发帖
    </div>
    <div class="mypost-main"></div>
  </div>
</template>

<script>
export default {
  name: "MyPost",
  computed: {},
  components: {},
  created() {}
};
</script>
<style>
.mypost {
  display: flex;
  background-color: #fff;
  width: 700px;
}

.mypost-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}
</style>
