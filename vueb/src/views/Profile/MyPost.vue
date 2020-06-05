<template>
  <div class="mypost">
    <div class="mypost-title">
      我的发帖
    </div>
    <div class="mypost-main">
      <div
        class="discuss-post"
        @click="toPost(item.postID)"
        v-for="(item, index) in myPost"
        :key="index"
      >
        <div class="mypost-post-header">
          <img class="clock" src="../../assets/Icon/Post/clock.png" alt="图片无法加载QAQ" />
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
    </div>
  </div>
</template>

<script>
import {getMyPost} from '../../network/user'
export default {
  name: "MyPost",
  data() {
    return {
      myPost: [
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
    }
  },
  computed: {},
  methods:{
    getLevel(level) {
      return (
        "https://img-static.mihoyo.com/level/level" + level + ".png"
      );
    },
    toPost(PostID) {
      if (!this.$store.state.token){
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
    },
  },
  components: {},
  created() {
    let userID = this.$store.state.user.userID;
    getMyPost(userID).then(res => {
      if (res == null || res == ''){
        this.myPost = [];
      }else{
        this.myPost = res;
      }
    }).catch(err => {
      this.$message.error("获取信息失败了~请检查网络并再次尝试")
    })
  }
};
</script>
<style>
.mypost {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 700px;
}

.mypost-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.mypost-main{
  display: flex;
  flex-direction: column;
  height: auto;
}

.mypost-post-header {
  width: 640px;
  height: 24px;
  display: flex;
  align-content: center;
  align-items: center;
  line-height: 30px;
}

.mypost-post-header .clock{
  width: 16px;
  height: 16px;
}

.mypost-post-header .time {
  color: #999;
  margin-left: 10px;
  font-size: 14px;
}

</style>
