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
      liked: [
        {
          postID: 123,
          userID: 233,
          userName: "用户名",
          userLevel: 1,
          createTime: "05-24",
          content: `<div>帖子内容啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦</div>
            </br>`,
          imagePath:
            "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
          title: "写大作业好开心啊",
          likesNum: 0,
          browseNum: 1,
          commentNum: 2
        },
        {
          postID: 123,
          userID: 233,
          userName: "用户名",
          userLevel: 3,
          createTime: "05-24",
          content: `<div>帖子内容</div>`,
          imagePath: "https://img-static.mihoyo.com/avatar/avatar30060.png",
          title: "vue动态绑定class的几种方式",
          likesNum: 0,
          browseNum: 1,
          commentNum: 2
        },
        {
          postID: 123,
          userID: 233,
          userName: "用户名",
          userLevel: 3,
          createTime: "05-24",
          content: `<div>帖子内容</div>`,
          imagePath: "https://img-static.mihoyo.com/avatar/avatar30060.png",
          title: "vue动态绑定class的几种方式",
          likesNum: 0,
          browseNum: 1,
          commentNum: 2
        },
        {
          postID: 123,
          userID: 233,
          userName: "用户名",
          userLevel: 3,
          createTime: "05-24",
          content: `<div>帖子内容</div>`,
          imagePath: "https://img-static.mihoyo.com/avatar/avatar30060.png",
          title: "vue动态绑定class的几种方式",
          likesNum: 0,
          browseNum: 1,
          commentNum: 2
        }
      ]
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
  width: 700px;
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
