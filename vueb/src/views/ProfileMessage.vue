<template>
  <div class="scholl">
    <el-container>
      <el-aside width="200px"
        ><ul class="nav-l">
          <li @click="editPostClick">回复我的</li>
          <li @click="discussClick">收到的赞</li>
        </ul></el-aside
      >
      <el-main><router-view /></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "ProfileMessage",
  data() {
    return {};
  },
  methods: {}
};
</script>

<style>
.nav-l {
  
  width: 200px;
  min-height: 100%;
}

.nav-l li:hover {
  color: #409eff;
}

.nav-l li {
  font-size: 14px;
  padding: 0 20px;
  cursor: pointer;
  transition: border-color 0.3s, background-color 0.3s, color 0.3s;
  box-sizing: border-box;
  height: 56px;
  line-height: 56px;
  position: relative;
  -webkit-box-sizing: border-box;
  white-space: nowrap;
  list-style: none;
}

.el-aside {
  background-color: #fff;
}

.el-main {
  background-color: #fff;
}
</style>
