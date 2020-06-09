<template>
  <div class="viewprofile">
    <div class="viewprofile-title">
      封禁用户列表
    </div>
    <div class="viewprofile-main">
      <div
        class="discuss-post"
        @click="manageProfile(item.userID)"
        v-for="(item, index) in userList"
        :key="index"
      >
        <!-- 头部 -->
        <div class="viewprofile-post-header">
          <img
            class="clock"
            src="../../assets/Icon/Post/clock.png"
            alt="图片无法加载QAQ"
          />
          <div class="time">
            {{ item.createTime }}
          </div>
          <!-- <div class="viewprofile-post-middle"> -->
          <div class="userName">用户：{{ item.userName }}</div>
          <div class="adminName">处理人：{{ item.adminName }}</div>
          <!-- </div> -->
        </div>

        <!-- 评论内容 -->
        <div class="vp-content">封禁时间：{{ item.duration }}天</div>
        <!-- 标题 -->
        <div class="vp-title">封禁原因：{{ item.content }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getManagedUser } from "../../network/manage";
export default {
  name: "ViewProfile",
  data() {
    return {
      userList: [
        {
          userID: 10001,
          userName: "用户名",
          adminName: "管理员名",
          createTime: "05-24",
          duration: 7,
          content: "这个人不好好写大作业"
        },
        {
          userID: 10001,
          userName: "用户名",
          adminName: "管理员名",
          createTime: "05-24",
          duration: 3,
          content: "封禁原因"
        },
        {
          userID: 10001,
          userName: "用户名",
          adminName: "管理员名",
          createTime: "05-24",
          duration: 100,
          content: "封禁原因"
        }
      ]
    };
  },
  created() {
    getManagedUser()
      .then(res => {
        console.log(res)
        if (res == null) this.userList = [];
        else this.userList = res;
      })
      .catch(err => {
        this.$message.error("获取信息失败");
      });
  },
  methods: {
    manageProfile(userID) {
      this.$router.push({
        path: "/manageProfile",
        query: { userID: userID }
      });
    }
  }
};
</script>
<style>
.vp-content {
  display: flex;
  font-size: 16px;
  font-weight: 600;
  line-height: 20px;
  overflow: hidden;
  margin-top: 10px;
}

.vp-title {
  display: flex;
  padding: 15px;
  background-color: #f7f8fc;
  border-radius: 4px;
  color: #999;
  line-height: 18px;
  margin: 12px 12px 0 0;
}

.viewprofile {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 700px;
}

.viewprofile-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.viewprofile-main {
  display: flex;
  flex-direction: column;
  height: auto;
}

.viewprofile-post-header {
  width: 640px;
  height: 24px;
  display: flex;
  align-content: center;
  align-items: center;
  line-height: 30px;
}

.viewprofile-post-header .clock {
  width: 16px;
  height: 16px;
}

/* .viewprofile-post-middle {
  display: flex;
  line-height: 16px;
  align-items: center;
  margin-top: 10px;
  justify-content: flex-start;
} */

.userName,
.adminName {
  color: #999;
  font-size: 14px;
  line-height: 16px;
  display: flex;
  margin-left: 10px;
}

.viewprofile-post-header .time {
  color: #999;
  margin-left: 10px;
  font-size: 14px;
}
</style>
