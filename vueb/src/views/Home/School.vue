<template>
  <div class="school">
    <h1>校园周边</h1>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "School",
  data() {
    return {
    };
  },
  methods: {
    
  }
};
</script>
<style>
.school{
  background-color: #fff;
  margin: 20px 20px 20px 0;
  line-height: 50px;
  width: 790px;
  border-radius: 4px;
}
</style>
