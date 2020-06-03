<template>
  <div class="discuss">
    <div
      class="discuss-post"
      @click="toPost(item.PostID)"
      v-for="(item, index) in Post"
      :key="index"
    >
      <div class="discuss-post-header">
        <img class="left" :src="item.imagePath" alt="" />
        <div class="name">
          {{ item.userName }}
        </div>
        <img class="level" :src="getLevel(item)" alt="图片无法加载QAQ" />
        <div class="time">
          {{ item.createTime }}
        </div>
      </div>
      <!-- 中部 -->
      <div class="discuss-post-middle">
        <div class="title">
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
    <router-view></router-view>
  </div>
</template>

<script>
import { request } from "../../network/request";
import { getPostList } from "../../network/post";
export default {
  name: "Discuss",
  data() {
    return {
      Post: [
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
        }
      ]
    };
  },
  computed: {},
  methods: {
    toPost(PostID) {
      this.$router.push({
        path: "/post",
        query: { postID: PostID }
      });
    },
    getLevel(item) {
      return (
        "https://img-static.mihoyo.com/level/level" + item.userLevel + ".png"
      );
    }
  },
  mounted() {
    getPostList(1)
      .then(res => {
        if (res == null)
          this.$message({
            message: "没有该类型帖子的信息~",
            type: "warning"
          });
        else this.post = res;
      })
      .catch(err => {
        this.$message.error("获取信息失败了~请检查您的网络");
      });
  }
};
</script>
<style>
.discuss {
  background-color: #fff;
  margin: 20px 20px 20px 0;
  line-height: 50px;
  width: 790px;
  border-radius: 4px;
}

.discuss-post {
  
  height: 169px;
  padding: 24px 0px 24px 30px;
  display: flex;
  flex-direction: column;
}

.discuss-post-header {
  width: 640px;
  height: 24px;
  display: flex;
  align-content: center;
  align-items: center;
  line-height: 30px;
}

.discuss-post-header .left {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 1px solid #ebebeb;
  vertical-align: top;
}

.discuss-post-header .name {
  font-size: 12px;
  max-width: 180px;
  display: inline-block;
  vertical-align: middle;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-left: 10px;
}

.discuss-post-header .level {
  height: 12px;
  margin-left: 6px;
  font-size: 14px;
  display: inline-block;
  vertical-align: middle;
  height: 1em;
  fill: currentColor;
  overflow: hidden;
}

.discuss-post-header .time {
  color: #ccc;
  margin-left: 10px;
  font-size: 12px;
}

.discuss-post-middle {
  margin: 15px 0;
  width: 640px;
  height: 66px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.discuss-post-middle .title {
  display: flex;
  font-size: 16px;
  line-height: 20px;
  font-weight: 600;
  color: #333;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.discuss-post-middle .preview {
  display: flex;
  width: 640px;
  height: 18px;
  line-height: 18px;
  margin-top: 8px;
  color: #999;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.discuss-post-bottom {
  display: flex;
  line-height: 20px;
  align-items: center;
  justify-content: flex-end;
}

.discuss-post-bottom-item {
  display: flex;
}

.discuss-post-bottom img {
  width: 17.45px;
  height: 16px;
  color: #ccc;
}

.discuss-post-bottom .num {
  margin-left: 8px;
  color: #ccc;
  display: inline-block;
  width: 50px;
  display: flex;
}
</style>
