<template>
  <div class="liked">
    <div class="liked-title">
      我的点赞
    </div>
    <div class="liked-main">
      <div
        class="discuss-post"
        @click="toPost(item.postID)"
        v-for="(item, index) in liked"
        :key="index"
      >
        <div class="discuss-post-header">
          <img class="left" :src="item.imagePath" alt="" />
          <div class="name">
            {{ item.userName }}
          </div>
          <img
            class="level"
            :src="getLevel(item.userLevel)"
            alt="图片无法加载QAQ"
          />
          <div class="time">
            {{ item.createTime }}
          </div>
        </div>
        <!-- 中部 -->
        <div class="discuss-post-middle">
          <div class="title navigation">
            {{ item.title }}
          </div>
          <div class="preview" v-html="item.content"></div>
        </div>
        <div class="discuss-post-bottom">
          <div class="discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/preview.png" alt="" />
            <div class="num">{{ item.browseNum }}</div>
          </div>
          <div class="discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/likes_cur.png" alt="" />
            <div class="num">{{ item.likesNum }}</div>
          </div>
          <div class="discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/comment.png" alt="" />
            <div class="num">{{ item.commentNum }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getLikedPost} from "../../network/user"
export default {
  name: "Liked",
  data() {
    return {
      liked: []
    };
  },
  methods: {
    getLevel(level) {
      return "https://img-static.mihoyo.com/level/level" + level + ".png";
    },
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
  components: {},
  created() {
    let userID = this.$store.state.user.userID;
    getLikedPost(userID)
      .then(res => {
        if (res == null || res == "") {
          this.liked = [];
        } else {
          this.liked = res;
        }
      })
      .catch(err => {
        this.$message.error("获取信息失败了~请检查网络并再次尝试");
      });
  }
};
</script>
<style>
.liked {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 770px;
}

.liked-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.liked-main {
  display: flex;
  flex-direction: column;
  height: auto;
}
</style>
