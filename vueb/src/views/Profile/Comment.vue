<template>
  <div class="comment">
    <div class="comment-title">
      我的评论
    </div>
    <div class="comment-main">
      <div
        class="discuss-post"
        style="justify-content:space-between;"
        @click="toPost(item.postID)"
        v-for="(item, index) in comment"
        :key="index"
      >
        <!-- 头部 -->
        <div class="comment-post-header">
          <img
            class="clock"
            src="../../assets/Icon/Post/clock.png"
            alt="图片无法加载QAQ"
          />
          <div class="time">
            {{ item.createTime }}
          </div>
        </div>
        <!-- 评论内容 -->
        <div class="cp-content">
          {{ item.content }}
        </div>
        <!-- 标题 -->
        <div class="cp-title">回复帖子：{{ item.title }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import { getComment } from "../../network/user";
export default {
  name: "Comment",
  data() {
    return {
      comment: []
    };
  },
  methods: {
    toPost(PostID) {
      if (!this.$store.state.token) {
        this.$message({
          message: "登录后才能浏览帖子哦~",
          type: "warning"
        });
        return;
      }
      this.$router.push({
        path: "/post",
        query: { postID: PostID }
      });
    }
  },
  created() {
    let userID = this.$store.state.user.userID;
    getComment(userID)
      .then(res => {
        if (res == null || res == "") this.comment = [];
        else this.comment = res;
      })
      .catch(err => {
        this.$message.error("获取信息失败了~请检查网络并再次尝试");
      });
  }
};
</script>
<style>
.cp-content {
  font-size: 16px;
  font-weight: 600;
  line-height: 20px;
  text-align: left;
  display: inline-block;
  white-space: nowrap;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}

.cp-title {
  display: flex;
  padding: 15px;
  background-color: #f7f8fc;
  border-radius: 4px;
  color: #999;
  line-height: 18px;
  margin: 0 12px 0 0;
}

.comment {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 770px;
}

.comment-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.comment-main {
  display: flex;
  flex-direction: column;
  height: auto;
}

.comment-post-header {
  width: 640px;
  height: 24px;
  display: flex;
  align-content: center;
  align-items: center;
  line-height: 30px;
}

.comment-post-header .clock {
  width: 16px;
  height: 16px;
}

.comment-post-header .time {
  color: #999;
  margin-left: 10px;
  font-size: 14px;
}

.comment-post-middle {
  margin: 15px 0;
  width: 640px;
  height: 66px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
</style>
