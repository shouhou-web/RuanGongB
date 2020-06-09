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
import {getMyPost} from '../../network/user'
export default {
  name: "MyPost",
  data() {
    return {
      myPost: []
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
  width: 770px;
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
