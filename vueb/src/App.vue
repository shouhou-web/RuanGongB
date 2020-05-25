<template>
  <div id="app">
    <el-container class="main">
      <el-header class="top">
        <div class="container">
          <router-link class="logo" to="/home" replace>Home</router-link>
          <a
            class="href"
            href="https://cloud-beihangsoft-cn.e1.buaa.edu.cn/"
            target="_blank"
            >云平台
          </a>
          <a class="href" href="http://course.e2.buaa.edu.cn" target="_blank">
            课程中心
          </a>
          <router-link class="msg" to="/profileMessage">消息</router-link>
          <div class="profile" v-if="token" @click="toProfile">
            个人空间
          </div>
          <router-link class="register" v-if="!token" to="/register">注册</router-link>
          <router-link class="login" v-if="!token" :to="{path:'/login'}">登录</router-link>
        </div>
      </el-header>
      <el-main>
        <!-- router-view表示页面组件放的位置 -->
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      token: false
    };
  },
  methods: {
    toProfile() {
      this.$router.push({ path: "/profile", query: { id: "oraora" } });
    },
    toProfileMessage() {
      this.$router.push("/profileMessage");
    }
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
  height: 100%;
  width: 1880px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px;
  padding: 0px !important;
  width: 1100px;
  margin-left: auto;
  margin-right: auto;
}

/* 导航相关的样式 */
.container {
  text-align: center;
  line-height: 60px;
  height: 60px;
  font-weight: bold;
  width: 1100px;
  margin-left: auto;
  margin-right: auto;
}

.top {
  background-image: linear-gradient(to right,#a6c1ee,#fbc2eb);
  min-width: 1903px;
  width: 1903px;
  padding: 0px !important;
}

.top .container a {
  color: #ffffff;
  font-weight: bold;
}

.top .container .logo {
  width: 50px;
  float: left;
  margin-left: 60px;
}

.top .container .href {
  width: 70px;
  float: left;
  margin-left: 30px;
}

.top .container .msg {
  width: 50px;
  height: 40px;
  float: left;
  margin-left: 570px;
}

.top .container .profile {
  float: left;
  margin-left: 60px;
}

.top .container .register {
  float: left;
  margin-left: 50px;
}

.top .container .login {
  float: left;
  margin-left: 20px;
}
</style>
