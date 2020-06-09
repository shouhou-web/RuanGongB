<template>
  <div class="viewd">
    <div class="viewd-title">
      我的浏览
    </div>
    <div class="viewd-main">
      <div
        class="discuss-post"
        @click="toPost(item.postID)"
        v-for="(item, index) in viewdPost"
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
          <div class="viewd-time">
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
          <div class="discuss-likes discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/preview.png" alt="" />
            <div class="num">{{ item.browseNum }}</div>
          </div>
          <div class="discuss-comment discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/likes.png" alt="" />
            <div class="num">{{ item.likesNum }}</div>
          </div>
          <div class="discuss-likes discuss-post-bottom-item">
            <img src="../../assets/Icon/Post/comment.png" alt="" />
            <div class="num">{{ item.commentNum }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getBrowsePost } from "../../network/user";
export default {
  name: "Viewed",
  data() {
    return {
      viewdPost: []
    };
  },
  computed: {},
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
  mounted() {
    let userID = this.$store.state.user.userID;
    getBrowsePost(userID)
      .then(res => {
        if (res == null || res == "") {
          this.viewdPost = [];
        } else {
          this.viewdPost = res;
        }
      })
      .catch(err => {
        this.$message.error("获取信息失败了~请检查网络并再次尝试");
      });
  }
};
</script>
<style>
.viewd {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 770px;
}

.viewd-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.viewd-main {
  display: flex;
  flex-direction: column;
  height: auto;
}

.viewd-time {
  color: #999;
  font-size: 14px;
  margin-left: 10px;
}
</style>
