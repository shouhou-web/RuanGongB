<template>
  <div class="post">
    <!-- 左主界面 -->
    <div class="main">
      <!-- 文章主体 -->
      <div class="article">
        <div class="title">
          <h1>{{ post.title }}</h1>
        </div>
        <!-- 预览各项数据 -->
        <div class="preview">
          <div class="p-identify">来自板块：</div>
          <div class="p-identify2">{{ type[post.postIdentity] }}</div>
          <div class="discuss-post-bottom">
            <div class="discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/preview.png" alt="" />
              <div class="num">{{ post.browseNum }}</div>
            </div>
            <div class="discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/likes.png" alt="" />
              <div class="num">{{ post.likesNum }}</div>
            </div>
            <div class="discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/comment.png" alt="" />
              <div class="num">{{ post.commentNum }}</div>
            </div>
          </div>
        </div>
        <!-- 文章发表时间 -->
        <div class="time">
          文章发表：{{ post.createTime }} | 最后编辑：{{ post.editTime }}
        </div>
        <!-- 帖子内容 -->
        <div class="content" v-html="post.content">
          {{ post.content }}
        </div>
        <!-- 分割线 -->
        <div class="div"></div>
        <!-- 点赞按钮 -->
        <div class="likes-end">
          <img
            @click="likesClick"
            v-if="!isLikes"
            src="../../assets/Icon/Post/likes.png"
            alt=""
          />
          <img
            @click="unlikesClick"
            v-else
            src="../../assets/Icon/Post/likes_cur.png"
            alt="图片无法加载QAQ"
          />
          {{ post.likesNum }}
        </div>
      </div>
      <!-- 评论框 -->
      <div id="new-comment" class="new-comment">
        <div class="title">
          看帖是喜欢，评论才是真爱：
        </div>
        <textarea
          class="textarea"
          type="textarea"
          placeholder="请开始你的表演"
          v-model="newcomment"
          maxlength="1000"
          show-word-limit
        >
        </textarea>
        <el-button @click="commentPush" class="comment-submit" type="primary"
          >评论</el-button
        >
      </div>
      <!-- 评论区 -->
      <ul class="comment">
        <li
          class="comment-item"
          v-for="(item, index) in post.listComment"
          :key="index"
        >
          <img class="img" :src="item.imagePath" alt="QAQ" />
          <div class="main">
            <div class="main-top">
              <div class="main-top-title">
                <div class="name">
                  {{ item.userName }}
                </div>
                <img class="level" :src="getLevel(item.userLevel)" alt="" />
              </div>
              <div
                v-if="isAdmin"
                @click="manageProfile(item.userID)"
                class="main-top-manage"
              >
                管理
              </div>
            </div>
            <div class="content">
              {{ item.content }}
            </div>
            <div class="bottom">
              <div class="date">
                {{ item.createTime }}
              </div>
              <!-- <div class="likes">
                <img
                  class="likes-img"
                  v-if="!isLikes"
                  src="../../assets/Icon/Post/likes.png"
                  alt="QAQ"
                />
                <img
                  class="likes-img"
                  v-else
                  src="../../assets/Icon/Post/likes_cur.png"
                  alt="QAQ"
                />
                {{ post.likesNum }}
              </div> -->
            </div>
          </div>
        </li>
      </ul>
    </div>
    <!-- 右侧展示栏 -->
    <div class="aside">
      <!-- 作者 -->
      <div class="author">
        <img class="head" :src="post.imagePath" alt="图片无法加载QAQ" />
        <div class="xxx">
          <div
            @click="manageProfile(post.userID)"
            style="margin-left: 5px;"
            v-if="isAdmin"
            class="profile-manage"
          >
            管理
          </div>
          <div class="author-content">
            <div class="author-name">
              {{ post.userName }}
            </div>
            <img class="level" :src="getLevel(post.userLevel)" alt="" />
          </div>
        </div>
      </div>
      <!-- 所属板块 -->
      <div class="section-head">
        所属板块
      </div>
      <div class="div"></div>
      <div class="section-body">
        <img src="../../assets/Icon/Post/section.png" alt="图片无法加载QAQ" />
        <div class="content">
          {{ type[post.postIdentity] }}
        </div>
      </div>
    </div>
    <!-- 左侧固定可选操作 -->
    <div class="post-option">
      <div @click="commentClick">
        <img src="../../assets/Icon/Post/comment.png" alt="" />
      </div>
      <div>
        <img
          v-if="!isLikes"
          @click="likesClick"
          src="../../assets/Icon/Post/likes.png"
          alt=""
        />
        <img
          v-else
          @click="unlikesClick"
          src="../../assets/Icon/Post/likes_cur.png"
          alt=""
        />
      </div>
      <div @click="manageClick">
        <img v-if="isMyPost" src="../../assets/Icon/Post/manage.png" alt="" />
        <img
          v-else-if="isAdmin"
          src="../../assets/Icon/Post/manage.png"
          alt=""
        />
      </div>
    </div>
    <!-- 阴影 -->
    <div
      :class="[manage ? '' : 'page-mask-show']"
      @click="manage = !manage"
      class="page-mask"
    ></div>
    <!-- 悬浮窗 -->
    <div :class="[manage ? 'page-state' : 'page-state-hide']" class="open-page">
      <div class="hover-post login">
        <div class="hover-radio-title-post">
          管理
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-radio-post">
          <label v-if="isAdmin && post.stickState == 0" 
          class="hover-radio-i-post">
            <input
              name="type"
              type="radio"
              id="0"
              value="置顶帖子"
              v-model="manageType"
            />
            置顶帖子
          </label>
          <label
            v-else-if="isAdmin && post.stickState == 1"
            class="hover-radio-i-post"
          >
            <input
              name="type"
              type="radio"
              id="0"
              value="取消置顶"
              v-model="manageType"
            />
            取消置顶
          </label>
          <label v-else class="hover-radio-i-post">
            <input
              name="type"
              type="radio"
              id="0"
              value="编辑"
              :checked="manageType == '编辑'"
              v-model="manageType"
            />
            编辑
          </label>
          <label class="hover-radio-i-post" style="color:red">
            <input
              name="type"
              type="radio"
              id="0"
              value="删除"
              v-model="manageType"
            />
            删除
          </label>
        </div>
        <!-- <div class="login-div"></div> -->
        <div class="hover-radio-bottom-post">
          <button @click="cancel" class="hover-radio-button-post cancel">
            取消
          </button>
          <button @click="assure" class="hover-radio-button-post assure">
            确定
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  downPost,
  likePost,
  pushComment,
  stickPost,
  deletePost
} from "../../network/post";
import {isNotNew} from '../../network/user'
export default {
  name: "Post",
  data() {
    return {
      userID: 0,
      type: [
        "新手上路",
        "讨论区",
        "课程推荐",
        "校园周边",
        "资源共享",
        "刷题板块"
      ],
      manage: false,
      manageType: "编辑",
      newcomment: "",
      post: {
        postID: 123,
        userID: 233,
        userLevel: 9,
        postIdentity: 2,
        stickState: 0,
        content: `<div>帖子内容</div>`,
        userName: "用户名",
        accessoryPath: "附件路径",
        imagePath:
          "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
        title: "写大作业好开心啊",
        likesNum: 1,
        browseNum: 1,
        commentNum: 2,
        createTime: "05-24",
        editTime: "05-24",
        listComment: [
          {
            commentID: 1,
            userID: 233,
            postID: 123,
            level: 9,
            createTime: "2020-05-21",
            likesNum: 25,
            content:
              "这个大作业是今天写完还是明天写完，我永远也不知道，也许写不完了",
            userName: "用户名",
            imagePath:
              "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
            userLevel: 2
          },
          {
            commentID: 1,
            userID: 233,
            postID: 123,
            level: 9,
            createTime: "2020-05-21",
            likesNum: 25,
            content: "评论内容",
            userName: "用户名",
            imagePath:
              "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
            userLevel: 2
          },
          {
            commentID: 1,
            userID: 233,
            postID: 123,
            level: 9,
            createTime: "2020-05-21",
            likesNum: 25,
            content: "评论内容",
            userName: "用户名",
            imagePath:
              "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
            userLevel: 2
          },
          {
            commentID: 1,
            userID: 233,
            postID: 123,
            level: 9,
            createTime: "2020-05-21",
            likesNum: 25,
            content: "评论内容",
            userName: "用户名",
            imagePath:
              "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
            userLevel: 2
          }
        ],
        listLikes: [10000]
      }
    };
  },
  methods: {
    commentPush() {
      if (this.newcomment.length <= 5) {
        this.$message({
          message: "请书写至少五个字的评论~",
          type: "warning"
        });
        return;
      }
      let postID = this.post.postID;
      let userID = this.userID;
      let content = this.newcomment;
      pushComment(userID, postID, content)
        .then(res => {
          if (res == null) this.$message.error("评论失败了~请检查您的网络");
          else this.post.listComment.push(res);
        })
        .catch(err => {
          this.$message.error("评论失败了~请检查您的网络");
        });
    },
    likesClick() {
      this.post.listLikes.push(this.userID);
      this.post.likesNum += 1;
    },
    unlikesClick() {
      let id = this.post.listLikes.indexOf(this.userID);
      console.log(id);
      this.post.listLikes.splice(id, 1);
      this.post.likesNum -= 1;
    },
    manageClick() {
      this.manage = true;
    },
    commentClick() {
      let el = document.getElementById("new-comment");
      console.log(el);
      this.$nextTick(function() {
        window.scrollTo({ behavior: "smooth", top: el.offsetTop-70 });
      });
    },
    cancel() {
      this.manage = false;
    },
    assure() {
      if (this.manageType == "编辑") this.toEdit();
      else if (this.manageType == "删除") {
        let postID = this.post.postID;
        let userID = this.$store.state.user.userID;
        deletePost(postID, userID)
          .then(res => {
            if (res == 0) {
              this.$message({
                message: "删除成功",
                type: "success"
              });
              this.$router.push({ path: "/home" });
            } else this.$message.error("删除失败，请检查您的网络~");
          })
          .catch(err => {
            this.$message.error("删除失败，请检查您的网络~");
          });
      } else if (this.manageType == "置顶帖子") {
        console.log("置顶帖子");
        let postID = this.post.postID;
        let adminID = this.$store.state.user.userID;
        let stickstate = 1;
        stickPost(postID, adminID, stickstate)
          .then(res => {
            if (res == 0)
              this.$message({
                message: "置顶成功",
                type: "success"
              });
            else this.$message.error("置顶失败，请检查您的网络~");
          })
          .catch(err => {
            this.$message.error("置顶失败，请检查您的网络~");
          });
      } else if (this.manageType == "取消置顶") {
        console.log("取消置顶");
        let postID = this.post.postID;
        let adminID = this.$store.state.user.userID;
        let stickstate = 0;
        stickPost(postID, adminID, stickstate)
          .then(res => {
            if (res == 0)
              this.$message({
                message: "取消置顶成功",
                type: "success"
              });
            else this.$message.error("取消置顶失败，请检查您的网络~");
          })
          .catch(err => {
            this.$message.error("取消置顶失败，请检查您的网络~");
          });
      }
    },
    toEdit() {
      let post = this.post;
      this.$router.push({
        path: "/editPost",
        query: {
          post: post
        }
      });
    },
    getLevel(level) {
      return "https://img-static.mihoyo.com/level/level" + level + ".png";
    },
    manageProfile(userID) {
      this.$router.push({
        path: "/manageProfile",
        query: { userID: userID }
      });
    }
  },
  computed: {
    isAdmin() {
      return this.$store.state.user.userIdentity == 1;
    },
    isLikes() {
      let userID = this.userID;
      return (
        this.post.listLikes.filter(item => {
          return item == userID;
        }).length == 1
      );
    },
    isMyPost() {
      let userID = this.userID;
      return userID == this.post.userID;
    }
  },
  created() {
    //发送请求
    console.log(this.$route.query.postID);
    let postID = this.$route.query.postID;
    this.userID = this.$store.state.user.userID;
    downPost(postID)
      .then(post => {
        //下载帖子数据
        console.log(this.post);
        console.log(post);
        this.post = post;
      })
      .catch(err => {
        this.$message.error("加载失败了~请检查您的网络");
      });
  },
  beforeDestroy() {
    console.log("destroy");
    let userID = this.userID;
    let postID = this.post.postID;
    let state = this.post.listLikes.filter(item => {
      return item == userID;
    }).length;
    likePost(userID, postID, state)
      .then(res => {
        if (res == 1) {
          this.$message.error("点赞数据保存失败了~请检查您的网络");
        }
      })
      .catch(err => {
        this.$message.error("加载失败了~请检查您的网络");
      });
    if (this.post.postID == 9999) {
      isNotNew(userID);
    }
  }
};
</script>
<style>
.xxx {
  margin-bottom: 35px;
}
/* 悬浮窗 */
.hover-post {
  height: 247px;
  width: 480px;
  position: fixed;
  background-color: #fff;
}

.hover-radio-title-post {
  display: flex;
  justify-content: flex-start;
  padding: 0 30px;
  font-size: 16px;
  font-weight: 600;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
}

.hover-radio-post {
  display: flex;
  flex-direction: column;
  width: 480px;
  padding: 0px 30px;
  height: 112px;
  line-height: 50px;
  align-content: center;
  justify-content: space-between;
  background-color: #fff;
  border-bottom: 1px solid #ebebeb;
}

.hover-radio-i-post {
  display: flex;
  align-content: center;
  align-items: center;
  padding: 2px;
  width: 100px;
  margin-left: 30px;
  line-height: 50px;
}

.hover-radio-i-post input {
  margin-right: 10px;
}

.hover-radio-bottom-post {
  display: flex;
  width: 480px;
  -webkit-box-pack: center;
  text-align: center;
  padding: 10px 0 30px 0;
  justify-content: center;
}

.hover-radio-button-post {
  width: 104px;
  height: 32px;
  margin: 0 20px;
  box-sizing: border-box;
  border-radius: 4px;
  border: 1px solid #ccc;
  outline: none;
}

.cancel {
  color: #666;
  background-color: #fff;
}

.assure {
  background-color: #4cc3ff;
  border-color: #4cc3ff;
  color: #fff;
}
/* 可选选项 */
.post-option {
  position: fixed;
  top: 95px;
  left: 68%;
  margin-left: -50%;
  width: 46px;
  background-color: #fff;
  border-radius: 4px;
  padding: 0 6px;
  -webkit-box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.15);
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.15);
}

.post-option img {
  width: 22px;
  height: 22px;
  margin: 12px 0;
}

.post {
  display: flex;
  margin-top: 30px;
  height: auto;
}
/* 主文章 */
.post .main .article {
  display: flex;
  line-height: 30px;
  flex-direction: column;
  align-items: center;
  width: 700px;
  border-radius: 5px;
  margin: 5px;
  padding: 40px 50px;
  background-color: #fff;
}

.post .main .article .title {
  display: flex;
  width: 100%;
}

.post .main .article h1 {
  font-size: 22px;
  line-height: 26px;
  margin: 0;
  word-break: break-word;
  display: flex;
}

.post .main .article .preview {
  display: flex;
  align-items: center;
  height: 40px;
  padding: 0 15px;
  background-color: #f7f8fc;
  width: 600px;
  margin-top: 30px;
}

.p-identify {
  color: #999;
  font-size: 14px;
}

.p-identify2 {
  color: #fa0;
  font-size: 14px;
  margin-right: 280px;
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

.post .main .article .preview .discuss-post-bottom img {
  margin-left: 20px;
  width: 17.45px;
  height: 16px;
  color: #ccc;
}

.post .main .article .preview .discuss-post-bottom .num {
  display: flex;
  color: #ccc;
  display: inline-block;
  width: 10px;
  margin-left: 5px;
}

.post .main .article .time {
  color: #ccc;
  margin-top: 20px;
  font-size: 14px;
  display: flex;
  justify-content: center;
  align-content: center;
  align-items: center;
}

.post .main .article .content {
  margin: 20px 0;
  padding: 20px;
}

.post .main .article .div {
  height: 2px;
  background-color: lightgrey;
  width: 80%;
}

.post .main .article .likes-end {
  display: flex;
  align-items: center;
  margin: 20px;
  padding: 20px;
}

.post .main .article .likes-end img {
  width: 40px;
  height: 40px;
  margin-right: 20px;
}

/* 侧边栏 */
.post .aside {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  padding: 5px;
}

/* 侧边栏-作者 */
.post .aside .author {
  display: flex;
  align-items: center;
  border-radius: 5px;
  background-color: #fff;
  width: 280px;
  height: 160px;
  padding: 0 20px 0 30px;
}

.post .aside .author .head {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}

.post .aside .author .author-content {
  display: flex;
  justify-content: space-around;
  line-height: 30px;
  line-height: 50px;
  align-items: center;
}

.post .aside .author .author-content .author-name {
  color: #666;
  line-height: 22px;
  font-weight: 600;
  font-size: 16px;
  margin-right: 6px;
  margin-left: 15px;
  white-space: normal;
  display: inline;
  max-width: 180px;
}
.main .main-top .main-top-title .level,
.post .aside .author .author-content .level {
  height: 16px;
  font-size: 14px;
  display: inline-block;
  vertical-align: middle;
}

.main .main-top .main-top-title .level {
  margin-top: 3px;
}

/* 侧边栏-所属模块 */
.post .aside .section-head {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  width: 280px;
  height: 80px;
  margin-top: 30px;
}

.post .aside .div {
  height: 1px;
  background-color: lightgrey;
  width: 100%;
}

.post .aside .section-body {
  background-color: #fff;
  display: flex;
  align-items: center;
  width: 280px;
  height: 90px;
  padding: 20px;
}

.post .aside .section-body .content {
  margin-left: 20px;
}

/* 评论输入 */
.post .main {
  height: auto;
}

.post .main .new-comment {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 700px;
  height: 245px;
  padding: 30px 50px;
  background-color: #fff;
  margin: 20px 5px;
  border-radius: 5px;
}

.post .main .new-comment .title {
  display: flex;
  justify-content: flex-start;
  line-height: 30px;
  font-size: 14px;
  color: #ccc;
}

.post .main .new-comment .textarea {
  border: 0;
  border: 1px solid #f0f1f5;
  border-radius: 5px;
  background-color: #fff;
  width: 600px;
  height: 100px;
  padding: 10px;
  resize: none;
  outline: none;
}

.comment-submit {
  widows: 86px;
  height: 34px;
  margin: 20px 10px 5px 500px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 评论样式 */
.post .main .comment {
  margin-bottom: 30px;
}

.post .main .comment .comment-item {
  background-color: #fff;
  width: 700px;
  height: 148px;
  padding: 24px 50px;
  line-height: 22px;
  font-size: 14px;
  display: flex;
  border-radius: 4px;
  justify-content: flex-start;
  margin: 0px 5px;
  border-bottom: 1px solid #ebebeb;
}

.post .main .comment .img {
  width: 44px;
  height: 44px;
  border-radius: 50%;
}

.post .main .comment .main {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  line-height: 30px;
  padding: 0 10px;
}

.post .main .comment .main .main-top {
  display: flex;
  justify-content: flex-start;
  width: 500px;
  font-size: 14px;
  line-height: 22px;
}

.post .main .comment .main .main-top-title {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
  height: 44px;
}

.aside .author .profile-manage {
  color: #fff;
  font-size: 14px;
  width: 60px;
  margin-left: 50px;
  margin-bottom: 15px;
}

.post .main .comment .main .main-top-manage {
  color: #fff;
  font-size: 14px;
  width: 60px;
  margin-left: 400px;
}

.aside .author .profile-manage:hover,
.post .main .comment .main .main-top-manage:hover {
  color: #ccc;
}

.post .main .comment .main .bottom {
  display: flex;
  flex-direction: row;
  margin-top: 5px;
}

.post .main .comment .main .bottom .date {
  color: #ccc;
  font-size: 14px;
  line-height: 30px;
  display: flex;
  width: 100%;
  justify-content: flex-end;
  margin-top: -10px;
}

.post .main .comment .main .bottom .likes {
  margin-left: 400px;
  display: flex;
  width: 30px;
  justify-content: space-between;
  align-items: center;
}

.post .main .comment .main .bottom .likes-img {
  width: 15.33px;
  height: 15.33px;
}

.el-main {
  background-color: #f0f1f5 !important;
  height: auto;
}
</style>
