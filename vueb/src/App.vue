<template>
  <div id="app">
    <!-- 路由发生改变会渲染相应的组件 -->
    <div class="top">
      <router-link class="logo" to="/home" replace>Home</router-link>
      <a class="href" href="/home">关联网站</a>
      <router-link class="msg" to="/profileMessage">
        <img src="./assets/Icon/Home/msg.png" alt="" />
      </router-link>
      <router-link class="profile" :to="'/profile/' + this.UserID"
        >Profile</router-link
      >
    </div>
    <!-- router-view表示页面组件放的位置 -->
    <ul class="nav-l">
      <li @click="editPostClick">发布帖子</li>
      <li @click="discussClick">讨论区</li>
      <li @click="courseClick">课程推荐</li>
      <li @click="schoolClick">校园周边</li>
      <li @click="resourceClick">资源共享</li>
      <li @click="exerciseClick">刷题板块</li>
    </ul>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  methods: {
    homeClick() {
      this.$router.push({ path: "/home" });
    },
    editPostClick() {
      this.$router.push("/editPost");
    },
    discussClick() {
      this.$router.push("/discuss");
    },
    courseClick() {
      this.$router.push("/course");
    },
    schoolClick() {
      this.$router.push("/school");
    },
    resourceClick() {
      this.$router.push("/resource");
    },
    exerciseClick() {
      this.$router.push("/exercise");
    }
  },
  data() {
    return {
      UserID: "shouhou",
      profile: {
        path: "/profile",
        query: {
          name: "shouhou",
          age: "20"
        }
      }
    };
  }
};
</script>

<style>
@import "./assets/css/base.css";
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.top {
  padding: 30px;
  display: flex;
  background: palegreen;
}

.top a {
  font-weight: bold;
  color: #2c3e50;
}

.top .logo {
  width: 10%;
  margin-left: 5%;
  margin-right: 5%;
}

.top .href {
  width: 10%;
  margin-left: 5%;
  margin-right: 25%;
}

.top .msg img {
  width: 15%;
}

.top .profile {
  width: 10%;
}

.nav-l {
  position: absolute;
  border-right: solid 1px #e6e6e6;
  list-style: none;
  margin: 0;
  padding-left: 0;
  background-color: #fff;
  width: 200px;
  min-height: 400px;
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
</style>
