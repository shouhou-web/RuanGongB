<template>
  <div id="app">
    <el-container class="main">
      <el-header class="top">
        <div class="container">
          <router-link class="head-logo" to="/home" replace>Home</router-link>
          <a
            class="head-href"
            href="https://cloud-beihangsoft-cn.e1.buaa.edu.cn/"
            target="_blank"
            >云平台
          </a>
          <a
            class="head-href"
            href="http://course.e2.buaa.edu.cn"
            target="_blank"
          >
            课程中心
          </a>
          <router-link class="head-msg" to="/profileMessage">消息</router-link>
          <div class="profile" v-if="token" @click="toProfile">
            {{ $store.state.user.username }}
          </div>
          <router-link class="head-register" v-if="!token" to="/register">
            注册
          </router-link>
          <div class="head-login" v-if="!token" @click="open = !open">
            登录
          </div>
        </div>
      </el-header>
      <el-main>
        <!-- router-view表示页面组件放的位置 -->
        <router-view />
      </el-main>
    </el-container>
    <!-- 阴影 -->
    <div
      :class="[open ? '' : 'page-mask-show']"
      @click="open = !open"
      class="page-mask"
    ></div>
    <!-- 悬浮窗 -->
    <div :class="[open ? 'page-state' : 'page-state-hide']" class="open-page">
      <div class="login">
        <div class="login-title">
          登录
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="login-middle">
          <input
            class="login-input"
            v-model="username"
            placeholder="用户名或邮箱"
            type="text"
          />
          <input
            class="login-input"
            v-model="password"
            placeholder="密码"
            type="password"
          />
          <button @click="submit" class="login-button">登录</button>
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="login-bottom">
          <a href="">忘记密码？</a>
          <a href="">还没有账户？立即注册</a>
        </div>
      </div>
    </div>
    <!-- 消息提示 -->
  </div>
</template>

<script>
import { login } from "./network/user";
export default {
  name: "App",
  data() {
    return {
      open: false,
      username: "",
      password: ""
    };
  },
  computed: {
    token() {
      return this.$store.state.token;
    }
  },
  methods: {
    toProfile() {
      this.$router.push({
        path: "/profile",
        query: { id: this.$store.state.user.username }
      });
    },
    toProfileMessage() {
      this.$router.push("/profileMessage");
    },
    submit() {
      console.log(this.username);
      console.log(this.password);
      if (!this.username || !this.password) {
        this.$message({
          message: "用户名或密码不能为空",
          type: "warning"
        });
        return;
      }
      login(this.username, this.password)
        .then(res => {
          console.log(res);
          if (res == null){
             this.$message.error("用户名与密码不匹配");
             return;
          }
          else {
            this.$store.commit('login',res)
            this.$message({
              message: "恭喜你，登陆成功",
              type: "success"
            });
            this.open = false;
            this.username = "";
            this.password = "";
          }
        })
        .catch(err => {
          this.$message.error("请检查网络");
            return;
        });
    }
  }
};
</script>

<style>
@import "./assets/css/base.css";
/* 悬浮窗登陆注册 */

.login {
  position: fixed;
  width: 375px;
  height: 428px;
  left: 40%;
  top: 20%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #fff;
  border-radius: 4px;
}

.login-div {
  width: 100%;
  height: 1.5px;
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
}

.login-title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 375px;
  height: 76px;
  font-size: 20px;
  font-weight: normal;
}

.login-middle {
  display: flex;
  flex-direction: column;
  width: 375px;
  height: 255px;
  padding: 25px 30px;
  justify-content: space-between;
  background-color: #e8ecf3;
}

.login-input {
  text-align: center;
  height: 50px;
  padding: 15px 20px;
  font-size: 15px;
  color: #111;
  line-height: 1.5;
  outline-style: none;
  border: 0px;
}

.login-input:focus {
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}

.login-button {
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  width: 315px;
  height: 50px;
  border-radius: 4px;
  border: 0 solid;
  color: #fff;
  outline-style: none;
}

.login-bottom {
  display: flex;
  flex-direction: column;
  width: 375px;
  height: 95px;
  align-content: center;
  padding: 20px;
  justify-content: space-between;
}

.login-bottom a {
  cursor: pointer;
  color: #426799;
  text-decoration: none;
  background-color: transparent;
}
/*右侧遮罩层  */

.page-mask {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  opacity: 0.3;
}

.page-mask-show {
  display: none;
}

.page-state {
  z-index: 4;
}

.page-state-hide {
  display: none;
}

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
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  min-width: 1903px;
  width: 1903px;
  padding: 0px !important;
  color: white;
}

.top .container a {
  color: #ffffff;
  font-weight: bold;
}

.head-logo {
  width: 50px;
  float: left;
  margin-left: 10px;
}

.head-href {
  width: 70px;
  float: left;
  margin-left: 30px;
}

.head-msg {
  width: 50px;
  height: 40px;
  float: left;
  margin-left: 570px;
}

.head-profile {
  float: left;
  margin-left: 60px;
}

.head-register {
  float: left;
  margin-left: 50px;
}

.head-login {
  float: left;
  margin-left: 20px;
}
</style>
