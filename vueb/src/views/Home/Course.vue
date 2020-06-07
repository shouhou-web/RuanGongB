<template>
  <div class="discuss">
    <!-- 置顶及新手入门 -->
    <div class="post-top">
      <!-- 置顶 -->
      <div @click="toPost(item.postID)" v-for="(item, index) in top" :key="index" class="pt-i">
        <div class="tag">
          置顶
        </div>
        <div class="title navigation">
          {{ item.title }}
        </div>
      </div>
    </div>
    <!-- 帖子列表 -->
    <div class="discuss-post" v-for="(item, index) in post" :key="index">
      <div v-if="canRead(item.postLevel)" @click="toPost(item.postID)">
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
      <div v-else @click="warn(item.postLevel)">
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
          <div class="postLevel">
            <div class="postLevel-content">
              等级Lv.{{ item.postLevel }}以上可浏览
            </div>
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
    <router-view></router-view>
  </div>
</template>

<script>
import { request } from "../../network/request";
import { getPostList } from "../../network/post";
export default {
  name: "Course",
  data() {
    return {
      top: [
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
        }
      ],
      post: [
        {
          postID: 123,
          userID: 233,
          userName: "用户名",
          userLevel: 1,
          postLevel : 3,
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
          postLevel : 12,
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
  computed: {
    level() {
      return this.$store.state.user.userLevel;
    }
  },
  methods: {
    warn(postLevel) {
      this.$message({
        type : 'warning',
        message: '该帖子需要用户权限等级Lv.' + postLevel + '以上才可浏览~'
      })
    },
    canRead(postLevel) {
      return this.$store.state.user.userLevel >= postLevel;
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
    },
    getLevel(level) {
      return "https://img-static.mihoyo.com/level/level" + level + ".png";
    }
  },
  mounted() {
    getPostList(2)
      .then(res => {
        if (res == null)
          this.$message({
            message: "没有该类型帖子的信息~",
            type: "warning"
          });
        else {
          this.post = res.filter((item) => {
            return item.stickState == 0
          });
          this.top = res.filter((item) => {
            return item.stickState == 1
          });
        }
      })
      .catch(err => {
        this.$message.error("获取信息失败了~请检查您的网络");
      });
  }
};
</script>
<style>
.post-top {
  border-bottom: 1px solid #ebebeb;
  display: flex;
  flex-direction: column;
  padding: 12px 30px;
}

.pt-i {
  display: flex;
  padding: 10px;
}

.pt-i .tag {
  border: 1px solid #4cc3ff;
  color: #4cc3ff;
  margin-right: 10px;
  height: 18px;
  border-radius: 2px;
  text-align: center;
  line-height: 18px;
  font-weight: 600;
  padding: 0 4px;
}

.pt-i .title {
  line-height: 20px;
  font-weight: 600;
  font-size: 15px;
}

.discuss {
  background-color: #fff;
  margin: 20px 20px 20px 0;
  line-height: 50px;
  width: 790px;
  border-radius: 4px;
}
</style>
