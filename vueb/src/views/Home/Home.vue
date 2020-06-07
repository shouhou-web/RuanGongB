<template>
  <!-- 路由发生改变会渲染相应的组件 -->
  <div class="home">
    <div class="home-main"><router-view /></div>
    <div class="home-aside">
      <div class="home-option">
        <div class="title">
          可选操作
        </div>
        <div class="main">
          <div class="editpost" @click="editPostClick">发布帖子</div>
        </div>
      </div>
      <!-- 侧边导航栏 -->
      <div class="home-nav">
        <div class="title">
          帖子板块
        </div>
        <div class="nav">
          <div
            class="nav-i navigation"
            :class="[onClick.search ? 'blue' : '']"
            @click="searchClick"
          >
            <!-- <img src="../../assets/Icon/Home/discuss.png" alt="" /> -->
            <img src="../../assets/Icon/Home/discuss.png" alt="" />
            <p>
              搜索帖子
            </p>
          </div>
          <div
            class="nav-i navigation"
            :class="[onClick.discuss ? 'blue' : '']"
            @click="discussClick"
          >
            <!-- <img src="../../assets/Icon/Home/discuss.png" alt="" /> -->
            <img src="../../assets/Icon/Home/discuss.png" alt="" />
            <p>
              讨论区
            </p>
          </div>
          <div
            class="nav-i navigation"
            :class="[onClick.course ? 'blue' : '']"
            @click="courseClick"
          >
            <img src="../../assets/Icon/Home/course.png" alt="" />
            <p>
              课程推荐
            </p>
          </div>
          <div
            class="nav-i navigation"
            :class="[onClick.school ? 'blue' : '']"
            @click="schoolClick"
          >
            <img src="../../assets/Icon/Home/school.png" alt="" />
            <p>
              校园周边
            </p>
          </div>
          <div
            class="nav-i navigation"
            :class="[onClick.resource ? 'blue' : '']"
            @click="resourceClick"
          >
            <img src="../../assets/Icon/Home/resource.png" alt="" />
            <p>
              资源共享
            </p>
          </div>
          <div
            class="nav-i navigation"
            :class="[onClick.exercise ? 'blue' : '']"
            @click="exerciseClick"
          >
            <img src="../../assets/Icon/Home/exercise.png" alt="" />
            <p>
              刷题板块
            </p>
          </div>
        </div>
      </div>
    </div>
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
      if (!this.$store.state.token) {
        this.$message({
          message: "登录后才能发布帖子哦~",
          type: "warning"
        });
        return;
      }
      this.$router.push({
        path: "/editPost",
        query: {
          post: null
        }
      });
    },
    searchClick() {
      this.closeAll();
      this.onClick.search = true;
      this.$router.push("/home/searchPost")
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
        exercise: false,
        search : false
      }
    };
  }
};
</script>
<style>
.el-aside {
  padding: 0 !important;
  margin: 0 !important;
}

.home,
.home-main {
  background-color: rgb(240, 241, 245);
}

.home-aside {
  margin: 20px 0;
  background-color: rgb(240, 241, 245); 
}

.home-option {
  width: 280px;
  display: flex;
  flex-direction: column;
  height: 140px;
  margin: 0 0 20px 0;
  border-radius: 4px;
  background-color: #fff;
}

.home-option .title,
.home-nav .title {
  display: flex;
  height: 50px;
  border-bottom: 1px solid #ebebeb;
  padding: 0 20px;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
}

.home-option .main {
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 50px;
  align-content: center;
  height: 90px;
}

.home-option .editpost {
  width: 130px;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #ffe733;
  padding: 0 18px;
  height: 36px;
  color: #663c00;
  background-color: #ffe14c;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 600;
}

.home-nav {
  background-color: #fff;
  border-radius: 4px;
  height: 400px;
}

.home-nav .nav {
  padding: 10px 20px 20px 20px;
  height: 360px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.home-nav .nav-i {
  display: flex;
  align-items: center;
}

.home-nav .nav-i img {
  width: 48px;
  height: 48px;
  margin-right: 20px;
  margin-left: 20px;
}

.home {
  display: flex;
}

.blue {
  color: #00c3ff;
}
</style>
