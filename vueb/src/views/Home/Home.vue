<template>
  <!-- 路由发生改变会渲染相应的组件 -->
  <div class="home">
    <el-container>
      <el-aside width="200px">
        <ul class="nav-l">
          <li @click="editPostClick">发布帖子</li>
          <li :class="[onClick.discuss ? 'blue' : '']" @click="discussClick">
            讨论区
          </li>
          <li :class="[onClick.course ? 'blue' : '']" @click="courseClick">
            课程推荐
          </li>
          <li :class="[onClick.school ? 'blue' : '']" @click="schoolClick">
            校园周边
          </li>
          <li :class="[onClick.resource ? 'blue' : '']" @click="resourceClick">
            资源共享
          </li>
          <li :class="[onClick.exercise ? 'blue' : '']" @click="exerciseClick">
            刷题板块
          </li>
        </ul>
      </el-aside>
      <el-main><router-view /></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  methods: {
    homeClick() {
      this.$router.push({ path: "/home" });
    },
    editPostClick() {
      this.$router.push({path:"/editPost"});
    },
    discussClick() {
      this.closeAll();
      this.onClick.discuss = true;
      this.$router.push("/home/discuss");
    },
    courseClick() {
      this.closeAll();
      this.onClick.course = true;
      this.$router.push("/home/course");
    },
    schoolClick() {
      this.closeAll();
      this.onClick.school = true;
      this.$router.push("/home/school");
    },
    resourceClick() {
      this.closeAll();
      this.onClick.resource = true;
      this.$router.push("/home/resource");
    },
    exerciseClick() {
      this.closeAll();
      this.onClick.exercise = true;
      this.$router.push("/home/exercise");
    },
    closeAll() {
      for (let key in this.onClick) this.onClick[key] = false;
    }
  },
  data() {
    return {
      onClick: {
        discuss: true,
        course: false,
        school: false,
        resource: false,
        exercise: false
      }
    };
  }
};
</script>
<style>
.home .nav-l {
  width: 200px;
  min-height: 100%;
}

.home .nav-l li:hover {
  color: #409eff;
}

.blue {
  color: #409eff;
}

.home .nav-l li {
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

.home .el-aside {
  color: #333;
  text-align: center;
  line-height: 200px;
  background-color: #fff;
}

.home .el-main {
  color: #333;
  text-align: center;
  line-height: 160px;
  background-color: #fff;
}
</style>
