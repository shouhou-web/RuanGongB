<template>
  <div id="app">
    <el-container class="main">
      <el-header class="top">
        <div class="container">
          <router-link class="head-logo" to="/home" replace>
            <img src="./assets/img/logo.png" alt="" />
          </router-link>
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
          <!-- <div class="head-msg" @click="toProfileMessage">消息</div> -->
          <div class="head-profile" v-if="token" @click="toProfile">
            <img :src="$store.state.user.imagePath" alt="" />
            <div class="head-profile-text">
              {{ $store.state.user.userName }}的个人空间
            </div>
          </div>
          <div v-if="!token" class="unToken">
            <div class="head-register" @click="openRegister">
              注册
            </div>
            <div class="head-login" @click="openLogin">
              登录
            </div>
          </div>
        </div>
      </el-header>
      <el-main>
        <!-- router-view表示页面组件放的位置 -->
        <router-view />
      </el-main>
    </el-container>
    <div
      type="primary"
      @click="openFullScreen1"
      v-loading.fullscreen.lock="fullscreenLoading"
    ></div>
    <!-- 阴影 -->
    <div
      :class="[open ? '' : 'page-mask-show']"
      @click="open = !open"
      class="page-mask"
    ></div>
    <!-- 悬浮窗 -->
    <div :class="[open ? 'page-state' : 'page-state-hide']" class="open-page">
      <div v-if="type == 0" class="hover-app login">
        <div class="hover-title login-title">
          登录
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-middle login-middle">
          <input
            class="hover-input"
            v-model="username"
            placeholder="用户名或邮箱"
            type="text"
          />
          <input
            class="hover-input"
            v-model="password"
            placeholder="密码"
            type="password"
          />
          <button @click="loginSubmit" class="hover-button">登录</button>
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-bottom login-bottom">
          <p @click="forgetPassword">忘记密码？</p>
          <div style="display:flex;justify-content:center;">
            <div>还没有账户？</div>
            <p @click="change">立即注册</p>
          </div>
        </div>
      </div>
      <div v-else class="hover-app register">
        <div class="hover-title register-title">
          注册
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-middle register-middle">
          <input
            class="hover-input"
            v-model="username"
            placeholder="用户名"
            type="text"
          />
          <input
            class="hover-input"
            v-model="password"
            placeholder="密码"
            type="password"
          />
          <input
            class="hover-input"
            v-model="password2"
            placeholder="请确认密码"
            type="password"
          />
          <button @click="registerSubmit" class="hover-button">注册</button>
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-bottom register-bottom">
          <div>已有账户？</div>
          <p @click="change">登录</p>
        </div>
      </div>
    </div>
    <!-- 消息提示 -->
  </div>
</template>

<script>
import { login, register } from "./network/user";
export default {
  name: "App",
  data() {
    return {
      open: false,
      username: "",
      password: "",
      password2: "",
      search: "",
      type: 0
    };
  },
  computed: {
    fullscreenLoading() {
      return this.$store.state.fullscreenLoading;
    },
    token() {
      if (sessionStorage.getItem("user") != null) {
        console.log("我执行了", sessionStorage.getItem("user"));
        this.$store.commit("login", JSON.parse(sessionStorage.getItem("user")));
      }
      console.log(this.$store.state.token);
      return this.$store.state.token;
    },
    isAdmin() {
      return this.$store.state.user.userIdentity == 1;
    },
    isNew() {
      return this.$store.state.user.isNew == 1;
    },
    isState() {
      return this.$store.state.user.userState == 1;
    }
  },
  methods: {
    openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 2000);
    },
    toProfile() {
      this.$router.push({
        path: "/profile",
        query: { id: this.$store.state.user.userName }
      });
    },
    getLevel(level) {
      return "https://img-static.mihoyo.com/level/level" + level + ".png";
    },
    toProfileMessage() {
      console.log(!this.$store.state.token);
      if (!this.$store.state.token) {
        this.$message({
          message: "登录后才能查看站内消息哦~",
          type: "warning"
        });
        return;
      }
      this.$router.push("/profileMessage");
    },
    openLogin() {
      this.open = true;
      this.type = 0;
    },
    openRegister() {
      this.open = true;
      this.type = 1;
    },
    change() {
      this.type = 1 - this.type;
    },
    registerSubmit() {
      console.log(this.username);
      console.log(this.password);
      if (!this.username || !this.password || !this.password2) {
        this.$message({
          message: "用户名或密码不能为空",
          type: "warning"
        });
        return;
      }
      register(this.username, this.password, this.password2)
        .then(res => {
          console.log(res);
          if (res === 1) {
            this.$message.error("用户名已存在");
            return;
          } else if (res === 2) {
            this.$message.error("两次密码不等");
            return;
          } else if (res == 3) {
            this.$message.error("密码应至少由两种形式的字符组成，长度在6-18位之间");
            return;
          } else {
            this.$message({
              message: "注册成功,请登录",
              type: "success"
            });
            this.change();
          }
        })
        .catch(err => {
          this.$message.error("注册失败了~请检查网络并再次尝试");
          return;
        });
    },
    // 登录
    loginSubmit() {
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
          console.log("(", res, ")");
          if (res == null || res == "") {
            this.$message.error("用户名与密码不匹配");
            return;
          } else {
            // 存储数据
            console.log("传回数据", res);
            sessionStorage.setItem("user", JSON.stringify(res));
            console.log("本地缓存", sessionStorage.getItem("user"));
            this.$store.commit("login", res);
            this.$message({
              message: "恭喜你，登录成功",
              type: "success"
            });
            this.open = false;
            this.username = "";
            this.password = "";
            if (res.isNew == 1) {
              this.$router.push({
                path: "/post",
                query: { postID: 9999 }
              });
            }
          }
        })
        .catch(err => {
          this.$message.error("请检查网络");
          return;
        });
    },
    forgetPassword() {
      this.$message({
        message: "请联系管理员修改密码，电话15010487399",
        type: "success"
      });
    }
  }
};
</script>

<style>
@import "./assets/css/base.css";
@import "./assets/css/post.css";
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  width: 100%;
}

.el-main {
  background-color: rgb(240, 241, 245);
  color: #333;
  text-align: center;
  height: auto;
  padding: 20px 0 !important;
  width: 1100px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 40px;
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
  display: flex;
  align-items: center;
  align-content: center;
}

.top {
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  min-width: 100%;
  width: 100vw;
  padding: 0px !important;
  color: white;
  position: fixed;
  z-index: 10;
}

.top .container a {
  color: #ffffff;
  font-weight: bold;
}

.head-logo {
  width: 111px;
  float: left;
  margin-left: 10px;
  height: 37px;
}

.head-logo img {
  width: 100%;
  height: 100%;
}

.head-href {
  width: 70px;
  float: left;
  margin-left: 30px;
}

.unToken {
  display: flex;
  align-items: center;
  flex-grow: 1;
  justify-content: flex-end;
  margin-right: 30px;
}

.head-msg {
  width: 50px;
  height: 40px;
  margin-left: 570px;
  display: flex;
  align-items: center;
}

.head-profile {
  margin-left: 60px;
  flex-grow: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-right: 30px;
  height: 100%;
}

.head-profile img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
}

.head-profile-text {
  display: flex;
  align-items: center;
  line-height: 40px;
}

.head-register {
  margin-left: 50px;
}

.head-login {
  margin-left: 50px;
  margin-right: 20px;
}

/* 悬浮窗登陆注册 */

.hover-app {
  position: fixed;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background-color: #fff;
  border-radius: 4px;
  width: 375px;
  height: 428px;
  left: 40%;
  top: 20%;
}

.login {
  width: 375px;
  height: 428px;
  left: 40%;
  top: 20%;
}

.register {
  width: 375px;
  height: 428px;
  left: 40%;
  top: 20%;
}

.hover-title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 375px;
  height: 76px;
  font-size: 20px;
  font-weight: normal;
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

.hover-middle {
  display: flex;
  flex-direction: column;
  width: 375px;
  height: 255px;
  padding: 25px 30px;
  justify-content: space-between;
  background-color: #e8ecf3;
  width: 375px;
}

.login-middle {
  height: 255px;
}

.register-middle {
  height: 355px;
}

.hover-input {
  text-align: center;
  height: 50px;
  padding: 15px 20px;
  font-size: 15px;
  color: #111;
  line-height: 1.5;
  outline-style: none;
  border: 0px;
}

.hover-input:focus {
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}

.hover-button {
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  width: 315px;
  height: 50px;
  border-radius: 4px;
  border: 0 solid;
  color: #fff;
  outline-style: none;
}

.hover-bottom {
  display: flex;
  width: 375px;
  align-content: center;
  padding: 20px;
}

.register-bottom {
  height: 65px;
  justify-content: center;
  align-items: center;
}

.login-bottom {
  height: 95px;
  flex-direction: column;
  justify-content: space-between;
}

.hover-bottom p {
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
</style>
