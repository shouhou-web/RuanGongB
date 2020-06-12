<template>
  <div class="profile">
    <div class="profile-title">
      <img
        class="profile-title-left"
        :src="$store.state.user.imagePath"
        alt="图片无法加载QAQ"
      />
      <div class="profile-title-middle">
        <div class="ptm-title">
          <div class="ptm-username">
            {{ $store.state.user.userName }}
          </div>
          <img class="ptm-level" :src="getLevel" alt="图片无法加载QAQ" />
          <div class="ptm-id">用户ID：{{ $store.state.user.userID }}</div>
        </div>
        <div class="ptm-word">
          <svg class="icon-signature" viewBox="0 0 1024 1024">
            <path
              d="M599.771429 0H248.685714C151.698286 0 73.142857 76.361143 73.142857 170.642286v682.642285C73.142857 947.565714 151.698286 1024 248.685714 1024h526.628572c96.914286 0 175.542857-76.434286 175.542857-170.642286v-512C950.857143 152.795429 793.673143 0 599.771429 0z"
              fill="#FB7199"
            ></path>
            <path
              d="M658.285714 292.571429H365.714286a73.142857 73.142857 0 0 0 0 146.285714h292.571428a73.142857 73.142857 0 0 0 0-146.285714zM731.428571 585.142857H292.571429a73.142857 73.142857 0 0 0 0 146.285714h438.857142a73.142857 73.142857 0 0 0 0-146.285714z"
              fill="#FFFFFF"
            ></path>
          </svg>
          <div class="ptm-word-text">
            {{ $store.state.user.userWord }}
          </div>
        </div>
        <router-link to="/profile/editInfo" class="ptm-button"
          >编辑资料</router-link
        >
      </div>
      <!-- <div class="profile-title-right">
        <div class="ptr-likes">
          {{ $store.state.user.likesNum }}
        </div>
        <div class="ptr-likestest">获赞</div>
      </div> -->
    </div>
    <div class="profile-main">
      <div class="profile-aside">
        <header class="pa-header">个人中心</header>
        <ul class="pa-list">
          <li
            v-for="(item, index) in paList"
            @click="palClick(index, item.to)"
            :key="item.to"
          >
            <div class="pal-item">
              <img class="pal-img" :src="item.img" alt="" />
              <div :class="[item.current ? 'blue' : '']" class="pal-text">
                {{ item.name }}
              </div>
            </div>
          </li>
          <li @click="logout">
            <div class="pal-item">
              <img
                class="pal-img"
                src="../../assets/Icon/Profile/7.png"
                alt=""
              />
              <div class="pal-text">
                退出登录
              </div>
            </div>
          </li>
        </ul>
      </div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      paList: [
        {
          name: "我的发帖",
          img: require("../../assets/Icon/Profile/1.png"),
          to: "/profile/myPost",
          current: true
        },
        {
          name: "我的浏览",
          img: require("../../assets/Icon/Profile/2.png"),
          to: "/profile/viewed",
          current: false
        },
        {
          name: "我的评论",
          img: require("../../assets/Icon/Profile/3.png"),
          to: "/profile/comment",
          current: false
        },
        {
          name: "我的点赞",
          img: require("../../assets/Icon/Profile/4.png"),
          to: "/profile/liked",
          current: false
        }
      ]
    };
  },
  computed: {
    getLevel() {
      return (
        "https://img-static.mihoyo.com/level/level" +
        this.$store.state.user.userLevel +
        ".png"
      );
    },
    isAdmin() {
      return this.$store.state.user.userIdentity == 1;
    }
  },
  components: {},
  methods: {
    palClick(id, to) {
      console.log(id, to);
      for (let i = 0; i < this.paList.length; i++)
        this.paList[i].current = false;
      this.$store.commit("setProfileClick", id);
      this.paList[id].current = true;
      this.$router.push(to);
    },
    logout() {
      this.$store.commit("logout");
      sessionStorage.clear();
      this.$message({
        message: "成功退出账号",
        type: "success"
      });
      this.$router.push({ path: "/home" });
    }
  },
  created() {
    console.log('profile.vue')
    let common = {
      name: "我的等级",
      img: require("../../assets/Icon/Profile/5.png"),
      to: "/profile/profileLevel",
      current: false
    };
    let admin = {
      name: "封禁用户",
      img: require("../../assets/Icon/Profile/5.png"),
      to: "/profile/viewProfile",
      current: false
    };
    let end = {
      name: "编辑资料",
      img: require("../../assets/Icon/Profile/6.png"),
      to: "/profile/editInfo",
      current: false
    };
    if (this.$store.state.user.userIdentity == 1) this.paList.push(admin);
    else this.paList.push(common);
    this.paList.push(end);
    if (sessionStorage.getItem("profileClick") != null) {
      console.log(sessionStorage.getItem("profileClick"))
      this.paList[sessionStorage.getItem("profileClick")].current = true;
    }
  }
};
</script>
<style>
.ptm-word {
  display: flex;
  align-items: center;
}

.icon-signature {
  font-size: 14px;
  height: 15px;
  margin-right: 5px;
}

.profile-main {
  display: flex;
}

.profile-aside {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  border-radius: 4px;
  width: 280px;
  margin: 0 20px;
  height: 410px;
}

.pa-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 360px;
}

.pal-item {
  display: flex;
  align-items: center;
  -webkit-box-align: center;
  line-height: 16px;
  width: 220px;
  height: 50px;
  padding: 0 20px;
}

.blue {
  color: #00c3ff;
}

.pal-item:hover {
  color: #00c3ff;
}

.pal-text {
  margin-left: 15px;
}

.pal-img {
  width: 18px;
  height: 19px;
}

.pa-list {
  list-style: none;
  padding: 0 30px 10px;
}

.pa-header {
  display: flex;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.profile {
  display: flex;
  flex-direction: column;
  height: auto;
}

.profile-title {
  display: flex;
  align-items: center;
  line-height: 30px;
  width: 1070px;
  height: 160px;
  margin: 20px 20px;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 4px;
}

.profile-title-left {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  border: 1px solid #ebebeb;
  vertical-align: top;
  margin-right: 30px;
}

.profile-title-middle {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  -webkit-box-flex: 1;
  flex-grow: 1;
  width: 470px;
  height: 120px;
  padding: 10px;
}

.ptm-title {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  padding: 6px 0;
  width: 470px;
  height: 28px;
}

.ptm-username {
  font-size: 16px;
  font-weight: 600;
  line-height: 16px;
}

.ptm-level {
  margin-left: 10px;
  height: 16px;
  width: 29.41px;
  flex-shrink: 0;
  fill: currentColor;
  overflow: hidden;
}

.ptm-button {
  background-color: #fff;
  color: #00b2ff;
  border: 1px solid #00c3ff;
  border-radius: 4px;
  transition-duration: 0.2s;
  transition-property: boder-color, color;
  display: inline-flex;
  -webkit-box-align: center;
  align-items: center;
  justify-content: center;
  outline: none;
  width: 86px;
  height: 34px;
}

.profile-title-right {
  display: flex;
  flex-direction: column;
  height: 50px;
  -webkit-box-align: center;
  align-items: center;
}

.ptm-id {
  margin-left: 10px;
  font-size: 13px;
  color: #ccc;
}

.el-main {
  background-color: #f0f1f5;
  height: auto;
}
</style>
