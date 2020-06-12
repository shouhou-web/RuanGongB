<template>
  <!-- 路由发生改变会渲染相应的组件 -->
  <div class="Home">
    <div class="Home-header">
      <div class="Hheader-icon">
        <img :src="HIcon" alt="" />
      </div>
      <div class="Hheader-middle">
        <div class="title">
          {{ Htitle }}
        </div>
        <div class="describe">
          {{ Hdescribe }}
        </div>
      </div>
      <div class="upload">
        <div class="editpost-button" @click="editPostClick">发布帖子</div>
      </div>
    </div>
    <div class="home">
      <div class="home-main"><router-view /></div>
      <div class="home-aside">
        <!-- 侧边导航栏 -->
        <div class="home-nav">
          <div class="title">
            帖子板块
          </div>
          <div class="nav">
            <div
              class="nav-i navigation"
              v-for="(item, index) in navigateList"
              :key="index"
              :class="[current == index ? 'blue' : '']"
              @click="navigateClick(index, item.path)"
            >
              <img :src="item.img" alt="" />
              <p>
                {{ item.title }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  created() {
    console.log("app.vue");
    if (sessionStorage.getItem("homeClick") != null) {
      this.$store.commit("setHomeClick", sessionStorage.getItem("homeClick"));
    }
  },
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
      if (this.$store.state.user.userState == 1) {
        this.$message({
          message: "禁言状态不能发布帖子哦~",
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
    navigateClick(index, path) {
      this.$store.commit("setHomeClick", index);
      this.$router.push(path);
    }
  },
  computed: {
    current() {
      return this.$store.state.homeClick;
    },
    HIcon() {
      return this.navigateList[this.current].img;
    },
    Htitle() {
      return this.navigateList[this.current].title;
    },
    Hdescribe() {
      return this.navigateList[this.current].describe;
    }
  },
  data() {
    return {
      navigateList: [
        {
          title: "搜索帖子",
          describe: "",
          img: require("../../assets/Icon/Home/search.png"),
          path: "/home/searchPost"
        },
        {
          title: "讨论区",
          describe: "你的ddl肝完了吗？就来讨论",
          img: require("../../assets/Icon/Home/discuss.png"),
          path: "/home/discuss"
        },
        {
          title: "课程推荐",
          describe: "哪门课程的ddl最让你头秃？",
          img: require("../../assets/Icon/Home/course.png"),
          path: "/home/course"
        },
        {
          title: "校园周边",
          describe: "只要我在咖啡店里，ddl就找不到我",
          img: require("../../assets/Icon/Home/school.png"),
          path: "/home/school"
        },
        {
          title: "资源共享",
          describe: "我有独特的肝ddl技巧",
          img: require("../../assets/Icon/Home/resource.png"),
          path: "/home/resource"
        },
        {
          title: "刷题板块",
          describe: "这里是不满于ddl的勇者们的战场",
          img: require("../../assets/Icon/Home/exercise.png"),
          path: "/home/exercise"
        }
      ],
      onClick: {
        discuss: true,
        course: false,
        school: false,
        resource: false,
        exercise: false,
        search: false
      }
    };
  }
};
</script>
<style>
.Home-header {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 4px;
  width: 1090px;
  height: 120px;
  margin-top: 20px;
}

.Hheader-icon {
  width: 80px;
  height: 80px;
  display: inline-block;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  position: relative;
  overflow: hidden;
  border-radius: 8px;
  border: 1px solid #ebebeb;
}

.Hheader-icon img {
  width: 100%;
  height: 100%;
}

.Hheader-middle {
  margin-left: 20px;
  flex-grow: 1;
}

.Hheader-middle .title {
  display: flex;
  font-size: 18px;
  line-height: 20px;
}

.Hheader-middle .describe {
  display: flex;
  font-size: 14px;
  line-height: 18px;
  margin-top: 6px;
  color: #999;
}

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

.home-option .editpost-button {
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

.editpost-button-f {
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 50px;
  align-content: center;
  height: 90px;
  width: 280px;
}

.editpost-button {
  width: 130px;
  height: 36px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  padding: 0 18px;
  height: 36px;
  color: #fff !important;
  border-radius: 4px;
  font-size: 16px;
  font-weight: 600;
}

.home-nav {
  background-color: #fff;
  border-radius: 4px;
  height: 400px;
  width: 280px;
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
