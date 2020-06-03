<template>
  <div class="course">
    <h1>课程推荐</h1>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Course",
  data() {
    return {
    };
  },
  methods: {
    
  }
};
</script>
<style>
.course{
  background-color: #fff;
  margin: 20px 20px 20px 0;
  line-height: 50px;
  width: 790px;
  border-radius: 4px;
}
</style>
